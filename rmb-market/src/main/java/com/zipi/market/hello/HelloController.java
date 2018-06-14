package com.zipi.market.hello;

import com.zipi.domain.common.R;
import com.zipi.service.user.OrderService;
import com.zipi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangyu
 * @date 2018/6/14
 */
@RestController
@RequestMapping("/order/*")
public class HelloController {

    @Autowired
    OrderService userService;

    @GetMapping("list")
    public R list() {
        try {
            return R.isOk().data(userService.list());
        } catch (Exception e) {
            return R.isFail(e);
        }
    }
}
