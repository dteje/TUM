import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Change {
    static List<Integer> coins;

    //static int[] values, first;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Ainput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {

            int numOfCoins = scanner.nextInt();
            int amount = scanner.nextInt();

            coins = new ArrayList<>();
            for (int coin = 0; coin < numOfCoins; coin++) {
                coins.add(scanner.nextInt());
            }


            //initialize num of coins
            Integer[] values = new Integer[amount + 1];
            //boolean[] ready = new boolean[numOfCoins];
            Integer[] first = new Integer[amount + 1];
            for (int i = 0; i < values.length; i++) {
                values[i] = 0;
                first[i] = 0;
            }

            //values[0] = 0;
            for (int i = 1; i <= amount; i++) {
                values[i] = Integer.MAX_VALUE;
                for (int coin : coins) {
                    if (i - coin >= 0 && values[i - coin] + 1 < values[i]) {
                        values[i] = values[i - coin] + 1;
                        first[i] = coin;
                    }
                }
            }

            Map<Integer, Integer> counter = new HashMap<>();

            while (amount > 0) {
                if (counter.containsKey(first[amount]))
                    counter.put(first[amount], counter.get(first[amount]) + 1);
                else counter.put(first[amount], 1);
                amount -= first[amount];
            }


            //printing
            System.out.format("Case #%d: ", testcase);
            for (int i = 0; i < coins.size() ; i++) {
                //System.out.println(coins.get(i));
                if (counter.containsKey(coins.get(i))){
                    int v = counter.get(coins.get(i));
                    System.out.print(v + " ");
                }
                else System.out.print("0 ");
            }
            System.out.println("");
            //System.out.println(counter.get(coins.get(coins.size()-2)));


        }
    }

    /*
    public static int fst(int amount){
        return Change.aux(amount, amount);
    }*//*

    public static int aux(int x, int amount, int index) {
        if (x < 0) return 1000;
        if (x == 0) return 0;

        int best = 1000;
        for (Integer c : coins) {
            best = Math.min(best, aux(x - c, amount, index + 1) + 1);
        }
        values[index] = best;
        return best;
    }*/
}
