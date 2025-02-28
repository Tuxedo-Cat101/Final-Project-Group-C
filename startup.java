
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class startup {
    public void startupcheck(){
        if (new File("serialnumbers").exists()){
        }else{
            try {
                Files.createDirectories(Paths.get("serialnumbers"));
            } catch (Exception e) {
            }
        }
        if (new File("serialnumbers/serialnumber.txt").exists()){
        }else{
            try {
                File serials = new File("serialnumbers/serialnumber.txt");
                serials.createNewFile();
            } catch (Exception e) {
            }
        }
        if (new File("unassignedastros").exists()){}else{
            try {
                Files.createDirectories(Paths.get("unassignedastros"));
            } catch (Exception e) {
            }
        }
        if (new File("assignedastros").exists()){}else{
            try {
                Files.createDirectories(Paths.get("assignedastros"));
            } catch (Exception e) {
            }
        }
        if (new File("spaceshipdata").exists()){}else{
            try {
                Files.createDirectories(Paths.get("spaceshipdata"));
            } catch (Exception e) {
            }
        }
    }
}
