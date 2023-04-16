package assign10;

import java.util.ArrayList;
import java.util.Collections;

public class findKthLargestTimingTest {
    public static void main(String[] args) {
        long startTime, midpointTime, stopTime;
        int problemSize = 100000;

        int timesToLoop = 1000;

        int k = 10000;

        for (int j = 0; j < 10; j++) {

            ArrayList<Integer> list = new ArrayList<>();

            for(int x = 0; x < problemSize; x++) {
                list.add(x);
            }
            Collections.shuffle(list);

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            startTime = System.nanoTime();
            for (long i = 0; i < timesToLoop; i++) {
                //FindKLargest.findKLargestSort(list,k);
                FindKLargest.findKLargestHeap(list,k);
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            for (long i = 0; i < timesToLoop; i++) {

            }

            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

            System.out.println(" " + averageTime);
            k += 10000;

        }
    }
}
