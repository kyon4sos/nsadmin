package org.nekostudio.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.nekostudio.common.JsonResult;
import org.nekostudio.entity.AppUser;
import org.nekostudio.entity.SysMenu;
import org.nekostudio.entity.UserSysMenu;
import org.nekostudio.service.web.ISysMenuService;
import org.nekostudio.service.web.IUserService;
import org.nekostudio.service.web.IUserSysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neko
 */
@RestController
public class ProfileController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IUserSysMenuService userSysMenuService;

    @Autowired
    private ISysMenuService sysMenuService;


    @GetMapping("profile")
    public JsonResult get() {
        String name =(String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser appUser = userService.findByUsername(name);
        List<UserSysMenu> menuIds = userSysMenuService.list(new QueryWrapper<UserSysMenu>().eq("user_id", appUser.getId()));
        List<Integer> ids = menuIds.stream().map(UserSysMenu::getMenuId).collect(Collectors.toList());
        if (!ids.isEmpty()) {
            List<SysMenu> sysMenus = sysMenuService.listByIds(ids);
            return JsonResult.ok(sysMenus);
        }
        return JsonResult.ok(Collections.emptyList());

    }

}
