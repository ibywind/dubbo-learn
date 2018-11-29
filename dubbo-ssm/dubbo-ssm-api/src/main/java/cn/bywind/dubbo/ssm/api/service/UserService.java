package cn.bywind.dubbo.ssm.api.service;

import cn.bywind.dubbo.ssm.api.entity.User;

public interface UserService {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User selectUserById(Long id);

    /**
     * 根据姓名查询用户
     * @param userName
     * @return
     */
    User selectUserByUserName(String userName);
}
