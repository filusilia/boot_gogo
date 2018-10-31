package com.alice.nsgogo.security;

import com.alice.nsgogo.security.handler.AuthenticationAccessDeniedHandler;
import com.alice.nsgogo.security.handler.CustomAuthenticationFailureHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author alice on 2018/3/27 0027.
 * @version 1.0
 * @since 1.0
 */
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    public WebSecurityConfig(CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
                             MyFilterSecurityMetadataSource metadataSource,
                             MyAccessDecisionManager decisionManager, AuthenticationAccessDeniedHandler deniedHandler) {
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.metadataSource = metadataSource;
        this.decisionManager = decisionManager;
        this.deniedHandler = deniedHandler;
    }

    /**
     * Allows modifying and accessing the {@link UserDetailsService} from
     * {@link #userDetailsServiceBean()} without interacting with the
     * {@link org.springframework.context.ApplicationContext}. Developers should override this method when changing
     * the instance of {@link #userDetailsServiceBean()}.
     *
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("user")
                .password("password")
                .roles("USER")
                .build();
        manager.createUser(user);
        return new UserInfoServiceImpl();
    }

    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final MyFilterSecurityMetadataSource metadataSource;
    private final MyAccessDecisionManager decisionManager;
    private final AuthenticationAccessDeniedHandler deniedHandler;

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
     * should not invoke this method by calling super as it may override their
     * configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(metadataSource);
                        o.setAccessDecisionManager(decisionManager);
                        return o;
                    }
                })
//                .antMatchers(HttpMethod.GET,"/assets/js/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").successForwardUrl("/module/index").failureUrl("/loginOut").failureHandler(customAuthenticationFailureHandler).permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and().csrf().disable().exceptionHandling().accessDeniedHandler(deniedHandler);
//        http.addFilterBefore(myFilterSecurityInterceptor,FilterSecurityInterceptor.class)
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**", "/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("alice_x").password("alice_x.122").roles("ROLE_SUPER");
    }
}
