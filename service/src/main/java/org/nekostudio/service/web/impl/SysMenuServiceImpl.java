package org.nekostudio.service.web.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nekostudio.dao.SysMenuDao;
import org.nekostudio.entity.RoleMenu;
import org.nekostudio.entity.SysMenu;
import org.nekostudio.service.web.IRoleMenuService;
import org.nekostudio.service.web.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neko
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements ISysMenuService {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Cacheable(cacheNames = "sysmenu",key = "#root.methodName")
    @Override
    public List<SysMenu> selectList() {
        return super.list();
    }

    @Override
    public List<SysMenu> findByRoleId(Integer roleId) {
        List<Integer> ids = roleMenuService
                .list(new QueryWrapper<RoleMenu>()
                        .lambda()
                        .eq(RoleMenu::getRoleId, roleId))
                .stream()
                .map(RoleMenu::getMenuId)
                .collect(Collectors.toList());
        if (!ids.isEmpty()) {
            return this.listByIds(ids);
        }
       return Collections.emptyList();
    }
}
