package org.nekostudio.service.web.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nekostudio.dao.RoleMenuDao;
import org.nekostudio.dto.RoleDto;
import org.nekostudio.entity.Role;
import org.nekostudio.entity.RoleMenu;
import org.nekostudio.entity.SysMenu;
import org.nekostudio.service.web.IRoleMenuService;
import org.nekostudio.service.web.IRoleService;
import org.nekostudio.service.web.ISysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neko
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements IRoleMenuService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Override
    public boolean updateRoleAndMenu(RoleDto roleDto) {
        List<Integer> menuIds = roleDto.getMenuIds();
        List<SysMenu> sysMenus = menuService.listByIds(menuIds);
        ArrayList<RoleMenu> roleMenus = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(sysMenu.getId());
            roleMenu.setRoleId(roleDto.getId());
            roleMenus.add(roleMenu);
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        roleService.updateById(role);
        roleMenuService.removeByIds(menuIds);
        roleMenuService.saveBatch(roleMenus);
        return true;
    }


}
