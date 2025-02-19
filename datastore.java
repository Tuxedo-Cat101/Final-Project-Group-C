

public class datastore {

//Save persistent data in external files (e.g., secure CSV files).n 
//Protect files to prevent unauthorized access. 
//Load data from files when the app starts

    // Secret key for AES encryption (16 bytes for AES-128)
    private static final String SECRET_KEY = " "; 
    private static final String FILE_NAME = "";

    static class Person {
String name;
String dateOfBirth;
int year, month, day;
double serialNumber;
String address;
String email;
String phoneNumber;
String payRate;
double weight;
String nextOfKin;
String status;

public Person(String name, String dateOfBirth, int year, int month, int day, double serialNumber, String address, String email, String phoneNumber, String payRate, double weight, String nextOfKin, String status) {
this.name = name;
this.dateOfBirth = dateOfBirth;
this.year = year;
this.month = month;
this.day = day;
this.serialNumber = serialNumber;
this.address = address;
this.email = email;
this.phoneNumber = phoneNumber;
this.payRate = payRate;
this.weight = weight;
this.nextOfKin = nextOfKin;
this.status = status;
}
    }
}
