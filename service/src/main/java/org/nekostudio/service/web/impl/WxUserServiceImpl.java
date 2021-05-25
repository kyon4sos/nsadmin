package org.nekostudio.service.web.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nekostudio.dao.WxUserDao;
import org.nekostudio.entity.WxUser;
import org.nekostudio.service.web.IWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author neko
 */
@Service
public class WxUserServiceImpl implements IWxUserService {
    @Autowired
    private WxUserDao wxUserDao;
    @Override
    public IPage<WxUser> selectPage(IPage<WxUser> page) {
        Page<WxUser> wxUserPage = new Page<>();
        QueryWrapper<WxUser> wrapper = new QueryWrapper<>();
        return wxUserDao.selectPage(wxUserPage, wrapper);
    }

    @Override
    public int insert(WxUser user) {
        return wxUserDao.insert(user);
    }
}
