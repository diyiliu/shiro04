package com.diyiliu.web.service.impl;

import com.diyiliu.web.dao.OrganizationDao;
import com.diyiliu.web.entity.Organization;
import com.diyiliu.web.service.OrganizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: OrganizationServiceImpl
 * Author: DIYILIU
 * Update: 2015-10-27 14:09
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Resource
    private OrganizationDao organizationDao;


    @Override
    public List<Organization> findAll() {
        return organizationDao.findAll();
    }

    @Override
    public Organization findOne(Long organizationId) {
        return organizationDao.findOne(organizationId);
    }

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationDao.createOrganization(organization);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationDao.deleteOrganization(organizationId);
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        return organizationDao.updateOrganization(organization);
    }

    @Override
    public List<Organization> findAllWithExclude(Organization organization) {
        return organizationDao.findAllWithExclude(organization);
    }

    @Override
    public void moveOrganization(Organization source, Organization target) {
        organizationDao.moveOrganization(source, target);
    }
}
