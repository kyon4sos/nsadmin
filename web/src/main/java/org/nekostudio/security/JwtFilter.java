package org.nekostudio.security;


import lombok.extern.slf4j.Slf4j;
import org.nekostudio.common.JwtUtil;
import org.nekostudio.entity.Role;
import org.nekostudio.service.web.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";

    @Autowired
    private IRoleService roleService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = request.getHeader(TOKEN_HEADER);
        if (StringUtils.hasText(token) && token.startsWith(PREFIX)) {
            token = token.substring(PREFIX.length());
            if (JwtUtil.validToken(token)) {
                String username = JwtUtil.getSubject(token);
                List<String> roleNameList = JwtUtil.getRoles(token);
                List<Role> roles = roleService.selectList();
                ArrayList<Role> roleArrayList = new ArrayList<>();
                for (String s:roleNameList) {
                    for(Role role:roles) {
                        if (s.equals(role.getName())) {
                            roleArrayList.add(role);
                        }
                    }
                }
                List<GrantedAuthority> authorities = roleArrayList
                        .stream()
                        .flatMap(r -> r.getSysMenus().stream())
                        .map(m -> {
                            String authority ="PERMIT_ALL";
                            if (m.getAuthority() != null) {
                                authority = m.getAuthority();
                            }
                            return new SimpleGrantedAuthority(authority);
                        })
                        .collect(Collectors.toList());
                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username,
                        token, authorities);
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
