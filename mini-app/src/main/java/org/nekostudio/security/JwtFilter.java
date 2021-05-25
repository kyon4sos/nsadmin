package org.nekostudio.security;


import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.nekostudio.common.JwtUtil;
import org.nekostudio.entity.AppUser;
import org.nekostudio.service.web.IUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final String TOKEN_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    @Resource
    private IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = request.getHeader(TOKEN_HEADER);
        if (StringUtils.hasText(token) && token.startsWith(PREFIX)) {
            token = token.substring(PREFIX.length());
            String username = JwtUtil.getSubject(token);
            AppUser appUser = userService.findByUsername(username);
            try {
                JwtUtil.validToken(token, appUser.getSalt());
                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(appUser,
                        token, null);
                SecurityContextHolder.getContext().setAuthentication(userToken);
            } catch (JWTVerificationException e) {
                log.info("jwt exception {} {}", e.getClass(), e.getMessage());
                request.setAttribute("jwt.exception", e);
            }
        }
        filterChain.doFilter(request, response);
    }
}
