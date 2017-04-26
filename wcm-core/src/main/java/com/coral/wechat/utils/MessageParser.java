package com.coral.wechat.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wechat Message Parser
 */
public class MessageParser {

    private final static Logger LOG = LoggerFactory.getLogger(MessageParser.class);

    /**
     * Parse the XML from wechat
     */
    public static Map<String, String> parseXml(HttpServletRequest request) {
        // the parse result map
        Map<String, String> map = new HashMap<String, String>();

        // request input stream
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for (Element e : elements) {
                map.put(e.getName(), e.getText());
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        } finally {
            // release resource
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                }
            }
        }
        return map;
    }

}
