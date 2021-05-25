package org.nekostudio.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.nekostudio.entity.WxUser;

@Mapper
public interface WxUserDao extends BaseMapper<WxUser> {
}