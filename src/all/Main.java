package all;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.NClob;
import java.util.ArrayList;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Ask pass: ");
		String password = sc.nextLine();

		File f = new File("password.pk");

		EncryptorManager enc = null;
		try {
			enc = new EncryptorManager(password, f);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidAlgorithmParameterException
				| FileNotFoundException e) {
			e.printStackTrace();
		}
		if (f.length() == 0) {
			Account c = new Account("Mail", "peeeeu", "tttototo", new Date(0),
					1);

			ArrayList<Account> accs = new ArrayList<Account>();
			accs.add(c);
			try {
				enc.encryptAccounts(accs);
			} catch (IllegalBlockSizeException | BadPaddingException
					| IOException e) {
				e.printStackTrace();
			}

		}

		try {
			ArrayList<Account> acc = enc.decryptAccounts();
			System.out.println(acc);
		} catch (IllegalBlockSizeException | BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		enc.close();

		sc.close();
	}
}
