package com.yootk.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
//@TableName("emp") // 定义该VO类对应的数据表名称
public class Emp {
    @TableId(type = IdType.ASSIGN_ID) // 手工配置
    private String eid;
    private String ename;
    private Double sal;
    private Long did;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String flag;
}
