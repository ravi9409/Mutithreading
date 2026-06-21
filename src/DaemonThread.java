public class DaemonThread {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            System.out.println("Daemon Thread");
        });
        t1.setDaemon(true);
        t1.start();
        System.out.println("Main Thread");
        //Daemon thread will finish after main thread since its running in background 
    }
}
