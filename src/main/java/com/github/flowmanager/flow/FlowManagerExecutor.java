package com.github.flowmanager.flow;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlowManagerExecutor<T extends FlowSession,V extends FlowContext> implements Callable<Throwable>{
	
	public static final Integer FLOW_EXECUTION_TIMEOUT = 600;

	private static final Logger log = LogManager.getLogger(FlowManagerExecutor.class);

	private final FlowDirector<T, V> flowDirector;
	
	public FlowManagerExecutor(final FlowDirector<T, V> flowDirector) {
		this.flowDirector = flowDirector;
	}
	
	public void close(){
		flowDirector.getFlowChainExecutor().close();
	}
	
	@Override
	public Throwable call() {
		Throwable t = null;
		try {
			FlowChainExecutor<T, V> executor = flowDirector.getFlowChainExecutor();
			
			t = executor.call();
//			if(resultOk){
			if (log.isInfoEnabled())
				log.info("FLOW EXECUTION identifier " + flowDirector.getIdentifier());
//			}

		} catch (Throwable e) {
			t = e;
//			if (log.isDebugEnabled())
			log.error("FLOW EXECUTION has been interrupted message " + e.getMessage(),e);
			onException(e);
			
		}
		try {
			// close current executing flow
			flowDirector.getFlowChainExecutor().close();
		} catch (Throwable e) {
			t = e;
//			if (log.isDebugEnabled())
			log.error("FLOW EXECUTION CLOSE ERROR " + e.getMessage(),e);
			onException(e);
			
		}
		
		return t;
		
	}

	public FlowDirector<T, V> getFlowDirector() {
		return flowDirector;
	}
	
	protected void  onException(Throwable t){
		// do nothing subclasses will override it..
	}
	
 
}
