import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class H {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Hinput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; ++testcase) {
            int numOfArmies = scanner.nextInt();
            int a = scanner.nextInt();

            for (int i = 1; i < numOfArmies; ++i) {
                int b = scanner.nextInt();
                int res = gcd(a, b);
                a = res;
            }

            System.out.format("Case #%d: %d\n", testcase, a);
        }
    }

    static int gcd(int a, int b) {
        if (0 == b) return a;
        return gcd(b, a % b);
    }
}
