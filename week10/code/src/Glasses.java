import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//naive approach, lets see
/*
public class Glasses {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Binput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase<=t ; testcase++){
            //System.out.print("Case "+testcase+": ");
            int numOfGlasses = scanner.nextInt();
            int numOfQueries = scanner.nextInt();
            Node[] tree = new Node[numOfGlasses];
            for(int i=0 ; i<tree.length ; i++){
                tree[i] = new Node();
            }
            int c = 0;
            for(int q=0 ; q<numOfQueries ; q++){
                String id = scanner.next();
                if(id.equals("i")){
                    //insert
                    int ini = scanner.nextInt();
                    int end = scanner.nextInt();
                    int amount = scanner.nextInt();
                    for(int i=ini-1 ; i<=end-1 ; i++){
                        tree[i].add(amount);
                    }
                }
                else{
                    //retrieve and add to c
                    c += tree[scanner.nextInt()-1].getValue();

                }
            }
            System.out.format("Case #%d: %d\n", testcase, c);
        }
    }
}

class Node{
    private int currentValue;
    public Node(int initialValue){
        currentValue = initialValue;
    }
    public Node(){
        currentValue=0;
    }
    public void add(int v){
        this.currentValue += v;
    }

    public int getValue(){
        return currentValue;
    }
}*/
