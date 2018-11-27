package cn.bywind.rpc.v_one;

public class ServiceProvider implements Service {
    @Override
    public String sayHelloWithName(String name) {
        return "hello "+name;
    }
}
