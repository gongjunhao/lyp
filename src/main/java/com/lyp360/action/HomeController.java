package com.lyp360.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by junhao on 2016/4/24.
 */
@RequestMapping(value = "/")
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home(){
        return "index";
    }
}
