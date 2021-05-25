package org.nekostudio.controller;

import lombok.extern.slf4j.Slf4j;
import org.nekostudio.annotation.Audit;
import org.nekostudio.annotation.LimitRequest;
import org.nekostudio.enums.Operation;
import org.nekostudio.common.JsonResult;
import org.nekostudio.entity.AppUser;
import org.nekostudio.service.web.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author neko
 */
@RestController
@Slf4j
public class HomeController {

    @Autowired
    private IUserService userService;

    @GetMapping("/test/{username}")
    public JsonResult test(@PathVariable String username){
        AppUser user = userService.findByUsername(username);
        return JsonResult.ok(user);
    }

    @Audit(name = "测试",type = Operation.DELETE)
    @LimitRequest(frequency = 3,second = 100)
    @GetMapping("/ad")
    public JsonResult test2(@RequestParam("name") String name){
        return JsonResult.ok("ad");
    }

    @Audit(name = "测试",type = Operation.DELETE)
    @LimitRequest(frequency = 3,second = 100)
    @PostMapping("/test_user")
    public JsonResult test3(@RequestBody AppUser appUser){
        return JsonResult.ok("ad");
    }
}
