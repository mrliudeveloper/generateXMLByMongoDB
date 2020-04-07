package com.github.hollykunge.security.simulation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.github.hollykunge.security.simulation.biz.ConfigBiz;
import com.github.hollykunge.security.simulation.entity.DataInfo;
import com.github.hollykunge.security.simulation.entity.FlowData;
import com.github.hollykunge.security.simulation.entity.Node;
import com.github.hollykunge.security.simulation.entity.SystemInfo;
import org.bson.Document;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoCollection;

@Controller
public class SystemController {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ConfigBiz configbiz;
	//mongodb的基本元素:数据库、集合、文档
	//类似于关系型数据库中的：数据库、表名、记录
	public String collectionName="systeminfo";
	
	//查询所有
	@ResponseBody
	@RequestMapping("/retrieveAll")
	public List<DataInfo> retrieveAllByMongo() {
		List<DataInfo> findAll = mongoTemplate.findAll(DataInfo.class, collectionName);
        logger.info(findAll.toString());
		return findAll;
	}
	/**
	 * 根据systemId更新mongodb文档
	 * @param systemId
	 * @param systemInfo
	 * @param systemName
	 * @param systemDescribe
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateByMongo")
	public Boolean updateByMongo(String systemId,String systemInfo,String systemName,String systemDescribe)
	{
		logger.info(systemId+":"+systemName+":"+systemDescribe+":"+systemInfo);
		SystemInfo system = JSONArray.parseObject(systemInfo,SystemInfo.class);
		logger.debug(system.toString());
		Query query=Query.query(Criteria.where("_id").is(systemId));
		Update update = Update.update("systemInfo", system);
		try {
			mongoTemplate.upsert(query, update, collectionName);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		
	}
	//根据Id查询响应到前端
	@ResponseBody
	@RequestMapping("/retrieveByMongo")
	public DataInfo retrieveByMongo(String systemId) throws IOException {
		//String id="098aef08";		
		Query query = Query.query(Criteria.where("_id").is(systemId));
        DataInfo dataInfo = null;
		try {
			dataInfo = mongoTemplate.findOne(query, DataInfo.class, collectionName);
			logger.info("--------->根据id查询:"+dataInfo.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn("--------->根据id没有查询到任何信息");
		}
		return dataInfo;	
	}
	/**
	 * 生成xml配置文件，目前是响应到前端
	 * 可以根据需求更改
	 * @param systemId
	 * @param modelName
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/downloadModelConfigByMongo",method = RequestMethod.POST)
	public void downloadModelConfigByMongo(String systemId,String modelName,String fileName,HttpServletResponse response) throws IOException
	{
		/*String id="098aef08";
		String modelName="insA";
		String fileName="";
		*/
		Query query = Query.query(Criteria.where("_id").is(systemId));
        DataInfo dataInfo = mongoTemplate.findOne(query, DataInfo.class, collectionName);
		logger.info("--------->根据id查询:"+dataInfo.toString());
		
		//创建根节点
		org.jdom2.Document rootDocument = null;
		try {
			SystemInfo systemInfo=dataInfo.getSystemInfo();
			rootDocument = new org.jdom2.Document();
			//生成xml文件
			configbiz.generateDocument(systemId, modelName, rootDocument, systemInfo);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage());
		}
        //XML格式化器
		XMLOutputter XMLOut = new XMLOutputter(configbiz.FormatXML());
        XMLOut.output(rootDocument,response.getOutputStream());
	}
	/**
	 * 初始化一个文档，返回值为文档id
	 * 当前为从UUID中截取的前八位，可以根据需求自定义id生成策略
	 */
	@ResponseBody
	@RequestMapping("/createByMongo")
	public String  createByMongo() {
		SystemInfo systemInfo = new SystemInfo();
		String systemId = UUID.randomUUID().toString().substring(0, 8);
		
		FlowData flowData=systemInfo.getFlowData();
		Node node = new Node();
		node.setShape("bus");
		node.setType("bus");
		node.setX(450);
		node.setY(210);
		node.setId("2efd998c");
		node.setIndex(0);
		flowData.getNodes().add(node);
		JSONObject json =(JSONObject)JSONObject.toJSON(systemInfo);
		//写入mongodb
		MongoCollection<Document> createCollection = mongoTemplate.getCollection(collectionName);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("_id", systemId);
		hashMap.put("systemInfo", json);
		Document document=new Document(hashMap);
		createCollection.insertOne(document);

		return systemId;
	}	
}
