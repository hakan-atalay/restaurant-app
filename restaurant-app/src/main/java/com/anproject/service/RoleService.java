package com.anproject.service;

import com.anproject.dao.RoleDAO;
import com.anproject.entity.Role;

import jakarta.inject.Inject;

import java.util.List;

public class RoleService {
	
	@Inject
    private RoleDAO roleDAO;
    
	public void saveRole(Role role) {
        roleDAO.saveRole(role);
    }

    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    public void deleteRole(int id) {
        roleDAO.deleteRole(id);
    }
    
    public Role getRoleById(int id) {
        return roleDAO.getRoleById(id);
    }

    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
    
}
