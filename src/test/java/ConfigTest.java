import com.yuntongxun.weixin.util.ConfigString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liugang on 2018/7/4.
 */
@Slf4j
public class ConfigTest extends BaseTest {

    @Autowired
    private ConfigString configString;

    @Test
    public void test(){
        log.info(configString.toString());
    }

}
