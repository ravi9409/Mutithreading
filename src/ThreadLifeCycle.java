public class ThreadLifeCycle {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Java Thread States Demo ---");
        Thread t1 = new Thread(() -> {
        });
        System.out.println("1. State after new Thread(): " + t1.getState() + " ");

        // 2. RUNNABLE
        // We call start(). It is now executing (or ready to execute).
        t1.start();
        System.out.println("2. State after t1.start(): " + t1.getState() + " ");

        // 3. TERMINATED
        // We wait for t1 to completely finish its empty block of code.
        t1.join();
        System.out.println("3. State after t1 finishes: " + t1.getState() + "\n");

        // ---------------------------------------------------------

        // 4. TIMED_WAITING
        // A thread that goes to sleep for 5 seconds.
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        });
        t2.start();
        Thread.sleep(200); // Give t2 a moment to actually fall asleep
        System.out.println("4. State while sleeping: " + t2.getState() + " ");

        // ---------------------------------------------------------

        // 5. WAITING
        // A thread that waits indefinitely for a lock notification.
        //Once inside the synchronized block, the code calls lock.wait().
        // This is where the granular magic happens. Calling wait() performs two
        // actions atomically: it suspends the thread's execution indefinitely,
        // and it immediately releases its hold on the lock so that other threads can use it.
        //  Because no timeout duration is passed to the wait() method, the thread
        // transitions to the WAITING state and will remain parked there forever until
        // another thread calls lock.notify() or lock.notifyAll().
        Object lock = new Object();
        Thread t3 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                }
            }
        });
        t3.start();
        Thread.sleep(200); // Give t3 a moment to call lock.wait()
        System.out.println("5. State while waiting indefinitely: " + t3.getState());

        // ---------------------------------------------------------

        // 6. BLOCKED
        // We need two threads for this. Thread A hogs the lock forever.
        // Thread B tries to get the lock, but can't, so it gets BLOCKED.
        Thread threadHog = new Thread(() -> {
            synchronized (lock) {
                while (true) { /* Infinite loop holding the lock! */ }
            }
        });
        threadHog.start();
        Thread.sleep(200); // Give the hog time to grab the lock

        Thread t4_blocked = new Thread(() -> {
            synchronized (lock) {
                System.out.println("I will never print because I can't get the lock.");
            }
        });
        t4_blocked.start();
        Thread.sleep(200); // Give t4 time to try (and fail) to get the lock
        System.out.println("6. State when waiting for a held lock: " + t4_blocked.getState());

        // Force exit because threadHog is in an infinite loop
        System.exit(0);
    }
}
