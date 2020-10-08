package com.github.flowmanager.flow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.flowmanager.exception.SessionNotConnectedException;
import com.github.flowmanager.exception.SessionTimeoutException;
import com.github.flowmanager.util.StopWatch;

public class ExecuteFlow extends AbstractFlowChain<TestFlowSession,FlowContext>{

	private static final Logger log = LogManager.getLogger(ExecuteFlow.class);

	@Override
	public void doHandle(TestFlowSession session,FlowContext flowContext)throws FlowExecutionException,SessionNotConnectedException,SessionTimeoutException {
		try {
			System.out.println("ExecuteFlow");

			StopWatch stopWatch = null;
			if(log.isDebugEnabled()){
				stopWatch = new StopWatch();
				stopWatch.start(this.getClass().getName());
			}	
			
//			System.out.println("res "+ session.execute("password", null, new ExecuteRequest(),ExecuteResponse.class));
			
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
	
//	public class ExecuteRequest{
//		
//		public ExecuteRequest(){
//			
//		}
//	}
//
//	public class ExecuteResponse{
//		
//		public ExecuteResponse(){
//			
//		}
//	}

}
