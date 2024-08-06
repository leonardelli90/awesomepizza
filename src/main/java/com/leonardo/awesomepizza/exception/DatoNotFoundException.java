package com.leonardo.awesomepizza.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DatoNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6119593455385732905L;

	public DatoNotFoundException(String message) {
        super(message);
    }

    public DatoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatoNotFoundException(Throwable cause) {
        super(cause);
    }
}