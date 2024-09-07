package com.anproject.resource;

import java.util.List;

import com.anproject.entity.Role;
import com.anproject.service.RoleService;

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
	public Response saveRole(Role role) {
		try {
			roleService.saveRole(role);
			return Response.status(Response.Status.CREATED).entity(role).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRole(Role role) {
		try {
			roleService.updateRole(role);
			return Response.status(Response.Status.OK).entity(role).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
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
	public Response getRoleById(@PathParam("id") int id) {
		try {
			Role role = roleService.getRoleById(id);
			if (role != null) {
				System.out.println(role.getRoleName());
				return Response.status(Response.Status.OK).entity(role).build();
			} else {
				System.out.println("yok yemedi");

				return Response.status(Response.Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			System.out.println("yok yemedi exception");

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRoles() {
		try {
			List<Role> roles = roleService.getAllRoles();
			return Response.status(Response.Status.OK).entity(roles).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

}
