package com.lyp360.action.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by GongJunhao on 2016/4/29.
 * PermissionController
 */
@Controller
@RequestMapping(value = "/admin/permission")
public class PermissionController {

    @RequestMapping(value = "listPage", method = RequestMethod.GET)
    public String listPage(){
        return "/admin/permission/list";
    }
}
