
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PasswordProblem {
    static int numOfDigits;
    static final long[] POWERS_OF_10 =
            {1L, 10L, 100L, 1000L, 10000L, 100000L, 1000000L, 10000000L, 100000000L, 1000000000L,
                    10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L,
                    1000000000000000L, 10000000000000000L, 100000000000000000L
            };

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Binput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            numOfDigits = scanner.nextInt();
            long x = scanner.nextInt();
            //int factor = (int) Math.pow(10, numOfDigits);
            long factor = POWERS_OF_10[numOfDigits];
            long val = lookFor(x);
            //boolean flag = false;
            while (true) {
                long res = x * val;
                //String s = res +"";
                //String s2 = s.substring(s.length()-numOfDigits);

                //int new_num = Math.max(Integer.parseInt(s2), 1);
                if (res%factor == 1){
                    System.out.format("Case #%d: %d\n", testcase, val);
                    break;
                }
                else val += 10;
            }


        }
    }

    static long lookFor(long num) {

        long val = num % 10;
        if (val == 1) return 1;
        else if (val == 3) return 7;
        else if (val == 7) return 3;
        else return 9;


    }
}
