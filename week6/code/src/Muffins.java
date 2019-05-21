import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Muffins {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Ainput2.in"));
        //Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase<=t ; testcase++){

            int numOfBakers = scanner.nextInt();
            int numOfJudges = scanner.nextInt();

            Graph graph = new Graph(numOfBakers*2+numOfJudges*2);

            int[][] matrix = new int[numOfJudges+1][numOfBakers+1];
            List<List<Integer>> judgePrefs = new ArrayList<List<Integer>>(new ArrayList<>(numOfJudges));

            for(int judge=0 ; judge < numOfJudges ; judge++){
                judgePrefs.add(judge, new ArrayList<>());
                int muffin = scanner.nextInt();

                while(muffin != 0){
                    if(muffin < 0){
                        muffin *= -2;
                    } else {
                        muffin = muffin * 2 - 1;

                    }
                    List<Integer> aux = new ArrayList<>();
                    aux.add(muffin);
                    judgePrefs.add(judge, aux);
                    muffin = scanner.nextInt();
                }
            }

            for (int j = 1; j <= numOfBakers; j++){
                graph.addEdge(0,j*2-1,1);
                graph.addEdge(0,j*2,1);
            }

            for (int j = 0; j < judgePrefs.size(); j++){

                graph.addEdge(numOfBakers*2+j+1,numOfBakers*2+numOfJudges+1,1);

                for (int k = 0; k < judgePrefs.get(j).size(); k++){
                    graph.addEdge(judgePrefs.get(j).get(k), numOfBakers*2+j+1, 1);
                }
            }
            int realJudges = 0;
            for (int j = 0; j < judgePrefs.size(); j++){
                if (judgePrefs.get(j).size() > 0){
                    realJudges++;
                }
            }
            //System.out.println("bakers = "+ numOfBakers);
          //  if(numOfBakers < realJudges){
            //    System.out.format("Case #%d: no\n", testcase);
              //  continue;
            //}

            System.out.println("numOfBak " + numOfBakers +", numofjudges=" + numOfJudges);

            int maxFlow = graph.getMaxFlow(0,numOfBakers*2+numOfJudges+1);
            if (maxFlow >= realJudges) System.out.format("Case #%d: yes\n");
            else System.out.format("Case #%d: no\n");
        }
    }
}

class Vertex {
    int h;      // Height of node
    int e_flow; // Excess Flow

    public Vertex(int h, int e_flow) {
        this.h = h;
        this.e_flow = e_flow;
    }
}

class Edge {
    int u, v; // Edge is from u to v
    int flow; // Current flow
    int capacity;

    public Edge(int flow, int capacity, int u, int v) {
        this.flow = flow;
        this.capacity = capacity;
        this.u = u;
        this.v = v;
    }

}

class Graph {

    int V; //Number of vertices
    List<Edge> edge;   // Array of edges
    List<Vertex> ver;  // Array of vertices

    public Graph(int V) {
        edge = new ArrayList<>();
        ver = new ArrayList<>();
        this.V = V;
        for (int i = 0; i < V; i++)
            ver.add(new Vertex(0, 0));
    }

    // function to add an edge to graph
    public void addEdge(int u, int v, int w) {
        edge.add(new Edge(0, w, u, v));
    }

    public void preflow(int s) {
        ver.get(s).h = ver.size();

        for (int i = 0; i < edge.size(); i++) {
            // If current edge goes from source
            if (edge.get(i).u == s) {
                // Flow is equal to capacity
                edge.get(i).flow = edge.get(i).capacity;
                // Initialize excess flow for adjacent v
                ver.get(edge.get(i).v).e_flow += edge.get(i).flow;
                // Add an edge from v to s in residual graph with capacity equal to 0
                edge.add(new Edge(-edge.get(i).flow, 0, edge.get(i).v, s));
            }
        }
    }

    public int overFlowVertex() {
        for (int i = 1; i < ver.size() - 1; i++)
            if (ver.get(i).e_flow > 0)
                return i;
        // -1 if no overflowing Vertex
        return -1;
    }

    public void updateReverseEdgeFlow(int i, int flow) {
        int u = edge.get(i).v, v = edge.get(i).u;
        for (int j = 0; j < edge.size(); j++) {
            if (edge.get(j).v == v && edge.get(j).u == u) {
                edge.get(j).flow -= flow;
                return;
            }
        }

        // adding reverse Edge in residual graph
        Edge e = new Edge(0, flow, u, v);
        edge.add(e);
    }

    public boolean push(int u) {
        // Traverse through all edges to find an adjacent (of u) to which flow can be pushed
        for (int i = 0; i < edge.size(); i++) {
            // Checks u of current edge is same as given overflowing vertex
            if (edge.get(i).u == u) {
                // if flow is equal to capacity then no push is possible
                if (edge.get(i).flow == edge.get(i).capacity)
                    continue;
                // Push is only possible if height of adjacent is smaller than height of overflowing vertex
                if (ver.get(u).h > ver.get(edge.get(i).v).h) {
                    // Flow to be pushed is equal to minimum of remaining flow on edge and excess flow.
                    int flow = Math.min(edge.get(i).capacity - edge.get(i).flow, ver.get(u).e_flow);
                    // Reduce excess flow for overflowing vertex
                    ver.get(u).e_flow -= flow;
                    // Increase excess flow for adjacent
                    ver.get(edge.get(i).v).e_flow += flow;
                    // Add residual flow (With capacity 0 and negative flow)
                    edge.get(i).flow += flow;
                    updateReverseEdgeFlow(i, flow);
                    return true;
                }
            }
        }

        return false;
    }

    public void relabel(int u) {
        // Initialize minimum height of an adjacent
        int mh = Integer.MAX_VALUE;

        // Find the adjacent with minimum height
        for (int i = 0; i < edge.size(); i++) {
            if (edge.get(i).u == u) {
                // if flow is equal to capacity then no
                // relabeling
                if (edge.get(i).flow == edge.get(i).capacity)
                    continue;

                // Update minimum height
                if (ver.get(edge.get(i).v).h < mh) {
                    mh = ver.get(edge.get(i).v).h;
                    // updating height of u
                    ver.get(u).h = mh + 1;
                }
            }
        }
    }


    // returns maximum flow from s to t
    public int getMaxFlow(int s, int t) {
        preflow(s);

        // loop untill none of the Vertex is in overflow
        while (overFlowVertex() != -1) {
            int u = overFlowVertex();
            if (!push(u))
                relabel(u);
        }

        // ver.back() returns last Vertex, whose
        // e_flow will be final maximum flow
        return ver.get(ver.size() - 1).e_flow;

    }
}