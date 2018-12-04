package cn.bywind.dubboprovider;

import org.springframework.stereotype.Component;

@Component
public class AopRunner {

    @Say
    public Object testAop(){

        return null;
    }
}
