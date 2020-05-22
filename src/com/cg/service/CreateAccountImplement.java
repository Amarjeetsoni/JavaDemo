package com.cg.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.cg.bean.WalletAccountBean;
import com.cg.bean.WalletMoneyStatement;
import com.cg.repo.Details;

import WalletDaoInterface.WalletDaoLayer;

class delay extends Thread {
	public void run() {
      try {
		sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}       		
 	}
}
public class CreateAccountImplement implements WalletDaoLayer {

	
	@Override
	public void createAccount() throws IOException {
		WalletAccountBean wb;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		System.out.println("\t\t*************Welcome To Create Acoount Page***************\n\n(*) fields are mandatory to fill\n");
		System.out.print("Enter FirstName*: ");
		String first_name = bf.readLine();
		System.out.print("Enter MiddleName: ");
		String middle_name = bf.readLine();
		System.out.print("Enter LastName*: ");
		String last_name = bf.readLine();
		System.out.print("Enter Date of Birth(DD/MM/YYYY)*: ");
		String dob = bf.readLine();
		System.out.print("Enter Age: ");
		int age = Integer.parseInt(bf.readLine());
		System.out.print("Enter MobileNo*: ");
		String mobileNum = bf.readLine();
		System.out.print("Enter emailID*: ");
		String email = bf.readLine();
		String user_name = null;
		String pass;
		System.out.println("\n\t\t\t:::Detail Varifying:::");
		new delay().run();
		Details d = new Details();
		if(d.varifyAccountDetails(email, mobileNum)) {
			System.out.print("Details varified\n\n");
			System.out.println("Now Choose a user Name:\n===> Press 1 for Manual Enter\n===> Press 2 from suggestions\nChoose: ");
			int num = sc.nextInt();
			if(num == 1) {
				System.out.print("Enter User Name: ");
				user_name = sc.next();
				while(d.check_name(user_name) == false) {
					System.out.println("UserName already exist Try for another..");
					System.out.print("Enter User Name: ");
					user_name = sc.next();
				}
				System.out.print("User_name varified...\nChoose a Password(minimum of 8 charater): ");
				pass = sc.next();
				while(pass.length() < 8) {
					System.out.print("password Length is less then 8 charaters try again..\n\\nChoose a Password(minimum of 8 charater): ");
					pass = sc.next();
				}
				
				wb = new WalletAccountBean(first_name, middle_name,last_name,dob, age, mobileNum, email, user_name, pass, 0);
				d.addAccount(wb);
				WalletMoneyStatement acc = new WalletMoneyStatement(user_name, pass, "Acoount Created..");
				d.addStatement(acc);
				System.out.println("Account successfully Created...");
			}
			else if(num == 2) {
				List<String> str = new ArrayList<String>();
				String str1 = (first_name) + "_" + (last_name) + mobileNum.substring(mobileNum.length()-4);
				String str2 = (first_name) +  "_" + mobileNum.substring(mobileNum.length()-4);
				String str3 = (first_name) +  "_" + mobileNum.substring(mobileNum.length()-5) + last_name;
				if(d.checkUserName(str1)) {
					str.add(str1);
				}
				if(d.checkUserName(str2)) {
					str.add(str2);
				}
				if(d.checkUserName(str3)) {
					str.add(str3);
				}
				if(str.size() == 0) {
					System.out.println("NO Suggestion available.. Enter manuualy\n");
					System.out.print("Enter User Name: ");
					user_name = sc.next();
					while(d.check_name(user_name) == false) {
						System.out.println("UserName already exist Try for another..");
						System.out.print("Enter User Name: ");
						user_name = sc.next();
					}
				}
				else {
					System.out.println(str);
					System.out.print("Select any One of them: ");
					int check = sc.nextInt();
					if(check < str.size()) {
						user_name = str.get(check-1);
					}
				}
				System.out.print("User_name varified...\nChoose a Password(minimum of 8 charater): ");
				pass = sc.next();
				while(pass.length() < 8) {
					System.out.print("password Length is less then 8 charaters try again..\n\nChoose a Password(minimum of 8 charater): ");
					pass = sc.next();
				}
				
				wb = new WalletAccountBean(first_name, middle_name,last_name,dob, age, mobileNum, email, user_name, pass, 0);
				d.addAccount(wb);
				WalletMoneyStatement acc = new WalletMoneyStatement(user_name, pass, "Acoount Created..");
				d.addStatement(acc);
				System.out.println("Account successfully Created...");
			}
			else {
				 System.out.println("Invalid Entery...");
				 
			}
		}
		else
		{
			System.out.print("EmailId/MobileNumber already exist or not valid, Try to login...");
		}
//		sc.close();
	}

	@Override
	public void depositBalance(double money, String user, String pass) {
		// TODO Auto-generated method stub
		List<WalletAccountBean> dp = Details.wb.stream().filter(e->e.getUser_name().equals(user) && e.getPassword().equals(pass)).collect(Collectors.toList());
		double d = dp.get(0).getBalance();
		Details.wb.stream().filter(e->e.getUser_name().equals(user) && e.getPassword().equals(pass)).forEach(e->e.setBalance(money + e.getBalance()));
        Details.ws.stream().filter(e->e.getUser_id().equals(user) && e.getPassword().equals(pass)).forEach(e->e.setStatement("Money Deposit Rs. " + money + " in your account updated balance: " + (d+money)));
		
        System.out.print("\nMoney Credit successfully...");
		System.out.print("Updated balance is ");
		
		ShowBalanceImple b = new ShowBalanceImple();
    	b.showBalance(user, pass);
		
	}

	@Override
	public void foundTransferAmount(String user1, String pass, String user2, double bal) {
		// TODO Auto-generated method stub
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

	@Override
	public void printallTransation(String user, String pass) {
		// TODO Auto-generated method stub
		 List<WalletMoneyStatement> accounts = Details.ws.stream()
       		  .filter(e->e.getUser_id().equals(user) && e.getPassword().equals(pass))
       		  .collect(Collectors.toList());
         System.out.println("All Transations ARE: \n");
	      for(String str: accounts.get(0).getStatement()) {
	    	  System.out.println(str);
	      }
		
	}

	@Override
	public void showBalance(String user_name, String pass) {
		// TODO Auto-generated method stub
		Details.wb.stream().filter(e->e.getUser_name().equals(user_name) && e.getPassword().equals(pass)).forEach(e->System.out.print(e.getBalance()));
		
	}

	@Override
	public void withdrwAmmount(Double d, String user, String pass) {
		// TODO Auto-generated method stub
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
