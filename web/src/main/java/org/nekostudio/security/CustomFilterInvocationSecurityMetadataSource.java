package org.nekostudio.security;

import org.nekostudio.entity.SysMenu;
import org.nekostudio.service.web.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author neko
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private ISysMenuService sysMenuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        String method = ((FilterInvocation) object).getRequest().getMethod();
        Set<SysMenu> menus = new HashSet<>(sysMenuService.selectList());
        for (SysMenu menu : menus) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                return SecurityConfig.createList(menu.getPermission());
            }
        }
        return SecurityConfig.createList(org.nekostudio.config.SecurityConfig.PERMIT_ALL);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
