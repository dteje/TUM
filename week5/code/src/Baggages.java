import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
public class Baggages {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Ainput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {

            int numOfCities = scanner.nextInt();
            int numOfConnec = scanner.nextInt();
            int source = 0;
            int target = numOfCities-1;

            Graph graph = new Graph(numOfCities);

            for (int m = 0; m < numOfConnec; m++) {
                int ori = scanner.nextInt();
                int des = scanner.nextInt();
                int wei = scanner.nextInt();
                graph.edges.add(new Edge(ori-1,des-1,wei));
                graph.edges.add(new Edge(des-1,ori-1,wei));

            }

            // PREFLOW

            graph.heights[source] = numOfCities;
            for (int i=0 ; i < graph.edges.size() ; i++){

               // graph.edges.get(i).currentFlow = 0;

                if(graph.edges.get(i).ori == source ){
                    //i.e. des is adjacent to source
                    graph.edges.get(i).currentFlow = graph.edges.get(i).capacity;
                    graph.excess[graph.edges.get(i).des] += graph.edges.get(i).capacity;
                    graph.edges.add(new Edge(graph.edges.get(i).des,graph.edges.get(i).ori,0, -graph.edges.get(i).currentFlow));
                }
            }


            int ret = overFlowNode(graph);

            while (ret != -1)
            {
                if (!push(ret, graph)){
                    relabel(ret, graph);
                }
                ret = overFlowNode(graph);

            }

            int res = graph.excess[target];
            if(res == 0) System.out.format("Case #%d: impossible\n", testcase);
            else System.out.format("Case #%d: %d\n", testcase, res);

        }

    }

    private static void relabel(int start, Graph graph) {
        int max = Integer.MAX_VALUE;

        // Find the adjacent with minimum height
        for (int i = 0; i < graph.edges.size(); i++)
        {
            if (graph.edges.get(i).ori == start)
            {
                if (graph.edges.get(i).currentFlow != graph.edges.get(i).capacity) {
                    if (graph.heights[(graph.edges.get(i).des)] < max) {
                        max = graph.heights[graph.edges.get(i).des];
                        graph.heights[start] = max + 1;
                    }
                }

            }
        }
    }

    static int overFlowNode(Graph graph){

      //  System.out.println("excess.length = " + graph.excess.length);
        for (int i = 1; i < graph.excess.length - 1; i++) {
            //System.out.println("i = " + i);
            if (graph.excess[i] > 0) {
                return i;
            }
        }

        return -1;
    }

    static boolean push(int start, Graph graph){
        for (int i = 0; i < graph.edges.size(); i++)
        {
            if (graph.edges.get(i).ori == start)
            {

                if (graph.edges.get(i).currentFlow >= graph.edges.get(i).capacity)
                    continue;


                if (graph.heights[start] > graph.heights[graph.edges.get(i).des])
                {
                    int min1 = graph.edges.get(i).capacity - graph.edges.get(i).currentFlow;
                    int min2 = graph.excess[start];
                    int flow = Math.min(min1,min2);
                    graph.excess[start] -= flow;
                    graph.excess[graph.edges.get(i).des] += flow;
                    graph.edges.get(i).currentFlow += flow;

                    int ori = graph.edges.get(i).des;
                    int des = graph.edges.get(i).ori;

                    for (int j = 0; j < graph.edges.size(); j++)
                    {
                        if (graph.edges.get(j).des == des && graph.edges.get(j).ori == ori)
                        {
                            graph.edges.get(j).currentFlow -= flow;
                            break;
                        }
                    }

                    //Residual graph
                    Edge e = new Edge(ori, des, 0, flow);
                    graph.edges.add(e);




                    return true;
                }
            }
        }


        return false;
}


    }

class Edge{
    int ori;
    int des;
    int capacity;
    int currentFlow;
    public Edge(int ori, int des, int cap, int flow) {
        this.ori = ori;
        this.des = des;
        this.capacity = cap;
        //currentFlow = 0;
        this.currentFlow = flow;
    }
        public Edge(int ori, int des, int cap) {
            this.ori = ori;
            this.des = des;
            this.capacity = cap;
        }
}


class Node{
    int height;
    int excess;

    public Node(int height, int excess) {
        this.height = height;
        this.excess = excess;
    }
    public Node(){

    }
}

class Graph{
    List<Edge> edges;
    //List<Node> nodes;
    int[] heights;
    int[] excess;

    public Graph(int size){
        edges = new ArrayList<>();
        heights = new int[size];
        excess = new int[size];
    }

    public Graph(List<Edge> edges) {
        this.edges = edges;
    }


}

*/