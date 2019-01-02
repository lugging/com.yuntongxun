import com.yuntongxun.weixin.dto.PostMsgDTO;
import lombok.Data;
import ognl.Ognl;
import ognl.OgnlException;

/**
 * Created by liugang on 2018/7/17.
 */
public class OgnlTest {

    public static void main(String[] args) {

        PostMsgDTO postMsgDTO = new PostMsgDTO();
        postMsgDTO.setPayload("value payload");
        postMsgDTO.setUrl("https://www.baidu.com");

        OgnlPo ognlPo = new OgnlPo();
        ognlPo.setPostMsgDTO(postMsgDTO);
        ognlPo.setName("zhangsan");

        try {
            Object value = Ognl.getValue("postMsgDTO.url", ognlPo);

            System.out.println(value);

            Ognl.setValue("postMsgDTO.url", ognlPo, "https://www.taobao.com");

            System.out.println(ognlPo.toString());

        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }

    @Data
    static class OgnlPo{
        private String name;

        private PostMsgDTO postMsgDTO;
    }
}
