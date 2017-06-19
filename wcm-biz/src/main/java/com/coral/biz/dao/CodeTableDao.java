package com.coral.biz.dao;

import com.coral.base.mongo.MBaseDao;
import com.coral.biz.model.CodeTableModel;
import com.coral.biz.model.OrderModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ccc on 2017/6/14.
 */
@Repository(CodeTableDao.SPRING_BEAN_NAME)
public class CodeTableDao extends MBaseDao<CodeTableModel> {

    public static final String SPRING_BEAN_NAME = "mongo.dao.CodeTableDao";

    public CodeTableDao() {
    }

    public CodeTableModel findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        List<CodeTableModel> codeTableModels = findByQuery(query);
        if (codeTableModels.size() > 0) {
            return codeTableModels.get(0);
        } else {
            return null;
        }
    }

    public Class getEntityClass() {
        return CodeTableModel.class;
    }

}
