package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import com.cg.bean.WalletAccountBean;
import com.cg.repo.Details;

public class FoundTransferImpl implements FoundTransfer {

	@Override
	public void foundTransferAmount(String user1, String pass, String user2, double bal) {
		List<WalletAccountBean> obj = Details.wb.stream().filter(e->e.getPassword().equals(pass) && e.getUser_name().equals(user1)).collect(Collectors.toList());
        double bal1 = obj.get(0).getBalance();
        if(bal1 < bal) {
        	System.out.print("Not Enough Money in your Account: ");
        	ShowBalanceImple b = new ShowBalanceImple();
        	b.showBalance(user1, pass);
        }
        else
        {
        	Details.wb.stream().filter(e->e.getUser_name().equals(user1) && e.getPassword().equals(pass)).forEach(e->e.setBalance(e.getBalance()-bal));
        	Details.wb.stream().filter(e->e.getUser_name().equals(user2)).forEach(e->e.setBalance(e.getBalance() + bal));
        	
        	Details.ws.stream().filter(e->e.getUser_id().equals(user1) && e.getPassword().equals(pass)).forEach(e->e.setStatement("Money Withdraw Rs. " + bal + " from your account to another acoount " + user2 + " updated balance: " + (bal1 - bal)));
        	Details.ws.stream().filter(e->e.getUser_id().equals(user2)).forEach(e->e.setStatement("Money cradited Rs. " + bal + " from account " + user1 + " to your account updated balance: " + (bal1 - bal)));
            System.out.println("Balance Transfered successfull..\nupdated balance: ");
            ShowBalanceImple b = new ShowBalanceImple();
        	b.showBalance(user1, pass);
        }

	}

}
