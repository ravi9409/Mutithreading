// DEADLOCK: Thread-1 holds lock1, waits for lock2.
//           Thread-2 holds lock2, waits for lock1.
//           Neither can proceed → deadlock!
public class Deadlock {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("T1 acquired lock1, waiting for lock2...");
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                synchronized (lock2) {           // blocked — T2 holds lock2
                    System.out.println("T1 done");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("T2 acquired lock2, waiting for lock1...");
                synchronized (lock1) {           // blocked — T1 holds lock1
                    System.out.println("T2 done");
                }
            }
        });

        t1.start();
        t2.start();
        // Program hangs here — both threads wait forever
    }
}
