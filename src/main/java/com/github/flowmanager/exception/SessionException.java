package com.github.flowmanager.exception;

public class SessionException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SessionException() {
		super();
	}
	
	public SessionException(String msg) {
		super(msg);
	}

	public SessionException(Throwable e) {
		super(e);
	}

}
