/*
 * Threads active when this program runs:
 *
 * Thread              | Daemon | Purpose
 * --------------------|--------|------------------------------------------------
 * main                | No     | Your application's main thread
 * Signal Dispatcher   | Yes    | Handles OS signals (e.g. SIGINT)
 * Reference Handler   | Yes    | Clears Reference objects (WeakRef, SoftRef, PhantomRef)
 * Attach Listener     | Yes    | Handles JVM tool attach requests (e.g. from jstack, profilers)
 * Notification Thread | Yes    | JVM internal notification dispatch
 * Common-Cleaner      | Yes    | Runs Cleaner actions (modern replacement for finalization)
 * Finalizer           | Yes    | Runs finalize() methods on GC'd objects
 *
 * JVM exits when all non-daemon threads finish — here, only "main" is non-daemon.
 */
public class AllActiveThread {
    public static void main(String[] args) {
        
        for(Thread t:Thread.getAllStackTraces().keySet()){
            System.out.println(t.getName()+"    "+t.getState());
        }
        System.out.println();
    }
}
