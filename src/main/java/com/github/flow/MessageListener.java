package com.github.flow;

public interface MessageListener<T,S> {

	public void onMessage(T message,S ioSession);
}
