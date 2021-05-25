package org.nekostudio.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nekostudio.entity.Role;

/**
 * @author neko
 */
@Mapper
@TableName(resultMap = "BaseResultMap")
public interface RoleDao extends BaseMapper<Role> {
    Role selectRole(Integer id);
    IPage<Role> findPage(@Param("page") Page<Role> page);
    int updateRole(Role role);
}
