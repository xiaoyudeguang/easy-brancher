package com.zlyx.easybrancher.abstracts;

import java.util.Map;

/**
 * @Auth 赵光
 * @Describle 抽象分支类
 * @2018年12月27日 下午8:52:48
 */
public abstract class AbstractBrancher {

	/**
	 *  分支业务方法
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public abstract Object doService(Map<String, Object> params) throws Exception;
}
