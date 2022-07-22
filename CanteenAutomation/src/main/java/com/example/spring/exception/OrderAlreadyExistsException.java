package com.example.spring.exception;

public class OrderAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -2418773242468470956L;



	public OrderAlreadyExistsException() {
	}

	public OrderAlreadyExistsException(String message) {
		super(message);
	}
}



