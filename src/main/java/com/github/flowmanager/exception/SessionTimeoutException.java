package com.github.flowmanager.exception;

public class SessionTimeoutException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SessionTimeoutException() {
		super();
	}
	
	public SessionTimeoutException(String msg) {
		super(msg);
	}

	public SessionTimeoutException(Throwable e) {
		super(e);
	}

}
