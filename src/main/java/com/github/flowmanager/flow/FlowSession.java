package com.github.flowmanager.flow;

public interface FlowSession {

	
	public void close();
	public boolean isConnected();

	public long getIdentifier();
	
//	public abstract <T,R> R execute(String password, Object encryption,T request,Class<R> response);

}