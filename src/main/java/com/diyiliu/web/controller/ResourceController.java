package com.diyiliu.web.controller;

import com.diyiliu.web.service.ResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Description: ResourceController
 * Author: DIYILIU
 * Update: 2015-10-26 17:17
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @RequiresPermissions("resource:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
        return "/resource/list";
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model) {
        com.diyiliu.web.entity.Resource parent = resourceService.findOne(parentId);
        model.addAttribute("parent", parent);
        com.diyiliu.web.entity.Resource child = new com.diyiliu.web.entity.Resource();
        child.setParentId(parentId);
        child.setParentIds(parent.makeSelfAsParentIds());
        model.addAttribute("resource", child);
        model.addAttribute("op", "新增子节点");
        return "resource/edit";
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String create(com.diyiliu.web.entity.Resource resource, RedirectAttributes redirectAttributes) {
        resourceService.createResource(resource);
        redirectAttributes.addFlashAttribute("msg", "新增子节点成功");
        return "redirect:/resource.htm";
    }

    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{resourceId}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable Long resourceId, Model model) {
        model.addAttribute("resource", resourceService.findOne(resourceId));
        model.addAttribute("op", "修改");
        return "resource/edit";
    }

    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{resourceId}/update", method = RequestMethod.POST)
    public String update(com.diyiliu.web.entity.Resource resource, RedirectAttributes redirectAttributes) {
        resourceService.updateResource(resource);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/resource.htm";
    }

    @RequiresPermissions("resource:delete")
    @RequestMapping(value = "/{resourceId}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long resourceId, RedirectAttributes redirectAttributes) {
        resourceService.deleteResource(resourceId);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/resource.htm";
    }
}
