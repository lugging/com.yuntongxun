import cn.hutool.core.codec.Base64;
import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.DocumentEncryptUtil;
import com.yuntongxun.weixin.util.WeiXInEncryptUtil;
import com.yuntongxun.weixin.util.WeiXinHttpUtils;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import javax.crypto.SecretKey;
import java.util.Random;
import java.util.UUID;

/**
 * Created by liugang on 2018/7/4.
 */
public class Test {

    public static void main(String[] args) throws DocumentException, DecoderException, AesException {
//        System.out.println(UUID.randomUUID().toString().replace("-", ""));

        String token = "bf8614e2fd10471fa1f6c6df061ef672";

        String aesKey = "yODkYTsOTqaxDQoOFrRxpu9WyptBjYwDU4YV5pHCZZQ";

        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);

        String sRespData = "<xml><ToUserName><![CDATA[zhsngsan]]></ToUserName><FromUserName><![CDATA[lisi]]></FromUserName><CreateTime>1530784350627</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[张三 李四 王五]]></Content></xml>";

        String sReqTimeStamp = "1409659813";
        String sReqNonce = "123421";

        String sEncryptMsg = null;
        try{
            sEncryptMsg = wxcpt.EncryptMsg(sRespData, sReqTimeStamp, sReqNonce);
            System.out.println("after encrypt sEncrytMsg: " + sEncryptMsg);
            // 加密成功
            // TODO:
            // HttpUtils.SetResponse(sEncryptMsg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            // 加密失败
        }

//         解析出url上的参数值如下：
//         String sVerifyMsgSig = HttpUtils.ParseUrl("msg_signature");
        String sVerifyMsgSig = DocumentEncryptUtil.xpathEncryptStr(sEncryptMsg, "/xml/MsgSignature");
        // String sVerifyTimeStamp = HttpUtils.ParseUrl("timestamp");
        String sVerifyTimeStamp = DocumentEncryptUtil.xpathEncryptStr(sEncryptMsg, "/xml/TimeStamp");
        // String sVerifyNonce = HttpUtils.ParseUrl("nonce");
        String sVerifyNonce =  DocumentEncryptUtil.xpathEncryptStr(sEncryptMsg, "/xml/Nonce");

        String encryptStr = DocumentEncryptUtil.parsetEncryptStr(sEncryptMsg);
        String result = WeiXInEncryptUtil.encrypt(token, sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, encryptStr, wxcpt);
        System.out.println("rsp xxx" + result);


//        String hex = "776c63613034653731386266";
//
//        byte[] bs = Hex.decodeHex(hex.toCharArray());
//
//        System.out.println(new String(bs));
    }
}
