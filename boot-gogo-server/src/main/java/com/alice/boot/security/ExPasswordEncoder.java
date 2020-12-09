package com.alice.boot.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author alice on 2020/7/29 013.
 * @version 1.0
 * @since 1.0
 * 自定义password encoder
 * 用于取消密码加密保存数据库
 */
public class ExPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        //不做任何加密处理
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //charSequence是前端传过来的密码，s是数据库中查到的密码
        return rawPassword.toString().contentEquals(rawPassword);
    }
}
