package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import com.cg.bean.WalletAccountBean;
import com.cg.repo.Details;

public class WithDrawImple implements WithDraw {

	
	public void withdrwAmmount(Double d, String user, String pass) {
		List<WalletAccountBean> obj = Details.wb.stream().filter(e->e.getPassword().equals(pass) && e.getUser_name().equals(user)).collect(Collectors.toList());
        double bal = obj.get(0).getBalance();
        if(bal < d) {
        	System.out.print("Not Enough Money in your Account: ");
        	ShowBalanceImple b = new ShowBalanceImple();
        	b.showBalance(user, pass);
        }
        else
        {
        	Details.wb.stream().filter(e->e.getUser_name().equals(user) && e.getPassword().equals(pass)).forEach(e->e.setBalance(e.getBalance()-d));
        	Details.ws.stream().filter(e->e.getUser_id().equals(user) && e.getPassword().equals(pass)).forEach(e->e.setStatement("Money Withdraw Rs. " + d + " in your account updated balance: " + (bal - d)));
            System.out.print("Balance successfully withdrawn from your account...\nupdated balance: ");
            ShowBalanceImple b = new ShowBalanceImple();
        	b.showBalance(user, pass);
        }
	}

}
