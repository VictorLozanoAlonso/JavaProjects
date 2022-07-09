import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task1{
    static final int SIZE = 2000;

    public static void main(String[] args) {
        double [][] a = new double[SIZE][SIZE];
        double [][] b = new double[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                a[i][j] = 1.0;
                b[i][j] = 2.0;
            }
        }
        long time = System.currentTimeMillis();
        parallelAddMatrix(a, b);
        System.out.println((System.currentTimeMillis() - time)	+ " msec - parallelAddMatrix()");
        time = System.currentTimeMillis();
        sequentialAddMatrix(a, b);
        System.out.println((System.currentTimeMillis() - time) + " msec - sequentialAddMatrix()");
    }
    public static double[][] parallelAddMatrix(double[][] a, double[][] b){
        double[][] r = new double[SIZE][SIZE];
        AddValues t1 = new AddValues(r, a, b, 0, 0);
        AddValues t2 = new AddValues(r, a, b, 1000, 0);
        AddValues t3 = new AddValues(r, a, b, 0, 1000);
        AddValues t4 = new AddValues(r, a, b, 1000, 1000);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(t1);
        executorService.execute(t2);
        executorService.execute(t3);
        executorService.execute(t4);
        executorService.shutdown();
        return r;
    }

    public static class AddValues implements Runnable {

        private final double[][] result;
        private double[][] a;
        private double[][] b;
        private int x, y;

        public AddValues(double[][] result, double[][] a, double[][] b, int x, int y) {
            this.result = result;
            this.a = a;
            this.b = b;
            this.x = x;
            this.y = y;
        }

        @Override
        public void run() {
            for (int i = y; i < (y + 1000); i++) {
                for (int j = x; j < (x + 1000); j++) {
                    result[i][j] += a[i][j] + b[i][j];
                }
            }
        }
    }

    public static double[][] sequentialAddMatrix(double[][] a, double[][] b) {
        double[][] result = new double[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }
}
