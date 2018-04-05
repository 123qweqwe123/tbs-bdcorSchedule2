package com.bdcor.utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * <pre>
 * 功能说明：自定义PropertyPlaceholderConfigurer返回properties内容
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
public class CustomizedPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	private static Map<String, Object> ctxPropertiesMap;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		ctxPropertiesMap = new HashMap<String, Object>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	/**
	 * 根据key找到value
	 * @param name
	 * @return
	 */
	public static String getContextProperty(String name) {
		return (String)ctxPropertiesMap.get(name);
	} 

}