package com.cg.ui;

import java.io.IOException;
import java.util.Scanner;

import com.cg.repo.Details;
import com.cg.service.CreateAccountImplement;
import com.cg.service.DepositImplement;
import com.cg.service.FoundTransferImpl;
import com.cg.service.InsertLoginDetails;
import com.cg.service.PrintTransationImpl;
import com.cg.service.ShowBalanceImple;
import com.cg.service.WithDrawImple;
class DelayPro extends Thread{
	public void run() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
public class WalletApplication {

	public static void main(String[] args) throws IOException {
		System.out.println("==================================  Welcome To XYZ Bank Wallet Application  ======================================");
		Scanner sc = new Scanner(System.in);
		System.out.println("Options: \n==> 1.LogIn\n==> 2.Create a new account");
		System.out.print("Choose: ");
		int num = sc.nextInt();
		InsertLoginDetails lg = new InsertLoginDetails();
	    while(true) {
	    	int breakVal = 0;
		switch(num) {
	    case 1: 
	    	
	    	if(lg.getDetails()) {
	    		System.out.println("::::::::::::::::::::::::::::::   Welcome to your Account '" + lg.user_name + "'      :::::::::::::::::::::");
	    		
	    		System.out.println("\nSelect Any Options:\n==>1. Show Balance\t\t\t==>2. Deposit Amount\n==>3. Withdraw Amount\t\t\t==>4. Fund Transfer\n==>5. Print Transaction\t\t\t==>6. Logout");
	    		System.out.print("Select: ");
	    		int choise = sc.nextInt();
	    		while(choise != 7) {
	    		switch(choise) {
	    		case 1:
	    			System.out.print("The Balance in your Account: ");
	    			new ShowBalanceImple().showBalance(lg.user_name, lg.pass);
	    			break;
	    		case 2:
	    			System.out.print("Enter Amount(deposit): ");
	    			double amount = sc.nextDouble();
	    			new DepositImplement().depositBalance(amount, lg.user_name, lg.pass);
	    			break;
	    		case 3:
	    			System.out.print("Enter Amount(Withdraw): ");
	    			double amount1 = sc.nextDouble();
	    			new WithDrawImple().withdrwAmmount(amount1, lg.user_name, lg.pass);
	    			break;
	    		case 4:
	    			System.out.print("Enter Amount(FundTransfer): ");
	    			double amount2 = sc.nextDouble();
	    			System.out.print("Enter UserId(Reciver): ");
	    			String user2 = sc.next();
	    			if((new Details()).check_name(user2) == false) {
	    				new FoundTransferImpl().foundTransferAmount(lg.user_name, lg.pass, user2, amount2);
	    			}
	    			else {
	    				System.out.println("User Name Not Exist Try Again...");
	    			}
	    			break;
	    		case 5:
	    			System.out.println("Your All Transations: " + lg.user_name + " account");
	    			new PrintTransationImpl().printallTransation(lg.user_name, lg.pass);
	    			break;
	    		case 6:
	    			System.out.println("\t\t\t>>>>>>>  Logging Out  <<<<<<<<");
	    			new DelayPro().run();
	    			lg.user_name = null;
	    			lg.pass = null;
	    			break;
	    		default:
	    			System.out.println(">>>>>  Wrong Choise..... \n try Again..");	
	    		}
	    		if(choise == 6)
	    	    	break;
	    		System.out.println("\nSelect Any Options:\n==>1. Show Balance\t\t\t==>2. Deposit Amount\n==>3. Withdraw Amount\t\t\t==>4. Fund Transfer\n==>5. Print Transaction\t\t\t==>6. Logout");
	    		System.out.print("Select: ");
	    	    choise = sc.nextInt();
	    	    breakVal = choise;
	    	    if(choise == 6)
	    	    	break;
	    	}
	    	}
	    	break;
	    case 2:
	    	CreateAccountImplement cAcc = new CreateAccountImplement();
	    	cAcc.createAccount();
	    	break;	
	    default:
	    	System.out.println("Please choose Correct option...");
    		break;
	    }
		System.out.println("Options: \n==> 1.LogIn\n==> 2.Create a new account\n==> 3. Exit");
		System.out.print("Choose: ");
		num = sc.nextInt();
		
		if(num == 3)
			break;
	    }
	    
	    System.out.println("\n\n\t\t\t>>>>>>>>>>>>>>>  Please Wait....  <<<<<<<<<<<<<<<<<<< ");
	    new DelayPro().run();
	    System.out.println("Thanx For Visiting");
	}
}
