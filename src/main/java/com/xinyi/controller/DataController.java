package com.xinyi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xinyi.bean.XinyiMaterial;
import com.xinyi.service.MaterialDataService;
import com.xinyi.test.ChangeMaterialInfo;

@Controller
@RequestMapping("/Material")
public class DataController {
	
	@RequestMapping(value="/getMaterialInfo",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getMaterialInfo() throws JsonProcessingException {
		String result =  MaterialDataService.getMaterialInfo();
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value="/getmodifyhistory",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getmodifyhistory() throws JsonProcessingException {
		String result =  MaterialDataService.getmodifyhistoryInfo();
		System.out.println("control"+result);
		return result;
	}
	
	
	@RequestMapping(value="/changematerialnumber",produces="application/json;charset=utf-8")
	@ResponseBody
	public String changematerialnumber(XinyiMaterial info) throws JsonProcessingException {
		
		System.out.println("info: "+info.toString());
		String result =  MaterialDataService.changematerialnumber(info);
		System.out.println("controlresult"+result);
		return result;
	}
	
	@RequestMapping(value="/addmaterial",produces="application/json;charset=utf-8")
	@ResponseBody
	public String Addmaterial(XinyiMaterial info) throws JsonProcessingException {
		
		String result =  MaterialDataService.addMaterial(info);
		MaterialDataService.addChangeHistory(info);
		System.out.println("controlresult"+result);
		return result;
	}
}
