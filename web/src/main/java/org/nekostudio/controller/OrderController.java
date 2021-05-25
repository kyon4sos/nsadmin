package org.nekostudio.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nekostudio.common.JsonResult;
import org.nekostudio.entity.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author neko
 *
 */
@RestController
public class OrderController {
    @GetMapping("orders")
    public JsonResult list(Page<Order> page) {
        return null;
    }
}
