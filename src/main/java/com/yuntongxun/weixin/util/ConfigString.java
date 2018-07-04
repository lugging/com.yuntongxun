package com.yuntongxun.weixin.util;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liugang on 2018/7/4.
 */
@Data
public abstract class ConfigString {
//    String baseUrl = "http://111.231.58.23";

    public static Map<String, String> appSecret = new HashMap<String, String>();

    static {
        appSecret.put("1000002", "lrdRCnG_z5zpu9rl4SJ5bfTHv5wvmgWBIYnoSCrXa04");
        appSecret.put("1000003", "Z06Nh4LDlezcV9ziynGzsSZQwQDk_wiw1lIaS33eeNI");
    }

    public static String baseUrl = "http://118.25.24.169";

    /**
     * 微信请求 Token
     */
    public static String token = "bf8614e2fd10471fa1f6c6df061ef672";

    public static String secret = "EYZeFtG-8ZKIH5KdchjeFNE5-DlvRxSzSJnean_6YwM";

    public static String corpid = "wl361d5a0876";

    /**
     * EncodingAESKey用于消息体的加密，是AES密钥的Base64编码
     */
    public static String aesKey = "bbHoe/rR2CjiqqE5bhtl6w==";

    /**
     * 获取Token 接口
     */
    public static String serverAccess = baseUrl + "/cgi-bin/gettoken";

    /**
     * 读取成员
     */
    public static String userGet = baseUrl + "/cgi-bin/user/get";
}