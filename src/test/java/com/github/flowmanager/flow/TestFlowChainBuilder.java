package com.github.flowmanager.flow;

public class TestFlowChainBuilder extends AbstractFlowChainBuilder<TestFlowSession,FlowContext>{

	@Override
	public void build() {
		flowChainExecutor.addFlow(new TestFlow());
		flowChainExecutor.addFlow(new ExecuteFlow());
	}
}
