
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class base {

    public static void main(String[] args) throws FileNotFoundException {

        MainFrame_copy_copy.main(args);
        startup start = new startup();
        start.startupcheck();
        
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
        boolean exit = false;
        int choice = 0;
        int innerChoice = 0;
        int exitconfromation;
        int removalconfromation;
        String spaceshipName;
        Double fuelCapacity;
        int shipAstroCapacity;
        double fuelAmount;
        double currentFuel = 0.0;
        
        regexverifier rgx = new regexverifier();
        datastore2 data = new datastore2();
        launch launch = new launch();
        
        //Password  
        //First run generates password
        //2nd Prompts for it
        //Admin password allows for resting password
//Menu for selecting what you want to pick.
        do {
            System.out.println("Menu: \n1. Astronaut Options \n2. Spaceship Options \n3. Start Launch Countdown \n4. Exit Application");
//Get choice
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                choice = -1;
            }

            while (choice <= 0 || choice > 4) {
                System.out.println("Invalid Choice Selection");
                System.out.println("Menu: \n1. Astronaut Options \n2. Spaceship Options \n3. Start Launch Countdown \n4. Exit Application");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    choice = -1;
                }
            }
            switch (choice) {
                //ASTRO INFO
                case 1:
                    System.out.println("Menu: \n1. Add Astronaut \n2. Edit Astronaut \n3. Remove Astronaut \n4. Return to Menu");
                    try {
                        innerChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        innerChoice = -1;
                    }
                    while (choice <= 0 || choice > 4) {
                        System.out.println("Invalid Choice Selection");
                        System.out.println("Menu: \n1. Add Astronaut \n2. Edit Astronaut \n3. Remove Astronaut \n4. Return to Menu");
                        try {
                            innerChoice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            innerChoice = -1;
                        }
                    }
                    switch (innerChoice) {
                        //Ask for name, date of birth, serial #, address, email, phone #, pay rate, weight, next of kin, status(in space or on Earth).
                        //Add astro and validate inputs
                        case 1:
                            System.out.println("Please enter the name of the new astronaut: ");
                            name = scanner.nextLine();
                            while (name.trim().isEmpty()) {
                                System.out.println("The astronauts name cannot be blank.");
                                System.out.println("Please enter the name of the astronaut: ");
                                name = scanner.nextLine();
                            }
                            System.out.println("Please enter the year(ex: 1986) the astronaut was born: ");
                            try {
                                year = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                year = -1;
                            }
                            while (year < 1900) {
                                System.out.println("Invalid year, please enter a valid year: ");
                                try {
                                    year = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    year = -1;
                                }
                            }
                            System.out.println("Please enter the month(1-12) the astronaut was born: ");
                            try {
                                month = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                month = -1;
                            }
                            while (0 >= month || month > 12) {
                                System.out.println("Invalid month choice, month must be between 1 and 12, please enter a valid month: ");
                                try {
                                    month = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    month = -1;
                                }
                            }
                            System.out.println("Please enter the day the astronaut was born on:");
                            try {
                                day = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                day = -1;
                            }
                            while (day < 1 || day > 31) {
                                System.out.println("Invalid day, day must be between 1-31, please enter a valid day: ");
                                try {
                                    day = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    day = -1;
                                }
                            }
                            DateOfBirth = String.format("%02d/%02d/%04d", month, day, year);

                            System.out.println("Please enter the astronauts address: ");
                            Address = scanner.nextLine();
                            while (Address.trim().isEmpty()) {
                                System.out.println("The astronauts address cannot be blank.");
                                System.out.println("Please enter the address of the astronaut: ");
                                Address = scanner.nextLine();
                            }
                            //Serial Number is to be incremented by 10 from the previous serial #, must be unique for each astro
                            SerialNumber = data.getSerialNumber();
                            System.out.println("Serial Number: " + SerialNumber);
                            System.out.println("Please enter the astronauts Email: ");
                            Email = scanner.nextLine();
                            while (rgx.isEmailValid(Email) == false) {
                                System.out.println("Invalid Email, please enter a valid Email: ");
                                Email = scanner.nextLine();
                            }
                            System.out.println("Please enter the astronauts phone number: ");
                            PhoneNumber = scanner.nextLine();
                            while (rgx.isValidPhoneNumber(PhoneNumber) == false) {
                                System.out.println("Invalid Phone Number, please enter a valid Phone Number: ");
                                PhoneNumber = scanner.nextLine();
                            }
                            System.out.println("Please enter the astronauts payrate: ");
                            PayRate = scanner.nextLine();
                            while (rgx.isValidPayRate(PayRate) == false) {
                                System.out.println("Invalid payrate, please enter a valid payrate formatted as ($#,###.##): ");
                                PayRate = scanner.nextLine();
                            }
                            System.out.println("Please enter the astronauts weight in pounds: ");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Invalid input. Please enter a valid weight in pounds: ");
                                scanner.nextLine();
                            }
                            Weight = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.println("Please enter the astronauts next of kin: ");
                            NextOfKin = scanner.nextLine();
                            while (NextOfKin.trim().isEmpty()) {
                                System.out.println("The astronauts next of kin cannot be blank.");
                                System.out.println("Please enter the next of kin for the astronaut: ");
                                NextOfKin = scanner.nextLine();
                            }
                            System.out.println("Is the astronaut currently in space or on Earth?");
                            Status = scanner.nextLine();
                            while (!Status.toUpperCase().equals("EARTH") && !Status.toUpperCase().equals("ON EARTH") && !Status.toUpperCase().equals("SPACE") && !Status.toUpperCase().equals("IN SPACE")) {
                                System.out.println("Invalid entry, please state if the astronaut is on Earth or in Space.");
                                Status = scanner.nextLine();
                            }
                            if (data.saveAstronautInfo(name, DateOfBirth, SerialNumber, Address, Email, PhoneNumber, PayRate, Weight, NextOfKin, Status)) {
                                data.updateSerialNumber(SerialNumber);
                            }
                            break;
                        //edit astro by selected serial #
                        case 2:
                            System.out.println("Please enter the serial number of the astronaut you wish to edit.");
                            SerialNumber = scanner.nextLine();
                            while (SerialNumber.trim().isEmpty()) {
                                System.out.println("The astronauts next of kin cannot be blank.");
                                System.out.println("Please enter the next of kin for the astronaut: ");
                                SerialNumber = scanner.nextLine();
                            }
                            data.readAstronautInfo(SerialNumber);
                            System.out.println("Please enter the name of the new astronaut: ");
                            name = scanner.nextLine();
                            while (name.trim().isEmpty()) {
                                System.out.println("The astronauts name cannot be blank.");
                                System.out.println("Please enter the name of the astronaut: ");
                                name = scanner.nextLine();
                            }
                            System.out.println("Please enter the year(ex: 1986) the astronaut was born: ");
                            try {
                                year = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                year = -1;
                            }
                            while (year < 1900) {
                                System.out.println("Invalid year, please enter a valid year: ");
                                try {
                                    year = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    year = -1;
                                }
                            }
                            System.out.println("Please enter the month(1-12) the astronaut was born: ");
                            try {
                                month = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                month = -1;
                            }
                            while (0 >= month || month > 12) {
                                System.out.println("Invalid month choice, month must be between 1 and 12, please enter a valid month: ");
                                try {
                                    month = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    month = -1;
                                }
                            }
                            System.out.println("Please enter the day the astronaut was born on:");
                            try {
                                day = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                day = -1;
                            }
                            while (day < 1 || day > 31) {
                                System.out.println("Invalid day, day must be between 1-31, please enter a valid day: ");
                                try {
                                    day = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    day = -1;
                                }
                            }
                            DateOfBirth = String.format("%02d/%02d/%04d", month, day, year);

                            System.out.println("Please enter the astronauts address: ");
                            Address = scanner.nextLine();
                            while (Address.trim().isEmpty()) {
                                System.out.println("The astronauts address cannot be blank.");
                                System.out.println("Please enter the address of the astronaut: ");
                                Address = scanner.nextLine();
                            }
                            System.out.println("Please enter the astronauts Email: ");
                            Email = scanner.nextLine();
                            while (rgx.isEmailValid(Email) == false) {
                                System.out.println("Invalid Email, please enter a valid Email: ");
                                Email = scanner.nextLine();
                            }
                            System.out.println("Please enter the astronauts phone number: ");
                            PhoneNumber = scanner.nextLine();
                            while (rgx.isValidPhoneNumber(PhoneNumber) == false) {
                                System.out.println("Invalid Phone Number, please enter a valid Phone Number: ");
                                PhoneNumber = scanner.nextLine();
                            }
                            System.out.println("Please enter the astronauts payrate: ");
                            PayRate = scanner.nextLine();
                            while (rgx.isValidPayRate(PayRate) == false) {
                                System.out.println("Invalid payrate, please enter a valid payrate formatted as ($#,###.##): ");
                                PayRate = scanner.nextLine();
                            }
                            System.out.println("Please enter the astronauts weight in pounds: ");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Invalid input. Please enter a valid weight in pounds: ");
                                scanner.nextLine();
                            }
                            Weight = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.println("Please enter the astronauts next of kin: ");
                            NextOfKin = scanner.nextLine();
                            while (NextOfKin.trim().isEmpty()) {
                                System.out.println("The astronauts next of kin cannot be blank.");
                                System.out.println("Please enter the next of kin for the astronaut: ");
                                NextOfKin = scanner.nextLine();
                            }
                            System.out.println("Is the astronaut currently in space or on Earth?");
                            Status = scanner.nextLine();
                            while (!Status.toUpperCase().equals("EARTH") && !Status.toUpperCase().equals("ON EARTH") && !Status.toUpperCase().equals("SPACE") && !Status.toUpperCase().equals("IN SPACE")) {
                                System.out.println("Invalid entry, please state if the astronaut is on Earth or in Space.");
                                Status = scanner.nextLine();
                            }
                            data.editAstronautInfo(name, DateOfBirth, SerialNumber, Address, Email, PhoneNumber, PayRate, Weight, NextOfKin, Status);
                            break;
                        //remove astro data but request confirmation
                        case 3:
                            System.out.println("Please enter the serial number of the astronaut you wish to delete.");
                            SerialNumber = scanner.nextLine();
                            while (SerialNumber.trim().isEmpty()) {
                                System.out.println("The astronauts next of kin cannot be blank.");
                                System.out.println("Please enter the next of kin for the astronaut: ");
                                SerialNumber = scanner.nextLine();
                            }
                            System.out.println("Are you sure you want to delete this astronaut data? 1 for yes, 2 for no.");
                            try {
                                removalconfromation = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                removalconfromation = -1;
                            }
                            while (removalconfromation != 1 && removalconfromation != 2) {
                                System.out.println("Thats not a valid choice");
                                System.out.println("Are you sure you want to delete this astronaut data? 1 for yes, 2 for no.");
                                try {
                                    removalconfromation = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    removalconfromation = -1;
                                }
                            }
                            if (removalconfromation == 1) {
                                data.removeAstronautInfo(SerialNumber);
                            }
                            if (removalconfromation == 2) {
                                break;
                            }
                            break;
                        case 4:
                            break;
                    }
                    break;
                //SPACESHIP MANAGEMENT
                //Spaceship Options, 1.Add Spaceship 2.Assign Astros 3.Load Fuel
                case 2:
                    System.out.println("Menu: \n1. Add Spaceship \n2. Assign Astronauts \n3. Load Fuel");
                    try {
                        innerChoice = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        innerChoice = -1;
                    }
                    while (choice <= 0 || choice > 3) {
                        System.out.println("Invalid Choice Selection");
                        System.out.println("Menu: \n1. Add Spaceship \n2. Assign Astronauts \n3. Load Fuel");
                        try {
                            innerChoice = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            innerChoice = -1;
                        }
                    }
                    switch (innerChoice) {
                        //Add spaceship: get name, fuel capacity;
                        case 1:
                            System.out.println("Enter spaceship name: ");
                            spaceshipName = scanner.nextLine();
                            while (spaceshipName.trim().isEmpty()) {
                                System.out.println("The spaceship name cannot be blank.");
                                System.out.println("Please enter the spaceship name: ");
                                spaceshipName = scanner.nextLine();
                            }
                            System.out.println("Enter how many astronauts the ship can hold: ");
                            try {
                                shipAstroCapacity = scanner.nextInt();
                                scanner.nextLine();
                            } catch (InputMismatchException e) {
                                scanner.nextLine();
                                shipAstroCapacity = -1;
                            }
                            while (shipAstroCapacity < 1) {
                                System.out.println("The spaceship must hold at least one astronaut: ");
                                System.out.println("Enter how many astronauts the ship can hold: ");
                                try {
                                    shipAstroCapacity = scanner.nextInt();
                                    scanner.nextLine();
                                } catch (InputMismatchException e) {
                                    scanner.nextLine();
                                    shipAstroCapacity = -1;
                                }
                            }
                            System.out.println("Enter the spaceships fuel capacity in pounds: ");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Invalid input. Please enter a valid fuel capacity in pounds: ");
                                scanner.nextLine();
                            }
                            fuelCapacity = scanner.nextDouble();
                            scanner.nextLine();
                            data.saveSpaceShipData(spaceshipName, fuelCapacity, shipAstroCapacity);
                            break;
                        //Add astros function
                        case 2:
                            System.out.println("Enter spaceship name to load astronauts onto:");
                            data.readSpaceshipNames();
                            spaceshipName = scanner.nextLine();
                            data.loadAstros(spaceshipName);
                            break;
                        //Fuels ship based on input with method in spaceship
                        case 3:
                            System.out.println("Enter spaceship name to fuel: ");
                            data.readSpaceshipNames();
                            spaceshipName = scanner.nextLine();
                            while (spaceshipName.trim().isEmpty()) {
                                System.out.println("The spaceship name cannot be blank.");
                                System.out.println("Please enter the spaceship name: ");
                                spaceshipName = scanner.nextLine();
                            }
                            System.out.println("How much fuel do you want to put into " + spaceshipName + ": ");
                            while (!scanner.hasNextDouble()) {
                                System.out.println("Invalid input. Please enter a valid fuel amount in pounds: ");
                                scanner.nextLine();
                            }
                            fuelAmount = scanner.nextDouble();
                            scanner.nextLine();
                            data.setCurrentFuel(fuelAmount, spaceshipName);
                            System.out.println(spaceshipName + " has been fueled. Current fuel level: " + data.getCurrentFuel(spaceshipName) + " pounds. ");
                            break;
                    }
                    break;
                //Launch Process
                //Launch Countdown for a selected spaceship
                //Countdown from 10 to 0 before launch.
                //Space walk: when rocket above 70,000m, start 30sec timer(astros do spacewalk in said time.)
                //Return to earth: Gravity, burn-up, parachute.
                case 3:
                    System.out.println("Select spaceship to launch: ");
                    data.readSpaceshipNames();
                    spaceshipName = scanner.nextLine();
                    while (spaceshipName.trim().isEmpty()) {
                        System.out.println("The spaceship name cannot be blank.");
                        System.out.println("Please enter the spaceship name: ");
                        spaceshipName = scanner.nextLine();
                    }
                    if (data.check(spaceshipName)) {
                        launch.launchCountdown();
                        System.out.println(launch.launch(data.getCurrentFuel(spaceshipName), spaceshipName));
                        System.out.println("Press Enter to start spacewalk: ");
                        scanner.nextLine();
                        launch.SpaceWalk();
                        System.out.println("Preparing ship returnal, Press Enter to start re-entry to Earth");
                        scanner.nextLine();
                        System.out.print(launch.returnal());
                    } else{
                        System.err.println("Requirements to launch not met. Please meet requirements and then try again.");
                    }
                    break;
                //exit conformation
                case 4:
                    System.out.println("Are you sure you want to exit?");
                    System.out.println("Enter 1 for yes, 2 for no.");
                    try {
                        exitconfromation = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                        exitconfromation = -1;
                    }
                    while (exitconfromation != 1 && exitconfromation != 2) {
                        System.out.println("Thats not a valid choice");
                        System.out.println("Are you sure you want to you want to exit? 1 for yes, 2 for no.");
                        try {
                            exitconfromation = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            scanner.nextLine();
                            exitconfromation = -1;
                        }
                    }
                    if (exitconfromation == 1) {
                        exit = true;
                    }
                    if (exitconfromation == 2) {
                        break;
                    }
                    break;
            }
        } while (exit != true);
        System.exit(0);
    }
}
