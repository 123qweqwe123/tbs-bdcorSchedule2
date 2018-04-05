package com.bdcor.peace3.mobileSendRule.schedule;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bdcor.datasource.MultipleDataSource;
import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.ruleCheck.Group;
import com.bdcor.peace3.mobileSendRule.utile.db.OracleHandler;
import com.bdcor.peace3.mobileSendRule.utile.properties.PropertiesUtils;
import com.bdcor.peace3.mobileSendRule.web.MyApp;
import com.bdcor.utils.MailUtils;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;

/**
 * 检查短信发送状态
 * 
 * @author Administrator
 */
@Component
public class MobileSendStatusCheck implements IScheduleTaskDealSingle<String> {

	@Autowired
    private CommDao commDao;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	Set<String> handlerD = new HashSet<String>();
	
	@PostConstruct
	public void init(){
		try {
			MyApp.startWeb();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> selectTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> taskItemList,
			int eachFetchDataNum) throws Exception {
		List<String> returnList = new ArrayList<String>();
		Date date = new Date();
		//String d = DateUtils.format(date, "yyyyMMdd");
		String d = new SimpleDateFormat("yyyyMMdd").format(date);
		logger.info(date.toLocaleString());
		if(handlerD.contains(d)){
			logger.info("数据已经处理:"+d);
			returnList.add("1");
			return null;
		}
		

//		OracleHandler.getHandler().open();
		
		StringBuffer sb = new StringBuffer();
		sb.append("短信回执状态不正确"+d+"====>"+System.lineSeparator());
		for(String line:sendState(d)){
			sb.append(line+System.lineSeparator());
		}
		
		sb.append(System.lineSeparator()+System.lineSeparator()+System.lineSeparator()+"欢迎短信发送不正确"+d+"====>"+System.lineSeparator());
		for(String line:wellcomeMsgSend(d)){
			sb.append(line+System.lineSeparator());
		}
		
		if(StringUtils.isNotBlank(sb.toString())){
			//记录日志和发送email
			FileUtils.writeStringToFile(new File("peace3_mobile_send/mobileSendStatusCheck-"+d+".txt"), sb.toString());
			MailUtils.sendMail(PropertiesUtils.getInstance().getProByKey("send_email"), "短信发送状态&&欢迎类短信发送情况",sb.toString());
		}
		
		
		handlerD.add(d);
		
//		OracleHandler.getHandler().close();
		
		
		returnList.add("2");
		return returnList;
	}

	@Override
	public Comparator<String> getComparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean execute(String task, String ownSign) throws Exception {
		
		//logger.info("obj:"+task);
		logger.info("处理数据个数:"+task);
		
		
		
		return true;
	}
	
	public static void main(String[] a) throws Exception{
		MobileSendStatusCheck check = new MobileSendStatusCheck();
		check.selectTasks("", "", 1, null, 1);
	}
	
	
	/**
	 * 短信发送状态
	 * @param d
	 * @return
	 */
	private List<String> sendState(String d){
		
		List<String> rList = new ArrayList<String>();
		List<Map<String, Object>> list = null;
		try {
			MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
			list = commDao.query("select PATIENT_ID,id,STATE,msg_name,REPORT_CODE from pip_msg_send where to_char(SENDTIME_PREINSTALL,'yyyymmdd')='"+d+"' and (REPORT_CODE is null or (REPORT_CODE is not null and STATE='2'))");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list!=null){
			for(Map<String,Object> map:list){
				String v = map.get("id".toUpperCase())
						+"###"+map.get("PATIENT_ID")
						+"###"+map.get("STATE")
						+"###"+map.get("msg_name".toUpperCase())
						+"###"+map.get("REPORT_CODE");
				logger.info("短信发送状态 查找到数据:==>"+v);
				rList.add(v);
			}
		}
		logger.info(" 短信发送状态 短信条数:==>"+rList.size());
		return rList;
	}

	
	/**
	 * 欢迎类短信发送情况
	 * @param d
	 * @return
	 */
	private List<String> wellcomeMsgSend(String d){
		
		String msgType = "M006";
		
		List<String> wellcomeList = new ArrayList<String>();
		List<Map<String, Object>> list2 = null;
		try {
			MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
			list2 = commDao.query(
						"select p.patient_id, p.patient_name,send.msg_id from pip_comm_patient p left join ("
					  +"select send1.* from PIP_MSG_SEND send1 left join PIP_MSG_MSGTMP msg on send1.msg_id=msg.ID where msg.MSG_TYPE_CODE like '%"+msgType+"%'" 
					  +") send on p.patient_id=send.patient_id  where to_char(p.GROUP_DATE,'yyyyMMdd')='"+d+"'");
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(list2!=null){
			for(Map<String,Object> map:list2){
				String v = map.get("PATIENT_ID")
						+"###"+map.get("PATIENT_NAME")
						+"###"+map.get("msg_id".toUpperCase());
				logger.info("欢迎短信 查找到数据:==>"+v);
				if(map.get("msg_id".toUpperCase())==null){
					wellcomeList.add(v);
				}
				
			}
		}
		
		logger.info("欢迎短信  短信条数:==>"+wellcomeList.size());
		return wellcomeList;
	}
}
