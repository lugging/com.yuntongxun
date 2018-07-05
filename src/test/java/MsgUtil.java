import cn.hutool.core.util.RandomUtil;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.SHA1;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.DocumentEncryptUtil;
import com.yuntongxun.weixin.util.RspMsg;
import com.yuntongxun.weixin.util.WeiXInEncryptUtil;
import org.dom4j.DocumentException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by liugang on 2018/7/5.
 */
public class MsgUtil {

    /**
     <xml>
     <Encrypt><![CDATA[u1fkDjYXaC/eu/hE6poeZ1/2f5f5maD32S4E+KBUX5ou+j/Xulyxp5W7n57dmnO20nVoC5XPht5izVNmmbbbMGrnQ/0nNfu4z0KWWhiriqcjp/+pzmxYZdPkNe8kahsLAgz3JGbYyuPuQ+q1FuTp46ZNzBH/KBSy1ydJAW3DXSyt7pFPKE3pR4xCoGik5wcVhejMFrqCfy70G0ssJi+lqCaiSudaiqgOqh8PBxY0JEzdGxnBUDWAgw8jK+jFtuAutE3cjM/gIxieRDpAQZF0Fu3c+p6n4i8+yU+Q+BExAB2v6B242P7eLv9jSgG2b28uT4gyZmM4t49scbQkxqWG4Zq3wMyaYIuuGPaORnhFvZpOexpIVHYk56y+paDUEwZSrJtcf8rcqSTS3enDTxMcD3mYFvHvspo1fAg3LFAuBPQ=]]></Encrypt>
     <MsgSignature><![CDATA[1c04e0c71e422c08831a252f21f2ae36e6a94d3f]]></MsgSignature>
     <TimeStamp>1530781652230</TimeStamp>
     <Nonce><![CDATA[z29664]]></Nonce>
     </xml>
     * @throws AesException
     */
    public static void main(String[] args) throws AesException, DocumentException, UnsupportedEncodingException {
        String token = "bf8614e2fd10471fa1f6c6df061ef672";

        String aesKey = "yODkYTsOTqaxDQoOFrRxpu9WyptBjYwDU4YV5pHCZZQ";

        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, aesKey, ConfigString.corpid);

        String ToUserName = "zhsngsan";
        String FromUserName = "lisi";
        String sReqTimeStamp = System.currentTimeMillis() + "";
        String sReqNonce = RandomUtil.randomString(6);
        String sRespData = RspMsg.rspMsgTextXml(ToUserName, FromUserName, sReqTimeStamp, "张三 李四 王五"); //Encrypt 响应报文

        System.out.println(sRespData);
        String rspXml = wxcpt.EncryptMsg(sRespData, sReqTimeStamp, sReqNonce);

        System.out.println(rspXml);

//         解析出url上的参数值如下：
//         String sVerifyMsgSig = HttpUtils.ParseUrl("msg_signature");
        String sVerifyMsgSig = DocumentEncryptUtil.xpathEncryptStr(rspXml, "/xml/MsgSignature");
        // String sVerifyTimeStamp = HttpUtils.ParseUrl("timestamp");
        String sVerifyTimeStamp = DocumentEncryptUtil.xpathEncryptStr(rspXml, "/xml/TimeStamp");
        // String sVerifyNonce = HttpUtils.ParseUrl("nonce");
        String sVerifyNonce =  DocumentEncryptUtil.xpathEncryptStr(rspXml, "/xml/Nonce");

        String encryptStr = DocumentEncryptUtil.parsetEncryptStr(rspXml);
        String result = WeiXInEncryptUtil.encrypt(token, sVerifyMsgSig, sVerifyTimeStamp, sVerifyNonce, encryptStr, wxcpt);
        System.out.println(result);
    }

}
