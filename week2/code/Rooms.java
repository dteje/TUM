import java.util.*;

public class Rooms {

        public static void main(String[] args){

            //Scanner scanner = new Scanner(new File("C:/Users/danie/Desktop/input.in"));
            Scanner scanner = new Scanner(System.in);

            int t = scanner.nextInt();
            scanner.nextLine();

            for (int testcase = 1; testcase <= t; testcase++) {
                System.out.format("Case #%d:\n", testcase);
                int s = scanner.nextInt(); //number of stations
                int f = scanner.nextInt(); //number of rooms
                List<Integer> rooms = new ArrayList<>();
                for (int i = 1; i <= s; i++) {
                    int ini = scanner.nextInt();
                    int end = scanner.nextInt();
                    if(ini==end) rooms.add(ini);
                    else {
                        for(int j=ini ; j <=end ; j++){
                            rooms.add(j);
                        }
                    }
                }
                Collections.sort(rooms);
                for(int i=1 ; i <= f ; i++){
                    int roomnumber = scanner.nextInt();
                    System.out.format("%d\n", rooms.get(roomnumber-1));
                }
            }


        }
    }
