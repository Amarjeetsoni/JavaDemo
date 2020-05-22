package com.cg.bean;


public class WalletAccountBean {
   protected String firstName, middleName, lastName;
   protected String DOB;
   protected int age;
   protected String mobile_no;
   protected String emailId;
   protected String user_name;
   protected String password;
   protected double Balance;
   
public WalletAccountBean(String firstName, String middleName, String lastName, String dOB, int age, String mobile_no,
		String emailId, String user_name, String password, double Balance) {
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
	DOB = dOB;
	this.age = age;
	this.mobile_no = mobile_no;
	this.emailId = emailId;
	this.user_name = user_name;
	this.password = password;
	this.Balance = 0;
}
public double getBalance() {
	return Balance;
}
public void setBalance(double balance) {
	Balance = balance;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getMiddleName() {
	return middleName;
}
public void setMiddleName(String middleName) {
	this.middleName = middleName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getDOB() {
	return DOB;
}
public void setDOB(String dOB) {
	DOB = dOB;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getMobile_no() {
	return mobile_no;
}
public void setMobile_no(String mobile_no) {
	this.mobile_no = mobile_no;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getUser_name() {
	return user_name;
}
public void setUser_name(String user_name) {
	this.user_name = user_name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
   
}
