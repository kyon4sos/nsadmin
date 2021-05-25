package org.nekostudio.service.web;

import com.baomidou.mybatisplus.extension.service.IService;
import org.nekostudio.dto.RoleDto;
import org.nekostudio.entity.RoleMenu;

/**
 * @author neko
 */
public interface IRoleMenuService  extends IService<RoleMenu> {
    boolean updateRoleAndMenu(RoleDto roleDto);
}
