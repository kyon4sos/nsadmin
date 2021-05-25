package org.nekostudio.controller;

import org.nekostudio.common.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neko
 */
@RestController
public class ProductController {

    @GetMapping("product")
    public JsonResult get() {
        return JsonResult.ok("hello");
    }
}
