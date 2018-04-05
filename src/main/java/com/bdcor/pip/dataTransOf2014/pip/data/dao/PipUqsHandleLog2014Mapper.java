package com.bdcor.pip.dataTransOf2014.pip.data.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.dataTransOf2014.pip.data.domain.PipUqsHandleLog2014;
import com.bdcor.pip.dataTransOf2014.pip.data.domain.PipUqsHandleLog2014Example;

public interface PipUqsHandleLog2014Mapper {
    int countByExample(PipUqsHandleLog2014Example example);

    int deleteByExample(PipUqsHandleLog2014Example example);

    int insert(PipUqsHandleLog2014 record);

    int insertSelective(PipUqsHandleLog2014 record);

    List<PipUqsHandleLog2014> selectByExample(PipUqsHandleLog2014Example example);

    int updateByExampleSelective(@Param("record") PipUqsHandleLog2014 record, @Param("example") PipUqsHandleLog2014Example example);

    int updateByExample(@Param("record") PipUqsHandleLog2014 record, @Param("example") PipUqsHandleLog2014Example example);
}