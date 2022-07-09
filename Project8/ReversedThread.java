public class ReversedThread {
    static final int SIZE = 50;

    public static void main(String[] args) {
        Thread []threads = new Thread[SIZE];
        for (int i = 0; i < SIZE; i++) {
            threads[i] = new Thread(new PrintThread(i));
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = SIZE - 1; i >= 0; i--) {
            try {
                Thread.sleep(250);
                threads[i].start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class PrintThread implements Runnable {
        private final int index;

        public PrintThread(int i) {
            this.index = i;
        }

        @Override
        public void run() {
            System.out.println("Hello from Thread! <" + (index + 1) + ">");
        }
    }
}