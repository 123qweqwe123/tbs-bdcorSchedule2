package com.bdcor.peace3.mobileSendRule.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.util.MyBatisRepository;

@MyBatisRepository
public interface CommDao {
	
	List<Map<String,Object>> query(@Param("sql")String sql);

}
