package com.github.flowmanager.flow;

import java.util.concurrent.Callable;


public interface FlowChainExecutor<T extends FlowSession,V extends FlowContext> extends Callable<Throwable>{

	public void addFlow(FlowChain<T,V> flow);
	public void close();
	public FlowChainProcessor<T,V> getProcessor();
	public T getSession();
	
	

}
