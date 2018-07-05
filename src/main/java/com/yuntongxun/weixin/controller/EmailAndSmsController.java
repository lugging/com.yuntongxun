package com.yuntongxun.weixin.controller;

import cn.hutool.core.io.IoUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.DocumentEncryptUtil;
import com.yuntongxun.weixin.util.WeiXInEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by liugang on 2018/7/4.
 */
@RestController
@Slf4j
public class EmailAndSmsController {

    String token = "I6fNaLDiP6OuqYvsV4x0h6z7eWt";

    String aesKey = "qTCSSE5Ay9xlC2W9NuMsX5EIHvsTnDyHTkqjUfplHpm";

    /**
     * 开启短信邮件对接
     */
    @GetMapping(value = "email")
    public String receiveEmailGet(HttpServletRequest servletRequest,
                                  @RequestParam String msg_signature,
                                  @RequestParam String timestamp,
                                  @RequestParam String nonce,
                                  @RequestParam String echostr) throws IOException, AesException {
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);
        log.info("msg_signature {}, timestamp {},  nonce {}, echostr {} ",msg_signature, timestamp, nonce, echostr);
        String sEchoStr = wxBizMsgCrypt.VerifyURL(msg_signature, timestamp, nonce, echostr);
        log.info(sEchoStr);
        return sEchoStr;
    }

    /**
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param servletRequest
     * @return
     * @throws IOException
     * @throws AesException
     */
    @PostMapping(value = "email")
    public String receiveEmailPost(@RequestParam String msg_signature,
                                   @RequestParam String timestamp,
                                   @RequestParam String nonce,
                                   HttpServletRequest servletRequest) throws IOException, AesException, DocumentException {
        String reqBody = IoUtil.read(servletRequest.getInputStream(), ConfigString.CHARSET);
        log.info("msg_signature {}, timestamp {},  nonce {}, reqBody: {} ",msg_signature, timestamp, nonce, reqBody);
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);
        String encryptStr = DocumentEncryptUtil.parsetEncryptStr(reqBody);
        String result = WeiXInEncryptUtil.encrypt(token, msg_signature, timestamp, nonce, encryptStr, wxBizMsgCrypt);
        log.info("解密后reqBodyStr {}", result);
        return result;
    }
}
