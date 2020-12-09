package com.alice.boot.service;

import com.alice.boot.entity.User;

import java.util.List;

/**
 * @author Alice on 2020/8/11
 * @version 1.0
 * @since 1.0
 */
public interface UserService {
    /**
     * 根据主键获取用户名
     * 返回list兼容防止出现多个重复用户名
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 根据用户名获取用户
     * 返回list兼容防止出现多个重复用户名
     *
     * @param username
     * @return
     */
    List<User> getUserByUsername(String username);

    /**
     * 根据用户id获取权限列表
     *
     * @param id
     * @return
     */
    List<String> getPermissionsByUserId(Long id);
}
