package com.yootk.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

// @Configuration
public class DruidMonitorConfiguration { // Druid监控配置
    @Bean("druidStatViewServlet")
    public ServletRegistrationBean<StatViewServlet> getDruidStatViewServlet() {
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(
                new StatViewServlet(), "/druid/*"); // 程序的访问路径
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_ALLOW, "127.0.0.1"); // 白名单
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_DENY, ""); // 黑名单
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_USERNAME, "muyan"); // 用户名
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_PASSWORD, "yootk");// 密码
        registrationBean.addInitParameter(
                StatViewServlet.PARAM_NAME_RESET_ENABLE, "true"); // 允许重置
        return registrationBean;
    }
    @Bean
    @DependsOn("webStatFilter")
    public FilterRegistrationBean<WebStatFilter> getDruidWebStatFilter(
            WebStatFilter webStatFilter
    ) {
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(webStatFilter);
        registrationBean.addUrlPatterns("/*"); // 对所有的路径都进行监控配置
        registrationBean.addInitParameter(WebStatFilter.PARAM_NAME_EXCLUSIONS,
                "*.js,*.gif,*.jpg,*.bmp,*.css,*.ico,/druid/*"); // 路径排除
        return registrationBean;
    }
    @Bean("webStatFilter")
    public WebStatFilter getWebStatFilter() {   // 获取WEB状态过滤
        WebStatFilter statFilter = new WebStatFilter();
        statFilter.setSessionStatEnable(true); // 对Session状态进行监控
        return statFilter;
    }
    @Bean("sqlStatFilter")
    public StatFilter getSQLStatFilter(
            @Value("${spring.yootk.datasource.druid.stat.merge-sql}")
                    boolean mergeSql,
            @Value("${spring.yootk.datasource.druid.stat.log-slow-sql}")
                    boolean logSlowSql,
            @Value("${spring.yootk.datasource.druid.stat.slow-sql-millis}")
                    long slowSqlMillis
    ) { // 定义关于SQL监控的处理部分
        StatFilter filter = new StatFilter();
        filter.setMergeSql(mergeSql); // 是否需要合并统计
        filter.setLogSlowSql(logSlowSql); // 慢SQL记录
        filter.setSlowSqlMillis(slowSqlMillis); // 慢SQL执行时间
        return filter;
    }
    @Bean("sqlWallConfig")
    public WallConfig getSQLWallConfig() { // 获取防火墙
        WallConfig wc = new WallConfig(); // 配置防火墙
        wc.setMultiStatementAllow(true); // 允许进行多个Statatement操作（批处理）
        wc.setDeleteAllow(false); // 不允许执行删除
        return wc;
    }
    @Bean("sqlWallFilter")
    public WallFilter getSQLWallFilter(WallConfig wallConfig) { // 注入防火墙配置项
        WallFilter filter = new WallFilter();
        filter.setConfig(wallConfig);
        return filter;
    }
}
