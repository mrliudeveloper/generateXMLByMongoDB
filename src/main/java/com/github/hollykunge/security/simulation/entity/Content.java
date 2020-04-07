package com.github.hollykunge.security.simulation.entity;

public class Content {

	private String key;
	
	private String type;
	
	private String name;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Content [key=" + key + ", type=" + type + ", name=" + name + "]";
	}
	
	
	
}
