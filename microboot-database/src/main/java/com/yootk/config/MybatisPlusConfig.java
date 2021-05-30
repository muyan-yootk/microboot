package com.yootk.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MybatisPlusConfig { // Mybatis拦截器配置
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    @Bean
    public MybatisPlusInterceptor getMybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(
                new PaginationInnerInterceptor(DbType.MYSQL) // 使用MySQL分页实现
        );
        return interceptor;
    }
    @Bean("mybatisSqlSessionFactoryBean")
    public MybatisSqlSessionFactoryBean getMybatisSqlSessionFactoryBean(
            @Autowired DataSource dataSource, // 要使用的数据源
            @Value("${mybatis-plus.config-location}") Resource configLocation, // 资源文件路径
            @Value("${mybatis-plus.type-aliases-package}") String typeAliasesPackage, // 扫描别名
            @Value("${mybatis-plus.mapper-locations}") String mapperLocations, // Mapping映射路径
            @Value("${mybatis-plus.global-config.db-config.logic-not-delete-value}") String logicNotDeleteValue,
            @Value("${mybatis-plus.global-config.db-config.logic-delete-value:}") String logicDeleteValue
    ) throws Exception {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource); // 配置项目中要使用的数据源
        mybatisPlus.setVfs(SpringBootVFS.class); // 配置程序的扫描类
        mybatisPlus.setTypeAliasesPackage(typeAliasesPackage); // 扫描包的别名
        Resource[] mappings = this.resourcePatternResolver.getResources(mapperLocations);
        mybatisPlus.setMapperLocations(mappings);
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig(); // 数据配置
        dbConfig.setLogicNotDeleteValue(logicNotDeleteValue); // 未删除时的数据内容
        dbConfig.setLogicDeleteValue(logicDeleteValue); // 删除时的数据内容
        GlobalConfig globalConfig = new GlobalConfig(); // 定义全局配置
        globalConfig.setDbConfig(dbConfig); // 全局配置项
        mybatisPlus.setGlobalConfig(globalConfig);
        return mybatisPlus;
    }
}
