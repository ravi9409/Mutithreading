package locks.methods;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();

            lock.lock(); // waits until lock is available
            try {
                System.out.println(threadName + " acquired the lock");

                Thread.sleep(3000); // simulate work

                System.out.println(threadName + " releasing the lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };

        new Thread(task, "Thread-1").start();
        new Thread(task, "Thread-2").start();
    }
}