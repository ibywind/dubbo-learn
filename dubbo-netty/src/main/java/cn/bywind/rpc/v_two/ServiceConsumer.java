package cn.bywind.rpc.v_two;


public class ServiceConsumer {

    public static void main(String[] args) throws Exception{
        ServiceClient client = new ServiceClient();
        HelloService helloService = (HelloService)
                client.createProxy(HelloService.class);
        String bywind = helloService.sayHelloWithName("bywind");
        System.out.println("helloService :"+bywind);

        GoodByeService goodByeService = (GoodByeService)
                client.createProxy(GoodByeService.class);
        String bywind1 = goodByeService.sayGoodbye(new Person("bywind", 28));
        System.out.println("goodByeService : "+bywind1);
    }
}
