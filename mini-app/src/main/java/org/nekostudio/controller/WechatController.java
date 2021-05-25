package org.nekostudio.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.nekostudio.common.JsonResult;
import org.nekostudio.config.WxMaConfiguration;
import org.nekostudio.entity.WxUser;
import org.nekostudio.service.web.IWxUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author neko
 */
@RestController
@RequestMapping("/wx/{appid}")
public class WechatController {
    @Autowired
    private IWxUserService wxUserService;
    @GetMapping("login")
    public JsonResult loin(@RequestParam("code") String code, @PathVariable String appid) throws WxErrorException {
        WxMaService wxService = WxMaConfiguration.getMaService(appid);
        WxMaJscode2SessionResult result = wxService.jsCode2SessionInfo(code);
        WxUser wxUser = new WxUser();
        BeanUtils.copyProperties(result, wxUser);
        wxUserService.insert(wxUser);
        return JsonResult.ok(wxUser);
    }
}
