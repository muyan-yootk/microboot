package com.yootk.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.Synchronized;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor // 生成一个全参构造
public class SaleTicket {
    private int ticket; // 设置票数

    @SneakyThrows // 处理中断异常
    @Synchronized // 同步处理
    public void sale() {    // 售票操作
        while (this.ticket > 0) {   // 此时有票
            if (this.ticket > 0) {  // 准备售票
                TimeUnit.SECONDS.sleep(1); // 模拟异常
                System.err.println("【" + Thread.currentThread().getName() + "】售票，ticket = " + this.ticket--);
            }
        }
    }
}
