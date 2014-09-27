package all;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.CryptoPrimitive;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptorManager {
	private MessageDigest md = null;

	private Cipher encrypt = null;
	private Cipher decrypt = null;

	private SecretKeySpec k = null;
	private byte[] iv = null;

	FileOutputStream fos = null;
	FileInputStream fis = null;

	public EncryptorManager(String password, File f)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			FileNotFoundException {
		md = MessageDigest.getInstance("SHA-256");
		encrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		k = new SecretKeySpec(md.digest(password.getBytes()), "AES");
		encrypt.init(Cipher.ENCRYPT_MODE, k);
		iv = encrypt.getIV();

		fos = new FileOutputStream(f);
		fis = new FileInputStream(f);

		decrypt = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		decrypt.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(iv));

	}

	public void encryptAccounts(ArrayList<Account> accounts)
			throws IllegalBlockSizeException, BadPaddingException, IOException {

		String s = "";
		for (Account account : accounts) {
			s += account;
			s += "\n===========";
		}

		byte[] plainText = s.getBytes();
		fos.write(encrypt.doFinal(plainText));

	}

	public ArrayList<Account> decryptAccounts() throws IOException,
			IllegalBlockSizeException, BadPaddingException {
		ArrayList<Account> decryptedAccounts = new ArrayList<>();
		byte[] encryptedBytes = new byte[(int) fis.getChannel().size()];
		fis.read(encryptedBytes);

		String decryptedString = new String(decrypt.doFinal(encryptedBytes));
		String[] splitedString = decryptedString.split("\n");

		for (int i = 0; i < splitedString.length; i = i + 4) {
			String pseudo = splitedString[i].substring(3);
			String password = splitedString[i + 1].substring(3);
			String email = splitedString[i + 2].substring(3);

			decryptedAccounts
					.add(new Account(email, pseudo, password, null, 0));
		}
		return decryptedAccounts;
	}

	public void close() {
		try {
			fis.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
