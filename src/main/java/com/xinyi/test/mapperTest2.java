package com.xinyi.test;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.xinyi.dao.XinyiUserMapper;

@Service
public class mapperTest2 {
	@Autowired
	private static XinyiUserMapper xinyiUserMapper;
	@Autowired
	private static ApplicationContext context;
	 
	public static void main(String[] args) {
		context.getBeanDefinitionNames();
	}

	@Test
	public void test() {

		ArrayList<String> list = (ArrayList<String>) Arrays.asList(context.getBeanDefinitionNames());
		for(String s:list) {
			System.out.println(s);
		}
		xinyiUserMapper.selectByPrimaryKey(5);
	}
}
