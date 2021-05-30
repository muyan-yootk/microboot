package com.yootk.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("member") // 定义该VO类对应的数据表名称
public class Member {
    @TableId // 配置主键列
    private String mid;
    private String name;
    private Integer age;
    private Double salary;
    private Date birthday;
    private String content;
    @TableLogic // 逻辑删除处理
    private Integer isdel; // 逻辑删除字段
}
