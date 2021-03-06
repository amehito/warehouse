package com.xinyi.test;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.SimpleFormatter;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.omg.IOP.CodecPackage.FormatMismatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xinyi.bean.XinyiImport;
import com.xinyi.bean.XinyiMaterial;
import com.xinyi.bean.XinyiPicking;
import com.xinyi.bean.XinyiPickingExample;
import com.xinyi.bean.XinyiUser;
import com.xinyi.bean.XinyiUserExample;
import com.xinyi.bean.XinyiUserExample.Criteria;
import com.xinyi.dao.XinyiImportMapper;
import com.xinyi.dao.XinyiMaterialMapper;
import com.xinyi.dao.XinyiPickingMapper;
import com.xinyi.dao.XinyiUserMapper;
import com.xinyi.utils.MybatisOfSpringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MapperTest {
	@Resource

	
	public static void main(String[] args) {
//		XinyiUserMapper xinyiUserMapper;
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		TimeZone zone = TimeZone.getDefault();
//		format.setTimeZone(zone);
//		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
//		XinyiMaterialMapper Mapper = sqlSession.getMapper(XinyiMaterialMapper.class);
//		XinyiMaterial record = new XinyiMaterial();
//		record.setMaterialId("10000000011");
//		record.setViceId("AC0003");
//		record.setMaterialName("测试名称2");
//		record.setMaterialSpec("测试规格0002");
//		record.setWarehousePosition("AP0000002");
//		record.setMaterialType("断路器");
//		record.setMaterialUnit("只");
//		record.setMaterialPrice((float)9.5);
//		record.setStockNumber(3);
//		record.setStockSafe(2);
//		record.setBatchManage("不确定");
//		
//		record.setStartTime(new Date());
//		 
//		record.setCreateManager("叶叶叶");
//		
//		
//		Mapper.insert(record);
//		sqlSession.commit();
		
//		XinyiUserExample example = new XinyiUserExample();
//		Criteria createCriteria = example.createCriteria();
//		createCriteria.andUserUsernameEqualTo("Mark");
//		XinyiUser xinyiUser = userMapper.selectByExample(example).get(0);
//		System.out.println(xinyiUser.getUserUsername());
//		sqlSession.close();
		
//		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
//		sqlSession.select(arg0, arg1);
		
	}

	
	public void t() throws JsonProcessingException {
		ObjectMapper jsonCreater = new ObjectMapper();
		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();		
		XinyiPickingMapper mapper = sqlSession.getMapper(XinyiPickingMapper.class);
	
		XinyiPicking record = new XinyiPicking();
		record.setId(10);
		XinyiMaterialMapper materialMapper = sqlSession.getMapper(XinyiMaterialMapper.class);
		String data = mapper.selectByPrimaryKey(10).getMaterials();
		com.alibaba.fastjson.JSONArray array = com.alibaba.fastjson.JSONArray.parseArray(data);
		com.alibaba.fastjson.JSONObject object = array.getJSONObject(0);
	//	JSONArray jsonArray = JSONArray.fromObject(data);
	//	JSONObject object = (JSONObject) jsonArray.get(0);
//		System.out.println(object.get("number"));
		sqlSession.commit();
		
	 
	}
	//新益picking测试
	@Test
	public void tt() throws InterruptedException {		
		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
		List<XinyiImport> list = sqlSession.getMapper(XinyiImportMapper.class).selectAll();
		System.out.println(list.size());
		System.out.println(1);
		File file = new File("C:\\Users\\45981\\Desktop\\机物料");
		ArrayList<String> list2 = new ArrayList<String>();
		traverse(list2,file);
		System.out.println(list2.size());
	}
	int i = 0;
	public void traverse(ArrayList list,File file) {
		
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f:files) {
				traverse(list,f);
			}
		}
		else {
			list.add(file.getName());
		}
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









































