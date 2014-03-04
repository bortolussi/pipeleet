package com.pipeleet.application;

import com.pipeleet.business.conversation.ConversationID;
import com.pipeleet.business.user.UserID;

public class PostMessageCommand {

	private final ConversationID conversationId;
	private final UserID author;
	private final String messageContent;

	public PostMessageCommand(ConversationID conversationId, UserID author, String messageContent) {
		this.conversationId = conversationId;
		this.author = author;
		this.messageContent = messageContent;
	}

	public ConversationID getConversationId() {
		return conversationId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public UserID getAuthor() {
		return author;
	}

}
