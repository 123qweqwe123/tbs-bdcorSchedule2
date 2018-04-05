package com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.util.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bdcor.datasource.MultipleDataSource;
import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate.SendMsg;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByWeek.SendCountInWeek;
import com.bdcor.peace3.mobileSendRule.utile.db.OracleHandler;

public class MsgSendedOfPatient {
	static Logger log = LoggerFactory.getLogger(MsgSendedOfPatient.class); 
	
    private CommDao commDao;
	
	public String patientId;
	
	public Map<String,MsgSendedByDate> sendByDate = new TreeMap<String,MsgSendedByDate>(
			
			new Comparator<String>(){
		@Override
		public int compare(String o1, String o2) {
			try {
				Date d1 = DateUtils.parseIso8601Date(o1);
				Date d2 = DateUtils.parseIso8601Date(o2);
				if(d1.after(d2)){
					return 1;
				}if(d1.before(d2)){
					return -1;
				}else{
					return 0;
				}
			} catch (ParseException e) {
				log.error("",e);
			}
			return 0;
		}
		
	}
	
	);
	
	
	
	public Map<String,MsgSendedByWeek> sendByWeek = new TreeMap<String,MsgSendedByWeek>(new Comparator<String>(){
		@Override
		public int compare(String o1, String o2) {
			int d1 = Integer.parseInt(o1);
			int d2 = Integer.parseInt(o2);
			if(d1 >d2 ){
				return 1;
			}else if(d1<d2){
				return -1;
			}else{
				return 0;
			}
		}
		
	});
	
	
	private String folderName = "sendedMsg";
	
	public MsgSendedOfPatient(String patientid,CommDao commDao){
		this.commDao = commDao;
		this.patientId = patientid;
		load();
	}
	
	/**
	 * 当天数据
	 * @return
	 */
	public MsgSendedByDate getCurrentSendMsg(){
		//String currentD = DateUtils.format(new Date(), "yyyy-MM-dd");
		String currentD = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		return this.sendByDate.get(currentD);
	}
	
	/**
	 * 当周数据
	 * @return
	 */
	public MsgSendedByWeek getCurrtentWeekSendMsg(){
		DateTime datetime = DateTime.now();
		int weekOfday = datetime.getWeekOfWeekyear();
		
		MsgSendedByWeek msgWeek = new MsgSendedByWeek(weekOfday+"");
		
		for(DateTime time:MsgSendedByWeek.getDateOfCurrentWeek()){
			String day = time.toString("yyyy-MM-dd");
			MsgSendedByDate sendDay = this.sendByDate.get(day);
			if(sendDay!=null){
				for(SendMsg msg:sendDay.sendMsg){
					if(msg!=null){
						SendCountInWeek sendCountOfTypeInWeek = new SendCountInWeek(msg.msgType,1);
						msgWeek.addSendCountInWeek(sendCountOfTypeInWeek);
					}
				}
			}
		}
		if(msgWeek.hasData()){
			this.sendByWeek.put(msgWeek.getWeekKey(), msgWeek);
		}
		
		if(this.sendByWeek.size()>0){
			saveWeekSendedMsg();
		}
		
		return msgWeek;
	}
	/**
	 * 短信个数
	 * @param msgid
	 * @return
	 */
	public int msgCountByMsgId(String msgid){
		int count = 0;
		for(Entry<String, MsgSendedByDate> entry:sendByDate.entrySet()){
			if(entry.getValue() != null){
				for(SendMsg msg:entry.getValue().sendMsg){
					if(msg.msgId!=null && msgid.equals(msg.msgId)){
						count ++;
					}
				}
			}
		}
		return count;
	}
	
	/**
	 * 
	 */
	private void saveWeekSendedMsg(){
		StringBuffer sb = new StringBuffer();
		for(Entry<String,MsgSendedByWeek> entry:this.sendByWeek.entrySet()){
			sb.append(entry.getValue().toString());
		}
		
		try {
			FileUtils.writeStringToFile(this.storeMsgFileOfWeek(),sb.toString());
		} catch (IOException e) {
			log.error("",e);
		}
		
	}
	
	public void add(MsgSendedByDate sendByDate){
		
		if(this.sendByDate.containsKey(sendByDate.getDayKey())){
			this.sendByDate.get(sendByDate.getDayKey()).sendMsg.addAll(sendByDate.sendMsg);
		}else{
			this.sendByDate.put(sendByDate.getDayKey(), sendByDate);
		}
		
		try {
			FileUtils.writeStringToFile(this.storeMsgFileOfDay(), sendByDate.toString(),true);
		} catch (IOException e) {
			log.error("",e);
		}
		
	}
	
	
	private void load(){
		this.loadFormFile();
	
		if(sendByDate.size()==0){
			this.loadFormDB();
			return;
		}
		
	}
	
	private void loadFormFile(){
		List<String> lines = null;
		try {
			if(this.storeMsgFileOfDay().exists()){
				lines = FileUtils.readLines(this.storeMsgFileOfDay());
			}
		} catch (IOException e) {
			log.error("",e);
		}
		
		if(lines!=null && lines.size()>0){
			for(String line:lines){
				MsgSendedByDate msgSended = new MsgSendedByDate(line);
				this.sendByDate.put(msgSended.getDayKey(), msgSended);
			}
		}
		
		
	}
	
	private void loadFormDB(){

		String sql = "select msg.ID,msg.MSG_TYPE_CODE,send.SENDTIME_PREINSTALL from pip_msg_send send left join pip_msg_msgtmp msg on send.msg_id=msg.ID where send.PATIENT_ID='"+this.patientId+"'";
		
		try {
			MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
			List<Map<String, Object>> list = commDao.query(sql);
			
			for(Map<String,Object> map:list){
				String msgId = map.get("ID").toString();
				String msgType = map.get("MSG_TYPE_CODE").toString();
				String sendTime = map.get("SENDTIME_PREINSTALL").toString();
				
				MsgSendedByDate msgSended = new MsgSendedByDate(sendTime,msgType,msgId);
				this.sendByDate.put(msgSended.getDayKey(), msgSended);
				
			}
			
		} catch (Exception e) {
			log.error("",e);
		}
		
		
		StringBuffer sb = new StringBuffer();
		for(MsgSendedByDate sendByDate:this.sendByDate.values()){
			sb.append(sendByDate.toString());
		}
		try {
			FileUtils.writeStringToFile(this.storeMsgFileOfDay(),sb.toString() ,true);
		} catch (IOException e) {
			log.error("",e);
		}
		
		
		
		for(MsgSendedByDate sendByDate2:this.sendByDate.values()){
			DateTime datetime = DateTime.parse(sendByDate2.getDayKey(), DateTimeFormat.forPattern("yyyy-MM-dd"));
			for(Entry<String, List<DateTime>> entry:MsgSendedByWeek.getDateOfWeekFromDate(datetime).entrySet()){
				String week = entry.getKey();
				MsgSendedByWeek msgWeek = new MsgSendedByWeek(week);
				for(DateTime time:entry.getValue()){
					String day = time.toString("yyyy-MM-dd");
					MsgSendedByDate sendDay = this.sendByDate.get(day);
					if(sendDay!=null){
						for(SendMsg msg:sendDay.sendMsg){
							if(msg!=null){
								SendCountInWeek sendCountOfTypeInWeek = new SendCountInWeek(msg.msgType,1);
								msgWeek.addSendCountInWeek(sendCountOfTypeInWeek);
							}
						}
					}
				}
				if(msgWeek.hasData()){
					this.sendByWeek.put(msgWeek.getWeekKey(), msgWeek);
				}
			}
			break;
		}

		if(this.sendByWeek.size()>0){
			saveWeekSendedMsg();
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	private File storeMsgFileOfDay(){
		return new File(this.folderName+File.separator+this.patientId+File.separator+"sendedMsg.txt");
	}
	
	/**
	 * 
	 * @return
	 */
	private File storeMsgFileOfWeek(){
		return new File(this.folderName+File.separator+this.patientId+File.separator+"week.txt");
	}

}
