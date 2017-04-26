package com.coral.wechat.msg;

import com.cccis.wechat.util.MessageBuilder;

/**
 * Created by CCC on 2015/12/25.
 */
public class TransferMsg extends BaseMsg {

    @Override
    public String toXml() {
        /*MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("MsgType", RespType.TRANSFER);
        mb.surroundWith("xml");
        return mb.toString();*/

        MessageBuilder mb = new MessageBuilder(super.toXml());
        mb.addData("MsgType", RespType.TRANSFER);
        mb.addSubData("TransInfo", "KfAccount", "coral@CCC-DRP");
        mb.surroundWith("xml");
        return mb.toString();
    }

    @Override
    public String toString() {
        return "TransferCustomerMsg []";
    }


}
