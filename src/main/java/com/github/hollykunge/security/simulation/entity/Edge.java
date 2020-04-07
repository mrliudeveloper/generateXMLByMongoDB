package com.github.hollykunge.security.simulation.entity;

public class Edge {
	
	private String shape;
	
	private String source;
	
	private String sourceAnchor;
	
	private String target;
	
	private String targetAnchor;
	
	private String id;
	
	private String index;

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceAnchor() {
		return sourceAnchor;
	}

	public void setSourceAnchor(String sourceAnchor) {
		this.sourceAnchor = sourceAnchor;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTargetAnchor() {
		return targetAnchor;
	}

	public void setTargetAnchor(String targetAnchor) {
		this.targetAnchor = targetAnchor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Edge [shape=" + shape + ", source=" + source + ", sourceAnchor=" + sourceAnchor + ", target=" + target
				+ ", targetAnchor=" + targetAnchor + ", id=" + id + ", index=" + index + "]";
	}
	

}
