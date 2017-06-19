package com.coral.biz.dao;

import com.coral.base.mongo.MBaseDao;
import com.coral.biz.model.UserModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CCC on 2017/4/28.
 */
@Repository(UserDao.SPRING_BEAN_NAME)
public class UserDao extends MBaseDao<UserModel> {

    public static final String SPRING_BEAN_NAME = "mongo.dao.UserDao";

    public UserDao() {
    }

    public UserModel findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        List<UserModel> userModels = findByQuery(query);
        if (userModels.size() > 0) {
            return userModels.get(0);
        } else {
            return null;
        }
    }

    public Class getEntityClass() {
        return UserModel.class;
    }
}
