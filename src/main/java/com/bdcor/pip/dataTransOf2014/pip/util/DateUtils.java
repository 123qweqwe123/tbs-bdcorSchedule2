package com.bdcor.pip.dataTransOf2014.pip.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author rp
 *
 */
public final class DateUtils {
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static DateFormat dateFormatLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static DateFormat dateFormatIdCard = new SimpleDateFormat("yyyy.MM.dd");
	
	public static Date parse(String date) {
		try {
			return dateFormat.parse(date.trim());
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date parseIdCard(String date) {
		try {
			return dateFormatIdCard.parse(date.trim());
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date stringToDate(String date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date ret = null;
		try {
			ret = formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 字符串格式化为 yyyy-MM-dd HH:mm:ss 日期格式
	 * @param date
	 * @return
	 */
	public static Date parseLong(String date) {
		try {
			return dateFormatLong.parse(date.trim());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String formatLong(Date date){
		if(date==null)
			return null;
		try{
			return dateFormatLong.format(date);
		}catch(Exception ex){
			return null;
		}
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		try{
			return dateFormat.format(date);
		}catch(Exception ex){
			return null;
		}
	}
	
	/**
	 * 获取统计结束时间
	 * 当天的 00:00:00
	 * */
	public static Date end(){
		String str = format(new Date());
		str += "  00:00:00";
		return parseLong(str);
	}
	
	/**
	 * 获取统计开始时间
	 * 昨天的 00:00:00
	 * */
	public static Date start(){
		Date ret = end();
		ret.setTime(ret.getTime() - 24L * 3600 * 1000 );
		return ret;
	}
}
