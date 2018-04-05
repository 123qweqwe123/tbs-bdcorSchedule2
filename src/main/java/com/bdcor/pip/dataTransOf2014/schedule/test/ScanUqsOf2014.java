package com.bdcor.pip.dataTransOf2014.schedule.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bdcor.pip.dataTransOf2014.handlerUqs2014.FindLostBinding4;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;


/**
 * 重新解析高危一期答卷数据。
 * 		重新解析原因：原解析中未包括 登记人信息
 * 
 * @author Administrator
 */
@Component
public class ScanUqsOf2014 implements IScheduleTaskDealSingle<String> {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	Set<String> lccSet = new HashSet<String>();
	
	@Override
	public List<String> selectTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> taskItemList,
			int eachFetchDataNum) throws Exception {
		List<String> rList = new ArrayList<String>();
	
		//System.out.println("selectTasks");
		logger.info("selectTasks");
		logger.info("selectTasks==>"+
		"taskParameter:"+taskParameter+
		",ownSign:"+ownSign+
		",taskItemNum:"+taskItemNum+
		",eachFetchDataNum:"+eachFetchDataNum);
		for(TaskItemDefine item:taskItemList){
			String itemid = item.getTaskItemId();
			if(!lccSet.contains(itemid)){
				lccSet.add(itemid);
				
				//201410-201612
				rList.add(itemid+"|20141001"+"|20141031");
				rList.add(itemid+"|20141101"+"|20141130");
				rList.add(itemid+"|20141201"+"|20141231");
				
				rList.add(itemid+"|20150101"+"|20150131");
				rList.add(itemid+"|20150201"+"|20150228");
				rList.add(itemid+"|20150301"+"|20150331");
				rList.add(itemid+"|20150401"+"|20150430");
				rList.add(itemid+"|20150501"+"|20150531");
				rList.add(itemid+"|20150601"+"|20150630");
				rList.add(itemid+"|20150701"+"|20150731");
				rList.add(itemid+"|20150801"+"|20150831");
				rList.add(itemid+"|20150901"+"|20150930");
				rList.add(itemid+"|20151001"+"|20151031");
				rList.add(itemid+"|20151101"+"|20151130");
				rList.add(itemid+"|20151201"+"|20151231");
				
				rList.add(itemid+"|20160101"+"|20160131");
				rList.add(itemid+"|20160201"+"|20160228");
				rList.add(itemid+"|20160301"+"|20160331");
				rList.add(itemid+"|20160401"+"|20160430");
				rList.add(itemid+"|20160501"+"|20160531");
				rList.add(itemid+"|20160601"+"|20160630");
				rList.add(itemid+"|20160701"+"|20160731");
				rList.add(itemid+"|20160801"+"|20160831");
				rList.add(itemid+"|20160901"+"|20160930");
				rList.add(itemid+"|20161001"+"|20161031");
				
			}
		}
		return rList;
	}

	@Override
	public Comparator<String> getComparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean execute(String task, String ownSign) throws Exception {
		//System.out.println("execute task:"+ownSign);
		logger.info("obj:"+task);
		logger.info("execute task:"+ownSign);
		String[] ses = task.split("\\|");
		FindLostBinding4.find(ses[1], ses[2], ses[0]);
		return true;
	}
	
	public static void main(String[] a) throws Exception{
		final ScanUqsOf2014 bean = new ScanUqsOf2014();
		/*
		TaskItemDefine item = new TaskItemDefine();
		item.setTaskItemId("21");
		List<TaskItemDefine> list = new ArrayList<TaskItemDefine>();
		list.add(item);
		
		bean.selectTasks(null, null, 0, list, 1);
		*/
		
		String itemid = "21";
		List<String> rList = new ArrayList<String>();
	
				//201410-201612
				rList.add(itemid+"|20141001"+"|20141031");
				rList.add(itemid+"|20141101"+"|20141130");
				rList.add(itemid+"|20141201"+"|20141231");
				
				rList.add(itemid+"|20150101"+"|20150131");
				rList.add(itemid+"|20150201"+"|20150228");
				rList.add(itemid+"|20150301"+"|20150331");
				rList.add(itemid+"|20150401"+"|20150430");
				rList.add(itemid+"|20150501"+"|20150531");
				rList.add(itemid+"|20150601"+"|20150630");
				rList.add(itemid+"|20150701"+"|20150731");
				rList.add(itemid+"|20150801"+"|20150831");
				rList.add(itemid+"|20150901"+"|20150930");
				rList.add(itemid+"|20151001"+"|20151031");
				rList.add(itemid+"|20151101"+"|20151130");
				rList.add(itemid+"|20151201"+"|20151231");
				
				rList.add(itemid+"|20160101"+"|20160131");
				rList.add(itemid+"|20160201"+"|20160228");
				rList.add(itemid+"|20160301"+"|20160331");
				rList.add(itemid+"|20160401"+"|20160430");
				rList.add(itemid+"|20160501"+"|20160531");
				rList.add(itemid+"|20160601"+"|20160630");
				rList.add(itemid+"|20160701"+"|20160731");
				rList.add(itemid+"|20160801"+"|20160831");
				rList.add(itemid+"|20160901"+"|20160930");
				rList.add(itemid+"|20161001"+"|20161031");
				
		for(final String i:rList){
			
//			new Thread(){
//				public void run(){
//					try {
						bean.execute(i, "dd");
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//				
//			}.start();
			
		}
		
	}

}
