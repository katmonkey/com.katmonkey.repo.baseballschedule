package com.katmonkey.baseballschedule.model;

public class Team {
	
	private String city;
	private String name;
	
	public Team(String city, String name) {
		this.city = city;
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
