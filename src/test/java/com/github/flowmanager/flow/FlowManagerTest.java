package com.github.flowmanager.flow;

import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;


public class FlowManagerTest {

	private java.util.concurrent.ExecutorService executorService;
	private FlowDirector<TestFlowSession,FlowContext> director;
	private CallableFlowManager flowManager;
	
	@Before
	public void setup() {
		executorService = Executors.newCachedThreadPool();
		
		TestFlowSession session = new TestFlowSession();
		FlowContext flowContext = new FlowContext();
		
		flowManager = new CallableFlowManager(executorService, CallableFlowChainExecutor.class);

		director = new TestFlowDirector(1L,flowManager);
		TestFlowChainExecutor<TestFlowSession,FlowContext> executor = new TestFlowChainExecutor<TestFlowSession,FlowContext>(session, flowContext);
		director.constructFlow(session,executor);
	}
	
	@Test
	public void testFlowChain() throws Exception {
		try {
			flowManager.add(director);
			Thread.sleep(100);
			FlowManagerExecutor<TestFlowSession,FlowContext> executor = flowManager.getExecutor(1L);
			System.out.println("executor " + executor);
			executor.close();
			Thread.sleep(30000);
		} catch (Exception e) {
			e.printStackTrace();
			TestCase.fail(e.getMessage());
		}
		//executorService.submit(new FlowManagerExecutor<TestGdmSession,FlowContext>(director));
	}
	
	@After
	public void tearDown() {
		flowManager.setRunning(false);
		//executorService.shutdown();
	}
}
