package com.yootk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

// 当前的项目都是基于MyBatis / MyBatisPlus开发的，所以此时一定要基于MyBatis标准开发事务管理器
@Slf4j
public class MultiDataSourceTransaction
        implements org.apache.ibatis.transaction.Transaction {// 多数据源事务管理器
    private DataSource dataSource; // 事务是需要有DataSource支持
    private Connection currentConnection; // 当前的数据库连接
    private boolean autoCommit; // 是否要进行自动提交启用
    private boolean isConnectionTransactional; // 是否要启用事务
    private ConcurrentHashMap<String, Connection> otherConnectionMap; // 保存其他的Connection对象
    private String currentDatabaseName; // 当前数据库名称
    public MultiDataSourceTransaction(DataSource dataSource) {
        this.dataSource = dataSource; // 保存数据元
        this.otherConnectionMap = new ConcurrentHashMap<>(); // 保存其他数据库连接
        this.currentDatabaseName = DynamicDataSource.getDataSource(); // 获取当前操作的数据库名称
    }
    private void openMainConnection() throws SQLException { // 打开一个连接
        // 通过当前得到的DataSource接口实例来获取一个Connection接口实例
        this.currentConnection = DataSourceUtils.getConnection(this.dataSource);
        this.autoCommit = this.currentConnection.getAutoCommit(); // 获取当前的是否自动提交的状态
        this.isConnectionTransactional = DataSourceUtils.isConnectionTransactional(this.currentConnection, this.dataSource);
        log.info("当前数据库连接：{}、事务支持状态：{}。", this.currentConnection, this.isConnectionTransactional);
    }
    @Override
    public Connection getConnection() throws SQLException { // 获取数据库连接
        // 存在有数据源的前提下才可以实现连接的获取，那么首先要判断是否有数据源存在
        String datasourceName = DynamicDataSource.getDataSource(); // 获取当前数据源名称
        if (null == datasourceName || datasourceName.equals(this.currentDatabaseName)) {    // 现在的数据源为当前使用的数据库
            if (this.currentConnection != null) {   // 当前存在有数据库连接
                return this.currentConnection; // 返回当前的连接
            } else {    // 如果当前的数据源没有开启过连接
                openMainConnection(); // 开启一个数据库连接
                this.currentDatabaseName = datasourceName; // 保存当前的数据库名称
                return this.currentConnection; // 返回当前的连接
            }
        } else {    // 没有连接
            if (!this.otherConnectionMap.containsKey(datasourceName)) { // 没有当前这个数据源的名称存在
                Connection conn = dataSource.getConnection(); // 获取数据库连接
                this.otherConnectionMap.put(datasourceName, conn); // 保存连接
            }
            return this.otherConnectionMap.get(datasourceName);
        }
    }

    @Override
    public void commit() throws SQLException { // 数据库事务提交
        // 当前存在有Connection接口实例，同时没有开启自动的事务提交，并且存在有支持事务的连接
        if (this.currentConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
            log.info("数据库事务提交，当前数据库连接：{}", this.currentConnection);
            this.currentConnection.commit(); // 提交当前的数据库事务
            for (Connection connecion : this.otherConnectionMap.values()) { // 控制其它的数据库连接
                connecion.commit(); // 保证其他的连接提交事务
            }
        }
    }

    @Override
    public void rollback() throws SQLException { // 事务回滚
        if (this.currentConnection != null && !this.isConnectionTransactional && !this.autoCommit) {
            log.info("数据库事务回滚，当前数据库连接：{}", this.currentConnection);
            this.currentConnection.rollback(); // 回滚当前的数据库事务
            for (Connection connecion : this.otherConnectionMap.values()) { // 控制其它的数据库连接
                connecion.rollback(); // 保证其他的连接提交回滚
            }
        }
    }

    @Override
    public void close() throws SQLException { // 事务关闭
        DataSourceUtils.releaseConnection(this.currentConnection, this.dataSource);
        for (Connection connecion : this.otherConnectionMap.values()) { // 控制其它的数据库连接
            DataSourceUtils.releaseConnection(connecion, this.dataSource);
        }
    }

    @Override
    public Integer getTimeout() throws SQLException { // 超时配置
        return 500;
    }
}
