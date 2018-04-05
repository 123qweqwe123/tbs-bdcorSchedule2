package com.bdcor.peace3.mobileSendRule.ruleCheck;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.obj.Patient;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate.SendMsg;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByWeek.SendCountInWeek;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByWeek;

/**
 * 

 * 
 * 

XX_M008	生日福类

 * @author Administrator
        
*/


//生日当天发送短信
public class GroupBirthday extends Group {
	
	public GroupBirthday(List<Patient> patients,CommDao commDao) {
		super(patients,commDao);
	}

	
	
	public void checkCurrentDate() throws Exception{
		MsgSendedByDate msgSendedByDate = msgSended.getCurrentSendMsg();
		
		boolean isSendBirthdayMsg = false;
		if(msgSendedByDate!=null){
			for(SendMsg msg:msgSendedByDate.sendMsg){
				if(msg.msgType.contains("M008")){
					isSendBirthdayMsg = true;
				}
			}
		}
		
		if(!isSendBirthdayMsg){
			throw new Exception("未发送生日短信");
		}
		
	}
	
	public void checkCurrentWeek() throws Exception{

		
	}
	
	public void checkCurrentMonth() throws Exception{
		
	}
	
	public static void main(String[] a){
		System.out.println(DateTime.now().getDayOfMonth());
		
	}
	
}
