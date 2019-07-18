package com.xinyi.test;

import com.xinyi.service.MaterialDataService;

public class notifyModel {
	private String admin;
	private String Time;
	private String[] Materials;
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
	public String[] getMaterials() {
		return Materials;
	}
	public void setMaterials(String[] materials) {
		Materials = materials;
	}
	
}
