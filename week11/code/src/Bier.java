import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Math.PI;

public class Bier {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Ainput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {

            int numOfValves = scanner.nextInt();
            int numOfPipes = scanner.nextInt();

            MaxFlow maxFlow = new MaxFlow(numOfValves);
            Double[][] graph = new Double[numOfValves][numOfValves];
            for(int i=0 ; i<graph.length ; i++){
                for(int j=0 ; j<graph[i].length ; j++) graph[i][j] = 0.0;
            }
            for (int p = 0; p < numOfPipes; p++) {
                //Read ini, end, sides and length of side
                int ini = scanner.nextInt();
                int end = scanner.nextInt();
                int sides = scanner.nextInt();
                double length = scanner.nextDouble();
                graph[ini-1][end-1] +=  getArea(sides, length);
                graph[end-1][ini-1] +=  getArea(sides, length);
            }

            double res = maxFlow.fordFulkerson(graph, 0, numOfValves-1);
            if(res > 0.0000000001 ) System.out.format("Case #%d: %.14f\n", testcase,res);
            else System.out.format("Case #%d: impossible\n", testcase);


        }
    }

    private static double getArea(int n, double s) {
        if (n!=0)  return s*s*n/(4*Math.tan(PI/n));
        else return s * s * PI;
        //https://www.mathopenref.com/polygonregulararea.html
    }
}

// Java program for implementation of Ford Fulkerson algorithm https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/

class MaxFlow {
    int V; //Number of vertices in graph

    /* Returns true if there is a path from source 's' to sink
    't' in residual graph. Also fills parent[] to store the
    path */
    boolean bfs(Double rGraph[][], int s, int t, int parent[]) {
        // Create a visited array and mark all vertices as not
        // visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        // Create a queue, enqueue source vertex and mark
        // source vertex as visited
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (rGraph[u][v] > 0)
                    if (!visited[v]) {
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
            }
        }

        // If we reached sink in BFS starting from source, then
        // return true, else false
        return (visited[t] == true);
    }

    // Returns tne maximum flow from s to t in the given graph
    Double fordFulkerson(Double graph[][], int s, int t) {
        int u, v;

        // Create a residual graph and fill the residual graph
        // with given capacities in the original graph as
        // residual capacities in residual graph

        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        Double rGraph[][] = new Double[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store path
        int parent[] = new int[V];

        Double max_flow = 0.; // There is no flow initially

        // Augment the flow while tere is path from source
        // to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            Double path_flow = Double.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

            // Add path flow to overall flow
            max_flow += path_flow;
        }

        // Return the overall flow
        return max_flow;
    }

    public MaxFlow(int V) {
        this.V = V;
    }
}
//class MaxFlow {
//    int V;    //Number of vertices in graph
//
//    /* Returns true if there is a path from source 's' to sink
//      't' in residual graph. Also fills parent[] to store the
//      path */
//    boolean bfs(Double rGraph[][], int s, int t, int parent[]) {
//        // Create a visited array and mark all vertices as not
//        // visited
//        boolean visited[] = new boolean[V];
//        for (int i = 0; i < V; i++)
//            visited[i] = false;
//
//        // Create a queue, enqueue source vertex and mark
//        // source vertex as visited
//        LinkedList<Integer> queue = new LinkedList<Integer>();
//        queue.add(s);
//        visited[s] = true;
//        parent[s] = -1;
//
//        // Standard BFS Loop
//        while (queue.size() != 0) {
//            int u = queue.poll();
//
//            for (int v = 0; v < V; v++) {
//                if (rGraph[u][v] > 0 && !visited[v] ) {
//                    queue.add(v);
//                    parent[v] = u;
//                    visited[v] = true;
//                }
//            }
//        }
//
//        // If we reached sink in BFS starting from source, then
//        // return true, else false
//        return (visited[t] == true);
//    }
//
//    // Returns tne maximum flow from s to t in the given graph
//    Double fordFulkerson(Double graph[][], int s, int t) {
//        int u, v;
//
//        // Create a residual graph and fill the residual graph
//        // with given capacities in the original graph as
//        // residual capacities in residual graph
//
//        // Residual graph where rGraph[i][j] indicates
//        // residual capacity of edge from i to j (if there
//        // is an edge. If rGraph[i][j] is 0, then there is
//        // not)
//        Double rGraph[][] = new Double[V][V];
//
//        for (u = 0; u < V; u++)
//            for (v = 0; v < V; v++)
//                rGraph[u][v] = graph[u][v];
//
//        // This array is filled by BFS and to store path
//        int parent[] = new int[V];
//
//        Double max_flow = 0.0;  // There is no flow initially
//
//        // Augment the flow while tere is path from source
//        // to sink
//        while (bfs(rGraph, s, t, parent)) {
//            // Find minimum residual capacity of the edhes
//            // along the path filled by BFS. Or we can say
//            // find the maximum flow through the path found.
//            Double path_flow = Double.MAX_VALUE;
//            for (v = t; v != s; v = parent[v]) {
//                u = parent[v];
//                path_flow = Math.min(path_flow, rGraph[u][v]);
//            }
//
//            // update residual capacities of the edges and
//            // reverse edges along the path
//            for (v = t; v != s; v = parent[v]) {
//                u = parent[v];
//                rGraph[u][v] -= path_flow;
//                rGraph[v][u] += path_flow;
//            }
//
//            // Add path flow to overall flow
//            max_flow += path_flow;
//        }
//
//        // Return the overall flow
//        return max_flow;
//    }
//
//    public MaxFlow(int V) {
//        this.V = V;
//    }
//}
