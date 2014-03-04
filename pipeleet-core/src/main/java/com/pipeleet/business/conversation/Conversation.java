package com.pipeleet.business.conversation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.pipeleet.business.EventBusContextHolder;
import com.pipeleet.business.user.UserID;

public class Conversation {

	private Set<UserID> participants;
	private List<Message> messages;

	public Conversation(UserID... participants) {
		this.participants = new HashSet<UserID>();
		messages = new LinkedList<Message>();
		invite(participants);
	}

	public void invite(UserID... participants) {
		if (isEmpty(participants))
			throw new IllegalArgumentException("Cannot invite participants to conversation. No participant provided.");
		for (UserID participant : participants) {
			invite(participant);
		}
	}

	private void invite(UserID participant) {
		if (participant == null)
			throw new IllegalArgumentException("Cannot invite <" + participant + "> to conversation. Invalid value for participant.");
		participants.add(participant);
		EventBusContextHolder.getContext().getEventBus().raise(new UserInvited(participant));
	}

	public void post(Message message) {
		if (!participants.contains(message.getAuthor()))
			throw new IllegalArgumentException("Cannot push message. Message author <" + message.getAuthor() + "> has not been invited.");
		this.messages.add(message);
		EventBusContextHolder.getContext().getEventBus().raise(new MessagePosted<String>(message.getContent()));
	}

	public boolean contains(Message message) {
		return messages.contains(message);
	}

	public List<Message> getMessages() {
		return messages;
	}

	private boolean isEmpty(UserID... participants) {
		return participants == null || participants.length == 0;
	}

}
