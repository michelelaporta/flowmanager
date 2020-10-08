package com.github.flowmanager.flow;

import com.github.flowmanager.exception.SessionNotConnectedException;
import com.github.flowmanager.exception.SessionTimeoutException;

public abstract class AbstractCallableFlowChain<T extends FlowSession,V extends FlowContext> implements CallableFlowChain<T,V,Boolean> {

	protected T flowSession;
	protected V flowContext;
	
	@Override
	public void setFlowSession(T flowSession) {
		this.flowSession = flowSession;
	}
	
	@Override
	public void setFlowContext(V context) {
		this.flowContext = context;
	}
	
	@Override
	public Boolean call() throws Exception {
		try {
			doHandle();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public abstract boolean accept(V flowContext);

	public abstract void doHandle()throws FlowExecutionException,SessionNotConnectedException,SessionTimeoutException;


}
