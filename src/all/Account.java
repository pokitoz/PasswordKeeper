package all;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

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
		return "Ps=" + pseudo + "\n" + "Pw=" + password + "\n"
				+ "Em=" + email;
	}
}
