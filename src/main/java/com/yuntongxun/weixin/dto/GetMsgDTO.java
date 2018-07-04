package com.yuntongxun.weixin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by liugang on 2018/7/4.
 */
@Data
public class GetMsgDTO {

    @ApiModelProperty(value = "接口地址")
    private String url;

}
