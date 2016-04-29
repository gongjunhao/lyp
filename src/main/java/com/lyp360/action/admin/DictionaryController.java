package com.lyp360.action.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by GongJunhao on 2016/4/29.
 * DictionaryController
 */
@Controller
@RequestMapping(value = "/admin/dictionary")
public class DictionaryController {

    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String listPage(){
        return "/admin/dictionary/list";
    }
}
