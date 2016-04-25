package com.lyp360.action.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by GongJunhao on 2016/4/25.
 *
 * AdminController
 */
@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    @RequestMapping(value = "")
    public String adminIndex(){
        return "/admin/index";
    }
}
