package m.pogrebinsky.creation.example1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Executing new thread: "
                    + Thread.currentThread().getName() +
                    " Priority: " + Thread.currentThread().getPriority());
        });

        thread.setName("WORKER");
        thread.setPriority(Thread.MAX_PRIORITY);

        //new to me
        //this lambda function is called when an uncaught
        //exception happens in a thread. Good place to cleanup
        //and release resources before the crashing thread dies.
        thread.setUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
        });

        System.out.println("Current thread: " + Thread.currentThread().getName() + " before starting new thread.");
        thread.start();
        System.out.println("Current thread: " + Thread.currentThread().getName() + " after starting new thread.");

        Thread.sleep(1000);
    }
}
