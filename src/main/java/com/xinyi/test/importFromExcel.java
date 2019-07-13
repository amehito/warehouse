package com.xinyi.test;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xinyi.bean.XinyiMaterial;
import com.xinyi.dao.XinyiMaterialMapper;
import com.xinyi.utils.MybatisOfSpringUtil;

public class importFromExcel {
	
	public void sqltest() {
		SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
		XinyiMaterialMapper Mapper = sqlSession.getMapper(XinyiMaterialMapper.class);
		XinyiMaterial material = new XinyiMaterial();
		for(int i =0 ;i<10;i++) {
			material.setMaterialId("1111111000"+i);
			material.setMaterialName("test");
			material.setMaterialType("test");
			material.setMaterialUnit("test");
			material.setMaterialPrice((float)11);
			material.setStockNumber(11);
			material.setStockSafe(19);
			material.setStartTime(new Date());
			material.setCreateManager("test");
			Mapper.insert(material);
			
		}
		sqlSession.commit();
	}
	@Test
	public void test()  {
		File csv = new File("C:\\Users\\45981\\Desktop\\material.csv");
		try {
			BufferedReader br = new BufferedReader(new FileReader(csv));
			
			String line = "";
			String everyLine = "";
			SqlSession sqlSession = MybatisOfSpringUtil.getSessionFactory().openSession();
			XinyiMaterialMapper Mapper = sqlSession.getMapper(XinyiMaterialMapper.class);
			XinyiMaterial material = new XinyiMaterial();
			List<String> allString = new ArrayList<String>();
			String[] materialInfo = new String[25];
			String[] index ; 
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			while ((line = br.readLine()) != null) // 读取到的内容给line变量
			{
				
				everyLine = line;
				
				allString.add(everyLine);
				index = everyLine.split(",");
				
				for(int i=0;i<index.length;i++) {
					
					materialInfo[i] = index[i];	
				}
				
				if(index.length<21) {
					materialInfo[20] = materialInfo[19] = "";
				}
				//如果日期为空会报错，所以初始化无效日期
				if(materialInfo[16].equals(""))
					materialInfo[16] = "2100/01/01";
				if(materialInfo[19].equals(""))
					materialInfo[19] = materialInfo[17];
				
				if(materialInfo[8].equals("只")) {
					System.out.println(materialInfo[0]);
					System.out.println(materialInfo[1]);
					System.out.println(materialInfo[2]);
					System.out.println(materialInfo[3]);
					System.out.println(materialInfo[4]);
					System.out.println(materialInfo[5]);
					System.out.println(materialInfo[6]);
					System.out.println(materialInfo[7]);
					System.out.println(materialInfo[8]);
					System.out.println(materialInfo[9]);
				}
				
				material.setMaterialId(materialInfo[0]);
				material.setViceId(materialInfo[1]);
				material.setMaterialName(materialInfo[2]);
				material.setMaterialSpec(materialInfo[3]);
				material.setWarehousePosition(materialInfo[4]);
				material.setPlus(materialInfo[5]);
				material.setMaterialType(materialInfo[6]);
				material.setMaterialUnit(materialInfo[7]);
				material.setMaterialPrice(Float.parseFloat(materialInfo[8]));
				
				material.setStockNumber(Integer.parseInt(materialInfo[9]));
				material.setStockSafe(Integer.parseInt(materialInfo[10]));
				material.setBatchManage(materialInfo[13]);
				material.setFinance(materialInfo[14]);
				material.setStatus(materialInfo[15]);
				material.setFinishTime(format.parse(materialInfo[16]));
				material.setStartTime(format.parse(materialInfo[17]));
				material.setCreateManager(materialInfo[18]);
				material.setChangeTime(format.parse(materialInfo[19]));			
				material.setChangeManager(materialInfo[20]);
				Mapper.insert(material);
				
			}
			sqlSession.commit();
			System.out.println("csv表格中所有行数：" + allString.size());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		

	}
	
	
	
	
}
