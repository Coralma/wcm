package com.coral.wechat.utils;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;

/**
 * Created by CCC on 2015/11/11.
 */
public class EncryptUtils {
    static Charset CHARSET = Charset.forName("utf-8");

    public static void main(String[] args) {
        Base64 base64 = new Base64();
        String token = "drp";
        String appId = "wx61391e1f10ee735f";
        String data = "vNU7h/4UMUulXdduv2lN/ePYH8x3BE/vWsrS1bftg5g+HlnfFrT8RzNgAIgZCL8EvZDpl2g37RoJdv9LQUztMejO9o4mIpebE+4kIQeYZNq9txLNE3kSHkGC9XvsScJbsD02pZ1wfAi8j4Qorh5u7EqaDUXLjJP5vvIwiQl1uNxQZJWBXBGZxC3Xc03GoRa5ETA4jkL1O5ZRmDi4mPJ5ENAvjDWAhldQnzV5Iuos3vum6wOQwc+cjFILZKAYxK0ht41AygPyg0QaqRWYHFKGWMwwFlIYjHf1EH5fbGE+w+GwgGZJ5EJinoNPWymQt/EzjAI48IYG5nuSqXRtgmKgD8Ti+ld30b3hdpQBrgh4Y9NU5hHsit+JdcngtLKCo2oIDQ0F24UOYeTujx7tqkHXHLNZEm00PwdWCt3XfLxX570=";
        byte[] rs = Base64.decodeBase64(data);
        String s = new String(rs, CHARSET);
        System.out.println(s);
    }
}
