import java.util.Scanner;

public class base {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name;
        String DateOfBirth;
        int year, month, day;
        String SerialNumber;
        String Address;
        String Email;
        String PhoneNumber;
        String PayRate;
        double Weight;
        String NextOfKin;
        String Status;
        int i = 0;
        boolean exit = false;
        int choice;
        int innerChoice;
        int exitconfromation;
        int removalconfromation;
        String spaceshipName = "";
        Double fuelCapacity = 0.0;
        int spaceshipNum = 0;
        int shipSelector;
        double fuelAmount;
        double currentFuel = 0.0;
        
        regexverifier rgx = new regexverifier();
        astronaut astro = new astronaut();
        spaceship spaceship = new spaceship();
        launch launch = new launch();
        datastore2 data = new datastore2();
        SerialNumber = data.getserialnumber();
        
//Password  
        //First run generates password
        //2nd Prompts for it
        //Admin password allows for resting password
//Menu for selecting what you want to pick.
        do {
            System.out.println("Menu: \n1. Astronaut Options \n2. Spaceship Options \n3. Start Launch Countdown \n4. Exit Application");
//Get choice
            choice = scanner.nextInt();
            if (choice <= 0 || choice > 5){
            System.out.println("Invalid Choice Selection");
            System.out.println("Menu: \n1. Astronaut Options \n2. Spaceship Options \n3. Start Launch Countdown \n4. Exit Application");
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
                            System.out.println("Please enter the year(ex: 1986) the astronaut was born: ");
                            year = scanner.nextInt();
                            scanner.nextLine();
                            while (year < 1900){
                                System.out.println("Invalid year, please enter a valid year: ");
                                year = scanner.nextInt();
                                scanner.nextLine();
                            }
                            System.out.println("Please enter the month(1-12) the astronaut was born: ");
                            month = scanner.nextInt();
                            scanner.nextLine();
                            while (0 >= month || month > 12) {
                            System.out.println("Invalid month choice, month must be between 1 and 12, please enter a valid month: ");
                            month = scanner.nextInt();
                            scanner.nextLine();
                            }
                            System.out.println("Please enter the day the astronaut was born on:");
                            day = scanner.nextInt();
                            scanner.nextLine();
                            while (day < 1 || day > 31){
                            System.out.println("Invalid day, day must be between 1-31, please enter a valid day: ");
                            day = scanner.nextInt();
                            scanner.nextLine();
                            }
        //Serial Number is to be incremented by 1 from the previous serial #, must be unique for each astro
                            System.out.println("Please enter a valid Email: ");
                            Email = scanner.nextLine();
                            while (rgx.isEmailValid(Email) == false){
                            System.out.println("Invalid Email, please enter a valid Email: ");
                            Email = scanner.nextLine();
                            }
                            System.out.println("Please enter a valid phone number: ");
                            PhoneNumber = scanner.nextLine();
                            while (rgx.isValidPhoneNumber(PhoneNumber) == false){
                                System.out.println("Invalid Phone Number, please enter a valid Phone Number: ");
                                PhoneNumber = scanner.nextLine();
                            }
                            System.out.println("Please enter the new astronauts payrate: ");
                            PayRate = scanner.nextLine();
                            while (rgx.isValidPayRate(PayRate) == false){
                            System.out.println("Invalid payrate, please enter a valid payrate formatted as ($#,###.##): ");
                            PayRate = scanner.nextLine();
                            }  
                            System.out.println("Please enter the astronauts weight in pounds: ");
                            Weight = scanner.nextDouble();
                            System.out.println("Please enter the astronauts next of kin: ");
                            NextOfKin = scanner.nextLine();
                            System.out.println("Is the astronaut currently in space or on Earth?");
                            Status = scanner.nextLine();
                            while (Status.toUpperCase() != "SPACE" || Status.toUpperCase() != "EARTH" || Status.toUpperCase() != "ON EARTH" || Status.toUpperCase() != "IN SPACE"){
                                System.out.println("Is the astronaut currently in space or on Earth?");
                                Status = scanner.nextLine();
                            }
                            break;
                        //edit astro by selected serial #
                        case 2:
                            System.out.println("Please enter the serial number of the astronaut you wish to edit.");

                            
                            break;
                        //remove astro data but request confirmation
                        case 3:
                            System.out.println("Select the astronaut you wish to remove");
                            //area to select who is removed, write when save if figured out
                            
                            System.out.println("Are you Sure, 1 for yes, 2 for no.");
                            removalconfromation = scanner.nextInt();
                            scanner.nextLine();
                            if (removalconfromation == 1) {
                                //remove info
                            }
                            if (removalconfromation == 2) {
                                //back to removal menu
                            }
                            if (removalconfromation != 1 && removalconfromation != 2){
                                System.out.println("Thats not a valid choice");
                                //loop back to yes=1 and no=2
                            }
                            break;
                        case 4:
                            break;
                    }
                    break;
                //SPACESHIP MANAGEMENT
                //Spaceship Options, 1.Add Spaceship 2.Assign Astros 3.Load Fuel
                case 2:
                System.out.println("Menu: \n1. Add Spaceship \n2. Assign Astros \n3. Load Fuel");
                        innerChoice = scanner.nextInt();
                        scanner.nextLine();
                    if (choice <= 0 || choice > 3){
                        System.out.println("Menu: \n1. Add Spaceship \n2. Assign Astros \n3. Load Fuel");
                        innerChoice = scanner.nextInt();
                        scanner.nextLine();
                    }
                    switch (innerChoice){
                        //Add spaceship: get name, fuel capacity;
                        case 1:
                            System.out.println("Enter spaceship name: ");
                            spaceshipName = scanner.nextLine();
                            System.out.println("Enter the spaceships fuel capacity in pounds: ");
                            fuelCapacity = scanner.nextDouble();
                            break;
                        //Add astros function
                        case 2:
                            System.out.println("Select spaceship to load astronauts onto:");
                            i = 0;
                            while(i < spaceshipNum){
                            System.out.println((i+1)+". "+spaceshipName);
                            i++;
                            }
                            shipSelector = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        //Fuels ship based on input with method in spaceship
                        case 3:
                            System.out.println("Select ship to fuel: ");
                            i = 0;
                            while (i < spaceshipNum){
                            System.out.println(spaceshipNum+". "+spaceshipName);
                            }
                            shipSelector = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("How much fuel do you want to put into "+spaceshipName+": ");
                            fuelAmount = scanner.nextDouble();
                            spaceship.loadFuel(fuelCapacity, fuelAmount, currentFuel);
                            System.out.println(spaceshipName+" has been fueled. Current fuel level: "+currentFuel+" pounds. ");
                            break;
                    }

                break;
                //Launch Process
                //Launch Countdown for a selected spaceship
                //Countdown from 10 to 0 before launch.
                //Space walk: when rocket above 70,000m, start 30sec timer(astros do spacewalk in said time.)
                //Return to earth: Gravity, burn-up, parachute.
                case 3:
                System.out.println("Select ship to launch: ");
                    i = 0;
                    while (i < spaceshipNum){
                        System.out.println(spaceshipNum+". "+spaceshipName);
                        i++;
                        }
                    shipSelector = scanner.nextInt();
                    scanner.nextLine();
                    launch.launchCountdown();
                    System.out.println(launch.launch(fuelCapacity, currentFuel));
                    System.out.println("Press Enter to start spacewalk: ");
                    scanner.nextLine();
                    launch.SpaceWalk();
                    System.out.println("Preparing ship returnal, Press Enter to start re-entry to Earth");
                    scanner.nextLine();
                    System.out.print(launch.returnal());
                    break;
                //exit conformation
                case 4:
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
                    if (exitconfromation != 1 && exitconfromation != 2){
                        System.out.println("Invalid option, try again");
                        System.out.println("Are you sure you want to exit?");
                        System.out.println("Enter 1 for yes, & 2 for no.");
                        exitconfromation = scanner.nextInt();
                        scanner.nextLine();
                       }
                    break;
            }
        } while (exit != true);
    }
}