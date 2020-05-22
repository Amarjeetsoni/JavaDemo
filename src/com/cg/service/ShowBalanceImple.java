package com.cg.service;

import com.cg.repo.Details;

public class ShowBalanceImple extends Details implements ShowBalance {

	@Override
	public void showBalance(String user_name, String pass) {
	    	Details.wb.stream().filter(e->e.getUser_name().equals(user_name) && e.getPassword().equals(pass)).forEach(e->System.out.print(e.getBalance()));
	}
  
}
