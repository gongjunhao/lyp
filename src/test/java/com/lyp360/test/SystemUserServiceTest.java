package com.lyp360.test;

import com.lyp360.dao.SystemuserMapper;
import com.lyp360.entity.Systemuser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        Systemuser systemuser = new Systemuser();
        systemuser.setId(11l);
        systemuser.setLoginname("admin");
        userDao.insert(systemuser);
    }
}
