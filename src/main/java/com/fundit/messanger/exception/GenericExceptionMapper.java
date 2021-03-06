package com.fundit.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fundit.messanger.model.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage error = new ErrorMessage(ex.getMessage(), 500, "www.google.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}


}
