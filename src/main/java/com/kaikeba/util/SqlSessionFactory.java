package com.kaikeba.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import com.kaikeba.service.SqlSession;

public class SqlSessionFactory {

	
	public static SqlSession builder(Class className) throws Exception{
		//创建被监控的对象
		SqlSession obj = (SqlSession) className.newInstance();
		//创建通知对象
		InvocationHandler handler = new Invocation(obj);
		//向JVM申请
		SqlSession _proxy = (SqlSession) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
		return _proxy;
	}
}
