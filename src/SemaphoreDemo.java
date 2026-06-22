import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    static Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {

        Runnable task = () -> {
            try {
                semaphore.acquire(); // take permit

                System.out.println(
                    Thread.currentThread().getName()
                    + " acquired permit");

                Thread.sleep(3000);

                System.out.println(
                    Thread.currentThread().getName()
                    + " releasing permit");

                semaphore.release(); // return permit

            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        for(int i=1; i<=5; i++) {
            new Thread(task, "Thread-" + i).start();
        }
    }
}