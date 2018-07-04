package com.yuntongxun.weixin.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
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
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class UserPO {

    @XmlElement(name = "username", required = true)
    @JsonProperty(value = "username")
    private String userName;

    @XmlElement(name = "password", required = true)
    @JsonProperty(value = "password")
    private String passWord;

}
