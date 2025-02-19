
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//FILE SYSTEM
public class datastore2 {

    // Assigns the folder where serial numbers are entered
    File folder = new File("serialnumbers");
    File serials = new File("serialnumbers/serialnumber.txt");

    public void createFile() {
        String serialNumber = getSerialNumber();  // Get the serial number as a string
        File file = new File(this.folder, serialNumber + ".txt"); // Create the file with the serial number
        System.out.println("Created file: " + file.getAbsolutePath());  // For debugging purposes
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
}