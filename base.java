import java.util.Scanner;

public class base {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;
        String DateOfBirth;
        int year, month, day;
        double SerialNumber;
        String Address;
        String Email;
        double PhoneNumber;
        double PayRate;
        double Weight;
        String NextOfKin;
        String Status;
        int i = 0;
        boolean exit = false;
        int choice;
        int innerChoice;
        int exitconfromation;
//Password  
        //First run generates password
        //2nd Prompts for it
        //Admin password allows for resting password
//Menu for selecting what you want to pick.
        do {
            System.out.println("Menu: \n1. Astronaut Options \n2. Spaceship Options \n3. Prepare For Launch \n4. Start Launch Countdown \n5. Exit Application");
//Get choice
            choice = scanner.nextInt();
            if (choice <= 0 || choice > 5){
            System.out.println("Invalid Choice Selection");
            System.out.println("Menu: \n1. Astronaut Options \n2. Spaceship Options \n3. Prepare For Launch \n4. Start Launch Countdown \n5. Exit Application");
            choice = scanner.nextInt();
            scanner.nextLine();
            }
            scanner.nextLine();
            switch (choice) {
                //ASTRO INFO
                
                case 1:
                    System.out.println("Menu: \n1. Add Astronaut \n2. Edit Astronaut \n3. Remove Astronaut \n4. Return to Menu");
                        innerChoice = scanner.nextInt();
                        scanner.nextLine();
                    if (choice <= 0 || choice > 4){
                        System.out.println("Menu: \n1. Add Astronaut \n2. Edit Astronaut \n3. Remove Astronaut \n4. Return to Menu");
                        innerChoice = scanner.nextInt();
                        scanner.nextLine();
                    }
                    switch (innerChoice){
                        //Ask for name, date of birth, serial #, address, email, phone #, pay rate, weight, next of kin, status(in space or on Earth).
                        //Add astro and validate inputs
                        case 1:
                            System.out.println("Please enter the name of the new astronaut: ");
                            name = scanner.nextLine();
                            System.out.println("Please enter the year(ex: 1986) you were born: ");
                            year = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Please enter the month(1-12) you were born: ");
                            month = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Please enter the day you were born on:");
                            day = scanner.nextInt();
                            scanner.nextLine();
        //Serial Number is to be incremented by 1 from the previous serial #, must be unique for each astro
                            System.out.println();
                            break;
                        //edit astro by selected serial #
                        case 2:

                            break;
                        //remove astro data but request confirmation
                        case 3:

                            break;
                        case 4:

                            break;
                    }
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;
                case 5:
                        System.out.println("Are you sure you want to exit?");
                        System.out.println("Enter 1 for yes, & 2 for no.");
                        exitconfromation = scanner.nextInt();
                        scanner.nextLine();
                       if(exitconfromation == 1){
                        exit = true;
                       } 
                    if (exitconfromation == 2){
                        exit = false;
                    }
                       if (exitconfromation != 1 || exitconfromation != 2){
                        System.out.println("False option, try again");
                        System.out.println("Are you sure you want to exit?");
                        System.out.println("Enter 1 for yes, & 2 for no.");
                       }
                    break;

//SPACESHIP MANAGEMENT
    //Add spaceship: get name, fuel capacity;
    //Add astros function and load fuel
//Launch Process
    //Countdown from 10 to 0 before launch.
    //
//Space walk: when rocket above 70,000m, start 30sec timer(astros do spacewalk in said time.)
//Return to earth: Gravity, burn-up, parachute.
            }
        } while (exit != true);
    }
}
