package com.github.flowmanager.flow;

public class TestCallableFlowChainBuilder extends AbstractFlowChainBuilder<TestFlowSession,FlowContext>{

	@Override
	public void build() {
		MasterFlow testFlow = new MasterFlow();
		testFlow.registerSubFlow(new CallableTestFlow());
		flowChainExecutor.addFlow(testFlow);
	}
}
