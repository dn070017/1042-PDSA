import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            Hand[] playerArray = new Hand[count];
            int idx = 0;
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand player = new Hand(cardsArray);
                playerArray[idx++] = player;
            }
            Arrays.sort(playerArray);
            Hand targetPlayer = playerArray[count-target];
            Card[] targetCard = targetPlayer.getCards();
            Arrays.sort(targetCard);
            String ans = """";
            for (int i = 0; i < 4; i++) {
                ans += targetCard[i].getSuit() + ""_"" + targetCard[i].getFace() + "","";
            }
            ans += targetCard[4].getSuit() + ""_"" + targetCard[4].getFace();
            System.out.println(ans);

        }
    }
}

