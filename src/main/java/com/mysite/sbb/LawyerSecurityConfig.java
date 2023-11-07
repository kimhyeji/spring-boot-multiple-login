package com.mysite.sbb;

import com.mysite.sbb.lyUser.LawyerSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(2)
@Configuration
public class LawyerSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LawyerSecurityService lawyerSecurityService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(lawyerSecurityService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .authorizeRequests().antMatchers("/lawyer/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/lawyer/login")
                .loginProcessingUrl("/lawyer/login")
                .defaultSuccessUrl("/lawyer/l_page")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/lawyer/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }
}