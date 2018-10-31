package com.alice.nsgogo.security;

import com.alice.nsgogo.entity.User;
import com.alice.nsgogo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author Nano on 2018/5/15.
 * @version 1.0
 * @since 1.0
 */
public class UserInfoServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        List<User> userList = userMapper.select(user);
        if (userList.isEmpty() || userList.size() > 1) {
            throw new UsernameNotFoundException("用户名数据库中为空或不止一个!");
        }
        user = userList.get(0);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ADMIN,ROLE_USER");
        user.setAuthorities(grantedAuthorities);
        return user;
    }
}
