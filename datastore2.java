import java.io.File;
import java.io.IOException;



public class datastore2{
    public void createfile() {
        try {
            File folder = new File("serialnumbers");
            if (!folder.exists()) {
                folder.mkdirs(); // Create the folder if it doesn't exist
            }
            File file = new File(folder, getserialnumber()+".txt");
            if (file.createNewFile()) {
                
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public String getserialnumber() {
        





    }
}