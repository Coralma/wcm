package com.coral.biz.service.impl;

import com.coral.biz.dao.UserDao;
import com.coral.biz.model.UserModel;
import com.coral.biz.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccc on 2017/6/15.
 */
@Service(UserService.SPRING_BEAN_NAME)
public class UserServiceImpl implements UserService {

    @Resource(name = UserDao.SPRING_BEAN_NAME)
    private UserDao userDao;

    public List<UserModel> loadUsers() {
        return userDao.findAll();
    }

    public void saveUser(UserModel userModel) {
        userDao.save(userModel);
    }

    @Override
    public void deleteUser(String id) {
        userDao.removeById(id);
    }
}
