package com.pipeleet.application;

import com.pipeleet.business.conversation.ConversationID;
import com.pipeleet.business.user.UserID;

public class StartConversationCommand {

	private final ConversationID conversationId;
	private final UserID[] participants;

	public StartConversationCommand(ConversationID conversationId, UserID... participants) {
		if (conversationId == null)
			throw new IllegalArgumentException("Cannot start conversation. No converationId provided");
		this.conversationId = conversationId;
		if (participants == null)
			throw new IllegalArgumentException("Cannot start conversation. No participant provided");
		this.participants = participants;
	}

	public ConversationID getConversationId() {
		return conversationId;
	}

	public UserID[] getParticipants() {
		return participants;
	}

}
