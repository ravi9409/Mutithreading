public class WithoutAtomicIntegerExample {
    private static int counter = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> increment());
        Thread t2 = new Thread(() -> increment());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter: " + counter);
    }

    public static synchronized void increment() {
        for (int i = 0; i < 10000; i++)
            counter++;
    }
}
