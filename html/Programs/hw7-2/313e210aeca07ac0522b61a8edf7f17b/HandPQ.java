import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class HandPQ {

    int capacity;
    List<Hand> list;

    HandPQ (int capacity){
        this.capacity = capacity;
        list = new LinkedList<>();
    }

    public void add(Hand hand) {
        list.add(hand);
        if (list.size() > capacity){
            this.deleteMin();
        }
    }

    public Hand deleteMin() {
        Hand min = Collections.min(list);
        list.remove(min);
        return min;
    }


    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            HandPQ pq = new HandPQ(target);

            while (br.ready()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand = new Hand(cardsArray);
                pq.add(hand);
            }

            Card[] cards = pq.deleteMin().getCards();
            Arrays.sort(cards);
            System.out.println(toString(cards));

        }
    }

    public static String toString(Card[] cards){
        String temp = """";
        String sp = """";
        for (Card c:cards){
            temp += sp + c.getSuit() + ""_"" + c.getFace();
            sp = "","";
        }
        return temp;
    }
}

