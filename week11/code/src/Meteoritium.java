//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//
//public class Meteoritium {
//    static final int MAXVAL = 1000;
//    static List<Edge> edges;
//    static List<Point> intersections;
//
//    public static void main(String[] args) throws FileNotFoundException {
//        Scanner scanner = new Scanner(System.in);
//        //Scanner scanner = new Scanner(new File("Binput1.in"));
//
//        int t = scanner.nextInt();
//        scanner.nextLine();
//
//        for (int testcase = 1; testcase <= t; ++testcase) {
//            int x_met = scanner.nextInt();
//            int y_met = scanner.nextInt();
//            Point met = new Point(x_met, y_met);
//            int numOfSides = scanner.nextInt();
//            edges = new ArrayList<>();
//            intersections = new ArrayList<>();
//
//            for (int side = 0; side < numOfSides; ++side) {
//                int x_a = scanner.nextInt();
//                int y_a = scanner.nextInt();
//                int x_b = scanner.nextInt();
//                int y_b = scanner.nextInt();
//                edges.add(new Edge(new Point(x_a, y_a), new Point(x_b, y_b)));
//            }
//
//            if (isContained(met)) System.out.format("Case #%d: jackpot\n", testcase);
//            else System.out.format("Case #%d: too bad\n", testcase);
//
//
//        }
//    }
//
//
//
//    private static boolean isContained(Point met) {
//        //Trazo una linea de x_met hasta x = +inf
//        Edge toInf = new Edge(met, new Point(1111, met.y+1000));
//
//        //Recorro los Edges y comparo con toInf
//        int c = 0;
//
//        for (Edge e : edges) {
//
//            if (intersect(e, toInf)) c++;
//        }
//
//        for (Point p : intersections) {
//            System.out.println("POINT: x = " + p.x + ", y = " + p.y);
//        }
//        //System.out.println(c+" = c");
//        if (c % 2 == 0) return false;
//        return true;
//        //Cuento intersecciones
//
//    }
//
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
//    private static boolean intersect(Edge e1, Edge e2) {
//
//        Point p1 = e1.a;
//        Point q1 = e1.b;
//        Point p2 = e2.a; //(met_x, met_y)
//        Point q2 = e2.b; //(MAXVAL, met_y)
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
//    public static boolean inLine(Point A, Point B, Point C) {
//        // if AC is horizontal
//        if (A.x == C.x) return B.x == C.x;
//        // if AC is vertical.
//        if (A.y == C.y) return B.y == C.y;
//        // match the gradients
//        return (A.x - C.x) * (A.y - C.y) == (C.x - B.x) * (C.y - B.y);
//    }
//
//
//}
//
//
//
//
//class Edge {
//    Point a, b;
//
//    public Edge(Point a, Point b) {
//        this.a = a;
//        this.b = b;
//    }
//}
//
