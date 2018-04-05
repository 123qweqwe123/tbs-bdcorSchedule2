package com.bdcor.pip.uqsToDw.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.uqsToDw.domain.Answer;
import com.bdcor.pip.uqsToDw.domain.QuestionNaire;
import com.bdcor.util.MyBatisRepository;

@MyBatisRepository
public interface UqsToDwDao {

	List<QuestionNaire> getQnList();
	
	List<Map<String,String>> getPIdList(@Param("qnId")String qnId);
	
	void execInsertSql(String sql);
	
	List<Answer> getAnswerList(@Param("area")String area, @Param("qnId")String qnId, @Param("patientId")String patientId);

	
}
