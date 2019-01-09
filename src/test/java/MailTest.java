import com.uc.training.SmadminApplication;
import com.uc.training.common.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 版权声明： Copyright (c) 2008 ucarinc. All Rights Reserved.
 *
 * @author 何麒（qi.he@ucarinc.com）
 * @Version 1.0
 * @date 2019/1/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmadminApplication.class)
public class MailTest {
    @Autowired
    private MailService mailService;
    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("494173758@qq.com","dukun","520");
    }
}
