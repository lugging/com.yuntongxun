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
 <xml>
 <Encrypt><![CDATA[msg_encrypt]]></Encrypt>
 <MsgSignature><![CDATA[msg_signature]]></MsgSignature>
 <TimeStamp>timestamp</TimeStamp>
 <Nonce><![CDATA[nonce]]></Nonce>
 </xml>
 1.msg_encrypt为经过加密的密文，算法参见附录
 2.MsgSignature为签名，算法参见附录
 3.TimeStamp为时间戳，Nonce为随机数，由单位自行生成
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xml")
public class ReciveWeiXinRspDTO {

    @XmlElement(name = "Nonce", required = true)
    private String nonce;

    @XmlElement(name = "TimeStamp", required = true)
    private String timeStamp;

    @XmlElement(name = "MsgSignature", required = true)
    private String msgSignature;

    @XmlElement(name = "Encrypt", required = true)
    private String encrypt;
}