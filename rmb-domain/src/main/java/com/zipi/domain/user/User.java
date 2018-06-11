package com.zipi.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author liangyu
 * @date 2018/6/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;

    private Date createTime;

    private Date updateTime;

    private String userId;
}
