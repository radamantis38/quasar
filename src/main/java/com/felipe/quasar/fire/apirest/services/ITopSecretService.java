package com.felipe.quasar.fire.apirest.services;

import com.felipe.quasar.fire.apirest.exceptions.CannotDecodeException;
import com.felipe.quasar.fire.apirest.models.TopSecretRequest;
import com.felipe.quasar.fire.apirest.models.TopSecretRequestResponse;
import com.felipe.quasar.fire.apirest.models.TopSecretSplitRequest;

/**
 * Interface of top secret service
 * 
 * @author Felipe Parra
 *
 */
public interface ITopSecretService {

	/**
	 * Handle TopSecret request
	 * 
	 * @param topSecretRequest
	 * @return
	 * @throws CannotDecodeException
	 */
	public TopSecretRequestResponse HandleTopSecretRequest(TopSecretRequest topSecretRequest)
			throws CannotDecodeException;

	public TopSecretRequestResponse HandleTopSecretSplitRequest(String value,
			TopSecretSplitRequest topSecretSplitRequest) throws CannotDecodeException;
}
