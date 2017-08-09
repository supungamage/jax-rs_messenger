package com.fundit.messanger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.fundit.messanger.database.Database;
import com.fundit.messanger.model.Comment;
import com.fundit.messanger.model.ErrorMessage;
import com.fundit.messanger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = Database.getMessages();
	
	public List<Comment> getAllComments(long messageId) { 
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(long messageId, long commentId) { 
		ErrorMessage error = new ErrorMessage("Not Found", 406, "www.google.com");
		Response response = Response.status(Status.NOT_FOUND).entity(error).build();
		
		Message message = messages.get(messageId);
		if(message == null) {
			throw new WebApplicationException(response);
		}
		
		Map<Long, Comment> comments = messages.get(messageId).getComments(); 
		Comment comment = comments.get(commentId);
		if(comment == null) {
			throw new NotFoundException(response);
		}

		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments(); 
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		
		if(messageId <= 0 || comment == null || comment.getId() < 0 ) {
			return null;
		}
		
		Map<Long, Comment> comments = messages.get(messageId).getComments(); 
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments(); 
		return comments.remove(commentId);
	}
}
