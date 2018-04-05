package com.bdcor.peace3.mobileSendRule.ruleCheck;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.obj.Patient;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate;
import com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended.MsgSendedByDate.SendMsg;

/**
 * 
  糖尿病实验组：回复类短信：每月一条 “药物依从类”短信，每月一条 “血糖类”短信，
		每周1条生活方式、1条高血压、1条血糖、1条基础教育、1条药物依从性、1条运动类
 * 
 * 
 * 
11_M001	基础教育类
11_M002	用药依从性类
11_M003	高血压类
11_M004	运动相关类
11_M004	运动类
11_M006	欢迎参加研究类
11_M007	随访提醒类
11_M008	生日福类
11_M011	血糖类
11_M012	生活方式类

11_R001	
11_R002	
11_R003	
11_R004	
11_R005	
11_R006
 * @author Administrator
 * 11:糖尿病实验组
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


//11:糖尿病实验组
public class Group11 extends Group {
	
	public Group11(List<Patient> patients,CommDao commDao) {
		super(patients,commDao);
	}

	
	
	public void checkCurrentDate() throws Exception{
		if("13702722".equals(this.patient.id)){
			System.out.println("");
		}
		checkCurrentDateCommon("11_");
		MsgSendedByDate msgSendedByDate = msgSended.getCurrentSendMsg();
		
		if(DateTime.now().getDayOfWeek()==DateTimeConstants.MONDAY){
			if(msgSendedByDate!=null && msgSendedByDate.sendMsg.size()>0){
				int msgCount = 0;
				for(SendMsg msg:msgSendedByDate.sendMsg){
					if(Group.notDateLimit.indexOf(msg.msgType.replace("11_", ""))==-1){
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
		}
	}
	
	public void checkCurrentWeek() throws Exception{
		String typeFlag = "";
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
	
	public static void main(String[] a){
		System.out.println(DateTime.now().getDayOfWeek());
		
	}
	
}
