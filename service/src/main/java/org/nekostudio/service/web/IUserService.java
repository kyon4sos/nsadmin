package org.nekostudio.service.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nekostudio.dto.AppUserDto;
import org.nekostudio.entity.AppUser;

/**
 * @author neko
 */
public interface IUserService extends IService<AppUser> {
    /**
     * @param username 用户名
     * @return appuser 用户
     */

    AppUser findByUsername(String username);

    IPage<AppUser> selectPage(Page<AppUser> page);

    int create(AppUser appUser);

    int updateUserAndRole(AppUser user);

    AppUser updateUser(AppUserDto userDto);

    int loginDelete(long id);

}
