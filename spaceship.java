import java.util.Scanner;

class spaceship {

    String name;
    int capacity;

   
    spaceship(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

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
    void loadFuel() {
        
    }
}