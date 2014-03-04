package com.pipeleet.business.conversation;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.pipeleet.business.EventBus;
import com.pipeleet.business.EventBusContext;
import com.pipeleet.business.EventBusContextHolder;
import com.pipeleet.business.user.UserID;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ EventBusContextHolder.class })
public class ConversationTest {

	private EventBus eventBus;

	@Before
	public void setup() {
		PowerMockito.mockStatic(EventBusContextHolder.class);
		EventBusContext eventBusContext = Mockito.mock(EventBusContext.class);
		Mockito.when(EventBusContextHolder.getContext()).thenReturn(eventBusContext);
		eventBus = Mockito.mock(EventBus.class);
		Mockito.when(eventBusContext.getEventBus()).thenReturn(eventBus);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void a_conversation_should_have_a_initiator() {
		new Conversation(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_invite_a_valid_user_to_conversation() {
		UserID participant = new UserID();
		Conversation conversation = new Conversation(participant);
		conversation.invite(null);	
	}
		
	@Test(expected=IllegalArgumentException.class)
	public void fail_to_add_message_to_conversation_when_not_invited() {
		//given bob creates a conversation
		UserID bob = new UserID();
		Conversation conversation = new Conversation(bob);
		UserID hacker = new UserID();
		//when hacker push its message
		Message message = new Message("blabla",hacker);
		conversation.post(message);
		//then it should fail
	}
	
	@Test
	public void should_add_messages_to_conversation_when_invited() {
		//given bob creates a conversation with alice
		UserID bob = new UserID();
		Conversation conversation = new Conversation(bob);
		UserID alice = new UserID();
		conversation.invite(alice);
		//when bob pushes its message
		Message aMessage = new Message("blabla",bob);
		conversation.post(aMessage);
		//when alice pushes its message
		Message anotherMessage = new Message("blabla",alice);
		conversation.post(anotherMessage);
		//then message should be added to conversation
		Assert.assertTrue(conversation.contains(aMessage));
		//then message should be added to conversation
		Assert.assertTrue(conversation.contains(anotherMessage));
	}
	
	@Test
	public void should_post_BOB_message() {
		//given a user BOB
		UserID bob = new UserID();
		//given a user ALICE
		UserID alice = new UserID();
		//given a conversation between BOB and ALICE
		Conversation conversation = new Conversation(bob,alice);
		//when BOB post hello to that conversation
		conversation.post(new Message("hello", bob));
		//then hello should be should be posted
		Mockito.verify(eventBus).raise(new MessagePosted<String>("hello"));
	}
	
	@Test
	public void should_post_BOB_and_ALICE_messages() {
		//given a user BOB
		UserID bob = new UserID();
		//given a user ALICE
		UserID alice = new UserID();
		//given a conversation between BOB and ALICE
		Conversation conversation = new Conversation(bob,alice);
		//when BOB post hello to that conversation
		conversation.post(new Message("hello!!!", bob));
		//when ALICE post hello to that conversation
		conversation.post(new Message("whats up?", alice));
		//then hello!!!! should be should be posted
		Mockito.verify(eventBus).raise(new MessagePosted<String>("hello!!!"));
		//then whats up? should be should be posted
		Mockito.verify(eventBus).raise(new MessagePosted<String>("whats up?"));
	}
	
	@Test
	public void should_invite_BOB_and_ALICE() {
		//given a user BOB
		UserID bob = new UserID();
		//given a user ALICE
		UserID alice = new UserID();
		//when I create a conversation between BOB and ALICE
		new Conversation(bob,alice);
		//then BOB should be invited to that conversation
		Mockito.verify(eventBus).raise(new UserInvited(bob));
		//then ALICE should be invited to that conversation
		Mockito.verify(eventBus).raise(new UserInvited(alice));
	}
	
}
