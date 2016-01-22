package com.diyiliu.web.service.impl;

import com.diyiliu.web.dao.ResourceDao;
import com.diyiliu.web.service.ResourceService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description: ResourceServiceImpl
 * Author: DIYILIU
 * Update: 2015-10-22 17:24
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceDao resourceDao;

    @Override
    public Set<String> findPermissions(Long... resourceIds) {

        Set<String> permissions = new HashSet<String>();

        for (Long resourceId : resourceIds) {
            com.diyiliu.web.entity.Resource resource = resourceDao.findOne(resourceId);

            if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }

        return permissions;
    }

    @Override
    public List<com.diyiliu.web.entity.Resource> findMenues(Set<String> permissions) {
        List<com.diyiliu.web.entity.Resource> allResources = findAll();
        List<com.diyiliu.web.entity.Resource> menus = new ArrayList<>();
        for (com.diyiliu.web.entity.Resource resource : allResources) {
            if (resource.isRootNode()) {
                continue;
            }
            if (resource.getType() != com.diyiliu.web.entity.Resource.ResourceType.menu) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    @Override
    public com.diyiliu.web.entity.Resource findOne(Long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    public List<com.diyiliu.web.entity.Resource> findAll() {

        return resourceDao.findAll();
    }

    @Override
    public com.diyiliu.web.entity.Resource createResource(com.diyiliu.web.entity.Resource resource) {
        return resourceDao.createResource(resource);
    }

    @Override
    public com.diyiliu.web.entity.Resource updateResource(com.diyiliu.web.entity.Resource resource) {
        return resourceDao.updateResource(resource);
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceDao.deleteResource(resourceId);
    }

    private boolean hasPermission(Set<String> permissions, com.diyiliu.web.entity.Resource resource) {
        if (StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

}
