import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Holes {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Cinput3.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            int numOfPoints = scanner.nextInt();
            List<Point> points = new ArrayList<>();
            //List<Point> hull = new ArrayList<>();
            Point minPoint = new Point(scanner.nextInt(), scanner.nextInt());
            points.add(minPoint);
            int pos_min = 0;
            for (int p = 1; p < numOfPoints; p++) {
                int px = scanner.nextInt();
                int py = scanner.nextInt();
                Point point = new Point(px, py);
                points.add(point);
                if (px < minPoint.x || (px == minPoint.x && py < minPoint.y)) {
                    pos_min = p;
                    minPoint = point;
                }
            }

            GrahamScan2 gs = new GrahamScan2();
            List<Point> hull = gs.getConvexHull(points);

            if(hull == null){
                System.out.format("Case #%d: 0.0\n", testcase);
                continue;
            }
            double res = getArea(hull);
            System.out.format("Case #%d: %.1f\n", testcase, res * 2);

/*

            //P is first vertex of convex hull
            //hull.push(minPoint);

            //sort remaining
            //List<Point> sortedPoints = new ArrayList<>(getSortedPointSet(points, minPoint));
            //for(Point p : sortedPoints) System.out.println(p.x + ", " +p.y);
            //System.out.println("size = " + sortedPoints.size());


            //Q ← (q1,q2,...,qn) ordered list of points in P where q1 = p and ](Y,p,qi) ≤](Y,p,qj) for 2 ≤ i < j ≤ n with Y = (px,py+1
            //Queue<Point> queue =
            Stack<Point> hull = new Stack<>();

            hull.push(sortedPoints.get(0));
            hull.push(sortedPoints.get(1));
            //hull.push(sortedPoints.get(2));

            //for(Point p: hull) System.out.println(p.x +", "+p.y);


            for (int i = 2; i < sortedPoints.size(); i++) {
                int val = ccw(hull.get(hull.size() - 2), hull.get(hull.size() - 1), sortedPoints.get(i - 1));
//                System.out.println(testcase);

                while (val >= 0) {
                    //System.out.println(hull.size() + ", " + sortedPoints.size());
                    val = ccw(hull.get(hull.size() - 2), hull.get(hull.size() - 1), sortedPoints.get(i - 1));
                    if (val > 0) {
                        hull.pop();
                    }
                    //System.out.println(hull.size() + ", " + sortedPoints.size());

                }

                //System.out.println("in");

                hull.push(sortedPoints.get(i - 1));


            }

            hull.push(sortedPoints.get(0));

            System.out.println("HULL: ");
            for (Point p : hull) System.out.println(p.x + ", " + p.y);
            System.out.format("\n\n\n");


            //for(Point p : points) System.out.println(p.x + ", " +p.y);
        }*/
        }
    }

    private static double getArea(List<Point> hull) { //Shoelace form
        double res = 0.0;
        int j = hull.size() - 1;
        for (int i = 0; i < hull.size(); i++) {
            res += (hull.get(j).x + hull.get(i).x) * (hull.get(j).y - hull.get(i).y);
            j = i;
        }
        return Math.abs(res / 2.0);
    }

    static double ccw(Point a, Point b, Point p) {
        //return (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x);
        return ((p.y - a.y) * (b.x - a.x) - (p.x - a.x) * (b.y - a.y));

    }


}

/*
class Point implements Comparable<Point> {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        //Returns + if argument is >, 0 if equal, - if arg < o
        if (this.x != o.x) return this.x - o.x;
        else return this.y - o.y;
    }
}
*/
class GrahamScan2 {

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
        /*long crossProduct = (((long) b.x - a.x) * ((long) c.y - a.y)) -
                (((long) b.y - a.y) * ((long) c.x - a.x));

        if (crossProduct > 0) {
            return Turn.COUNTER_CLOCKWISE;
        } else if (crossProduct < 0) {
            return Turn.CLOCKWISE;
        } else {
            return Turn.COLLINEAR;
        }*/
        return Turn.COLLINEAR;
        //Remove comments and final return for using
    }
}



