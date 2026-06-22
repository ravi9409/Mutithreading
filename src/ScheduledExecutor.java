import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    public static void main(String[] args) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        System.out.println("Main Thread: " + System.currentTimeMillis());

        scheduler.schedule(() -> {
            System.out.println("Task executed after 5 seconds");
            System.out.println("Time: " + System.currentTimeMillis());
        }, 5, TimeUnit.SECONDS);

        scheduler.shutdown();
    }
}
