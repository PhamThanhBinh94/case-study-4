package com.codegym;

import com.codegym.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;

    @Autowired
    private CustomerSuccesHandler customerSuccesHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) accountService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/","/users/create/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/products","/products/*","/bill/**","/customers/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/users","/users/list", "/users/edit").hasRole("USERS")
                .and().formLogin()
                .successHandler(customerSuccesHandler)
                .and().csrf()
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
