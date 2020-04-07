package com.github.hollykunge.security.simulation.entity;

import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

public class TestDemo {
	private static String activeModels;
	private static  String dataType;
	private static  String fileName;
	private static  String id;
	private static  String  interfaceList;
	private static String modelName;
	private static String data;
	public static void main(String[] args) {
		initData();
		
		
		/*List<ActiveDataType> parseArray = JSONObject.parseArray(dataType, ActiveDataType.class);
		System.out.println(parseArray);*/
		
		/*List<Interface> interfaces = JSONObject.parseArray(interfaceList, Interface.class);
		System.out.println(interfaces.get(0).getModelName());
		*/
		
		/*
		FlowData flowData = new FlowData();
		JSONObject jsonObj = JSONObject.parseObject(data);
		JSONArray nodesArray =(JSONArray)jsonObj.get("nodes");
		List<Node> nodes= JSONObject.parseArray(nodesArray.toJSONString(),Node.class);
		
		flowData.setNodes(nodes);
		JSONArray edgesArray = (JSONArray)jsonObj.get("edges");
		List<Edge> edges = JSONObject.parseArray(edgesArray.toJSONString(), Edge.class);
		flowData.setEdges(edges);
		
		System.out.println(flowData);
		*/
		
		List<ActiveModel> parseArray = JSONObject.parseArray(activeModels, ActiveModel.class);
		
		System.out.println(parseArray);
	}
	private static  void initData() {
		dataType ="[{\r\n" + 
				"	\"key\": 1,\r\n" + 
				"	\"name\": \"UDPosition\",\r\n" + 
				"	\"description\": \"user-defined\",\r\n" + 
				"	\"currentCount\": 7,\r\n" + 
				"	\"content\": [{\r\n" + 
				"		\"key\": 0,\r\n" + 
				"		\"type\": \"double\",\r\n" + 
				"		\"name\": \"longitude\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 1,\r\n" + 
				"		\"type\": \"double\",\r\n" + 
				"		\"name\": \"latitude\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 2,\r\n" + 
				"		\"type\": \"double\",\r\n" + 
				"		\"name\": \"altitude\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 4,\r\n" + 
				"		\"type\": \"double\",\r\n" + 
				"		\"name\": \"x\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 5,\r\n" + 
				"		\"type\": \"double\",\r\n" + 
				"		\"name\": \"y\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 6,\r\n" + 
				"		\"type\": \"double\",\r\n" + 
				"		\"name\": \"z\"\r\n" + 
				"	}]\r\n" + 
				"},\r\n" + 
				"{\r\n" + 
				"	\"key\": 2,\r\n" + 
				"	\"name\": \"UDPosture\",\r\n" + 
				"	\"description\": \"user-defined\",\r\n" + 
				"	\"currentCount\": 6,\r\n" + 
				"	\"content\": [{\r\n" + 
				"		\"key\": 0,\r\n" + 
				"		\"type\": \"int32_t\",\r\n" + 
				"		\"name\": \"vx\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 1,\r\n" + 
				"		\"type\": \"int32_t\",\r\n" + 
				"		\"name\": \"vy\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 2,\r\n" + 
				"		\"type\": \"int32_t\",\r\n" + 
				"		\"name\": \"vz\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 3,\r\n" + 
				"		\"type\": \"int32_t\",\r\n" + 
				"		\"name\": \"phi\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 4,\r\n" + 
				"		\"type\": \"int32_t\",\r\n" + 
				"		\"name\": \"psi\"\r\n" + 
				"	},\r\n" + 
				"	{\r\n" + 
				"		\"key\": 5,\r\n" + 
				"		\"type\": \"int32_t\",\r\n" + 
				"		\"name\": \"gamma\"\r\n" + 
				"	}]\r\n" + 
				"}]";
        fileName = "1.xml";
        id = UUID.randomUUID().toString().substring(0, 8);
       interfaceList = "[{\r\n" + 
        		"	\"key\": \"topic_001\",\r\n" + 
        		"	\"type\": \"double\",\r\n" + 
        		"	\"name\": \"topic_001\",\r\n" + 
        		"	\"description\": \"this is a normal output\",\r\n" + 
        		"	\"modelName\": \"insA\",\r\n" + 
        		"	\"category\": \"output\"\r\n" + 
        		"},\r\n" + 
        		"{\r\n" + 
        		"	\"key\": \"topic_002\",\r\n" + 
        		"	\"type\": \"UDPosition\",\r\n" + 
        		"	\"name\": \"topic_002\",\r\n" + 
        		"	\"description\": \"this is a normal output\",\r\n" + 
        		"	\"modelName\": \"insB\",\r\n" + 
        		"	\"category\": \"output\"\r\n" + 
        		"},\r\n" + 
        		"{\r\n" + 
        		"	\"key\": \"topic_003\",\r\n" + 
        		"	\"type\": \"UDPosture\",\r\n" + 
        		"	\"name\": \"topic_003\",\r\n" + 
        		"	\"description\": \"this is a normal output\",\r\n" + 
        		"	\"modelName\": \"insC\",\r\n" + 
        		"	\"category\": \"output\"\r\n" + 
        		"},\r\n" + 
        		"{\r\n" + 
        		"	\"key\": \"topic_002\",\r\n" + 
        		"	\"type\": \"UDPosition\",\r\n" + 
        		"	\"name\": \"topic_002\",\r\n" + 
        		"	\"description\": \"this is a normal input\",\r\n" + 
        		"	\"modelName\": \"insA\",\r\n" + 
        		"	\"category\": \"input\"\r\n" + 
        		"},\r\n" + 
        		"{\r\n" + 
        		"	\"key\": \"topic_003\",\r\n" + 
        		"	\"type\": \"UDPosture\",\r\n" + 
        		"	\"name\": \"topic_003\",\r\n" + 
        		"	\"description\": \"this is a normal input\",\r\n" + 
        		"	\"modelName\": \"insA\",\r\n" + 
        		"	\"category\": \"input\"\r\n" + 
        		"},\r\n" + 
        		"{\r\n" + 
        		"	\"key\": \"topic_003\",\r\n" + 
        		"	\"type\": \"UDPosture\",\r\n" + 
        		"	\"name\": \"topic_003\",\r\n" + 
        		"	\"description\": \"this is a normal input\",\r\n" + 
        		"	\"modelName\": \"insB\",\r\n" + 
        		"	\"category\": \"input\"\r\n" + 
        		"},\r\n" + 
        		"{\r\n" + 
        		"	\"key\": \"topic_001\",\r\n" + 
        		"	\"type\": \"double\",\r\n" + 
        		"	\"name\": \"topic_001\",\r\n" + 
        		"	\"description\": \"this is a normal input\",\r\n" + 
        		"	\"modelName\": \"insC\",\r\n" + 
        		"	\"category\": \"input\"\r\n" + 
        		"},\r\n" + 
        		"{\r\n" + 
        		"	\"key\": \"topic_002\",\r\n" + 
        		"	\"type\": \"UDPosition\",\r\n" + 
        		"	\"name\": \"topic_002\",\r\n" + 
        		"	\"description\": \"this is a normal input\",\r\n" + 
        		"	\"modelName\": \"insC\",\r\n" + 
        		"	\"category\": \"input\"\r\n" + 
        		"}]";
        modelName = "insA";
        data="{\"nodes\":[{\"shape\":\"matlab\",\"type\":\"node\",\"size\":\"80*80\",\"x\":124,\"y\":459,\"id\":\"df64a02c\",\"label\":\"insA\",\"index\":0},{\"shape\":\"c\",\"type\":\"node\",\"size\":\"80*80\",\"x\":310,\"y\":455,\"id\":\"03e70b1b\",\"label\":\"insB\",\"index\":1},{\"shape\":\"c\",\"type\":\"node\",\"size\":\"80*80\",\"x\":470,\"y\":459,\"id\":\"a8ade87a\",\"label\":\"insC\",\"index\":2},{\"shape\":\"bus\",\"type\":\"bus\",\"x\":309.5,\"y\":310,\"id\":\"2efd998c\",\"index\":3}],"
        		+ "\"edges\":[{\"shape\":\"line\",\"source\":\"df64a02c\",\"sourceAnchor\":0,\"target\":\"2efd998c\",\"targetAnchor\":1,\"id\":\"a483132b\",\"index\":4},{\"shape\":\"line\",\"source\":\"03e70b1b\",\"sourceAnchor\":0,\"target\":\"2efd998c\",\"targetAnchor\":4,\"id\":\"afa89dd6\",\"index\":5},{\"shape\":\"line\",\"source\":\"a8ade87a\",\"sourceAnchor\":0,\"target\":\"2efd998c\",\"targetAnchor\":8,\"id\":\"2064ffc4\",\"index\":6}]}";
        activeModels="[{\"modelName\":\"insA\",\"chargers\":[\"130224199710301012\"]},{\"modelName\":\"insB\",\"chargers\":[\"232101199505060023\",\"888888888808088888\"]},{\"modelName\":\"insC\",\"chargers\":[\"777777777707077777\",\"666666666606066666\"]}]";
	}
}
