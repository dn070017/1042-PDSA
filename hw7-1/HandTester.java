import java.util.Arrays;
import java.io.FileReader;
import java.io.BufferedReader;

public class HandTester {

    public static void main(String[] args) throws Exception{

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            int idx = 0;
            int count = Integer.parseInt(br.readLine());
            Hand[] handArray = new Hand[count];

            for(String in = br.readLine(); in != null; in = br.readLine()) {

                String[] cardStr = in.split(",");
                Card[] cardsArray = new Card[5];

                for(int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split("_");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                handArray[idx++] = new Hand(cardsArray);
            }

            Arrays.sort(handArray);
            Hand hand = handArray[count - 1];
            Card[] cards = hand.getCards();
            Arrays.sort(cards);
            for (Card card : cards) {
                System.out.printf("%s_%s\t", card.getSuit(), card.getFace());
            }
            System.out.println();
        }
    }
}