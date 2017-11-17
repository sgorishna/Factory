package com.spec.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class InvalidDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidDataException(String message) {
		super(message);
	}

	public InvalidDataException(Throwable cause) {
		super(cause);
	}

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
