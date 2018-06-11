package com.zipi.service.user.Impl;

import com.zipi.dao.UserMapper;
import com.zipi.domain.user.User;
import com.zipi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liangyu
 * @date 2018/6/9
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long insert(User user) {
        long num = userMapper.insert(user);
        System.out.println(num);
        return num;
    }

    @Override
    public List<User> list() {
        List<User> list = userMapper.list();
        User user = list.get(0);
        return list;
    }
}
