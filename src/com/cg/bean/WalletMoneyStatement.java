package com.cg.bean;

import java.util.ArrayList;
import java.util.List;

public class WalletMoneyStatement {
    protected String user_id;
    protected String password;
    protected List<String> statement = new ArrayList<String>();
    
    public WalletMoneyStatement(String user_id, String pass, String st) {
		this.user_id = user_id;
		this.password = pass;
		this.statement.add(st);
	}
    
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement.add(statement);
	}
    
}
