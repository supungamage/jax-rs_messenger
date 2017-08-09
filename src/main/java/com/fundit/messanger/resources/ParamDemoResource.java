package com.fundit.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("/demo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class ParamDemoResource {

	@GET
	@Path("/param")
	public String getParamAnnotation(@QueryParam("query") String query, 
			@MatrixParam("metrix") String matrix, 
			@HeaderParam("header") String header, 
			@CookieParam("cookey") String cookey) {
		return "Test : " + query + " : " + matrix + " : " + header + " : " + cookey;
	}
	
	@GET
	@Path("/context")
	public String getContextAnnotation(@Context UriInfo uriInfo, @Context HttpHeaders header) {
		String path = uriInfo.getAbsolutePath().toString();
		MultivaluedMap<String, String> pathParams = uriInfo.getPathParameters();
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		
		String cookies = header.getCookies().toString();
		return "Test : " + path + " : " + cookies;
	}
}
