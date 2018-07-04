package com.yuntongxun.weixin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by liugang on 2018/7/3.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class UserRspDTO {

    @XmlElement(name = "status", required = true)
    private String status;

    @XmlElement(name = "errmsg", required = true)
    private String errmsg;
}
