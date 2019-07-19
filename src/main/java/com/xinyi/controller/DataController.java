package com.xinyi.controller;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mysql.cj.protocol.x.Notice;
import com.xinyi.bean.XinyiMaterial;
import com.xinyi.service.MaterialDataService;
import com.xinyi.test.ChangeMaterialInfo;
import com.xinyi.test.Material;
import com.xinyi.test.notifyModel;

@Controller
@RequestMapping("/Material")
public class DataController {
	notifyModel notify;
	final static int  materialInfoNumber = 4;
	ArrayList<notifyModel> list = new ArrayList<notifyModel>();
	@RequestMapping(value="/notify",produces="application/json;charset=utf-8")
	@ResponseBody
	public String Notice(@RequestParam(value="materials[0][materialId]",required = false) String info,
            HttpServletRequest request) {
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
        System.out.println(notify.getAdmin());
        System.out.println(notify.getTime());
        
//        params.remove("user");
//        params.remove("Time");
        System.out.println(params.size());
        ArrayList<Material> materialList = new ArrayList<Material>();
        for(int i=0;i<params.size()/materialInfoNumber;i++) {
        	Material material = new Material();
        	material.setMaterialId(params.get("materials["+i+1+"][materialId]")[0]);
        	material.setMaterial(params.get("materials["+i+1+"][material]")[0]);
        	material.setNumber(Integer.parseInt(params.get("materials["+i+1+"][number]")[0]));
        	material.setUnit(params.get("materials["+i+1+"][unit]")[0]);
        	materialList.add(material);
        }
        notify.setMaterials(materialList);
        list.add(notify);
        System.out.println(list.get(0).getMaterials().get(1).getMaterial());
		return "success";
		
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
