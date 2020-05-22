package WalletDaoInterface;

import java.io.IOException;

public interface WalletDaoLayer {
	 void createAccount() throws IOException;
	 void depositBalance(double money, String user, String pass);
	 void foundTransferAmount(String user1, String pass, String user2, double bal);
	 void printallTransation(String user, String pass);
	 public void showBalance(String user_name, String pass);
	 void withdrwAmmount(Double d, String user, String pass);
}
