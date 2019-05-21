import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


//Lets upload something at least

public class DStories {
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Dinput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase <= t ; testcase++){

            int numOfChars = scanner.nextInt();
            int numOfDependencies = scanner.nextInt();
            int[] chapsPerChar = new int[numOfChars];
            List<Chapter> chapters = new ArrayList<>();

            for(int chap=0 ; chap < numOfChars ; chap++){
                int numOfChapsCharHas = scanner.nextInt();
                chapsPerChar[chap] = numOfChapsCharHas;
               // chapters.add();
            }

            for(int i=0 ; i < numOfDependencies ; i++){
                int charA = scanner.nextInt();
                int chapA = scanner.nextInt();
                int charB = scanner.nextInt();
                int chapB = scanner.nextInt();
//                chapters.add();

            }
        }

    }
}


class  Dependency{
    int first, second;
}

class Chapter{
    int character;
    boolean done;
    List<Integer> dependencies;
    public Chapter(int c){
        done = false;
        character = c;
        dependencies = new ArrayList<>();
    }
    public Chapter(int c, List<Integer> l){
        done = false;
        character = c;
        dependencies = l;
    }
}

class Character{
    int name;
    int numOfChapters;
    public Character(int name, int n){
        this.name = name;
        this.numOfChapters = n;
    }
}
