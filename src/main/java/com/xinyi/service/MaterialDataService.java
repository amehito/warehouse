package com.xinyi.service;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinyi.bean.XinyiMaterial;
import com.xinyi.bean.XinyiModifyhistory;
import com.xinyi.bean.XinyiPicking;
import com.xinyi.bean.XinyiPickingExample;
import com.xinyi.dao.XinyiMaterialMapper;
import com.xinyi.dao.XinyiModifyhistoryMapper;
import com.xinyi.dao.XinyiPickingMapper;
import com.xinyi.test.notifyModel;
import com.xinyi.utils.MybatisOfSpringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class MaterialDataService {
	public static ObjectMapper jsonCreater = new ObjectMapper();
	static SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static String getMaterialInfo() throws JsonProcessingException {
		
		
		XinyiMaterialMapper mapper = sqlSession.getMapper(XinyiMaterialMapper.class);
		ArrayList<XinyiMaterial> list = (ArrayList<XinyiMaterial>) mapper.selectAll();	
		jsonCreater.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		String result = jsonCreater.writeValueAsString(list);
	//	System.out.println("service"+result);
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
	public static void savePickRequest(notifyModel notify) throws ParseException, JsonProcessingException {
		// TODO Auto-generated method stub
		XinyiPickingMapper mapper = sqlSession.getMapper(XinyiPickingMapper.class);
		XinyiPicking record = new XinyiPicking();
		System.out.println("admin:"+notify.getAdmin());
		record.setName(notify.getAdmin());
		record.setTime(new Date());
		System.out.println(notify.getTime()+"传入时间："+notify.getTime());
		String maString = jsonCreater.writeValueAsString(notify.getMaterials());
		record.setMaterials(maString);
		record.setPlus("未通过");
	//	record.setMaterials("测试");
		System.out.println("material:"+maString);

		mapper.insertSelective(record);
		sqlSession.commit();
		
		System.out.println("savePickRequest:");
		
	}
	public static ArrayList<XinyiPicking> getUncompletes() {
		// TODO Auto-generated method stub
		
		XinyiPickingMapper mapper = sqlSession.getMapper(XinyiPickingMapper.class);
		
		XinyiPickingExample example = new XinyiPickingExample();
		com.xinyi.bean.XinyiPickingExample.Criteria criteria = example.createCriteria();
		criteria.andPlusEqualTo("未通过");
		sqlSession.clearCache();
		
		ArrayList<XinyiPicking> list =(ArrayList<XinyiPicking>) mapper.selectByExample(example );
		return list;
	}
	
	public static boolean passRequest(int id,String admin) {
		// TODO Auto-generated method stub
		XinyiPickingMapper mapper = sqlSession.getMapper(XinyiPickingMapper.class);
		try {
			XinyiPicking record = new XinyiPicking();
			record.setId(id);
			record.setPlus(admin+"通过");
			//修改材料表中的数量
			XinyiMaterialMapper materialMapper = sqlSession.getMapper(XinyiMaterialMapper.class);
			XinyiMaterial material;
			String data = mapper.selectByPrimaryKey(id).getMaterials();
			com.alibaba.fastjson.JSONArray jsonArray = com.alibaba.fastjson.JSONArray.parseArray(data);
			for(int i=0;i<jsonArray.size();i++) {
				com.alibaba.fastjson.JSONObject object = jsonArray.getJSONObject(i);
			    String materialId =	(String) object.get("materialId");
			   
			    int num = object.getIntValue("number");
			    material = materialMapper.selectByPrimaryKey(materialId);
			    int stockNum = material.getStockNumber();
			    material.setStockNumber(stockNum - num);
			    materialMapper.updateByPrimaryKey(material);
			}
			mapper.updateByPrimaryKeySelective(record);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			return false;
		}
		
		return true;
		
	}
	public static String getAllRequestInfo() throws JsonProcessingException {
		// TODO Auto-generated method stub
		XinyiPickingMapper mapper = sqlSession.getMapper(XinyiPickingMapper.class);
		jsonCreater.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"));
		String result = jsonCreater.writeValueAsString(mapper.selectAll());

		return result;
	}
	
}
