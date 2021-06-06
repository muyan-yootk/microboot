package com.yootk.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource implements Serializable { // 数据源动态切换
    // 每一次不同的请求线程操作都有可能要使用到自己的DataSource
    private static final ThreadLocal<String> DATASOURCE_CONTEXT_HOLDER = new ThreadLocal<>();
    @Override
    protected Object determineCurrentLookupKey() { // 获取当前的查询结果
        return getDataSource();
    }
    static interface DataSourceNames {  // 定义一个数据源的名称接口标记
        String MUYAN_DATASOURCE = "muyan"; // “muyan”数据库的标记
        String YOOTK_DATASOURCE = "yootk"; // “yootk”数据库标记
    }

    /**
     * 构建动态数据源
     * @param defaultTargetDataSource 默认的数据源对象
     * @param targetDataSources 全部的数据源对象
     */
    public DynamicDataSource(DataSource defaultTargetDataSource,
                             Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource); // 调用父类方法
        super.setTargetDataSources(targetDataSources); // 调用父类方法
        super.afterPropertiesSet(); // 属性的设置
    }
    public static void setDataSource(String dataSourceName) {    // 扩充的数据源处理方法
        DATASOURCE_CONTEXT_HOLDER.set(dataSourceName); // 保存当前线程多少数据源名称
    }
    public static String getDataSource() { // 获取数据源的名称标记
        return DATASOURCE_CONTEXT_HOLDER.get();
    }
    public static void clearDataSource() {
        DATASOURCE_CONTEXT_HOLDER.remove(); // 清除当前线程之中保存的数据源名称
    }

    @Override
    public String toString() { // 做一个默认的信息输出
        try {
            return "DataSourceName = " + getDataSource() + "、DataSource实例 = " + super.getResolvedDataSources().get(getDataSource()).getConnection().getCatalog();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "DataSourceName = " + getDataSource();
    }
}
