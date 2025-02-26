import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class MainFrame_copy_copy {
    private static final String USER_PASSWORD_FILE = "user_password.txt";
    private static final String ADMIN_PASSWORD_FILE = "admin_password.txt";
    private static final String ENCRYPTION_KEY = "0123456789abcdef";  // 16-byte key for AES

    public static void main(String[] args) {
        // Ask if user or admin mode is chosen
        System.out.print("Enter 'user' for User Mode or 'admin' for Admin Mode: ");
        String mode = System.console().readLine().trim().toLowerCase();

        if ("user".equals(mode)) {
            handleUserMode();
        } else if ("admin".equals(mode)) {
            handleAdminMode();
        } else {
            System.out.println("Invalid mode selected. Exiting program.");
            System.exit(1);  // Exit the program if an invalid mode is selected
        }
    }

            // Handle User Mode
        private static void handleUserMode() {
            System.out.println("User Mode: Verify or Generate password.");

            // Ask for user password input
            System.out.print("Enter User password: ");
            String enteredPassword = System.console().readLine();  // Read password from console

            String userPassword = readUserPassword();
            if (userPassword == null) {
                System.out.println("No existing user password found.");

                // Generate a new random password
                String generatedPassword = generatePassword();
                System.out.println("Generated Password: " + generatedPassword);

                // Encrypt and store the generated password
                storeUserPassword(generatedPassword);
                System.out.println("New password stored and encrypted.");

                // Now, we read the encrypted password from the file, and then decrypt it
                try {
                    String encryptedPassword = readUserPassword();  // Read the encrypted password
                    String decryptedPassword = decryptPassword(encryptedPassword);  // Decrypt it
                    System.out.println("Decrypted Password: " + decryptedPassword);
                } catch (Exception e) {
                    System.out.println("Error decrypting password: " + e.getMessage());
                }

                // Exit the system after password is generated and stored
                System.exit(0);
            } else {
                if (verifyUserPassword(enteredPassword)) {
                    System.out.println("Access granted... Accessing system.");
                } else {
                    System.out.println("Incorrect password. Access denied.");
                    System.exit(1);  // Exit the program if password is incorrect
                }
            }
        }


    // Handle Admin Mode
    private static void handleAdminMode() {
        System.out.println("Enter Admin to reset W.O.P.R.");

        String adminPassword = readAdminPassword();
        if (adminPassword == null) {
            System.out.print("Set Admin password: ");
            String enteredPassword = System.console().readLine();  // Set the admin password
            storeAdminPassword(enteredPassword);
            System.out.println("Admin password set successfully.");
        }

        System.out.print("Enter Admin password: ");
        String enteredAdminPassword = System.console().readLine();  // Read admin password from console
        if (verifyAdminPassword(enteredAdminPassword)) {
            System.out.println("Admin access granted... Accessing W.O.P.R");
            System.out.println("Greetings Professor Falken... Shall we play a game?");
            resetUserPassword();
        } else {
            System.out.println("Incorrect admin password. Exiting program.");
            System.exit(1);  // Exit the program if the admin password is incorrect
        }
    }

    // Generate a random password of 12 characters
    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        for (int i = 0; i < 12; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    // Encrypt and store the user password
    public static void storeUserPassword(String password) {
        try {
            String encryptedPassword = encryptPassword(password);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_PASSWORD_FILE))) {
                writer.write(encryptedPassword);
            }
        } catch (Exception e) {
            System.err.println("Error storing user password: " + e.getMessage());
        }
    }

    // Encrypt the password using AES
    public static String encryptPassword(String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt the user password
    public static String decryptPassword(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    // Verify the user password
    public static boolean verifyUserPassword(String enteredPassword) {
        try {
            String storedEncryptedPassword = readUserPassword();
            String decryptedPassword = decryptPassword(storedEncryptedPassword);
            return decryptedPassword.equals(enteredPassword);
        } catch (Exception e) {
            System.err.println("Error verifying user password: " + e.getMessage());
            return false;
        }
    }

    // Read the encrypted user password
    public static String readUserPassword() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_PASSWORD_FILE))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;  // Password file doesn't exist
        }
    }

    // Store the admin password
    public static void storeAdminPassword(String password) {
        try {
            String encryptedPassword = encryptPassword(password);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_PASSWORD_FILE))) {
                writer.write(encryptedPassword);
            }
        } catch (Exception e) {
            System.err.println("Error storing admin password: " + e.getMessage());
        }
    }

    // Verify the admin password
    public static boolean verifyAdminPassword(String enteredPassword) {
        try {
            String storedEncryptedPassword = readAdminPassword();
            String decryptedPassword = decryptPassword(storedEncryptedPassword);
            return decryptedPassword.equals(enteredPassword);
        } catch (Exception e) {
            System.err.println("Error verifying admin password: " + e.getMessage());
            return false;
        }
    }

    // Read the encrypted admin password
    public static String readAdminPassword() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_PASSWORD_FILE))) {
            return reader.readLine();
        } catch (IOException e) {
            return null;  // Admin password file doesn't exist
        }
    }

    // Reset the user password by the admin
    public static void resetUserPassword() {
        System.out.print("New Password: ");
        String newPassword = System.console().readLine();  // Read new password from console
        storeUserPassword(newPassword);
        System.out.println("Password changed");
    }
}
