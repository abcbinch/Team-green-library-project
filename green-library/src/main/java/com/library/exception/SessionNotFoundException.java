package com.library.exception;

public class SessionNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
    public SessionNotFoundException(String message) {
        super(message);
    }
}
