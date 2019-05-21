import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HikingReturns {


    public static void main (String[] args) throws FileNotFoundException {

        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Ainput3.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase<=t ; testcase++ ){

            int numberOfNodes = scanner.nextInt();
            int numberOfEdges = scanner.nextInt();

            int[] dist = new int[numberOfNodes+1]; //distance from source to vertex i
            int[] prev = new int[numberOfNodes+1]; //previous node
            //ignore 0

            int[][] matrix = new int[numberOfNodes+1][numberOfNodes+1];

            PriorityQueue<QueuePair> queue = new PriorityQueue();
            int[] auxQueue = new int[numberOfNodes+1];


            //Initialize dist, prev, creates list of nodes, creates priority queue
            for (int node=1 ; node<=numberOfNodes ; node++){
                dist[node] = 1001; //inf
                prev[node] = -1; //no prev.
            }

            for(int i=0 ; i<matrix.length ; i++){
                for(int j=0 ; j<matrix.length ; j++){
                    matrix[i][j] = 0;
                }
            }

            //Read edges and add them to the graph
            for (int edge=1 ; edge<=numberOfEdges ; edge++){
                int ori = scanner.nextInt();
                int des = scanner.nextInt();
                int weight = scanner.nextInt();
                //Create edge with weight from ori to des
                matrix[ori][des] = weight;
            }

            dist[1] = 0;
            //auxQueue[1] = 0;

            //Dijkstra
            while(!queue.isEmpty()) {


                QueuePair qp = queue.poll();
                if(qp.left == 1001) break;

                for(int i=0 ; i < matrix.length ; i++){
                    int alt = qp.left + matrix[qp.right][i];
                    if(alt < dist[qp.right][])
                }


                for(Edge e : source.nexts){

                    int alt = dist[source.name] + e.weight;
                    if(alt <= dist[nodes[e.destination].name]){

                        //Node updated = nodes[e.destination];

                        //System.out.println("sise = " + queue.size());
                        queue.remove(new QueuePair(dist[nodes[e.destination].name], nodes[e.destination]));
                       // System.out.println("sise = " + queue.size());

                        dist[nodes[e.destination].name] = alt;
                        prev[nodes[e.destination].name] = source.name;

                        QueuePair qp = new QueuePair(alt, nodes[e.destination]);

                        queue.add(qp);

                    }
                }

            }

            System.out.format("Case #%d: %d\n",testcase,dist[numberOfNodes]);


        }
    }
}

class QueuePair{
    int left;
    int right;

    public QueuePair(int left, int right) {
        this.left = left;
        this.right = right;
    }
}

