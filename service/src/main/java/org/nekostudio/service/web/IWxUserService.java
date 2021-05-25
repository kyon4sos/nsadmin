package org.nekostudio.service.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.nekostudio.entity.WxUser;

/**
 * @author neko
 */
public interface IWxUserService  {
    IPage<WxUser> selectPage(IPage<WxUser> page);
    int insert(WxUser user);
}
