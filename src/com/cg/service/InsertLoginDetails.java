package com.cg.service;

import java.util.Scanner;

import com.cg.repo.Details;

class delay1 extends Thread{
	public void run() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
public class InsertLoginDetails {
    public String user_name, pass;
	public InsertLoginDetails() {
    	
    }
	public boolean getDetails() {
    	System.out.print("UserName: ");
    	Scanner sc = new Scanner(System.in);
        user_name = sc.next();
    	System.out.print("PassWord: ");
    	pass = sc.next();
    	Details d = new Details();
    	System.out.println("\n\t\t************Varifying Details*************");
    	(new delay1()).run();
    	if(d.varifyLoginCredintial(user_name, pass)) {
    		System.out.println("Detail Varified...");
    		return true;
    	}
    	else
    	{
    		System.out.println("\nUserName/Password is Wrong");
    		(new delay1()).run();
    		(new delay1()).run();
    	}
    	return false;
    }
}
