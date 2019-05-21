import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Area51{

    static List<CPoint> list;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Cinput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            int numOfEdges = scanner.nextInt();
            list = new ArrayList<>();
            for (int i = 0; i < numOfEdges; i++) {
                CPoint p = new CPoint(scanner.nextDouble(), scanner.nextDouble());
                list.add(p);
            }
            //for(CPoint cp : list) System.out.format("%f %f\n", cp.x, cp.y);
            list.add(list.get(0));
            double area = Math.abs(area());
            System.out.format("Case #%d: %f\n", testcase, area);
        }

    }

    public static double area() {
        double area = 0.;
        for(int i=0 ; i<list.size()-1 ; i++){
            int j = (i+1)%list.size();
            area += list.get(i).x * list.get(j).y;
            area -= list.get(i).y * list.get(j).x;
        }
        return 0.5 * Math.abs(area);
    }
}

class CPoint {

    double x, y;
    public CPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
