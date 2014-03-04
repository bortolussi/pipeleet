package com.pipeleet.application;

import com.pipeleet.business.conversation.Conversation;
import com.pipeleet.business.conversation.ConversationRepository;

public class StartConversationCommandHandler {
	
	private ConversationRepository repository;

	public StartConversationCommandHandler(ConversationRepository repository) {
		this.repository = repository;
	}

	public void handle(StartConversationCommand command) {
		Conversation conversation = new Conversation(command.getParticipants());
		repository.save(conversation);	
	}

}
