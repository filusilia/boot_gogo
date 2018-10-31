package com.alice.nsgogo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * @author Aozaki on 2018/10/26.
 * @version 1.0
 * @since 1.0
 */
@Service
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private final Logger log = LoggerFactory.getLogger(MyFilterSecurityMetadataSource.class);

    @Autowired
    public MyFilterSecurityMetadataSource() {

    }

    public List<ConfigAttribute> getAttributes(Object object) {

        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String httpMethod = fi.getRequest().getMethod();
        List<ConfigAttribute> attributes = new ArrayList<>();
        log.info("ok  sec:{}-->{}", url, httpMethod);
        // Lookup your database (or other source) using this information and populate the
        // list of attributes

        for(Map.Entry<String,String> entry:urlRoleMap.entrySet()){
            if(antPathMatcher.match(entry.getKey(),url)){
                return SecurityConfig.createList(entry.getValue());
            }
        }
        return SecurityConfig.createList("ROLE_USER");
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }


    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final Map<String,String> urlRoleMap = new HashMap<String,String>(){{
        put("/open/**","ROLE_ANONYMOUS");
        put("/health","ROLE_ANONYMOUS");
        put("/restart","ROLE_ADMIN");
        put("/demo","ROLE_USER");
    }};
}