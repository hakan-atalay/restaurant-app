package com.anproject.resource;

import java.util.List;

import com.anproject.dto.request.RoleRequestDTO;
import com.anproject.dto.response.RoleResponseDTO;
import com.anproject.service.RoleService;

import jakarta.annotation.security.RolesAllowed;
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

@Path("/roles")
public class RoleResource {

	@Inject
	private RoleService roleService;

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response saveRole(RoleRequestDTO roleRequestDto) {
		try {
			roleService.saveRole(roleRequestDto);
			return Response.status(Response.Status.CREATED).entity(roleRequestDto).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response updateRole(RoleRequestDTO roleRequestDto) {
		try {
			roleService.updateRole(roleRequestDto);
			return Response.status(Response.Status.OK).entity(roleRequestDto).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response deleteRole(@PathParam("id") int id) {
		try {
			roleService.deleteRole(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/get-by-id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("admin")
	public Response getRoleById(@PathParam("id") int id) {
		try {
			RoleResponseDTO role = roleService.getRoleById(id);
			if (role != null) {
				return Response.status(Response.Status.OK).entity(role).build();
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
	@RolesAllowed("admin")
	public Response getAllRoles() {
		try {
			List<RoleResponseDTO> roles = roleService.getAllRoles();
			return Response.status(Response.Status.OK).entity(roles).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
