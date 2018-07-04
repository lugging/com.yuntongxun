package com.yuntongxun.weixin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by liugang on 2018/7/4.
 */
@Data
public class PostMsgDTO {

    @ApiModelProperty(value = "接口地址")
    private String url;

    @ApiModelProperty(value = "接口报文")
    private String payload;

}
