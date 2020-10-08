package com.github.flowmanager.flow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestFlowSession implements FlowSession{

	private static final Logger log = LogManager.getLogger(TestFlowSession.class);

	@Override
	public void close() {
		log.info("close() CALLED");
	}

	@Override
	public boolean isConnected() {
		return true;
	}

	public <T, R> R execute(String password, Object encryption, T request, Class<R> response)  {
		try {
			System.out.println("response.getName() " + response.getName());
			return Class.forName(response.getName()).asSubclass(response).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	};
	


	@Override
	public long getIdentifier() {
		return 1;
	}
}
