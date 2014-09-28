package passwordkeeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException, IllegalBlockSizeException {
           
              
               try {
                    UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());             

                    } catch (Exception e) {
                      System.err.println("Look and feel not set.");
                    }

             new GUILogin();
               
              
	}
}
