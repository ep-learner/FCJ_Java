package com.itheima.service.impl;



import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("accountService")
@Scope("singleton")
public class AccountServiceImpl implements IAccountService {
    @Resource(name = "dao")
    private AccountDaoImpl ad;

    @Value("3")
    private int a;
    public void saveAccount() {
        ad.saveAccount();
        System.out.println("service Account 调用了");
        System.out.println(a);
    }
}
