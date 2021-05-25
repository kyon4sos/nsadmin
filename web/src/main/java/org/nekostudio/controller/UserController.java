package org.nekostudio.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.nekostudio.common.JsonResult;
import org.nekostudio.dto.AppUserDto;
import org.nekostudio.entity.AppUser;
import org.nekostudio.entity.Role;
import org.nekostudio.entity.UserRole;
import org.nekostudio.service.web.IRoleService;
import org.nekostudio.service.web.IUserRoleService;
import org.nekostudio.service.web.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neko
 */
@Slf4j
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleService roleService;

    @GetMapping(value = "user",params = {"phone"})
    public JsonResult getByPhone(@RequestParam("phone") String phone) {
        List<AppUser> appUsers = userService.list(new QueryWrapper<AppUser>().eq("phone", phone));
        return JsonResult.ok(appUsers);
    }
    @GetMapping(value = "user/{uid}/role")
    public JsonResult getRoleByUid(@PathVariable long uid) {
        List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, uid));
        if (!userRoles.isEmpty()) {
            List<Integer> ids = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
            List<Role> roles = roleService.listByIds(ids);
            return JsonResult.ok(roles);
        }
        return JsonResult.ok(Collections.emptyList());
    }
    @GetMapping(value = "users",params = {"page_size","current"})
    public JsonResult getAll(@RequestParam("page_size") long size,@RequestParam("current") long current) {
        Page<AppUser> page = new Page<>(current,size);
        IPage<AppUser> users = userService.selectPage(page);
        return JsonResult.ok(users);
    }

    @GetMapping("/user/{username}")
    public JsonResult get(@PathVariable("username") String username) {
        AppUser appUser = userService.findByUsername(username);
        return JsonResult.ok(appUser);
    }

    @PutMapping("/user/{id}")
    public JsonResult updateUser(@Validated @RequestBody AppUserDto user) {
        AppUser appUser = userService.updateUser(user);
        if (appUser!=null) {
            return JsonResult.ok(appUser);
        }
        return  JsonResult.fail();
    }

    @PostMapping("/user")
    public JsonResult addUser(@RequestBody AppUserDto userDto) {
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(userDto, appUser);
        int i = userService.create(appUser);
        if (i>0) {
            return JsonResult.ok();
        }
        return JsonResult.fail();
    }

    @DeleteMapping("/user/{uId}")
    public JsonResult delUser(@PathVariable long uId) {
         userService.loginDelete(uId);
        return JsonResult.ok();
    }

}
