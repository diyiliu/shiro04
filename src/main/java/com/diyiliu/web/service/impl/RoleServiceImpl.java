package com.diyiliu.web.service.impl;

import com.diyiliu.web.dao.RoleDao;
import com.diyiliu.web.entity.Role;
import com.diyiliu.web.service.ResourceService;
import com.diyiliu.web.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description: RoleServiceImpl
 * Author: DIYILIU
 * Update: 2015-10-22 15:46
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private ResourceService resourceService;

    @Override
    public Set<String> findRoles(Long... roleIds) {

        Set<String> roles = new HashSet<String>();

        for (Long roleId : roleIds) {
            Role role = roleDao.findOne(roleId);

            if (role != null) {
                roles.add(role.getRole());
            }
        }

        return roles;
    }

    @Override
    public Set<String> findPermissions(Long... roleIds) {

        Set<Long> permissions = new HashSet<Long>();

        for (Long roleId : roleIds) {

            Role role = roleDao.findOne(roleId);

            if (role != null) {

                permissions.addAll(role.getResourceIds());
            }
        }

        return resourceService.findPermissions(permissions.toArray(new Long[0]));
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }
}
