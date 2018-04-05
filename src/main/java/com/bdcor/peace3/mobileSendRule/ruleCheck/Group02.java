package com.bdcor.peace3.mobileSendRule.ruleCheck;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.stereotype.Component;

import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.obj.Patient;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate.SendMsg;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByWeek.SendCountInWeek;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByWeek;

/**
 * 
非糖尿病对照组：5、25号发送“感谢类短信”
 * 
 * 
 * 
02_M006	欢迎参加研究类
02_M007	随访提醒类
02_M008	生日福类
02_M013	感谢类
 * @author Administrator
 * 02:非糖尿病对照组
 * 01：非糖尿病实验组 02:非糖尿病对照组 11:糖尿病实验组 12:糖尿病对照组
 * 
 	
	 * 检查当天发送的短信是否有问题；
	 * 
	 *  *给每个人发送短信的条数是否正确（超过两条的，发通知）        c
	 *  
		*是否存在给“对照组”发送非“感谢类短信”           
*/


//02:非糖尿病对照组
public class Group02 extends Group {
	
	public Group02(List<Patient> patients,CommDao commDao) {
		super(patients,commDao);
	}

	
	
	public void checkCurrentDate() throws Exception{
		checkCurrentDateCommon("02_");
		
		MsgSendedByDate msgSendedByDate = msgSended.getCurrentSendMsg();
		
		int dayOfMonth = DateTime.now().getDayOfMonth();
		
		if(dayOfMonth!=5 && dayOfMonth!=20){
			if(msgSendedByDate!=null && msgSendedByDate.sendMsg.size()>0){
				int msgCount = 0;
				for(SendMsg msg:msgSendedByDate.sendMsg){
					if(Group.notDateLimit.indexOf(msg.msgType.replace("02_", ""))==-1){
						msgCount++;
					}
				}
				if(msgCount>0){
					//error  非5号和25号不发短信
					throw new Exception("非5号和20号不发短信");
				}
			}
		}else{
			if(msgSendedByDate==null || msgSendedByDate.sendMsg.size()==0){
				//error  非周一，但未发短信
				throw new Exception("5号或20号，但未发短信");
			}
		}
	}
	
	public void checkCurrentWeek() throws Exception{
		String typeFlag = "";

		/**
		 * 
week_02_M013=1
		 */
		checkCurrentWeek(typeFlag);

		
	}
	
	public void checkCurrentMonth() throws Exception{
		
	}
	
	public static void main(String[] a){
		System.out.println(DateTime.now().getDayOfMonth());
		
	}
	
}
