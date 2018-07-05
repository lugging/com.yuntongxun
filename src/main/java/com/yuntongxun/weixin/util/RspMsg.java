package com.yuntongxun.weixin.util;

/**
 * Created by liugang on 2018/7/5.
 */
public class RspMsg {

    public static String rspXml(String msgContent, String msg_signature, String timestamp, String nonce){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<xml>\n" +
                "   <Encrypt><![CDATA["+ msgContent+"]]></Encrypt>\n" +
                "   <MsgSignature><![CDATA["+ msg_signature +"]]></MsgSignature>\n" +
                "   <TimeStamp>"+ timestamp +"</TimeStamp>\n" +
                "   <Nonce><![CDATA[ "+ nonce +" ]]></Nonce>\n" +
                "</xml>");
        return stringBuilder.toString();
    }


    /**
     * <xml>
     <ToUserName><![CDATA[toUser]]></ToUserName>
     <FromUserName><![CDATA[fromUser]]></FromUserName>
     <CreateTime>1348831860</CreateTime>
     <MsgType><![CDATA[text]]></MsgType>
     <Content><![CDATA[this is a test]]></Content>
     </xml>
     */

    public static String rspMsgTextXml(String toUser, String fromUser, String createTime, String content){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<xml>\n" +
                "   <ToUserName><![CDATA["+toUser+"]]></ToUserName>\n" +
                "   <FromUserName><![CDATA["+fromUser+"]]></FromUserName> \n" +
                "   <CreateTime>"+createTime+"</CreateTime>\n" +
                "   <MsgType><![CDATA[text]]></MsgType>\n" +
                "   <Content><![CDATA["+content+"]]></Content>\n" +
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
        stringBuilder.append("<xml>\n" +
                "   <ToUserName><![CDATA["+toUser+"]]></ToUserName>\n" +
                "   <FromUserName><![CDATA["+fromUser+"]]></FromUserName>\n" +
                "   <CreateTime>"+createTime+"</CreateTime>\n" +
                "   <MsgType><![CDATA[image]]></MsgType>\n" +
                "   <Image>\n" +
                "       <MediaId><![CDATA["+media_id+"]]></MediaId>\n" +
                "   </Image>\n" +
                "</xml>");
        return stringBuilder.toString();
    }
}

