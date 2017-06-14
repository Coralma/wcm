package com.coral.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by CCC on 2017/4/28.
 */
@Document
public class OrderModel implements Serializable {

    @org.springframework.data.annotation.Id
    private String id;
    private String fromAddress;
    private String toAddress;
    private BigDecimal price;
    private String payChannel;

}
