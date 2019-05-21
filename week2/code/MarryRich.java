import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class MarryRich {


    private static int[] parent;
    private static int[] size;
    private static int[] moneys;

    public static void main(String[] args) {

        //Scanner scanner = new Scanner(new File("C:/Users/danie/Desktop/input2.in"));
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase <= t ; testcase++){


            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();

            //Initialize
            parent = new int[a+1];
            size = new int[a+1];
            moneys = new int[a-1];
            for(int j=0 ; j<a+1 ; j++){
                parent[j] = j;
                size[j] = 1;
            }

            scanner.nextLine();


            for(int i=0 ; i < a-1 ; i++){
                moneys[i] = scanner.nextInt();
            }

            for(int j=0 ; j < b ; j++){
                //relations
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                union(x,y);
            }


            //Arrays.sort(moneys);


            boolean[] isMarried = new boolean[a];
            for(int j=0 ; j<isMarried.length ; j++) isMarried[j] = false;

            for(int j=0 ; j<c ; j++){
                //relations
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                union(x,y);
                isMarried[x] = true;
                isMarried[y] = true;
            }

            int root = find(a);
            int maxmoney = -1;

            for (int j = 1 ; j < a; j++){

                if(!areConnected(j, root) && maxmoney < moneys[j-1] && !isMarried[j]){
                    maxmoney = moneys[j-1];
                }
            }

            if(maxmoney==-1) System.out.format("Case %d: Impossible", testcase);
            else System.out.format("Case #%d: %d\n", testcase, maxmoney);
        }
    }



    private static int find(int a){
        int root = a;
        while(root != parent[root]){
            root = parent[root];
        }
        while(a != root){
            int aux = parent[a];
            parent[a] = root;
            a = aux;
        }
        return root;
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a == b) return; //already merged

        int size_a = size[a];
        int size_b = size[b];

        if(size_a < size_b){
            size[b] += size_a;
            parent[a] = b;
        } else {
            size[a] += size_b;
            parent[b] = a;
        }

    }

    public static boolean areConnected(int x, int y) {
        return find(x) == find(y);
    }

}
