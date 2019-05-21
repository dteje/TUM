import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*
public class Hiking {


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

            //Set<Integer> nodes = new HashSet<Integer>();
            Node[] nodes = new Node[numberOfNodes+1];
            PriorityQueue<QueuePair> queue = new PriorityQueue<>();

            //Initialize dist, prev, creates list of nodes, creates priority queue
            for (int node=1 ; node<=numberOfNodes ; node++){
                dist[node] = 1001; //inf
                prev[node] = -1; //no prev.
                Node n = new Node();
                n.name = node;
                nodes[node] = n; //At list[node] place Node n;
                queue.add(new QueuePair(1001, n));
            }

            //Read edges and add them to the graph
            for (int edge=1 ; edge<=numberOfEdges ; edge++){
                int ori = scanner.nextInt();
                int des = scanner.nextInt();
                int weight = scanner.nextInt();
                //Create edge with weight from ori to des
                Edge e = new Edge();
                e.weight = weight;
                e.origin = ori; //prescindible
                e.destination = des;
                //Add edge to origin
                nodes[ori].nexts.add(e);
            }

            dist[1] = 0;

            //Dijkstra
            while(!queue.isEmpty()) {
                Node source = queue.poll().node;

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
*/
