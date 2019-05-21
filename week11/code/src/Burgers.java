import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Burgers {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Dinput3.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase<=t ; ++testcase){
            int radius = scanner.nextInt();
            int numOfBurgers = scanner.nextInt();

            if(numOfBurgers == 1){
                System.out.format("Case #%d: %.1f\n", testcase, (float) radius);
                continue;
            }
            double ang = 360.0/(2*numOfBurgers);
            double R = radius + (radius/(Math.sin(Math.toRadians(ang))));

            if (R%(int) R <0.000001 )System.out.format("Case #%d: %.1f\n", testcase, R);
            else System.out.format("Case #%d: %.9f\n", testcase, R);
        }
    }
}
