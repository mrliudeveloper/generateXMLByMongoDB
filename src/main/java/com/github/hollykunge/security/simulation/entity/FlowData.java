package com.github.hollykunge.security.simulation.entity;

import java.util.ArrayList;
import java.util.List;

public class FlowData {

	private List<Node>nodes=new ArrayList<Node>();
	
	private List<Edge>edges=new ArrayList<Edge>();

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	@Override
	public String toString() {
		return "FlowData [nodes=" + nodes + ", edges=" + edges + "]";
	}
	
	
}
