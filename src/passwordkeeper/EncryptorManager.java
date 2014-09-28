package passwordkeeper;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptorManager {
	private MessageDigest md = null;

	private Cipher encrypt = null;
	private Cipher decrypt = null;

	private SecretKeySpec k = null;

	private FileOutputStream fos = null;
	private FileInputStream fis = null;

  
        
	public EncryptorManager(String password)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			FileNotFoundException {
                
		md = MessageDigest.getInstance("SHA-256");
		encrypt = Cipher.getInstance("AES");
		k = new SecretKeySpec(md.digest(password.getBytes()), "AES");
		encrypt.init(Cipher.ENCRYPT_MODE, k);

                fos = new FileOutputStream("ttt.tt");
		fis = new FileInputStream("ttt.tt");

		decrypt = Cipher.getInstance("AES");
		decrypt.init(Cipher.DECRYPT_MODE, k);

	}

	public void encryptAccounts(ArrayList<Account> accounts)
			throws IllegalBlockSizeException, BadPaddingException, IOException {

		String s = "";
		for (Account account : accounts) {
			s += account;
			s += "===========\n";
		}
                
                System.out.println("Encrypt\n\n" + s);

		byte[] plainText = s.getBytes();
                byte[] encrypted = encrypt.doFinal(plainText);
		fos.write(encrypt.doFinal(plainText));
                
                System.out.println(encrypted.length);
                

	}

	public ArrayList<Account> decryptAccounts() throws IOException,
			IllegalBlockSizeException, BadPaddingException {
		ArrayList<Account> decryptedAccounts = new ArrayList<>();

       
                
                
                byte[] encryptedBytes = new byte[160];
		fis.read(encryptedBytes);
                if(encryptedBytes.length == 0){
                    return null;
                }
                System.out.println(encryptedBytes.length);
                
		String decryptedString = new String(decrypt.doFinal(encryptedBytes));
		String[] splitedString = decryptedString.split("\n");

                System.out.println(decryptedString);
                
		for (int i = 0; i < splitedString.length; i = i + 4) {
			String pseudo = splitedString[i].substring(3);
			String password = splitedString[i + 1].substring(3);
			String email = splitedString[i + 2].substring(3);

			decryptedAccounts
					.add(new Account(email, pseudo, password, null, 0));
		}
                
                
                encryptAccounts(decryptedAccounts);
                
         
		
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
