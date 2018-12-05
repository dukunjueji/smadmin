package remote;

import com.uc.training.base.bd.dto.MemberDTO;
import com.zuche.framework.common.SpringApplicationContext;
import com.zuche.framework.remote.RemoteClientFactory;
import com.zuche.framework.remote.serializer.JavaSerialzerProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created with IntelliJ IDEA.
 * <p>
 * 测试示例：<br>
 * 1. 注入想要测试的远程服务类。<br>
 * 2. 编写单元测试案例。<br>
 * 3. 修改modules.properties文件,为想要测试的服务项目指定地址。<br>
 * 4. 确认操作3指定的服务地址服务可用。<br>
 * <p>
 *
 * @author zhangjun (zhangjun01@zuche.com)
 * @since 2017/12/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:frameworkContext.xml")
public class DemoRemoteServiceTest {

    private static final String API_QUERY_MEMBER = "smbase.api.queryOneMember";

    private static final String DEMO = "fiveplus.api.demo.DemoRemoteService";

    private static final String ADMIN_PROVINCE = "ucadmin.department.getAllProvcList";

    private static final String LIST_MESSAGE = "smorder.api.listMessages";

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void init() {
        SpringApplicationContext.initApplicationContext(applicationContext);
    }


    @Test
    public void getDemoTest() {
        MemberDTO dto = new MemberDTO();
        dto.setId(10L);
        JavaSerialzerProxy re = (JavaSerialzerProxy) RemoteClientFactory.getInstance()
                .executeToObject(API_QUERY_MEMBER, dto);
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(re.getBytes());
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        System.out.println(obj);
    }

}
