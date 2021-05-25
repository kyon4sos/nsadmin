package org.nekostudio.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.nekostudio.common.JsonResult;
import org.nekostudio.dto.RoleDto;
import org.nekostudio.entity.Role;
import org.nekostudio.entity.RoleMenu;
import org.nekostudio.entity.SysMenu;
import org.nekostudio.service.web.IRoleMenuService;
import org.nekostudio.service.web.IRoleService;
import org.nekostudio.service.web.ISysMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neko
 */
@Slf4j
@RestController
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private ISysMenuService sysMenuService;

    @GetMapping(value = "role", params = {"display_name", "page_size", "current"})
    public JsonResult getByName(@Validated @RequestParam("display_name") String name, @RequestParam("page_size") long size, @RequestParam("current") long current) {
        Page<Role> page = new Page<>(current,size);
        Page<Role> rolePage = roleService.page(page, new QueryWrapper<Role>().lambda().like(Role::getDisplayName, name));
        return JsonResult.ok(rolePage);
    }

    @GetMapping(value = "roles", params = {"page_size", "current"})
    public JsonResult list(@RequestParam("page_size") long size, @RequestParam("current") long current) {
        Page<Role> page = new Page<>(current, size);
//        IPage<Role> roles = roleService.findPage(page);
        Page<Role> rolePage = roleService.page(page);
        return JsonResult.ok(rolePage);
    }

    @GetMapping(value = "roles")
    public JsonResult list() {
        List<Role> roles = roleService.selectList();
        return JsonResult.ok(roles);
    }

    @PostMapping(value = "role")
    public JsonResult put(@Validated @RequestBody RoleDto roleDto) {
        log.info("role {}", roleDto);
        boolean res = roleService.createOrUpdate(roleDto);
        if (res) {
            return JsonResult.ok();
        }
        return JsonResult.fail();
    }

    @PutMapping("/role/{id}/state")
    public JsonResult updateState(@Validated @RequestBody Role role) {
        boolean res = roleService.updateById(role);
        if (res) {
            return JsonResult.ok();
        }
        return JsonResult.fail();
    }

    @PutMapping("/role/{id}")
    public JsonResult updateState(@Validated @RequestBody RoleDto roleDto) {
        List<Integer> menuIds = roleDto.getMenuIds();
        roleMenuService.remove(new QueryWrapper<RoleMenu>().lambda()
                .eq(RoleMenu::getRoleId, roleDto.getId()));
        if (!menuIds.isEmpty()) {
            Role role = new Role();
            BeanUtils.copyProperties(roleDto, role);
            List<SysMenu> sysMenus = sysMenuService.listByIds(menuIds);
            List<RoleMenu> roleMenuList = sysMenus.stream()
                    .map(m -> {
                        RoleMenu roleMenu = new RoleMenu();
                        roleMenu.setMenuId(m.getId());
                        roleMenu.setRoleId(roleDto.getId());
                        return roleMenu;
                    })
                    .collect(Collectors.toList());
            roleService.updateById(role);
            roleMenuService.saveBatch(roleMenuList);
        }

        return JsonResult.ok();
    }
}
