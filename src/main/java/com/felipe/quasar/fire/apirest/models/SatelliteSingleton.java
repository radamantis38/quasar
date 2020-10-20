package com.felipe.quasar.fire.apirest.models;

public final class SatelliteSingleton {
	private static SatelliteSingleton instance;
	
	private Satellite satellite1;
	private Satellite satellite2;
	private Satellite satellite3;

	private SatelliteSingleton() {
		
	}

	public static SatelliteSingleton getInstance() {
		if (instance == null) {
			instance = new SatelliteSingleton();
		}
		return instance;
	}

	public Satellite getSatellite1() {
		return satellite1;
	}

	public void setSatellite1(Satellite satellite1) {
		this.satellite1 = satellite1;
	}

	public Satellite getSatellite2() {
		return satellite2;
	}

	public void setSatellite2(Satellite satellite2) {
		this.satellite2 = satellite2;
	}

	public Satellite getSatellite3() {
		return satellite3;
	}

	public void setSatellite3(Satellite satellite3) {
		this.satellite3 = satellite3;
	}
	
}
