package com.github.flowmanager.flow;

import java.util.concurrent.ExecutorService;

public class CallableFlowManager extends FlowManager<TestFlowSession,FlowContext> {

	public CallableFlowManager(ExecutorService executorService,Class<CallableFlowChainExecutor> executor) {
		super(executorService, executor);
	}
}
