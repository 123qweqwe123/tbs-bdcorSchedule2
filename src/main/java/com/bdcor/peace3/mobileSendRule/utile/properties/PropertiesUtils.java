package com.bdcor.peace3.mobileSendRule.utile.properties;

import com.bdcor.configCenter.client.PropertiesInConfigCenter;

public class PropertiesUtils {
	PropertiesInConfigCenter configer;
	
	private static PropertiesUtils pro = null;
	public static PropertiesUtils getInstance(){
		if(pro==null){
			pro = new PropertiesUtils();
		}
		return pro;
	}
	private PropertiesUtils(){
		configer = new PropertiesInConfigCenter();
		configer.setLocalProFile("peace3/peace3.properties");
		configer.setNo("006");
		configer.setProjName("schedule_peace3_mobilecheck");
		configer.setZookeeperConnection("172.31.201.173:2181");
		configer.setAutoLoad(false);
		configer.init();
	}

	public String getProByKey(String key){
		return configer.getProperty(key);
	}
}
