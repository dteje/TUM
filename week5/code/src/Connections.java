import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Connections {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Cinput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase <= t ; t++){

            int numOfPoints = scanner.nextInt();
            int numOfConnections = scanner.nextInt();
            int heights[] = new int[numOfPoints];
            int excesses[] = new int[numOfPoints];
            boolean[][] matrix = new boolean[numOfPoints][numOfPoints];
            List<EdgeC> edges = new ArrayList<>();

            for(int i=0 ; i<numOfPoints ; i++){
                for(int j=0 ; i<matrix[i].length ; j++){
                    matrix[i][j] = false;
                }
                heights[i] = 0;
                excesses[i] = 0;

            }

            for(int m=0 ; m<numOfConnections ; m++){
                int ori = scanner.nextInt();
                int des = scanner.nextInt();
                matrix[ori-1][des-1] = true;
                matrix[des-1][ori-1] = true;
                edges.add(new EdgeC(ori-1, des-1, 1, 0));
                //edges.add(new EdgeC(des-1, ori-1, 1, 0));
            }

            heights[0] = numOfPoints;






        }
    }
}

class EdgeC{
    int ori, des, cap, flow;

    public EdgeC(int ori, int des, int cap, int flow) {
        this.ori = ori;
        this.des = des;
        this.cap = cap;
        this.flow = flow;
    }
}
