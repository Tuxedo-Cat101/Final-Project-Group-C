
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//FILE SYSTEM
public class datastore2 {

// Define a static constant key for AES encryption (Should be kept secret)
    private static final String ENCRYPTION_KEY = "1234567890abcdef"; // 16-byte key for AES-128

    // Assigns the folder where serial numbers and astronaut data is entered.
    @SuppressWarnings("unused")
    File serialfolder = new File("serialnumbers");
    File unassignedAstros = new File("unassignedastros");
    File assignedAstros = new File("assignedastros");
    File serials = new File("serialnumbers/serialnumber.txt");
    File spaceshipfolder = new File("spaceshipdata");

    int numofAstros;

    public void startupcheck(){
        if (new File("serialnumbers").exists()){
        }else{
            File serialfolder = new File("serialnumbers");
        }
        if (new File("serialnumbers/serialnumber.txt").exists()){
        }else{
            File serials = new File("serialnumbers/serialnumber.txt");
        }
        if (new File("unassignedastros").exists()){}else{
            File astrofolder = new File("unassignedastros");
        }
        if (new File("assignedastros").exists()){}else{
            File astrofolder = new File("assignedastros");
        }
        if (new File("spaceshipdata").exists()){}else{
            File spaceshipfolder = new File("spaceshipdata");
        }
    }

    //Finds what the serialnumber is with incrementation
    public String getSerialNumber() {
        int serialNumber = 420;  // Starting serial number
        File file = new File("serialnumbers/serialnumber.txt");
        
        if (file.exists()) {
            try {
                // Read all lines of the file into a List
                List<String> lines = Files.readAllLines(Paths.get("serialnumbers/serialnumber.txt"));
                
                // Check if the first line is empty or if the file is empty
                if (lines.isEmpty() || lines.get(0).trim().isEmpty()) {
                    return Integer.toString(serialNumber);
                }
                
                // Calculate the serial number based on the number of existing lines
                int lineCount = lines.size();  // Count the number of lines in the file
                
                // Increment the serial number based on the line count
                return Integer.toString(serialNumber + (lineCount * 10));
                
            } catch (IOException e) {
                System.err.println("Error reading the file.");
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("No spaceship with that name exists.");
            return null;
        }
    }

    //Writes incremented serialnumber into serialnumber.txt
    public void updateSerialNumber(String serialnumber) {
        File serials = new File("serialnumbers/serialnumber.txt");
    
        try {
            // Check if the file exists and if it's empty
            List<String> lines = Files.readAllLines(serials.toPath());
    
            // If the file is empty, write the serial number directly
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(serials, true))) {
                if (!lines.isEmpty()) {
                    writer.newLine(); // Only add a new line if there is already content
                }
                writer.write(serialnumber); // Write the new serial number
            }
    
        } catch (IOException e) {
            System.err.println("Error while updating the serial number file.");
            e.printStackTrace();
        }
    }

   // Method to save astronaut info to a file with encryption
    public static boolean saveAstronautInfo(String name, String DateOfBirth, String serialNumber, String Address, String Email,
                                            String PhoneNum, String PayRate, double Weight, String NextOfKin, String Status) {
        String filename = "unassignedastros/" + serialNumber + ".txt";
        File file = new File(filename);

        if (file.exists()) {
            System.out.println("Astronaut with that serial number already exists.");
            return false;
        } else {
            // Prepare the astronaut info string
            String astronautInfo = "Serial Number: " + serialNumber + "\n"
                    + "Name: " + name + "\n"
                    + "Date of Birth: " + DateOfBirth + "\n"
                    + "Address: " + Address + "\n"
                    + "Email: " + Email + "\n"
                    + "Phone Number: " + PhoneNum + "\n"
                    + "Pay Rate: " + PayRate + "\n"
                    + "Weight: " + Double.toString(Weight) + "\n"
                    + "Next of Kin: " + NextOfKin + "\n"
                    + "Status: " + Status + "\n";

            try {
                // Encrypt the astronaut info
                String encryptedInfo = encrypt(astronautInfo);

                // Write the encrypted info to the file
                try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                    writer.print(encryptedInfo);
                    System.out.println("Astronaut information saved.");
                }
            } catch (Exception e) {
                System.err.println("An error occurred while encrypting or saving astronaut info.");
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }

    // Encrypt method using AES
        // AES encryption key - ensure this is 16 bytes for AES-128
        public static String encrypt(String data) throws Exception {
            // AES key - use your own key or generate it securely
            SecretKeySpec keySpec = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
    
            // Create AES cipher instance with CBC mode and PKCS5 padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    
            // Generate a random IV (Initialization Vector) for each encryption
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] iv = cipher.getIV();
    
            // Encrypt the data
            byte[] encryptedData = cipher.doFinal(data.getBytes("UTF-8"));
    
            // Combine IV and encrypted data for easy decryption later
            byte[] combinedData = new byte[iv.length + encryptedData.length];
            System.arraycopy(iv, 0, combinedData, 0, iv.length);
            System.arraycopy(encryptedData, 0, combinedData, iv.length, encryptedData.length);
    
            // Return the encrypted data as a Base64 encoded string
            return Base64.getEncoder().encodeToString(combinedData);
        }
    
        public static String decrypt(String encryptedData) throws Exception {
            // AES key - same as used during encryption
            SecretKeySpec keySpec = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
    
            // Decode the encrypted data from Base64
            byte[] combinedData = Base64.getDecoder().decode(encryptedData);
    
            // Extract the IV (first 16 bytes)
            byte[] iv = new byte[16];
            System.arraycopy(combinedData, 0, iv, 0, iv.length);
    
            // Extract the encrypted data (after the IV)
            byte[] encryptedBytes = new byte[combinedData.length - iv.length];
            System.arraycopy(combinedData, iv.length, encryptedBytes, 0, encryptedBytes.length);
    
            // Create AES cipher instance with CBC mode and PKCS5 padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
    
            // Decrypt the data
            byte[] decryptedData = cipher.doFinal(encryptedBytes);
    
            // Return the decrypted data as a string
            return new String(decryptedData, "UTF-8");
        }
    
        // Method to read astronaut info from a file, decrypt it, and display it
        public static void readAstronautInfo(String serialNumber) {
            String filename = "unassignedastros/" + serialNumber + ".txt";
            File file = new File(filename);
    
            if (file.exists()) {
                try {
                    // Read the encrypted astronaut info from the file into a string
                    String encryptedInfo = Files.readString(file.toPath()).trim();
    
                    // Decrypt the encrypted info after reading the file
                    String decryptedInfo = decrypt(encryptedInfo);  // Decrypting the data
    
                    // Print the decrypted astronaut info
                    System.out.println("Astronaut Information (Decrypted): ");
                    System.out.println(decryptedInfo);
    
                } catch (Exception e) {
                    System.err.println("An error occurred while reading or decrypting astronaut info.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("No astronaut with that serial number exists.");
            }
        }
    
    // Method to edit astronaut information and save the updated details to the file
    public static void editAstronautInfo(String name, String DateOfBirth, String serialNumber, String Address, String Email,
                                         String PhoneNum, String PayRate, double Weight, String NextOfKin, String Status) {
        String filename = "unassignedastros/" + serialNumber + ".txt";
        File file = new File(filename);
        if (file.exists()) {
            // Read the existing encrypted file content
            StringBuilder encryptedInfo = new StringBuilder();
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    encryptedInfo.append(scanner.nextLine()).append("\n");
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found while reading: " + filename);
                e.printStackTrace();
                return;
            }
            // Decrypt the existing astronaut info
            try {
                // Update the astronaut info with the new data
                String updatedInfo = "Serial Number: " + serialNumber + "\n"
                        + "Name: " + name + "\n"
                        + "Date of Birth: " + DateOfBirth + "\n"
                        + "Address: " + Address + "\n"
                        + "Email: " + Email + "\n"
                        + "Phone Number: " + PhoneNum + "\n"
                        + "Pay Rate: " + PayRate + "\n"
                        + "Weight: " + Double.toString(Weight) + "\n"
                        + "Next of Kin: " + NextOfKin + "\n"
                        + "Status: " + Status + "\n";

                // Encrypt the updated astronaut info
                String encryptedUpdatedInfo = encrypt(updatedInfo);  // Encrypt the new info

                // Write the encrypted updated info back to the file
                try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                    writer.print(encryptedUpdatedInfo);  // Write encrypted updated info

                    System.out.println("Astronaut information updated and re-encrypted.");
                } catch (IOException e) {
                    System.err.println("An error occurred while updating astronaut info.");
                    e.printStackTrace();
                }

            } catch (Exception e) {
                System.err.println("An error occurred during decryption.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No astronaut with that serial number exists.");
        }
    }

    public static void main(String[] args) {
        // Test the methods
        saveAstronautInfo("John Doe", "1990-01-01", "12345", "123 Space St", "johndoe@example.com", "123-456-7890", "$5000", 70.5, "Jane Doe", "Active");

        // Example of reading astronaut info
        readAstronautInfo("12345");

        // Example of editing astronaut info
        editAstronautInfo("John Doe", "1990-01-01", "12345", "123 Space St", "johnnew@example.com", "123-456-7890", "$6000", 72.5, "Jane Doe", "Inactive");
    }





    // Method to delete astronaut information
    public void removeAstronautInfo(String serialNumber) throws FileNotFoundException {
        // Construct the filename from the serial number
        File data = new File("unassignedastros/" + serialNumber + ".txt");
        int numberToDelete = Integer.parseInt(serialNumber);
        // Create a temporary file to write the remaining numbers
        File tempFile = new File("serialnumbers/tempFile.txt");
        Scanner serialFile = new Scanner(this.serials);
        int numOfLines = 0;
        this.numofAstros = search();
        boolean numberFound = false;
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            // Read the file line by line
            while (serialFile.hasNextLine()) {
                numOfLines++;
                line = serialFile.nextLine();
                try {
                    if (numberToDelete != Integer.parseInt(line)) {
                        writer.write(line);
                        if (numOfLines < this.numofAstros) {
                            writer.write("\n");
                        }
                    }
                    if (numberToDelete == Integer.parseInt(line)) {
                        writer.newLine();
                        numberFound = true;
                    }
                } catch (Exception e) {
                    // Stuff is broke here
                    // Empty line
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        serialFile.close();
        if (numberFound) {
            data.delete();
            this.serials.delete();
            boolean success = tempFile.renameTo(this.serials);
            if (success) {
                System.out.println("Astronaut data successfully deleted.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            tempFile.delete();
            System.out.println("Astronaut with that serial number not found.");
        }
    }

    //Finds how many astronauts in total there are
    public static int search() {
        File directory = new File("unassignedastros");
        File file = new File("assignedastros");
        int numberOfFiles = 0;
        for (File element : directory.listFiles()) {
            if (element.isDirectory()) {
                //add the number of files of subfolder
                numberOfFiles += search();
            } else {
                numberOfFiles++;
            }
        }
        for (File element : file.listFiles()) {
            if (element.isDirectory()){
                //add the number of files of subfolder
                numberOfFiles += search();
            } else {
                numberOfFiles++;
            }
        }
        //return number of files found
        return numberOfFiles;
    }

    //Saves spaceship info
    public static boolean saveSpaceShipData(String spaceshipName, Double fuelcapacity, int shipAstroCapacity) {
        String filename = "spaceshipdata/" + spaceshipName + ".txt";
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("Spaceship with that name already exists.");
            return false;
        } else {
            String spaceshipInfo = "Space Ship Name: " + spaceshipName + "\n"
                    + "Fuel Capacity: " + Double.toString(fuelcapacity) + "\n"
                    + "Astronaut Capacity: " + Integer.toString(shipAstroCapacity) + "\n"
                    + "Current Fuel Amount: 0"+ "\n"
                    + "Current Crew Onboard: 0";
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.print(spaceshipInfo);
                System.out.println("Spaceship information saved. ");
                writer.close();
            } catch (IOException e) {
                System.err.println("An error occurred while saving astronaut info.");
                e.printStackTrace();
            }
            return true;
        }
    }

    //Displays spaceship info
    public static void readSpaceshipInfo(String spaceshipName) {
        String filename = "spaceshipdata/" + spaceshipName + ".txt";
        File file = new File(filename);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + filename);
                e.printStackTrace();
            }
        } else {
            System.out.println("No spaceship with that name exists.");
        }
    }

    //Displays all spaceship names
    public static void readSpaceshipNames(){
        String filename = "spaceshipdata";
        File file = new File(filename);
        String returner;
        for (File element : file.listFiles()) {
            returner = element.getName();
            System.out.println("Spaceship: "+returner.replace(".txt", ""));
        }
    }
    
    //Loads astronauts onto selected spaceship but not over the ships capactiy
    public static void loadAstros(String spaceshipName) {
        int i = getCurrentCrew(spaceshipName);
        int crewCount = 0;
        File file = new File("spaceshipdata/" + spaceshipName + ".txt");
        if (file.exists()) {
            int shipAstroCapacity = getShipCapacity(spaceshipName);
            File directory = new File("unassignedastros");
            File targetDirectory = new File("assignedastros");
            // Loop through the unassigned astronauts untilthe ship's capacity is reached
            for (File element : directory.listFiles()) {
                Path sourcePath = Paths.get("unassignedastros/" + element.getName());
                Path targetPath = Paths.get("assignedastros/" + element.getName());
                if (i >= shipAstroCapacity) {
                    crewCount = shipAstroCapacity;
                    setCurrentCrew(spaceshipName, crewCount);
                    System.out.println("Ship already at capacity.");
                    return;
                }
                try {
                    Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    crewCount++;
                    i++;
                } catch (IOException e) {
                    System.err.println("Error moving file: " + element.getName());
                    e.printStackTrace();
                }
                setCurrentCrew(spaceshipName, crewCount);
            }
            System.out.println(spaceshipName + " successfully loaded with astronauts.");
        } else {
            System.out.println("No spaceship with that name exists.");
        }
    }

    //Gets spaceships astronaut capacity
    public static int getShipCapacity(String spaceshipName){
        String filename = "spaceshipdata/"+ spaceshipName+".txt";
        File file = new File(filename);
        int shipAstroCapacity;
        String line = "0.0";
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                List<String> lines = Files.readAllLines(Paths.get("spaceshipdata/"+ spaceshipName+".txt"));
                line = lines.get(2);
                line = line.replace("Astronaut Capacity: ","");
                scanner.close();
            } catch (Exception e) {
                System.err.println("File not found." );
                e.printStackTrace();
            }
            shipAstroCapacity = Integer.parseInt(line);
            return shipAstroCapacity;
        } else {
            System.out.println("No spaceship with that name exists.");
        }
        return 0;

    }

    //Gets spaceships fuel capacity
    public static double getShipFuelCapacity(String spaceshipName){
        String filename = "spaceshipdata/"+ spaceshipName+".txt";
        File file = new File(filename);
        double fuelcapacity;
        String line = "0.0";
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                List<String> lines = Files.readAllLines(Paths.get("spaceshipdata/"+ spaceshipName+".txt"));
                line = lines.get(1);
                line = line.replace("Fuel Capacity: ","");
                scanner.close();
            } catch (Exception e) {
                System.err.println("File not found." );
                e.printStackTrace();
            }
            fuelcapacity = Double.parseDouble(line);
            return fuelcapacity;
        } else {
            System.out.println("No spaceship with that name exists.");
        }
        return 0.0;
    }

    //Gets the spaceships current fuel
    public static double getCurrentFuel(String spaceshipName){
        String filename = "spaceshipdata/"+ spaceshipName+".txt";
        File file = new File(filename);
        double currentFuel;
        String line = "0.0";
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                List<String> lines = Files.readAllLines(Paths.get("spaceshipdata/"+ spaceshipName+".txt"));
                line = lines.get(3);
                line = line.replace("Current Fuel Amount: ","");
                scanner.close();
            } catch (Exception e) {
                System.err.println("File not found." );
                e.printStackTrace();
            }
            currentFuel = Double.parseDouble(line);
            return currentFuel;
        } else {
            System.out.println("No spaceship with that name exists.");
        }
        return 0.0;
    }

    //Gets how many assigned astros a ship has.
    public static int getCurrentCrew(String spaceshipName){
        String filename = "spaceshipdata/"+ spaceshipName+".txt";
        File file = new File(filename);
        int currentCrew;
        String line = "0.0";
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                List<String> lines = Files.readAllLines(Paths.get("spaceshipdata/"+ spaceshipName+".txt"));
                line = lines.get(4);
                line = line.replace("Current Crew Onboard: ","");
                scanner.close();
            } catch (Exception e) {
                System.err.println("File not found." );
                e.printStackTrace();
            }
            currentCrew = Integer.parseInt(line);
            return currentCrew;
        } else {
            System.out.println("No spaceship with that name exists.");
        }
        return 0;
    }

    //Sets how many assigend astros a ship has
    public static void setCurrentCrew(String spaceshipName, int crew){
        String filePath = "spaceshipdata/"+spaceshipName+".txt";
        int lineNumber = 5; // Line number to modify (1-based index)
        String newContent = "Current Crew Onboard: "+Integer.toString(crew);
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Check if the line number is within the range of the file
            if (lineNumber > 0 && lineNumber <= lines.size()) {
                // Modify the specific line
                lines.set(lineNumber - 1, newContent);

                // Write the modified lines back to the file
                Files.write(Paths.get(filePath), lines);
            } else {
                System.out.println("Line number out of range.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Sets the ships current fuel
    public static void setCurrentFuel(double loadFuel, String spaceshipName){
        String filePath = "spaceshipdata/"+spaceshipName+".txt";
        int lineNumber = 4; // Line number to modify (1-based index)
        double fuel;
        fuel = Math.min(getCurrentFuel(spaceshipName) + loadFuel, getShipFuelCapacity(spaceshipName));
        String newContent = "Current Fuel Amount: "+Double.toString(fuel);

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Check if the line number is within the range of the file
            if (lineNumber > 0 && lineNumber <= lines.size()) {
                // Modify the specific line
                lines.set(lineNumber - 1, newContent);

                // Write the modified lines back to the file
                Files.write(Paths.get(filePath), lines);
            } else {
                System.out.println("Line number out of range.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Makes sure ship meets requirements to launch
    public static boolean check(String spaceshipName){
        File file = new File("assignedastros");
        int numberOfFiles = 0;
        boolean check1;
        boolean check2;
        if (getCurrentFuel(spaceshipName) > getShipFuelCapacity(spaceshipName)/4 ){
            check1 = true;
        } else{
            check1 = false;
            System.out.println("The selected spaceship has less than a quarter of its maximum fuel capacity. \nPlease fuel the spaceship to at least 1/4 of its fuel capacity.");
        }
        if (getCurrentCrew(spaceshipName) > 0){
            check2 = true;
        }else{
            check2 = false;
            System.out.println("The selected spaceship has no assigned astronauts. \nPlease assign at least 1 astronaut to launch.");
        }
        if (check1 == true && check2 == true){
            return true;
        }else{
            return false;
        }
    }
}
