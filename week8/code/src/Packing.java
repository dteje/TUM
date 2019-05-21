import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Packing {

    static List<Box> pila;
    static List<Box> boxes;
    static int currentHeight;

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Cinput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            currentHeight = 0;
            int height = scanner.nextInt();
            int numOfBoxes = scanner.nextInt();
            pila = new ArrayList<>();
            boxes = new ArrayList<>();
            for (int boxtype = 0; boxtype < numOfBoxes; boxtype++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                int z = scanner.nextInt();
                boxes.add(new Box(x, y, z));
            }
            Collections.sort(boxes, new Comparator<Box>() {
                @Override
                public int compare(Box o1, Box o2) {
                    return o1.maxdimension - o2.maxdimension;
                }
            });
            pila.add(boxes.get(0));
            boxes.get(0).quantity--;
            currentHeight += boxes.get(0).dimensions.get(0);

            Boolean flag = false;
            for (Box b : boxes) {
                while(b.quantity>0 && fits(b)) {
                    //updateHeight();
                    //System.out.format("Case #%d: no\n", testcase);
                    //flag = true;
                    //break;
                    b.quantity--;
                } //else {
                    //b.quantity--;
                //}

            }

            //if(flag) continue;
            System.out.format("Case #%d: %d\n", testcase, currentHeight);
            if(currentHeight>=height)System.out.format("Case #%d: yes\n", testcase);
        }
    }

    private static boolean fits(Box b) {
        if(b.quantity==0) return false;
        Box top = pila.get(pila.size() - 1);
        if (top == null) {
            System.out.println("asd");
            return true;
        }
        System.out.println("top: "+top.dimensions.toString());
        System.out.println("box: "+ b.dimensions.toString());



        //TODO: not take care of top.z
        //1 2

        if (b.dimensions.get(1) < top.dimensions.get(1) && b.dimensions.get(2) < top.dimensions.get(2)) {
            updateHeight(b, b.dimensions.get(0));
            return true;
        } else if (b.dimensions.get(2) < top.dimensions.get(1) && b.dimensions.get(1) < top.dimensions.get(2)) {
            updateHeight(b, b.dimensions.get(0));
            return true;
        }
        //2 0
        else if (b.dimensions.get(2) < top.dimensions.get(2) && b.dimensions.get(0) < top.dimensions.get(0)) {
            updateHeight(b, b.dimensions.get(1));
            return true;
        } else if (b.dimensions.get(2) < top.dimensions.get(0) && b.dimensions.get(0) < top.dimensions.get(2)) {
            updateHeight(b, b.dimensions.get(1));
            return true;
        }
        //1 0
        else if (b.dimensions.get(1) < top.dimensions.get(1) && b.dimensions.get(0) < top.dimensions.get(0)) {
            updateHeight(b, b.dimensions.get(2));
            return true;
        } else if (b.dimensions.get(1) < top.dimensions.get(0) && b.dimensions.get(0) < top.dimensions.get(1)) {
            updateHeight(b, b.dimensions.get(2));
            return true;
        }


        return false;
    }

    private static void updateHeight(Box b, int h) {
        //System.out.println("aa");
        currentHeight += h;
        pila.add(b);
    }
}


class Box {
    int quantity;
    List<Integer> dimensions = new ArrayList<>();
    int maxdimension;

    public Box(int x, int y, int z) {
        this.quantity = 5;
        dimensions.add(x);
        dimensions.add(y);
        dimensions.add(z);
        Collections.sort(dimensions);
        this.maxdimension = dimensions.get(1) * dimensions.get(2);
    }
}
