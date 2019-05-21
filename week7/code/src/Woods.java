import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Woods {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Dinput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            int numOfTrees = scanner.nextInt();
            int numOfSaws = scanner.nextInt();
            long[] saws = new long[numOfSaws];
            List<Long> times = new ArrayList<>();
            for (int tree = 0; tree < numOfTrees ; tree++) {
                times.add(tree, scanner.nextLong());
            }

            //Greedy-knapsack
            Collections.sort(times, Collections.reverseOrder());

            for (int tree = 0; tree < numOfTrees; tree++) {
                int saw = 0;
                if (tree < saws.length) saw = tree;
                else {
                    for (int i = 0; i < saws.length; i++) {
                        if (saws[saw] > saws[i]) saw = i;
                    }
                }
                saws[saw] += times.get(tree);
            }
            long max = 0;
            for (int i = 0; i < saws.length; i++) {
                if (saws[i] > max)
                    max = saws[i];
            }

            System.out.println("Case #"+testcase+": " + max);


        }


    }
}