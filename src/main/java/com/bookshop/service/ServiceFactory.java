package com.bookshop.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {
	
	/**
	 * 登录用户名称
	 */
	public static String loginUsername = "";
	
	/**
	 * 泛型方法，根据服务对象类型创建一个服务对象
	 * @param serviceType
	 * @return
	 */
	@SuppressWarnings("resource")
	public static <T> T createService(Class<T> serviceType){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		T service = context.getBean(serviceType);
		return service;
	}

}
