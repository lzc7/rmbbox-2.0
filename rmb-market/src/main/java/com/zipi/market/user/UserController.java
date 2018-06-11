package com.zipi.market.user;

import com.zipi.domain.common.R;
import com.zipi.domain.user.User;
import com.zipi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author liangyu
 * @date 2018/6/9
 */
@RestController
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("list")
    public R list() {
        try {
            return R.isOk().data(userService.list());
        } catch (Exception e) {
            return R.isFail(e);
        }
    }

    @GetMapping("add")
    public R insert(){
        try {
            User user = new User();
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setUserId("886D6615-B121-11E6-B164-525400C920FE");
            return R.isOk().data(userService.insert(user));
        } catch (Exception e) {
            return R.isFail(e);
        }
    }
}
