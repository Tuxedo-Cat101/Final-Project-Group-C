import java.util.Scanner;

public class spaceship {

    String name;
    double capacity;

   
    

    // Method to display ship details
    void displayShipDetails() {
        System.out.println("Ship Name: " + name);
        System.out.println("Capacity: " + capacity);
    }

    void createAndDisplayShip() {
        Scanner scanner = new Scanner(System.in);

  
        System.out.print("What is the name of the ship?: ");
        this.name = scanner.nextLine();

        System.out.print("What is the ship fuel capacity?: ");
        this.capacity = scanner.nextInt();

       
        displayShipDetails();
    }
    //Loads fuel onto spacecraft
    public Double loadFuel(double fuelCapacity, double fuelAmount, double currentFuel) {
        double fuel;
        fuel = Math.min(currentFuel + fuelAmount, fuelCapacity);
        currentFuel = fuel;
        return fuel;
    }
}