package com.coral.servlet;

import com.coral.wechat.msg.BaseMsg;
import com.coral.wechat.msg.TextMsg;
import com.coral.wechat.msg.req.TextReqMsg;

/**
 * Created by CCC on 2017/4/26.
 */
public class WcmWeChatServlet extends AbstractWeChatServlet {

    @Override
    protected String getToken() {
        return "wcm";
    }

    @Override
    protected BaseMsg handleTextMsg(TextReqMsg event) {
        TextMsg text = new TextMsg();
        text.setToUserName(event.getFromUserName());
        text.setContent("如果您需要帮助，请拨打我们的服务电话110");
        return text;
    }
}
