package com.diyiliu.web.controller;

import com.diyiliu.shiro.bind.Constants;
import com.diyiliu.web.entity.User;
import com.diyiliu.web.service.ResourceService;
import com.diyiliu.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * Description: LoginController
 * Author: DIYILIU
 * Update: 2015-10-19 9:48
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private ResourceService resourceService;

    /**
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public String login(HttpServletRequest request) {

        request.removeAttribute("error");

        return "/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String login(HttpServletRequest req, Model model) {

        //System.out.println("login -- ");

        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(exceptionClassName)
                || IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {

            model.addAttribute("error", "用户名/密码错误");
            return "login";
        } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {

            model.addAttribute("error", "登录错误次数超限，请稍后再试！");
            return "login";

        } else if (exceptionClassName != null) {
            model.addAttribute("error", "登录异常：" + exceptionClassName);

            return "login";
        }

        return "redirect:/index.htm";
    }


    @RequestMapping(value = "index")
    public String index(HttpSession session, Model model) {

        User user = (User) session.getAttribute(Constants.CURRENT_USER);

        Set<String> permissions = userService.findPermissions(user.getUsername());
        List<com.diyiliu.web.entity.Resource> menus = resourceService.findMenues(permissions);

        model.addAttribute("menus", menus);

        return "/index";
    }

    @RequestMapping(value = "welcome")
    public String welcome() {


        return "/welcome";
    }

    @RequestMapping(value = "logout")
    public String logout(){

        SecurityUtils.getSubject().logout();
        return "redirect:/index.htm";
    }
}
