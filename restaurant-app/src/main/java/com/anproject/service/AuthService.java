package com.anproject.service;

import com.anproject.dao.AppUserDAO;
import com.anproject.dto.request.LoginRequestDTO;
import com.anproject.entity.AppUser;
import com.anproject.security.JwtService;
import com.anproject.security.PasswordHashingService;

import jakarta.inject.Inject;

public class AuthService {

	@Inject
	private AppUserDAO appUserDao;

	@Inject
	private PasswordHashingService passwordHashingService;

	@Inject
	private JwtService jwtService;

	public String authenticate(LoginRequestDTO loginRequestDTO) {

		AppUser appUser = appUserDao.getAppUserByNickname(loginRequestDTO.getNickname());

		if (appUser == null) {
			return null;
		}

		if (passwordHashingService.verifyPassword(loginRequestDTO.getPassword(), appUser.getPassword())) {
			// Return JWT token
			return jwtService.generateToken(appUser.getNickname(), appUser.getRole().getRoleName());
		} else {
			return null;
		}
		
	}

}
