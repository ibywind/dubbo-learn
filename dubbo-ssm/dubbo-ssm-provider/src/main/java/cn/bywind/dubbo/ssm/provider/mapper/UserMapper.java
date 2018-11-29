package cn.bywind.dubbo.ssm.provider.mapper;

import cn.bywind.dubbo.ssm.api.entity.User;


public interface UserMapper {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 根据登陆账户查询user
     * @param userName
     * @return
     */
    User selectUserByName(String userName);
}
