package passwordkeeper;

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

        private String fileName = null;
        
	public EncryptorManager(String password, String fileName)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException,
			FileNotFoundException {
                
		md = MessageDigest.getInstance("SHA-256");
		encrypt = Cipher.getInstance("AES");
		k = new SecretKeySpec(md.digest(password.getBytes()), "AES");
		encrypt.init(Cipher.ENCRYPT_MODE, k);

		decrypt = Cipher.getInstance("AES");
		decrypt.init(Cipher.DECRYPT_MODE, k);
                
                this.fileName = fileName;

	}

	public void encryptAccounts(ArrayList<Account> accounts)
			throws IllegalBlockSizeException, BadPaddingException, IOException {

                FileOutputStream fos = new FileOutputStream(fileName);
                
		String s = "";
		for (Account account : accounts) {
			s += account;
			s += "===========\n";
		}
              
		byte[] plainText = s.getBytes();
                byte[] encrypted = encrypt.doFinal(plainText);
		fos.write(encrypted);
                fos.close();
                

	}

	public ArrayList<Account> decryptAccounts() throws IOException,
			IllegalBlockSizeException, BadPaddingException {
		
                ArrayList<Account> decryptedAccounts = new ArrayList<>();

                FileInputStream fis = new FileInputStream(fileName);
                byte[] encryptedBytes = new byte[(int) fis.getChannel().size()];
		fis.read(encryptedBytes);
                if(encryptedBytes.length == 0){
                    return null;
                }
                
		String decryptedString = new String(decrypt.doFinal(encryptedBytes));
		String[] splitedString = decryptedString.split("\n");

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

}
