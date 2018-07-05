package com.yuntongxun.weixin.util;

/**
 * Created by liugang on 2018/7/5.
 */
public class RspMsg {

    /**
     * 通用响应XMl报文
     * @param msgContent
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static String rspXml(String msgContent, String msg_signature, String timestamp, String nonce){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<xml>" +
                "<Encrypt><![CDATA["+ msgContent+"]]></Encrypt>" +
                "<MsgSignature><![CDATA["+ msg_signature +"]]></MsgSignature>" +
                "<TimeStamp>"+ timestamp +"</TimeStamp>" +
                "<Nonce><![CDATA["+ nonce +"]]></Nonce>" +
                "</xml>");
        return stringBuilder.toString();
    }

    /**
     * 文本消息回复响应XML报文
     <xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>1348831860</CreateTime>
         <MsgType><![CDATA[text]]></MsgType>
         <Content><![CDATA[this is a test]]></Content>
     </xml>
     */
    public static String rspMsgTextXml(String toUser, String fromUser, String createTime, String content){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<xml>" +
                "<ToUserName><![CDATA["+toUser+"]]></ToUserName>" +
                "<FromUserName><![CDATA["+fromUser+"]]></FromUserName>" +
                "<CreateTime>"+createTime+"</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA["+content+"]]></Content>" +
                "</xml>");
        return stringBuilder.toString();
    }

    /**
     * <xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>1348831860</CreateTime>
         <MsgType><![CDATA[image]]></MsgType>
         <Image>
         <MediaId><![CDATA[media_id]]></MediaId>
         </Image>
     </xml>
     * @param toUser
     * @param fromUser
     * @param createTime
     * @param media_id
     * @return
     */
    public static String rspMsgImageXml(String toUser, String fromUser, String createTime, String media_id){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<xml>" +
                "<ToUserName><![CDATA["+toUser+"]]></ToUserName>" +
                "<FromUserName><![CDATA["+fromUser+"]]></FromUserName>" +
                "<CreateTime>"+createTime+"</CreateTime>" +
                "<MsgType><![CDATA[image]]></MsgType>" +
                "<Image>" +
                "<MediaId><![CDATA["+media_id+"]]></MediaId>" +
                "</Image>" +
                "</xml>");
        return stringBuilder.toString();
    }
}

