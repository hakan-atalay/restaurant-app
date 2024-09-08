package com.anproject.resource;

import java.util.List;

import com.anproject.dto.request.AppUserRequestDTO;
import com.anproject.dto.response.AppUserResponseDTO;
import com.anproject.service.AppUserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class AppUserResource {
	
	@Inject
	AppUserService appUserService;
	
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveAppUser(AppUserRequestDTO appUserRequestDTO) {
		try {
			appUserService.saveAppUser(appUserRequestDTO);
			return Response.status(Response.Status.CREATED).entity(appUserRequestDTO).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAppUser(AppUserRequestDTO appUserRequestDTO) {
		try {
			appUserService.updateAppUser(appUserRequestDTO);
			return Response.status(Response.Status.OK).entity(appUserRequestDTO).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRole(@PathParam("id") int id) {
		try {
			appUserService.deleteAppUser(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/get-by-id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRoleById(@PathParam("id") int id) {
		try {
			AppUserResponseDTO user = appUserService.getUserById(id);
			if (user != null) {
				return Response.status(Response.Status.OK).entity(user).build();
			} else {
				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRoles() {
		try {
			List<AppUserResponseDTO> users = appUserService.getAllAppUsers();
			return Response.status(Response.Status.OK).entity(users).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
