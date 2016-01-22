package com.diyiliu.web.controller;

import com.diyiliu.web.entity.User;
import com.diyiliu.web.service.OrganizationService;
import com.diyiliu.web.service.RoleService;
import com.diyiliu.web.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Description: UserController
 * Author: DIYILIU
 * Update: 2015-10-26 17:18
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private RoleService roleService;


    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "/user/list";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("user", new User());
        model.addAttribute("op", "新增");
        return "/user/edit";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(User user, RedirectAttributes redirectAttributes) {
        userService.createUser(user);
        redirectAttributes.addAttribute("msg", "新增成功");
        return "redirect:/user.htm";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{userId}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable Long userId, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.findOne(userId));
        model.addAttribute("op", "修改");
        return "/user/edit";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{userId}/update", method = RequestMethod.POST)
    public String update(User user, RedirectAttributes redirectAttributes) {
        userService.updateUser(user);
        redirectAttributes.addAttribute("msg", "修改成功");
        return "redirect:/user.htm";
    }


    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{userId}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable Long userId, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.findOne(userId));
        model.addAttribute("op", "删除");
        return "/user/edit";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{userId}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
        userService.deleteUser(userId);
        redirectAttributes.addAttribute("msg", "删除成功");
        return "redirect:/user.htm";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{userId}/changePassword", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable Long userId, Model model) {
        setCommonData(model);
        model.addAttribute("user", userService.findOne(userId));
        model.addAttribute("op", "修改密码");
        return "/user/changePassword";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/{userId}/changePassword", method = RequestMethod.POST)
    public String changePassword(@PathVariable Long userId, String newPassword, RedirectAttributes redirectAttributes) {
        userService.changePassword(userId, newPassword);
        redirectAttributes.addAttribute("msg", "修改密码成功");
        return "redirect:/user.htm";
    }


    private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }
}
