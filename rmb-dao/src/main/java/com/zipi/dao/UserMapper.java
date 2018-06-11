package com.zipi.dao;

import com.zipi.domain.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liangyu
 * @date 2018/6/9
 */
@Mapper
public interface UserMapper {

    long insert(User user);

    List<User> list();
}
