import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class C {

    static int[][] arr;
    static int[] votes;
    static int[] costs;
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Cinput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();


        for (int testcase = 1; testcase <= t; ++testcase) {
            int numOfDistricts = scanner.nextInt();
            List<District> districts = new ArrayList<>();
            int totalPeople = 0, totalCost =0;
           // System.out.println("Districts = "+ numOfDistricts);
            arr = new int[numOfDistricts+1][numOfDistricts+1];
            votes = new int[numOfDistricts+1];
            costs = new int[numOfDistricts+1];
            for(int i = 0 ; i < arr.length ; i++){
                for(int j=0 ; j < arr.length ; j++){
                    arr[i][j] = -1;
                }

            }
            for (int dis = 1; dis <= numOfDistricts; dis++) {
                int p = scanner.nextInt(); int c = scanner.nextInt();
                totalCost += c;
                totalPeople += p;
                costs[dis] =  c;
                votes[dis] = p;
                districts.add(new District(p, c));
            }
            int neededPeople = (totalPeople + 1) / 2;


            System.out.format("Case #%d: %d %d\n", testcase, knapSack(neededPeople, numOfDistricts+1), 1);
        }
    }


    static int knapSack(int W, int n)
    {
        // Base Case 
        if (n == 0 || W == 0)
            return 0;

        // If weight of the nth item is more than Knapsack capacity W, then 
        // this item cannot be included in the optimal solution 
        if (costs[n-1] > W)
            return knapSack(W, n-1);

            // Return the maximum of two cases:  
            // (1) nth item included  
            // (2) not included 
        else return Math.max( votes[n-1] + knapSack(W-costs[n-1], n-1),
                knapSack(W, n-1)
        );
    }
}/*

    int solve(int idx, int val){
        if(idx == n){
            //this is the base case of the recursion, i.e when
            //the votes is >= X then only we consider the solution
            //else we reject the solution and pass Infinity
            if(val >= X) return 0;
            else return INF;
        }
        //this is the step where we return the solution if we have calculated it previously
        //when arr[idx][val] == -1, that means that the solution has not been calculated before
        //and we need to calculate it now
        if(arr[idx][val] != -1) return arr[idx][val];

        //this is the step where we do not pick the current element in the knapsack
        int v1 = solve(idx+1, val);

        //this is the step where we add the current element in the knapsack
        int v2 = solve(idx+1, val + votes[idx]) + costs[idx];

        //here we are taking the minimum of the above two choices that we made and trying
        //to find the better one, i.e the one which is the minimum
        int ans = Math.min(v1, v2);

        //here we are setting the answer, so that if we find this state again, then we do not calculate
        //it again rather use this solution that we calculated
        arr[idx][val] = ans;

        return arr[idx][val];
    }
*/
class District implements Comparable<District> {

    double ratio;
    int people;
    int cost;

    public District(int people, int cost) {
        this.people = people;
        this.cost = cost;
        this.ratio = 1.0 * people / cost;
    }

    @Override
    public int compareTo(District o) {
        if (this.ratio > o.ratio) return +1;
        if (this.ratio < o.ratio) return -1;
        return this.cost - o.cost;
    }

    public String toString() {
        return "District: " + people + " votes, " + cost + "€";
    }
}

/**/
