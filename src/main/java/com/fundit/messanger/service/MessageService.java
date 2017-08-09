package com.fundit.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.fundit.messanger.database.Database;
import com.fundit.messanger.exception.DataNotFoundException;
import com.fundit.messanger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = Database.getMessages();

	public MessageService() {
		messages.put(1l, new Message(1, "Message 1", "Supun"));
		messages.put(2l, new Message(2, "Message 2", "Supun"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("Data Not Found for " + id);
		}
		return messages.get(id);
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		
		if(message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	
	public List<Message> getMessages(int year) {
		Calendar calendar = Calendar.getInstance();
		List<Message> yearMessages = new ArrayList<Message>();
		for (Message message : messages.values()) {
			calendar.setTime(message.getCreatedDate());
			if(calendar.get(Calendar.YEAR) == year) {
				yearMessages.add(message);
			}
		}
		
		return yearMessages;
	}
	
	public List<Message> getMessagesPaginated(int start, int limit) {
		if((start + limit) > messages.size()) {
			return null;
		}
		
		return (new ArrayList<Message>(messages.values()).subList(start, start + limit));
	}
}
