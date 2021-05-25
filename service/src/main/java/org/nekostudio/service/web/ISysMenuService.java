package org.nekostudio.service.web;

import com.baomidou.mybatisplus.extension.service.IService;
import org.nekostudio.entity.SysMenu;

import java.util.List;

/**
 * @author neko
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenu> selectList();

    List<SysMenu> findByRoleId(Integer roleId);
}
