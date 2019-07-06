package com.xinyi.service;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinyi.bean.XinyiMaterial;
import com.xinyi.bean.XinyiModifyhistory;
import com.xinyi.bean.XinyiUser;
import com.xinyi.dao.XinyiMaterialMapper;
import com.xinyi.dao.XinyiModifyhistoryMapper;
import com.xinyi.dao.XinyiUserMapper;
import com.xinyi.utils.MybatisOfSpringUtil;



public class MaterialDataService {
	public static ObjectMapper jsonCreater = new ObjectMapper();
	static SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
	
	public static String getMaterialInfo() throws JsonProcessingException {
		
		
		XinyiMaterialMapper mapper = sqlSession.getMapper(XinyiMaterialMapper.class);
		ArrayList<XinyiMaterial> list = (ArrayList<XinyiMaterial>) mapper.selectAll();	
		jsonCreater.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		String result = jsonCreater.writeValueAsString(list);
		System.out.println("service"+result);
		return result; 
		
	}
	public static String getmodifyhistoryInfo() throws JsonProcessingException {
		// TODO Auto-generated method stub
		
		XinyiModifyhistoryMapper mapper = sqlSession.getMapper(XinyiModifyhistoryMapper.class);
		ArrayList<XinyiModifyhistory> list = (ArrayList<XinyiModifyhistory>) mapper.selectAll();	
		jsonCreater.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		String result = jsonCreater.writeValueAsString(list);
		return result;
	}
}
