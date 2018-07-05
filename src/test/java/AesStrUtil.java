import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * Created by liugang on 2018/7/5.
 */
public class AesStrUtil {



    /**
     *  msg_signature c2706e49545e2892019f24d6b8d410bfbfdcb340,
     *  timestamp 1530722080,
     *  nonce 1529991172,
     *  reqBody: nwHEHfj8CrczsLkle6b/luGCJ3CXWTE7VqjOL3ihMydmR+XyuBHQ0GCIVOLnzirCjy7Hb0orHNXIOyVKoMI9jjOuFak2XcwcTfHF7UqCkmZVs3IIhzLyMSPnj3PGbvWW
     * @param args
     * @throws AesException
     */
    public static void main(String[] args) throws AesException {

        String sToken = "2dkUADeBWdb6MX1K3AbLW";
        String sCorpID = "wl361d5a0876";
        String sEncodingAESKey = "2v3AbDQPDxFomFepY32qS7fr7jqRFpoiPOABWLT6cU4";

        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);

        String replyMsg = "1529991172";
        // 加密
        String nonce = "12312";
        String encrypt = wxcpt.encrypt(nonce, replyMsg);
        String timeStamp = System.currentTimeMillis() + "";
        // 生成安全签名
        if (timeStamp == "") {
            timeStamp = Long.toString(System.currentTimeMillis());
        }

        String sig = SHA1.getSHA1(sToken, timeStamp, nonce, encrypt);
        System.out.println(" signature  " + sig);
        System.out.println(" encryptMsg  " + encrypt);


        // 解析出url上的参数值如下：
        // String sVerifyMsgSig = HttpUtils.ParseUrl("msg_signature");
        String sVerifyMsgSig = "c2706e49545e2892019f24d6b8d410bfbfdcb340";
        // String sVerifyTimeStamp = HttpUtils.ParseUrl("timestamp");
        String sVerifyTimeStamp = "1530722080";
        // String sVerifyNonce = HttpUtils.ParseUrl("nonce");
        String sVerifyNonce = "1529991172";
        String msg = "nwHEHfj8CrczsLkle6b/luGCJ3CXWTE7VqjOL3ihMydmR+XyuBHQ0GCIVOLnzirCjy7Hb0orHNXIOyVKoMI9jjOuFak2XcwcTfHF7UqCkmZVs3IIhzLyMSPnj3PGbvWW";
        // 验证安全签名
        String signature = SHA1.getSHA1(sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, msg);

        // 和URL中的签名比较是否相等
        // System.out.println("第三方收到URL中的签名：" + msg_sign);
        // System.out.println("第三方校验签名：" + signature);
        if (!signature.equals(sVerifyMsgSig)) {
            System.out.println("第三方收到URL中的签名 "+ signature + "第三方校验签名 " + signature);
        } else {
            System.out.println("签名验证成功 !!!");
        }

        // 解密
        String result = wxcpt.decrypt(msg);
        System.out.println(result);
    }

}
