package com.pipeleet.business;

import com.pipeleet.business.conversation.BusinessEvent;

public class DefaultEventBusContext implements EventBusContext  {

	private final com.google.common.eventbus.EventBus eventBus;

	public DefaultEventBusContext() {
		super();
		eventBus = new com.google.common.eventbus.EventBus();
	}

	@Override
	public EventBus getEventBus() {
		// TODO Auto-generated method stub
		return new EventBus() {
			
			@Override
			public void unRegister(Object listener) {
				eventBus.unregister(listener);
				
			}
			
			@Override
			public void register(Object listener) {
				eventBus.unregister(listener);
				
			}
			
			@Override
			public void raise(BusinessEvent event) {
				eventBus.post(event);
				
			}
		};
	}


}
