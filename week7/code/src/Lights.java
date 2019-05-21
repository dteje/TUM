import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Lights {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Binput3.in"));
        int t = scanner.nextInt();
        scanner.nextLine();
        for (int testcase = 1; testcase <= t; testcase++) {
            int length = scanner.nextInt();
            int numOfLights = scanner.nextInt();
            int radiusOfLight = scanner.nextInt();
            List<Integer> positions = new ArrayList<>();
            for (int light = 0; light < numOfLights; light++) {
                int pos = scanner.nextInt();
                positions.add(pos);
            }
            if (numOfLights == 0 || radiusOfLight == 0) {
                System.out.format("Case #%d: impossible\n", testcase);
                continue;
            }
            Collections.sort(positions);
            int index = 0, from = 0, counter = 0;
            int to = from + radiusOfLight;
            boolean possible = true;
            while (true) {
                if (to - radiusOfLight >= length) {
                    break;
                }
                int res = -1;
                for (int i = index; i < numOfLights; ++i) {
                    if (positions.get(i) <= to) {
                        res = i;
                    } else {
                        break;
                    }
                }
                index = res;
                if (index == -1) {
                    possible = false;
                    break;
                }
                if (from <= positions.get(index) && positions.get(index) <= to) {
                    ++counter;
                    from = positions.get(index);
                    to = from + 2 * radiusOfLight;
                    ++index;
                } else {
                    break;
                }
            }
            if (possible && (to - radiusOfLight >= length)) System.out.format("Case #%d: %d\n", testcase, counter);
            else System.out.format("Case #%d: impossible\n", testcase);
        }
    }
}