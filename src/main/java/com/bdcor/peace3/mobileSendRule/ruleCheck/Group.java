package com.bdcor.peace3.mobileSendRule.ruleCheck;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcor.datasource.MultipleDataSource;
import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.obj.Patient;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate.SendMsg;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByWeek;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByWeek.SendCountInWeek;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedCache;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedOfPatient;
import com.bdcor.peace3.mobileSendRule.utile.properties.PropertiesUtils;


public abstract class Group {
	
    private CommDao commDao;
	
	static Logger log = LoggerFactory.getLogger(Group.class);
	
	MsgSendedOfPatient msgSended = null;
	List<Patient> patients = null;
	public Group(List<Patient> patients,CommDao commDao){
		this.patients = patients;
		this.commDao = commDao;
	}
	
	Patient patient;
	public String handler(){
		StringBuffer sb = new StringBuffer();
		for(Patient patient:patients){
			this.patient = patient;
			String patientCheck = patientHandler();
			if(StringUtils.isNotBlank(patientCheck)){
				sb.append(patientCheck+System.lineSeparator());
			}
		}
		return sb.toString();
	}
	

	public String patientHandler() {
		msgSended = MsgSendedCache.getInstance().getPatientSendedMsg(patient.id,commDao);
		
		//String d = DateUtils.format(new Date(), "yyyyMMdd");
		String d = new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		String sql = "select msg.ID,msg.MSG_TYPE_CODE,send.SENDTIME_PREINSTALL from pip_msg_send send left join pip_msg_msgtmp msg on send.msg_id=msg.ID where to_char(send.SENDTIME_PREINSTALL,'yyyymmdd')='"+d+"' and send.PATIENT_ID='"+patient.id+"'";
		try{
			MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
			List<Map<String, Object>> list = commDao.query(sql);
			for(Map<String,Object> map:list){
				String msgId = map.get("ID").toString();
				String msgType = map.get("MSG_TYPE_CODE").toString();
				String sendTime = map.get("SENDTIME_PREINSTALL").toString();
				
				MsgSendedByDate msg = new MsgSendedByDate(sendTime,msgType,msgId);
				msgSended.add(msg);
			}
		}catch(Exception e){
			log.error("", e);
		}
		
		StringBuffer sb = new StringBuffer();
		try {
			checkCurrentDate();
		} catch (Exception e) {
			String msg = this.patient.id+" "+this.patient.name+":"+e.getMessage();
			log.error(msg);
			sb.append(msg);
		}
		
		try {
			checkCurrentWeek();
		} catch (Exception e) {
			String msg = this.patient.id+" "+this.patient.name+":"+e.getMessage();
			log.error(msg);
			sb.append(msg);
		}
		
		try {
			checkCurrentMonth();
		} catch (Exception e) {
			String msg = this.patient.id+" "+this.patient.name+":"+e.getMessage();
			log.error(msg);
			sb.append(msg);
		}
		return sb.toString();
	}
	
	abstract void checkCurrentDate() throws Exception;
	abstract void checkCurrentWeek() throws Exception;
	abstract void checkCurrentMonth() throws Exception;

	
	
	void checkCurrentWeek(String typeFlag) throws Exception{
		MsgSendedByWeek msgSendedByWeek = msgSended.getCurrtentWeekSendMsg();
		Set<SendCountInWeek> sendMsg = msgSendedByWeek.sendMsg;
		if(sendMsg!=null){
			for(SendCountInWeek msgType:sendMsg){
				String type = msgType.msgType;
				int count = msgType.count;
				String normalCountStr = PropertiesUtils.getInstance().getProByKey("week_"+type+typeFlag);
				if(StringUtils.isNumeric(normalCountStr)){
					int normalCount = Integer.parseInt(normalCountStr);
					if(count>normalCount){
						//error 类别超过本周发送条数
						throw new Exception("类别超过本周发送条数:"+type);
					}
				}
				
			}
		}
	}
	/**
	 * 通用每天发送短信检查
	 * 	是否超过两条
	 * 	类别是否正确
	 * 	是否有重复发短信
	 * @throws Exception
	 */
	void checkCurrentDateCommon(String typeStart) throws Exception{
		MsgSendedByDate msgSendedByDate = msgSended.getCurrentSendMsg();
		if(msgSendedByDate!=null && 
				msgSendedByDate.sendMsg!=null && 
				msgSendedByDate.sendMsg.size()>2){
			//error  短信条数超过两条
			throw new Exception("短信条数超过两条");
		}
		if(msgSendedByDate!=null){
			for(SendMsg msg:msgSendedByDate.sendMsg){
				if(msg.msgType.equals("M099")){
					//跳过手机号确认短信
					continue;
				}
				if(!msg.msgType.startsWith(typeStart)){
					//error   短信类别不正确
					throw new Exception("短信类别不正确");
				}
				if(msgSended.msgCountByMsgId(msg.msgId)>1){
					//error   短信重复发送
					throw new Exception("短信重复发送.msgid:"+msg.msgId);
				}
			}
		}
	}
	/**
	 * 不受发送日期限制的短信类别。生日 随访提醒 欢迎 手机号确认短信
	 */
	static String notDateLimit = "M006|M007|M008|M099";
	
}
