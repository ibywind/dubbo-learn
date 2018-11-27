package cn.bywind.dubboprovider.impl;

import cn.bywind.dubboapi.model.User;
import cn.bywind.dubboapi.service.UserService;
import com.alibaba.dubbo.config.annotation.Service;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"

)
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        return new User("byiwnd",28);
    }
}
