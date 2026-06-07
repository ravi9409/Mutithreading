import java.util.concurrent.Executors;

public class ExecutorService implements Runnable{

    public static void main(String[] args) {
        System.out.println("Hello world");
        java.util.concurrent.ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new ExecutorService());
        executorService.shutdown();
    }


    @Override
    public void run() {
        System.out.println("Thread 1");
    }
}
