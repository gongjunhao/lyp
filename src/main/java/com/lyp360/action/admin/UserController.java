package com.lyp360.action.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyp360.dao.SystemUserMapper;
import com.lyp360.entity.SystemRole;
import com.lyp360.entity.SystemUser;
import com.lyp360.service.ISystemRoleService;
import com.lyp360.service.ISystemUserService;
import com.lyp360.utils.MD5;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by GongJunhao on 2016/4/27.
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    private ISystemUserService userService;

    @Autowired
    private ISystemRoleService roleService;

    @RequestMapping(value = "/changePwd", method = RequestMethod.GET)
    public String changePwd(HttpServletRequest request) {
        return "/admin/user/changePwd";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info(HttpServletRequest request, Model model) {
        SystemUser user = (SystemUser)request.getSession().getAttribute("user");
        List<SystemRole> roles = roleService.getRolesByUserId(user.getId());
        model.addAttribute("roles", roles);
        return "/admin/user/info";
    }

    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    public String listPage(HttpServletRequest request) {
        return "/admin/user/list";
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
        SystemUser user = data.getObject("user", SystemUser.class);
        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(pageNum, pageSize, orderBy + " " + sort);
        //紧跟着的第一个select方法会被分页
        List<SystemUser> list = userService.selectUserList(user);
        PageInfo page = new PageInfo(list);
        return page;
    }
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody String user) {
        try {
            SystemUser curUser = JSON.parseObject(user, SystemUser.class);
            userService.updateByPrimaryKeySelective(curUser);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/pwdValidate", method = RequestMethod.GET)
    public String pwdValidate(HttpServletRequest request, HttpServletResponse response) {
        try {
            SystemUser user = (SystemUser)request.getSession().getAttribute("user");
            String srcPwd = userService.selectByPrimaryKey(user.getId()).getPassWord();
            String oldPwd = request.getParameter("oldPwd");
            MD5 md5 = new MD5();
            String getPwd = md5.GetMD5Code(oldPwd);
            if(srcPwd.equals(getPwd)){
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("原密码不正确");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "changePwd", method = RequestMethod.POST)
    public String changePwd(HttpServletRequest request, @RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd1") String newPwd1, @RequestParam("newPwd2") String newPwd2, Model model) {
        SystemUser user = (SystemUser)request.getSession().getAttribute("user");
        MD5 md5 = new MD5();
        String getPwd = md5.GetMD5Code(newPwd1);
        user.setPassWord(getPwd);
        userService.updateByPrimaryKey(user);
        model.addAttribute("message", "修改成功");
        return "/admin/user/changePwd";
    }
}
