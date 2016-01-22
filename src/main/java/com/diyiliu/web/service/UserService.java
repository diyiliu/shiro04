package com.diyiliu.web.service;

import com.diyiliu.web.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Description: UserService
 * Author: DIYILIU
 * Update: 2015-10-19 13:50
 */
public interface UserService {

    public User findByUsername(String username);

    public Set<String> findRoles(String username);

    public Set<String> findPermissions(String username);

    public List<User> findAll();

    public User createUser(User user);

    public User updateUser(User user);

    public void deleteUser(Long userId);

    public void changePassword(Long userId, String newPassword);

    public User findOne(Long userId);
}
