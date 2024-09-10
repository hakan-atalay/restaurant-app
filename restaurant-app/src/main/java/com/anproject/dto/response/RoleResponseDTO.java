package com.anproject.dto.response;


import com.anproject.entity.Role;

public class RoleResponseDTO {
	private int id;
	private String roleName;

	public RoleResponseDTO() {
	}

	public RoleResponseDTO(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}

	public static RoleResponseDTO fromEntity(Role role) {
		return new RoleResponseDTO(role.getId() ,role.getRoleName());
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
