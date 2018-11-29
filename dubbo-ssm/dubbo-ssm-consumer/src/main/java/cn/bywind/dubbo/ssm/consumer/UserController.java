package cn.bywind.dubbo.ssm.consumer;


import cn.bywind.dubbo.ssm.api.entity.User;
import cn.bywind.dubbo.ssm.api.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getUserById/{id}")
    public User getUser(@PathVariable("id") Long userId){
        return userService.selectUserById(userId);
    }
}
