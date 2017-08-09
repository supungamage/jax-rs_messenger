package com.fundit.messanger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fundit.messanger.model.Profile;
import com.fundit.messanger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource { 
	
	ProfileService service = new ProfileService();
	
	@GET
	public List<Profile> getProfiles() {
		return service.getProfiles();
	}
	
	@GET 
	@Path("/{profileId}")
	public Profile getProfile(@PathParam("profileId") String name) {
		return service.getProfile(name);
	}
	
	@POST
	public Profile addProfile(Profile profile) {
		return service.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileId}")
	public Profile updateProfile(@PathParam("profileId") String name, Profile profile) {
		profile.setProfileName(name);
		return service.updateProfile(profile);
	}
	
	@DELETE
	@Path("/{profileId}")
	public Profile removeProfile(@PathParam("profileId") String name) {
		return service.removeProfile(name);
	}
	

}
