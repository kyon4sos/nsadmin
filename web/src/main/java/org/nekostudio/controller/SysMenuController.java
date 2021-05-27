package org.nekostudio.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.nekostudio.common.JsonResult;
import org.nekostudio.dto.TreeBuilder;
import org.nekostudio.entity.SysMenu;
import org.nekostudio.service.web.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author neko
 */
@Slf4j
@RestController
@RequestMapping("sysmenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;


    @ApiOperation("获取菜单分页")
    @GetMapping(params = {"name", "page_size", "current"})
    public JsonResult getByName(@RequestParam("name") String name, @RequestParam("page_size") long size, @RequestParam("current") long current) {
        Page<SysMenu> page = new Page<>(current, size);
        Page<SysMenu> menuPage = menuService.page(page, new QueryWrapper<SysMenu>().lambda()
                .like(SysMenu::getName, name));
        return JsonResult.ok(menuPage);
    }

    @ApiOperation("获取菜单分页")
    @GetMapping(params = "role_id")
    public JsonResult get(@RequestParam("role_id") Integer roleId) {
        List<SysMenu> menus = menuService.findByRoleId(roleId);
        return JsonResult.ok(menus);
    }

    @ApiOperation("获取菜单分页")
    @GetMapping(params = {"page_size", "current"})
    public JsonResult list(@RequestParam("page_size") long size, @RequestParam("current") long current) {
        IPage<SysMenu> permissions = menuService.page(new Page<>(current, size));
        return JsonResult.ok(permissions);
    }

    @ApiOperation("更新菜单")
    @PutMapping
    public JsonResult put(@RequestBody SysMenu sysMenu) {
        boolean saveOrUpdate = menuService.saveOrUpdate(sysMenu);
        if (saveOrUpdate) {
            return JsonResult.ok();
        }
        return JsonResult.fail();
    }

    @ApiOperation("获取树型菜单")
    @GetMapping("treedata")
    public JsonResult tree() {
        List<SysMenu> list = menuService.list();
        TreeBuilder<SysMenu> builder = new TreeBuilder<>();
        List<SysMenu> tree = builder.build(list);
        return JsonResult.ok(tree);
    }
}
