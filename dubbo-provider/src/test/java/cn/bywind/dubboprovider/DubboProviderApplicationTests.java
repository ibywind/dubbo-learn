package cn.bywind.dubboprovider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboProviderApplicationTests {

	@Autowired
	AopRunner runner;

	@Test
	public void contextLoads() {
	}


	@Test
	public void aop(){
		Object o = runner.testAop();
		System.out.println(o);
	}


}
