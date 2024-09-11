package com.anproject.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.anproject.dao.AppUserDAO;
import com.anproject.dao.RoleDAO;
import com.anproject.dto.request.AppUserRequestDTO;
import com.anproject.dto.response.AppUserResponseDTO;
import com.anproject.entity.AppUser;
import com.anproject.entity.Role;
import com.anproject.security.PasswordHashingService;

import jakarta.inject.Inject;

public class AppUserService {

	@Inject
	private AppUserDAO appUserDao;

	@Inject
	private RoleDAO roleDAO;
	
	@Inject
	private PasswordHashingService passwordHashingService;

	public void saveAppUser(AppUserRequestDTO appUserRequestDto) {
		Role role = roleDAO.getRoleById(appUserRequestDto.getRoleId());
		AppUser appUser = appUserRequestDto.toEntity(appUserRequestDto, role);
		appUser.setPassword(passwordHashingService.hashPassword(appUser.getPassword()));
		appUser.setCreated(new Date());
		appUserDao.saveAppUser(appUser);
	}

	public void updateAppUser(AppUserRequestDTO appUserRequestDto) {
		AppUser existingAppUser = appUserDao.getAppUserById(appUserRequestDto.getId());

		if (existingAppUser != null) {
			Role role = roleDAO.getRoleById(appUserRequestDto.getRoleId());
			AppUser appUser = appUserRequestDto.toEntity(appUserRequestDto, role);
			appUser.setCreated(existingAppUser.getCreated());
			appUser.setUpdated(new Date());
			
			  if (appUserRequestDto.getPassword() != null && !appUserRequestDto.getPassword().isEmpty()) {
	                appUser.setPassword(passwordHashingService.hashPassword(appUserRequestDto.getPassword()));
	            } else {
	                appUser.setPassword(existingAppUser.getPassword());
	            }
			  
			appUserDao.updateAppUser(appUser);
		}

	}

	public void deleteAppUser(int id) {
		appUserDao.deleteAppUser(id);
	}

	public AppUserResponseDTO getUserById(int id) {
		AppUser appUser = appUserDao.getAppUserById(id);
		AppUserResponseDTO appUserResponseDTO = AppUserResponseDTO.fromEntity(appUser);
		return appUserResponseDTO;
	}

	public List<AppUserResponseDTO> getAllAppUsers() {
		List<AppUser> appUserList = appUserDao.getAllAppUsers();
		List<AppUserResponseDTO> appUserResponseDtoList = appUserList.stream()
				.map(appUser -> AppUserResponseDTO.fromEntity(appUser)).collect(Collectors.toList());
		return appUserResponseDtoList;
	}

}
