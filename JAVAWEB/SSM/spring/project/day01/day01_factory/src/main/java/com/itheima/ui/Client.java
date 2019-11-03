package com.itheima.ui;

import com.itheima.service.IAccountService;
import com.itheima.Beanfactory.*;
public class Client {
    public static void main(String[] args) {
        //AccountServiceImpl as = new AccountServiceImpl();
        IAccountService as = (IAccountService)new factory().getBean("accountService");
        as.saveAccount();
    }
}
