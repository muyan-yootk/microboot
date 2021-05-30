package com.yootk.config;

import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.HashMap;
import java.util.Map;

public class TransactionConfig { // 事务配置类
    private static final int TRANSACTION_METHOD_TIMEOUT = 5; // 事务处理的超时时间
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.yootk..service.*.*(..))";
    @Autowired
    private TransactionManager transactionManager; // 事务管理器
    @Bean("txAdvice")
    public TransactionInterceptor transactionInterceptorConfig() {
        // 配置数据读取事务规则
        RuleBasedTransactionAttribute readOnlyAttribute = new RuleBasedTransactionAttribute();
        readOnlyAttribute.setReadOnly(true); // 只读事务
        readOnlyAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED); // 非事务运行
        // 配置了数据更新事务规则
        RuleBasedTransactionAttribute requiredAttribute = new RuleBasedTransactionAttribute();
        requiredAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 事务开启
        requiredAttribute.setTimeout(TRANSACTION_METHOD_TIMEOUT); // 事务处理超时时间
        // 配置所有要进行事务处理的方法名称定义
        Map<String, TransactionAttribute> transactionAttributeMap = new HashMap<>();
        transactionAttributeMap.put("add*", requiredAttribute);
        transactionAttributeMap.put("edit*", requiredAttribute);
        transactionAttributeMap.put("delete*", requiredAttribute);
        transactionAttributeMap.put("list*", readOnlyAttribute);
        transactionAttributeMap.put("get*", readOnlyAttribute);
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.setNameMap(transactionAttributeMap); // 配置方法名称的映射
        TransactionInterceptor interceptor = new TransactionInterceptor(transactionManager, source);
        return interceptor;
    }
    @Bean("txAdvisor")
    public Advisor transactionAdvisor(
            @Autowired TransactionInterceptor interceptor
    ) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, interceptor);
    }
}
