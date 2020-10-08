package com.github.flowmanager.exception;

public class SessionNotConnectedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SessionNotConnectedException() {
		super();
	}
	
	public SessionNotConnectedException(String msg) {
		super(msg);
	}

	public SessionNotConnectedException(Throwable e) {
		super(e);
	}

}
