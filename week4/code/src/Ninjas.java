import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ninjas {


    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Einput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {

            int numOfRooms = scanner.nextInt();
            int numOfEdges = scanner.nextInt();
            int numOfDungeons = scanner.nextInt();

            List<Integer> dungeons = new ArrayList<Integer>();
            int[][] weightMatrix = new int[numOfRooms][numOfRooms];
            int[] dist = new int[numOfRooms];
            int[] pred = new int[numOfRooms];


            //Ini arrays
            for(int i=0 ; i<numOfRooms ; i++){
                for(int j=0 ; j<numOfRooms ; j++){
                    weightMatrix[i][j] = 0;
                }
                dist[i] = 1000;
                pred[i] = 1000;
            }

            //Reads dungeons, aka final pos
            for (int d=0 ; d<numOfDungeons ; d++){
                dungeons.add(scanner.nextInt());
            }

            //Reads paths
            for(int m=0 ; m<numOfEdges ; m++){
                int ori = scanner.nextInt();
                int des = scanner.nextInt();
                int weight = scanner.nextInt();
                weightMatrix[ori-1][des-1] = weight;
            }

            LinkedList<Integer> queue = new LinkedList<>();

            //Bellmand-Ford
            int src = 0;
            dist[src] = 0;
            queue.push(src);

            while(!queue.isEmpty()){
                int vertex = queue.pop();
                for(int neig=0 ; neig<weightMatrix.length ; neig++){
                    int pathweight = weightMatrix[vertex][neig];
                    if(pathweight > 0){
                        //If its neig, weight > 0
                        int alt = dist[vertex] + pathweight;
                        if(alt < dist[neig]){
                            dist[neig] = alt;
                            pred[neig] = vertex;
                            //if neig not in queue
                            if(!queue.contains(neig)) queue.push(neig);

                        }

                    }
                }
            }

            System.out.format("Case #%d: %d\n", testcase, dist[numOfRooms-1]);


        }

    }
}
