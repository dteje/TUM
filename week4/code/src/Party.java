import java.util.Scanner;

public class Party {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Dinput2.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase = 1 ; testcase <= t ; testcase++){

            int numOfTasks = scanner.nextInt();
            scanner.nextLine();

            boolean[][] matrix = new boolean[numOfTasks][numOfTasks];
            int[] weights = new int[numOfTasks];

            for(int i=0 ; i<numOfTasks ; i++){
                for(int j=0 ; j<numOfTasks ; j++){
                    matrix[i][j] = false;
                }
            }

            for(int task = 0 ; task < numOfTasks ; task++){
                weights[task] = scanner.nextInt();
                int numOfDependencies = scanner.nextInt();
                for(int dependency = 0 ; dependency<numOfDependencies ; dependency++){
                    int dep = scanner.nextInt();
                    matrix[task][dep-1] = true;
                }
            }

            for(int task = 1 ; task < numOfTasks ; task++){
                int max = 0;
                for (int i = 0; i < task; i++){
                    if (matrix[i][task] && weights[i] > max){
                        max = weights[i];
                    }
                }
                weights[task] += max;
            }

            System.out.format("Case #%d: %d\n", testcase, weights[numOfTasks-1]);


        }

    }
}
