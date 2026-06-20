package ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceSubmit implements Runnable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Hello world");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // executorService.execute(new ExecutorServiceExample());
        Future<Integer> future = executorService.submit(() -> {
            return 10 + 20;
        });

        Integer result = future.get();

        System.out.println(result);
        executorService.shutdown();
    }

}
