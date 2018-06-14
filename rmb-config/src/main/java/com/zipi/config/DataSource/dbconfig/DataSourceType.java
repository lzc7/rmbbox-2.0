package com.zipi.config.DataSource.dbconfig;

import lombok.Data;

/**
 * @author liangyu
 * @date 2018/6/14
 */
public enum DataSourceType {
    CLUSTER("read", "从库"),
    MASTER("write", "主库");

    private String type;

    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
