import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ratios {


    static boolean[] isPrime;
    static List<Double> list;

    public static void main(String[] args) throws FileNotFoundException {

        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Dinput2.in"));

        int t = scanner.nextInt();
        //scanner.nextLine();
        getPrimes(10000000);
        //System.out.println(list.size() + "");
        for (int testcase = 1; testcase <= t; testcase++) {
            Double p = scanner.nextDouble();
            Double q = scanner.nextDouble();
            Double min = Math.min(p, q);
            Double max = Math.max(p,q);
            //p = Math.min(p, q);
            //System.out.format("Case #%d: ", testcase);
            Double ratio = min / max;
            //ratio = Math.round(ratio);
            ratio = Math.round(ratio * 100000000.0) / 100000000.0;

            //System.out.println(ratio);
            Boolean flag = false;
            Boolean found = false;
            while (!flag) {
                for (int i = 0; i < list.size(); i++) {
                    //n1 = i;
                    for (int j = i; j < list.size(); j++) {
                        Double val = list.get(i) / list.get(j);
                        val = Math.round(val * 100000000.0) / 100000000.0;

                       /* if (testcase != 1) {
                            System.out.println("i: " + list.get(i) + ", j: " + list.get(j) + " = " + val);
                        }*/
                       //System.out.println(val +", "+ratio);
                        if (val.equals(ratio)) {
                            //System.out.println(list.get(i) + " " + list.get(j));
                            System.out.format("Case #%d: %.0f %.0f\n", testcase, list.get(i), list.get(j));
                            found = true;

                            flag = true;
                        }
                    }
                }
                flag = true;
            }

            if (!found) {
                System.out.format("Case #%d: impossible\n", testcase);
            }
        }

    }


    private static void getPrimes(int number) {
        list = new ArrayList<>();
        isPrime = new boolean[number];
        for (int i = 0; i < number; i++)
            isPrime[i] = true;

        isPrime[0] = false;
        isPrime[1] = false;
        // determine primes < n using Sieve of Eratosthenes
        for (int factor = 2; factor * factor < number; factor++) {
            if (isPrime[factor]) {
                Double casted = new Double(factor);
                list.add(casted);
                for (int j = factor; factor * j < number; j++) {
                    isPrime[factor * j] = false;
                }
                //for (int i=factor*2; i<=isPrime.length; i += factor)
                //  isPrime[i] = false;
            }
        }
    }
}