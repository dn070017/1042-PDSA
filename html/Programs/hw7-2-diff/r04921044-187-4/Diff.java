
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader buffer = new BufferedReader(new FileReader(args[0]))){

            String[] params = buffer.readLine().split("","");
            int count = Integer.parseInt(params[0]);
            int target = Integer.parseInt(params[1]);

            ArrayList<Hand> hands = new ArrayList<> ();

            for (int i=0; i<count; ++i) {
                String[] cardsString = buffer.readLine().split("","");
                Card[] cards = new Card[5];

                for (int j=0; j<5; ++j) {
                    String[] suitAndFace = cardsString[j].split(""_"");
                    cards[j] = new Card(suitAndFace[1], suitAndFace[0]);
                }

                hands.add(new Hand(cards));
            }

            Collections.sort(hands);

            Card[] cards = hands.get(hands.size()-target).getCards();
            for (int i=0; i<4; ++i) {
                System.out.print(cards[i].getSuit() + ""_"" + cards[i].getFace() + "","");
            }
            System.out.print(cards[4].getSuit() + ""_"" + cards[4].getFace());

            System.out.println();
        }
    }
}

