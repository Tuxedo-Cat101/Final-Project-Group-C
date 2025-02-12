public class launch {

    double speed = 0;
    double altitude = 0;
    //Starts 10 Second Countdown
    public void launchCountdown() {
        int timer = 10; 
        System.out.println("T-Minus " + timer + ".");
        while (timer > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer--;
            System.out.println(timer);
        }
        System.out.println("The spaceship has launched!");
    }
    //Launches Spacecraft and calculates its altitude/speed.
    public String launch(Double fuelCapactiy, Double currentFuel){
        int second = 0;
        speed = 0;
        altitude = 0;
        while (altitude < 70000){
        speed = speed + ((2*30) - (9.81));
        currentFuel = currentFuel - 2;
            if (currentFuel > 0) {break;}
        altitude = altitude + speed;
        second++;
        System.out.println(second+": Altitude: "+Double.toString(altitude));
        try
    {
        Thread.sleep(1000);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
        }
        if (currentFuel > 0) {
        return "Spacecraft ran out of fuel before it could reach space";
        }
        return "Spacecraft successfully reached space, spacewalk can now be executed.";
    }
    //Starts spacewalk and counts for 30 secs.
    public void SpaceWalk(){
        System.out.println("Starting 30 second spacewalk: ");
        int timer = 30; 
        System.out.println("T-Minus " + timer + ".");
        while (timer > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timer--;
            System.out.println(timer);
        }
        System.out.println("Spacewalk Completed. ");
    }
    //Begins returnal of spacecraft back to Earth, deploys parachute at 10000 meters.
    public String returnal(){
        int second = 0;
        speed = 0;
        altitude = 70000;
        while (altitude > 10000){
        speed = speed - 9.81;
        altitude = altitude + speed;
        if (speed > 3000){break;}
        second++;
        System.out.println(second+": Altitude: "+Double.toString(altitude));
        try
        {
        Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
        Thread.currentThread().interrupt();
        }
    }
        System.out.println("Deploying Parachute: ");
        while (altitude > 0){
        speed = -7;
        altitude = altitude + speed;
        second++;
        System.out.println(second+": Altitude: "+Double.toString(altitude));
        try
        {
        Thread.sleep(1000);
        }
        catch(InterruptedException ex)
        {
        Thread.currentThread().interrupt();
        }
    }
    if (speed > 3000){
    return "Spacecraft burned up on re-entry.";
    }
    return "The ship has landed safely. Astronauts may exit. \nMission Success";
}
}