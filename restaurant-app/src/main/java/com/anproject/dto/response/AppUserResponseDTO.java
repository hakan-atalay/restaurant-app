package com.anproject.dto.response;

import java.util.Date;

import com.anproject.entity.AppUser;

public class AppUserResponseDTO {
	private int id;
	private String nickname;
	private String email;
	private RoleResponseDTO roleResponseDto;
	private Date created;
	private Date updated;

	public AppUserResponseDTO() {
	}

	public AppUserResponseDTO(int id, String nickname, String email, RoleResponseDTO roleResponseDto, Date created,
			Date updated) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.roleResponseDto = roleResponseDto;
		this.created = created;
		this.updated = updated;
	}

	public static AppUserResponseDTO fromEntity(AppUser appUser) {
		return new AppUserResponseDTO(
				appUser.getId(),
				appUser.getNickname(), 
				appUser.getEmail(),
				RoleResponseDTO.fromEntity(appUser.getRole()),
				appUser.getCreated(), 
				appUser.getUpdated());
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

	public RoleResponseDTO getRoleResponseDto() {
		return roleResponseDto;
	}

	public void setRoleResponseDto(RoleResponseDTO roleResponseDto) {
		this.roleResponseDto = roleResponseDto;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
