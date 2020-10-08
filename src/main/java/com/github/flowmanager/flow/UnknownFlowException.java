package com.github.flowmanager.flow;

public class UnknownFlowException extends Exception {
	
	private static final long serialVersionUID = 6789432;

	public UnknownFlowException() {
		super();
	}

	public UnknownFlowException(String msg) {
		super(msg);
	}

	public UnknownFlowException(Throwable cause) {
		super(cause);
	}

	public UnknownFlowException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
