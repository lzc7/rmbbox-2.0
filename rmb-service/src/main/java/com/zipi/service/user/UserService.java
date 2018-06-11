package com.zipi.service.user;

import com.zipi.domain.user.User;

import java.util.List;

/**
 * @author liangyu
 * @date 2018/6/9
 */
public interface UserService {

    long insert(User user);

    List<User> list();
}
