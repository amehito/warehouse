package com.xinyi.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinyi.bean.XinyiManufactures;
import com.xinyi.bean.XinyiSupplierInfo;
import com.xinyi.dao.XinyiManufacturesMapper;
import com.xinyi.dao.XinyiMaterialMapper;
import com.xinyi.dao.XinyiSupplierInfoMapper;
import com.xinyi.utils.MybatisOfSpringUtil;

@Controller
@RequestMapping("/OtherInfo")
public class OthersController {
	
	private static ObjectMapper jsonCreater = new ObjectMapper() ;
	SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
//	XinyiMaterialMapper Mapper = sqlSession.getMapper(XinyiMaterialMapper.class);

	@RequestMapping(value="/manufactures",method=RequestMethod.GET
			,produces="application/json;charset=utf-8")
	@ResponseBody
	
	public String getManufacturesList() throws JsonProcessingException {
		XinyiManufacturesMapper mapper = sqlSession.getMapper(XinyiManufacturesMapper.class);
		List<XinyiManufactures> list = mapper.selectAll();
		String result = jsonCreater.writeValueAsString(list);
		System.out.println(result);
		return result ;
		
	}
	
	@RequestMapping(value="/supplier",method=RequestMethod.GET
			,produces="application/json;charset=utf-8")
	public @ResponseBody String getSupplierInfo() throws JsonProcessingException {
		XinyiSupplierInfoMapper mapper = sqlSession.getMapper(XinyiSupplierInfoMapper.class);
		List<XinyiSupplierInfo> list = mapper.selectAll();
		System.out.println(list.size());
		
		return jsonCreater.writeValueAsString(list);
	}
}
