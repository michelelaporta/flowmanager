package com.github.flow;

import com.github.flowmanager.exception.SessionNotConnectedException;

public interface MessageSender<Body,Sess,Envelope> {

	public void sendMessage(Sess ioSession,Envelope message,MessageListener<Body,Sess> task) throws SessionNotConnectedException,Exception;
	
	//public void sendMessage(M message,String messageId,MessageListener<D,T> task) throws Exception;
	
//	void unregisterTasks(String messageNumber);
//	public boolean isBusy();
}
