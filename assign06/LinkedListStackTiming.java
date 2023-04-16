package assign06;

public class LinkedListStackTiming {

    public static void main(String[] args) {
        LinkedListStack<Integer> testList = new LinkedListStack<Integer>();
        int problemSize = 1000000;

        long startTime, midpointTime, stopTime;
        for (int i = 0; i < 14; i++) {
            testList.clear();
            for(int x = 0; x < problemSize ; x++) {
                testList.push(x);
            }

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { // empty block
            }

            // Now, run the test.

            long timesToLoop = 100000;

            startTime = System.nanoTime();

            for (int j = 0; j < timesToLoop; j++) {
                testList.peek();
                //testList.pop()
                //testList.push()
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.

            //testList.clear();
            for (int j = 0; j < timesToLoop; j++) { // empty block
                //testList.pop
                //testList.push
            }

            stopTime = System.nanoTime();

            // Compute the time, subtract the cost of running the loop
            // from the cost of running the loop and computing square roots.
            // Average it over the number of runs.

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

            System.out.println(averageTime);
            problemSize += 1000000;
        }
    }
}
