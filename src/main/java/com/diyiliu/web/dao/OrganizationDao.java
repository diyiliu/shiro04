package com.diyiliu.web.dao;


import com.diyiliu.web.entity.Organization;

import java.util.List;

/**
 * <p>Organization: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface OrganizationDao {

    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    public Organization findOne(Long organizationId);
    public List<Organization> findAll();

    public List<Organization> findAllWithExclude(Organization oraganization);

    public void moveOrganization(Organization source, Organization target);
}
