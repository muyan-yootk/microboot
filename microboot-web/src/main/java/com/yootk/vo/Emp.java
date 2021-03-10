package com.yootk.vo;

import lombok.*;

@Data // 会与@NonNull注解相集合生成有参构造
@NoArgsConstructor // 要求当前的类自动的生成无参构造方法
@AllArgsConstructor // 生成的是一个全参构造
@RequiredArgsConstructor(staticName="setNonnull")
public class Emp {
    @NonNull // 一旦要生成无参构造，这个注解就有冲突了，该注解失效
    private Long empno;
    @NonNull // 一旦要生成无参构造，这个注解就有冲突了，该注解失效
    private String ename;
    private Double salary;
}
