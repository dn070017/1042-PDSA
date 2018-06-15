import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ maxpq = new MinPQ(target);

            // Player[] playerArray = new Player[count];
            for (int idx = 0; idx < count; idx++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand player = new Hand(cardsArray);
                maxpq.insert(player);

                if (maxpq.size() > target) {  //當PQ的size超過target時就把多的丟出來
                    maxpq.delMin();
                }

            }
            Hand targetplayer = (Hand) maxpq.delMin();
            Card[] targethand = targetplayer.getCards();
            for (int i = 0; i < 5; i++) {
                if (i == 4) {
                    System.out.println(targethand[4].getSuit() + ""_"" + targethand[4].getFace());
                } else {
                    System.out.print(targethand[i].getSuit() + ""_"" + targethand[i].getFace() + "","");
                }
            }

        }
    }

}
