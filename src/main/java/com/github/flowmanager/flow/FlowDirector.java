package com.github.flowmanager.flow;


public interface FlowDirector<T extends FlowSession,V extends FlowContext>{

	public long getIdentifier();
	public void setFlowChainProcessorBuilder(FlowChainProcessorBuilder<T,V> flowChainBuilder);
	public FlowChainExecutor<T,V> getFlowChainExecutor();
	public FlowManager<T,V> getFlowManager();
	public void constructFlow(T session,FlowChainExecutor<T,V> flowChainExecutor);

}