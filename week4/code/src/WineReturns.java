/*import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class WineReturns {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("Cinput1.in"));
        //Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase <= t ; testcase++){

            int numOfCities = scanner.nextInt();
            int numOfRoads = scanner.nextInt();
            int numOfSMarkets = scanner.nextInt();
            int posOfLea = scanner.nextInt();
            int posOfPeter = scanner.nextInt();

            int[] distLea = new int[numOfCities];
            int[] prevLea = new int[numOfCities];
            int[] distPeter = new int[numOfCities];
            int[] prevPeter = new int[numOfCities];

            int[] supermarkets = new int[numOfCities];
            Node[] nodes = new Node[numOfCities];


            PriorityQueue<QueuePair> queueLea = new PriorityQueue<>();
            PriorityQueue<QueuePair> queuePeter = new PriorityQueue<>();


            //Initial
            for(int i=0 ; i < numOfCities ; i++){

                distLea[i] = 1001;
                distPeter[i] = 1001;
                prevLea[i] = -1;
                prevPeter[i] = -1;
                nodes[i] = new Node(i);
                QueuePair qp = new QueuePair(1001, nodes[i]);
                if(i == posOfLea-1){
                    distLea[i] = 0;
                    prevLea[i] = i;
                    QueuePair qpL = new QueuePair(distLea[i], nodes[i]);
                    queueLea.add(qpL);
                    queuePeter.add(qp);

                }
                if(i == posOfPeter-1){
                    distPeter[i] = 0;
                    prevPeter[i] = i;
                    QueuePair qpP = new QueuePair(distPeter[i], nodes[i]);
                    queuePeter.add(qpP);
                    queueLea.add(qp);
                }

                supermarkets[i] = 0;
            }



            //Read paths
            for(int m=0 ; m < numOfRoads ; m++ ){
                int ori = scanner.nextInt();
                int des = scanner.nextInt();
                int time = scanner.nextInt();
                Edge e1 = new Edge(des-1,ori-1,time);
                Edge e2 = new Edge(ori-1,des-1,time);
                nodes[ori-1].nexts.add(e2);
                nodes[des-1].nexts.add(e1);

            }

            //Read supermarkets
            for(int s=0 ; s < numOfSMarkets ; s++){
                int city = scanner.nextInt();
                int time = scanner.nextInt();
                supermarkets[city-1] = time;
            }

            if(numOfSMarkets == 0|| numOfRoads == 0){
                System.out.format("Case #%d: impossible\n", testcase);
                continue;
            }

            //distLea[posOfLea-1] = 0;
            //queueLea.remove(new QueuePair(1001, new Node(posOfLea-1)));
            //queueLea.add(new QueuePair(0, new Node(posOfLea-1)));
            //Dijkstra Lea
            while(!queueLea.isEmpty()) {
                Node source = queueLea.poll().node;
                //System.out.println("source = "+source.name);

                for(Edge e : source.nexts){
                    int alt = distLea[source.name] + e.time;
                    if(alt <= distLea[nodes[e.des].name]){
                        queueLea.remove(new QueuePair(distLea[nodes[e.des].name], nodes[e.des]));
                        distLea[nodes[e.des].name] = alt;
                        prevLea[nodes[e.des].name] = source.name;
                        QueuePair qp = new QueuePair(alt, nodes[e.des]);
                        queueLea.add(qp);
                    }
                }
            }

            //distPeter[posOfPeter-1] = 0;
            //queuePeter.remove(new QueuePair(1001, new Node(posOfPeter-1)));
            //queuePeter.add(new QueuePair(0, new Node(posOfPeter-1)));
            //Disjkstra Peter
            while(!queuePeter.isEmpty()) {
                Node source = queuePeter.poll().node;
                //System.out.println("source = "+source.name);
                for(Edge e : source.nexts){
                    int alt = distPeter[source.name] + e.time;
                    if(alt <= distPeter[nodes[e.des].name]){
                        queuePeter.remove(new QueuePair(distPeter[nodes[e.des].name], nodes[e.des]));
                        distPeter[nodes[e.des].name] = alt;
                        prevPeter[nodes[e.des].name] = source.name;
                        QueuePair qp = new QueuePair(alt, nodes[e.des]);
                        queuePeter.add(qp);
                    }
                }
            }
/*
         //print dists and pred of peter{
            for(int i=0 ; i<prevLea.length ; i++){
                System.out.print(prevLea[i]+",");
            }
            System.out.println("");
            for(int i=0 ; i<distLea.length ; i++){
                System.out.print(distLea[i]+",");
            }
            System.out.println("");
            for(int i=0 ; i<prevPeter.length ; i++){
                System.out.print(prevPeter[i]+",");
            }
            System.out.println("");
            for(int i=0 ; i<distPeter.length ; i++){
                System.out.print(distPeter[i]+",");
            }
            System.out.println("");



*/


/*
            int minDist = 1001;
            int res = 1001;

            for(int sm = 0 ; sm < numOfSMarkets ; sm++){
                if(supermarkets[sm] > 0){
                    int t1 = distLea[sm];
                    System.out.println(t1);
                    if(t1 == 1001) continue;
                    int dist = 0;
                    int node = sm;

                    while(prevPeter[node] != posOfPeter-1){
                        dist += Math.abs(distPeter[node] - distPeter[prevPeter[node]]);
                        node = prevPeter[node];
                    }

                    if(node != posOfPeter) continue;
                    int t2 = dist;
                    res = t1+t2+supermarkets[sm];
                    if(res < minDist) minDist = res;

                }


            }
            if (minDist == 1001) System.out.format("Case #%d: impossible\n", testcase);
            else System.out.format("Case #%d: %s", testcase, distToPrint(minDist));

            int min = 1001;
            int leaToPeter = distLea[posOfPeter-1];
            int peterToLea = distPeter[posOfLea-1];
            for(int sm = 0 ; sm < numOfSMarkets ; sm++){
                if(supermarkets[sm] > 0){
                    int distToLea = distLea[sm];
                    int distToPeter = distPeter[sm];
                    if(distToLea == 1001 && distToPeter == 1001) continue;
                    int res = Math.min(distToLea+supermarkets[sm], distToPeter+supermarkets[sm]);
                    res += Math.min(leaToPeter, peterToLea);
                    if(res <= min){
                        min = res;
                    }
                }
            }
            if (min == 1001) System.out.format("Case #%d: impossible\n", testcase);
            else System.out.format("Case #%d: %s\n", testcase, distToPrint(min));

        }
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
}/

class Edge{
    int ori;
    int des;
    int time;
    public Edge(int ori, int des, int time){
        this.ori = ori;
        this.des = des;
        this.time = time;
    }
}

class Node{
    int name;
    List<Edge> nexts;
    public Node(int name){
        nexts = new ArrayList<>();
        this.name = name;
    }
}

class QueuePair implements Comparable<QueuePair> {

    public int dist;
    public Node node;

    public QueuePair(int dist, Node node) {
        this.dist = dist;
        this.node = node;
    }

    @Override
    public int compareTo(QueuePair o) {
        return (this.dist - o.dist);
    }
//Source: https://stackoverflow.com/questions/29872664/add-key-and-value-into-an-priority-queue-and-sort-by-key-in-java
}
*/