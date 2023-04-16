package assign08;
import java.util.*;

public class JavaTreeTiming {
    public static void main(String[] args) {
        long startTime, midpointTime, stopTime;
        int problemSize = 1000;

        int timesToLoop = 1000;

        for (int j = 0; j < 10; j++) {


            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000) { //empty block
            }

            startTime = System.nanoTime();



            for (long i = 0; i < timesToLoop; i++){

                Random rand = new Random(i);

                TreeSet<Integer> test = new TreeSet<>();

                ArrayList<Integer> randomArray = new ArrayList<>();

                for(int x = 0; x < problemSize; x++){
                    randomArray.add(x);
                }

                Collections.shuffle(randomArray,rand);

                for(int x =0; x< problemSize; x++){
                    test.add(randomArray.get(x));
                }
//                for(int x = 0; x<problemSize; x++){
//                    test.contains(x);
//                }

            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            for (long i = 0; i < timesToLoop; i++) {
                Random rand = new Random(i);
                TreeSet<Integer> test1 = new TreeSet<>();

                ArrayList<Integer> randomArray1 = new ArrayList<>();

                for(int x = 0; x < problemSize; x++){
                    randomArray1.add(x);
                }

                Collections.shuffle(randomArray1,rand);

//                for(int x =0; x< problemSize; x++){
//                    test1.add(randomArray1.get(x));
//                }
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
