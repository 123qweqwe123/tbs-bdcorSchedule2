package com.bdcor.pip.cp3UqsTodw.domain;

import java.util.List;

public class QuestionSet {
	
	private String id;
	private List<Question> questions;
	private int cycle;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	
}
