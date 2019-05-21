import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DNA {

    static Integer[] frequencies;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Einput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {

            int numOfHumans = scanner.nextInt();
            int numOfMouses = scanner.nextInt();

            String[] humanSequences = new String[numOfHumans];
            String[] mouseSequences = new String[numOfMouses];

            frequencies = new Integer[10]; //AA, CC, TT, GG, AC, AT, AG, CT, CG, TG
            //frequencies2 = new ArrayList<>();


            for (int h = 0; h < numOfHumans; h++) {
                humanSequences[h] = scanner.next();
            }

            for (int m = 0; m < numOfMouses; m++) {
                mouseSequences[m] = scanner.next();
            }

            for(int i=0 ; i<frequencies.length ; i++){
                frequencies[i] = 0;
            }

            for (int h = 0; h < humanSequences.length; h++) {
                char[] s1 = humanSequences[h].toCharArray();
                for (int m = 0; m < mouseSequences.length; m++) {
                    char[] s2 = mouseSequences[m].toCharArray();
                    for (int nuc = 0; nuc < s1.length; nuc++) {
                        char c1 = s1[nuc];
                        char c2 = s2[nuc];
                        updateFreqs(c1, c2);
                    }
                }
            }
            //printFreqs();


            Arrays.sort(frequencies,4, 10, Collections.reverseOrder());
            int best = Integer.MIN_VALUE;

            int[] values = new int[10];
            for (int i = 0; i <= 10; i++) {
                for (int j = 0; j <= 10; j++) {
                    for (int k = 0; k <= 10; k++) {
                        for (int l = 0; l <= 10; l++) {
                            if((i+j+k+l)%2 == 0){
                                values[0] = i;
                                values[1] = j;
                                values[2] = k;
                                values[3] = l;
                                values[4] = 10;
                                values[5] = 10;
                                values[6] = 10 - ((i + j + k + l) / 2);
                                values[7] = -10;
                                values[8] = -10;
                                values[9] = -10;

                                int score = score(frequencies, values);
                                best = Math.max(score, best);

                            }
                        }
                    }
                }
            }
            System.out.format("Case #%d: %d\n", testcase, best);

        }

    }

    private static void updateFreqs(char c1, char c2) {
        switch (c1) {
            case 'A':
                switch (c2) {
                    case 'A':
                        frequencies[0]++;
                        break;
                    case 'C':
                        frequencies[4]++;
                        break;
                    case 'T':
                        frequencies[5]++;
                        break;
                    case 'G':
                        frequencies[6]++;
                        break;

                }
                break;
            case 'C':
                switch (c2) {
                    case 'A':
                        frequencies[4]++;
                        break;
                    case 'C':
                        frequencies[1]++;
                        break;
                    case 'T':
                        frequencies[7]++;
                        break;
                    case 'G':
                        frequencies[8]++;
                        break;

                }
                break;
            case 'T':
                switch (c2) {
                    case 'A':
                        frequencies[5]++;
                        break;
                    case 'C':
                        frequencies[7]++;
                        break;
                    case 'T':
                        frequencies[2]++;
                        break;
                    case 'G':
                        frequencies[9]++;
                        break;

                }
                break;
            case 'G':
                switch (c2) {
                    case 'A':
                        frequencies[6]++;
                        break;
                    case 'C':
                        frequencies[8]++;
                        break;
                    case 'T':
                        frequencies[9]++;
                        break;
                    case 'G':
                        frequencies[3]++;
                        break;

                }
                break;

        }
    }

    private static void printFreqs() {
        //System.out.format("Case #%d: ", testcase);
        for (int i = 0; i < frequencies.length; i++) {
            System.out.print(frequencies[i]);
        }
        System.out.println("");
    }

    private static int score(Integer[] frequencies, int[] values){
        int res = 0;
        for (int i=0; i<10; i++)
        {
            res += (frequencies[i] * values[i]);
        }
        return res;
    }
}

 /* Source: Adapted from https://www.topcoder.com/community/competitive-programming/tutorials/greedy-is-good/
            *   Best = -Infinity
                For S [1] = 1 to 10
                    For S [2] = 1 to 10
                        For S [3] = 1 to 10
                            For S [4] = 1 to 10
                                If (S [1] + S [2] + S [3] + S [4]) mod 2 = 0
                                    S [5] = S[6] = 10
                                    S [7] = 10 - (S [1] + S [2] + S [3] + S[4]) / 2
                                    S [8] = S [9] = S [10] = -10
                                    // in Best we save the greatest average homology score
                                    Best = max (Best , score (F,S))
                                    // obtained so far.
                                Endif
                            Endfor
                       Endfor
                    Endfor
                Endfor
            */

