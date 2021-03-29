package com.yootk.common.validation.annotation.handler;

import com.yootk.common.validation.annotation.RegexValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegexConstraintValidator implements ConstraintValidator<RegexValidator, Object> { // 处理类
    private String regexExpression; // 保存正则表达式
    @Override
    public void initialize(RegexValidator constraintAnnotation) {
        this.regexExpression = constraintAnnotation.pattern(); // 通过注解配置的内容获取表达式信息
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) { // 数据不允许为空
            return false;
        }
        return value.toString().matches(this.regexExpression);
    }
}
