package com.github.flowmanager.flow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.flowmanager.exception.SessionNotConnectedException;
import com.github.flowmanager.exception.SessionTimeoutException;
import com.github.flowmanager.util.StopWatch;

public class CallableTestFlow extends AbstractCallableFlowChain<TestFlowSession,FlowContext>{

	private static final Logger log = LogManager.getLogger(CallableTestFlow.class);

	@Override
	public void doHandle()throws FlowExecutionException,SessionNotConnectedException,SessionTimeoutException {
		try {
			StopWatch stopWatch = null;
			if(log.isDebugEnabled()){
				stopWatch = new StopWatch();
				stopWatch.start(this.getClass().getName());
			}	
			
			log.info("doHandle " + this);
			
			if(log.isDebugEnabled()){
				stopWatch.stop();
				log.debug(stopWatch.prettyPrint());
			}
			
		} catch (SessionNotConnectedException e) {
			log.error(e.getMessage(), e);
			throw e;
			
		} catch (SessionTimeoutException e) {
			log.error(e.getMessage(), e);
			throw e;
			
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
			throw new FlowExecutionException(e);
		}
	}
	
	@Override
	public boolean accept(FlowContext flowContext) {
		return true;
	}

	
}
