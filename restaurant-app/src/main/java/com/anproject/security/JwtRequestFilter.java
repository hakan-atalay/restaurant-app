package com.anproject.security;

import java.io.IOException;
import java.security.Principal;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(1000)
public class JwtRequestFilter implements ContainerRequestFilter {

	@Inject
	private JwtService jwtService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String token = requestContext.getHeaderString("Authorization");

		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);

			if (jwtService.validateToken(token)) {
				String role = jwtService.getRoleFromToken(token);
				requestContext.setSecurityContext(new CustomSecurityContext(role));
			}
		}
	}

	private static class CustomSecurityContext implements SecurityContext {
		private String role;

		public CustomSecurityContext(String role) {
			this.role = role;
		}

		@Override
		public boolean isUserInRole(String role) {
			return this.role.equals(role);
		}

		@Override
		public String getAuthenticationScheme() {
			return "Bearer";
		}

		@Override
		public Principal getUserPrincipal() {
			return null;
		}

		@Override
		public boolean isSecure() {
			// TODO Auto-generated method stub
			return false;
		}
	}
}
