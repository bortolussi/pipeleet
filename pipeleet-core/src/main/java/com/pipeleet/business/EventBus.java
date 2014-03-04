package com.pipeleet.business;

import com.pipeleet.business.conversation.BusinessEvent;

public interface EventBus {

	void register(Object listener);

	void unRegister(Object listener);

	void raise(BusinessEvent event);

}
