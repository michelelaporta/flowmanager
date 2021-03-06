package com.github.flowmanager.flow;


public abstract class AbstractFlowDirector<T extends FlowSession,V extends FlowContext> implements FlowDirector<T,V> {
	
	private FlowChainProcessorBuilder<T,V> flowChainBuilder;
	private long identifier;
	private  FlowManager<T,V> flowManager;
	
	
	public AbstractFlowDirector(long identifier,FlowChainProcessorBuilder<T,V> flowChainBuilder,FlowManager<T,V> flowManager){
		this.flowChainBuilder = flowChainBuilder;
		this.identifier = identifier;
		this.flowManager = flowManager;
	}
	
	@Override
	public void setFlowChainProcessorBuilder(FlowChainProcessorBuilder<T,V> flowChainBuilder) {
		this.flowChainBuilder = flowChainBuilder;
	}

	@Override
	public FlowChainExecutor<T,V> getFlowChainExecutor() {
        return flowChainBuilder.getFlowChainExecutor();
	}
	
	@Override
	public void constructFlow(T session,FlowChainExecutor<T,V> flowChainExecutor){
		flowChainBuilder.createNewFlowChainExecutor(session,flowChainExecutor);
		flowChainBuilder.build();
	}
	
	@Override
	public long getIdentifier() {
		return identifier;
	}
	
	@Override
	public FlowManager<T, V> getFlowManager() {
		return flowManager;
	}
}
