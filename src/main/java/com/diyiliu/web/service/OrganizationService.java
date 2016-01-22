package com.diyiliu.web.service;

import com.diyiliu.web.entity.Organization;

import java.util.List;

/**
 * Description: OrganizationService
 * Author: DIYILIU
 * Update: 2015-10-27 14:08
 */
public interface OrganizationService {

    public List<Organization> findAll();

    public Organization findOne(Long organizationId);

    public Organization createOrganization(Organization organization);

    public void deleteOrganization(Long organizationId);

    public Organization updateOrganization(Organization organization);

    public List<Organization> findAllWithExclude(Organization organization);

    public void moveOrganization(Organization source, Organization target);
}
