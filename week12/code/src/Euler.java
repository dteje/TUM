import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Euler {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Ainput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            APoint p1 = new APoint(scanner.nextDouble(), scanner.nextDouble());
            APoint p2 = new APoint(scanner.nextDouble(), scanner.nextDouble());
            APoint p3 = new APoint(scanner.nextDouble(), scanner.nextDouble());
            APoint centroid = getCentroid(p1, p2, p3);
            APoint orthocenter = getOrthocenter(p1, p2, p3);
            APoint circumcenter = getCircumcenter(p1, p2, p3);
            System.out.format("Case #%d:\n", testcase);
            System.out.format("%f %f\n", centroid.x, centroid.y);
            System.out.format("%f %f\n", orthocenter.x, orthocenter.y);
            System.out.format("%f %f\n", circumcenter.x, circumcenter.y);
        }
    }


    private static APoint getCentroid(APoint a, APoint b, APoint c) {
        return new APoint((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3);
    }

    private static APoint getOrthocenter(APoint a, APoint b, APoint c){
        if(a.y == b.y) return getOrthocenter(c, b, a);
        //A-B
        double perpendicularSlopeAB = 0.;
        if(a.x!=b.x && a.y!=b.y) perpendicularSlopeAB = -1/((b.y-a.y)/(b.x-a.x));
        ALine ab = new ALine(perpendicularSlopeAB, c);


        //A-C
        double perpendicularSlopeAC = 0.;
        if(a.x!=c.x && a.y!=c.y) perpendicularSlopeAC = -1/((c.y-a.y)/(c.x-a.x));
        ALine ac = new ALine(perpendicularSlopeAC, b);

        return solveEquation(ab, ac);

    }

    private static APoint getCircumcenter(APoint a, APoint b, APoint c){
        //A-B
        if(a.y == b.y) return getCircumcenter(c, b, a);
        APoint midAB = new APoint((a.x+b.x)/2, (a.y+b.y)/2);
        double slopeAB = -1/((b.y-a.y)/(b.x-a.x));
        ALine ab = new ALine(slopeAB, midAB);
        //A-C
        APoint midAC = new APoint((a.x+c.x)/2, (a.y+c.y)/2);
        double slopeAC = -1/((c.y-a.y)/(c.x-a.x));
        ALine ac = new ALine(slopeAC, midAC);

        //System.out.println(slopeAB + " " + midAB.x + ", " + midAB.y);
        return solveEquation(ab, ac);
    }

    private static APoint solveEquation(ALine a, ALine b) {
        //x
        double top = a.m*a.p.x - b.m*b.p.x + b.p.y - a.p.y;
        double bot = a.m - b.m;
        double x = 0.;
        if(bot != 0) x = top/bot;
        //y
        double y = a.m*(x-a.p.x) + a.p.y;
        return new APoint(x, y);
    }

}


class APoint{
    double x, y;

    public APoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class ALine{
    //y - p.y = m(x - p.x)
    double m;
    APoint p;
    public ALine(double m, APoint p){
        this.m = m;
        this.p = p;
    }
}


