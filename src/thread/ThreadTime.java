package thread;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class ThreadTime extends Thread {
    

    public void run() {
        while (true) {
            System.out.println("Current Time: " + LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
        }
    }

    public static void main(String[] args) {
        ThreadTime t1 = new ThreadTime();
        t1.start();
    }
}
