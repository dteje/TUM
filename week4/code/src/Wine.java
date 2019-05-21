import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
public class Wine {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Cinput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase<=t ; testcase++){

            int numOfCities = scanner.nextInt();
            int numOfRoads = scanner.nextInt();
            int numOfSMarkets = scanner.nextInt();
            int posOfLea = scanner.nextInt();
            int posOfPeter = scanner.nextInt();

            ArrayList<ArrayList<Edge>> graphread = new ArrayList<>(numOfRoads);
            ArrayList<SMarket> supermarkets = new ArrayList<>();
            Edge[][] graph = new Edge[numOfCities][numOfCities];

            for (int i=1 ; i<=numOfRoads ; i++)
            {
                int ori = scanner.nextInt();
                int des = scanner.nextInt();
                int wei = scanner.nextInt();
                graph[ori-1][des-1] = new Edge(wei, des);
                graph[des-1][ori-1] = new Edge(wei, ori);

            }

            graph = (Edge[][]) graphread.toArray();

            for (int i=1 ; i<=numOfSMarkets ; i++){
                int city = scanner.nextInt();
                int weig = scanner.nextInt();
                supermarkets.add(new SMarket(city,weig));
            }

            if(supermarkets.isEmpty()) {System.out.format("Case #%d: impossible\n", testcase);}
            else {
                int[] predOfLea = new int[numOfCities + 1];
                int[] predOfPeter = new int[numOfCities + 1];
                int[] distOfLea = new int[numOfCities + 1];
                int[] distOfPeter = new int[numOfCities + 1];


                dijkstra(new DijParams(posOfLea, predOfLea, distOfLea, graph));
                dijkstra(new DijParams(posOfPeter, predOfPeter, distOfPeter, graph));

                int minDist = Integer.MAX_VALUE;
                int res = Integer.MAX_VALUE;

                for(SMarket sm : supermarkets){
                    int t1 = distOfLea[sm.city];
                    if(t1 == Integer.MAX_VALUE) continue;
                    int dist = 0;
                    int node = sm.city;

                    while (predOfPeter[node] != 0)
                    {
                        dist += Math.abs(distOfPeter[node] - distOfPeter[predOfPeter[node]]);
                        node = predOfPeter[node];
                    }

                    if (node != posOfPeter) continue;

                    int t2 = dist;
                    res = t1 + t2 + sm.weight;

                    if(res < minDist) minDist = res;
                }
                if (minDist == Integer.MAX_VALUE) System.out.format("Case #%d: impossible\n", testcase);
                else System.out.format("Case #%d: %s", testcase, distToPrint(minDist));

            }

        }

    }

    private static DijParams dijkstra(DijParams params) {
        int source = params.source;
        int[] dist = params.dist;
        int[] pred = params.pred;
        Edge[][] graph = params.graph;

        boolean[] visited = new boolean[graph.length];

        for (int i=1; i<=graph.length; ++i)
        {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
        }

        visited[source] = true;
        dist[source] = 0;
        pred[source] = 0;

        //auto cmp = [](Pq_Edge left, Pq_Edge right) { return (left.e.weight > right.e.weight); }; // TODO

        PriorityQueue<QueueEdge> queue = new PriorityQueue<>();

        for (Edge e : graph[source])
        {
            queue.add(new QueueEdge(e, source));
        }

        while(!queue.isEmpty()) {

            Edge e = queue.poll().edge;

            if(!visited[e.dest]){
                visited[e.dest] =true;
                dist[e.dest] = e.weight;
                for(Edge ed : graph[e.dest]){
                    Edge newEd = new Edge(dist[e.dest] + ed.weight, ed.dest);
                    QueueEdge qe = new QueueEdge(newEd,e.dest);
                    queue.add(qe);
                }
            }

        }


        return new DijParams(source, pred, dist, graph);
    }


    private static String distToPrint(int time){

        int h = time / 60;
        int m = time % 60;
        String res = h + ":";
        if (m < 10){
            res = res + "0" + m;
        } else{
            res += m;
        }
        return res;
    }
}

class Edge{
    public int weight;
    public int dest;
    public Edge(int w, int d){weight = w ; dest = d; }
}

class SMarket{
    public int city;
    public int weight;
    public SMarket(int c, int w){city=c; weight=w;};
}

class QueueEdge{
    public QueueEdge(Edge edge, int prev) { this.edge = edge; this.prev = prev; }
    public Edge edge;
    public int prev;
}

class DijParams{
    int source;
    //int pos;
    int[] pred;
    int[] dist;
    Edge[][] graph;

    public DijParams(int source, int[] pred, int[]dist, Edge[][] graph) {
        this.source = source;
        this.pred = pred;
        this.dist = dist;
        this.graph = graph;
    }
}

class Node{
    public List<Edge> nexts;
    public int name;

    public Node(){
        nexts = new ArrayList<>();
    }
}
*/