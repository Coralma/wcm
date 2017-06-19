package com.coral.biz.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by CCC on 2017/4/28.
 */
@Document
public class CodeTableModel implements Serializable {

    @org.springframework.data.annotation.Id
    private String id;
    private String codeId;
    private String key;
    private String value;

    public CodeTableModel(String codeId, String key, String value) {
        this.codeId = codeId;
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
