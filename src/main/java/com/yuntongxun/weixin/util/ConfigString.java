package com.yuntongxun.weixin.util;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liugang on 2018/7/4.
 */
@Data
public abstract class ConfigString {

    public static String CHARSET = "UTF-8";

    public static String secret = "SD3n37NtlsZdPjdVP_7ZPNR06FaoyeHw9jnRbImj6h";

    public static Map<String, String> workUsers = new HashMap();
    static {
        workUsers.put("liugang", "刘刚");
        workUsers.put("zhangsan", "张三");
        workUsers.put("caizhiming", "蔡志明");
    }

    public static Map<String, String> appSecret = new HashMap<String, String>();

    static {
        appSecret.put("1000005", "x6HrnPnxqTkYH-Etfvm61HtJr8iiEABV-t-_6vP-_N4");
        appSecret.put("1000002", "YZmCfmbjCgtO8_4Qwp6YBGQKqdRiX9wQ4VRoEPN0uec");
        appSecret.put("1000003", "F_E_C5FQRaa24LQu_oxNZ_KE7Vpp0x6_ipMoJJONCEI");
        appSecret.put("1000004", "oMQGrV5txr7mpP2nuyfeTZFy7f_rAjGCPLRuT4ttjbs");
    }

    public static String baseUrl = "http://";

    /**
     * 容联考核
     */
    public static String corpid = "wlb633b76e17";

    /**
     * 获取Token 接口
     */
    public static String serverAccess = baseUrl + "/cgi-bin/gettoken";

    /**
     * 读取成员
     */
    public static String userGet = baseUrl + "/cgi-bin/user/get";
}