package com.diyiliu.web.service.impl;

import com.diyiliu.shiro.helper.PasswordHelper;
import com.diyiliu.web.dao.UserDao;
import com.diyiliu.web.entity.Role;
import com.diyiliu.web.entity.User;
import com.diyiliu.web.service.RoleService;
import com.diyiliu.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Description: UserServiceImpl
 * Author: DIYILIU
 * Update: 2015-10-19 13:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleService roleService;

    @Resource
    private PasswordHelper passwordHelper;

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 根据用户名查找其角色
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> findRoles(String username) {

        User user = userDao.findByUsername(username);

        if (user == null) {

            return Collections.emptySet();
        }

        return roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
    }

    /**
     * 根据用户名查找其权限
     *
     * @param username
     * @return
     */
    @Override
    public Set<String> findPermissions(String username) {

        User user = userDao.findByUsername(username);

        if (user == null) {

            return Collections.emptySet();
        }

        return roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }


    @Override
    public User createUser(User user) {
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);

        userDao.updateUser(user);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.findOne(userId);
    }
}
