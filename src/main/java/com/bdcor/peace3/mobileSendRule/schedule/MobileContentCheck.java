package com.bdcor.peace3.mobileSendRule.schedule;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.util.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bdcor.datasource.MultipleDataSource;
import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.ruleCheck.Group;
import com.bdcor.peace3.mobileSendRule.utile.db.OracleHandler;
import com.bdcor.peace3.mobileSendRule.utile.properties.PropertiesUtils;
import com.bdcor.utils.MailUtils;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;

/**
 * 检查发送的内容是否有特殊字符
 * 
 * @author Administrator
 */
@Component
public class MobileContentCheck implements IScheduleTaskDealSingle<String> {
	
	@Autowired
    private CommDao commDao;
	
	static Logger logger = LoggerFactory.getLogger(MobileContentCheck.class);
	
	Set<String> handlerD = new HashSet<String>();
	static Set<String> speChar = new HashSet<String>();
	static{
		
			
			for(String s:PropertiesUtils.getInstance().getProByKey("special.char").split(",")){
				speChar.add(s);
			}
	}
	
	@Override
	public List<String> selectTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> taskItemList,
			int eachFetchDataNum) throws Exception {
		List<String> returnList = new ArrayList<String>();
		Date date = new Date();
		String d = new SimpleDateFormat("yyyyMMdd").format(date);
		logger.info(date.toLocaleString());
		
		if(handlerD.contains(d)){
			logger.info("数据已经处理:"+d);
			returnList.add("1");
			return null;
		}
		
//		OracleHandler.getHandler().open();
		List<String> rList = new ArrayList<String>();
	
		
		MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
		List<Map<String, Object>> list = commDao.query("select id,msg_name from pip_msg_send where to_char(SENDTIME_PREINSTALL,'yyyymmdd')='"+d+"'");
		for(Map<String,Object> map:list){
			String v = map.get("id".toUpperCase())+"###"+map.get("msg_name".toUpperCase());
			logger.info("查找到数据:==>"+v);
			rList.add(v);
		}
		
		logger.info("短信条数:==>"+rList.size());
		
		
		
		
		StringBuffer sb = new StringBuffer();
		for(String line:rList){
			String[] ses = line.split("###");
			String id = ses[0];
			String content = ses[1];
			for(String speC:speChar){
				if(content.indexOf(speC)!=-1){
					sb.append(speC+":"+id+"==>"+content+System.lineSeparator());
					continue;
				}
			}
		}
		
		
		if(StringUtils.isNotBlank(sb.toString())){
			//记录日志和发送email
			FileUtils.writeStringToFile(new File("peace3_mobile_send/mobileContentCheck-"+d+".txt"), sb.toString());
			MailUtils.sendMail(PropertiesUtils.getInstance().getProByKey("send_email"), "短信内容中有特殊字符", d+"====><br>"+sb.toString());
		}
		
		
		handlerD.add(d);
		
		
		returnList.add(rList.size()+"");
//		OracleHandler.getHandler().close();
		return returnList;
	}

	@Override
	public Comparator<String> getComparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean execute(String task, String ownSign) throws Exception {
		
		//logger.info("obj:"+task);
		logger.info("处理数据个数:"+task);
		
		
		
		return true;
	}
	
	public static void main(String[] a) throws Exception{
			
		
		
		System.out.println(DateTime.now().toString("yyyy-MM-dd"));
		
	}

}
