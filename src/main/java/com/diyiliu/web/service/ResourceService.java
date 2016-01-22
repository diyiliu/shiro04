package com.diyiliu.web.service;

import com.diyiliu.web.entity.Resource;

import java.util.List;
import java.util.Set;

/**
 * Description: ResourceService
 * Author: DIYILIU
 * Update: 2015-10-22 17:20
 */
public interface ResourceService {

    public Set<String> findPermissions(Long... resourceIds);

    public List<Resource> findMenues(Set<String> permissions);

    public Resource findOne(Long resourceId);

    public List<Resource> findAll();

    public Resource createResource(Resource resource);

    public Resource updateResource(Resource resource);

    public void deleteResource(Long resourceId);
}
