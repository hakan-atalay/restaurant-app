package com.anproject.dto.response;

import java.util.Objects;

import com.anproject.entity.Role;

public class RoleResponseDTO {
	private String roleName;

	public RoleResponseDTO() {
	}

	public RoleResponseDTO(String roleName) {
		this.roleName = roleName;
	}

	public static RoleResponseDTO fromEntity(Role role) {
		return new RoleResponseDTO(role.getRoleName());
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(roleName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleResponseDTO other = (RoleResponseDTO) obj;
		return Objects.equals(roleName, other.roleName);
	}

}
