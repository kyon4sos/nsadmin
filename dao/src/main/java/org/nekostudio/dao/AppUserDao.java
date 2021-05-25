package org.nekostudio.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.nekostudio.entity.AppUser;

/**
 * @author neko
 */
@Mapper
public interface AppUserDao extends BaseMapper<AppUser> {
}
