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

    // Method to check password strength
    public static boolean isPasswordStrong(String password) {
        // Password strength criteria: at least 8 characters, contains letters and numbers
        return password.length() >= 8 && password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*");
    }

    // Method to simulate storing users' encrypted passwords (for example, in a database)
    public static String getCorrectEncryptedPassword() {
        return encryptPassword("PointBreak47!x");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of allowed attempts
        int maxAttempts = 3;
        int attemptsLeft = maxAttempts;

        // Correct encrypted password (in real scenarios, this would come from a database)
        String correctEncryptedPassword = getCorrectEncryptedPassword();

        // Give the user a chance to input password
        while (attemptsLeft > 0) {
            System.out.print("Password: ");
            String inputPassword = scanner.nextLine();

            // Check if the password is strong
            if (!isPasswordStrong(inputPassword)) {
                System.out.println("Password is weak. It must contain at least 8 characters, including letters and numbers.");
                continue; // Skip this iteration and ask for a new password
            }

            // Encrypt the input password and compare it with the correct encrypted password
            if (encryptPassword(inputPassword).equals(correctEncryptedPassword)) {
                System.out.println("Accessing WOPR...");
                System.out.println("Greetings Professor Falken, Shall we play a game? ");
                break; // Exit the loop if password is correct
            } else {
                attemptsLeft--;
                if (attemptsLeft > 0) {
                    System.out.println("Incorrect password. You have " + attemptsLeft + " attempts left.");
                } else {
                    System.out.println("Incorrect password. No attempts left.");
                    // Optionally, you can exit or lock the user out at this point
                }
            }
        }

        // Close scanner resource
        scanner.close();
    }
}