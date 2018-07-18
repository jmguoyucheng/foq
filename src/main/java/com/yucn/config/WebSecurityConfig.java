//package com.yucn.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
///**
// * Created by Administrator on 2018/2/12.
// */
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private AuthenticationSuccessHandler yucnAuthenticationSuccessHandler;
//    @Autowired
//    private AuthenticationFailureHandler yucnAuthenticationFailureHandler;
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("/yucn_signin.html")
//                .loginProcessingUrl("/auth/form")
//                .successHandler(yucnAuthenticationSuccessHandler)
//                .failureHandler(yucnAuthenticationFailureHandler)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/yucn_signin.html").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf().disable();
//    }
//}
