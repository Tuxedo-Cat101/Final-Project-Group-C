import java.io.*;
import java.security.Key;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AstronautInfoEncrypted {
    
    // AES key (you can store this securely or generate it dynamically)
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1234567890123456"; // 16-byte key for AES-128

    // Encrypt data using AES
    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Encode as Base64 to store in file
    }

    // Decrypt data using AES
    public static String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;
        int year, month, day;

        // Collect astronaut information
        System.out.println("Please enter the name of the new astronaut: ");
        name = scanner.nextLine();
        
        System.out.println("Please enter the year(ex: 1986) the astronaut was born: ");
        year = scanner.nextInt();
        scanner.nextLine();
        
        while (year < 1900) {
            System.out.println("Invalid year, please enter a valid year: ");
            year = scanner.nextInt();
            scanner.nextLine();
        }
        
        System.out.println("Please enter the month(1-12) the astronaut was born: ");
        month = scanner.nextInt();
        scanner.nextLine();
        
        while (month < 1 || month > 12) {
            System.out.println("Invalid month choice, month must be between 1 and 12, please enter a valid month: ");
            month = scanner.nextInt();
            scanner.nextLine();
        }
        
        System.out.println("Please enter the day the astronaut was born on:");
        day = scanner.nextInt();
        scanner.nextLine();
        
        while (day < 1 || day > 31) {
            System.out.println("Invalid day, day must be between 1-31, please enter a valid day: ");
            day = scanner.nextInt();
            scanner.nextLine();
        }

        // Prepare the data string to be written
        String dataToSave = "Astronaut Name: " + name + "\n" +
                            "Born Year: " + year + "\n" +
                            "Born Month: " + month + "\n" +
                            "Born Day: " + day + "\n" +
                            "-----------------------------\n";

        try {
            // Encrypt the data before saving
            String encryptedData = encrypt(dataToSave);

            // Write the encrypted data to a file
            FileWriter fw = new FileWriter("astronaut_info_encrypted.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(encryptedData + "\n");
            bw.close();
            System.out.println("Astronaut information has been encrypted and saved.");
        } catch (Exception e) {
            System.out.println("An error occurred during encryption.");
            e.printStackTrace();
        }

        // Optionally, you can decrypt the data later to verify or retrieve the original info
        try {
            // Reading the encrypted data from the file
            BufferedReader br = new BufferedReader(new FileReader("astronaut_info_encrypted.txt"));
            String encryptedLine;
            while ((encryptedLine = br.readLine()) != null) {
                // Decrypt the line and print the original content
                String decryptedData = decrypt(encryptedLine);
                System.out.println("Decrypted Data: \n" + decryptedData);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("An error occurred during decryption.");
            e.printStackTrace();
        }

        scanner.close(); // Close the scanner
    }
}
