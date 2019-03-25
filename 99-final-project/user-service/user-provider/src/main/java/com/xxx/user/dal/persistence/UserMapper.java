package com.xxx.user.dal.persistence;

import com.xxx.user.dal.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据用户id查询用户
     * @param uid
     * @return
     */
    User getUserByUid(Integer uid);

    /**
     *  新增用户，插入一条数据
     * @param user
     * @return
     */
    Integer insertSelective(User user);
}
