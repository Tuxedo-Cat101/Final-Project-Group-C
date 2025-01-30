
import java.util.Scanner;

public class base{
 public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String name;
    double DateOfBirth;
    double SerialNumber;
    String Address;
    String Email;
    double PhoneNumber;
    double PayRate;
    double Weight;
    String NextOfKin;
    String Status;
    int i = 0;
    int choice;
//Password  
    //First run generates password
    //2nd Prompts for it
    //Admin password allows for resting password
//Menu for selecting what you want to pick.
do {
System.out.println("Menu: \n1. Astronaut Options \n2. Spaceship Options \n3. Prepare For Launch \n4. Start Launch Countdown");
//Get choice


switch (choice) {


//ASTRO INFO
    //Ask for name, date of birth, serial #, address, email, phone #, pay rate, weight, next of kin, status(in space or on Earth).
        //Add astro and validate inputs
        //edit astro by selected serial #,
        //remove astro data but request confirmation
    System.out.println("");
//SPACESHIP MANAGEMENT
    //Add spaceship: get name, fuel capacity;
    //Add astros function and load fuel
//Launch Process
    //Countdown from 10 to 0 before launch.
    //
//Space walk: when rocket above 70,000m, start 30sec timer(astros do spacewalk in said time.)
//Return to earth: Gravity, burn-up, parachute.
}
} while(i != 100);
}
}