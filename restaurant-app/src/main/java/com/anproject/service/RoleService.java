package com.anproject.service;

import com.anproject.dao.RoleDAO;
import com.anproject.dto.request.RoleRequestDTO;
import com.anproject.dto.response.RoleResponseDTO;
import com.anproject.entity.Role;

import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

public class RoleService {
	
	@Inject
    private RoleDAO roleDAO;
    
	public void saveRole(RoleRequestDTO roleRequestDto) {
		Role role = roleRequestDto.toEntity(roleRequestDto);
        roleDAO.saveRole(role);
    }

    public void updateRole(RoleRequestDTO roleRequestDto) {
    	Role role = roleRequestDto.toEntity(roleRequestDto);
        roleDAO.updateRole(role);
    }

    public void deleteRole(int id) {
        roleDAO.deleteRole(id);
    }
    
    public RoleResponseDTO getRoleById(int id) {
    	Role role = roleDAO.getRoleById(id);
    	RoleResponseDTO roleResponseDto = RoleResponseDTO.fromEntity(role);
        return roleResponseDto;
    }

    public List<RoleResponseDTO> getAllRoles() {
    	List<Role> roleList = roleDAO.getAllRoles();
    	List<RoleResponseDTO> roleResponseDtoList = roleList.stream()
    			.map(role -> RoleResponseDTO.fromEntity(role))
    			.collect(Collectors.toList());
        return roleResponseDtoList;
    }
    
}
