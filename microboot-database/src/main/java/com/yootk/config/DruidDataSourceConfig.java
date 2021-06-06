package com.yootk.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DruidDataSourceConfig {
    // 对应的application.yml配置项前缀：spring.datasource.druid.xx（xx是具体的配置子项）
    private static final String DRUID_POOL_PREFIX = "spring.datasource.druid.";
    private static final String DATABASE_MUYAN_DRUID_PREFIX = "spring.datasource.muyan.";
    private static final String DATABASE_YOOTK_DRUID_PREFIX = "spring.datasource.yootk.";
    @Bean("druidMuyanDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.muyan")
    public DataSource getMuyanDataSource(
            @Autowired Environment env) { // 数据源的配置
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setXaDataSourceClassName(env.getProperty(DATABASE_MUYAN_DRUID_PREFIX + "type"));
        dataSourceBean.setUniqueResourceName("muyan");
        Properties properties = build(env, DATABASE_MUYAN_DRUID_PREFIX, DRUID_POOL_PREFIX);
        dataSourceBean.setXaProperties(properties); // 保存所有的配置属性
        return dataSourceBean;
    }
    @Bean("druidYootkDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.yootk")
    public DataSource getYootkDataSource(
            @Autowired Environment env) { // 数据源的配置
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setXaDataSourceClassName(env.getProperty(DATABASE_YOOTK_DRUID_PREFIX + "type"));
        dataSourceBean.setUniqueResourceName("yootk");
        Properties properties = build(env, DATABASE_YOOTK_DRUID_PREFIX, DRUID_POOL_PREFIX);
        dataSourceBean.setXaProperties(properties); // 保存所有的配置属性
        return dataSourceBean;
    }
    private Properties build(Environment env,
                             String databasePrefix, String druidPrefix) {
        Properties prop = new Properties();
        prop.put("url", env.getProperty(databasePrefix + "url"));
        prop.put("username", env.getProperty(databasePrefix + "username"));
        prop.put("password", env.getProperty(databasePrefix + "password"));
        prop.put("driverClassName", env.getProperty(
                databasePrefix + "driverClassName", ""));
        prop.put("initialSize", env.getProperty(
                druidPrefix + "initial-size", Integer.class));
        prop.put("maxActive", env.getProperty(druidPrefix + "max-active", Integer.class));
        prop.put("minIdle", env.getProperty(druidPrefix + "min-idle", Integer.class));
        prop.put("maxWait", env.getProperty(druidPrefix + "max-wait", Integer.class));
        prop.put("poolPreparedStatements", env.getProperty(
                druidPrefix + "pool-prepared-statements", Boolean.class));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(druidPrefix +
                        "max-pool-prepared-statement-per-connection-size", Integer.class));
        prop.put("maxPoolPreparedStatementPerConnectionSize",
                env.getProperty(druidPrefix +
                        "max-pool-prepared-statement-per-connection-size", Integer.class));
        prop.put("validationQuery", env.getProperty(druidPrefix + "validation-query"));
        prop.put("testOnBorrow", env.getProperty(
                druidPrefix + "test-on-borrow", Boolean.class));
        prop.put("testOnReturn", env.getProperty(
                druidPrefix + "test-on-return", Boolean.class));
        prop.put("testWhileIdle", env.getProperty(
                druidPrefix + "test-while-idle", Boolean.class));
        prop.put("timeBetweenEvictionRunsMillis",
                env.getProperty(druidPrefix +
                        "time-between-eviction-runs-millis", Integer.class));
        prop.put("minEvictableIdleTimeMillis", env.getProperty(druidPrefix +
                "min-evictable-idle-time-millis", Integer.class));
        return prop;
    }
}
