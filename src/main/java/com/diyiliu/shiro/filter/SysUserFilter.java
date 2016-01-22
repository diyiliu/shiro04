package com.diyiliu.shiro.filter;

import com.diyiliu.shiro.bind.Constants;
import com.diyiliu.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Description: SysUserFilter
 * Author: DIYILIU
 * Update: 2015-10-16 16:11
 */
public class SysUserFilter extends PathMatchingFilter{

    @Resource
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        //System.out.println("SysUserFilter -- ");

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
