package remote;

import com.uc.training.base.bd.dto.MemberDTO;
import com.uc.training.common.vo.RemoteResult;
import com.uc.training.ord.re.OrderGoodsRE;
import com.zuche.framework.common.SpringApplicationContext;
import com.zuche.framework.remote.RemoteClientFactory;
import com.zuche.framework.remote.RemoteType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

/**
 * 获取新车辆详情测试用例
 */
@ContextConfiguration(locations = {"classpath:frameworkContext.xml"})
public class OrdRPCDemo extends AbstractTestNGSpringContextTests {
    private static final Logger logger = LoggerFactory
            .getLogger(OrdRPCDemo.class);
    @Autowired
    private ApplicationContext applicationContext;

    //String host = "http://cmstest3.maimaiche.com:80/uccms";
    String localHost = "http://127.0.0.1:8080/smorder";

    String service = "uccms.model.queryModelDetailByPara";
    String service2 = "smbase.api.queryOneMember";
    String service3 = "smorder.api.getOrderGoodsByOrderId";
    String service4 = "smbase.api.queryOneMember";

    /**
     * 订单rpc测试
     */
    @Test
    public void queryModelInfo() throws Exception {
        SpringApplicationContext.initApplicationContext(applicationContext);
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(1L);
         Integer orderId = 23;
        RemoteResult<List<OrderGoodsRE>> re1 = (RemoteResult<List<OrderGoodsRE>>) RemoteClientFactory.getInstance(RemoteType.HESSIAN)
                .executeToObject(service4, memberDTO);
        System.out.println(re1);
    }

}
