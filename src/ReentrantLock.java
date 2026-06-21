public class ReentrantLock {
    public synchronized void outerMethod() {
        System.out.println("Entered outerMethod");
        innerMethod(); // Calling another synchronized method
        System.out.println("Exiting outerMethod");
    }

    public synchronized void innerMethod() {
        System.out.println("Entered innerMethod");
        // Do something
        System.out.println("Exiting innerMethod");
    }

    public static void main(String[] args) {
        ReentrantLock example = new ReentrantLock();

        Thread thread = new Thread(() -> {
            example.outerMethod();
        });

        thread.start();
    }
}
