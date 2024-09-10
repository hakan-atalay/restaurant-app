package com.anproject.dto.request;


import com.anproject.entity.Role;

public class RoleRequestDTO {
	private int id;
	private String roleName;

	public RoleRequestDTO() {}
	
	public static Role toEntity(RoleRequestDTO requestDto) {
		Role role = new Role();
		role.setId(requestDto.getId());
		role.setRoleName(requestDto.getRoleName());
		return role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
