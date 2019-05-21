import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


//Adapted from https://www.geeksforgeeks.org/program-for-goldbachs-conjecture-two-primes-with-given-sum/

public class Goldbach {

    static int max;
    static List<Integer> primes;

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Ainput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            System.out.print("Case #"+testcase+": ");
            int number = scanner.nextInt();
            if(number %2 == 0) even(number);
            else odd(number);
        }

    }

    private static void odd(int number) {
        System.out.format("\n");
    }

    private static void even(int n) {
        boolean[] isPrime = new boolean[n];

        for (int i = 2; i < n; i++)
            isPrime[i] = true;

        // determine primes < n using Sieve of Eratosthenes
        for (int factor = 2; factor * factor < n; factor++) {
            if (isPrime[factor]) {
                for (int j = factor; factor * j < n; j++)
                    isPrime[factor * j] = false;
            }
        }

        // count primes
        int primes = 0;
        for (int i = 2; i < n; i++)
            if (isPrime[i]) primes++;



        // store primes in list
        int[] list = new int[primes];
        int count = 0;
        for (int i = 0; i < n; i++)
            if (isPrime[i]) list[count++] = i;

        // check if n can be expressed as sum of two primes
        int left = 0, right = count - 1;
        while (left <= right) {
            if (list[left] + list[right] == n) break;
            else if (list[left] + list[right] < n) left++;
            else right--;
        }
        System.out.format("%d %d\n", list[left], list[right]);
    }


}


