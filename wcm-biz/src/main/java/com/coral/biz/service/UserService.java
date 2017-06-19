package com.coral.biz.service;

import com.coral.biz.dao.UserDao;
import com.coral.biz.model.UserModel;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccc on 2017/6/15.
 */
public interface UserService {

    public final static String SPRING_BEAN_NAME = "service.UserService";

    List<UserModel> loadUsers();

    void saveUser(UserModel userModel);

    void deleteUser(String id);
}
