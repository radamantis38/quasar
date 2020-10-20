package com.felipe.quasar.fire.apirest.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopSecretRequest {

	@NotNull
	@Size(min=3, max=3)
	private Satellite[] satellites;

	public Satellite[] getSatellites() {
		return satellites;
	}

	public void setSatellites(Satellite[] satellites) {
		this.satellites = satellites;
	}

}
