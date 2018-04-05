package com.bdcor.pip.copyTableToDw.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.util.MyBatisRepository;

@MyBatisRepository
public interface CopyTableToDwDao {

	int dataCount(@Param("tableName")String tableName);
	
	void truncateTable(@Param("tableName")String tableName);
	
	List<String> getLccCodeList_All();
	
	void copyData(@Param("tableName")String tableName,@Param("lccCode")String lccCode);
	
	void copyTableRelation(@Param("lccCode")String lccCode);
}
