package com.yootk.lombok;

import lombok.Cleanup;
import lombok.Data;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Data // 此时的程序会自动生成一个双参构造
public class MessageRead { // 在此时进行异常的控制
    @NonNull
    private String filePath; // 文件路径
    @NonNull
    private String fileName; // 文件名称
    @SneakyThrows // 帮助用户手工处理异常
    public String load() {  // 数据读取
        @Cleanup InputStream input = new FileInputStream(new File(this.filePath, this.fileName));
        byte data [] = new byte[1024];
        int len = input.read(data); // 数据读取
        return new String(data, 0, len);
    }
}
