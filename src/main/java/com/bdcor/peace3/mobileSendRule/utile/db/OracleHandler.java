package com.bdcor.peace3.mobileSendRule.utile.db;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OracleHandler {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	int index = 0;
	int pageSize = 1000;
	
	
	Connection conn = null;
	public static String objToString(Object o){
		if(o==null){
			return null;
		}
		return o.toString();
	}
}
