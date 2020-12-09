package com.alice.boot.service;

import com.alice.boot.entity.User;
import com.alice.boot.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alice on 2020/8/11
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;

    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, UserRoleMapper userRoleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userMapper.select(user);
    }

    @Override
    public List<String> getPermissionsByUserId(Long id) {
        List<String> p = new ArrayList<>();
        p.add("/index");
        p.add("/user/info");
        p.add("/auth/logout");
        p.add("/user/");
        return p;
    }
}