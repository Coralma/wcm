package com.coral.wechat.msg.req;

import com.coral.wechat.utils.MessageBuilder;

public class BaseEvent extends BaseReq {

    private String event;

    public BaseEvent() {
        setMsgType(ReqType.EVENT);
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toXml() {
        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("MsgType", ReqType.EVENT);
        mb.addData("Event", event);
        return mb.toString();
    }

}
