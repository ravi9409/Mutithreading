
public class Volatile {
    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            while (running) {
                // keep running
            }
            System.out.println("Thread stopped");
        });

        t1.start();

        Thread.sleep(2000);

        running = false;
        System.out.println("Main changed running to false");
    }
}
