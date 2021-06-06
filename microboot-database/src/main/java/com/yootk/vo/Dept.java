package com.yootk.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
//@TableName("dept") // 定义该VO类对应的数据表名称
public class Dept { // 描述部门信息类
    @TableId(type = IdType.AUTO) // 自动增长ID
    private Long did; // 部门ID
    private String dname; // 部门名称
    private String loc; // 部门为孩子
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String flag; // 标记数据库的米可能构成
}
