import cn.hutool.core.codec.Base64;
import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.yuntongxun.weixin.util.ConfigString;
import com.yuntongxun.weixin.util.WeiXinHttpUtils;
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

    public static void main(String[] args) throws DocumentException {
//        System.out.println(UUID.randomUUID().toString().replace("-", ""));

        /**
         * 随机生成密钥
         */
//        byte[] privateKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
//
//        String privateKeyBase64 = Base64.encode(privateKey);
//
//        System.out.println(privateKeyBase64);
//        String body = WeiXinHttpUtils.getToken(ConfigString.corpid, ConfigString.secret);
//
//        System.out.println(body);
//

        String xml = "<xml> \n" +
                "   <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "   <AgentID><![CDATA[toAgentID]]></AgentID>\n" +
                "   <Encrypt><![CDATA[msg_encrypt]]></Encrypt>\n" +
                "</xml>";

        Document document = DocumentHelper.parseText(xml);

        String base64Msg = "ewogICAidG91c2VyIiA6ICJsaXVnYW5nIiwKICAgIm1zZ3R5cGUiIDogInRleHQiLAogICAiYWdlbnRpZCIgOiAxMDAwMDAyLAogICAidGV4dCIgOiB7CiAgICAgICAiY29udGVudCIgOiAi5L2g55qE5b+r6YCS5bey5Yiw77yM6K+35pC65bim5bel5Y2h5YmN5b6A6YKu5Lu25Lit5b+D6aKG5Y+W44CCPGJyPuWHuuWPkeWJjeWPr+afpeecizxhIGhyZWY9XCJodHRwOi8vMTE4LjI1LjI0LjE2OVwiPumCruS7tuS4reW/g+inhumikeWunuWGtTwvYT7vvIzogarmmI7pgb/lvIDmjpLpmJ/jgIIiCiAgIH0KfQ==";
        byte[] msg = Base64Decoder.decode(base64Msg.getBytes());
        System.out.println(new String(msg));
    }
}
