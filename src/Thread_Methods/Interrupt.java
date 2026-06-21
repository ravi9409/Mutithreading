public class Interrupt {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        });

        t.start();
        //Break the sleeping and waiting time and instantly execute it 
        t.interrupt();
    }
}