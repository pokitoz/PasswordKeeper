/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordkeeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Pokitoz
 */
public class Decrypt {
    
    public static void main(String[] args){
        
    File f = new File("ttt.tt");
    EncryptorManager manager = null;
     
        try {
            manager = new EncryptorManager("testetetet");
    
            Account ca = new Account("TET2", "TT", "Testui", null, 0);
            Account cb = new Account("TET3", "TT", "Testui", null, 0);
            Account cc = new Account("TET4", "TT", "Testui", null, 0);
            Account cd = new Account("TET5", "TT", "Testui", null, 0);
            
        
            ArrayList<Account> accs = new ArrayList<>();
            accs.add(ca);
            accs.add(cb);
            accs.add(cc);
            accs.add(cd);
            
            manager.encryptAccounts(accs);
            manager.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
        } 
    
    }
             
    
}
