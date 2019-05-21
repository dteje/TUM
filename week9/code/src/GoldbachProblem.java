import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoldbachProblem {

    static boolean[] isPrime;
    static List<Integer> list;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Ainput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();
        getPrimes(100000001);

        for (int testcase = 1; testcase <= t; testcase++) {
            int number = scanner.nextInt();

            System.out.format("Case #%d: ", testcase);
            if (number % 2 == 0) even(number);
            else odd(number);
            System.out.println();
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
                list.add(factor);
                for (int j = factor; factor * j < number; j++) {
                    isPrime[factor * j] = false;
                }
                //for (int i=factor*2; i<=isPrime.length; i += factor)
                  //  isPrime[i] = false;
            }
        }
    }

    private static void even(int number) {


        int numPrimes = 0;
        for (int i = 2; i < number; i++)
            if (isPrime[i]) numPrimes++;

        int[] primes = new int[numPrimes];

        int c = 0;

        for (int i = 0; i < number; i++) {

            if (isPrime[i]) primes[c++] = i;
        }


        int left = 0;
        int right = primes.length - 1;
        while (left <= right) {
            if (primes[left] + primes[right] == number) break;
            else if (primes[left] + primes[right] < number) left++;
            else right--;
        }
        System.out.format("%d %d", primes[left], primes[right]);


    }

    private static void odd(int number) {

        if (isPrime[number - 4]) {
            //Option 1: 2 + 2 + (number - 4) if (number-4) is prime
            System.out.format("2 2 %d", number - 4);
            return;
        }

        //Option 2: 3 + even (number-3)
        System.out.print("3 ");
        even(number - 3);

    }

}

