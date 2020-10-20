package com.felipe.quasar.fire.apirest.models;

import javax.validation.constraints.NotNull;

public class TopSecretSplitRequest {

	@NotNull
	private String[] message;
	@NotNull
	private double distance;

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
