package assign08;

public class SortedTiming {
    public static void main(String[] args) {
        long startTime, midpointTime, stopTime;
        int problemSize = 1000;

        int timesToLoop = 1000;

        for (int j = 0; j < 10; j++) {

            BinarySearchTree<Integer> test = new BinarySearchTree<>();
            for(int i =0; i< problemSize; i++){
                test.add(i);
            }

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { //empty block
            }



            startTime = System.nanoTime();
            for (long i = 0; i < timesToLoop; i++) {
                for(int x = 0; x<problemSize; x++){
                    test.contains(x);
                }
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
            problemSize += 1000;

        }
    }
}
