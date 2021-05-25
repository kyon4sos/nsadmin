package org.nekostudio.service.web.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nekostudio.dao.PermissionDao;
import org.nekostudio.entity.Permission;
import org.nekostudio.service.web.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author neko
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public IPage<Permission> selectPage(IPage<Permission> page) {
        return permissionDao.selectPage(page, null);
    }
}
