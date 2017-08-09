package com.fundit.messanger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.fundit.messanger.model.Message;
import com.fundit.messanger.model.custom.MessageFilterPram;
import com.fundit.messanger.service.MessageService;

@Path("/messages")
@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MassageResource {

	MessageService service = new MessageService();
	/*
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterPram filter) {
		
		if(filter.getYear() > 0) {
			return service.getMessages(filter.getYear());
		}
		
		if(filter.getStart() != null && filter.getStart().intValue() >= 0 
				&& filter.getLimit() != null && filter.getLimit().intValue() >= 0) {
			return service.getMessagesPaginated(filter.getStart(), filter.getLimit());
		}
		
		return service.getAllMessages();
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJSONMessages(@BeanParam MessageFilterPram filter) {
		System.out.println("JSON called");
		if(filter.getYear() > 0) {
			return service.getMessages(filter.getYear());
		}
		
		if(filter.getStart() != null && filter.getStart().intValue() >= 0 
				&& filter.getLimit() != null && filter.getLimit().intValue() >= 0) {
			return service.getMessagesPaginated(filter.getStart(), filter.getLimit());
		}
		
		return service.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXMLMessages(@BeanParam MessageFilterPram filter) {
		System.out.println("XML called");
		if(filter.getYear() > 0) {
			return service.getMessages(filter.getYear());
		}
		
		if(filter.getStart() != null && filter.getStart().intValue() >= 0 
				&& filter.getLimit() != null && filter.getLimit().intValue() >= 0) {
			return service.getMessagesPaginated(filter.getStart(), filter.getLimit());
		}
		
		return service.getAllMessages();
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) { 
		Message message = service.getMessage(id);
		makeSelfLink(uriInfo, message);
		makeProfileLink(uriInfo, message);
		makeCommentsLink(uriInfo, message);
		return message;
	}
	
	@POST
	public Response addMessage(@Context UriInfo uriInfo, Message message) {
		Message newMessage = service.addMessage(message);
		URI url = uriInfo.getRequestUriBuilder().path(String.valueOf(newMessage.getId())).build();
		
		//return Response.status(Status.CREATED).entity(newMessage).build();
		return Response.created(url).entity(newMessage).build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return service.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message removeMessage(@PathParam("messageId") long id) {
		return service.removeMessage(id);
	} 
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	private void makeSelfLink(UriInfo uriInfo, Message message) {
		String url = uriInfo.getBaseUriBuilder().path(MassageResource.class).path(String.valueOf(message.getId())).build().toString();
		message.addLink(url, "self");
	}
	
	private void makeProfileLink(UriInfo uriInfo, Message message) {
		String url = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(String.valueOf(message.getAuthour())).build().toString();
		message.addLink(url, "profile");
	}
	
	private void makeCommentsLink(UriInfo uriInfo, Message message) {
		String url = uriInfo.getBaseUriBuilder()
				.path(MassageResource.class)
				.path(MassageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
		message.addLink(url, "profile");
	}
}
