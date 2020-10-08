package com.github.flowmanager.flow;

public abstract class AbstractFlowChainExecutor<T extends FlowSession,V extends FlowContext> implements FlowChainExecutor<T,V>{

	private FlowChainProcessor<T,V> processor;
	private T session;
	
	public AbstractFlowChainExecutor(T session,FlowChainProcessor<T,V> processor) {
		this.processor = processor;
		this.session = session;
	}

	@Override
	public Throwable call() {
		try {
			processor.start(session);
		} catch (Throwable e) {
			return e;
		}
		return null;
	}
	
	@Override
	public void close() {
		processor.close(session);
	}
	
	public void addFlow(FlowChain<T,V> handler) {
		processor.addFlow(handler);
	}
	
	public T getSession() {
		return session;
	}
	
	public FlowChainProcessor<T,V> getProcessor() {
		return processor;
	}

}
