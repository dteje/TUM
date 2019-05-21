import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customs {
    static int[][] matrix;
    static List<Integer> a, b;

    public static void main(String[] args) throws FileNotFoundException {


        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Cinput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();


        for (int testcase = 1; testcase <= t; testcase++) {
            int numOfCities = scanner.nextInt();
            matrix = new int[numOfCities][numOfCities];
            a = new ArrayList<>();
            b = new ArrayList<>();

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }

            for (int city = 0; city < numOfCities; city++) {
                int numOfRoads = scanner.nextInt();
                for (int connection = 0; connection < numOfRoads; connection++) {
                    int to = scanner.nextInt();
                    matrix[city][to - 1] = 1;
                }
            }

            for (int city = 0; city < numOfCities; city++) {
                if (putInA(city)) a.add(city);
                else b.add(city);
            }

            System.out.format("Case #%d:\n", testcase);
            for (int i = 0; i < a.size(); i++) {
                if (i == a.size() - 1)
                    System.out.println(a.get(i) + 1);
                else
                    System.out.print(a.get(i) + 1 + " ");

            }
        }
    }


    static boolean putInA(int city) {
        //returns true if has to be added to A
        int counterA = 0, counterB = 0;
        for (int i = 0; i < matrix[city].length && i < city; i++) {
            if (matrix[city][i] == 1) {
                if (a.contains(i)) counterA++;
                else if (b.contains(i)) counterB++;
            }

        }
        if (counterA > counterB) return false; //put it B
        return true; //put it in A

    }
}
