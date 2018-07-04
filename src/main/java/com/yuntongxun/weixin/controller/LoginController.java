package com.yuntongxun.weixin.controller;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.io.IoUtil;
import cn.hutool.json.JSONObject;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yuntongxun.weixin.domain.UserPO;
import com.yuntongxun.weixin.dto.*;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.WeiXinSDK;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.ExecutionException;


/**
 * Created by liugang on 2018/7/4.
 */
@Slf4j
@RestController(value = "/user")
@CrossOrigin
public class LoginController {

    private static String CHARSET = "UTF-8";

    @PostMapping(value = "login")
    public UserRspDTO userLogin(@RequestBody UserPO userPO){

        log.info(userPO.toString());

        UserRspDTO userRspDTO = UserRspDTO.builder().errmsg("").status("0").build();
        return userRspDTO;
    }
}
