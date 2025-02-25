
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
    
    int numofAstros = search("astrodata");

    public void createFile() {
        String serialNumber = getSerialNumber();  // Get the serial number as a string
        File file = new File(this.astrofolder, serialNumber + ".txt"); // Create the file with the serial number
    }

    public String getSerialNumber() {
        File serials = new File("serialnumbers/serialnumber.txt");
        int serialNumber = 420;  // Starting serial number
        String line;
        int i = 0;
        try {
            // Open the serialnumber.txt file for reading
            Scanner myReader = new Scanner(serials);
            line = myReader.nextLine();
            while (myReader.hasNextLine()) {
                if (line != null || !line.equals("")) {
                    i = i + 10;
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
        String serialnumber = getSerialNumber(); // Assuming this returns the serial number
        File serials = new File("serialnumbers/serialnumber.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(serials, true))) {
            // Open the writer in append mode (true argument)
            writer.newLine(); // Start with a new line
            writer.write(serialnumber); // Write the new serial number
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
    public void removeAstronautInfo(String serialNumber) throws FileNotFoundException {
        // Construct the filename from the serial number
        File serials = new File("serialnumbers/serialnumber.txt");
        String filename = "serialnumbers/serialnumber.txt";
        File data = new File("astrodata/" + serialNumber + ".txt");
        int numberToDelete = Integer.parseInt(serialNumber);
        // Create a temporary file to write the remaining numbers
        File tempFile = new File("serialnumbers/tempFile.txt");
        Scanner serialFile = new Scanner(serials);
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
                    if (numberToDelete == Integer.parseInt(line)){
                        numberFound = true;
                    }
                } catch (Exception e) {
                    // Stuff is broke here
                    // Empty line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (numberFound = true){
            data.delete();
            serials.delete();
            tempFile.renameTo(serials);
            System.out.println("Astronaut data successfully deleted.");
        } else{
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

}
