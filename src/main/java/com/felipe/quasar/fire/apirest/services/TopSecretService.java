package com.felipe.quasar.fire.apirest.services;

import org.springframework.stereotype.Service;

import com.felipe.quasar.fire.apirest.exceptions.CannotDecodeException;
import com.felipe.quasar.fire.apirest.models.Position;
import com.felipe.quasar.fire.apirest.models.Satellite;
import com.felipe.quasar.fire.apirest.models.SatelliteSingleton;
import com.felipe.quasar.fire.apirest.models.TopSecretRequest;
import com.felipe.quasar.fire.apirest.models.TopSecretRequestResponse;
import com.felipe.quasar.fire.apirest.models.TopSecretSplitRequest;

/**
 * Implementation of top secret service
 * 
 * @author Felipe Parra
 *
 */
/**
 * @author merqueo
 *
 */
@Service
public class TopSecretService implements ITopSecretService {

	/**
	 * Handle top secret response
	 */
	public TopSecretRequestResponse HandleTopSecretRequest(TopSecretRequest topSecretRequest)
			throws CannotDecodeException {

		Position position = new Position();
		TopSecretRequestResponse response = new TopSecretRequestResponse();

		try {
			validateTopSecretRequest(topSecretRequest);
		} catch (Exception e) {
			throw e;

		}

		Satellite[] satellites = topSecretRequest.getSatellites();
		DecodeMessage decoder = new DecodeMessage(satellites[0].getDistance(), satellites[1].getDistance(),
				satellites[2].getDistance(), satellites[0].getMessage(), satellites[1].getMessage(),
				satellites[2].getMessage());
		decoder.getLocation();
		position.setX(decoder.getX());
		position.setY(decoder.getY());
		response.setMessage(decoder.getMessage());
		response.setPosition(position);

		return response;
	}

	/**
	 * Handel top secret split response
	 */
	public TopSecretRequestResponse HandleTopSecretSplitRequest(String value,
			TopSecretSplitRequest topSecretSplitRequest) throws CannotDecodeException {

		Position position = new Position();
		TopSecretRequestResponse response = new TopSecretRequestResponse();
		Satellite satelliteCurrent;

		SatelliteSingleton satelliteSingleton = SatelliteSingleton.getInstance();

		satelliteCurrent = new Satellite();
		satelliteCurrent.setDistance(topSecretSplitRequest.getDistance());
		satelliteCurrent.setMessage(topSecretSplitRequest.getMessage());
		satelliteCurrent.setName(value);
		
		switch (value) {
		case "kenobi":
			satelliteSingleton.setSatellite1(satelliteCurrent);
			break;
		case "skywalker":
			satelliteSingleton.setSatellite2(satelliteCurrent);
			break;
		case "sato":
			satelliteSingleton.setSatellite3(satelliteCurrent);
			break;
		}

		if (satelliteSingleton.getSatellite1() != null && satelliteSingleton.getSatellite2() != null
				&& satelliteSingleton.getSatellite3() != null) {
			DecodeMessage decoder = new DecodeMessage(satelliteSingleton.getSatellite1().getDistance(),
					satelliteSingleton.getSatellite2().getDistance(), satelliteSingleton.getSatellite3().getDistance(),
					satelliteSingleton.getSatellite1().getMessage(), satelliteSingleton.getSatellite2().getMessage(),
					satelliteSingleton.getSatellite3().getMessage());
			decoder.getLocation();
			position.setX(decoder.getX());
			position.setY(decoder.getY());
			response.setMessage(decoder.getMessage());
			response.setPosition(position);

			return response;
		} else {
			throw new CannotDecodeException("No hay datos suficiente");
		}

	}

	/**
	 * Validate satellites names
	 * 
	 * @param topSecretRequest
	 * @throws CannotDecodeException
	 */
	private void validateTopSecretRequest(TopSecretRequest topSecretRequest) throws CannotDecodeException {
		Satellite[] satellites = topSecretRequest.getSatellites();
		String[] expectedNames = { "kenobi", "skywalker", "sato" };

		for (int i = 0; i < expectedNames.length - 1; i++) {
			if (!expectedNames[i].equals(satellites[i].getName())) {
				throw new CannotDecodeException("No se puede codificar");
			}
		}

	}

}
