package com.wzt.springboot_jsp_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @User:Tao
 * @date:2021/1/18
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @RequestMapping("save")
    //@RequiresRoles(value = {"user","admin"})//同时具有
    @RequiresPermissions("user:update:01")
    public String save() {
        System.out.println("成功。");
        // 获取主体对象
        // Subject subject = SecurityUtils.getSubject();
        // // 操作
        // if (subject.hasRole("admin")) {
        //     System.out.println("保存订单。");
        // } else {
        //     System.out.println("无权访问。");
        // }

        return "redirect:/index.jsp";
    }

}
