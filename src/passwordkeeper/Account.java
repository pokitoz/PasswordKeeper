package passwordkeeper;

import java.sql.Date;

public class Account {

	private String number;
	private String email;
	private String pseudo;
	private String password;

	public Account(String email, String pseudo, String password, Date date,
			int number) {

		this.number = String.valueOf(number);
		this.email = email;
		this.pseudo = pseudo;
		this.password = password;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return    "Ps=" + pseudo + "\n"
                        + "Pw=" + password + "\n"
			+ "Em=" + email + "\n";
	}
}
