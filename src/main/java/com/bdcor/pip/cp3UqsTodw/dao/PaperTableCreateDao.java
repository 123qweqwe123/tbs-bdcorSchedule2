package com.bdcor.pip.cp3UqsTodw.dao;


import com.bdcor.pip.cp3UqsTodw.domain.Answer;
import com.bdcor.pip.cp3UqsTodw.domain.QuestionNaire;
import com.bdcor.util.MyBatisRepository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface PaperTableCreateDao {

	List<QuestionNaire> getQnList();
	
	void execUpdateSql(String sql);
	
	void execInsertSql(String sql);
	
	int checkHasInsert(@Param("patientId") String patientId, @Param("qnId") String qnId);

	List<Map<String,String>> getNewPIdList(@Param("qnId") String qnId);

	List<Answer> getAnswerList(@Param("qnId") String qnId, @Param("patientId") String patientId);

	String test();

	void bakTable(@Param("target") String target,@Param("src") String src);

	int isTableExists(@Param("table_name")String table_name);
    void reNameTable(@Param("src")String src , @Param("target")String target);

    void dropTable(@Param("tablename") String tablename);

	void clearTable(@Param("tableName")String tableName);
}
