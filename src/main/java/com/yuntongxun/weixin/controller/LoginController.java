package com.yuntongxun.weixin.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yuntongxun.weixin.dto.*;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.WeiXInEncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * Created by liugang on 2018/7/4.
 */
@Slf4j
@RestController()
@CrossOrigin
public class LoginController {

    String token = "x63hvac3OP9c5ByTGEBE";

    String aesKey = "6aW5klhPLRuOCOC7BiUSyei8vjNWw4XHRnvQgDS0qWL";

    @GetMapping(value = "login")
    public String userGetLogin(HttpServletRequest servletRequest,
                                @RequestParam String msg_signature,
                                @RequestParam String timestamp,
                                @RequestParam String nonce) throws IOException, AesException {
        String reqBody = IoUtil.read(servletRequest.getInputStream(), ConfigString.CHARSET);
        log.info("msg_signature {}, timestamp {},  nonce {}, reqBody: {} ",msg_signature, timestamp, nonce, reqBody);

        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);
        String unEncryptStr = WeiXInEncryptUtil.encrypt(token, msg_signature, timestamp, nonce, reqBody, wxBizMsgCrypt);
        log.info("解密后reqBodyStr {}", unEncryptStr);
        String result = wxBizMsgCrypt.decrypt(reqBody);
        log.info(result);
        return result;
    }

    @PostMapping(value = "login")
    public String userLogin(HttpServletRequest servletRequest,
                                @RequestParam String msg_signature,
                                @RequestParam String timestamp,
                                @RequestParam String nonce) throws IOException, AesException {
        String reqBody = IoUtil.read(servletRequest.getInputStream(), ConfigString.CHARSET);
        log.info("msg_signature {}, timestamp {},  nonce {}, reqBody: {} ",msg_signature, timestamp, nonce, reqBody);

        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);
        String unEncryptStr = WeiXInEncryptUtil.encrypt(token, msg_signature, timestamp, nonce, reqBody, wxBizMsgCrypt);
        log.info("解密后reqBodyStr {}", unEncryptStr);
        UserRspDTO userRspDTO = UserRspDTO.builder().errmsg("").status("0").build();
        String rspJson = JSONUtil.toJsonStr(userRspDTO);
        String encrypt = wxBizMsgCrypt.encrypt(nonce, rspJson);
        return encrypt;
    }
}
