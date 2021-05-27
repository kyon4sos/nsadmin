package org.nekostudio.controller;

import io.swagger.annotations.ApiOperation;
import org.nekostudio.common.JsonResult;
import org.nekostudio.common.JwtUtil;
import org.nekostudio.dto.LoginInfo;
import org.nekostudio.entity.AppUser;
import org.nekostudio.service.web.IUserService;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author neko
 */
@RestController
public class AuthController {

    @Resource
    private ProviderManager authenticationManager;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private IUserService userService;

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public JsonResult register(@RequestBody @Validated LoginInfo loginInfo) {
        String username = loginInfo.getUsername();
        String password = loginInfo.getPassword();
        password = passwordEncoder.encode(password);
        AppUser appUser = new AppUser().setUsername(username)
                .setPassword(password);
        userService.create(appUser);
        return JsonResult.ok(appUser);
    }
    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public JsonResult login(@RequestBody LoginInfo info, HttpServletResponse response) {
        String password = info.getPassword();
        String username = info.getUsername();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = authenticationManager.authenticate(userToken);
        AppUser user =(AppUser) authenticate.getPrincipal();
        String token = JwtUtil.createTokenByDefaultKey(user);
        user.setPassword(null);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Authorization", token);
        return JsonResult.ok(user);
    }
}
