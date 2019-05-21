import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class A {

    static Rectangle r1, r2;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Ainput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; ++testcase) {

            r1 = new Rectangle((double) scanner.nextInt() , (double) scanner.nextInt() );

            r2 = new Rectangle((double) scanner.nextInt()-0.001, (double) scanner.nextInt()-0.001);


            if (fits()) System.out.format("Case #%d: possible\n", testcase);
            else System.out.format("Case #%d: impossible\n", testcase);


        }

    }

    static boolean fits() {
        if (r2.x <= r1.x && r2.y <= r1.y) return true;
        if (r2.y <= r1.y && (r2.x <= r1.x || r1.y * (r2.x * r2.x + r2.y * r2.y) >= (2 * r2.x * r2.y * r1.x + (r2.x * r2.x - r2.y * r2.y) * Math.sqrt(r2.x * r2.x + r2.y * r2.y - r1.x * r1.x))))
            return true;
        return false;
    }
}

class Rectangle {
    double x, y;

    public Rectangle(double x, double y) {
        this.x = Math.max(x, y);
        this.y = Math.min(x, y);
    }
}
