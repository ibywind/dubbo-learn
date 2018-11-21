package cn.bywind.dubboconsumer;

import cn.bywind.dubboapi.model.User;
import cn.bywind.dubboapi.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboConsumerApplicationTests {

	@Reference(
			version = "1.0.0",
			application = "${dubbo.application.id}",
			url = "dubbo://localhost:12345"
	)
	UserService userService;

	@Test
	public void testGetUserFromDubbo() {
		User user = userService.getUser();
		System.out.println(user);
	}




}
