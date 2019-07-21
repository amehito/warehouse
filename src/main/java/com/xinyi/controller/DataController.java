package com.xinyi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinyi.bean.XinyiMaterial;
import com.xinyi.bean.XinyiPicking;
import com.xinyi.service.MaterialDataService;
import com.xinyi.test.Material;
import com.xinyi.test.notifyModel;

@Controller
@RequestMapping("/Material")
public class DataController {
	notifyModel notify;
	final static int  materialInfoNumber = 4;
	boolean needQuery = false;
	static boolean firstLogin = true;
	static final String NO_MESSAGE = "no message";
	ArrayList<notifyModel> list = new ArrayList<notifyModel>();
	public static ObjectMapper jsonCreater = new ObjectMapper();
	ArrayList<XinyiPicking> uncompleteList;
	
	@RequestMapping(value="/notificationInit",produces="application/json;charset=utf-8")
	public @ResponseBody String notificationInit() throws JsonProcessingException {
		uncompleteList = MaterialDataService.getUncompletes();
		System.out.println(uncompleteList.size());
		if(uncompleteList ==null) {
			return NO_MESSAGE;
		}
		ArrayList<XinyiPicking> theList =  (ArrayList<XinyiPicking>) uncompleteList.clone();
		jsonCreater.setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh-mm-ss"));
		String result = jsonCreater.writeValueAsString(theList);
		return result;
	}
	
	@RequestMapping(value="/passRequest")
	public @ResponseBody String passRequest(HttpServletRequest request) {
		System.out.println(request.getParameterMap().toString());
		String id = request.getParameter("id");
		System.out.println("id :"+id);
		MaterialDataService.passRequest(Integer.parseInt(id));
		changeNotifyState();
		return "PassSuccess";
	}

	@RequestMapping(value="/showNotification",produces="application/json;charset=utf-8")
	public @ResponseBody String show() throws JsonProcessingException {
//		 =  jsonCreater.writeValueAsString(list);
		//第一次登陆也查询所有
//		if(firstLogin) {
//			changeNotifyState();
//			firstLogin = false;
//		}
		if(!needQuery) {
			return NO_MESSAGE;
		}
		//每一个连接都有一个独立的list
		ArrayList<XinyiPicking> theList =  (ArrayList<XinyiPicking>) uncompleteList.clone();
		String result = jsonCreater.writeValueAsString(theList);
		
		needQuery = false;
		
		System.out.println(list.size());
		return result;
	}
	
	@RequestMapping(value="/notify",produces="application/json;charset=utf-8")
	@ResponseBody
	public String Notice(@RequestParam(value="materials[0][materialId]",required = false) String info,
            HttpServletRequest request) throws JsonProcessingException, ParseException {
		if(info == null)
			return "notice";
		System.out.println(info);
	//	System.out.println(info.getAdmin());
	//	System.out.println(info.getMaterials());
		System.out.println("获取单个参数：");
        System.out.println(info);
        System.out.println("获取所有参数：");	
        Map<String,String[]> params =  request.getParameterMap();
        for(Map.Entry<String,String[]> entry:params.entrySet()){
            System.out.println("key:"+entry.getKey()+" value:"+ Arrays.asList(entry.getValue()));
            
        }
        notify = new notifyModel();
        notify.setAdmin(params.get("user")[0]);
        notify.setTime(params.get("Time")[0]);
 //       System.out.println(notify.getAdmin());
//        System.out.println(notify.getTime());
        
//        params.remove("user");
//        params.remove("Time");
        System.out.println(params.size());
        ArrayList<Material> materialList = new ArrayList<Material>();
        for(int i=0;i<params.size()/materialInfoNumber;i++) {
        	Material material = new Material();
        	material.setMaterialId(params.get("materials["+i+"][materialId]")[0]);
        	material.setMaterial(params.get("materials["+i+"][material]")[0]);
        	material.setNumber(Integer.parseInt(params.get("materials["+i+"][number]")[0]));
        	material.setUnit(params.get("materials["+i+"][unit]")[0]);
        	materialList.add(material);
        }
        notify.setMaterials(materialList);
        MaterialDataService.savePickRequest(notify);
        list.add(notify);
        changeNotifyState();
		return "success";
		
	}
	
	private void changeNotifyState() {
		// TODO Auto-generated method stub
		needQuery = true;
		//得到数据库中未完成的元组，其中plus存放是否完成
		uncompleteList = MaterialDataService.getUncompletes();
		
	}

	@RequestMapping(value="/getMaterialInfo",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getMaterialInfo() throws JsonProcessingException {
		System.out.println(new Date().toString()+"start:getMaterialInfo");
		String result =  MaterialDataService.getMaterialInfo();
		System.out.println(new Date().toString()+"done:getMaterialInfo");
		return result;
	}
	
	@RequestMapping(value="/getmodifyhistory",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getmodifyhistory() throws JsonProcessingException {
		System.out.println(new Date().toString()+"start:getModifyhistory");
		String result =  MaterialDataService.getmodifyhistoryInfo();
		
		System.out.println(new Date().toString()+"done:getmodifyhistory");
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
