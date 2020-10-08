package com.github.flowmanager.flow;

public class FlowExecutionException extends Exception {
	
	private static final long serialVersionUID = 6789432;

	public FlowExecutionException() {
		super();
	}

	public FlowExecutionException(String msg) {
		super(msg);
	}

	public FlowExecutionException(Throwable cause) {
		super(cause);
	}

	public FlowExecutionException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
