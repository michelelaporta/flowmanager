package com.github.flowmanager.flow;

import com.github.flowmanager.exception.SessionNotConnectedException;
import com.github.flowmanager.exception.SessionTimeoutException;


public interface FlowChain<T extends FlowSession,V extends FlowContext> {

	void next(FlowChain<T,V> chain);
	boolean hasNext();
	FlowChain<T,V> getNext();
    void process(T session,V flowContext)throws FlowExecutionException;
    boolean accept(V flowContext);
    void doHandle(T session,V flowContext)throws FlowExecutionException,SessionNotConnectedException,SessionTimeoutException;
    void interrupt();
    
}
