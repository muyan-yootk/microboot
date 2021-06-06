package com.yootk.action.muyan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@Slf4j
public class DeptAction {
    @Autowired
    private DataSource dataSource; // 应该根据当前的程序包自动的切换
    @RequestMapping("/dept_datasource")
    public Object getDataSource() throws Exception { // 获取数据源的信息
        log.info("【MUYAN】数据源：{}", this.dataSource); // 日志输出
        return this.dataSource.getConnection().getCatalog(); // 获取分类日志
    }
}
