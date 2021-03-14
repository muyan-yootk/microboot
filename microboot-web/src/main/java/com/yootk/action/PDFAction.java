package com.yootk.action;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller // 使用的是普通的控制器的注解
@RequestMapping("/data/*")
public class PDFAction {
    @GetMapping("pdf") // 配置映射路径
    public void createPDFData(HttpServletResponse response) throws Exception { // 使用Response是为了响应处理
        response.setHeader("Content-Type", "application/pdf"); // 设置响应类型
        // 一般情况下可以将是生成的PDF文件进行下载，所以此时强制性的开启下载，并且配置下载文件名称
        response.setHeader("Content-Disposition", "attachement;filename=yootk.pdf");
        // 下面开始使用ITextPDF组件来在内存之中形成一份要生成的PDF文件
        Document document = new Document(PageSize.A4, 10, 10, 50, 20); // 主要是设置页面的大小
        PdfWriter.getInstance(document, response.getOutputStream()); // 获取PDF的输出流配置
        document.open(); // 开始构建PDF文档内容
        // 在当前的程序里面已经提供了图片的资源，所以利用资源匹配符进行资源路径定义
        Resource imageResource = new ClassPathResource("/images/muyan_yootk_share.png"); // Spring所提供的资源访问
        Image image = Image.getInstance(imageResource.getFile().getAbsolutePath()); // 通过指定的路径加载图片
        // PDF文件在进行生成的时候是基于坐标的方式实现的绘制处理
        image.scaleToFit(PageSize.A4.getWidth() / 2, PageSize.A4.getHeight());
        float pointX = (PageSize.A4.getWidth() - image.getScaledWidth()) / 2;
        float pointY = PageSize.A4.getHeight() - image.getHeight() - 100;
        image.setAbsolutePosition(pointX, pointY); // 绘制坐标的定义
        document.add(image); // 开始追加图片到文档之中
        document.add(new Paragraph("\n\n\n")); // 图片之后换三行而后输出文字信息
        // 如果要想输出文字，而且要想输出正常的中文，就必须设置有字库
        Resource fontResource = new ClassPathResource("/fonts/Alibaba-PuHuiTi-Bold.ttf"); // 字体的资源
        BaseFont baseFont = BaseFont.createFont(fontResource.getFile().getAbsolutePath(),
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 20, Font.NORMAL); // 定义要使用的字库
        // 在PDF文件上开始绘制文本信息
        String titles[] = new String[] {"沐言科技", "李兴华编程训练营", "人人分享公益直播"};
        String contents [] = new String[] {"www.yootk.com", "edu.yootk.com", "share.yootk.com"};
        for (int x = 0; x < titles.length; x++) {   // 实现数据的循环输出
            PdfPTable table = new PdfPTable(2); // 定义表格
            PdfPCell cell = new PdfPCell(); // 创建单元格信息
            cell.setPhrase(new Paragraph(titles[x], font)); // 输出文字定义
            table.addCell(cell); // 追加单元格
            cell = new PdfPCell();
            cell.setPhrase(new Paragraph(contents[x]));
            table.addCell(cell);
            document.add(table);
        }
        document.close();
    }
}
