package com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended;

import java.util.HashSet;
import java.util.Set;

public class MsgSendedByDate {

	public String patientId;
	public String date;
	public Set<SendMsg> sendMsg = new HashSet<SendMsg>();
	public String count;
	public MsgSendedByDate(String date,String msgType,String msgId){
		this.date = date;
		this.sendMsg.add(new SendMsg(msgType,msgId));
	}
	public MsgSendedByDate(String lineInFile){
		String[] ss = lineInFile.split("###");
		
		this.date = ss[0];
		this.sendMsg.add(new SendMsg(ss[1]));
	}
	
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(SendMsg msg:sendMsg){
			sb.append(msg+",");
		}
		if(sb.length()>1){
			sb.deleteCharAt(sb.length()-1);
		}
		
		return date + "###" +
				sb.toString()+System.lineSeparator();
	}
	
	public String getDayKey(){
		return date.substring(0, 10);
	}
	
	public static class SendMsg{
		
		public SendMsg(String msgType,String msgId){
			this.msgId = msgId;
			this.msgType = msgType;
		}
		
		public SendMsg(String lineInFile){
			String newLine = lineInFile.replace("[", "");
			newLine = newLine.replace("]", "");
			String[] ss = newLine.split(":");
			this.msgId = ss[1];
			this.msgType = ss[0];
		}
		
		public String msgType;
		public String msgId;
		
		
		
		@Override
		public int hashCode() {
			return this.msgType.hashCode()+this.msgId.hashCode();
		}



		@Override
		public boolean equals(Object obj) {
			if(obj instanceof SendMsg){
				SendMsg sendMsg2 = (SendMsg)obj;
				return sendMsg2.msgType.equals(this.msgType) && sendMsg2.msgId.equals(this.msgId);
			}
			return false;
		}



		public String toString(){
			return "["+this.msgType+":"+this.msgId+"]";
		}
	}
}
