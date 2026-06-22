package locks.methods;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptDemo {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        lock.lock(); // Main thread holds lock
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                try {
                    System.out.println("Got lock");
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting");
            }
        });

        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
        lock.unlock();
    }
}
