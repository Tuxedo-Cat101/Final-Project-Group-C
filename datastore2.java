
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
    @SuppressWarnings("unused")
    File serialfolder = new File("serialnumbers");
    File astrofolder = new File("astrodata");
    File serials = new File("serialnumbers/serialnumber.txt");
    File spaceshipfolder = new File("spaceshipdata");

    int numofAstros = search("astrodata");

    public void startupcheck(){
        if (new File("serialnumbers").exists()){
        }else{
            File serialfolder = new File("serialnumbers");
        }
        if (new File("serialnumbers/serialnumber.txt").exists()){
        }else{
            File serials = new File("serialnumbers/serialnumber.txt");
        }
        if (new File("astrodata").exists()){}else{
            File astrofolder = new File("astrodata");
        }
        if (new File("spaceshipdata").exists()){}else{
            File spaceshipfolder = new File("spaceshipdata");
        }
    }

    public void createFile() {
        String serialNumber = getSerialNumber();  // Get the serial number as a string
        @SuppressWarnings("unused")
        File file = new File(this.astrofolder, serialNumber + ".txt"); // Create the file with the serial number
    }

    public String getSerialNumber() {
        int serialNumber = 420;  // Starting serial number
        boolean finish = true;
        while (finish) {
            if (new File("astrodata/" + serialNumber + ".txt").exists()) {
                serialNumber = serialNumber + 10;
            } else {
                finish = false;
            }
        }
        return Integer.toString(serialNumber);

    }

    public void updateSerialNumber(String Serialnumber) {
        String serialnumber = Serialnumber;
        File serials = new File("serialnumbers/serialnumber.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(serials, true))) {
            // Open the writer in append mode (true argument)
            writer.newLine(); // Start with a new line
            writer.write(serialnumber); // Write the new serial number
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to save astronaut info to a file
    public static boolean saveAstronautInfo(String name, String DateOfBirth, String serialNumber, String Address, String Email,
            String PhoneNum, String PayRate, double Weight, String NextOfKin, String Status) {
        String filename = "astrodata/" + serialNumber + ".txt";
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("Astronaut with that serial number already exists.");
            return false;
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
                System.out.println("Astronaut information saved. ");
                writer.close();
            } catch (IOException e) {
                System.err.println("An error occurred while saving astronaut info.");
                e.printStackTrace();
            }
            return true;
        }
    }

    // Method to read astronaut info from a file and display it
    public static void readAstronautInfo(String serialNumber) {
        String filename = "astrodata/" + serialNumber + ".txt";
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
                System.out.println("Astronaut information updated ");
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
    public void removeAstronautInfo(String serialNumber) throws FileNotFoundException {
        // Construct the filename from the serial number
        File data = new File("astrodata/" + serialNumber + ".txt");
        int numberToDelete = Integer.parseInt(serialNumber);
        // Create a temporary file to write the remaining numbers
        File tempFile = new File("serialnumbers/tempFile.txt");
        Scanner serialFile = new Scanner(this.serials);
        int numOfLines = 0;
        this.numofAstros = search("astrodata");
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
                System.out.println("Astronaut data successfully deleted and file renamed.");
            } else {
                System.out.println("Failed to rename the file.");
            }
        } else {
            tempFile.delete();
            System.out.println("Astronaut with that serial number not found.");
        }
    }

    public static int search(String folderpath) {
        File directory = new File(folderpath);
        int numberOfFiles = 0;
        for (File element : directory.listFiles()) {
            if (element.isDirectory()) {
                //add the number of files of subfolder
                numberOfFiles += search(element.getAbsolutePath());
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
                    + "Fuel Amount: 0";
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
            System.out.println("No astronaut with that serial number exists.");
        }
    }
}