import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Fractal {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Einput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; ++testcase) {
            int numOfProductions = scanner.nextInt();
            int numOfLoops = scanner.nextInt();
            int angle = scanner.nextInt();
            String original = scanner.next();
            Map<String, String> myMap = new HashMap<>();
            for (int production = 0; production < numOfProductions; ++production) {
                String read = scanner.next();

                String[] prod = read.split("=>");
                myMap.put(prod[0], prod[1]);
            }

            String string = getTranslation(numOfLoops, original, myMap);

            EPoint point = new EPoint(0, 0);
            //EVector direction = new EVector(1,0);


            int direction = 0;

            System.out.format("Case #%d:\n%f %f\n", testcase, point.x, point.y);
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                if (c == '+') {
                    //add 30 left
                    direction += angle;
                    continue;
                }
                if (c == '-') {
                    direction -= angle;
                    continue;
                }
                point = walk(point, direction);
                //System.out.println("in");
                System.out.format("%f %f\n", point.x, point.y);
            }



        }
    }

    static String getTranslation(int n, String original, Map<String, String> myMap) {
        String modified = original;
        String orig = modified;

        for (int i = 0; i < n; i++) {
            int c = 0, index = 0;
            while (c < orig.length()) {
                String key = orig.substring(c, c + 1);
                if (myMap.containsKey(key)) {
                    String ini = modified.substring(0, index + c);
                    String replacement = myMap.get(key);
                    String end = modified.substring(c + index + 1);
                    modified = ini + myMap.get(key) + end;
                    index += replacement.length() - 1;

                }
                c++;
            }
            orig = modified;

        }
        return orig;
    }

    static EPoint walk(EPoint current, int direction){
        //System.out.format("P(%.2f %.2f) %d\n", current.x, current.y, direction);
        //direction = direction % 360;
        //System.out.format("cos = %.2f ; sin = %.2f\n", Math.cos(direction%360),Math.sin(direction%360));
        //System.out.format("%.2f %.2f \n", Math.cos(direction), Math.sin(direction));/

        return new EPoint(current.x + Math.cos(Math.toRadians(direction)),current.y + Math.sin(Math.toRadians(direction)));
    }

}

class EPoint {
    double x, y;

    public EPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class EVector {
    double x, y;
    public EVector(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
