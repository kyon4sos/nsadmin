package org.nekostudio.service.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.nekostudio.dto.RoleDto;
import org.nekostudio.entity.Role;

import java.util.List;

/**
 * @author neko
 */

public interface IRoleService extends IService<Role> {


    /**
     * 分页查询角色
     * @param page
     * @return
     */
    IPage<Role> findPage(IPage<Role> page);

    /**
     * 查询角色
     * @return
     */
    List<Role> selectList();

    IPage<Role> findPage(Page<Role> page);

    /** 插入或更新
     * @param roleDto
     * @return
     */
    boolean createOrUpdate(RoleDto roleDto);

    int logicDelete(long id);
}
