package com.github.flowmanager.flow;

public class TestFlowChainExecutor<T extends FlowSession,V extends FlowContext> extends AbstractFlowChainExecutor<T,V>{

	public TestFlowChainExecutor(T session,V flowContext) {
		super(session, new TestFlowChainProcessor<T,V>(flowContext));
	}

}
