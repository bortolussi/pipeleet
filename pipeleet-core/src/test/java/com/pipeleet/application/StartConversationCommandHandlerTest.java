package com.pipeleet.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.pipeleet.business.EventBus;
import com.pipeleet.business.EventBusContext;
import com.pipeleet.business.EventBusContextHolder;
import com.pipeleet.business.conversation.ConversationID;
import com.pipeleet.business.conversation.ConversationRepository;
import com.pipeleet.business.conversation.UserInvited;
import com.pipeleet.business.user.UserID;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ EventBusContextHolder.class })
public class StartConversationCommandHandlerTest {

	private StartConversationCommandHandler handler;

	@Mock
	private ConversationRepository conversationRepository;

	private EventBus eventBus;

	@Before
	public void setup() {
		PowerMockito.mockStatic(EventBusContextHolder.class);
		EventBusContext eventBusContext = Mockito.mock(EventBusContext.class);
		Mockito.when(EventBusContextHolder.getContext()).thenReturn(eventBusContext);
		eventBus = Mockito.mock(EventBus.class);
		Mockito.when(eventBusContext.getEventBus()).thenReturn(eventBus);
		handler = new StartConversationCommandHandler(conversationRepository);
	}

	@Test
	public void should_start_conversation_between_BOB_AND_ALICE() {
		ConversationID conversationId = new ConversationID();
		//given a user BOB
		UserID BOB = new UserID();
		//given a user ALICE
		UserID ALICE = new UserID();
		//when I start a conversation between BOB and ALICE
		handler.handle(new StartConversationCommand(conversationId, BOB, ALICE));
		//then BOB should be invited to that conversation
		Mockito.verify(eventBus).raise(new UserInvited(BOB));
		//then ALICE should be invited to that conversation
		Mockito.verify(eventBus).raise(new UserInvited(ALICE));
	}


}
