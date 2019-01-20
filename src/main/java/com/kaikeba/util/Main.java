package com.kaikeba.util;

import com.kaikeba.impl.DeptMapper;
import com.kaikeba.service.SqlSession;

public class Main {
	public static void main(String[] args) throws Exception {
		
		//代理模式    		
		//被代理的对象 Person
//		jvm拦截被代理的对象，拦截到了然后对对象进行处理（Invocation）
		SqlSession sqlSession = SqlSessionFactory.builder(DeptMapper.class);
		int value = sqlSession.save("insert into dept values(50,'TEST','BEIJING')");
		System.out.println(value);
	}
}
