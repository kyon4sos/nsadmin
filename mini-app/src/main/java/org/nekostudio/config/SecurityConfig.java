package org.nekostudio.config;


import lombok.extern.slf4j.Slf4j;
import org.nekostudio.security.JwtFilter;
import org.nekostudio.security.handler.JwtAccessDenieHandler;
import org.nekostudio.security.handler.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.annotation.Resource;

/**
 * @author neko
 */
@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private JwtFilter jwtFilter;
    @Resource
    private JwtAccessDenieHandler jwtAccessDenieHandler;
    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Resource
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login","/test/**","/wx/**/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(userDetailsService)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDenieHandler)
                .and()
                .addFilterBefore(jwtFilter, ExceptionTranslationFilter.class);
    }
}
