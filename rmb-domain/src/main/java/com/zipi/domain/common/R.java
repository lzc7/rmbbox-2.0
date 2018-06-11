package com.zipi.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liangyu
 * @date 2018/6/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = -4577255781088498763L;
    private static final int OK = 0;
    private static final int FAIL = 1;
    private static final int UNAUTHORIZED = 2;

    private T data; //服务端数据
    private int status = OK; //状态码
    private String msg = ""; //描述信息

    //APIS
    public static R isOk() {
        return new R();
    }

    public static R isFail() {
        return new R().status(FAIL);
    }

    public static R isFail(Throwable e) {
        return isFail().msg(e);
    }

    public R msg(Throwable e) {
        this.setMsg(e.toString());
        return this;
    }

    public R data(T data) {
        this.setData(data);
        return this;
    }

    public R status(int status) {
        this.setStatus(status);
        return this;
    }
}