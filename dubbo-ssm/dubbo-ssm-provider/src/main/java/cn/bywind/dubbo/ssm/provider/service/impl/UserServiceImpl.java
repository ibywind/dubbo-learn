package cn.bywind.dubbo.ssm.provider.service.impl;

import cn.bywind.dubbo.ssm.api.entity.User;
import cn.bywind.dubbo.ssm.api.service.UserService;
import cn.bywind.dubbo.ssm.provider.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

     @Resource
     private UserMapper userMapper;

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User selectUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据姓名查询用户
     *
     * @param userName
     * @return
     */
    @Override
    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByName(userName);
    }
}
