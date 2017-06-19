package com.coral.core.action;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ccc on 2017/6/15.
 */
public class BaseAction {

    public static final Map<String, String> SUCCESS = new HashMap<String, String>(){{
        put("result","1"); //  1 means SUCCESS
    }};
    public static final Map<String, String> FAILED =  new HashMap<String, String>(){{
        put("result","0"); //  1 means FAILED
    }};

    public static Map<String, String> getFailedMessage(String message) {
        Map<String, String> failedMap = Maps.newHashMap(FAILED);
        failedMap.put("message",message);
        return failedMap;
    }
}
