package all;

import java.util.ArrayList;

public class AccountManager {

	private ArrayList<Account> accounts = null;
	private EncryptorManager encryptManager = null;
	
	public AccountManager() {
		accounts = new ArrayList<>();

	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

}
