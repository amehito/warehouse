package com.xinyi.service;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import com.xinyi.test.ChangeMaterialInfo;
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
	public static String changematerialnumber(XinyiMaterial info) {
		System.out.println(info.getStockNumber());
		int num =info.getStockNumber() ;
		XinyiMaterialMapper mapper = sqlSession.getMapper(XinyiMaterialMapper.class);
		XinyiMaterial record = new XinyiMaterial();
		record.setStockNumber(num);
	//	mapper.updateByExampleSelective(record, example);
		// TODO Auto-generated method stub
		return null;
	}
	public static String addMaterial(XinyiMaterial info) {
		System.out.println(info.getMaterialId());
		System.out.println(info.getMaterialName());
		info.setStartTime(new Date());
		info.setCreateManager("测试数据");
		String result = "插入成功";
		// TODO Auto-generated method stub
		XinyiMaterialMapper mapper = sqlSession.getMapper(XinyiMaterialMapper.class);
		try {
			mapper.insert(info);
		}catch (Exception e) {
			// TODO: handle exception
			mapper.updateByPrimaryKeySelective(info);
			result  = "已经存在该信息,以将值修改为最新的";
			System.out.println(e.toString());
		}
		finally {
			sqlSession.commit();
		}
		
		return result;
	}
	public static void addChangeHistory(XinyiMaterial info) {
		// TODO Auto-generated method stub
		XinyiModifyhistoryMapper mapper = sqlSession.getMapper(XinyiModifyhistoryMapper.class);
		XinyiModifyhistory record = new XinyiModifyhistory();
		record.setMaterialid(info.getMaterialId());
		record.setMaterialname(info.getMaterialName());
		record.setMaterialnumber(info.getStockNumber());
		record.setMaterialunit(info.getMaterialUnit());
		record.setModifymanager(info.getCreateManager());
		record.setModifyname("存入");
		record.setModifytime(new Date());
		mapper.insert(record );
		sqlSession.commit();
	}
	
}
