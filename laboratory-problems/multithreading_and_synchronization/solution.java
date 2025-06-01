import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Main {

    static double average = 0;
    // DEFINE OTHER GLOBAL VARIABLES

    static final BoundedRandomGenerator random = new BoundedRandomGenerator();

    private static final int ARRAY_LENGTH = 10000000;

    private static final int NUM_THREADS = 10;


    private static double totalSum;
    public static Lock lock;

    static void init() {
        lock = new ReentrantLock();
        totalSum = 0.0;
    }

    // DO NOT CHANGE
    public static int[] getSubArray(int[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end);
    }

    public static void main(String[] args) {

        init();

        int[] arr = ArrayGenerator.generate(ARRAY_LENGTH);

        CalculateThread[] threads = new CalculateThread[NUM_THREADS];
        int segmentSize = ARRAY_LENGTH / NUM_THREADS;

        for (int i = 0; i < NUM_THREADS; i++) {
            int startIndex = i * segmentSize;
            int endIndex = (i == NUM_THREADS - 1) ? ARRAY_LENGTH : startIndex + segmentSize;

            threads[i] = new CalculateThread(getSubArray(arr, startIndex, endIndex), startIndex, endIndex);
            threads[i].start();
        }

        // Each instance should take a subarray from the original array with equal length
        try {
            for (int i = 0; i < NUM_THREADS; i++) {
                threads[i].join();
            }

            average = totalSum / ARRAY_LENGTH;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // DO NOT CHANGE

        System.out.println("Your calculated average grade is: " + average);
        System.out.println("The actual average grade is: " + ArrayGenerator.actualAvg);

        SynchronizationChecker.checkResult();

    }


    // TO DO: Make the CalculateThread class a thread, you can add methods and attributes
    static class CalculateThread extends Thread{

        private int[] arr;
        int startSearch;
        int endSearch;

        public CalculateThread(int[] arr, int startSearch, int endSearch) {
            this.arr = arr;
            this.startSearch = startSearch;
            this.endSearch = endSearch;
        }

        public Double calculateAverageGrade() {
            return Arrays.stream(arr).average().getAsDouble();
        }

        public void calculateAverageGradeParallel() {
            double sum = 0;
            for (int i : arr) {
                sum += i;
            }
            lock.lock();
            try{
                totalSum += sum;
            } finally{
                lock.unlock();
            }

        }

        @Override
        public void run(){
            calculateAverageGradeParallel();
        }
    }

    /******************************************************
     // DO NOT CHANGE THE CODE BELOW TO THE END OF THE FILE
     *******************************************************/

    static class BoundedRandomGenerator {
        static final Random random = new Random();
        static final int RANDOM_BOUND_UPPER = 10;
        static final int RANDOM_BOUND_LOWER = 6;

        public int nextInt() {
            return random.nextInt(RANDOM_BOUND_UPPER - RANDOM_BOUND_LOWER) + RANDOM_BOUND_LOWER;
        }

    }

    static class ArrayGenerator {

        private static double actualAvg = 0;

        static int[] generate(int length) {
            int[] array = new int[length];

            for (int i = 0; i < length; i++) {
                int grade = Main.random.nextInt();
                actualAvg += grade;
                array[i] = grade;
            }

            actualAvg /= array.length;

            return array;
        }
    }

    static class SynchronizationChecker {
        public static void checkResult() {
            if (ArrayGenerator.actualAvg != average) {
                throw new RuntimeException("The calculated result is not equal to the actual average grade!");
            }
        }
    }
}
