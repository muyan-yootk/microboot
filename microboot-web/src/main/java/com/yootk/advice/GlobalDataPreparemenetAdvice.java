package com.yootk.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice // 同样的注解，不同的味道
public class GlobalDataPreparemenetAdvice { // 全局数据预处理
    @InitBinder("company")
    public void company(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("company."); // 参数前缀
    }
    @InitBinder("dept")
    public void dept(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("dept."); // 参数前缀
    }
}
