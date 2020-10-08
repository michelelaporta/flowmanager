package com.github.flowmanager.flow;

import java.util.concurrent.Callable;

import com.github.flowmanager.exception.SessionNotConnectedException;
import com.github.flowmanager.exception.SessionTimeoutException;


public interface CallableFlowChain<T extends FlowSession,V extends FlowContext,Z> extends Callable<Z> {

//    void process(T session,V flowContext)throws FlowExecutionException;
    boolean accept(V flowContext);
    void doHandle()throws FlowExecutionException,SessionNotConnectedException,SessionTimeoutException;
    void setFlowSession(T session);
    void setFlowContext(V context);
    
}
