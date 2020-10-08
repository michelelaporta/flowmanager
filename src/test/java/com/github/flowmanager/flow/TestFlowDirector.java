package com.github.flowmanager.flow;

public class TestFlowDirector extends AbstractFlowDirector<TestFlowSession,FlowContext> {
	
	public TestFlowDirector(long identifier,FlowManager<TestFlowSession, FlowContext> flowManager) {
		super(identifier,new TestFlowChainBuilder(),flowManager);
	}

}
