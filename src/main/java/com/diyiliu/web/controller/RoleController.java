package com.diyiliu.web.controller;

import com.diyiliu.web.entity.Role;
import com.diyiliu.web.service.ResourceService;
import com.diyiliu.web.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Description: RoleController
 * Author: DIYILIU
 * Update: 2015-10-26 17:17
 */

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private ResourceService resourceService;

    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("roleList", roleService.findAll());
        return "/role/list";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setCommonData(model);
        model.addAttribute("role", new Role());
        model.addAttribute("op", "新增");
        return "role/edit";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Role role, RedirectAttributes redirectAttributes) {
        roleService.createRole(role);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/role.htm";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{roleId}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable Long roleId, Model model) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(roleId));
        model.addAttribute("op", "修改");
        return "role/edit";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{roleId}/update", method = RequestMethod.POST)
    public String update(Role role, RedirectAttributes redirectAttributes) {
        roleService.updateRole(role);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/role.htm";
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{roleId}/delete", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable Long roleId, Model model) {
        setCommonData(model);
        model.addAttribute("role", roleService.findOne(roleId));
        model.addAttribute("op", "删除");
        return "role/edit";
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{roleId}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable Long roleId, RedirectAttributes redirectAttributes) {
        roleService.deleteRole(roleId);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/role.htm";
    }

    private void setCommonData(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
    }
}
