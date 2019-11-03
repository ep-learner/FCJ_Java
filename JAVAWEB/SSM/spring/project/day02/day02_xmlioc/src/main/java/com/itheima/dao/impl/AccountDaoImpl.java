package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import org.springframework.stereotype.Component;

@Component("dao")
public class AccountDaoImpl implements IAccountDao {
    public void saveAccount() {
        System.out.println("dao 调用了*******");
    }
}
