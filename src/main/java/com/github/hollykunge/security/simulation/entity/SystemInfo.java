package com.github.hollykunge.security.simulation.entity;

import java.util.ArrayList;
import java.util.List;

public class SystemInfo {

	
	private FlowData flowData=new FlowData();
	
	private List<ActiveModel> activeModels=new ArrayList<ActiveModel>();
	
	private List<ActiveDataType> activeDataTypes=new ArrayList<ActiveDataType>();
	
	private List<Interface> interfaceList=new ArrayList<Interface>();

	public FlowData getFlowData() {
		return flowData;
	}

	public void setFlowData(FlowData flowData) {
		this.flowData = flowData;
	}

	public List<ActiveModel> getActiveModels() {
		return activeModels;
	}

	public void setActiveModels(List<ActiveModel> activeModels) {
		this.activeModels = activeModels;
	}

	public List<ActiveDataType> getActiveDataTypes() {
		return activeDataTypes;
	}

	public void setActiveDataTypes(List<ActiveDataType> activeDataTypes) {
		this.activeDataTypes = activeDataTypes;
	}

	public List<Interface> getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(List<Interface> interfaceList) {
		this.interfaceList = interfaceList;
	}

	@Override
	public String toString() {
		return "SystemInfo [flowData=" + flowData + ", activeModels=" + activeModels + ", activeDataTypes="
				+ activeDataTypes + ", interfaceList=" + interfaceList + "]";
	}

		
	
}