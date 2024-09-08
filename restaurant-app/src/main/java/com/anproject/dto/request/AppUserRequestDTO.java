package com.anproject.dto.request;

import java.util.Objects;

import com.anproject.entity.AppUser;
import com.anproject.entity.Role;

public class AppUserRequestDTO {
	private int id;
	private String nickname;
	private String email;
	private String password;
	private Role role;

	public AppUserRequestDTO() {}

	public static AppUser toEntity(AppUserRequestDTO appUserRequestDto) {
		AppUser appUser = new AppUser();
		appUser.setId(appUserRequestDto.getId());
		appUser.setNickname(appUserRequestDto.getNickname());
		appUser.setEmail(appUserRequestDto.getEmail());
		appUser.setPassword(appUserRequestDto.getPassword());
		appUser.setRole(appUserRequestDto.getRole());
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUserRequestDTO other = (AppUserRequestDTO) obj;
		return id == other.id;
	}

}
