import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Swords {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(new File("Binput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            Sword s1 = read(scanner);
            Sword s2 = read(scanner);

            Line blade1 = getBlade(s1);
            Line ab1 = getABLine(s1);
            Line blade2 = getBlade(s2);
            Line ab2 = getABLine(s2);

            Point mid1 = getIntersection(blade1, ab1);
            Point mid2 = getIntersection(blade2, ab2);

            if (Math.abs(blade1.m - blade2.m) < 0.000000001) {
                System.out.format("Case #%d: strange\n", testcase);
                continue;
            }

            Point intersection = getIntersection(blade1, blade2);
            if (intersection.y >=  Math.min(s1.c.y, s2.c.y)) System.out.format("Case #%d: %.1f %.1f\n", testcase, intersection.x, intersection.y);
            else System.out.format("Case #%d: strange\n", testcase);


        }

    }




    private static Line getABLine(Sword sword) {
        double top = sword.b.y - sword.a.y;
        double bot = sword.b.x - sword.a.x;
        if (top == 0) return new Line(Double.MAX_VALUE, sword.a, true);
        double slope = (top) / (bot);
        return new Line(slope, sword.a, false);
    }

    private static Sword read(Scanner scanner) {
        Point a = new Point((double) scanner.nextInt(), (double) scanner.nextInt());
        Point b = new Point((double) scanner.nextInt(), (double) scanner.nextInt());
        Point c = new Point((double) scanner.nextInt(), (double) scanner.nextInt());
        return new Sword(a, b, c);

    }

    private static Line getBlade(Sword sword) {
        double top = sword.b.y - sword.a.y;
        double bot = sword.b.x - sword.a.x;
        if (top == 0) return new Line(Double.MAX_VALUE, sword.c, true);
        double slope = -1 / ((top) / (bot));
        return new Line(slope, sword.c, false);
    }

    private static Point getIntersection(Line a, Line b) {
        if (a.vertical) return getVerticalIntersection(a.p.x, b);
        if (b.vertical) return getVerticalIntersection(b.p.x, a);
        //x
        double top = a.m * a.p.x - b.m * b.p.x + b.p.y - a.p.y;
        double bot = a.m - b.m;
        double x = 0.;
        if (bot != 0) x = top / bot;
        //y
        double y = a.m * (x - a.p.x) + a.p.y;
        return new Point(x, y);
    }

    private static Point getVerticalIntersection(double x, Line a) {
        double n = a.p.y - a.m * a.p.x;
        double y = a.m * x + n;
        return new Point(x, y);
    }
}


class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

}

class Sword {
    Point a, b;
    Point c;

    public Sword(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public String toString() {
        String res = "A(" + a.x + "," + a.y + ") ;";
        res += " B(" + b.x + "," + b.y + ") ";
        res += " C(" + c.x + "," + c.y + ") ";
        return res;
    }
}

class Line {
    //y - p.y = m(x - p.x)
    double m;
    Point p;
    boolean vertical;

    public Line(double m, Point p) {
        this.m = m;
        this.p = p;
        this.vertical = false;
    }

    public Line(double m, Point p, boolean vertical) {
        this.m = m;
        this.p = p;
        this.vertical = vertical;
    }


    public String toString() {
        return "m: " + m + ", p(" + p.x + "," + p.y + ")";
    }

}
