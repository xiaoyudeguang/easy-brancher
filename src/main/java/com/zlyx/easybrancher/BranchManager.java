package com.zlyx.easybrancher;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.StringUtils;

import com.zlyx.easybrancher.abstracts.AbstractBrancher;
import com.zlyx.easybrancher.annotations.Brancher;
import com.zlyx.easybrancher.interfaces.IBrancher;

/**
 * @Auth 赵光
 * @Describle 分支管理机
 * @2018年12月27日 下午8:44:16
 */
public class BranchManager implements IBrancher{
	
	private Map<String, AbstractBrancher> branchs = new HashMap<String,AbstractBrancher>();

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Map<String, Object> beanMap = event.getApplicationContext().getBeansWithAnnotation(Brancher.class);
		Object branchBean = null;
		Brancher branchAnnotation = null;
		for(String beanName : beanMap.keySet()) {
			branchBean = beanMap.get(beanName);
			branchAnnotation = branchBean.getClass().getAnnotation(Brancher.class);
			if(branchAnnotation.isOpen() && branchBean instanceof AbstractBrancher) {
				branchs.put(branchAnnotation.key(), (AbstractBrancher)branchBean);
			}
		}
	}
	
	@Override
	public Object dispatch(String key, Map<String,Object> params) {
		if(!StringUtils.isEmpty(key) && branchs.containsKey(key)) {
			try {
				return branchs.get(key).doService(params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void doService(Map<String, Object> params) throws Exception {
		for(String key : branchs.keySet()){
			branchs.get(key).doService(params);
		}
	}
	
}
