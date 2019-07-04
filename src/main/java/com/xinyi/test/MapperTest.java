package com.xinyi.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.SimpleFormatter;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.omg.IOP.CodecPackage.FormatMismatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinyi.bean.XinyiMaterial;
import com.xinyi.bean.XinyiUser;
import com.xinyi.bean.XinyiUserExample;
import com.xinyi.bean.XinyiUserExample.Criteria;
import com.xinyi.dao.XinyiMaterialMapper;
import com.xinyi.dao.XinyiUserMapper;
import com.xinyi.utils.MybatisOfSpringUtil;

public class MapperTest {
	@Resource
	
	
	public static void main(String[] args) {
		XinyiUserMapper xinyiUserMapper;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		TimeZone zone = TimeZone.getDefault();
		format.setTimeZone(zone);
		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
		XinyiMaterialMapper Mapper = sqlSession.getMapper(XinyiMaterialMapper.class);
		XinyiMaterial record = new XinyiMaterial();
		record.setMaterialId("10000000006");
		record.setViceId("AC0003");
		record.setMaterialName("测试名称2");
		record.setMaterialSpec("测试规格0002");
		record.setWarehousePosition("AP0000002");
		record.setMaterialType("断路器");
		record.setMaterialUnit("只");
		record.setMaterialPrice((float)9.5);
		record.setStockNumber(3);
		record.setStockSafe(2);
		record.setBatchManage("不确定");
		
		record.setStartTime(new Date());
		 
		record.setCreateManager("叶叶叶");
		
		
		Mapper.insert(record);
		sqlSession.commit();
		
//		XinyiUserExample example = new XinyiUserExample();
//		Criteria createCriteria = example.createCriteria();
//		createCriteria.andUserUsernameEqualTo("Mark");
//		XinyiUser xinyiUser = userMapper.selectByExample(example).get(0);
//		System.out.println(xinyiUser.getUserUsername());
//		sqlSession.close();
		
//		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
//		sqlSession.select(arg0, arg1);
		
	}
	
	@Test
	public void tt() throws InterruptedException {
		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
		XinyiUserMapper mapper = sqlSession.getMapper(XinyiUserMapper.class);
		XinyiUser record = new XinyiUser();
		record.setUserUsername("test2");
		record.setUserPassword("123456");
		System.out.println("插入前为"+record.getUserId());
		mapper.insert(record);
		sqlSession.commit();
		System.out.println(record.getUserId());
	}
	
	
	public void test() throws ParseException {
		ObjectMapper jsonCreater = new ObjectMapper();
		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
		XinyiMaterialMapper mapper = sqlSession.getMapper(XinyiMaterialMapper.class);
		ArrayList<XinyiMaterial> list = (ArrayList<XinyiMaterial>) mapper.selectAll();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for(XinyiMaterial material : list) {
			Date time = material.getStartTime();
			System.out.println(format.format(time));
			
		}
		DeserializationConfig cfg= jsonCreater.getDeserializationConfig();
		jsonCreater.setDateFormat(format);
	
		
		try {
			
			System.out.println(jsonCreater.writeValueAsString(list));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
