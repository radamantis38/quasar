package com.felipe.quasar.fire.apirest.models;

import javax.validation.constraints.NotNull;


public class Satellite {
	
	@NotNull
	private String name;
	@NotNull
	private double distance;
	@NotNull
	private String[] message;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String[] getMessage() {
		return message;
	}
	public void setMessage(String[] message) {
		this.message = message;
	}
	
}
