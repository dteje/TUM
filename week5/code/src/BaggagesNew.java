import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaggagesNew {

    public static void main(String[] args) throws FileNotFoundException {

        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Ainput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase <= t ; testcase++){

            int numOfNodes = scanner.nextInt();
            int numOfConnections = scanner.nextInt();

            Graph graph = new Graph(numOfNodes);

            for(int m=0 ; m<numOfConnections ; m++){
                int ori = scanner.nextInt();
                int des = scanner.nextInt();
                int wei = scanner.nextInt();
                graph.addEdge(ori-1, des-1, wei);
                graph.addEdge(des-1, ori-1, wei);

            }

            int res = graph.getMaxFlow(0, numOfNodes-1);

            if(res==0) System.out.format("Case #%d: impossible\n", testcase);
            else System.out.format("Case #%d: %d\n", testcase, res);

        }
    }
}

/* Vertex, Edge and Graph classes have been adapted from  https://www.geeksforgeeks.org/push-relabel-algorithm-set-2-implementation/
 */


/*
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


*/