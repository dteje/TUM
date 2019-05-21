import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Math.PI;

public class Letters {
    static List<Edge> edges;
    static List<Point> points;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Einput3.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; ++testcase) {

            int numOfVertices = scanner.nextInt();

            //Point ini = new Point(scanner.nextDouble(), scanner.nextDouble());
            //Point p_0 = ini;
            //Point p_1 = new Point(scanner.nextDouble(), scanner.nextDouble());

            edges = new ArrayList<>();

            points = new ArrayList<>();
            //points.add(p_0);
            //points.add(p_1);

            //edges.add(new Edge(p_0, p_1));

            //int i = 1;
            for (int i = 0; i < numOfVertices; i++) {
//                Edge edge = new Edge(p_0, p_1);
//                edges.add(edge);
//                p_0 = p_1;
//                p_1 = new Point(scanner.nextDouble(), scanner.nextDouble());
//                points.add(p_1);
                Point p = new Point(scanner.nextDouble(), scanner.nextDouble());
                points.add(p);
            }

            for (int i = 1; i < points.size(); i++) {
                Edge edge = new Edge(points.get(i - 1), points.get(i));
                edges.add(edge);
            }

            if(getArea(points) == 0){
                System.out.format("Case #%d: 0\n", testcase);
                continue;
            }

            edges.add(new Edge(points.get(points.size() - 1), points.get(0)));
            //edges.add(new Edge(p_1, ini));
            GrahamScan gs = new GrahamScan();
            List<Point> hull = gs.getConvexHull(points);

            //for (Edge e : edges) System.out.format("A(%f, %f) -- B(%f, %f)\n", e.a.x, e.a.y, e.b.x, e.b.y);


            //System.out.println();
            Point centroid = getCentroid();

            int pos = 0;
            for (int i = 0; i < numOfVertices; i++) {
                int initial = -2;
                //Point p, q;
                Point p = edges.get(i).a;
                Point q = edges.get(i).b;
                boolean possible = true;
                for (int j = 0; j < numOfVertices; j++) {
                    if (i == j) continue;
                    // System.out.println(i + " " + j);
                    Point p1 = edges.get(j).a;
                    Point q1 = edges.get(j).b;

                    if (initial == -2 || 0 == initial)
                        initial = orient(p, p1, q);
                    int inP = orient(p, p1, q);
                    //System.out.println("Q1: " + q1.x + ", " + q1.y);
                    int inQ = orient(p, q1, q);
                    //System.out.println("orient: " + in);
                    if (i == 6 && j == 1) {
                        System.out.print("");
                    }
                    if (((inP != initial) && (p1 != p && p1 != q)) || ((inQ != initial) && q1 != p && q1 != q) && 0 != initial) {
                       // System.out.println("in at : " + i + ", " + j);
                        possible = false;
                        break;
                    }
                }
                if (!possible) continue;
                Point proj = getProj(centroid, p, q);
                //System.out.println("PROJ: "+proj.x +", "+proj.y);
                if (onSegment(p, proj, q))
                    pos++;
            }
            System.out.format("Case #%d: %d\n", testcase, pos);
        }
    }

    static double getArea(List<Point> points)
    {
        // Initialze area
        double area = 0.0;

        int n=points.size();

        // Calculate value of shoelace formula
        int j = n - 1;
        for (int i = 0; i < n; i++)
        {

            area += (points.get(j).x + points.get(i).x) * (points.get(j).y - points.get(i).y);

            // j is previous vertex to i
            j = i;
        }

        // Return absolute value
        return Math.abs(area / 2.0);
    }

    static int orient(Point a, Point b, Point c) {
        double val = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
        int r = (int ) val;
        if (0 == r) return 0;
        return (val > 0) ? -1 : 1;
    }

    static Point getCentroid() {
        double centroidX = 0.0, centroidY = 0.0;
        for (Point dp : points) {
            centroidX += dp.x;
            centroidY += dp.y;
        }
        return new Point(centroidX / points.size(), centroidY / points.size());
    }

    static boolean onSegment(Point p, Point q, Point r) {

        int res = orient(p,q,r);
        //System.out.println("orient: "+res);
        if(res!=0) {
          //  System.out.println("false1");
            return false;
        }
        if(q.x<=Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y,r.y) && q.y >= Math.min(p.y, r.y)){
            //System.out.println("true");
            return true;

        }
        //System.out.println("false2");
        return false;
    }

    static Point getProj(Point p, Point x1, Point x2) // from http://stackoverflow.com/questions/1811549/perpendicular-on-a-line-from-a-given-point
    {
        Point proj = new Point(0, 0);

        double dx = x2.x - x1.x;
        double dy = x2.y - x1.y;
        double mag = Math.sqrt(dx * dx + dy * dy);
        dx /= mag;
        dy /= mag;
        double lam = (dx * (p.x - x1.x)) + (dy * (p.y - x1.y));
        proj.x = (dx * lam) + x1.x;
        proj.y = (dy * lam) + x1.y;
        return proj;
/*
        double k = ((x2.y-x1.y) * (p.x-x1.x) - (x2.x - x1.x) * (p.y - x1.y)) / (Math.pow(x2.y - x1.y, 2) + Math.pow(x2.x - x1.x, 2));
        proj.x = p.x - k * (x2.y - x1.y);
        proj.y = p.y + k * (x2.x - x1.x);

        return proj;*/
    }
}

class Point implements Comparable<Point> {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        //Returns + if argument is >, 0 if equal, - if arg < o
        double xs = this.x - o.x;
        double ys = this.y - o.y;
        if (xs == 0 && ys == 0) return 0;
        if (xs != 0) {
            if (xs > 0) return 1;
            else return -1;
        } else {
            if (ys > 0) return 1;
            else return -1;
        }
    }

}

class Edge {
    Point a, b;

    public Edge(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
}

class GrahamScan {

    /**
     * An enum denoting a directional-turn between 3 points (vectors).
     */
    public static enum Turn {
        CLOCKWISE, COUNTER_CLOCKWISE, COLLINEAR
    }

    /**
     * Returns true iff all points in <code>points</code> are collinear.
     *
     * @param points the list of points.
     * @return true iff all points in <code>points</code> are collinear.
     */
    public boolean areAllCollinear(List<Point> points) {

        if (points.size() < 2) {
            return true;
        }

        final Point a = points.get(0);
        final Point b = points.get(1);

        for (int i = 2; i < points.size(); i++) {

            Point c = points.get(i);

            if (getTurn(a, b, c) != Turn.COLLINEAR) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the convex hull of the points created from <code>xs</code>
     * and <code>ys</code>. Note that the first and last point in the returned
     * <code>List&lt;java.awt.Point&gt;</code> are the same point.
     *
     * @param xs the x coordinates.
     * @param ys the y coordinates.
     * @return the convex hull of the points created from <code>xs</code>
     * and <code>ys</code>.
     * @throws IllegalArgumentException if <code>xs</code> and <code>ys</code>
     *                                  don't have the same size, if all points
     *                                  are collinear or if there are less than
     *                                  3 unique points present.
     */
    public List<Point> getConvexHull(int[] xs, int[] ys) throws IllegalArgumentException {

        if (xs.length != ys.length) {
            throw new IllegalArgumentException("xs and ys don't have the same size");
        }

        List<Point> points = new ArrayList<Point>();

        for (int i = 0; i < xs.length; i++) {
            points.add(new Point(xs[i], ys[i]));
        }

        return getConvexHull(points);
    }

    /**
     * Returns the convex hull of the points created from the list
     * <code>points</code>. Note that the first and last point in the
     * returned <code>List&lt;java.awt.Point&gt;</code> are the same
     * point.
     *
     * @param points the list of points.
     * @return the convex hull of the points created from the list
     * <code>points</code>.
     * @throws IllegalArgumentException if all points are collinear or if there
     *                                  are less than 3 unique points present.
     */
    public List<Point> getConvexHull(List<Point> points) throws IllegalArgumentException {

        List<Point> sorted = new ArrayList<Point>(getSortedPointSet(points));

        if (sorted.size() < 3) {
            throw new IllegalArgumentException("can only create a convex hull of 3 or more unique points");
        }

        if (areAllCollinear(sorted)) {
            return null;
        }

        Stack<Point> stack = new Stack<Point>();
        stack.push(sorted.get(0));
        stack.push(sorted.get(1));

        for (int i = 2; i < sorted.size(); i++) {

            Point head = sorted.get(i);
            Point middle = stack.pop();
            Point tail = stack.peek();

            Turn turn = getTurn(tail, middle, head);

            switch (turn) {
                case COUNTER_CLOCKWISE:
                    stack.push(middle);
                    stack.push(head);
                    break;
                case CLOCKWISE:
                    i--;
                    break;
                case COLLINEAR:
                    stack.push(head);
                    break;
            }
        }

        // close the hull
        // stack.push(sorted.get(0));

        return new ArrayList<Point>(stack);
    }

    /**
     * Returns the points with the lowest y coordinate. In case more than 1 such
     * point exists, the one with the lowest x coordinate is returned.
     *
     * @param points the list of points to return the lowest point from.
     * @return the points with the lowest y coordinate. In case more than
     * 1 such point exists, the one with the lowest x coordinate
     * is returned.
     */
    public Point getLowestPoint(List<Point> points) {

        Point lowest = points.get(0);

        for (int i = 1; i < points.size(); i++) {

            Point temp = points.get(i);

            if (temp.y < lowest.y || (temp.y == lowest.y && temp.x < lowest.x)) {
                lowest = temp;
            }
        }

        return lowest;
    }

    /**
     * Returns a sorted set of points from the list <code>points</code>. The
     * set of points are sorted in increasing order of the angle they and the
     * lowest point <tt>P</tt> make with the x-axis. If tow (or more) points
     * form the same angle towards <tt>P</tt>, the one closest to <tt>P</tt>
     * comes first.
     *
     * @param points the list of points to sort.
     * @return a sorted set of points from the list <code>points</code>.
     * @see GrahamScan#getLowestPoint(java.util.List)
     */
    public Set<Point> getSortedPointSet(List<Point> points) {

        final Point lowest = getLowestPoint(points);

        TreeSet<Point> set = new TreeSet<Point>(new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {

                if (a == b || a.equals(b)) {
                    return 0;
                }

                // use longs to guard against int-underflow
                double thetaA = Math.atan2((long) a.y - lowest.y, (long) a.x - lowest.x);
                double thetaB = Math.atan2((long) b.y - lowest.y, (long) b.x - lowest.x);

                if (thetaA < thetaB) {
                    return -1;
                } else if (thetaA > thetaB) {
                    return 1;
                } else {
                    // collinear with the 'lowest' point, let the point closest to it come first

                    // use longs to guard against int-over/underflow
                    double distanceA = Math.sqrt((((long) lowest.x - a.x) * ((long) lowest.x - a.x)) +
                            (((long) lowest.y - a.y) * ((long) lowest.y - a.y)));
                    double distanceB = Math.sqrt((((long) lowest.x - b.x) * ((long) lowest.x - b.x)) +
                            (((long) lowest.y - b.y) * ((long) lowest.y - b.y)));

                    if (distanceA < distanceB) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });

        set.addAll(points);

        return set;
    }

    /**
     * Returns the GrahamScan#Turn formed by traversing through the
     * ordered points <code>a</code>, <code>b</code> and <code>c</code>.
     * More specifically, the cross product <tt>C</tt> between the
     * 3 points (vectors) is calculated:
     *
     * <tt>(b.x-a.x * c.y-a.y) - (b.y-a.y * c.x-a.x)</tt>
     * <p>
     * and if <tt>C</tt> is less than 0, the turn is CLOCKWISE, if
     * <tt>C</tt> is more than 0, the turn is COUNTER_CLOCKWISE, else
     * the three points are COLLINEAR.
     *
     * @param a the starting point.
     * @param b the second point.
     * @param c the end point.
     * @return the GrahamScan#Turn formed by traversing through the
     * ordered points <code>a</code>, <code>b</code> and
     * <code>c</code>.
     */
    public Turn getTurn(Point a, Point b, Point c) {

        // use longs to guard against int-over/underflow
        //long crossProduct = (((long) b.x - a.x) * ((long) c.y - a.y)) -
        //      (((long) b.y - a.y) * ((long) c.x - a.x));

        double crossProduct = ((b.x - a.x) * (c.y - a.y)) - ((b.y - a.y) * (c.x - a.x));

        if (crossProduct > 0) {
            return Turn.COUNTER_CLOCKWISE;
        } else if (crossProduct < 0) {
            return Turn.CLOCKWISE;
        } else {
            return Turn.COLLINEAR;
        }
    }
}
