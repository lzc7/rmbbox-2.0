package com.zipi.service.user.Impl;

import com.zipi.dao.cluster.OrderMapper;
import com.zipi.domain.user.User;
import com.zipi.service.user.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liangyu
 * @date 2018/6/14
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<User> list() {
        return orderMapper.list();
    }
}
