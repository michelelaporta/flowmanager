package com.github.flowmanager.flow;


public interface FlowChainProcessor<T extends FlowSession,V extends FlowContext> {

	public void addFlow(FlowChain<T,V> flow);

	public void start(T ctrSession);

	public void close(T ctrSession);
	
	public V getFlowContext();

}
