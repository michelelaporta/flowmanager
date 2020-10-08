package com.github.flowmanager.flow;

import com.github.flowmanager.exception.SessionNotConnectedException;

public class TestFlowChainProcessor<T extends FlowSession,V extends FlowContext> implements FlowChainProcessor<T,V>{

	private FlowChain<T,V> first;
	private FlowChain<T,V> previous;
	private V flowContext;

	public TestFlowChainProcessor(V flowContext){
		if(flowContext == null)
			throw new IllegalStateException("flowContext cannot be null");
		this.flowContext = flowContext;
	}
	
	public void addFlow(FlowChain<T,V> handler) {
		System.out.println("addFlow " + handler);
		if (previous != null) {
			previous.next(handler);
		}else
			first = handler;
		previous = handler;
	}

	public void start(T session) {
		if(first != null){
			try {
				first.process(session,flowContext);
			}catch (FlowExecutionException e) {
				e.printStackTrace();
			}catch (SessionNotConnectedException e) {
				e.printStackTrace();
				// break flow chain
				interrupt();
			}catch (Throwable e) {
				e.printStackTrace();
				// break flow chain
				interrupt();
			}
		}
	}	
	
	@Override
	public void close(T session) {

		if(session != null){
			if(first != null && first.hasNext()){
				first.next(null);
			}else{
				if(previous != null && previous.hasNext())
					previous.next(null);
			}
			interrupt();
			session.close();
		}
	}	
	
	private void interrupt(){
		if(first != null)
			first.next(null);
		if(previous != null)
			previous.next(null);
	}
	
	@Override
	public V getFlowContext() {
		return flowContext;
	}
}
