package com.bdcor.pip.uqsToDw.domain;

import java.util.List;

public class QuestionNaire {
	
	private String projectId;
	private String id;
	private List<QuestionSet> sets;
	
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<QuestionSet> getSets() {
		return sets;
	}
	public void setSets(List<QuestionSet> sets) {
		this.sets = sets;
	}
	
}
