
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class security {

    // Method to encrypt the password using SHA-256 hashing
    public static String encryptPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Hash the password
            byte[] hashedBytes = md.digest(password.getBytes());
            // Convert the byte array into a hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // Correct encrypted password
        String correctEncryptedPassword = encryptPassword("PointBreak");

        Scanner scanner = new Scanner(System.in);

        // Asking the user for the password
        System.out.print("Shall we play a game?: ");
        String inputPassword = scanner.nextLine();

        // Encrypting the input password and comparing it with the correct encrypted password
        if (encryptPassword(inputPassword).equals(correctEncryptedPassword)) {
            // Password is correct
            System.out.println("Access granted...");
            // Here, you can implement the code to show your content
            // For example, you could load a new page or display a message

            // For demonstration, we're printing a success message
            System.out.println("Welcome nerds");
        } else {
            // Password is incorrect
            System.out.println("Incorrect... SPY");
            // Optionally, you can call the main method again for retry
            main(args);  // Recursive call for retry
        }

        scanner.close();
    }
}
