package com.github.flowmanager.flow;


public abstract class FlowChainProcessorBuilder<T extends FlowSession,V extends FlowContext> {

	protected FlowChainExecutor<T,V> flowChainExecutor;
		
	public FlowChainExecutor<T,V> getFlowChainExecutor() {
			return flowChainExecutor;
	}
	
	public void createNewFlowChainExecutor(T flowSession,FlowChainExecutor<T,V> flowChainExecutor) {
		this.flowChainExecutor = flowChainExecutor;
	}
	
	public abstract void build();
	
}
