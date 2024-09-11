package com.anproject.security;

import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.PasswordHash;

public class PasswordHashingService {

	@Inject
	private PasswordHash passwordHash;

	public String hashPassword(String plainPassword) {
		return passwordHash.generate(plainPassword.toCharArray());
	}

	public boolean verifyPassword(String plainPassword, String hashedPassword) {
		return passwordHash.verify(plainPassword.toCharArray(), hashedPassword);
	}
	
}
