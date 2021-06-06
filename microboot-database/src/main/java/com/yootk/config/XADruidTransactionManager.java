package com.yootk.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

public class XADruidTransactionManager { // XZA事务管理器
    // UserTransaction可以保证在当前线程下的所有数据库操作都使用同一个Connection接口实例
    // 最终可以在进行事务提交或回滚的时候保证事务操作的原子性
    @Bean(name = "userTransaction")
    public UserTransaction getUserTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(1000); // 超时配置
        return userTransactionImp;
    }
    @Bean(name="atomikosTransactionManager")
    public TransactionManager getTransactionManager() {
        UserTransactionManager transactionManager = new UserTransactionManager();
        transactionManager.setForceShutdown(false); // 关闭强制退出
        return transactionManager;
    }
    @Bean("transactionManager")
    @DependsOn({"userTransaction", "atomikosTransactionManager"})
    public PlatformTransactionManager getPlatformTransactionManager(
            UserTransaction userTransaction,
            TransactionManager atomikosTransactionManager) {
        return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
    }
}
