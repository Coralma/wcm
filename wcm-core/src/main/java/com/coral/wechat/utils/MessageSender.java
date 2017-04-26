package com.coral.wechat.utils;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by CCC on 2015/11/2.
 */
public class MessageSender {
    private final static String webUrl = "";

    private final static String appId = "wxd4d53acf0d07ec08";

    private final static String secret = "a00c8c04b1518e556c388c5c5140fb4f";

    //测试环境
    //private final static String webUrl = "http://192.168.200.11:7001/wechat"; //测试环境
    //private final static String appId = "wxa01f3e249c02aa0c";
    //private final static String secret = "01e284bca9a2eda148c62c481e22a554";

    //Staging环境
    //private final static String webUrl = "http://180.167.25.250";
    //private final static String appId = "wxd4d53acf0d07ec08";
    //private final static String secret = "a00c8c04b1518e556c388c5c5140fb4f";

    //生产环境
    //private final static String webUrl = "https://www.cccdrp.com/wechat";
    //private final static String appId = "wx61391e1f10ee735f";
    //private final static String secret = "06a104216c64d10969d22f6cdad93442";

    public static void main(String[] args) {

        //        sendTemplateMsg();
        createMenu();
//        sendTextMsg();
        //sendNewsMsg();
     /*   generateRCode();*/
    }

    public static void sendTextMsg() {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + getAccessToken();
        String params = "{\"touser\":\"owOwWuAfvwT185OEvNYlsKG5iE2Q\",\"msgtype\":\"text\",\"text\": {\"content\":\"Hello\"}}";
        /*String params = "{\"touser\":\"otbIiwiIjjuUUjlXbV4blnCBqtHA\",\"msgtype\":\"text\",\"text\": {\"content\":\"Hello\"}}";*/
        String returnValue = HttpConnectionUtils.httpPost(url, params);
        System.out.println(returnValue);
    }

    public static void sendTemplateMsg() {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + getAccessToken();
        String params = "{\"touser\":\"owOwWuAfvwT185OEvNYlsKG5iE2Q\",\"template_id\":\"3nDfKpaMMAie7oBCJKYOaYjiUREFImHTPKWUk9lpJe8\","
                + "\"data\":{\"first\": { \"value\":\"车辆信息：沪A12345\", \"color\":\"#000000\"}, \"keyword1\":{\"value\":\"车辆进场\",\"color\":\"#000000\"},\"keyword2\":{\"value\":\"即将开始维修\",\"color\":\"#000000\"},\"remark\":{\"value\":\"【DRP平台】上海徐汇修理厂：您的爱车已进厂，我们会跟踪并通知您爱车的维修进度。详询021-123456\",\"color\":\"#000000\"}}}";
        System.out.println(params);
        String returnValue = HttpConnectionUtils.httpPost(url, params);
        System.out.println(returnValue);
    }

    public static void sendTemplateMsg2() {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + getAccessToken();
        String params = "{\"touser\":\"otbIiwiIjjuUUjlXbV4blnCBqtHA\",\"template_id\":\"1B83N07cj_ihKsXEkygeDrUUUkuH2x6JEEw3Cv45dAE\","
                + "\"data\":{\"vehicleNo\": { \"value\":\"沪A12345\", \"color\":\"#173177\"}, \"status\":{\"value\":\"完成维修\",\"color\":\"#173177\"},\"remark\":{\"value\":\"【CCCIS汽修厂直修平台】大众徐汇特约维修店：您的爱车已经完成维修，请于2015/12/12到厂接您的爱车回家。感谢你的支持。详询021-54345678\",\"color\":\"#173177\"}}}";
        System.out.println(params);
        String returnValue = HttpConnectionUtils.httpPost(url, params);
        System.out.println(returnValue);
    }

    public static void createMenu() {
        String bindVehicle = webUrl + "/#bind/:account";
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + getAccessToken();
        String params = "{\n" + "\t \"button\":[\n" + "\t\t  {\t\n" + "\t\t      \"type\":\"click\",\n" + "\t\t      \"name\":\"维修进度\",\n" + "\t\t      \"key\":\"FIX_PROGRESS\"\n" + "\t\t  },\n"
                + "\t\t  {\"type\":\"click\",\n" + "\t\t      \"name\":\"爱车信息\",\n" + "\t\t      \"key\":\"VEHICLE_INFO\"\n" + "\t\t  },\n"
                + "\t\t  {\"type\":\"view\",\n" + "\t\t      \"name\":\"寻找爱车\",\n" + "\t\t      \"url\":\"" + bindVehicle + "\"\n" + "\t\t  }\n"
                + "\t  ]\n" + "}";

        System.out.println(params);
        String returnValue = HttpConnectionUtils.httpPost(url, params);
        System.out.println(returnValue);
    }

    /**
     * {
     * "touser":"otbIiwiIjjuUUjlXbV4blnCBqtHA",
     * "msgtype":"news",
     * "news":{
     * "articles": [
     * {
     * "title":"Happy Day",
     * "description":"Is Really A Happy Day",
     * "picurl":"http://180.167.25.252/wechat/img/one-main.jpg"
     * }]
     * }
     * }
     */
    public static void sendNewsMsg() {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
        String params = "{\n" + "    \"touser\":\"owOwWuAfvwT185OEvNYlsKG5iE2Q\",\n"
                + "    \"msgtype\":\"news\",\n"
                + "    \"news\":{\n"
                + "        \"articles\": [\n"
                + "         {\n"
                + "             \"title\":\"车辆信息\"\n"
                /*+ "             \"picurl\":\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png\"\n"*/
                + "         },  "
                + "         {\n"
                + "             \"title\":\"点击查看车辆列表\",\n"
                + "             \"picurl\":\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png\"\n"
                + "         }\n"
                + "         ]\n"
                + "    }\n"
                + "}";
        System.out.println(params);
        String returnValue = HttpConnectionUtils.httpPost(url, params);
        System.out.println(returnValue);
    }

    public static void generateRCode() {
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + getAccessToken();
        String params = "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 19705}}}";
        String returnValue = HttpConnectionUtils.httpPost(url, params);
        System.out.println(returnValue);
    }

    public static String getAccessToken() {
        String accessToken = HttpConnectionUtils.httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret);
        //        String drpAccessToken = HttpConnectionUtils.httpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx61391e1f10ee735f&secret=06a104216c64d10969d22f6cdad93442");
        Gson gson = new Gson();
        HashMap map = gson.fromJson(accessToken, HashMap.class);
        System.out.println(map.get("access_token"));
        return (String) map.get("access_token");
    }
}
