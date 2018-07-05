package com.yuntongxun.weixin.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuntongxun.weixin.dto.GetMsgDTO;
import com.yuntongxun.weixin.dto.PostMsgDTO;
import com.yuntongxun.weixin.dto.TokenRspDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by liugang on 2018/7/4.
 */
@Slf4j
public class WeiXinSDK {

    public static JSONObject msg(PostMsgDTO msgDTO){
        String rspBody = WeiXinHttpUtils.msg(msgDTO.getUrl(), msgDTO.getPayload());
        log.info(rspBody);
        JSONObject jsonObject = JSONUtil.parseObj(rspBody);
        return jsonObject;
    }

    public static JSONObject getMsg(GetMsgDTO getMsgDTO){
        String rspBody = WeiXinHttpUtils.getMsg(getMsgDTO.getUrl());
        log.info(rspBody);
        JSONObject jsonObject = JSONUtil.parseObj(rspBody);
        return jsonObject;
    }

    public static TokenRspDTO getAccessToken(final String corpid, final String secret) throws ExecutionException {
        TokenRspDTO tokenRspDTO = new TokenRspDTO();
        //获取  accessToken
        String accessToken = CacheUtil.cache.get(secret, new Callable<String>() {
            public String call() throws Exception {
                String body = WeiXinHttpUtils.getToken(ConfigString.corpid, ConfigString.secret);
                log.info(body);
                ObjectMapper mapper = new ObjectMapper();
                JavaType jvt = mapper.getTypeFactory().constructParametricType(HashMap.class,String.class,String.class);
                Map<String,String> urMap = mapper.readValue(body, jvt);
                // 调用 接口获取 accessToken
                String accessToken = urMap.get("access_token");
                return accessToken;

            }
        });
        tokenRspDTO.setAccess_token(accessToken);
        tokenRspDTO.setErrcode("0");
        return tokenRspDTO;
    }

    public static TokenRspDTO getToken(final String corpid, final String secret) throws ExecutionException, IOException {
        TokenRspDTO tokenRspDTO = new TokenRspDTO();
        String body = WeiXinHttpUtils.getToken(corpid, secret);
        log.info(body);
        ObjectMapper mapper = new ObjectMapper();
        JavaType jvt = mapper.getTypeFactory().constructParametricType(HashMap.class,String.class,String.class);
        Map<String,String> urMap = mapper.readValue(body, jvt);
        // 调用 接口获取 accessToken
        String accessToken = urMap.get("access_token");
        tokenRspDTO.setAccess_token(accessToken);
        tokenRspDTO.setErrcode("0");
        return tokenRspDTO;
    }

    public static JSONObject userGet(final String userId, final String accessToken, String avatarAddr) throws ExecutionException {
        String body = WeiXinHttpUtils.userGet(accessToken, userId, avatarAddr);
        log.info(body);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        return jsonObject;
    }
}
