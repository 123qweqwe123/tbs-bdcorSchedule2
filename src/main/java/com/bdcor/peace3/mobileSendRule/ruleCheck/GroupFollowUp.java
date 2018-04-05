package com.bdcor.peace3.mobileSendRule.ruleCheck;

import java.util.List;

import org.joda.time.DateTime;

import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.obj.Patient;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate.SendMsg;

/**
 * 
 * 
 * 

XX_M007	随访短信

 * @author Administrator
        
*/


//随访前一天发送随访短信
public class GroupFollowUp extends Group {
	
	public GroupFollowUp(List<Patient> patients,CommDao commDao) {
		super(patients,commDao);
	}

	
	
	public void checkCurrentDate() throws Exception{
		MsgSendedByDate msgSendedByDate = msgSended.getCurrentSendMsg();
		
		boolean isSendBirthdayMsg = false;
		if(msgSendedByDate!=null){
			for(SendMsg msg:msgSendedByDate.sendMsg){
				if(msg.msgType.contains("M007")){
					isSendBirthdayMsg = true;
				}
			}
		}
		
		if(!isSendBirthdayMsg){
			throw new Exception("未发送随访短信");
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
