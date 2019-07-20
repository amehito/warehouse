package com.xinyi.test;

import java.util.List;
import java.util.Map;

import com.xinyi.service.MaterialDataService;

public class notifyModel {
	private String admin;
	private String Time;
	private List<Material> Materials;

	
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public List<Material> getMaterials() {
		return Materials;
	}
	public void setMaterials(List<Material> materials) {
		Materials = materials;
	}
	
	
	
	
}
