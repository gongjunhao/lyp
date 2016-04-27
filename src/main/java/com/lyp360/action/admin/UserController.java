package com.lyp360.action.admin;

import com.lyp360.dao.SystemUserMapper;
import com.lyp360.entity.SystemRole;
import com.lyp360.entity.SystemUser;
import com.lyp360.service.ISystemRoleService;
import com.lyp360.service.ISystemUserService;
import com.lyp360.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by GongJunhao on 2016/4/27.
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

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
