class Task extends Thread {
    private String taskName;

    Task(String name, int priority) {
        this.taskName = name;
        this.setName(name);
        this.setPriority(priority);
    }

    @Override
    public void run() {
        System.out.println("Starting: " + taskName + " | Priority: " + getPriority());
        for (int i = 0; i < 5; i++) {
            System.out.println(taskName + " -> step " + (i + 1));
        }
        System.out.println("Finished: " + taskName);
    }
}

public class ThreadPriority {
    public static void main(String[] args) {
        Task low    = new Task("LowPriorityThread",    Thread.MIN_PRIORITY);   // 1
        Task medium = new Task("MediumPriorityThread", Thread.NORM_PRIORITY);  // 5
        Task high   = new Task("HighPriorityThread",   Thread.MAX_PRIORITY);   // 10

        System.out.println("MIN_PRIORITY  = " + Thread.MIN_PRIORITY);
        System.out.println("NORM_PRIORITY = " + Thread.NORM_PRIORITY);
        System.out.println("MAX_PRIORITY  = " + Thread.MAX_PRIORITY);
        System.out.println("-----------------------------------");

        // Start in reverse priority order to show priority matters, not start order
        low.start();
        medium.start();
        high.start();

        try {
            low.join();
            medium.join();
            high.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("-----------------------------------");
        System.out.println("All threads completed.");
    }
}
