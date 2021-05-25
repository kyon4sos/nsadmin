package org.nekostudio.service.web.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.nekostudio.dao.RoleDao;
import org.nekostudio.dao.RoleMenuDao;
import org.nekostudio.dto.RoleDto;
import org.nekostudio.entity.Role;
import org.nekostudio.entity.RoleMenu;
import org.nekostudio.service.web.IRoleMenuService;
import org.nekostudio.service.web.IRoleService;
import org.nekostudio.service.web.ISysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neko
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Override
    public IPage<Role> findPage(IPage<Role> page) {
        return roleDao.selectPage(page, new QueryWrapper<>());
    }

    @Override
    public List<Role> selectList() {
        return roleDao.selectList(null);
    }

    @Override
    public IPage<Role> findPage(Page<Role> page) {
        return roleDao.findPage(page);
    }

    @Override
    public boolean createOrUpdate(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        this.saveOrUpdate(role);
        List<Integer> ids = new ArrayList<>(roleDto.getMenuIds());
//        List<SysMenu> sysMenus = sysMenuService.listByIds(ids);
        roleMenuService.remove(new QueryWrapper<RoleMenu>()
                .lambda()
                .eq(RoleMenu::getRoleId, role.getId()));
        if (!ids.isEmpty()) {
            List<RoleMenu> roleMenus = sysMenuService.listByIds(ids)
                    .stream()
                    .map(m -> {
                        RoleMenu roleMenu = new RoleMenu();
                        roleMenu.setRoleId(role.getId());
                        roleMenu.setMenuId(m.getId());
                        return roleMenu;
                    })
                    .collect(Collectors.toList());
            return this.roleMenuService.saveBatch(roleMenus);
        }
        return true;
    }

    @Override
    public int logicDelete(long id) {
        return roleDao.deleteById(id);
    }
}
