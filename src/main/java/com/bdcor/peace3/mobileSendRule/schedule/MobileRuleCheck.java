package com.bdcor.peace3.mobileSendRule.schedule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bdcor.datasource.MultipleDataSource;
import com.bdcor.peace3.mobileSendRule.dao.CommDao;
import com.bdcor.peace3.mobileSendRule.obj.Patient;
import com.bdcor.peace3.mobileSendRule.ruleCheck.RuleCheckHandler;
import com.bdcor.peace3.mobileSendRule.utile.db.OracleHandler;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;

/**
 * 检查短信发送规则是否正确
 * 
 * @author Administrator
 */
@Component
public class MobileRuleCheck implements IScheduleTaskDealSingle<String> {

	@Autowired
    private CommDao commDao;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	Set<String> handlerD = new HashSet<String>();
	
	
	@Override
	public List<String> selectTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> taskItemList,
			int eachFetchDataNum) throws Exception {
		List<String> returnList = new ArrayList<String>();
		Date date = new Date();
		//String d = DateUtils.format(date, "yyyyMMdd");
		String d = new SimpleDateFormat("yyyyMMdd").format(date);
		logger.info(date.toLocaleString());
		if(handlerD.contains(d)){
			logger.info("数据已经处理:"+d);
			returnList.add("1");
			return null;
		}
		
//		OracleHandler.getHandler().open();
		List<Patient> rList = new ArrayList<Patient>();
	
		
		MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
		/*String sql = "select patient_name,patient_id,BELONG_GROUP,IS_SMOKING,GROUP_DATE,BIRTHDAY " +
				"from pip_comm_patient where " +
				"BELONG_GROUP is not null " +
				"and (IS_DEAD<>1 or IS_DEAD is null)";*/
		String sql = "select p.patient_name, p.patient_id, p.BELONG_GROUP, p.IS_SMOKING, p.GROUP_DATE, p.BIRTHDAY, a.state state014 " +
				"from pip_comm_patient p " +
				"left join (select project_id, patient_id, state from PIP_UQS_ANSWERQN_LOG where state = 1 and substr(uqs_version, 5, 3) = '014') a " +
				"on p.project_id = a.project_id and p.patient_id = a.patient_id " +
				"where p.belong_group is not null " +
				"and (p.is_dead <> 1 or p.is_dead is null) " +
				"and a.state is null";
		List<Map<String, Object>> list = commDao.query(sql);
		for(Map<String,Object> map:list){
			String id = map.get("patient_id".toUpperCase()).toString();
			String name = map.get("patient_name".toUpperCase()).toString();
			String group = map.get("BELONG_GROUP").toString();
			String isSmoking = map.get("IS_SMOKING").toString();
			String groupDate = map.get("GROUP_DATE").toString();
			String birthday = map.get("BIRTHDAY").toString();

			Patient p = new Patient(id,name,group,isSmoking,groupDate,birthday);


			rList.add(p);
		}
		logger.info("需要发短信的人数:==>"+rList.size());
		
		RuleCheckHandler.getInstance().group(rList,commDao);
		
		handlerD.add(d);
		
//		OracleHandler.getHandler().close();
		
		returnList.add(rList.size()+"");
		
		
		return returnList;
	}

	@Override
	public Comparator<String> getComparator() {
		return null;
	}

	@Override
	public boolean execute(String task, String ownSign) throws Exception {
		
		//logger.info("obj:"+task);
		logger.info("处理数据个数:"+task);
		
		
		
		return true;
	}
	
	public static void main(String[] a) throws Exception{
		MobileRuleCheck ruleCheck = new MobileRuleCheck();
		
		ruleCheck.selectTasks(null, null, 0, null, 0);
	}

}
