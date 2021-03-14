package com.yootk.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;
@Data // 只要使用Lombok组件，99%的情况下就使用这一个注解
public class Message {
    @Excel(name = "信息标题", orderNum = "0", width = 30) // 生成Excel表格列的配置
    private String title; // 字符串的参数
    @Excel(name = "信息日期", orderNum = "1", width = 50) // 生成Excel表格列的配置
    private Date pubdate; // 日期参数
    @Excel(name = "信息内容", orderNum = "2", width = 100) // 生成Excel表格列的配置
    private String content;
}
