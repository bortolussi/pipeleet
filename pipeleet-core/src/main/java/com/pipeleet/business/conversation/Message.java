package com.pipeleet.business.conversation;

import java.util.Date;
import java.util.UUID;

import com.pipeleet.business.user.UserID;

public class Message {

	private final UUID uid;
	private final long timestamp;
	private String content;
	private UserID authorUID;

	public Message(String content, UserID participantUID) {
		uid = UUID.randomUUID();
		timestamp = new Date().getTime();
		setValue(content);
		setAuthor(participantUID);
	}

	private void setAuthor(UserID authorUID) {
		if (authorUID == null)  throw new IllegalArgumentException("Invalid message. No author provided");
		this.authorUID = authorUID;
	}

	private void setValue(String content) {
		if (content == null)  throw new IllegalArgumentException("Invalid message. No content provided");
		this.content = content;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public UserID getAuthor() {
		return authorUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	public String getContent() {
		return content;
	}



}
