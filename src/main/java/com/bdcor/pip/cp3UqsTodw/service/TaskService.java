package com.bdcor.pip.cp3UqsTodw.service;

import com.bdcor.datasource.MultipleDataSource;
import com.bdcor.pip.cp3UqsTodw.domain.Answer;
import com.bdcor.pip.cp3UqsTodw.domain.QuestionNaire;
import com.bdcor.pip.cp3UqsTodw.dao.PaperTableCreateDao;
import com.bdcor.pip.cp3UqsTodw.domain.*;
import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 16-11-3.
 */
public class TaskService implements IScheduleTaskDealSingle<Map<String,String>>{

    Logger log = LoggerFactory.getLogger(TaskService.class);

    String maxDateStr = null;
    Map<String,Date> dateMap = new HashMap<String,Date>();

    @Autowired
    private PaperTableCreateDao uqsToDwDao;

    public static List<QuestionNaire> qnList;

    public boolean execute(Map<String, String> task, String ownSign) throws Exception {
        log.info("开始执行数据操作");
        MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
        QuestionNaire qn = getQnById(task.get("UQS_VERSION"));
        String patientId = task.get("PATIENT_ID");
        List<Answer> aList = uqsToDwDao.getAnswerList(task.get("UQS_VERSION"),patientId);
        
        if(aList==null || aList.size()==0)return true;

        StringBuilder inSql = new StringBuilder("INSERT into G"+qn.getId()+"_DATA(PROJECT_ID,VERSION,PATIENT_ID");
        StringBuilder vSql = new StringBuilder("VALUES('"+qn.getProjectId()+"','"+aList.get(0).getVersion()+"','"+patientId+"'");

        Map<String,String> answerMap = getAnswerMap(aList);
        for(QuestionSet s : qn.getSets()){
            for(Question q : s.getQuestions()){
                if( (  qn.getId().contains("004011") && s.getId().equals("3") && !(q.getId().equals("1")|| q.getId().equals("2")) )
                    || ( qn.getId().contains("004011") && s.getId().equals("4"))
                 ){
                    continue;
                }
                //写死部分
                if(qn.getId().equals("004001")){
                	if(s.getId().equals("6") && !q.getId().equals("1") && !q.getId().equals("2")  || s.getId().equals("7"))continue;
                }else if(qn.getId().equals("004002002")){
                	if(s.getId().equals("5"))continue;
                }else if(qn.getId().equals("004011")){
                	if(s.getId().equals("3") && !q.getId().equals("1") && !q.getId().equals("2")  || s.getId().equals("4"))continue;
                }
                
                int cycleNum=0;
//                if(s.getCycle()==1 && !"1".equals(q.getId()))cycleNum=20;// cp3 循环提不予考虑
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
            MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_DW);
            uqsToDwDao.execInsertSql(inSql.toString()+")"+vSql+")");
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return true;
    }


    public List<Map<String, String>> selectTasks(String str, String s1, int intValue, List<TaskItemDefine> taskItemList, int i1) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String dateStr = sdf.format(new Date());
        
        if(qnList==null){
        	MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
            qnList = getQnList();
        }
        List<Map<String,String>> rList = new ArrayList<Map<String,String>>();
        
        for(int i=0;i<taskItemList.size();i++){
            String taskId = taskItemList.get(i).getTaskItemId().toUpperCase();
            if(dateMap.get(taskId) != null && dateMap.get(taskId).getTime()-new Date().getTime()<6l*3600*1000)continue;
            dateMap.put(taskId, new Date());
            log.info(dateStr+":"+taskId+"开始执行");
            System.out.println(dateStr+":"+taskId+"开始执行");
            
            //分类别处理{COPY:包括视图和表复制到CP3}
            if(taskId.startsWith("COPY_")){
            	Pattern pat = Pattern.compile("\\[([^\\]]+)]");
            	Matcher mat=pat.matcher(taskId);
            	int m=0;
            	Map<String,String> copyParam = new HashMap<String, String>();
            	while(mat.find()){
            		if(m==0){
            			copyParam.put("from", mat.group(1));
            		}
            		copyParam.put("to", mat.group(1));
            		m++;
            	}
            	if(m==0)continue;
            	
            	MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_DW);
            	if(uqsToDwDao.isTableExists(copyParam.get("to")) > 0){
            		uqsToDwDao.dropTable(copyParam.get("to"));
            		System.out.println("删除表:"+copyParam.get("to"));
            	}
            	uqsToDwDao.bakTable(copyParam.get("to"),copyParam.get("from")+"@TO_190CP3");
            }
            else if(taskId.startsWith("UQS_")){
            	String itemCode = taskId.substring(4);
            	
            	
            	MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
            	uqsToDwDao.clearTable("G"+itemCode+"_DATA@TO_122DW");
                List<Map<String,String>> pIdList = uqsToDwDao.getNewPIdList(itemCode);
                rList.addAll(pIdList == null ? new ArrayList<Map<String,String>>() : pIdList);
            }

        }
        
        if(rList.size()==0)return null;
        
        return rList;
    }

    public Comparator<Map<String, String>> getComparator() {
        return null;
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
    //  创建数据表
    public boolean createTable(){
        MultipleDataSource.setDataSourceKey(MultipleDataSource.Source.DATASOURCE_CP3);
        List<QuestionNaire> qnList = uqsToDwDao.getQnList();
        for(QuestionNaire qn : qnList){
            String tableName = "G"+qn.getId();
            StringBuilder cSql = new StringBuilder("CREATE TABLE "+tableName+"(\nPROJECT_ID VARCHAR2(32),\nPATIENT_ID VARCHAR2(32),VERSION  VARCHAR2(32)");
            StringBuilder cycleSql = new StringBuilder("CREATE TABLE "+tableName+"_CYCLE(\nPROJECT_ID VARCHAR2(32),\nPATIENT_ID VARCHAR2(32),VERSION  VARCHAR2(32)");
            boolean hasCycle=false;
            for(QuestionSet s : qn.getSets()){
                for(Question q : s.getQuestions()){
                    int cycleNum=0;
                    if(s.getCycle()==1 && !"1".equals(q.getId())){
                        cycleNum=20;
                        hasCycle = true;
                    }
                    for(int i=0;i<=cycleNum;i++){
                        //单选
                        if(q.getType().equals("SINGLE")){
                            if(i==0){
                                cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+" VARCHAR2(8)");
                            }else{
                                cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+" VARCHAR2(8)");
                            }
                        }

                        //单选填空
                        else if(q.getType().equals("SINGLEFILL")){
                            cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+" VARCHAR2(8)");

                            if(q.getOptions()==null || q.getOptions().size()==0)continue;
                            for(Option o : q.getOptions()){
                                if(o.getStyle().equals("FILLBLANK") || o.getStyle().equals("TEXTAREA")){
                                    if(i==0){
                                        cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+"_TEXT "+o.getType());
                                    }else{
                                        cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+"_TEXT "+o.getType());
                                    }
                                }
                            }
                        }

                        //多选
                        else if(q.getType().equals("MULTI")){
                            if(q.getOptions()==null || q.getOptions().size()==0)continue;
                            for(Option o : q.getOptions()){
                                if(i==0){
                                    cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" VARCHAR2(8)");
                                }else{
                                    cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" VARCHAR2(8)");
                                }
                            }
                        }

                        //多选填空
                        else if(q.getType().equals("MULTIFILL")){
                            if(q.getOptions()==null || q.getOptions().size()==0)continue;
                            for(Option o : q.getOptions()){
                                if(o.getStyle().equals("MULTI")){
                                    if(i==0){
                                        cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" VARCHAR2(8)");
                                    }else{
                                        cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" VARCHAR2(8)");
                                    }
                                }else if(o.getStyle().equals("FILLBLANK")  || o.getStyle().equals("TEXTAREA")){
                                    if(i==0){
                                        cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+"_TEXT "+o.getType());
                                    }else{
                                        cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+"_TEXT "+o.getType());
                                    }

                                }
                            }
                        }

                        //填空
                        else if(q.getType().equals("FILLBLANK")){
                            if(q.getOptions()==null || q.getOptions().size()==0)continue;
                            for(Option o : q.getOptions()){
                                if(i==0){
                                    cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" "+o.getType());
                                }else{
                                    cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" "+o.getType());
                                }
                            }
                        }

                        //表格
                        else if(q.getType().equals("TABLE")){
                            if(q.getOptions()==null || q.getOptions().size()==0)continue;
                            for(Option o : q.getOptions()){
                                if(i==0){
                                    cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId().replace("-", "_T_")+" "+o.getType());
                                }else{
                                    cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId().replace("-", "_T_")+" "+o.getType());
                                }
                            }
                        }
                    }
                }
            }

            cSql.append(")");
            uqsToDwDao.execUpdateSql("drop table "+tableName); // 删除历史表
            uqsToDwDao.execUpdateSql(cSql.toString());
            if(hasCycle){
                cycleSql.append(")");
                uqsToDwDao.execUpdateSql("drop table "+tableName.trim()+"_CYCLE"); // 删除历史表
                uqsToDwDao.execUpdateSql(cycleSql.toString());
            }
        }

        return true;
    }

    private String getRQId(String qId, int i) {
        if(i==0)return qId;
        if(i<10)return qId+"00"+i;
        if(i<100)return qId+"0"+i;
        return qId+""+i;
    }

    private QuestionNaire getQnById(String itemCode) {
        for(QuestionNaire qn : qnList){
            if(qn.getId().equals(itemCode)){
                return qn;
            }
        }
        return null;
    }

    private List<QuestionNaire> getQnList(){
        List list = uqsToDwDao.getQnList();
        return list == null ? new ArrayList<QuestionNaire>() : list;
    }

    private String getV(Map<String, String> map,String key,String realType){
        if(map.get(key)==null){
            return null;
        }
        if("DATE".equals(realType)){
            String d = map.get(key).trim();
            try{
                new SimpleDateFormat("yyyy-MM-dd").parse(d);
            }catch (Exception e) {
                d = "1900-01-01";
            }
            return "TO_DATE('"+d+"','yyyy-MM-dd')";
        }else if("DATETIME".equals(realType)){
            String d = map.get(key).trim();
            try{
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
            }catch (Exception e) {
                d = "1900-01-01 00:00:00";
            }
            return "TO_DATE('"+d+"','yyyy-MM-dd hh24:mi:ss')";
        }else{
            return "'"+map.get(key).trim()+"'";
        }
    }
}
