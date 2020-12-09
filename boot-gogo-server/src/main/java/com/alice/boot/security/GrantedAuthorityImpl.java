package com.alice.boot.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Alice
 */
public class GrantedAuthorityImpl implements GrantedAuthority {


    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
