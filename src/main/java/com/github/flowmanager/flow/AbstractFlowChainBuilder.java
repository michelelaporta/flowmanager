package com.github.flowmanager.flow;


public abstract class AbstractFlowChainBuilder<T extends FlowSession,V extends FlowContext> extends FlowChainProcessorBuilder<T,V>{

	public abstract void build();
}
