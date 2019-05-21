import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NijasReturn{

    static int[] parent;
    static int[] size;
    static int[][] matrix;
    static List<Tupla> k;

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Einput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase <= t ; t++){

            int numOfRooms = scanner.nextInt();
            int numOfPaths = scanner.nextInt();
            int numOfDungs = scanner.nextInt();

            boolean dungeons[] = new boolean[numOfRooms + 1];
            parent = new int[numOfRooms + 1 ];
            size = new int[numOfRooms+1];
            matrix = new int[numOfRooms+1][numOfRooms+1];

            k = new ArrayList<>();

            for(int i=0; i<numOfRooms; i++)
            {
                size[i] = 1;
                parent[i] = i;
            }
            for(int i =0; i<numOfDungs; i++)
            {
                int x = scanner.nextInt();
                dungeons[x] = true;
            }
            for(int i =0; i<numOfPaths; i++)
            {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int z = scanner.nextInt();
                Tupla tupla = new Tupla(x,y,z);
                k.add(tupla);
                matrix[tupla.x][tupla.y] = matrix[tupla.y][tupla.x] = tupla.z;
            }
            int cost = 0;

            k.sort(new Comparator<Tupla>() {
                @Override
                public int compare(Tupla o1, Tupla o2) {
                    return o2.z - o1.z;
                }
            });

            for(int i=0; i<k.size(); i++)
            {
                if(unionFind(k.get(i).x) == unionFind(k.get(i).y)) continue;
                int loc_d= 0;
                if(dungeons[k.get(i).x]) loc_d++;
                if(dungeons[k.get(i).y]) loc_d++;
                if(0 == loc_d) continue;
                unionMerge(k.get(i).x,k.get(i).y);
                cost += matrix[k.get(i).x][k.get(i).y];
                k.remove(i);
            }

            for(int i =0; i<k.size(); i++)
            {
                int fx,fy;
                fx = unionFind(k.get(i).x);
                fy = unionFind(k.get(i).y);
                if(fx == fy || (size[fx] == 1 && !dungeons[fx]) && (size[fy] == 1 && !dungeons[fy]))
                    continue;
                unionMerge(k.get(i).x,k.get(i).y);
                cost += matrix[k.get(i).x][k.get(i).y];
                k.remove(i);
            }
            System.out.format("Case #%d: %d\n", testcase, cost);

        }



    }

    static int unionFind(int x)
    {
        if(x == parent[x]) return x;
        parent[x] = unionFind(parent[x]);
        return parent[x];
    }
    static void unionMerge(int x, int y)
    {
        int fx = unionFind(x);
        int fy = unionFind(y);

        if(size[fx]>size[fy]) unionMerge(y,x);
        else{
            parent[fx] = fy;
            size[fy] += size[fx];
        }
    }

}

class Tupla{
    int x, y, z;
    public Tupla(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}