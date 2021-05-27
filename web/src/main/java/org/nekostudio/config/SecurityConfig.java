package org.nekostudio.config;


import lombok.extern.slf4j.Slf4j;
import org.nekostudio.security.CustomAccessDecisionManager;
import org.nekostudio.security.CustomFilterInvocationSecurityMetadataSource;
import org.nekostudio.security.JwtFilter;
import org.nekostudio.security.handler.JwtAccessDenieHandler;
import org.nekostudio.security.handler.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author neko
 */
@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //登录用户可访问
    public final static String PERMIT_ALL = "PERMIT_ALL";
    //未登录可访问
    public final static String  ANY = "ANY";
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private JwtAccessDenieHandler jwtAccessDenieHandler;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private CustomAccessDecisionManager customAccessDecisionManager;
    @Autowired
    private CustomFilterInvocationSecurityMetadataSource securityMetadataSource;
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
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**",
                "/js/**",
                "/index.html",
                "/img/**",
                "/fonts/**",
                "/favicon.ico",
                "/verifyCode",
                "/swagger/**",
                "/swagger-ui.html",
                "/v2/**",
                "/swagger-resources/**",
                "/webjars/springfox-swagger-ui/**"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .withObjectPostProcessor(
                        new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O obj) {
                                obj.setAccessDecisionManager(customAccessDecisionManager);
                                obj.setSecurityMetadataSource(securityMetadataSource);
                                return obj;
                            }
                        }
                )
                .and()
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .and()
                .userDetailsService(userDetailsService)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDenieHandler)
                .and()
                .addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
