package org.nekostudio.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nekostudio.common.JsonResult;
import org.nekostudio.entity.WxUser;
import org.nekostudio.service.web.IWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neko
 */
@RestController
@RequestMapping("/wxuser")
public class WxUserController {

    @Autowired
    private IWxUserService wxUserService;

    @GetMapping
    public JsonResult list(@RequestBody Page<WxUser> page) {
        IPage<WxUser> users = wxUserService.selectPage(page);
        return JsonResult.ok(users);
    }
}
