import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Football {
    public static void main(String[] args) throws FileNotFoundException {

        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Dinput2.in"));

        int t = scanner.nextInt();

        for(int testcase = 1 ; testcase <= t ; testcase++){

            //System.out.println("testcase " + testcase);
            int numOfTeams = scanner.nextInt();
            int numOfMatches = scanner.nextInt();

            Integer[] wins = new Integer[numOfTeams];
            List<Tupla> restantes = new ArrayList<>();

            for(int team=0 ; team<numOfTeams ; team++ ){
                wins[team] = scanner.nextInt();
            }

            for(int match=0 ; match<numOfMatches ; match++){
                int t1 = scanner.nextInt();
                int t2 = scanner.nextInt();
                restantes.add(new Tupla(t1-1, t2-1));
            }

            int[] result = new int[numOfTeams];


            for (int k = 0; k < numOfTeams; k++){
                List<Tupla> auxrestantes = new ArrayList<>();
                List<Integer> auxwins = (List<Integer>) Arrays.asList(wins);
                for (int j = 0; j < restantes.size(); j++){
                    if(restantes.get(j).team1 == k || restantes.get(j).team2 == k){ auxwins.set(k, auxwins.get(k)+1); }
                    else {
                        Tupla aux = restantes.get(j);
                        auxrestantes.add(aux);
                    }
                }
                for (int j = 0; j < auxrestantes.size(); j++){
                    if (auxwins.get(auxrestantes.get(j).team1) < auxwins.get(auxrestantes.get(j).team2)) {
                        int aux = auxwins.get(auxrestantes.get(j).team1) + 1;
                        auxwins.set(auxrestantes.get(j).team1, aux);
                    }

                    else {
                        int aux = auxwins.get(auxrestantes.get(j).team2) + 1;
                        auxwins.set(auxrestantes.get(j).team2,aux);
                    }
                }
                for (int j = 0; j < numOfTeams; j++){
                    if (auxwins.get(k) < auxwins.get(j)){
                        result[k] = -1;
                        break;
                    }
                    if (j == numOfTeams-1 && result[k] != -1){
                        result[k] = 1;
                    }
                }

            }

            String toprint ="";
            for (int j = 0; j < result.length; j++){
                if (result[j] == -1)
                    toprint +=  " no";
                else
                    toprint +=  " yes";
            }
            System.out.format("Case #%d:%s\n", testcase, toprint);


        }
    }
}

class Tupla{
    int team1, team2;
    public Tupla(int t1, int t2){
        team1 = t1;
        team2 = t2;
    }
}