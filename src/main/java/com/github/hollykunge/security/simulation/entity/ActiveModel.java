package com.github.hollykunge.security.simulation.entity;

import java.util.List;

public class ActiveModel {

	private String modelName;
	
	private List<String> chargers;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public List<String> getChargers() {
		return chargers;
	}

	public void setChargers(List<String> chargers) {
		this.chargers = chargers;
	}

	@Override
	public String toString() {
		return "ActiveModel [modelName=" + modelName + ", chargers=" + chargers + "]";
	}
	
	
}
