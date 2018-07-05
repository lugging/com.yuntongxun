package com.yuntongxun.weixin.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * Created by liugang on 2018/7/5.
 */
public class DocumentEncryptUtil {

    /**
     * 获取 请求xml报文中的 Encrypt字段值
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static String parsetEncryptStr(String xml) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        String encryptStr = root.selectSingleNode("/xml/Encrypt").getStringValue();
        return encryptStr;
    }

    /**
     * 根据指定的Xpath获取字段值
     * @param xml
     * @param xpath
     * @return
     * @throws DocumentException
     */
    public static String xpathEncryptStr(String xml, String xpath) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        String encryptStr = root.selectSingleNode(xpath).getStringValue();
        return encryptStr;
    }
}
