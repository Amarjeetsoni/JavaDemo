package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import com.cg.bean.WalletMoneyStatement;
import com.cg.repo.Details;

public class PrintTransationImpl implements PrintTransation {

	@Override
	public void printallTransation(String user, String pass) {
          List<WalletMoneyStatement> accounts = Details.ws.stream()
        		  .filter(e->e.getUser_id().equals(user) && e.getPassword().equals(pass))
        		  .collect(Collectors.toList());
          System.out.println("All Transations ARE: \n");
	      for(String str: accounts.get(0).getStatement()) {
	    	  System.out.println(str);
	      }
	}
}
