package com.github.flowmanager.flow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CallableFlowChainTest {

	private ExecutorService executorService;
	private FlowDirector<TestFlowSession,FlowContext> director;
	private CallableFlowManager flowManager;
	
	@Before
	public void setup() {
		executorService = Executors.newFixedThreadPool(10);
		TestFlowSession session = new TestFlowSession();
		FlowContext flowContext = new FlowContext();

		flowManager = new CallableFlowManager(executorService,CallableFlowChainExecutor.class);

		director = new TestCallableFlowDirector(1L,flowManager);
		TestFlowChainExecutor<TestFlowSession,FlowContext> executor = new TestFlowChainExecutor<TestFlowSession,FlowContext>(session, flowContext);
		director.constructFlow(session,executor);

	}
	
	@Test
	public void testFlowChain() throws Exception {
		Future<?> future = executorService.submit(new FlowManagerExecutor<TestFlowSession,FlowContext>(director));
		Object result = future.get();
		System.out.println(result);
	}
	
	@After
	public void tearDown() {
		executorService.shutdown();
	}
}
