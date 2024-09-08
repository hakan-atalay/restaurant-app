package com.anproject.dto.request;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(id, roleName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleRequestDTO other = (RoleRequestDTO) obj;
		return id == other.id && Objects.equals(roleName, other.roleName);
	}

}
