package com.coral.biz.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by CCC on 2017/4/28.
 */
@Document
public class UserModel implements Serializable {

    @org.springframework.data.annotation.Id
    private String id;
    private String username;
    private String realname;
    private String password;
    private String roleId;
    private String status;
    private String mobile;
    private String email;
    private Date doeDate;

    public UserModel() {

    }

    public UserModel(String username, String realname, String password, String roleId, String status, String mobile, String email, Date doeDate) {
        this.username = username;
        this.realname = realname;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
        this.mobile = mobile;
        this.email = email;
        this.doeDate = doeDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getDoeDate() {
        return doeDate;
    }

    public void setDoeDate(Date doeDate) {
        this.doeDate = doeDate;
    }
}
