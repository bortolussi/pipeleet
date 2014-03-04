package com.pipeleet.business.conversation;

import com.pipeleet.business.conversation.Conversation;

public interface ConversationRepository {

	public void save(Conversation conversation);

	public Conversation get(ConversationID conversationId);

}
