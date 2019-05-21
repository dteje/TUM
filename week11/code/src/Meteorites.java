//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Meteorites {
//    static final int MAXVAL = 1000;
//
//    static int[] vertx, verty;
//
//    public static void main(String[] args) throws FileNotFoundException {
//        //Scanner scanner = new Scanner(System.in);
//        Scanner scanner = new Scanner(new File("Binput2.in"));
//
//        int t = scanner.nextInt();
//        scanner.nextLine();
//
//        for (int testcase = 1; testcase <= t; ++testcase) {
//            int x_met = scanner.nextInt();
//            int y_met = scanner.nextInt();
//            int numOfSides = scanner.nextInt();
//            Point met = new Point(x_met, y_met);
//            Point[] polygon = new Point[numOfSides*2];
//            int i = 0;
//            for (int side = 0; side < numOfSides; ++side) {
//                int x_a = scanner.nextInt();
//                int y_a = scanner.nextInt();
//                polygon[i++] = new Point(x_a, y_a);
//                int x_b = scanner.nextInt();
//                int y_b = scanner.nextInt();
//                polygon[i++] = new Point(x_b, y_b);
//            }
//
//
//            if (isInside(polygon, numOfSides, met)) System.out.format("Case #%d: jackpot\n", testcase);
//            else System.out.format("Case #%d: too bad\n", testcase);
//            /*
//                if (isContained(met)) System.out.format("Case #%d: jackpot\n", testcase);
//                else System.out.format("Case #%d: too bad\n", testcase);
//*/
//
//
//        }
//    }
//
//    private static boolean isInside(Point[] polygon, int n, Point p) {
//        // There must be at least 3 vertices in polygon[]
//        if (n < 3) return false;
//
//        // Create a point for line segment from p to infinite
//        Point extreme = new Point(1001, p.y);
//
//        // Count intersections of the above line with sides of polygon
//        int count = 0, i = 0;
//        do {
//            int next = (i + 1) % n;
//
//            // Check if the line segment from 'p' to 'extreme' intersects
//            // with the line segment from 'polygon[i]' to 'polygon[next]'
//            if (intersect(polygon[i], polygon[next], p, extreme)) {
//                // If the point 'p' is colinear with line segment 'i-next',
//                // then check if it lies on segment. If it lies, return true,
//                // otherwise false
//                if (orientation(polygon[i], p, polygon[next]) == 0)
//                    return onSegment(polygon[i], p, polygon[next]);
//
//                count++;
//            }
//            i = next;
//        } while (i != 0);
//
//        // Return true if count is odd, false otherwise
//        return count%2 == 1;  // Same as (count%2 == 1)
//    }
//
//    private static int orientation(Point p, Point q, Point r) {
//        // See https://www.geeksforgeeks.org/orientation-3-ordered-points/
//        // for details of below formula.
//        int val = (q.y - p.y) * (r.x - q.x) -
//                (q.x - p.x) * (r.y - q.y);
//
//        if (val == 0) return 0;  // colinear
//
//        return (val > 0) ? 1 : 2; // clock or counterclock wise
//    }
//
//    private static boolean intersect(Point p1, Point q1, Point p2, Point q2) {
//
//
//        // Find the four orientations needed for general and
//        // special cases
//        int o1 = orientation(p1, q1, p2);
//        int o2 = orientation(p1, q1, q2);
//        int o3 = orientation(p2, q2, p1);
//        int o4 = orientation(p2, q2, q1);
//
//        // General case
//        if (o1 != o2 && o3 != o4) {
//            //p1 o p2 estan e2
//          /*  if (onSegment(p2, p1, q2)) {
//               // System.out.println("a");
//                intersections.add(p1);
//                // return true;
//
//            } else if (inLine(p2, q1, q2)) {
//                System.out.println("b");
//                intersections.add(q1);
//                //return true;
//            }*/
//            return true;
//        }
//
//
//        // Special Cases
//        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
//        if (o1 == 0 && onSegment(p1, p2, q1)) {
//            return true;
//        }
//
//        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
//        if (o2 == 0 && onSegment(p1, q2, q1)) {
//            return true;
//        }
//
//        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
//        if (o3 == 0 && onSegment(p2, p1, q2)) {
//            return true;
//        }
//        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
//        if (o4 == 0 && onSegment(p2, q1, q2)) {
//            return true;
//        }
//
//        return false; // Doesn't fall in any of the above cases
//
//    }
//
//    private static boolean onSegment(Point p, Point q, Point r) {
//        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
//                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
//            return true;
//
//        return false;
//    }
//
//
//}
