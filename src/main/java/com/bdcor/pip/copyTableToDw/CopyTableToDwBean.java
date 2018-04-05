package com.bdcor.pip.copyTableToDw;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdcor.datasource.MultipleDataSource;
import com.bdcor.datasource.MultipleDataSource.Source;
import com.bdcor.pip.copyTableToDw.dao.CopyTableToDwDao;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;


public class CopyTableToDwBean implements IScheduleTaskDealSingle<String> {
	protected static transient Logger log = LoggerFactory.getLogger(CopyTableToDwBean.class);
	
	@Autowired
	private CopyTableToDwDao copyTableToDwDao;
	
	private Date lastDate;
	
	@Override
	public boolean execute(String lccCode, String ownSign) throws Exception {
		MultipleDataSource.setDataSourceKey(Source.DATASOURCE_DW);
		copyTableToDwDao.copyData("pip_scm_frozentube", lccCode);
		copyTableToDwDao.copyData("pip_scan_uqs_info", lccCode);
		copyTableToDwDao.copyTableRelation(lccCode);
		return true;
	}

	public List<String> selectTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> taskItemList,
			int eachFetchDataNum) throws Exception {
		
		if(lastDate!=null && new Date().getTime()-lastDate.getTime()<12*60*60*1000){
			return null;
		}
		lastDate = new Date();
		
		System.out.println("CopyTableToDwBean start....");
		
		MultipleDataSource.setDataSourceKey(Source.DATASOURCE_DW);			
		//查询dw库特有表，检测数据库切换到dw
		int c = copyTableToDwDao.dataCount("G004_INHOS");
		System.out.println("change db to dw......count:"+c);
			
		copyTableToDwDao.truncateTable("pip_scm_frozentube");
		System.out.println("truncateTable : pip_scm_frozentube");
			
		copyTableToDwDao.truncateTable("pip_scan_uqs_info");
		System.out.println("truncateTable : pip_scan_uqs_info");
			
		copyTableToDwDao.truncateTable("PIP_RELATION_006");
		System.out.println("truncateTable : PIP_RELATION_006");
		
		//切换到006库，只查询不需要校验
		MultipleDataSource.setDataSourceKey(Source.DATASOURCE_006);
		
		return copyTableToDwDao.getLccCodeList_All();
	}
	
	public Comparator<String> getComparator() {
		return null;
	}
	

}