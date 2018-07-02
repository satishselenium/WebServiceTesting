package com.qa.data;
 
//POJO - Plain Object Data Object
public class Users {
	
	String name;
	String JOb;
	String id;
	String createdAt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public Users() {
		
	}
	public Users(String name, String job) {
		this.name=name;
		this.JOb=job;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJOb() {
		return JOb;
	}
	public void setJOb(String jOb) {
		JOb = jOb;
	}
    
	
	
	
}
