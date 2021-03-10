package com.yootk.test;

import com.yootk.lombok.SaleTicket;

public class TestSaleTicket {
    public static void main(String[] args) {
        SaleTicket saleTicket = new SaleTicket(11); // 一共10张票
        for (int x = 0; x < 10; x++) {
            new Thread(()->{
                saleTicket.sale(); // 售票操作
            }, "票贩子 - " + x).start();
        }
    }
}
