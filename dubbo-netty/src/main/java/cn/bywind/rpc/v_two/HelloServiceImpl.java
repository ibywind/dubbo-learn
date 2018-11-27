package cn.bywind.rpc.v_two;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHelloWithName(String name) {
        return "hello "+name;
    }
}
