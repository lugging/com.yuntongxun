package com.yuntongxun.weixin.util;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * Created by liugang on 2018/7/5.
 */
public class WeiXInEncryptUtil {

    /**
     * 解密
     * @param token
     * @param sVerifyMsgSig
     * @param sVerifyTimeStamp
     * @param sVerifyNonce
     * @param encryptStr
     * @param msgCrypt
     * @return
     * @throws AesException
     */
    public static String encrypt(String token, String sVerifyMsgSig, String sVerifyTimeStamp, String sVerifyNonce, String encryptStr, WXBizMsgCrypt msgCrypt) throws AesException {
        String signature = SHA1.getSHA1(token, sVerifyTimeStamp, sVerifyNonce, encryptStr);
        if (!signature.equals(sVerifyMsgSig)) {
            System.out.println("第三方收到URL中的签名 "+ sVerifyMsgSig + "第三方校验签名 " + signature);
        } else {
            System.out.println("签名验证成功 !!!");
        }
        // 解密
        String result = msgCrypt.decryptNoCorpId(encryptStr);
        return result;
    }

    /**
     * 生成签名
     * @param token
     * @param timeStamp
     * @param nonce
     * @param encrypt
     * @return
     * @throws AesException
     */
    public static String generSig(String token, String timeStamp, String nonce, String encrypt) throws AesException {
        String signature = SHA1.getSHA1(token, timeStamp, nonce, encrypt);
        return signature;
    }
}
