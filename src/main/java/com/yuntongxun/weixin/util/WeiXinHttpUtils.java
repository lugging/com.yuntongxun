package com.yuntongxun.weixin.util;

import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liugang on 2018/7/4.
 */
public class WeiXinHttpUtils {
    /**
     * 通用post接口
     * @param url
     * @param payload
     * @return
     */
    public static String msg(final String url, final String payload){
        String rspBody = HttpUtil.post(url, payload);
        return rspBody;
    }

    /**
     * 通用get接口
     * @param url
     * @return
     */
    public static String getMsg(final String url){
        String rspBody = HttpUtil.get(url);
        return rspBody;
    }

    public static String getToken(final String corpid, final String secret){
        Map<String, Object> param = new HashMap();
        param.put("corpid", corpid);
        param.put("corpsecret", secret);
        String rspBody = HttpUtil.get(ConfigString.serverAccess, param);
        return rspBody;
    }

    /**
     * @param access_token
     * @param userid
     * @param avatar_addr
     * @return
     */
    public static String userGet(String access_token, String userid, String avatar_addr){
        Map<String, Object> param = new HashMap();
        param.put("access_token", access_token);
        param.put("userid", userid);
        param.put("avatar_addr", avatar_addr);
        String rspBody = HttpUtil.get(ConfigString.userGet, param);
        return rspBody;
    }
}
