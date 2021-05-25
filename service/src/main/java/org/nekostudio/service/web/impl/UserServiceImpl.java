package org.nekostudio.service.web.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nekostudio.common.BussineException;
import org.nekostudio.common.ResultEnum;
import org.nekostudio.dao.AppUserDao;
import org.nekostudio.dao.UserRoleDao;
import org.nekostudio.dto.AppUserDto;
import org.nekostudio.entity.AppUser;
import org.nekostudio.entity.UserRole;
import org.nekostudio.service.constant.CacheName;
import org.nekostudio.service.web.IUserRoleService;
import org.nekostudio.service.web.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neko
 */
@Service
public class UserServiceImpl extends ServiceImpl<AppUserDao, AppUser> implements IUserService, UserDetailsService {

    @Autowired
    private AppUserDao appUserDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private IUserRoleService userRoleService;

    @Cacheable(cacheNames = CacheName.USER_KEY_PREFIX,key = "#username")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<AppUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        AppUser user = appUserDao.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        return user;
    }

    @Cacheable(cacheNames = CacheName.USER_KEY_PREFIX,key = "#username")
    @Override
    public AppUser findByUsername(String username) {
        QueryWrapper<AppUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        AppUser appUser = appUserDao.selectOne(queryWrapper);
        if (appUser == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return appUser;
    }

    @Override
    public IPage<AppUser> selectPage(Page<AppUser> page) {
        QueryWrapper<AppUser> queryWrapper = new QueryWrapper<>();
        return appUserDao.selectPage(page, queryWrapper);
    }

    @Override
    public int create(AppUser appUser) {
        AppUser user;
        user = this.getOne(new QueryWrapper<AppUser>()
                .lambda()
                .eq(AppUser::getUsername, appUser.getUsername()));

        if (user!=null) {
            throw new BussineException(ResultEnum.USERNAME_EXIST);
        }

        user = this.getOne(new QueryWrapper<AppUser>()
                .lambda()
                .eq(AppUser::getPhone, appUser.getPhone()));
        if (user!=null) {
            throw new BussineException(ResultEnum.PHONE_EXIST);
        }
        return appUserDao.insert(appUser);
    }

    @Override
    public int updateUserAndRole(AppUser user) {
        return 0;
    }


    @CachePut(cacheNames = CacheName.USER_KEY_PREFIX,key = "#userDto.username")
    @Override
    public AppUser updateUser(AppUserDto userDto) {
        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(userDto, appUser);
        List<Integer> ids = userDto.getRoleIds();
        this.updateById(appUser);
        ArrayList<UserRole> userRoles = new ArrayList<>();
        for (Integer rid : ids) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(rid);
            userRole.setUserId(appUser.getId());
            userRoles.add(userRole);
        }
        userRoleService.remove(new QueryWrapper<UserRole>()
                .lambda()
                .eq(UserRole::getUserId, userDto.getId()));
        if (!userRoles.isEmpty()) {
             userRoleService.saveBatch(userRoles);
        }
        return appUser;
    }

    @CacheEvict(CacheName.USER_KEY_PREFIX)
    @Override
    public int loginDelete(long id) {
        return appUserDao.deleteById(id);
    }
}
