package com.github.hollykunge.security.simulation.entity;

import java.util.List;

public class ActiveDataType {

	private String key;
	
	private String name;
	
	private String descripton;
	
	private Integer currentCount;
	
	private List<Content>content;

	
		
	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescripton() {
		return descripton;
	}



	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}



	public Integer getCurrentCount() {
		return currentCount;
	}



	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}



	public List<Content> getContent() {
		return content;
	}



	public void setContent(List<Content> content) {
		this.content = content;
	}



	@Override
	public String toString() {
		return "ActiveDataType [key=" + key + ", name=" + name + ", descripton=" + descripton + ", currentCount="
				+ currentCount + ", content=" + content + "]";
	}
	
	
}
