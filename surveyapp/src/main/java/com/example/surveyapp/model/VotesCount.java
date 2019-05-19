package com.example.surveyapp.model;

import java.io.Serializable;

public class VotesCount implements Serializable{
    /**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	
	private String name;
    private Long value;
    
	public String getName() {
		return name;
	}
	public VotesCount() {}
	public void setName(String name) {
		this.name = name;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
	public VotesCount(String name, Long value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public VotesCount(Object a, Object b) {
		this.name = String.valueOf(a);
		
		String stringToConvert = String.valueOf(b);
		this.value = Long.parseLong(stringToConvert);
		System.out.println(this.name + " "+ this.value);
	}
	
}