
import java.io.BufferedWriter;
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
        String line;
        int i = 0;
        try {
            // Open the serialnumber.txt file for reading
            Scanner myReader = new Scanner(this.serials);
            line = myReader.nextLine();
            while (myReader.hasNextLine()) {
                if (line != null || !line.equals("")){
                    i = i +10;
                }
                line = myReader.nextLine();
                
                
            }
            myReader.close();  // Close the scanner when done
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return Integer.toString(serialNumber + i);  // Return the updated serial number as a string
    }

    public void updateSerialNumber() {
        String serialnumber = getSerialNumber();
        String line;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.serials))){
            Scanner myReader = new Scanner(this.serials);
            while (myReader.hasNextLine()){
                line = myReader.nextLine();
            }
            writer.newLine();
            writer.write(serialnumber);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Method to save astronaut info to a file
    public static void saveAstronautInfo(String name, String DateOfBirth, String serialNumber, String Address, String Email,
            String PhoneNum, String PayRate, double Weight, String NextOfKin, String Status) {
        String filename = "astrodata/" + serialNumber + ".txt";
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("Astronaut with that serial number already exists.");
        } else {
            String astronautInfo
                    = "Serial Number: " + serialNumber + "\n"
                    + "Name: " + name + "\n"
                    + "Date of Birth: " + DateOfBirth + "\n"
                    + "Address: " + Address + "\n"
                    + "Email: " + Email + "\n"
                    + "Phone Number: " + PhoneNum + "\n"
                    + "Pay Rate: " + PayRate + "\n"
                    + "Weight: " + Double.toString(Weight) + "\n"
                    + "Next of Kin: " + NextOfKin + "\n"
                    + "Status: " + Status + "\n";
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.print(astronautInfo);
                System.out.println("Astronaut information saved in " + filename);
                writer.close();
            } catch (IOException e) {
                System.err.println("An error occurred while saving astronaut info.");
                e.printStackTrace();
            }
        }
    }

    // Method to read astronaut info from a file and display it
    public static void readAstronautInfo(String serialNumber) {
        String filename = "astrodata/" + serialNumber + ".txt";
        File file = new File(filename);
        if (file.exists()) {
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
        } else {
            System.out.println("No astronaut with that serial number exists.");
        }
    }

    // Method to edit astronaut information and save the updated details to the file
    public static void editAstronautInfo(String name, String DateOfBirth, String serialNumber, String Address, String Email,
            String PhoneNum, String PayRate, double Weight, String NextOfKin, String Status) {
        String filename = "astrodata/" + serialNumber + ".txt";
        File file = new File(filename);
        if (file.exists()) {
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
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                writer.print(astronautInfo);
                System.out.println("Astronaut information updated in " + filename);
                writer.close();
            } catch (IOException e) {
                System.err.println("An error occurred while updating astronaut info.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No astronaut with that serial number exists.");
        }
    }

    // Method to delete astronaut information
    public void removeAstronautInfo(String serialNumber) {
        String filename = "astrodata/" + serialNumber + ".txt";
        File file = new File(filename);
        String line;
        if (file.delete()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.serials))){
                Scanner myReader = new Scanner(this.serials);
                line = myReader.nextLine();
                while (!line.equals(serialNumber)){
                    line = myReader.nextLine();
                }
                if (line.equals(serialNumber)){
                    writer.write("");
                }
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Astronaut information removed.");
        } else {
            System.out.println("Error: Could not remove astronaut information.");
        }
    }

}
