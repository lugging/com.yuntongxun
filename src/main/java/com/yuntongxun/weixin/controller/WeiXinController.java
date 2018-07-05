package com.yuntongxun.weixin.controller;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.json.JSONObject;
import com.yuntongxun.weixin.dto.*;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.WeiXinSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.ExecutionException;


/**
 * Created by liugang on 2018/7/4.
 */
@Slf4j
@RestController(value = "/weixin")
@CrossOrigin
public class WeiXinController {

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
    public JSONObject getMsg(@RequestBody GetMsgDTO getMsgDTO) throws UnsupportedEncodingException {
        String url = URLDecoder.decode(getMsgDTO.getUrl(), ConfigString.CHARSET);
        getMsgDTO.setUrl(url);
        JSONObject jsonObject = WeiXinSDK.getMsg(getMsgDTO);
        return jsonObject;
    }
}
