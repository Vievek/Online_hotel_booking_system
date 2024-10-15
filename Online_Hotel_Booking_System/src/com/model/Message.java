package com.model;

public class Message {
	 private int message_id;
	 private int chat_id;
	 private int sender_id;
	 private String message;
	 
	public Message(int message_id, int chat_id, int sender_id, String message) {
		super();
		this.message_id = message_id;
		this.chat_id = chat_id;
		this.sender_id = sender_id;
		this.message = message;
	}

	public int getMessage_id() {
		return message_id;
	}

	public int getChat_id() {
		return chat_id;
	}

	public int getSender_id() {
		return sender_id;
	}

	public String getMessage() {
		return message;
	}
	
	
	 
	 
}
