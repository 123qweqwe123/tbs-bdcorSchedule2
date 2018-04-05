package com.bdcor.pip.cp3UqsTodw.main;

import com.bdcor.utils.CustomizedPropertyPlaceholderConfigurer;
import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

/**
 * Created by root on 16-11-3.
 */
public class Main {

    public static void main( String[]  a ) throws Exception {

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        TBScheduleManagerFactory tbsmf = new TBScheduleManagerFactory();

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

        tbsmf.setApplicationContext(ac);
        tbsmf.init(p);
    }

}
