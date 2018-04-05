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
 * 非糖尿病实验组：回复类短信：每月一条 “药物依从类”短信，每月一条 “高血压类”短信，
			抽烟：2条高血压 1条基础教育 1条药物依从性 1条运动类 1条戒烟类
		      不抽烟：2条高血压 2条基础教育 1条药物依从性 1条运动类
 * 
 * 
 * 
01_M001	基础教育类
01_M002	用药依从性类
01_M003	高血压类
01_M004	运动相关类
01_M004	运动类
01_M005	戒烟类

01_M006	欢迎参加研究类
01_M006	欢迎类
01_M007	随访提醒类
01_M008	生日福类
01_M008	生日类
01_R001	
01_R002	
01_R003	
01_R004	
01_R005	
01_R006	
 * @author Administrator
 * 01：非糖尿病实验组
 * 01：非糖尿病实验组 02:非糖尿病对照组 11:糖尿病实验组 12:糖尿病对照组
 * 
 	
	 * 检查当天发送的短信是否有问题；
	 * 
	 * 	*是否每天发送短信（除周一）                                                        c
	 *  *给每个人发送短信的条数是否正确（超过两条的，发通知）        c
	 *  
	 *  ******是否存在给“非糖尿病患者”发送“糖尿病类的短信”,   c
		**是否存在给“不抽烟患者”发送“抽烟类短信”            c
		**是否存在给“对照组”发送非“感谢类短信”           
*/


//01：非糖尿病实验组
public class Group01 extends Group {
	
	public Group01(List<Patient> patients,CommDao commDao) {
		super(patients,commDao);
	}

	
	
	public void checkCurrentDate() throws Exception{
		checkCurrentDateCommon("01_");
		
		MsgSendedByDate msgSendedByDate = msgSended.getCurrentSendMsg();
		
		if(DateTime.now().getDayOfWeek()==DateTimeConstants.MONDAY){
			if(msgSendedByDate!=null && msgSendedByDate.sendMsg.size()>0){
				int msgCount = 0;
				for(SendMsg msg:msgSendedByDate.sendMsg){
					if(Group.notDateLimit.indexOf(msg.msgType.replace("01_", ""))==-1){
						msgCount++;
					}
				}
				if(msgCount>0){
					//error  周一不发短信
					throw new Exception("周一不发短信");
				}
			}
		}else{
			if(msgSendedByDate==null || msgSendedByDate.sendMsg.size()==0){
				//error  非周一，但未发短信
				throw new Exception("非周一，但未发短信");
			}
			if(msgSendedByDate!=null){
				for(SendMsg msg:msgSendedByDate.sendMsg){
					if("2".equals(super.patient.isSmoking) && "01_M005".equals(msg.msgType)){
						//error 给不抽烟的发送了 戒烟类短信
						throw new Exception("给不抽烟的人发送了戒烟类短信");
					}
					
				}
			}
		}
	}
	
	public void checkCurrentWeek() throws Exception{
		String typeFlag = "";
		if("2".equals(super.patient.isSmoking)){
			//不抽烟
			typeFlag = "_2";
		}else{
			//抽烟
			typeFlag = "_1";
		}
		/**
		 * 
week_01_M001_1=1
week_01_M002_1=1
week_01_M003_1=2
week_01_M004_1=1
week_01_M005_1=1

week_01_M001_2=2
week_01_M002_2=1
week_01_M003_2=2
week_01_M004_2=1
		 */
		checkCurrentWeek(typeFlag);

		
	}
	
	public void checkCurrentMonth() throws Exception{
		
	}
	

	
}
