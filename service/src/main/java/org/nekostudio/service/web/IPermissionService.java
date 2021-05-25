package org.nekostudio.service.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nekostudio.entity.Permission;

/**
 * @author neko
 */
public interface IPermissionService {

    IPage<Permission> selectPage(IPage<Permission> page);

}
