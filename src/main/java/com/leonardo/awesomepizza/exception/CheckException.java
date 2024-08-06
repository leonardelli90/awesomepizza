package com.leonardo.awesomepizza.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CheckException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5811299406550586266L;

	public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }
    
}
