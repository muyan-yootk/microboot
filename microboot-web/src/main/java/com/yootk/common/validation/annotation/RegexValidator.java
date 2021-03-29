package com.yootk.common.validation.annotation;

import com.yootk.common.validation.annotation.handler.RegexConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER}) // 该注解允许在成员和参数上使用
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegexConstraintValidator.class) // 正则处理类
public @interface RegexValidator { // 自定义正则注解
    // 所有的验证注解里面需要提供有三个核心的属性内容：message、groups、payload
    String message() default "数据正则验证错误"; // 错误信息
    Class<?>[] groups() default { }; // 验证分组
    Class<? extends Payload>[] payload() default { }; // 附加源数据信息
    String pattern(); // 自定义属性，接收要使用的正则表达式
}
