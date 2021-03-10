package com.yootk.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data // 会与@NonNull注解相集合生成有参构造
@Accessors(prefix = "yootk") // 配置前缀
public class Emp {
    private Long yootkEmpno; // 要在属性上增加前缀
    private String yootkEname; // 要在属性上增加前缀
    private Double yootkSalary; // 要在属性上增加前缀
}
