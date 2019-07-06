package com.xinyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xinyi.service.MaterialDataService;

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
	
}
