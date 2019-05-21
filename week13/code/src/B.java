import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class B {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Scanner scanner = new Scanner(new File("Binput1.in"));

        int t = scanner.nextInt();
        scanner.nextLine();

        for (int testcase = 1; testcase <= t; ++testcase) {

            int numOfCards = scanner.nextInt();
            int maxOfPiles = scanner.nextInt();

            Stack<Card> cards = new Stack<>();
            List<Card> pilesTops = new ArrayList<>();

            for (int i = 0; i < numOfCards; ++i) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                cards.push(new Card(Math.max(x, y), Math.min(x, y)));
            }



            Collections.sort(cards);
            pilesTops.add(cards.pop());

            boolean flag = false; //true when num of piles > maxOfPiles

            while(!flag && !cards.isEmpty()){

                Card top = cards.pop();
                boolean placed = false; //true when top is placed on a bottom card (= top of a pile)
                int i=0;
                while(!placed && i<pilesTops.size()){
                    Card bottom = pilesTops.get(i);
                    if(bottom.canFit(top)){
                        pilesTops.set(i, top);
                        placed = true;
                    } else if(bottom.canFit(top.rotate())){
                        pilesTops.set(i,top);
                        placed = true;
                    }
                    i++;
                }

                if(!placed){
                    //add to a new pile
                    if(pilesTops.size() == maxOfPiles){
                        flag = true;
                        System.out.format("Case #%d: impossible\n", testcase);
                    } else{
                        pilesTops.add(top);
                    }
                }
            }
            if(!flag) System.out.format("Case #%d: possible\n", testcase);

        }
    }
}

class Card implements Comparable<Card> {

    int x, y;

    public Card(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Card o) {
        if (this.x == o.x && this.y == o.y) return 0;

        if (this.x > o.x) return +1;
        if (this.x == o.x && this.y >= o.y) return +1;

        return -1;
    }

    public boolean canFit(Card c){
        if(c.compareTo(this) == 0) return true;
        if(this.x >= c.x && this.y >= c.y) return true;
        return false;
    }

    public Card rotate(){
        return new Card(this.y, this.x);
    }

}