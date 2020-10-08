package com.github.flowmanager.flow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FlowChainTest {

	private ExecutorService executorService;
	private FlowDirector<TestFlowSession,FlowContext> director;
	
	@Before
	public void setup() {
		executorService = Executors.newFixedThreadPool(10);
		
		TestFlowSession session = new TestFlowSession();
		FlowContext flowContext = new FlowContext();
		
		director = new TestFlowDirector(1L,null);
		TestFlowChainExecutor<TestFlowSession,FlowContext> executor = new TestFlowChainExecutor<TestFlowSession,FlowContext>(session, flowContext);
		director.constructFlow(session,executor);

	}
	
	@Test
	public void testFlowChain() throws Exception {
		executorService.submit(new FlowManagerExecutor<TestFlowSession,FlowContext>(director));
	}
	
	@After
	public void tearDown() {
		executorService.shutdown();
	}
}
