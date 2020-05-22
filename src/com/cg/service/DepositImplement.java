package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import com.cg.bean.WalletAccountBean;
import com.cg.repo.Details;

public class DepositImplement extends Details implements Deposit {

	@Override
	public void depositBalance(double money, String user, String pass) {
		List<WalletAccountBean> dp = Details.wb.stream().filter(e->e.getUser_name().equals(user) && e.getPassword().equals(pass)).collect(Collectors.toList());
		double d = dp.get(0).getBalance();
		Details.wb.stream().filter(e->e.getUser_name().equals(user) && e.getPassword().equals(pass)).forEach(e->e.setBalance(money + e.getBalance()));
        Details.ws.stream().filter(e->e.getUser_id().equals(user) && e.getPassword().equals(pass)).forEach(e->e.setStatement("Money Deposit Rs. " + money + " in your account updated balance: " + (d+money)));
		
        System.out.print("\nMoney Credit successfully...");
		System.out.print("Updated balance is ");
		
		ShowBalanceImple b = new ShowBalanceImple();
    	b.showBalance(user, pass);
	}

}
