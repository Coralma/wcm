package com.coral.wechat.msg;

import com.coral.wechat.utils.FastJsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 文本信息
 * "touser":"OPENID",
 * "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
 * "url":"http://weixin.qq.com/download",
 * "data":{
 * "first": {
 * "value":"恭喜你购买成功！",
 * "color":"#173177"
 * },
 * "remark":{
 * "value":"欢迎再次购买！",
 * "color":"#173177"
 * }
 * }
 * }
 */
public class TemplateMsg extends BaseMsg {

    private String templateId;

    private String url;

    private Map<String, PairInfo> data = new HashMap<String, PairInfo>();

    public void addData(String name, String value, String color) {
        PairInfo node = new PairInfo();
        node.setValue(value);
        node.setColor(color);
        data.put(name, node);
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, PairInfo> getData() {
        return data;
    }

    public void setData(Map<String, PairInfo> data) {
        this.data = data;
    }

    @Override
    public String toJson() {
        String toUser = this.getToUserName();
        String templateId = this.getTemplateId();
        return FastJsonUtils.convertObject2JSONString(this).replace("toUserName", "touser").replace("templateId", "template_id");
    }

    class PairInfo {

        private String value;

        private String color;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
