package com.zlyx.easybrancher.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Auth 赵光
 * @Describle 分支业务接口
 * @2018年12月27日 下午8:46:03
 */
public interface IBrancher extends ApplicationListener<ContextRefreshedEvent>{

	/**
	  *  执行指定某个key对应分支
	 * @param key
	 * @param params
	 * @return
	 */
	public Object dispatch(String key, Map<String,Object> params);
	
	/**
	  *  执行指定多个key对应分支
	 * @param key
	 * @param params
	 * @return
	 */
	default void dispatch(List<String> keys, Map<String,Object> params) {
		for(String key: keys){
			dispatch(key,params);
		}
	}
	
	/**
	 *  执行所有分支并返回执行情况(不保证全部成功，只抛出异常)
	 * @param params
	 * @return 
	 * @throws Exception 
	 */
	public void doService(Map<String,Object> params) throws Exception;
}
