package com.anproject.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.anproject.dao.AppUserDAO;
import com.anproject.dto.request.AppUserRequestDTO;
import com.anproject.dto.response.AppUserResponseDTO;
import com.anproject.entity.AppUser;

import jakarta.inject.Inject;

public class AppUserService {
	
	@Inject
	private AppUserDAO appUserDao;
	
	public void saveAppUser(AppUserRequestDTO appUserRequestDto) {
		AppUser appUser = appUserRequestDto.toEntity(appUserRequestDto);
		appUser.setCreated(new Date());
        appUserDao.saveAppUser(appUser);
    }

    public void updateAppUser(AppUserRequestDTO appUserRequestDto) {
    	AppUser existingAppUser = appUserDao.getAppUserById(appUserRequestDto.getId());
    	
    	if(existingAppUser != null) {
        	AppUser appUser = appUserRequestDto.toEntity(appUserRequestDto);
        	appUser.setCreated(existingAppUser.getCreated());
        	appUser.setUpdated(new Date());
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
    			.map(appUser -> AppUserResponseDTO.fromEntity(appUser))
    			.collect(Collectors.toList());
        return appUserResponseDtoList;
    }
    
}
