package com.bdcor;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bdcor.utils.CustomizedPropertyPlaceholderConfigurer;
import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		//DataSyncABean b = (DataSyncABean)ac.getBean("dataSyncABean");
		
		TBScheduleManagerFactory scheduleManager = new TBScheduleManagerFactory();
		
		Properties p = new Properties();
		
		
		/*
		p.put("zkConnectString", "172.31.201.180:2181");
	    p.put("rootPath", "/aaa");
		p.put("zkSessionTimeout", "60000");
	    p.put("userName", "aaa");
	    p.put("password", "bbb");
	    p.put("isCheckParentPath", "true");
*/
	
		p.put("zkConnectString", CustomizedPropertyPlaceholderConfigurer.getContextProperty("zk.ipAndPort"));
	    p.put("rootPath", CustomizedPropertyPlaceholderConfigurer.getContextProperty("zk.path"));
		p.put("zkSessionTimeout", CustomizedPropertyPlaceholderConfigurer.getContextProperty("zk.zkSessionTimeout"));
	    p.put("userName", CustomizedPropertyPlaceholderConfigurer.getContextProperty("zk.userName"));
	    p.put("password", CustomizedPropertyPlaceholderConfigurer.getContextProperty("zk.password"));
	    p.put("isCheckParentPath", CustomizedPropertyPlaceholderConfigurer.getContextProperty("zk.isCheckParentPath"));
		
	    
	    scheduleManager.setApplicationContext(ac);
		scheduleManager.init(p);
		
		
	}

}
