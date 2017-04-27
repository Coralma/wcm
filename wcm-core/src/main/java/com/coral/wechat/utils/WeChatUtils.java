package com.coral.wechat.utils;

import com.coral.wechat.constants.WeChatConstants;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by CCC on 2016/10/26.
 */
public class WeChatUtils {

    private static final Logger LOG = LoggerFactory.getLogger(WeChatUtils.class);
    private static Gson gson = new Gson();

    private static LoadingCache<String, String> accessTokenCache = CacheBuilder.newBuilder().maximumSize(1).refreshAfterWrite(90, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
        @Override
        public String load(String s) throws Exception {
            String corpId = WeChatConstants.KEY_WECHAT_CORPID;
            String secret = WeChatConstants.KEY_WECHAT_SECRET;
            String tokenJson = HttpConnectionUtils.httpGet(StringUtils.replaceStringParam(WeChatConstants.ACCESS_TOKEN_URL, corpId, secret));
            LOG.info("token is:" + tokenJson);
            HashMap<String, String> map = gson.fromJson(tokenJson, HashMap.class);
            String accessToken = map.get(s);
            LOG.info("Take Access Token with appId: " + corpId + ", secret: " + secret + ", tokenJson: " + tokenJson + ", return token is:" + accessToken);
            return map.get(s);
        }
    });

    public static synchronized String getAccessToken() {
        String accessToken = null;
        try {
            accessToken = accessTokenCache.get(WeChatConstants.ACCESS_TOKEN);
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
        }
        return accessToken;
    }
}
