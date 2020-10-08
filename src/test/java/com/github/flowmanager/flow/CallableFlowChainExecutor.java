package com.github.flowmanager.flow;

public class CallableFlowChainExecutor extends FlowManagerExecutor<TestFlowSession,FlowContext> {

	public CallableFlowChainExecutor(FlowDirector<TestFlowSession,FlowContext> flowDirector) {
		super(flowDirector);
	}
	
	@Override
	protected void onException(Throwable t) {
		t.printStackTrace();
	}

}
