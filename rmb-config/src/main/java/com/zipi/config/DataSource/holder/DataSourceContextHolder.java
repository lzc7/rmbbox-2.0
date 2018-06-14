package com.zipi.config.DataSource.holder;

/**
 * 数据源上下文
 * 使用本地线程内存模型，保证线程证不受影响
 *
 * @author liangyu
 * @date 2018/6/14
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> localHolder = new ThreadLocal<>();

    public static void setCustomerType(String customerType) {
        localHolder.set(customerType);
    }

    public static String getCustomerType() {
        return localHolder.get();
    }

    public static void clearCustomerType() {
        localHolder.remove();
    }
}
