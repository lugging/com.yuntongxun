import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.DocumentEncryptUtil;
import com.yuntongxun.weixin.util.WeiXInEncryptUtil;
import org.dom4j.DocumentException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by liugang on 2018/7/5.
 */
public class MsgUtil {

    /**
     msg_signature 64800be58195c10c741ecdfadd4ce589984e5714,
     timestamp 1530770469,
     nonce 1530788570,
     reqBody: <xml><ToUserName><![CDATA[wl361d5a0876]]></ToUserName><Encrypt><![CDATA[C2MBWYOqlJamMuUEwFyp94FuZ3bjAQWb48G9ZsSTzFBZx6a9f9H1ggN5zU7bo5qn/z7h4sErLeZNHMMhP0whoky1XBIQXmbrT48WbLL6S7iF0yR1MlafXebT2c/q1zVCHyvatgIiQIjDr6VfTPxvooDT6pLxmJ5Ff6cVXoqAOmHa8zIxS0haaScrdRPlXCsh3QX4H6BO+Arr7VMmNmNrEy7MEUfJx9CzaZcIF9zoaAm94o59L+rxiUt+y4oxCxEOaojgKDJruPfod3ntsYHDpBmqEM6UmPqTtpSC6cLstnYpaHvokR9U2y/qZQuXkcMFkHKdwJZifWR+D362KVZ16avjJ6FjXqn8MpmUY/HSSPijFeKaxdHCaQiti22EbuQtusc/Dz0KZLyndOlXajQCesnMLQELadCMhZVuh7mpBqI=]]></Encrypt><AgentID><![CDATA[1000003]]></AgentID></xml>


     * @throws AesException
     */
    public static void main(String[] args) throws AesException, DocumentException, UnsupportedEncodingException {
        String token = "bf8614e2fd10471fa1f6c6df061ef672";

        String aesKey = "yODkYTsOTqaxDQoOFrRxpu9WyptBjYwDU4YV5pHCZZQ";

        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);
        // 解析出url上的参数值如下：
        // String sVerifyMsgSig = HttpUtils.ParseUrl("msg_signature");
        String sVerifyMsgSig = "64800be58195c10c741ecdfadd4ce589984e5714";
        // String sVerifyTimeStamp = HttpUtils.ParseUrl("timestamp");
        String sVerifyTimeStamp = "1530770469";
        // String sVerifyNonce = HttpUtils.ParseUrl("nonce");
        String sVerifyNonce = "1530788570";
        String msg = "<xml><ToUserName><![CDATA[wl361d5a0876]]></ToUserName><Encrypt><![CDATA[C2MBWYOqlJamMuUEwFyp94FuZ3bjAQWb48G9ZsSTzFBZx6a9f9H1ggN5zU7bo5qn/z7h4sErLeZNHMMhP0whoky1XBIQXmbrT48WbLL6S7iF0yR1MlafXebT2c/q1zVCHyvatgIiQIjDr6VfTPxvooDT6pLxmJ5Ff6cVXoqAOmHa8zIxS0haaScrdRPlXCsh3QX4H6BO+Arr7VMmNmNrEy7MEUfJx9CzaZcIF9zoaAm94o59L+rxiUt+y4oxCxEOaojgKDJruPfod3ntsYHDpBmqEM6UmPqTtpSC6cLstnYpaHvokR9U2y/qZQuXkcMFkHKdwJZifWR+D362KVZ16avjJ6FjXqn8MpmUY/HSSPijFeKaxdHCaQiti22EbuQtusc/Dz0KZLyndOlXajQCesnMLQELadCMhZVuh7mpBqI=]]></Encrypt><AgentID><![CDATA[1000003]]></AgentID></xml>";
        String encryptStr = DocumentEncryptUtil.parsetEncryptStr(msg);
        String result = WeiXInEncryptUtil.encrypt(token, sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, encryptStr, wxcpt);
        System.out.println(result);
    }

}
