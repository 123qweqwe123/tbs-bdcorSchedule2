package com.bdcor.peace3.mobileSendRule.obj;

public class Patient {

	public Patient(String id,String name,String group,String isSmoking,String groupDate,String birthday){
		this.id = id;
		this.name = name;
		this.group = group;
		this.isSmoking = isSmoking;
		this.groupDate = groupDate;
		this.birthday = birthday;
	}
	
	public String id;
	public String name;
	public String group;
	public String isSmoking;
	public String groupDate;
	public String birthday;
	
	public String toString(){
		return name
				+"###"+id
				+"###"+group
				+"###"+isSmoking
				+"###"+groupDate
				+"###"+birthday;
	}
	
}
