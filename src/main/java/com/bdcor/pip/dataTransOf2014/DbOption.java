package com.bdcor.pip.dataTransOf2014;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import com.bdcor.pip.dataTransOf2014.mybatis3.util.MyBatisSqlSessionFactory;
import com.bdcor.pip.dataTransOf2014.pip.data.dao.PipUqsHandleLog2014Mapper;
import com.bdcor.pip.dataTransOf2014.pip.data.domain.PipUqsHandleLog2014;

public class DbOption {

	SqlSession sqlSession = null;
	public DbOption(){
		sqlSession = MyBatisSqlSessionFactory.openSession();
	}
	
	public void close(){
		sqlSession.commit();
		sqlSession.close();
	}
	long i = 0L;
	/**
	 * @param args
	 * @throws Exception 
	 */
	public void insert(PipUqsHandleLog2014 record) throws Exception {
		
		try {
			PipUqsHandleLog2014Mapper mapper = sqlSession
					.getMapper(PipUqsHandleLog2014Mapper.class);
			
			
			mapper.insert(record);
			
			
		}catch(Exception e){
			throw new Exception(e);
		}
		i++;
		if(i%1000 == 0){
			sqlSession.commit();
		}
		
			
	}

	
	public static void main(String[] a) throws Exception{
		
		DbOption option = new DbOption();
		long start = System.currentTimeMillis();
		PipUqsHandleLog2014 record = new PipUqsHandleLog2014();
		
		record.setFileName("1");
		record.setCreateDate(new Date());
		
		
		option.insert(record);
		long end = System.currentTimeMillis();
		
		System.out.println((end-start));
		
		option.close();
		
		
	}
}
