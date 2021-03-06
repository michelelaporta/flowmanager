package com.github.flowmanager.flow;

import com.github.flowmanager.exception.SessionNotConnectedException;
import com.github.flowmanager.exception.SessionTimeoutException;

public abstract class AbstractFlowChain<T extends FlowSession,V extends FlowContext> implements FlowChain<T,V> {

	protected FlowChain<T,V> chain;

	public void next(FlowChain<T,V> chain) {
		this.chain = chain;
	}

	@Override
	public boolean hasNext() {
		return chain != null;
	}
	
	@Override
	public FlowChain<T, V> getNext() {
		return chain;
	}
	
	public abstract boolean accept(V flowContext);

	public abstract void doHandle(T session,V flowContext)throws FlowExecutionException,SessionNotConnectedException,SessionTimeoutException;

	public void process(T session,V flowContext) throws FlowExecutionException{
//		System.out.println("AbstractFlowChain<T, V> process session " + session + " context " + flowContext);
		if (accept(flowContext)) {
			try{
//				System.out.println("doHandle("+this+")");
						
				doHandle(session,flowContext);
			}catch (FlowExecutionException e) {
				e.printStackTrace();
		
			}catch (SessionTimeoutException e) {
				e.printStackTrace();
				interrupt();
				
			}catch (SessionNotConnectedException e) {
				e.printStackTrace();
				interrupt();
				
			}catch (Throwable e) {
				e.printStackTrace();
				interrupt();
			}
		} /*else{
			System.out.println("not accepted ");
			
		}*/
		
		if (chain != null) {
			try{
				chain.process(session,flowContext);
			}catch (FlowExecutionException e) {
				e.printStackTrace();
				
			}catch (SessionNotConnectedException e) {
				e.printStackTrace();
				interrupt();
				
			}catch (Throwable e) {
				e.printStackTrace();
				interrupt();
			}
		}/*else{
			System.err.println("chain is null");
		}*/
	}
	
	public void interrupt(){
		next(null);
		chain = null;
	}

}
