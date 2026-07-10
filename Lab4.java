/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab.pkg4;
import java.util.Scanner;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
/**
 *
 * @author A
 */
public class Lab4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
        DES des = new DES();
        
        
        SecretKey savedKey = null; 
        try {
            while (true) {
                System.out.println("\n=== DES TERMINAL TOOL ===");
                System.out.println("1. Encrypt  DES 56-bit");
                System.out.println("2. Decrypt (Use Cipher from Option 1)");
                System.out.println("3. Brute-Force Test (Small Key Length)");//can use to larger also 
                System.out.println("4. Exit");
                System.out.print("Select choice: ");
                int choice = input.nextInt();
                input.nextLine(); // Clear buffer
                switch (choice) {
                    case 1:
                        System.out.print("Enter plaintext: ");
                        String pText = input.nextLine();
                        savedKey = des.generateKey(); 
                        byte[] cipherText = des.encrypt(pText, savedKey);
                        System.out.println("Ciphertext (Base64): " + Base64.getEncoder().encodeToString(cipherText));
                        System.out.println("Key saved for Option 2.");
                        break;
                    case 2:
                        if (savedKey == null) {
                            System.out.println("Error: Please run Option 1 first to generate a key.");
                            break;
                        }
                        System.out.print("Enter Ciphertext to decrypt: ");
                        String b64Input = input.nextLine();
                        byte[] decodedCipher = Base64.getDecoder().decode(b64Input);
                        String result = des.decrypt(decodedCipher, savedKey);
                        System.out.println("Decrypted Text: " + result);
                        break;
                    case 3:
                        System.out.print("Enter text to hide: ");
    String secretMsg = input.nextLine();
    System.out.print("Enter bits to crack (try 20 or 24): ");
    int bits = input.nextInt();

   
    byte[] realKeyBytes = new byte[8];
   
    long targetValue = (long)Math.pow(2, bits) - 1; 
    for (int i = 0; i < 8; i++) {
        realKeyBytes[7 - i] = (byte) (targetValue >> (i * 8));
    }
    SecretKey realKey = new SecretKeySpec(realKeyBytes, "DES");
    
    
    byte[] targetCipher = des.encrypt(secretMsg, realKey);
    
  
    des.flexibleBruteForce(targetCipher, bits);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    
    }
    
}
