package com.yootk.config;

import com.yootk.filter.EduFilter;
import com.yootk.filter.MuyanFilter;
import com.yootk.filter.YootkFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;

@Configuration
@Order(-10) // 这个配置无法在过滤器上使用
public class WebFilterConfig { // 定义WEB过滤器
    @Bean
    public FilterRegistrationBean getMuyanFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(this.getMuyanFilter());
        filterRegistrationBean.setName("MFilter");
        filterRegistrationBean.addUrlPatterns("/orders/*");
        filterRegistrationBean.setOrder(5); // 设置过滤器的执行顺序
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean getYootkFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(this.getYootkFilter());
        filterRegistrationBean.setName("AFilter");
        filterRegistrationBean.addUrlPatterns("/orders/*");
        filterRegistrationBean.setOrder(2); // 设置过滤器的执行顺序
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean getEduFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(this.getEduFilter());
        filterRegistrationBean.setName("ZFilter");
        filterRegistrationBean.addUrlPatterns("/orders/*");
        filterRegistrationBean.setOrder(-100); // 设置过滤器的执行顺序
        return filterRegistrationBean;
    }
    @Bean
    public Filter getMuyanFilter() {
        return new MuyanFilter(); // 手工实例化Filter对象
    }
    @Bean
    public Filter getYootkFilter() {
        return new YootkFilter(); // 手工实例化Filter对象
    }
    @Bean
    public Filter getEduFilter() {
        return new EduFilter(); // 手工实例化Filter对象
    }
}
