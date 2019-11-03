package com.itheima.service.impl;

import com.itheima.Beanfactory.factory;
import com.itheima.dao.IAccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.IAccountService;

public class AccountServiceImpl implements IAccountService {

    public void saveAccount() {
//        AccountDaoImpl dao =new com.itheima.dao.impl.AccountDaoImpl();
        IAccountDao dao = (IAccountDao)new factory().getBean("accountDao");
        dao.saveAccount();
    }
}
