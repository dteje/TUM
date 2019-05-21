import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Naps {

    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

    public static void main(String[] args) throws FileNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(new File("Ainput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for(int testcase=1 ; testcase<=t ; testcase++){
            int numOfNaps = scanner.nextInt();
            Date[] napdays = new Date[numOfNaps];
            int[] durations = new int[numOfNaps];
            int rooms = 0;
            List<Integer> freerooms = new ArrayList<>();


            for(int nap=0 ; nap<numOfNaps ; nap++) {
                int day = scanner.nextInt();
                String hour = scanner.next();
                String[] hhmm = hour.split(":");
                Date ldt = new Date(2018, 02, day, Integer.parseInt(hhmm[0]), Integer.parseInt(hhmm[1]));
                napdays[nap] = ldt;
                int duration = scanner.nextInt();
                durations[nap] = duration;
            }

            for(int nap1=0 ; nap1<numOfNaps ; nap1++){
                for(int nap2=0 ; nap2<numOfNaps ; nap2++){
                    if(nap1 == nap2) continue;

                    Date ini1 = napdays[nap1];
                    Date end1 = new Date(ini1.getTime() + (durations[nap1] * ONE_MINUTE_IN_MILLIS));
                    Date ini2 = napdays[nap2];
                    Date end2 = new Date(ini2.getTime() + (durations[nap2] * ONE_MINUTE_IN_MILLIS));
                    //OVERLAP = (StartDate1 < EndDate2) and (StartDate2 < EndDate1)
                    if((ini1.compareTo(end2) <0 )&&(ini2.compareTo(end1) <0)){

                    }

                }
            }

        }


    }
}
