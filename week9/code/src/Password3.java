import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Password3 {
    static int numOfDigits;

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Binput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            numOfDigits = scanner.nextInt();
            int x = scanner.nextInt();
            //int factor = (int) Math.pow(10, numOfDigits);
            int val = lookFor(x);
            //boolean flag = false;
            while (true) {
                int res = x * val;
                String s1 = ""+res;
                String s2 = s1.substring(s1.length()-numOfDigits);
              //  System.out.print("s1=" + s1);
                int f = Integer.parseInt(s2);
                //System.out.println(f);
                if (res % f == 1){
                    System.out.format("Case #%d: %d\n", testcase, val);
                    break;
                }
                else val += 10;
            }


        }
    }

    static int lookFor(int num) {

        int val = num % 10;
        if (val == 1) return 1;
        else if (val == 3) return 7;
        else if (val == 7) return 3;
        else return 9;


    }
}
