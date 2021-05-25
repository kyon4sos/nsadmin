package org.nekostudio.service.web.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nekostudio.dao.UserRoleDao;
import org.nekostudio.entity.UserRole;
import org.nekostudio.service.web.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author neko
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao,UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;


    public int deleteBatchIds(List<UserRole> userRoles) {
        List<Integer> ids = userRoles.stream().map(UserRole::getId).collect(Collectors.toList());
       return  userRoleDao.deleteBatchIds(ids);
    }
}
