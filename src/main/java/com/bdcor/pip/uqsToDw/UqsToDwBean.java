package com.bdcor.pip.uqsToDw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.datasource.MultipleDataSource;
import com.bdcor.datasource.MultipleDataSource.Source;
import com.bdcor.pip.uqsToDw.dao.UqsToDwDao;
import com.bdcor.pip.uqsToDw.domain.Answer;
import com.bdcor.pip.uqsToDw.domain.Option;
import com.bdcor.pip.uqsToDw.domain.Question;
import com.bdcor.pip.uqsToDw.domain.QuestionNaire;
import com.bdcor.pip.uqsToDw.domain.QuestionSet;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;

public class UqsToDwBean implements IScheduleTaskDealSingle<Map<String,String>> {
	protected static transient Logger log = LoggerFactory.getLogger(UqsToDwBean.class);
	
	@Autowired
	private UqsToDwDao uqsToDwDao;
	
	private Map<String,Date> dMap = new HashMap<String,Date>();
	
	public static List<QuestionNaire> qnList;
	
	/**
	 * 单表插入不需要配置事务，配置时切换数据源会有问题
	 */
	@Override
	public boolean execute(Map<String,String> task, String ownSign) throws Exception {
		MultipleDataSource.setDataSourceKey(Source.DATASOURCE_006);
		
		String patientId = task.get("PATIENT_ID");
		String col = "PATIENT_ID";
		if(patientId.startsWith("G")){
			col = "RISK_CODE";
		}
		String area = task.get("LCC_CODE").substring(0,2);
		QuestionNaire qn = getQnById(task.get("ITEM_CODE"));
		
		List<Answer> aList = uqsToDwDao.getAnswerList(area,qn.getId(),patientId);
		if(aList==null || aList.size()==0)return true;
		StringBuilder inSql = new StringBuilder("INSERT into G"+qn.getId()+"_DATA(PROJECT_ID,VERSION,CREATE_DATE,"+col);
		StringBuilder vSql = new StringBuilder("VALUES('"+qn.getProjectId()+"','"+aList.get(0).getVersion()+"',sysdate,'"+patientId+"'");
		
		Map<String,String> answerMap = getAnswerMap(aList);
		for(QuestionSet s : qn.getSets()){
			for(Question q : s.getQuestions()){
				int cycleNum=0;
				if(s.getCycle()==1 && !"1".equals(q.getId()))cycleNum=20;
				for(int i=0;i<=cycleNum;i++){
					//单选
					if(q.getType().equals("SINGLE")){
						String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i);
						inSql.append(","+colN);
						vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":getV(answerMap,colN,null)));
					}
					
					//单选填空
					else if(q.getType().equals("SINGLEFILL")){
						String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i);
						inSql.append(","+colN);
						vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":getV(answerMap,colN,null)));
						
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							if(o.getStyle().equals("FILLBLANK") || o.getStyle().equals("TEXTAREA")){
								String colN_1 = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
								inSql.append(","+colN_1+"_TEXT");
								vSql.append(","+((getV(answerMap,colN_1,o.getRealType())==null || getV(answerMap,colN_1,o.getRealType()).equals(""))?"NULL":getV(answerMap,colN_1,o.getRealType())));
							}
						}
					}
					
					//多选
					else if(q.getType().equals("MULTI")){
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
							inSql.append(","+colN);
							vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":"'CHECKED'"));
						}
					}
					
					//多选填空
					else if(q.getType().equals("MULTIFILL")){
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							//if(o.getStyle().equals("MULTI")){
							String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
							inSql.append(","+colN);
							vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":"'CHECKED'"));
							if(o.getStyle().equals("FILLBLANK")  || o.getStyle().equals("TEXTAREA")){
								String colN_1 = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
								inSql.append(","+colN_1+"_TEXT");
								vSql.append(","+((getV(answerMap,colN_1,o.getRealType())==null || getV(answerMap,colN_1,o.getRealType()).equals(""))?"NULL":getV(answerMap,colN_1,o.getRealType())));
							}
						}
					}
					
					//填空
					else if(q.getType().equals("FILLBLANK")){
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
							inSql.append(","+colN);
							vSql.append(","+((getV(answerMap,colN,o.getRealType())==null || getV(answerMap,colN,o.getRealType()).equals(""))?"NULL":getV(answerMap,colN,o.getRealType())));
						}
					}
					
					//表格
					else if(q.getType().equals("TABLE")){
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId().replace("-", "_T_");
							inSql.append(","+colN);
							if("TSINGLE".equals(o.gettRemark().toUpperCase())){
								vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":"'CHECKED'"));
							}else{
								vSql.append(","+((getV(answerMap,colN,o.getRealType())==null || getV(answerMap,colN,o.getRealType()).equals("")) ?"NULL":getV(answerMap,colN,o.getRealType())));
							}
						}
					}
				}
			}
		}
		
		try {
			MultipleDataSource.setDataSourceKey(Source.DATASOURCE_DW);
		    uqsToDwDao.execInsertSql(inSql.toString()+")"+vSql+")");
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		synchronized (this) {
			dMap.put(task.get("ITEM_CODE"), new Date());
		}
		return true;
	}

	private QuestionNaire getQnById(String itemCode) {
		for(QuestionNaire qn : qnList){
			if(qn.getId().equals(itemCode)){
				return qn;
			}
		}
		return null;
	}

	public List<Map<String,String>> selectTasks(String taskParameter, String ownSign,
			int taskItemNum, List<TaskItemDefine> taskItemList,
			int eachFetchDataNum) throws Exception {
		MultipleDataSource.setDataSourceKey(Source.DATASOURCE_006);
		if(qnList==null){
			qnList = getQnList();
		}
		
		List<Map<String,String>> rList = new ArrayList<Map<String,String>>();
		
		for(int i=0;i<taskItemList.size();i++){
			String itemCode = taskItemList.get(i).getTaskItemId();
			if(dMap.get(itemCode)==null || new Date().getTime()-dMap.get(itemCode).getTime()>=3*60*1000){
				List<Map<String,String>> pIdList = uqsToDwDao.getPIdList(itemCode);
				rList.addAll(pIdList);
			}
		}
		
		return rList;
	}
	
	public Comparator<Map<String,String>> getComparator() {
		return new Comparator<Map<String,String>>() {
			public int compare(Map<String,String> o1, Map<String,String> o2) {
				return -1;//不需要排序
			}
		};
	}
	
	private List<QuestionNaire> getQnList(){
		List<QuestionNaire> list = uqsToDwDao.getQnList();
		if(list == null || list.size()==0)return list;
		for(QuestionNaire qn : list){
			if(qn.getSets()==null)continue;
			for(QuestionSet s : qn.getSets()){
				if(s.getQuestions()==null)continue;
				for(Question q : s.getQuestions()){
					if(q.getOptions()==null)continue;
					for(Option o : q.getOptions()){
						if(o.getNumArrCount()>0){
							if(o.getFormat()!=null && o.getFormat().trim().length()>0){
								String f = o.getFormat().trim().substring(1).replaceFirst("\\.",",");
								String r = f;
								String[] ar = f.split(",");
								if(ar.length==1 && Integer.parseInt(f)<4){
									r="4";
								}else if(ar.length==2 && Integer.parseInt(ar[0])-Integer.parseInt(ar[1])<4){
									r = (Integer.parseInt(ar[1])+4)+","+ar[1];
								}
								o.setType("NUMBER("+r+")");
								o.setRealType("NUMBER("+r+")");
							}else{
								o.setType("NUMBER(10,4)");
								o.setRealType("NUMBER(10,4)");
							}
						}
					}
				}
			}
		}
		return list;
	}
	
	private String getRQId(String qId, int i) {
		if(i==0)return qId;
		if(i<10)return qId+"00"+i;
		if(i<100)return qId+"0"+i;
		return qId+""+i;
	}
		
	private Map<String, String> getAnswerMap(List<Answer> aList) {
		Map<String, String> map = new HashMap<String, String>();
		if(aList==null || aList.size()==0)return map;
		
		for(Answer a : aList){
			map.put(a.getId(),a.getOptionId());
			if(a.getText()==null||a.getText().trim().length()==0){
				map.put(a.getId()+"_"+a.getOptionId().replace("-", "_T_"),"");
			}else{
				map.put(a.getId()+"_"+a.getOptionId().replace("-", "_T_"),a.getText().trim());
			}
		}
		return map;
	}
	
	private String getV(Map<String, String> map,String key,String realType){
		if(map.get(key)==null){
			return null;
		}
		if( map.get(key).equals("")){
			return "";
		}
		if("DATE".equals(realType)){
			String d = map.get(key).trim();
			try{
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
				s.setLenient(false);
				s.parse(d);
			}catch (Exception e) {
				d = "1900-01-01";
			}
			return "TO_DATE('"+d+"','yyyy-MM-dd')";
		}else if("DATETIME".equals(realType)){
			String d = map.get(key).trim();
			try{
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				s.setLenient(false);
				s.parse(d);
			}catch (Exception e) {
				d = "1900-01-01 00:00:00";
			}
			return "TO_DATE('"+d+"','yyyy-MM-dd hh24:mi:ss')";
		}else if(realType!=null && realType.contains("NUMBER")){
			String d = map.get(key).trim();
			if(d.toUpperCase().contains("N")){
				return null;
			}
			return "TO_NUMBER('"+d+"')";
		}else{
			return "'"+map.get(key).trim().replaceAll("'","''")+"'";
		}
	}

}