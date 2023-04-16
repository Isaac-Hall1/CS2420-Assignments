package assign10;

public class BinaryHeapTimingTest {
    public static void main(String[] args) {
        long startTime, midpointTime, stopTime;
        int problemSize = 10000000;

        int timesToLoop = 10000;



        for (int j = 0; j < 10; j++) {

            BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<Integer>();

            for(int x = 0; x < problemSize; x++) {
                heap.add((int) Math.random()* problemSize);

            }

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            startTime = System.nanoTime();
            for (long i = 0; i < timesToLoop; i++) {
               //heap.add((int) Math.random()* problemSize);
               //heap.peek();
               heap.extractMax();
              // heap.add(problemSize/2);
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            for (long i = 0; i < timesToLoop; i++) {
                //heap.add(problemSize/2);
               // heap.extractMax();
               // int rand = (int) Math.random() * problemSize;
            }

            stopTime = System.nanoTime();
            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

            System.out.println(" " + averageTime);
            problemSize += 10000000;

        }
    }
}
