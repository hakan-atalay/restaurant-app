package com.anproject.resource;

import com.anproject.dto.request.LoginRequestDTO;
import com.anproject.service.AuthService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/auth")
public class AuthResource {

	@Inject
	private AuthService authService;

	@POST
	@Path("/login")
	@RolesAllowed({"admin", "regular_user"})
	public Response login(LoginRequestDTO loginRequestDto) {
		String token = authService.authenticate(loginRequestDto);

		if (token != null) {
			return Response.ok(token).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Geçersiz kullanıcı adı veya şifre.").build();
		}
	}

	@POST
	@Path("/admin")
	public Response adminAction(@Context SecurityContext securityContext) {
		if (securityContext.isUserInRole("admin")) {
			return Response.ok("Admin işlemi başarılı").build();
		} else {
			return Response.status(Response.Status.FORBIDDEN).entity("Yetkiniz yok").build();
		}
	}

	@POST
	@Path("/user")
	public Response userAction(@Context SecurityContext securityContext) {
		if (securityContext.isUserInRole("regular_user")) {
			return Response.ok("Kullanıcı işlemi başarılı").build();
		} else {
			return Response.status(Response.Status.FORBIDDEN).entity("Yetkiniz yok").build();
		}
	}

}
