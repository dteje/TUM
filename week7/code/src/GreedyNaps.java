import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GreedyNaps {

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Ainput3.in"));

        int t = scanner.nextInt(); //read t
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; testcase++) {
            //Read n
            int numOfNaps = scanner.nextInt();
            List<Nap> naps = new ArrayList<>(numOfNaps);
            for (int nap = 0; nap < numOfNaps; nap++) {
                //Read d
                int day = scanner.nextInt();

                //Read h
                String hour = scanner.next();
                String[] hhmm = hour.split(":");
                int hh = Integer.parseInt(hhmm[0]);
                int mm = Integer.parseInt(hhmm[1]);

                //Read l
                int duration = scanner.nextInt();

                //Create new object nap and store it in the List naps
                naps.add(new Nap(day, hh, mm, duration));

            }

            int maxRooms = 0;

            //Go through the list of naps and for each one compare with all the previously stored.
            for (int i=0 ; i<naps.size() ; i++) { //nap1
                int min = 1; //(or min = 0 but next for loop up to j<=i)
                for (int j = 0 ; j<i ; j++) { //nap2
                    if (compareNaps(naps.get(i), naps.get(j)))
                        min++; //if they overlap, add one room
                }
                maxRooms = Math.max(min, maxRooms); //keep the max. number of rooms

            }
            System.out.format("Case #%d: %d\n", testcase, maxRooms * 500);

        }
    }

    private static boolean compareNaps(Nap nap1, Nap nap2) {
        //OVERLAP = (StartDate1 < EndDate2) and (StartDate2 < EndDate1)
        if (nap1.ini.compareTo(nap2.end) < 0) {
            if (nap2.ini.compareTo(nap1.end) < 0) {
                return true;
            }
        }
        /*if (nap2.ini.compareTo(nap1.end) < 0) {
            if (nap1.ini.compareTo(nap2.end) < 0) {
                return true;
            }
        }*/
        return false;
    }


}

class Nap {
    Date ini, end;

    public Nap(int day, int h, int m, int duration) {
        this.ini = new Date(2019, 02, day, h, m,00);
        long aux = ini.getTime() + duration*60000;
        end = new Date(aux); //end day = initial day + duration (in ms)
    }
}
