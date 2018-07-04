import com.yuntongxun.weixin.WebBootstrap;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by liugang on 2018/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=WebBootstrap.class)
@WebAppConfiguration
public class BaseTest {


}
