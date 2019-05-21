import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class D {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Dinput3.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; ++testcase) {

            long totalNumOfPieces = scanner.nextLong();
            long numOfSinglesPieces = scanner.nextLong();

            HashMap<BigInteger, BigInteger> map = new HashMap<>();

            for (int p = 0; p < numOfSinglesPieces; ++p) {
                BigInteger val1 = BigInteger.valueOf(scanner.nextInt());
                BigInteger val2 = BigInteger.valueOf(scanner.nextInt());
                BigInteger quantity = BigInteger.valueOf(scanner.nextInt());

                //BigInteger aux ;
                if (map.containsKey(val1)) map.put(val1,  map.get(val1).add(quantity));
                else map.put(val1, quantity);
                //aux = quantity;
                if (map.containsKey(val2)) map.put(val2, map.get(val2).add(quantity));
                else map.put(val2, quantity);

            }


            long numOfOdds = 0;
            for (BigInteger value : map.values()) {
                if (value.intValue() % 2 != 0) {
                    numOfOdds++;
                }
            }
            String s = "";
            if(numOfOdds == 0) s = "yes yes";
            else if(numOfOdds <= 2) s = "no yes";
            else s = "no no";
            System.out.format("Case #%d: %s\n", testcase, s);
        }
    }
}
