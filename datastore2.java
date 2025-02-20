
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//FILE SYSTEM
public class datastore2 {

    // Assigns the folder where serial numbers and astronaut data is entered.
    File serialfolder = new File("serialnumbers");
    File astrofolder = new File("astrodata");
    File serials = new File("serialnumbers/serialnumber.txt");

    public void createFile() {
        String serialNumber = getSerialNumber();  // Get the serial number as a string
        File file = new File(this.astrofolder, serialNumber + ".txt"); // Create the file with the serial number
    }

    public String getSerialNumber() {
        int serialNumber = 420;  // Starting serial number
        try {
            // Open the serialnumber.txt file for reading
            Scanner myReader = new Scanner(this.serials);
            while (myReader.hasNextLine()) {
                // For each line, increment the serial number
                serialNumber = serialNumber + 10;
                myReader.nextLine();  // Read the next line
            }
            myReader.close();  // Close the scanner when done
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return Integer.toString(serialNumber);  // Return the updated serial number as a string
    }

    public void updateSerialNumber(){
        String serialnumber = getSerialNumber();
        try {
            FileWriter writer = new FileWriter(this.serials);
            writer.write(serialnumber);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    // Method to save astronaut info to a file
    public static void saveAstronautInfo(String name, String DateOfBirth, String serialNumber, String Address, String Email, 
    String PhoneNum, String PayRate, double Weight, String NextOfKin, String Status) {
        String filename = "astrodata/"+serialNumber + ".txt";
        String astronautInfo = 
            "Serial Number: " + serialNumber + "\n" +       
            "Name: " + name + "\n" +
            "Date of Birth: " + DateOfBirth + "\n" +
            "Address: " + Address + "\n" +
            "Email: " + Email + "\n" +
            "Phone Number: " + PhoneNum + "\n" +
            "Pay Rate: " + PayRate + "\n" +
            "Weight: " + Double.toString(Weight) + "\n" +
            "Next of Kin: " + NextOfKin + "\n" +
            "Status: " + Status + "\n";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(astronautInfo);
            System.out.println("Astronaut information saved in " + filename);
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while saving astronaut info.");
            e.printStackTrace();
        }
    }

    // Method to read astronaut info from a file and display it
    public static void readAstronautInfo(String serialNumber) {
        String filename = "astrodata/"+serialNumber + ".txt";
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            System.out.println("Reading astronaut info from " + filename + ":\n");
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }
    }

    // Method to edit astronaut information and save the updated details to the file
    public static void editAstronautInfo(String name, String DateOfBirth, String serialNumber, String Address, String Email, 
    String PhoneNum, String PayRate, double Weight, String NextOfKin, String Status) {
        String filename = "astrodata/"+serialNumber + ".txt";
        String astronautInfo = "Serial Number: " + serialNumber + "\n" +       
            "Name: " + name + "\n" +
            "Date of Birth: " + DateOfBirth + "\n" +
            "Address: " + Address + "\n" +
            "Email: " + Email + "\n" +
            "Phone Number: " + PhoneNum + "\n" +
            "Pay Rate: " + PayRate + "\n" +
            "Weight: " + Double.toString(Weight) + "\n" +
            "Next of Kin: " + NextOfKin + "\n" +
            "Status: " + Status + "\n";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(astronautInfo);
            System.out.println("Astronaut information updated in " + filename);
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while updating astronaut info.");
            e.printStackTrace();
        }
    }

    // Method to delete astronaut information
    public static void removeAstronautInfo(String serialNumber) {
        String filename = "astrodata/"+serialNumber + ".txt";
        File file = new File(filename);
        if (file.delete()) {
            System.out.println("Astronaut information removed.");
        } else {
            System.out.println("Error: Could not remove astronaut information.");
        }
    }

}