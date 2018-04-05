package com.bdcor.peace3.mobileSendRule.ruleCheck;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.util.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bdcor.datasource.MultipleDataSource;
import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.obj.Patient;
import com.bdcor.peace3.mobileSendRule.utile.db.OracleHandler;
import com.bdcor.peace3.mobileSendRule.utile.properties.PropertiesUtils;
import com.bdcor.utils.MailUtils;

/**
 *  01：非糖尿病实验组 02:非糖尿病对照组 11:糖尿病实验组 12:糖尿病对照组
 * @author Administrator
 */
public class RuleCheckHandler {
	
    private CommDao commDao;
	
	static Logger log = LoggerFactory.getLogger(RuleCheckHandler.class);
	
	Group group01;
	Group group02;
	Group group11;
	Group group12;
	Group groupBirthday;
	Group groupFollowUp;
	
	private static RuleCheckHandler instance = null;
	public static RuleCheckHandler getInstance(){
		if(instance==null){
			instance = new RuleCheckHandler();
		}
		return instance;
	}
	
	public void group(List<Patient> patients,CommDao commDao) {
		this.commDao = commDao;
		List<Patient> groupPatient01 = new ArrayList<Patient>();
		List<Patient> groupPatient02 = new ArrayList<Patient>();
		List<Patient> groupPatient11 = new ArrayList<Patient>();
		List<Patient> groupPatient12 = new ArrayList<Patient>();
		List<Patient> groupPatientBirthday = new ArrayList<Patient>();
		List<Patient> groupPatientFollowUp = new ArrayList<Patient>();
		
		String nowDay = DateTime.now().toString("-MM-dd");
		String followUpDay = DateTime.now().plusDays(1).toString("yyyy-MM-dd");
		for(Patient p:patients){
			if(p.group.equals("01")){
				groupPatient01.add(p);
			}else if(p.group.equals("02")){
				groupPatient02.add(p);
			}else if(p.group.equals("11")){
				groupPatient11.add(p);
			}else if(p.group.equals("12")){
				groupPatient12.add(p);
			}
			//生日
			if(p.birthday!=null && p.birthday.contains(nowDay)){
				groupPatientBirthday.add(p);
			}
			try{
				//随访时间
				String sql = "select " +
						"SIX_PLAN_DATE,SIX_REAL_DATE," +
						"TWELVE_PLAN_DATE,TWELVE_REAL_DATE," +
						"EIGHTEEN_PLAN_DATE,EIGHTEEN_REAL_DATE," +
						"END_PLAN_DATE,END_REAL_DATE " +
						"from pip_comm_patient_date where PATIENT_ID='"+p.id+"'";
				MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
				List<Map<String, Object>> list = commDao.query(sql);
				for(Map<String,Object> map:list){
					String follow6Date = OracleHandler.objToString(map.get("SIX_PLAN_DATE"));
					String follow12Date = OracleHandler.objToString(map.get("TWELVE_PLAN_DATE"));
					String follow18Date = OracleHandler.objToString(map.get("EIGHTEEN_PLAN_DATE"));
					String follow24Date = OracleHandler.objToString(map.get("END_PLAN_DATE"));
					
					String follow6DateReal = OracleHandler.objToString(map.get("SIX_REAL_DATE"));
					String follow12DateReal = OracleHandler.objToString(map.get("TWELVE_REAL_DATE"));
					String follow18DateReal = OracleHandler.objToString(map.get("EIGHTEEN_REAL_DATE"));
					String follow24DateReal = OracleHandler.objToString(map.get("END_REAL_DATE"));
					//6
					if(follow6Date!=null && follow6Date.startsWith(followUpDay) && StringUtils.isBlank(follow6DateReal)){
						groupPatientFollowUp.add(p);
					}
					//12
					if(follow12Date!=null && follow12Date.startsWith(followUpDay) && StringUtils.isBlank(follow12DateReal)){
						groupPatientFollowUp.add(p);
					}
					//18
					if(follow18Date!=null && follow18Date.startsWith(followUpDay) && StringUtils.isBlank(follow18DateReal)){
						groupPatientFollowUp.add(p);
					}
					//24
					if(follow24Date!=null && follow24Date.startsWith(followUpDay) && StringUtils.isBlank(follow24DateReal)){
						groupPatientFollowUp.add(p);
					}
				}
			}catch(Exception e){
				log.error("", e);
			}
		}
		
		
		log.info("非糖尿病实验组 人数"+groupPatient01.size());
		log.info("非糖尿病对照组 人数"+groupPatient02.size());
		log.info("糖尿病实验组 人数"+groupPatient11.size());
		log.info("糖尿病对照组 人数"+groupPatient12.size());
		log.info("生日类短信 人数"+groupPatientBirthday.size());
		log.info("随访类短信 人数"+groupPatientFollowUp.size());
		
		StringBuffer sb = new StringBuffer();
		
		group01 = new Group01(groupPatient01,commDao);
		logMsg(sb,"非糖尿病实验组",group01);
		
		group02 = new Group02(groupPatient02,commDao);
		logMsg(sb,"非糖尿病对照组",group02);
		
		group11 = new Group11(groupPatient11,commDao);
		logMsg(sb,"糖尿病实验组",group11);
		
		group12 = new Group12(groupPatient12,commDao);
		logMsg(sb,"糖尿病对照组",group12);
		
		groupBirthday = new GroupBirthday(groupPatientBirthday,commDao);
		logMsg(sb,"生日类短信",groupBirthday);
		
		groupFollowUp = new GroupFollowUp(groupPatientFollowUp,commDao);
		logMsg(sb,"随访类短信",groupFollowUp);
		
		if(StringUtils.isNotBlank(sb.toString())){
			String d = new SimpleDateFormat("yyyyMMdd").format(new Date());

			if(StringUtils.isNotBlank(sb.toString())){
				//记录日志和发送email
				try {
					FileUtils.writeStringToFile(new File("peace3_mobile_send/ruleCheck-"+d+".txt"), sb.toString());
					MailUtils.sendMail(PropertiesUtils.getInstance().getProByKey("send_email"), "短信发送规则不正确", d+"====><br>"+sb.toString());
				} catch (Exception e) {
					log.error("",e);
				}
			}
		}
	}
	
	private void logMsg(StringBuffer sb,String typeStr,Group group){
		String groupCheck = group.handler();
		if(StringUtils.isNotBlank(groupCheck)){
			sb.append(typeStr+"：===》"+System.lineSeparator());
			sb.append(groupCheck+System.lineSeparator());
		}
	}
}
