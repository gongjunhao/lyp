package com.lyp360.test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.lyp360.dao.SystemuserMapper;
import com.lyp360.entity.Systemuser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by GongJunhao on 2016/4/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SystemUserServiceTest {

    @Autowired
    private SystemuserMapper userDao;

    @Test
    public void insertUser(){
        for (int i=0; i<=20; i++){
            Systemuser systemuser = new Systemuser();
            systemuser.setId(Long.valueOf(i));
            systemuser.setLoginname("admin");
            userDao.insert(systemuser);
        }
    }

    @Test
    public void selectUsers() {
        Systemuser systemuser = new Systemuser();
        systemuser.setLoginname("admin");

        //获取第1页，10条内容，默认查询总数count
        PageHelper.startPage(1, 2);

        //紧跟着的第一个select方法会被分页
        List<Systemuser> list = userDao.selectUserList(systemuser);
        String s = JSON.toJSONString(list, true);
        System.out.println(s);
    }
}
