/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab.pkg4;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
public class DES {
    public  SecretKey generateKey()throws Exception{
        KeyGenerator key=KeyGenerator.getInstance("DES");
        return key.generateKey();
    }
    public  byte[]encrypt(String text, SecretKey key)throws Exception{
        Cipher cipher=Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(text.getBytes());
    }
    public  String decrypt(byte[]ciphertext,SecretKey key)throws Exception{
        Cipher cipher=Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] Bytes=cipher.doFinal(ciphertext);
        return new String(Bytes);
    }
  public   void flexibleBruteForce(byte[] ciphertext, int bitsToCrack) {
        System.out.println("\n--- STARTING  BRUTE FORCE ---");
    System.out.println("Attempting to crack " + bitsToCrack + " bits...");
    
    // Calculate the maximum number of combinations (2 to the power of bitsToCrack)
    long maxIterations =(long)Math.pow(2, bitsToCrack); 
    System.out.println("Maximum possible attempts: " + maxIterations);

    byte[] guessedKey = new byte[8]; // Start with a blank key
    long startTime = System.currentTimeMillis();

    // all posepple
    try {
        Cipher cipher = Cipher.getInstance("DES");
    
        for (long attempt = 0; attempt < maxIterations; attempt++) {
          
            for (int i = 0; i < 8; i++) {
                guessedKey[7 - i] = (byte) (attempt >> (i * 8));
            }

            try {
                // Test  key
                SecretKeySpec secretKey = new SecretKeySpec(guessedKey, "DES");
                
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                
                byte[] decryptedBytes = cipher.doFinal(ciphertext);
                String result = new String(decryptedBytes);
                
                // look for word if add file with a lot of words he will find it like
                if (result.contains("Cybersecurity")||result.contains("Mohammed")||result.contains("Hello")) {
                    long endTime = System.currentTimeMillis();
                    System.out.println("\n! KEY CRACKED SUCCESSFULLY!");
                    System.out.println("Total Attempts: " + attempt);
                    System.out.println("Time Taken: " + ((endTime - startTime) / 1000) + " seconds");
                    System.out.println("Recovered Text: " + result);
                    return; 
                }
            } catch (Exception e) {
                //System.out.println(e);
            }

            // print every liilons u know it's not frozen after tem mill
            if (attempt % 10000000 == 0 && attempt > 0) {
                System.out.println("Still working... tested " + attempt + " keys.");
            }
        } //for loop
        
    } catch (Exception e) {
        System.out.println("Critical Error: " + e.getMessage());
    }
    
    System.out.println("\nAttack finished Key was not found within " + bitsToCrack + " bits.");
}
}