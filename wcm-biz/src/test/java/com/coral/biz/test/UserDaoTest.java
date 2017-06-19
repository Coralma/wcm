package com.coral.biz.test;

import com.coral.biz.dao.UserDao;
import com.coral.biz.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test.xml")
public class UserDaoTest {

    @Resource(name = UserDao.SPRING_BEAN_NAME)
    private UserDao userDao;

    @Test
    public void testSave() {
        UserModel user = new UserModel();
        user.setUsername("Coral");
        user.setEmail("coral@gmail.com");
        user.setMobile("123");
        user.setStatus("1");
        userDao.save(user);
    }
}
