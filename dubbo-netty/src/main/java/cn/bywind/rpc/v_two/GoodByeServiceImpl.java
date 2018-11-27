package cn.bywind.rpc.v_two;

public class GoodByeServiceImpl implements GoodByeService {
    @Override
    public String sayGoodbye(Person person) {
        return "GoodBye :"+person;
    }
}
