import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.*;
import com.yuntongxun.weixin.dto.PostMsgDTO;
import lombok.Data;

/**
 * Created by liugang on 2018/7/17.
 */
public class JpathTest {

    public static void main(String[] args) throws JsonProcessingException {

        PostMsgDTO postMsgDTO = new PostMsgDTO();
        postMsgDTO.setPayload("value payload");
        postMsgDTO.setUrl("https://www.baidu.com");

        OgnlTest.OgnlPo ognlPo = new OgnlTest.OgnlPo();
        ognlPo.setPostMsgDTO(postMsgDTO);
        ognlPo.setName("zhangsan");

        ObjectMapper objectMapper = new ObjectMapper();
//
//        JsonNode jsonNode = objectMapper.valueToTree(ognlPo);
//
//        JsonNode urlNode = jsonNode.findPath("url");
//
//        System.out.println(urlNode.asText());
//
//
//        System.out.println(objectMapper.writeValueAsString(jsonNode));

        Object document = Configuration.defaultConfiguration().jsonProvider().parse(objectMapper.writeValueAsString(ognlPo));
//
//        String url = JsonPath.read(document, "$.postMsgDTO.url");
//
//        System.out.println(url);
//
//        System.out.println(document.toString());

        Configuration conf = Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        DocumentContext documentContext = JsonPath.using(conf).parse(objectMapper.writeValueAsString(ognlPo));
        String gender0 = documentContext.read("$.postMsgDTO.url");

        System.out.println(gender0);

        documentContext.set("$.postMsgDTO.url", "https://www.taobao.com");

        System.out.println(documentContext.jsonString());
    }


    @Data
    static class OgnlPo{
        private String name;

        private PostMsgDTO postMsgDTO;
    }
}
