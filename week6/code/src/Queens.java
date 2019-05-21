import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Queens {
    static int[][] board2;

    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Dinput1.in"));

        //scanner.useDelimiter("");
        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {

            int size = scanner.nextInt();
            scanner.nextLine();
            //int[][] board = new int[size][size];
            board2 = new int[size][size];

            //int k=0;
            for (int j = 0; j < size; j++) {
                //
                String c = scanner.nextLine();
                for (int k = 0; k < size; k++) {
                    if (c.charAt(k) == '.')
                        board2[j][k] = 0;
                    else
                        board2[j][k] = 2; //use a 2 to denote a fixed queen
                }

                //scanner.nextLine();
            }
            if (scanner.hasNext()) scanner.nextLine();


            boolean legal = true;
            //first check if the given conf is even a legal conf
            for (int i = 0; i < board2.length && legal; i++) {
                for (int j = 0; j < board2.length && legal; j++) {
                    if (board2[i][j] != 0) {
                        board2[i][j] = 0;
                        if (!isSafe(i, j)) {
                            legal = false;
                        }
                        board2[i][j] = 2;
                    }
                }
            }
            if (legal && solveNQ(0)) {
                //board = tmp;
                System.out.format("Case #%d:\n", testcase);
                for (int j = 0; j < size; j++) {
                    String s = "";
                    for (int k = 0; k < size; k++) {
                        if (board2[j][k] == 0)
                            s += ".";
                        else
                            s += "x";
                    }
                    System.out.println(s);
                }
            } else {
                System.out.format("Case #%d:\nimpossible\n", testcase);
            }
        }

    }

    static boolean isSafe(int row, int col) {
        for (int i = 0; i < board2.length; i++) {
            if ((i != col && 0 != board2[row][i]) || (i != row && 0 != board2[i][col]))
                return false;
        }
        int i, j;
        for (i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board2[i][j] != 0)
                return false;
        }

        for (i = row + 1, j = col - 1; j >= 0 && i < board2.length; i++, j--) {
            if (board2[i][j] != 0)
                return false;
        }
        return true;
    }

    static boolean solveNQ(int col) {
        if (col >= board2.length)
            return true;
        for (int i = 0; i < board2.length; i++) {
            if (isSafe(i, col)) {
                if (board2[i][col] != 2)
                    board2[i][col] = 1;

                if (solveNQ(col + 1))
                    return true;

                if (board2[i][col] != 2)
                    board2[i][col] = 0;
            }
        }

        return false;
    }
}
