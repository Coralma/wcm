package com.coral.wechat.utils;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

/**
 * Created by CCC on 2015/12/30.
 */
public class ShotUrlUtils {

    private final static String SINA_URL = "http://api.t.sina.com.cn/short_url/shorten.json?source=1681459862&url_long=";
    private static Gson gson = new Gson();

    public static String generateShotURL(String longURL) {
        String postURL = SINA_URL + longURL;
        String returnJson = HttpConnectionUtils.httpGet(postURL);
        List<Map<String, String>> mapList = gson.fromJson(returnJson, List.class);
        return mapList.get(0).get("url_short");
    }

    public static void main(String[] args) {
        String longURL = "http://www.cccdrp.cn/bind/owOwWuAfvwT185OEvNYlsKG5iE2Q";
        System.out.println(generateShotURL(longURL));
    }
}
