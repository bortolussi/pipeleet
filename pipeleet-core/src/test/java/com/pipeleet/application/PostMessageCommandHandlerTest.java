package com.pipeleet.application;

import org.junit.Before;
import org.junit.Ignore;
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
import com.pipeleet.business.conversation.Conversation;
import com.pipeleet.business.conversation.ConversationID;
import com.pipeleet.business.conversation.ConversationRepository;
import com.pipeleet.business.conversation.MessagePosted;
import com.pipeleet.business.user.UserID;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ EventBusContextHolder.class })
public class PostMessageCommandHandlerTest {

	private PostMessageCommandHandler handler;

	@Mock
	private ConversationRepository conversationRepository;

	private EventBus eventBus;

	@Before
	public void setup() throws Exception {
		PowerMockito.mockStatic(EventBusContextHolder.class);
		EventBusContext eventBusContext = Mockito.mock(EventBusContext.class);
		Mockito.when(EventBusContextHolder.getContext()).thenReturn(eventBusContext);
		eventBus = Mockito.mock(EventBus.class);
		Mockito.when(eventBusContext.getEventBus()).thenReturn(eventBus);
		//conversationRepository = PowerMockito.mock(ConversationRepository.class);
		//PowerMockito.whenNew(EventBus.class).withNoArguments().thenReturn(eventBus);

		handler = new PostMessageCommandHandler(conversationRepository);
	}

	@Test
	@Ignore
	public void should_post_BOB_message_to_conversation() {
		//given a user BOB
		UserID BOB = new UserID();
		//given a user ALICE
		UserID ALICE = new UserID();
		//given a conversation between BOB and ALICE
		ConversationID conversationId = new ConversationID();
		Mockito.when(conversationRepository.get(new ConversationID())).thenReturn(new Conversation(BOB,ALICE));
		//when BOB post hello to conversation
		handler.handle(new PostMessageCommand(conversationId, BOB,"hello"));
		//then a message should be posted
		Mockito.verify(eventBus).raise(new MessagePosted<String>("hello"));
	}


}
