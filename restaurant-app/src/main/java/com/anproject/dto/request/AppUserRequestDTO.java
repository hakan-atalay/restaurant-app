package com.anproject.dto.request;


import com.anproject.entity.AppUser;
import com.anproject.entity.Role;

public class AppUserRequestDTO {
	private int id;
	private String nickname;
	private String email;
	private String password;
	private int roleId;

	public AppUserRequestDTO() {}

	public static AppUser toEntity(AppUserRequestDTO appUserRequestDto, Role role) {
		AppUser appUser = new AppUser();
		appUser.setId(appUserRequestDto.getId());
		appUser.setNickname(appUserRequestDto.getNickname());
		appUser.setEmail(appUserRequestDto.getEmail());
		appUser.setPassword(appUserRequestDto.getPassword());
		appUser.setRole(role);
		return appUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
