package com.diyiliu.web.service;

import com.diyiliu.web.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * Description: RoleService
 * Author: DIYILIU
 * Update: 2015-10-22 14:09
 */
public interface RoleService {

    public Set<String> findRoles(Long... roleIds);

    public Set<String> findPermissions(Long... roleIds);

    public List<Role> findAll();

    public Role findOne(Long roleId);

    public Role createRole(Role role);

    public Role updateRole(Role role);

    public void deleteRole(Long roleId);
}
