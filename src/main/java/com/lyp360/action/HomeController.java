package com.lyp360.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyp360.entity.CertificateCard;
import com.lyp360.entity.Dictionary;
import com.lyp360.service.ICertificateCardService;
import com.lyp360.service.IDictionaryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by junhao on 2016/4/24.
 *
 * HomeController
 */
@Controller
public class HomeController {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private IDictionaryService dictionaryService;

    @Autowired
    private ICertificateCardService cardService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "/cms/index";
    }

}
