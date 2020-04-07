package com.github.hollykunge.security.simulation.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class DataInfo {
	@Id
	private String _id;
	@Field
	private SystemInfo systemInfo;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public SystemInfo getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}

	@Override
	public String toString() {
		return "DataInfo [_id=" + _id + ", systemInfo=" + systemInfo + "]";
	}
	
	
}
