package com.coral.biz.service;

import com.coral.biz.model.CodeTableModel;
import com.coral.biz.model.UserModel;

import java.util.List;

/**
 * Created by ccc on 2017/6/16.
 */
public interface CodeTableService {

    public final static String SPRING_BEAN_NAME = "service.CodeTableService";

    List<CodeTableModel> loadCodeTable();
}
