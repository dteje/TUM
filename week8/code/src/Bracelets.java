import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Bracelets {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Binput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            char[] aux = a.toCharArray();
            Collections.reverse(Arrays.asList(aux));
            String a_rev = String.valueOf(aux);
            int val = 1000;
            for (int i = 0; i < a.length(); i++) {
                //original string
                String shift;
                if (i == 0) {
                    shift = a;
                } else {
                    shift = a.substring(i, a.length() - 1) + a.substring(0, i-1);
                }
                int ret = editDist(shift, b, shift.length(), b.length());
                val = Math.min(val, ret);
            }
            for (int i = 0; i < a_rev.length(); i++) {
                //reversed string
                String shift;
                if (i == 0) {
                    shift = a_rev;
                } else {
                    shift = a_rev.substring(i, a_rev.length() - 1) + a_rev.substring(0, i-1);
                }
                int ret = editDist(a_rev, b, a_rev.length(), b.length());
                val = Math.min(val, ret);
            }

            int f = (a.length()+b.length()-val)/2;
            System.out.println("Case #" + testcase + ": " + f);
        }

    }

    static int min(int x, int y, int z) {
        if (x <= y && x <= z) return x;
        if (y <= x && y <= z) return y;
        else return z;
    }

    static int editDist(String str1, String str2, int m, int n) {
        // If first string is empty, the only option is to
        // insert all characters of second string into first
        if (m == 0) return n;

        // If second string is empty, the only option is to
        // remove all characters of first string
        if (n == 0) return m;

        // If last characters of two strings are same, nothing
        // much to do. Ignore last characters and get count for
        // remaining strings.
        if (str1.charAt(m - 1) == str2.charAt(n - 1))
            return editDist(str1, str2, m - 1, n - 1);

        // If last characters are not same, consider all three
        // operations on last character of first string, recursively
        // compute minimum cost for all three operations and take
        // minimum of three values.
        return 1 + editDist(str1, str2, m - 1, n);// Replace

    }

//        return dp[m][n];
    //  }
}
