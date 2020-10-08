package com.github.flowmanager.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.flowmanager.exception.SessionNotConnectedException;
import com.github.flowmanager.exception.SessionTimeoutException;
import com.github.flowmanager.util.StopWatch;

public class MasterFlow extends AbstractFlowChain<TestFlowSession,FlowContext>{

	private static final Logger log = LogManager.getLogger(MasterFlow.class);
	
	private List<CallableFlowChain<TestFlowSession, FlowContext,Boolean>> subflows;
	private ExecutorService executorService;

	public MasterFlow(){
		subflows = new ArrayList<CallableFlowChain<TestFlowSession, FlowContext,Boolean>>();
		executorService = Executors.newSingleThreadExecutor();
	}

	public MasterFlow(List<CallableFlowChain<TestFlowSession, FlowContext,Boolean>> subflows){
		this.subflows = subflows; 
	}
	
	public void registerSubFlow(CallableFlowChain<TestFlowSession, FlowContext,Boolean> subflow) {
		this.subflows.add(subflow);
	}
	
	
	@Override
	public void doHandle(TestFlowSession session,FlowContext flowContext)throws FlowExecutionException,SessionNotConnectedException,SessionTimeoutException {
		try {
			StopWatch stopWatch = null;
			if(log.isDebugEnabled()){
				stopWatch = new StopWatch();
				stopWatch.start(this.getClass().getName());
			}	
			
			for(CallableFlowChain<TestFlowSession, FlowContext,Boolean> subFlow : subflows){
				try {
					subFlow.setFlowContext(flowContext);
					subFlow.setFlowSession(session);
					
					Future<Boolean> future = executorService.submit(subFlow);
					if(log.isDebugEnabled()){
						log.debug("submitted subflow [" + subFlow.getClass().getSimpleName() +"]");
					}

					boolean res = future.get(5, TimeUnit.SECONDS);
					if(log.isDebugEnabled()){
						log.debug("subflow["+subFlow.getClass().getSimpleName()+"] result " + res);
					}

					
				} catch (SessionNotConnectedException e) {
					log.error(e.getMessage(), e);
					throw e;
					
				} catch (SessionTimeoutException e) {
					log.error(e.getMessage(), e);
					throw e;
					
				} catch (Throwable e) {
					throw new FlowExecutionException(e);
				}
			}
		
			
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
