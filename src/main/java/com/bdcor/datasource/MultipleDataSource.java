package com.bdcor.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {
	
    private static final ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();
    
    public synchronized static void setDataSourceKey(Source dataSource) {
        dataSourceKey.set(dataSource.toString());
    }
    
    public synchronized static void getDataSourceKey() {
        dataSourceKey.get();
    }

    @Override
	public Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
    
    public enum Source{
    	
    	DATASOURCE_006{ //高危二期
    		public String toString(){
    			return "dataSource006";
    		}
    	},
    	
    	DATASOURCE_DW{//dw库
    		public String toString(){
    			return "dataSourceDw";
    		}
    	},
        DATASOURCE_CP3{//cp3库
            public String toString(){
                return "dataSourceCp3";
            }
        }

        }
}
