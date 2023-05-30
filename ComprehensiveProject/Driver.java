package comprehensive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver class that accepts a filename from the console and creates a disjointSetForest as specified in the file
 *
 * @author Bradley Bartelt & Isaac Hall
 * @version 4/22/23
 */
public class Driver {
    private DisjointSetForest<String> set;
    public static void main(String args[]) throws FileNotFoundException {
        //gets filename from the args array
        File file = new File(args[0]);
        Scanner scan = new Scanner(file);
        DisjointSetForest<String> set = new DisjointSetForest<String>();

        //first while loop creates the sets specified in the file, when there is a blank line, it
        //moves to the next loop
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            if(line.isEmpty()){
                break;
            }
            set.makeSet(line);
        }

        //this while loop unions all the specified sets together, when there is a blank line, it moves to the next loop
        while(scan.hasNextLine()){

            String line = scan.nextLine();
            if(line.isEmpty()){
                break;
            }
            // save the two separate string values
            StringBuilder e1 = new StringBuilder();
            StringBuilder e2 = new StringBuilder();
            int count = 0;
            // create the strings from the given line and separate them when theres a space
            for(int i =0; i < line.length(); i++){
                if(line.charAt(i) == 32){
                    count++;
                    continue;
                }
                if(count > 0){
                    e2.append(line.charAt(i));
                }
                else
                    e1.append(line.charAt(i));
            }
            // union them together
            set.union(e1.toString(),e2.toString());

        }

        //checks specified nodes to see if they are connected or not by calling getRepresentative,
        //if they are connected, prints "connected" if not, "not connected"
        while(scan.hasNextLine()){

            String line = scan.nextLine();
            // same as last loop, however, instead of unioning them we check their rep
            StringBuilder e1 = new StringBuilder();
            StringBuilder e2 = new StringBuilder();
            int count = 0;

            for(int i =0; i < line.length(); i++){
                if(line.charAt(i) == 32){
                    count++;
                    continue;
                }
                if(count > 0){
                    e2.append(line.charAt(i));
                }
                else
                    e1.append(line.charAt(i));
            }
            // if the reps are the same they're connected
            if(set.getRepresentative(e1.toString()).equals(set.getRepresentative(e2.toString())))
                System.out.println("connected");
            // else they aren't
            else
                System.out.println("not connected");
        }
    }
}

