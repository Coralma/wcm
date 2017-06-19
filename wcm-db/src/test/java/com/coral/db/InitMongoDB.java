package com.coral.db;

import com.coral.base.mongo.MBaseDao;
import com.coral.base.utils.DateUtils;
import com.coral.biz.dao.UserDao;
import com.coral.biz.dao.CodeTableDao;
import com.coral.biz.model.CodeTableModel;
import com.coral.biz.model.OrderModel;
import com.coral.biz.model.UserModel;
import com.coral.wechat.utils.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by ccc on 2017/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test.xml")
public class InitMongoDB {

    @Resource(name = UserDao.SPRING_BEAN_NAME)
    private UserDao userDao;

    @Resource(name = CodeTableDao.SPRING_BEAN_NAME)
    private CodeTableDao codeTableDao;

    @Test
    public void initDB() {
        initUser();
        initCodeTable();
    }

    private void initUser() {
        userDao.removeAll();
        userDao.save(new UserModel("Coral","Coral", MD5Utils.getMD5("1"),"1", "1", "123","coral@g.com", DateUtils.toDate("2017-01-01")));
        userDao.save(new UserModel("Vance", "Vance", MD5Utils.getMD5("1"),"1", "1", "123","vance@g.com",DateUtils.toDate("2016-11-11")));
        userDao.save(new UserModel("Lee", "Lee", MD5Utils.getMD5("1"),"1", "1", "123","lee@g.com",DateUtils.toDate("2016-05-22")));
    }

    private void initCodeTable() {
        codeTableDao.removeAll();
        //角色
        codeTableDao.save(new CodeTableModel("U001", "1","管理员"));
        codeTableDao.save(new CodeTableModel("U001", "2","操作员"));
        //用户状态
        codeTableDao.save(new CodeTableModel("U002", "1","生效"));
        codeTableDao.save(new CodeTableModel("U002", "2","失效"));
    }
}
