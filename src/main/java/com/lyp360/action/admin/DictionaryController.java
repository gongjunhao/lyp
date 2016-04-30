package com.lyp360.action.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyp360.entity.Dictionary;
import com.lyp360.entity.SystemUser;
import com.lyp360.service.IDictionaryService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by GongJunhao on 2016/4/29.
 * DictionaryController
 */
@Controller
@RequestMapping(value = "/admin/dictionary")
public class DictionaryController {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private IDictionaryService dictionaryService;

    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String listPage(){
        return "/admin/dictionary/list";
    }

    @ResponseBody
    @RequestMapping(value = "/childNodes", method = RequestMethod.POST)
    public Object getChildNodes(HttpServletRequest request){
        String parentId = request.getParameter("id");
        if(StringUtils.isBlank(parentId)){
            parentId = "0";
        }
        List<Dictionary> nodes = dictionaryService.getChildNodes(Long.parseLong(parentId));
        return nodes;
    }

    @ResponseBody
    @RequestMapping(value = "/nodes", method = RequestMethod.POST)
    public Object getNodes(HttpServletRequest request){
        Dictionary dictionary = new Dictionary();
        List<Dictionary> nodes = dictionaryService.selectDictionaryList(dictionary);
        return nodes;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageInfo list(@RequestBody String json) {
        log.debug(json);
        JSONObject data = JSON.parseObject(json);
        int pageNum = data.getInteger("pageNum");
        int pageSize = data.getInteger("pageSize");
        String orderBy = data.getString("orderBy");
        String sort = data.getString("sort");
        Dictionary dictionary = data.getObject("dictionary", Dictionary.class);
        PageHelper.startPage(pageNum, pageSize, orderBy + " " + sort);
        List<Dictionary> list = dictionaryService.selectDictionaryList(dictionary);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody String dictionary, HttpServletRequest request) {
        try {
            SystemUser user = (SystemUser)request.getSession().getAttribute("user");
            Dictionary dict = JSON.parseObject(dictionary, Dictionary.class);
            if(dict.getId() != null){
                dictionaryService.updateByPrimaryKeySelective(dict);
            } else {
                dict.setCreateUserId(user.getId());
                dict.setCreateTime(new Date());
                dict.setStatus("0");
                dictionaryService.insert(dict);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public void del(@RequestBody String dictionary, HttpServletRequest request) {
        try {
            Dictionary dict = JSON.parseObject(dictionary, Dictionary.class);
            if(dict.getId() != null){
                dict.setStatus("del");
                dictionaryService.updateByPrimaryKeySelective(dict);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
