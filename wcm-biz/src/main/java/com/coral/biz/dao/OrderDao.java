package com.coral.biz.dao;

import com.coral.base.mongo.MBaseDao;
import com.coral.biz.model.OrderModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by CCC on 2017/4/28.
 */
@Repository(OrderDao.SPRING_BEAN_NAME)
public class OrderDao extends MBaseDao<OrderModel> {

    public static final String SPRING_BEAN_NAME = "mongo.dao.OrderDao";

    public OrderDao() {
    }

    public OrderModel findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        List<OrderModel> orderModels = findByQuery(query);
        if (orderModels.size() > 0) {
            return orderModels.get(0);
        } else {
            return null;
        }
    }

    public Class getEntityClass() {
        return OrderModel.class;
    }
}
