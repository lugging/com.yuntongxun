import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.DocumentEncryptUtil;
import com.yuntongxun.weixin.util.WeiXInEncryptUtil;
import org.dom4j.DocumentException;

/**
 * Created by liugang on 2018/7/5.
 */
public class AesEmailSmsUtil {

    /**
     msg_signature 09177d2861f3feee8c693b7d22ca064dbf65d385,
     timestamp 1530756539,
     nonce 1530846033,
     reqBody: <xml><Encrypt><![CDATA[QI89TWCoV0UexW452Bk5O99Yu5jM5qexONrBsbbQiTUC7zm1gN8K0TOF4Nl3ZmAEv3tzjnqhViCITZE4qth7d7Af/d66ReizxCWPe6hdcDheRRiBpKpXQLbORUYuMFho6bYXDszuBSwi9wR0gb7v6dk6DXQg3om0TMtrR66MTBLM/xJY2W9WLCpL2hkXvkMJu9nvazK6qMgacdBSEbPlHLoFJZSnd4EFOmwKLsRjE+xLMstxBB4BsJNaOjAqEmg8ZjOF5hBz3pmxUFWbAqKDQl6mInkYhGIKdmWtl0hY+8eEYSG8gH0Ej+m4Tw+82q9+U9mjyIeQdGSTuqTRVYyCQMAGPl+FdEQihV6jIiSIiflplWp0Lk0JdBtCREOtq7Opj7v3vqAVepYJrLgKReeDomGdSXdE8iwKIJKGjRO+7UH7hPqNrkYpCvJ+J9OplUz0Vc7RWOzeBNbyX4hoPEAVmg==]]></Encrypt></xml>   * @param args
     * @throws AesException
     */
    public static void main(String[] args) throws AesException, DocumentException {
        String token = "I6fNaLDiP6OuqYvsV4x0h6z7eWt";

        String aesKey = "qTCSSE5Ay9xlC2W9NuMsX5EIHvsTnDyHTkqjUfplHpm";

        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);
        // 解析出url上的参数值如下：
        // String sVerifyMsgSig = HttpUtils.ParseUrl("msg_signature");
        String sVerifyMsgSig = "09177d2861f3feee8c693b7d22ca064dbf65d385";
        // String sVerifyTimeStamp = HttpUtils.ParseUrl("timestamp");
        String sVerifyTimeStamp = "1530756539";
        // String sVerifyNonce = HttpUtils.ParseUrl("nonce");
        String sVerifyNonce = "1530846033";
        String msg = "<xml><Encrypt><![CDATA[QI89TWCoV0UexW452Bk5O99Yu5jM5qexONrBsbbQiTUC7zm1gN8K0TOF4Nl3ZmAEv3tzjnqhViCITZE4qth7d7Af/d66ReizxCWPe6hdcDheRRiBpKpXQLbORUYuMFho6bYXDszuBSwi9wR0gb7v6dk6DXQg3om0TMtrR66MTBLM/xJY2W9WLCpL2hkXvkMJu9nvazK6qMgacdBSEbPlHLoFJZSnd4EFOmwKLsRjE+xLMstxBB4BsJNaOjAqEmg8ZjOF5hBz3pmxUFWbAqKDQl6mInkYhGIKdmWtl0hY+8eEYSG8gH0Ej+m4Tw+82q9+U9mjyIeQdGSTuqTRVYyCQMAGPl+FdEQihV6jIiSIiflplWp0Lk0JdBtCREOtq7Opj7v3vqAVepYJrLgKReeDomGdSXdE8iwKIJKGjRO+7UH7hPqNrkYpCvJ+J9OplUz0Vc7RWOzeBNbyX4hoPEAVmg==]]></Encrypt></xml>";
        // 验证安全签名
        String encryptStr = DocumentEncryptUtil.parsetEncryptStr(msg);

        String result = WeiXInEncryptUtil.encrypt(token, sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, encryptStr, wxcpt);
        System.out.println(result);
    }

}
