package com.github.flowmanager.flow;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FlowManager<T extends FlowSession,V extends FlowContext> extends Thread {

//	protected static final Logger log = Logger.getLogger(FlowManager.class);
	protected static Logger logger = LogManager.getLogger(FlowManager.class);

	private BlockingQueue<FlowDirector<T, V>> queue;
	private Boolean running;
	private ExecutorService executorService;
	private Class<? extends FlowManagerExecutor<T,V>> executor;
	private Map<Long, FlowManagerExecutor<T, V>> executors;

	public FlowManager(ExecutorService executorService,Class<? extends FlowManagerExecutor<T,V>> executor) {
		queue = new LinkedBlockingQueue<FlowDirector<T,V>>();
		setName(this.getClass().getSimpleName());
		this.executor = executor;
		this.executorService = executorService;
		this.executors = new HashMap<Long, FlowManagerExecutor<T, V>>();
		running = true;
		start();
	}

	public void add(FlowDirector<T, V> request) {
		queue.add(request);
	}

	public void run() {
		while (running){
			try {
				FlowDirector<T, V> director =  queue.take();
				FlowManagerExecutor<T,V> flowManagerExecutor =getDeclaredFlowManagerExecutor(director);
				executors.put(director.getIdentifier(), flowManagerExecutor);
				/*Future<Throwable> future = */executorService.submit(flowManagerExecutor);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private FlowManagerExecutor<T,V> getDeclaredFlowManagerExecutor(FlowDirector<T, V> director)throws Exception{
		try {
			Class<?>[] intArgsClass = new Class[] { FlowDirector.class};
			Constructor<? extends FlowManagerExecutor<T, V>> constructor = executor.getDeclaredConstructor(intArgsClass);
			constructor.setAccessible(true);
			Object[] intArgs = new Object[] { director };
			FlowManagerExecutor<T, V> flowChainExecutor = constructor.newInstance(intArgs);
			return flowChainExecutor;
		}catch (Exception e) {
			throw e;
		}
	}
	
	public Map<Long, FlowManagerExecutor<T, V>> getExecutors() {
		return executors;
	}
	
	public FlowManagerExecutor<T,V> getExecutor(Long identifier){
		if(executors.containsKey(identifier)){
			return executors.get(identifier);
		}
		return null;
	}
	
	public void setRunning(Boolean running) {
		this.running = running;
	}

	public BlockingQueue<FlowDirector<T, V>> getQueue() {
		return queue;
	}
	
}