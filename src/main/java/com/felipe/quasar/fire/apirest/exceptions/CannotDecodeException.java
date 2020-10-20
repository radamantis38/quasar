package com.felipe.quasar.fire.apirest.exceptions;

public class CannotDecodeException extends Exception { 
    /**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	public CannotDecodeException(String errorMessage) {
        super(errorMessage);
    }
}
