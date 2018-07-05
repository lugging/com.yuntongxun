package com.yuntongxun.weixin.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.RandomUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.DocumentEncryptUtil;
import com.yuntongxun.weixin.util.RspMsg;
import com.yuntongxun.weixin.util.WeiXInEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


/**
 * 接收消息服务器配置
 * Created by liugang on 2018/7/4.
 */
@Slf4j
@RestController
@CrossOrigin
public class MsgController {

    String token = "bf8614e2fd10471fa1f6c6df061ef672";

    String aesKey = "yODkYTsOTqaxDQoOFrRxpu9WyptBjYwDU4YV5pHCZZQ";

    static String keyStr = "值班";
    /**
     * 开启接收消息
     */
    @GetMapping(value = "/openMsg")
    public String receive3(@RequestParam String msg_signature,
                           @RequestParam String timestamp,
                           @RequestParam String nonce,
                           @RequestParam String echostr) throws IOException, AesException {
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);
        log.info("msg_signature {}, timestamp {},  nonce {}, echostr {} ",msg_signature, timestamp, nonce, echostr);
        String result = WeiXInEncryptUtil.encrypt(token, msg_signature, timestamp, nonce, echostr, wxBizMsgCrypt);
        log.info("解密后reqBodyStr {}", result);
        return result;
    }

    /**
     * 使用接收消息
     */
    @PostMapping(value = "/openMsg")
    public String receive2(HttpServletRequest servletRequest,
                           @RequestParam String msg_signature,
                           @RequestParam String timestamp,
                           @RequestParam String nonce) throws IOException, AesException, DocumentException {
        String reqBody = IoUtil.read(servletRequest.getInputStream(), ConfigString.CHARSET);
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);
        log.info("msg_signature {}, timestamp {},  nonce {}, reqBody: {} ",msg_signature, timestamp, nonce, reqBody);

        String encryptStr = DocumentEncryptUtil.parsetEncryptStr(reqBody);
        String unEncryptStr = WeiXInEncryptUtil.encrypt(token, msg_signature, timestamp, nonce, encryptStr, wxBizMsgCrypt);
        log.info("接收到应用消息:{}",unEncryptStr);

        String timeStamp = System.currentTimeMillis() + "";
        String rspNonce = RandomUtil.randomString(6).trim();
        String msgType = DocumentEncryptUtil.xpathEncryptStr(unEncryptStr, "/xml/MsgType");
        log.info("msgType : {}", msgType);

        String rspXml = reqBody;
        if(StringUtils.isNotEmpty(msgType) && msgType.equals("text")){ //普通文本消息
            String content = DocumentEncryptUtil.xpathEncryptStr(unEncryptStr, "/xml/Content");
            if(content.indexOf(keyStr) != -1){
                log.info("查询值班人员 {}", content);
                String users = appStr(ConfigString.workUsers);
                String ToUserName = DocumentEncryptUtil.xpathEncryptStr(unEncryptStr, "/xml/ToUserName");
                String FromUserName = DocumentEncryptUtil.xpathEncryptStr(unEncryptStr, "/xml/FromUserName");
                String rspEncrypt = RspMsg.rspMsgTextXml(ToUserName, FromUserName, timeStamp, users); //Encrypt 响应报文
                rspXml = wxBizMsgCrypt.EncryptMsg(rspEncrypt, timeStamp, rspNonce);
            } else {
                log.info("事件消息 ...");
            }
        }
        log.info("响应报文" + rspXml);
        return rspXml;
    }

    private String appStr(Map<String, String> users){
        StringBuilder sb = new StringBuilder();
        sb.append("当前值班人员: ");
        for(Map.Entry<String, String> entry : users.entrySet()){
            sb.append(entry.getValue()).append(" ");
        }
        return sb.toString();
    }

    private String appStr(Set<String> users){
        StringBuilder sb = new StringBuilder();
        sb.append("当前值班人员: ");
        for(String user : users){
            sb.append(user).append(" ");
        }
        return sb.toString();
    }

}
