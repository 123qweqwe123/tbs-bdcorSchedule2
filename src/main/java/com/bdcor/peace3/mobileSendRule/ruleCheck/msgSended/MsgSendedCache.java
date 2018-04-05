package com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended;

import java.util.HashMap;
import java.util.Map;

import com.bdcor.peace3.mobileSendRule.dao.CommDao;

public class MsgSendedCache {


	Map<String,MsgSendedOfPatient> patientIdAndMsgMap = new HashMap<String,MsgSendedOfPatient>();
	
	private MsgSendedCache(){}
	
	private static MsgSendedCache instance = null;
	public static MsgSendedCache getInstance(){
		if(instance==null){
			instance = new MsgSendedCache();
		}
		return instance;
	}
	
	public MsgSendedOfPatient getPatientSendedMsg(String patientId,CommDao commDao){
		MsgSendedOfPatient sendedMsg = this.patientIdAndMsgMap.get(patientId);
		if(sendedMsg==null){
			sendedMsg = new MsgSendedOfPatient(patientId,commDao);
			this.patientIdAndMsgMap.put(patientId, sendedMsg);
		}
		
		return sendedMsg;
	}
}
