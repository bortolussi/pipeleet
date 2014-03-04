package com.pipeleet.business;


public class EventBusContextHolder {

	private static InheritableThreadLocal contextHolder = new InheritableThreadLocal();

	public static EventBusContext getContext() {
		if (contextHolder.get() == null) {
			contextHolder.set(new DefaultEventBusContext());
		}
		return (EventBusContext) contextHolder.get();

	}

}
