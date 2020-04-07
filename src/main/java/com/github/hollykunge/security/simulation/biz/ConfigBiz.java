package com.github.hollykunge.security.simulation.biz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;
import org.jdom2.output.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.hollykunge.security.simulation.entity.ActiveDataType;
import com.github.hollykunge.security.simulation.entity.ActiveModel;
import com.github.hollykunge.security.simulation.entity.Content;
import com.github.hollykunge.security.simulation.entity.Interface;
import com.github.hollykunge.security.simulation.entity.SystemInfo;

@Service
public class ConfigBiz {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigBiz.class);

	List<String> topics5=new ArrayList<String>(Arrays.asList(
			"acquire_ready_state","initial_federate",
			"advance_grant","simulation_run","simulation_end"));
	List<String> topics2=new ArrayList<String>(Arrays.asList(
			"advance_request","node_ready"));
	 //XML格式化工具
	 public Format FormatXML(){
	        //格式化生成的xml文件，如果不进行格式化的话，生成的xml文件将会是很长的一行...
	        Format format = Format.getCompactFormat();
	        format.setEncoding("UTF-8");
	        format.setIndent(" ");
	        return format;
	 }
	 //topics下面的静态部分
	 public void generateStaticTopicElement(Element topicsElement) {
			
			List<String> staticTopics=new ArrayList<String>(Arrays.asList(
						"advance_request","node_ready","acquire_ready_state","initial_federate",
						"advance_grant","simulation_run","simulation_end"));
			
			for (int i = 0; i < staticTopics.size(); i++) {
				String topicString = staticTopics.get(i);
				Element topicElement=new Element("Topic");
				topicElement.setAttribute("name",topicString);
				topicsElement.addContent(topicElement);
			}
		}
	 //topics下的用户自定义的部分
	 public  void generateUserTopicElement(Element topicsElement,List<Interface> interfaceList) {
			Map<String,String>topicMap=new HashMap<String,String>();
			for (int i = 0; i < interfaceList.size(); i++) {
				Interface interfaceObj = interfaceList.get(i);
				
				String nameString =interfaceObj.getName();
				String typeString =interfaceObj.getType();
				if (!topicMap.containsKey(nameString)) {	
					topicMap.put(nameString,typeString);
				}	
			}
			for (String name : topicMap.keySet()) {
				Element topicElement=new Element("Topic");
				topicElement.setAttribute("name",name);
				topicElement.setAttribute("type",topicMap.get(name));
				topicsElement.addContent(topicElement);
			}
		}
	//ScenarioInfo标签及其内容
	public void generateScenarioInfo(String id ,String modelName,Element militaryScenarioelElement) {
			Element scenarioInfoElement=new Element("ScenarioInfo");
			militaryScenarioelElement.addContent(scenarioInfoElement);
			
			Element nameElement=new Element("Name");
			nameElement.setText("测试项目");
			scenarioInfoElement.addContent(nameElement);
			Element idElement=new Element("Id");
			idElement.setText(id);
			scenarioInfoElement.addContent(idElement);
			Element nodeElement=new Element("Node");
			nodeElement.setText(modelName);	
			scenarioInfoElement.addContent(nodeElement);
		}
	//生成整个文档
	public void generateDocument(String id, String modelName, org.jdom2.Document rootDocument, SystemInfo systemInfo) {
		Element militaryScenarioelElement=new Element("MilitaryScenario");
		rootDocument.addContent(militaryScenarioelElement);	

		//创建二级节点ScenarioInfo:name/Id/Node
		this.generateScenarioInfo(id, modelName, militaryScenarioelElement);
		
		//创建二级节点TypeDefine/Type
		List<ActiveDataType> activeDataTypes = systemInfo.getActiveDataTypes();
		this.generateTypeDefineElement(activeDataTypes,militaryScenarioelElement);
		
		//创建二级节点Topics
		Element topicsElement=new Element("Topics");
		militaryScenarioelElement.addContent(topicsElement);
		
		//创建预留主题区
		this.generateStaticTopicElement(topicsElement);
		//创建用户主题区
		List<Interface> interfaceList = systemInfo.getInterfaceList();
		this.generateUserTopicElement(topicsElement, interfaceList);
		
		//创建二级节点models
		
		Element modelsElement=new Element("Models");
		militaryScenarioelElement.addContent(modelsElement);
		
		this.generateModelsElement(systemInfo, interfaceList, modelsElement);
	}
	//typedefine标签及其内容
	public void generateTypeDefineElement(List<ActiveDataType> activeDataTypes,Element militaryScenarioelElement) {
		Element typeDefineElement=new Element("TypeDefine");	
		militaryScenarioelElement.addContent(typeDefineElement);
		for (int i = 0; i < activeDataTypes.size(); i++) {
			ActiveDataType activeDataType = activeDataTypes.get(i);
			generateTypeElement(typeDefineElement,activeDataType);
		}
	}
	//typedefine下的type的具体内容
	private  void generateTypeElement(Element typeDefineElement,ActiveDataType activeDataType) {
		String name = activeDataType.getName();
		String descripton = activeDataType.getDescripton();
		List<Content> content = activeDataType.getContent();
		//创建typedefine的子节点
		Element typeElement=new Element("Type");
		//typeElement.setAttribute("name",name);
		setValue(typeElement, "name", name);
		//typeElement.setAttribute("description", descripton);
		setValue(typeElement,"description", descripton);
		typeDefineElement.addContent(typeElement);
		for (int j = 0; j < content.size(); j++) {
			
			Content obj = content.get(j);
			String typeName =obj.getName();
			String type = obj.getType();
			Element parameterElement=new Element("Parameter");
			parameterElement.setAttribute("name", typeName);
			parameterElement.setAttribute("type", type);
			typeElement.addContent(parameterElement);
		}
	}
	//1+n中1的静态部分
	public void generateStaticModelElement(Element publishElement, Element subscribeElement) {
		addChildrenElement(publishElement, topics5);
		addChildrenElement(subscribeElement, topics2);
	}
	//1+n中n的静态部分
	public void generateUserModelElement(Element publishElement, Element subscribeElement) {
		addChildrenElement(publishElement, topics2);
		addChildrenElement(subscribeElement, topics5);
	}
	//为element添加属性
	public void addChildrenElement(Element Element, List<String> topics) {
		for (int e = 0; e <topics.size(); e++) {
			String topicName = topics.get(e);
			Element topicElement=new Element("Topic");
			topicElement.setAttribute("topicName", topicName);
			Element.addContent(topicElement);
		}
	}
	//1+n中1的订阅部分
	public void generateUserPublishElement(List<Interface> interfaceList, Element subscribeElement) {
		for (int i = 0; i < interfaceList.size(); i++) {
			Interface interfaceObj = interfaceList.get(i);
			String category = interfaceObj.getCategory();
			if (category.equals("output")) {
				Element topicElement=new Element("Topic");
				topicElement.setAttribute("topicName", interfaceObj.getName());
				subscribeElement.addContent(topicElement);
			}
		}
	}
	//防止null赋值问题
	public void setValue(Element element,String name,String value)
	{
		element.setAttribute(name, value==null?"":value);
	}
	//models标签及其具体内容
	public void generateModelsElement(SystemInfo systemInfo, List<Interface> interfaceList, Element modelsElement) {
		//生成固定model
		Element modelElement=new Element("Model");
		modelElement.setAttribute("name", "SIMUengine777");
		modelsElement.addContent(modelElement);
		
		Element publishElement=new Element("Publish");
		modelElement.addContent(publishElement);
		Element subscribeElement=new Element("Subscribe");
		modelElement.addContent(subscribeElement);
		this.generateStaticModelElement(publishElement, subscribeElement);
		
		this.generateUserPublishElement(interfaceList, subscribeElement);
		
		List<ActiveModel> activeModels = systemInfo.getActiveModels();
		this.generateUserModelElement(interfaceList, modelsElement, modelElement, publishElement, subscribeElement,
				activeModels);
	}
	//为1+n中的n添加订阅和发布的具体内容
	public void generateUserModelElement(List<Interface> interfaceList, Element modelsElement, Element modelElement,
			Element publishElement, Element subscribeElement, List<ActiveModel> activeModels) {
		for (int i = 0; i < activeModels.size(); i++) {
			ActiveModel activeModel = activeModels.get(i);
			logger.info("------->"+activeModel);
			String modelNameString = activeModel.getModelName();
			logger.info("------->"+modelNameString);
			Element modelElement2=new Element("Model");
			modelElement2.setAttribute("name",modelNameString);
			modelsElement.addContent(modelElement2);
			
			Element publishElement2=new Element("Publish");
			modelElement2.addContent(publishElement2);
			Element subscribeElement2=new Element("Subscribe");
			modelElement2.addContent(subscribeElement2);
			//添加预留接口
			this.generateUserModelElement(publishElement2,subscribeElement2);
			//添加用户接口
			this.generateUserPublishSubscribeElement(interfaceList, modelNameString, publishElement2, subscribeElement2);
		}
	}
	private void generateUserPublishSubscribeElement(List<Interface> interfaceList, String modelNameString,
			Element publishElement2, Element subscribeElement2) {
		for (int j = 0; j < interfaceList.size(); j++) {
			Interface interfaceObj = interfaceList.get(j);
			logger.info("----------->"+interfaceObj);
			String modelName = interfaceObj.getModelName();
			String name = interfaceObj.getName();
			String type = interfaceObj.getCategory();
			logger.info("<------------"+name);
			Element topicElement=new Element("Topic");
			topicElement.setAttribute("topicName", name);
			
			if (modelName.equals(modelNameString)) {
				if (type.equals("output")) {
					logger.info("=============添加了一个output");
					publishElement2.addContent(topicElement);
				}
				if (type.equals("input")) {
					logger.info("=============添加了一个input");
					subscribeElement2.addContent(topicElement);
				}
			}
			
		}
	}
	
}
