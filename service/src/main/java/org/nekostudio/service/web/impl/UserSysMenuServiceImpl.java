package org.nekostudio.service.web.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nekostudio.dao.UserSysMenuDao;
import org.nekostudio.entity.UserSysMenu;
import org.nekostudio.service.web.IUserSysMenuService;
import org.springframework.stereotype.Service;

/**
 * @author neko
 */
@Service
public class UserSysMenuServiceImpl extends ServiceImpl<UserSysMenuDao,UserSysMenu> implements IUserSysMenuService {
}
