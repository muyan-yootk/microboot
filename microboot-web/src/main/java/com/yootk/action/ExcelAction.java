package com.yootk.action;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.yootk.vo.Message;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller // 使用的是普通的控制器的注解
@RequestMapping("/data/*")
public class ExcelAction {
    @GetMapping("excel") // 配置映射路径
    public void createExcelData(HttpServletResponse response) throws Exception { // 使用Response是为了响应处理
        response.setHeader("Content-Type",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // 设置响应类型
        response.setHeader("Content-Disposition", "attachement;filename=yootk.xls");
        // 随后通过一些固定数据的循环方式来生成相应的Excel数据
        String titles[] = new String[] {"沐言科技", "李兴华编程训练营", "人人分享公益直播"};
        String contents [] = new String[] {"www.yootk.com", "edu.yootk.com", "share.yootk.com"};
        // 数据的生成需要将数据保存在Message类的对象之中
        List<Message> messageList = new ArrayList<>(); // 定义数据的保存集合
        for (int x = 0; x < titles.length; x++) {
            Message message = new Message();
            message.setTitle(titles[x]);
            message.setContent(contents[x]);
            message.setPubdate(new Date());
            messageList.add(message); // 将数据添加到集合之中
        }
        // 随后要开始进行XLS文件内容的配置了
        ExportParams exportParams = new ExportParams("沐言科技消息管理", "最新消息", ExcelType.XSSF);
        Workbook workbook = new XSSFWorkbook(); // 创建工作薄
        new ExcelExportService().createSheet(workbook, exportParams, Message.class, messageList);
        workbook.write(response.getOutputStream());
    }
}
