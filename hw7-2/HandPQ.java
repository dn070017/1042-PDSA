import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split(",");
            //System.out.println(header[1]);
            int count = Integer.parseInt(header[0]);
            int top = Integer.parseInt(header[1]);
            MinPQ<Hand> pq = new MinPQ<Hand>();

            Hand[] hands = new Hand[count];
            int idx = 0;


            for(String in = br.readLine(); in != null; in = br.readLine()) {

                String[] cardStr = in.split(",");
                Card[] cardsArray = new Card[5];

                for(int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split("_");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                pq.insert(new Hand(cardsArray));

                hands[idx++] = new Hand(cardsArray);

                if(pq.size() > top) {
                    pq.delMin();
                }
            }

            /*System.out.printf("%d\t%d\n", hands.length, pq.size());
            for(Hand hand: hands){
                Card[] cards = hand.getCards();
                for(Card card: cards){
                    System.out.printf("%s_%s\t", card.getSuit(), card.getFace());
                }
                System.out.println();
            }
            for(int i = 0; i < 52; i++){
                System.out.println(hands[i].compareTo(hands[52]));
            }*/


            /*Arrays.sort(hands, Collections.reverseOrder());
            int t = 1;
            for(Hand hand: hands) {
                Card[] cards = hand.getCards();
                Arrays.sort(cards);
                System.out.printf("%d\t%d\t", t++, hand.getHand());
                for(Card card: cards){
                    System.out.printf("%s_%s\t", card.getSuit(), card.getFace());
                }
                System.out.println();
            }*/


            Hand hand = pq.delMin();
            Card[] cards = hand.getCards();
            Arrays.sort(cards);
            int ii = 0;
            for(Card card: cards){
                if(ii == 0) { System.out.printf("%s_%s", card.getSuit(), card.getFace()); }
                else { System.out.printf(",%s_%s", card.getSuit(), card.getFace()); }
                ii++;
            }
            System.out.println();
        }
    }
}
