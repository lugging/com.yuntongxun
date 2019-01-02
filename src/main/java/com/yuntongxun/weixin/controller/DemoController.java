package com.yuntongxun.weixin.controller;

import com.yuntongxun.weixin.domain.UserPO;
import com.yuntongxun.weixin.dto.UserRspDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liugang on 2018/7/3.
 */
@Api(tags = "sim")
@RestController
@Slf4j
@CrossOrigin
public class DemoController {

    @PostMapping(value = "/demo")
    public UserRspDTO demo(@RequestBody UserPO userPO){

        log.info(userPO.toString());

        return UserRspDTO.builder().errmsg("").status("1").build();
    }

    @PostMapping(value = "/demoxml", consumes = "application/xml", produces ="application/xml")
    public UserRspDTO demoxml(@RequestBody UserPO userPO){

        log.info(userPO.toString());

        UserRspDTO userRspDTO = UserRspDTO.builder().errmsg("").status("1").build();

        return userRspDTO;
    }
}
