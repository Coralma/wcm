package com.coral.servlet;

import com.coral.wechat.msg.BaseMsg;
import com.coral.wechat.msg.req.*;
import com.coral.wechat.utils.MessageParser;
import com.coral.wechat.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Abstract WeChat servlet for WeChat integration. The New business WeChat integration project should extend this servlet class.
 *
 * @author Coral
 */
public abstract class AbstractWeChatServlet extends HttpServlet {

    private final static Logger LOG = LoggerFactory.getLogger(AbstractWeChatServlet.class);

    /*protected ILogService getLog() {
        return (ILogService) SpringBeanUtils.getBean(ILogService.SPRING_BEAN_NAME);
    }*/

    /**
     * return a token which defined in WeChat console.
     */
    protected abstract String getToken();

    /**
     * WeChat binding validation.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // validate the request from wechat.
        if (isLegal(request)) {
            PrintWriter out = response.getWriter();
            out.print(request.getParameter("echostr"));
            out.close();
        } else {
            LOG.error("请求不符合微信验证规则，系统不予响应");
        }
    }

    /**
     * Check the input stream from WeChat. Serilize
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (!isLegal(request)) {
            LOG.error("请求不符合微信验证规则，默认不予响应");
            return;
        }
        // 处理消息
        String resp = processRequest(request);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(resp);
        out.close();
    }

    /**
     * 处理消息
     */
    private String processRequest(HttpServletRequest request) {
        String response = "";
        Map<String, String> reqMap = MessageParser.parseXml(request);

        String fromUserName = reqMap.get("FromUserName");
        String toUserName = reqMap.get("ToUserName");
        String msgType = reqMap.get("MsgType");

        //getLog().info(fromUserName, "WeChat Access Start", "Request: " + reqMap);

        try {
            BaseMsg msg = null;// 要发送的消息

            // 事件推送
            if (msgType.equals(ReqType.EVENT)) {
                // 事件类型
                String eventType = reqMap.get("Event");

                // 二维码事件
                String ticket = reqMap.get("Ticket");
                if (ticket != null) {
                    String eventKey = reqMap.get("EventKey");
                    QrCodeEvent event = new QrCodeEvent(eventKey, ticket);
                    buildBasicEvent(reqMap, event);
                    msg = handleQrCodeEvent(event);
                }
                // 订阅
                if (eventType.equals(EventType.SUBSCRIBE)) {
                    BaseEvent event = new BaseEvent();
                    buildBasicEvent(reqMap, event);
                    msg = handleSubscribe(event);
                }
                // 取消订阅
                else if (eventType.equals(EventType.UNSUBSCRIBE)) {
                    BaseEvent event = new BaseEvent();
                    buildBasicEvent(reqMap, event);
                    msg = handleUnsubscribe(event);
                }
                // 点击菜单拉取消息时的事件推送
                else if (eventType.equals(EventType.CLICK)) {
                    String eventKey = reqMap.get("EventKey");
                    MenuEvent event = new MenuEvent(eventKey);
                    buildBasicEvent(reqMap, event);
                    msg = handleMenuClickEvent(event);
                }
                // 点击菜单跳转链接时的事件推送
                else if (eventType.equals(EventType.VIEW)) {
                    String eventKey = reqMap.get("EventKey");
                    MenuEvent event = new MenuEvent(eventKey);
                    buildBasicEvent(reqMap, event);
                    msg = handleMenuViewEvent(event);
                }
                // 上报地理位置事件
                else if (eventType.equals(EventType.LOCATION)) {
                    double latitude = Double.parseDouble(reqMap.get("Latitude"));
                    double longitude = Double.parseDouble(reqMap.get("Longitude"));
                    double precision = Double.parseDouble(reqMap.get("Precision"));
                    LocationEvent event = new LocationEvent(latitude, longitude, precision);
                    buildBasicEvent(reqMap, event);
                    msg = handleLocationEvent(event);
                }

            } else {// 接受普通消息

                // 文本消息
                if (msgType.equals(ReqType.TEXT)) {
                    String content = reqMap.get("Content");
                    TextReqMsg textReqMsg = new TextReqMsg(content);
                    buildBasicReqMsg(reqMap, textReqMsg);
                    msg = handleTextMsg(textReqMsg);
                }
                // 图片消息
                else if (msgType.equals(ReqType.IMAGE)) {
                    String picUrl = reqMap.get("PicUrl");
                    String mediaId = reqMap.get("MediaId");
                    ImageReqMsg imageReqMsg = new ImageReqMsg(picUrl, mediaId);
                    buildBasicReqMsg(reqMap, imageReqMsg);
                    msg = handleImageMsg(imageReqMsg);
                }
                // 音频消息
                else if (msgType.equals(ReqType.VOICE)) {
                    String format = reqMap.get("Format");
                    String mediaId = reqMap.get("MediaId");
                    String recognition = reqMap.get("Recognition");
                    VoiceReqMsg voiceReqMsg = new VoiceReqMsg(mediaId, format, recognition);
                    buildBasicReqMsg(reqMap, voiceReqMsg);
                    msg = handleVoiceMsg(voiceReqMsg);
                }
                // 视频消息
                else if (msgType.equals(ReqType.VIDEO)) {
                    String thumbMediaId = reqMap.get("ThumbMediaId");
                    String mediaId = reqMap.get("MediaId");
                    VideoReqMsg videoReqMsg = new VideoReqMsg(mediaId, thumbMediaId);
                    buildBasicReqMsg(reqMap, videoReqMsg);
                    msg = handleVideoMsg(videoReqMsg);
                }
                // 地理位置消息
                else if (msgType.equals(ReqType.LOCATION)) {
                    double locationX = Double.parseDouble(reqMap.get("Location_X"));
                    double locationY = Double.parseDouble(reqMap.get("Location_Y"));
                    int scale = Integer.parseInt(reqMap.get("Scale"));
                    String label = reqMap.get("Label");
                    LocationReqMsg locationReqMsg = new LocationReqMsg(locationX, locationY, scale, label);
                    buildBasicReqMsg(reqMap, locationReqMsg);
                    msg = handleLocationMsg(locationReqMsg);
                }
                // 链接消息
                else if (msgType.equals(ReqType.LINK)) {
                    String title = reqMap.get("Title");
                    String description = reqMap.get("Description");
                    String url = reqMap.get("Url");
                    LinkReqMsg linkReqMsg = new LinkReqMsg(title, description, url);
                    buildBasicReqMsg(reqMap, linkReqMsg);
                    msg = handleLinkMsg(linkReqMsg);
                }

            }

            if (msg == null) {
                // 回复空串是微信的规定，代表不回复
                return "";
            }

            msg.setFromUserName(toUserName);
            msg.setToUserName(fromUserName);
            response = msg.toXml();

            //getLog().info(fromUserName, "WeChat Access Complete", "response:" + response);
        } catch (Exception ex) {
            //getLog().error(fromUserName, "WeChat Access Error", ex);
        }
        return response;
    }

    /**
     * 处理文本消息
     */
    protected BaseMsg handleTextMsg(TextReqMsg msg) throws Exception {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理图片消息
     */
    protected BaseMsg handleImageMsg(ImageReqMsg msg) throws Exception {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理语音消息
     */
    protected BaseMsg handleVoiceMsg(VoiceReqMsg msg) throws Exception {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理视频消息
     */
    protected BaseMsg handleVideoMsg(VideoReqMsg msg) throws Exception {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理地理位置消息
     */
    protected BaseMsg handleLocationMsg(LocationReqMsg msg) throws Exception {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理链接消息
     */
    protected BaseMsg handleLinkMsg(LinkReqMsg msg) throws Exception {
        return handleDefaultMsg(msg);
    }

    /**
     * 处理扫描带参数二维码事件
     */
    protected BaseMsg handleQrCodeEvent(QrCodeEvent event) throws Exception {
        return handleDefaultEvent(event);
    }

    /**
     * 处理上报地理位置事件
     */
    protected BaseMsg handleLocationEvent(LocationEvent event) throws Exception {
        return handleDefaultEvent(event);
    }

    /**
     * 处理点击菜单拉取消息时的事件推送
     */
    protected BaseMsg handleMenuClickEvent(MenuEvent event) throws Exception {
        return handleDefaultEvent(event);
    }

    /**
     * 处理点击菜单跳转链接时的事件推送
     */
    protected BaseMsg handleMenuViewEvent(MenuEvent event) throws Exception {
        return handleDefaultEvent(event);
    }

    /**
     * 处理订阅事件<br>
     * 默认不回复
     */
    protected BaseMsg handleSubscribe(BaseEvent event) throws Exception {
        return null;
    }

    /**
     * 处理取消订阅事件<br>
     * 默认不回复
     */
    protected BaseMsg handleUnsubscribe(BaseEvent event) throws Exception {
        return null;
    }

    /**
     * 处理消息的默认方式<br>
     * 如果不重写该方法，则默认不返回任何消息
     */
    protected BaseMsg handleDefaultMsg(BaseReqMsg msg) {
        return null;
    }

    /**
     * 设置处理事件的默认方式<br>
     * 如果不重写该方法，则默认不返回任何消息
     */
    protected BaseMsg handleDefaultEvent(BaseEvent event) {
        return null;
    }

    /**
     * 为事件普通消息对象添加基本参数<br>
     * 参数包括：MsgId、MsgType、FromUserName、ToUserName和CreateTime
     */
    private void buildBasicReqMsg(Map<String, String> reqMap, BaseReqMsg reqMsg) {
        addBasicReqParams(reqMap, reqMsg);
        reqMsg.setMsgId(reqMap.get("MsgId"));
    }

    /**
     * 为事件推送对象添加基本参数<br>
     * 参数包括：Event、MsgType、FromUserName、ToUserName和CreateTime
     */
    private void buildBasicEvent(Map<String, String> reqMap, BaseEvent event) {
        addBasicReqParams(reqMap, event);
        event.setEvent(reqMap.get("Event"));
    }

    /**
     * 为请求对象添加基本参数，包括MsgType、FromUserName、ToUserName和CreateTime<br>
     * 请求对象包括普通消息和事件推送
     */
    private void addBasicReqParams(Map<String, String> reqMap, BaseReq req) {
        req.setMsgType(reqMap.get("MsgType"));
        req.setFromUserName(reqMap.get("FromUserName"));
        req.setToUserName(reqMap.get("ToUserName"));
        req.setCreateTime(Long.parseLong(reqMap.get("CreateTime")));
    }

    /**
     * 判断请求是否来自微信服务器
     */
    private boolean isLegal(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        return SignUtil.checkSignature(getToken(), signature, timestamp, nonce);
    }
}
