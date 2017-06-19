package com.coral.biz.test;

import com.coral.biz.dao.OrderDao;
import com.coral.biz.dao.UserDao;
import com.coral.biz.model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by ccc on 2017/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test.xml")
public class OrderDaoTest {

    @Resource(name = OrderDao.SPRING_BEAN_NAME)
    private OrderDao orderDao;

    @Test
    public void testOrder() {
        OrderModel orderModel = new OrderModel();
        orderModel.setFromAddress("shanghai");
        orderModel.setToAddress("nanjing");
        orderModel.setPayChannel("zhifubao");
        orderModel.setPrice(new BigDecimal("100"));
        orderDao.save(orderModel);
    }
}
