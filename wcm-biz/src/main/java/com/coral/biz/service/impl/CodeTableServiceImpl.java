package com.coral.biz.service.impl;

import com.coral.biz.dao.CodeTableDao;
import com.coral.biz.dao.UserDao;
import com.coral.biz.model.CodeTableModel;
import com.coral.biz.service.CodeTableService;
import com.coral.biz.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccc on 2017/6/16.
 */
@Service(CodeTableService.SPRING_BEAN_NAME)
public class CodeTableServiceImpl implements CodeTableService {

    @Resource(name = CodeTableDao.SPRING_BEAN_NAME)
    private CodeTableDao codeTableDao;

    @Override
    public List<CodeTableModel> loadCodeTable() {
        return codeTableDao.findAll();
    }

}
