package assign04;

import java.util.ArrayList;
import java.util.List;

public class LargestNumberSolverTimingTest {

    public static void main(String[] args) {
        long startTime, midpointTime, stopTime;
        int problemSize = 2000;

        int timesToLoop = 1000;

        for (int j = 0; j < 10; j++) {

            List list = new ArrayList<Integer[]>();

            for (int i = 0; i < problemSize; i++) {
                list.add(new Integer[]{(int) (Math.random() * 10), (int) (Math.random() * 10),(int) (Math.random() * 10), (int) (Math.random() * 10)});
            }
            problemSize += 2000;

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++)
                LargestNumberSolver.findKthLargest(list, (int) (Math.random()*list.size()));

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.

            for (long i = 0; i < timesToLoop; i++) {

            }

            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

            System.out.println(list.size() + " " + averageTime);

        }
    }
}

