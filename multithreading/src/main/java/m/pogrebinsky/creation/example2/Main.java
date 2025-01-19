package m.pogrebinsky.creation.example2;

public class Main {
    public static void main(String[] args) {
        NewThread newThread = new NewThread();
        newThread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello from new thread: " + Thread.currentThread().getName());
        }
    }
}
