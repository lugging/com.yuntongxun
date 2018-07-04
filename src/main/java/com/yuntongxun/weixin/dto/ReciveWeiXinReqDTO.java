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
 * <xml>
 <ToUserName><![CDATA[toUser]]</ToUserName>
 <AgentID><![CDATA[toAgentID]]</AgentID>
 <Encrypt><![CDATA[msg_encrypt]]</Encrypt>
 </xml>
 1.msg_encrypt为经过加密的密文
 2.AgentID为接收的应用id，可在应用的设置页面获取
 3.ToUserName为政务微信的CorpID
 * Created by liugang on 2018/7/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class ReciveWeiXinReqDTO {

    @XmlElement(name = "ToUserName", required = true)
    private String toUserName;

    @XmlElement(name = "AgentID", required = true)
    private String agentID;

    @XmlElement(name = "Encrypt", required = true)
    private String encrypt;
}