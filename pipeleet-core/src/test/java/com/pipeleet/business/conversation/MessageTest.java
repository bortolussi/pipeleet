package com.pipeleet.business.conversation;

import org.junit.Assert;
import org.junit.Test;

import com.pipeleet.business.conversation.Message;
import com.pipeleet.business.user.UserID;

public class MessageTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void fail_to_create_a_message_with_no_author() {
		new Message("blabla", null);
	}
	
	@Test
	public void two_different_messages_even_with_same_content_are_different() {
		UserID bob = new UserID();
		Assert.assertNotEquals(new Message("blabla", bob), new Message("blabla", bob));
	}
	
	@Test
	public void once_created_a_message_should_be_timestamped() {
		UserID bob = new UserID();
		Message message = new Message("blabla", bob);
		Assert.assertNotNull(message.getTimestamp());
	}


}
