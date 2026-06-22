import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
   static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Runnable task = () -> {
            lock.lock();

            try {
                System.out.println(Thread.currentThread().getName()
                        + " acquired lock");

                Thread.sleep(2000);

            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        };

        new Thread(task, "T1").start();
        new Thread(task, "T2").start();
    }
}
