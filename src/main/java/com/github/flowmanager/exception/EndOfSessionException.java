package com.github.flowmanager.exception;

public class EndOfSessionException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EndOfSessionException() {
		super();
	}
	
	public EndOfSessionException(String msg) {
		super(msg);
	}

	public EndOfSessionException(Throwable e) {
		super(e);
	}

}
