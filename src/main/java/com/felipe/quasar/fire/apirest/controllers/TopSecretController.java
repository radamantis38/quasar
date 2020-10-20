package com.felipe.quasar.fire.apirest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.quasar.fire.apirest.exceptions.CannotDecodeException;
import com.felipe.quasar.fire.apirest.models.TopSecretRequest;
import com.felipe.quasar.fire.apirest.models.TopSecretRequestResponse;
import com.felipe.quasar.fire.apirest.models.TopSecretSplitRequest;
import com.felipe.quasar.fire.apirest.services.ITopSecretService;

/**
 * Controller of TopSecret
 * 
 * @author Felipe Parra
 *
 */
@RestController
public class TopSecretController {

	@Autowired
	private ITopSecretService topSecretService;

	@PostMapping("/topsecret/")
	public ResponseEntity<TopSecretRequestResponse> topSecret(@Valid @RequestBody TopSecretRequest topsecretrequest) {
		try {
			return new ResponseEntity<>(topSecretService.HandleTopSecretRequest(topsecretrequest), HttpStatus.OK);
		} catch (CannotDecodeException e) {
			return new ResponseEntity("No se puede decodificar", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/topsecret_split/{name}")
	public ResponseEntity<TopSecretRequestResponse> topSecretSplit(@PathVariable String name,
			@Valid @RequestBody TopSecretSplitRequest topSecretSplitRequest) {

		if (!name.equals("kenobi") && !name.equals("skywalker") && !name.equals("sato")) {
			return new ResponseEntity("La ruta no existe", HttpStatus.NOT_FOUND);
		}

		try {
			return new ResponseEntity<>(topSecretService.HandleTopSecretSplitRequest(name, topSecretSplitRequest),
					HttpStatus.OK);
		} catch (CannotDecodeException e) {
			return new ResponseEntity("no hay suficiente información", HttpStatus.NOT_FOUND);
		}

	}

}
