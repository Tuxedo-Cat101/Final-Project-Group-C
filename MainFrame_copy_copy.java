import java.awt.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
//foot
public class MainFrame_copy_copy extends JFrame {
    private static final String USER_PASSWORD_FILE = "user_password.txt";
    private static final String ADMIN_PASSWORD_FILE = "admin_password.txt";
    private static final String ENCRYPTION_KEY = "0123456789abcdef";  // 16-byte key for AES

    private final Font mainFont = new Font("Segoe print", Font.BOLD, 18);
    private JTextField tfPassword, tfAdminPassword;
    private JTextArea taMessage;

    public MainFrame_copy_copy() {
        setTitle("W.O.P.R Joshua");
        setSize(500, 600);
        setMinimumSize(new Dimension(300, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialize();
    }

    public void initialize() {
        /*************** Main Panel ***************/
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(139, 0, 0));

        /*************** Message Area ***************/
        taMessage = new JTextArea();
        taMessage.setFont(mainFont);
        taMessage.setEditable(false);
        taMessage.setText("Opening Joshua...\nUser or Admin Mode.");
        JScrollPane scrollPane = new JScrollPane(taMessage);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        /*************** Button Panel ***************/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton btnUserMode = new JButton("User");
        btnUserMode.setFont(mainFont);
        btnUserMode.addActionListener(e -> handleUserMode());

        JButton btnAdminMode = new JButton("Admin");
        btnAdminMode.setFont(mainFont);
        btnAdminMode.addActionListener(e -> handleAdminMode());

        buttonPanel.add(btnUserMode);
        buttonPanel.add(btnAdminMode);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setVisible(true);
    }

   // Handle User Mode
private void handleUserMode() {
    taMessage.setText("User Mode: Verify or Generate password.\n");

    String userPassword = readUserPassword();
    if (userPassword == null) {
        String generatedPassword = generatePassword();
        taMessage.append("Generated Password: " + generatedPassword + "\n");

        // Encrypt and store the password
        storeUserPassword(generatedPassword);

        // Now read the encrypted password from the file, and then decrypt it
        try {
            String encryptedPassword = readUserPassword();  // Read the encrypted password
            String decryptedPassword = decryptPassword(encryptedPassword);  // Decrypt it
            taMessage.append("Decrypted Password: " + decryptedPassword + "\n");
        } catch (Exception e) {
            taMessage.append("Error decrypting password: " + e.getMessage() + "\n");
        }
    } else {
        String enteredPassword = JOptionPane.showInputDialog(this, "Enter password:");
        if (verifyUserPassword(enteredPassword)) {
            taMessage.append("Access granted... Accessing W.O.P.R\n");
        } else {
            taMessage.append("Incorrect password. Access denied.\n");
        }
    }
}
    // Handle Admin Mode
    private void handleAdminMode() {
        taMessage.setText("Enter Admin to reset W.O.P.R.\n");

        String adminPassword = readAdminPassword();
        if (adminPassword == null) {
            String enteredPassword = JOptionPane.showInputDialog(this, "Set Admin:");
            storeAdminPassword(enteredPassword);
            taMessage.append("Admin password set successfully.\n");
        }

        String enteredAdminPassword = JOptionPane.showInputDialog(this, "Enter Admin password:");
        if (verifyAdminPassword(enteredAdminPassword)) {
            taMessage.append("Admin access granted... Acessing W.O.P.R\n");
            taMessage.append("Greetings Professor Falken... Shall we play a game?\n");
            resetUserPassword();
        } else {
            taMessage.append("Incorrect admin password.\n");
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
    public void resetUserPassword() {
        String newPassword = JOptionPane.showInputDialog(this, "Play Thermonuclear War?: ");
        storeUserPassword(newPassword);
        taMessage.append("A strange game. The winning move is not to play \n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame_copy_copy());
    }
}