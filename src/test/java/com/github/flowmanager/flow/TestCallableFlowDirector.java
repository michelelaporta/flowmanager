package com.github.flowmanager.flow;

public class TestCallableFlowDirector extends AbstractFlowDirector<TestFlowSession,FlowContext> {
	
	public TestCallableFlowDirector(long identifier,FlowManager<TestFlowSession,FlowContext> flowManager) {
		super(identifier,new TestCallableFlowChainBuilder(),flowManager);
	}

}
