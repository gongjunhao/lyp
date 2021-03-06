package com.lyp360.test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyp360.entity.SystemUser;
import com.lyp360.service.ISystemUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by GongJunhao on 2016/4/25.
 * 分页测试
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SystemUserServiceTest {

    @Autowired
    private ISystemUserService userService;

    @Test
    public void insertUser(){
        for (int i=1; i<=20; i++){
            SystemUser systemuser = new SystemUser();
            systemuser.setId(Long.valueOf(i));
            systemuser.setLoginName("admin"+i);
            userService.insert(systemuser);
        }
    }

    @Test
    public void selectUsers() {
        SystemUser systemuser = new SystemUser();
        //systemuser.setLoginName("admin");

        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(1,10, "id desc");
        //紧跟着的第一个select方法会被分页
        List<SystemUser> list = userService.selectUserList(systemuser);

        PageInfo page = new PageInfo(list);

        String s = JSON.toJSONString(page, true);
        System.out.println(s);
    }

    @Test
    public void findUserByLoginName() {
        SystemUser user = userService.findUserByLoginName("admin");
        //SystemUser user = userService.selectByPrimaryKey(0l);
        String s = JSON.toJSONString(user, true);
        System.out.println(s);
    }
}
