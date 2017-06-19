package com.coral.biz.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by CCC on 2017/4/28.
 */
@Document
public class RoleModel implements Serializable {

    @org.springframework.data.annotation.Id
    private String id;
    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
