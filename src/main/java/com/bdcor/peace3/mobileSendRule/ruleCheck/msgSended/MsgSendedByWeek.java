package com.bdcor.peace3.mobileSendRule.ruleCheck.msgSended;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class MsgSendedByWeek {

	public String week;
	public Set<SendCountInWeek> sendMsg = new TreeSet<SendCountInWeek>(new Comparator<SendCountInWeek>(){

		@Override
		public int compare(SendCountInWeek o1, SendCountInWeek o2) {
			if(o1.msgType!=null && o2.msgType!=null){
				int length1 = o1.msgType.length();
				int length2 = o2.msgType.length();
				
				int type1 = Integer.parseInt(o1.msgType.substring(length1-3, length1));
				int type2 = Integer.parseInt(o2.msgType.substring(length2-3, length2));
				if(type1>type2){
					return 1;
				}else if(type1<type2){
					return -1;
				}else{
					return 0;
				}
			}
			return 0;
		}
		
	});
	public MsgSendedByWeek(String week,String msgType,String count){
		this.week = week;
		this.sendMsg.add(new SendCountInWeek(msgType,Integer.parseInt(count)));
	}
	public MsgSendedByWeek(String week){
		this.week = week;
	}
	
	public void addSendCountInWeek(SendCountInWeek week){
		
		if(this.sendMsg.contains(week)){
			for(SendCountInWeek msg:this.sendMsg){
				if(msg.equals(week)){
					msg.count += week.count;
				}
			}
		}else{
			this.sendMsg.add(week);
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(SendCountInWeek msg:sendMsg){
			sb.append(msg+",");
		}
		if(sb.length()>1){
			sb.deleteCharAt(sb.length()-1);
		}
		
		return week + "###" +
				sb.toString()+System.lineSeparator();
	}
	public boolean hasData(){
		return this.sendMsg.size()==0?false:true;
	}
	public String getWeekKey(){
		return week;
	}
	
	public static class SendCountInWeek{
		
		public SendCountInWeek(String msgType,int count){
			this.msgType = msgType;
			this.count = count;
		}
		
		public String msgType;
		public int count;

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return this.msgType.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			SendCountInWeek type = (SendCountInWeek)obj;
			return this.msgType.equals(type.msgType);
		}

		public String toString(){
			return "["+this.msgType+"]->"+this.count+"";
		}
	}
	
	public static List<DateTime> getDateOfCurrentWeek(){
		DateTime nowTime = DateTime.now();
		int currentDayInWeek = nowTime.getDayOfWeek();
		
		List<DateTime> rList = new ArrayList<DateTime>();
		for(int i=currentDayInWeek-1;i>=0;i--){
			rList.add(nowTime.minusDays(i));
		}
		
		return rList;
		
	}
	
	/**
	 * 从指定的日期开始到当前时间 的周 对应的日期
	 * @param datetime
	 * @return
	 */
	public static Map<String,List<DateTime>> getDateOfWeekFromDate(DateTime datetime){
		
		Map<String,List<DateTime>> map = new HashMap<String,List<DateTime>>();
		
		DateTime nowTime = DateTime.now();
		int fromDayInWeek = datetime.getDayOfWeek();
		
		int dayInweekIndex = fromDayInWeek;
		int weekIndex = datetime.getWeekOfWeekyear();
		List<DateTime> list = new ArrayList<DateTime>();
		map.put(weekIndex+"", list);
		for(;datetime.toDate().before(nowTime.toDate());datetime=datetime.plusDays(1)){
			
			list.add(datetime);
			if(dayInweekIndex%7==0){
				dayInweekIndex = 0;
				weekIndex++;
				list = new ArrayList<DateTime>();
				map.put(weekIndex+"", list);
				
				
			}
			dayInweekIndex++;
		}
		
		
		return map;
		
	}
	
	public static void main(String[] a){
		DateTime datetime = DateTime.parse("2016-09-11", DateTimeFormat.forPattern("yyyy-MM-dd"));
		getDateOfWeekFromDate(datetime);
	}
}
