package com.pipeleet.application;

import com.pipeleet.business.conversation.Conversation;
import com.pipeleet.business.conversation.ConversationID;
import com.pipeleet.business.conversation.ConversationRepository;
import com.pipeleet.business.conversation.Message;

public class PostMessageCommandHandler {

	private ConversationRepository repository;

	public PostMessageCommandHandler(ConversationRepository repository) {
		this.repository = repository;
	}

	public void handle(PostMessageCommand command) {
		ConversationID conversationId = command.getConversationId();
		Conversation conversation = repository.get(conversationId);
		if (conversation == null)
			throw new IllegalArgumentException("Cannot post message to conversation. Conversation<" + conversationId + "> does not exist");
		Message message = new Message(command.getMessageContent(), command.getAuthor());
		conversation.post(message);
	}

}
