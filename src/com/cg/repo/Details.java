package com.cg.repo;

import java.util.ArrayList;
import java.util.List;

import com.cg.bean.WalletAccountBean;
import com.cg.bean.WalletMoneyStatement;

public class Details {
    public Details() {
    	
    }
	public static List<WalletAccountBean> wb = new ArrayList<WalletAccountBean>();
    public static List<WalletMoneyStatement> ws = new ArrayList<>();  
  
    public void addAccount(WalletAccountBean w) {
	   wb.add(w);
   }
   
    public void addStatement(WalletMoneyStatement acc) {
    	ws.add(acc);
    }
   public boolean varifyAccountDetails(String emailId, String mobNo) {
	   for(int i = 0; i < wb.size(); i++) {
		   if(wb.get(i).getMobile_no().equals(mobNo)  || wb.get(i).getEmailId().equals(emailId)) {
			   return false;
		   }
	   }
	   String regex = "[0-9]+";
	   if(emailId.substring(emailId.length()-10).equals("@gmail.com") == false || mobNo.length() != 10 || mobNo.matches(regex) == false)
		   return false;
	   
	   return true;
   }
   public boolean varifyLoginCredintial(String userName, String password) {
	   long sw = wb.stream().filter(e->e.getUser_name().equals(userName) && e.getPassword().equals(password)).count();
       if(sw == 0) {
    	   return false;
       }
       return true;
   }
   
   public boolean checkUserName(String userName) {
	   long num = wb.stream().filter(e->e.getUser_name().equals(userName)).count();
       if(num == 0) {
    	   return true;
       }
       return false;
   }
   
   public boolean check_name(String name) {
	   for(int i = 0; i < wb.size(); i++) {
		   if(wb.get(i).getUser_name().equals(name))
			   return false;
	   }
	   return true;
   }
}
