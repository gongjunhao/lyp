package com.lyp360.action.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyp360.entity.SystemRole;
import com.lyp360.service.ISystemRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by GongJunhao on 2016/4/27.
 * RoleController
 */
@Controller
@RequestMapping(value = "/admin/role")
public class RoleController {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private ISystemRoleService roleService;


    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String listPage(HttpServletRequest request) {
        return "/admin/role/list";
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
        SystemRole role = data.getObject("role", SystemRole.class);
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum, pageSize, orderBy + " " + sort);
        //紧跟着的第一个select方法会被分页
        List<SystemRole> list = roleService.selectRoleList(role);
        PageInfo page = new PageInfo(list);
        return page;
    }
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody String user) {
        try {
            SystemRole role = JSON.parseObject(user, SystemRole.class);
            if(role.getId() != null){
                roleService.updateByPrimaryKeySelective(role);
            } else {
                role.setCreateTime(new Date());
                role.setStatus("0");
                roleService.insert(role);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/validRoleCode", method = RequestMethod.POST)
    public JSONObject validLoginName(@RequestBody String json) {
        try {
            log.debug(json);
            String roleCode = JSON.parseObject(json).getString("value");
            SystemRole role = roleService.findRoleByRoleCode(roleCode);
            JSONObject object = new JSONObject();
            if(role != null){
                object.put("isValid", false);
                object.put("value", roleCode);
            } else {
                object.put("isValid", true);
                object.put("value", roleCode);
            }
            return object;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public void del(@RequestBody String role) {
        try {
            SystemRole curRole = JSON.parseObject(role, SystemRole.class);
            curRole.setStatus("del");
            roleService.updateByPrimaryKeySelective(curRole);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
