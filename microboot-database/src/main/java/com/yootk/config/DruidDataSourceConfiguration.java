package com.yootk.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DruidDataSourceConfiguration { // 自定义的Druid配置类
    @Bean("yootkDruidDataSource") // 手工设置Bean名称
    public DruidDataSource getYootkDruidDataSource(
            @Value("${spring.yootk.datasource.driver-class-name}")
            String driverClassName, // 数据库驱动程序
            @Value("${spring.yootk.datasource.url}")
            String url, // 数据库连接地址
            @Value("${spring.yootk.datasource.username}")
            String username, // 数据库的用户名
            @Value("${spring.yootk.datasource.password}")
            String password, // 数据库的用户名
            @Value("${spring.yootk.datasource.druid.initial-size}")
            int initialSize, // 初始化连接数
            @Value("${spring.yootk.datasource.druid.min-idle}")
            int minIdle, // 最小维持连接数
            @Value("${spring.yootk.datasource.druid.max-active}")
            int maxActive, // 最大连接数
            @Value("${spring.yootk.datasource.druid.max-wait}")
            long maxWait, // 最长等待时间
            @Value("${spring.yootk.datasource.druid.time-between-eviction-runs-millis}")
            long timeBetweenEvictionRunsMillis, // 关闭空闲连接间隔
            @Value("${spring.yootk.datasource.druid.min-evictable-idle-time-millis}")
            long minEvictableIdleTimeMillis, // 最小存活时间
            @Value("${spring.yootk.datasource.druid.validation-query}")
            String validationQuery, // 验证查询
            @Value("${spring.yootk.datasource.druid.test-while-idle}")
            boolean testWhileIdle, // 测试空闲连接是否可用
            @Value("${spring.yootk.datasource.druid.test-on-borrow}")
            boolean testOnBorrow, // 测试后返回连接
            @Value("${spring.yootk.datasource.druid.test-on-return}")
            boolean testOnReturn, // 测试后归还
            @Value("${spring.yootk.datasource.druid.pool-prepared-statements}")
            boolean poolPreparedStatements, // 是否缓存PSTMT
            @Value("${spring.yootk.datasource.druid.max-pool-prepared-statement-per-connection-size}")
            int maxPoolPreparedStatementPerConnectionSize, // PSTMT缓存个数
            @Autowired StatFilter sqlStatFilter // 注入SQL监控
    ) {
        DruidDataSource dataSource = new DruidDataSource(); // 实例化DataSource子类对象
        dataSource.setDriverClassName(driverClassName); // 数据库驱动程序
        dataSource.setUrl(url); // 数据库的连接地址
        dataSource.setUsername(username); // 数据库用户名
        dataSource.setPassword(password); // 数据库密码
        dataSource.setInitialSize(initialSize); // 连接池初始化大小
        dataSource.setMinIdle(minIdle); // 最小维持的连接数量
        dataSource.setMaxActive(maxActive); // 最大的连接数量
        dataSource.setMaxWait(maxWait); // 最大等待时间
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis); // 检查的间隔时间
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis); // 存活时间
        dataSource.setValidationQuery(validationQuery); // 验证SQL
        dataSource.setTestWhileIdle(testWhileIdle); // 测试连接是否可用
        dataSource.setTestOnBorrow(testOnBorrow); // 获取时检测
        dataSource.setTestOnReturn(testOnReturn); // 归还时检测
        dataSource.setPoolPreparedStatements(poolPreparedStatements); // 是否缓存PSTMT
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize); // 缓存个数
        // 定义所有可能存在的监控项集合
        List<Filter> filterList = new ArrayList<>();
        filterList.add(sqlStatFilter); // 配置监控项
        dataSource.setProxyFilters(filterList); // 与DataSource整合
        return dataSource;
    }
}
