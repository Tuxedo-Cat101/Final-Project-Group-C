public class launch {
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
}





