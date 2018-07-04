package com.yuntongxun.weixin.controller;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yuntongxun.weixin.dto.*;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.WeiXinSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


/**
 * Created by liugang on 2018/7/4.
 */
@Slf4j
@RestController(value = "/weixin")
@CrossOrigin
public class WeiXinController {

    private static String CHARSET = "UTF-8";

    /**
     * 使用接收消息
     */
    @PostMapping(value = "/2")
    public String receive2(HttpServletRequest servletRequest,
                                      @RequestParam String msg_signature,
                                      @RequestParam String timestamp,
                                      @RequestParam String nonce) throws IOException, AesException {
        String reqBody = IoUtil.read(servletRequest.getInputStream(), CHARSET);
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(ConfigString.token, ConfigString.aesKey, ConfigString.corpid);

        log.info("msg_signature {}, timestamp {},  nonce {}, reqBody: {} ",msg_signature, timestamp, nonce, reqBody);

        /**
         * 解密函数
         * int DecryptMsg(const string &sMsgSignature, const string &sTimeStamp, const string &sNonce, const string &sPostData, string &sMsg);
         * sMsgSignature	是	从接收消息的URL中获取的msg_signature参数
         sTimeStamp	是	从接收消息的URL中获取的timestamp参数
         sNonce	是	从接收消息的URL中获取的nonce参数
         sPostData	是	从接收消息的URL中获取的整个post数据
         sMsg	是	用于返回解密后的msg，以xml组织
         */
        String reqBodyStr = wxBizMsgCrypt.DecryptMsg(msg_signature, timestamp, nonce, reqBody);

        log.info("解密后reqBodyStr {}", reqBodyStr);

        /**
         * 加密函数
         int EncryptMsg(const string &sReplyMsg, const string &sTimeStamp, const string &sNonce, string &sEncryptMsg);
         sReplyMsg	是	返回的消息体原文
         sTimeStamp	是	时间戳，调用方生成
         sNonce	是	随机数，调用方生成
         sEncryptMsg	是	用于返回的密文，以xml组织
         */
        // String replyMsg, String timeStamp, String nonce
        String rspBody = wxBizMsgCrypt.EncryptMsg(reqBodyStr, timestamp, nonce);
        return rspBody;
    }


    /**
     * 开启接收消息
     */
    @PostMapping(value = "/3")
    public String receive3(HttpServletRequest servletRequest,
                                       @RequestParam String msg_signature,
                                       @RequestParam String timestamp,
                                       @RequestParam String nonce,
                                       @RequestParam String echostr) throws IOException, AesException {
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(ConfigString.token, ConfigString.aesKey, ConfigString.corpid);
        log.info("msg_signature {}, timestamp {},  nonce {}, echostr {} ",msg_signature, timestamp, nonce, echostr);
        /**
         * 解密后的 echostr
         */
        String sEchoStr = wxBizMsgCrypt.VerifyURL(msg_signature, timestamp, nonce, echostr);
        return sEchoStr;
    }

    @GetMapping(value = "getToken")
    public TokenRspDTO getToken() throws IOException, ExecutionException {
        TokenRspDTO tokenRspDTO = WeiXinSDK.getAccessToken(ConfigString.corpid, ConfigString.secret);
        return tokenRspDTO;
    }

    @GetMapping(value = "getAppToken")
    public TokenRspDTO getAppToken(String agentId) throws IOException, ExecutionException {
        String secret = ConfigString.appSecret.get(agentId);
        TokenRspDTO tokenRspDTO = WeiXinSDK.getToken(ConfigString.corpid, secret);
        return tokenRspDTO;
    }

    @GetMapping(value = "getUser")
    public String getUser(String userId, String avatarAddr) throws IOException, ExecutionException {
        TokenRspDTO tokenRspDTO = WeiXinSDK.getAccessToken(ConfigString.corpid, ConfigString.secret);
        JSONObject jsonObject = WeiXinSDK.userGet(userId, tokenRspDTO.getAccess_token(), avatarAddr);
        return jsonObject.toJSONString(0);
    }

    @PostMapping(value = "postMsg")
    public JSONObject postMsg(@RequestBody PostMsgDTO msgDTO){
        String base64Msg = msgDTO.getPayload();
        byte[] msg = Base64Decoder.decode(base64Msg.getBytes());
        msgDTO.setPayload(new String(msg));
        JSONObject jsonObject = WeiXinSDK.msg(msgDTO);
        return jsonObject;
    }

    @PostMapping(value = "getMsg")
    public JSONObject getMsg(@RequestBody GetMsgDTO getMsgDTO){
        JSONObject jsonObject = WeiXinSDK.getMsg(getMsgDTO);
        return jsonObject;
    }
}
