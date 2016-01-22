package com.diyiliu.web.controller;

import com.diyiliu.web.entity.Organization;
import com.diyiliu.web.service.OrganizationService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Description: OrganizationController
 * Author: DIYILIU
 * Update: 2015-10-26 17:16
 */

@Controller
@RequestMapping(value = "/organization")
public class OrganizationController {

    @Resource
    private OrganizationService organizationService;

    @RequiresPermissions("organization:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index() {

        return "/organization/index";
    }

    @RequiresPermissions("organization:view")
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String showTree(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        return "/organization/tree";
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{id}/maintain", method = RequestMethod.GET)
    public String showMaintainForm(@PathVariable Long id, Model model) {
        model.addAttribute("organization", organizationService.findOne(id));
        return "/organization/maintain";
    }

    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable Long parentId, Model model) {
        Organization parent = organizationService.findOne(parentId);
        model.addAttribute("parent", parent);

        Organization child = new Organization();
        child.setParentId(parentId);
        child.setParentIds(parent.makeSelfAsParentIds());

        model.addAttribute("child", child);

        return "/organization/appendChild";
    }

    @RequiresPermissions("organization:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String create(Organization organization, RedirectAttributes redirectAttributes) {
        organizationService.createOrganization(organization);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/organization/success.htm";
    }

    @RequiresPermissions("organization:delete")
    @RequestMapping(value = "/{sourceId}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable Long sourceId, RedirectAttributes redirectAttributes) {
        organizationService.deleteOrganization(sourceId);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/organization/success.htm";
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{sourceId}/update", method = RequestMethod.POST)
    public String update(Organization organization, RedirectAttributes redirectAttributes) {
        organizationService.updateOrganization(organization);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/organization/success.htm";
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.GET)
    public String move(@PathVariable Long sourceId, Model model) {
        Organization source = organizationService.findOne(sourceId);
        model.addAttribute("source", source);
        model.addAttribute("targetList", organizationService.findAllWithExclude(source));
        return "/organization/move";
    }

    @RequiresPermissions("organization:update")
    @RequestMapping(value = "/{sourceId}/move", method = RequestMethod.POST)
    public String move(@PathVariable Long sourceId, @RequestParam Long targetId,
                       RedirectAttributes redirectAttributes) {
        Organization source = organizationService.findOne(sourceId);
        Organization target = organizationService.findOne(targetId);
        organizationService.moveOrganization(source, target);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/organization/success.htm";
    }


    @RequiresPermissions("organization:view")
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(@ModelAttribute("msg") String msg) {

        System.out.println(msg);

        return "organization/success";
    }

}
