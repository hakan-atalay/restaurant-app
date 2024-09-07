package com.anproject.config;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		packages("com.anproject.resource");
	}

}
