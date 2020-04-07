package com.github.hollykunge.security.simulation.entity;

public class Node {

	private String shape;
	
	private String type;
	
	private String size;
	
	private Integer x;
	
	private Integer y;
	
	private String id;
	
	private String label;
	
	private Integer index;

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Node [shape=" + shape + ", type=" + type + ", size=" + size + ", x=" + x + ", y=" + y + ", id=" + id
				+ ", label=" + label + ", index=" + index + "]";
	}
	
	
}
