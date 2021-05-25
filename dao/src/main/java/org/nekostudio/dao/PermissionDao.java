package org.nekostudio.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.nekostudio.entity.Permission;

/**
 * @author neko
 */
@Mapper
public interface PermissionDao extends BaseMapper<Permission> {
}
