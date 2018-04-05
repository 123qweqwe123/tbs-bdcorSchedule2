package com.bdcor.pip.uqsToDw.domain;

public class Option {
	
	private String id;
	private String type;
	private String realType;
	private String style;
	private int numArrCount;
	private String format;
	private String name;
	private String tRemark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealType() {
		return realType;
	}
	public void setRealType(String realType) {
		this.realType = realType;
	}
	public String gettRemark() {
		return tRemark;
	}
	public void settRemark(String tRemark) {
		this.tRemark = tRemark;
	}
	public int getNumArrCount() {
		return numArrCount;
	}
	public void setNumArrCount(int numArrCount) {
		this.numArrCount = numArrCount;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}

}
