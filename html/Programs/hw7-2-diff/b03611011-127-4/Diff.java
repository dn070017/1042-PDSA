import java.io.FileReader;
import java.io.BufferedReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int idx = 0;
            Hand[] playerArray = new Hand[count+1];
            
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand hand = new Hand(cardsArray);
                playerArray[idx++] = hand;
            }

            for (int i = 0; i < count; i++) {
                Hand[] min = new Hand[1];
                for (int j = i + 1; j < count; j++) {
                    if (playerArray[i].compareTo(playerArray[j]) == -1) {
                        min[0] = playerArray[i];
                        playerArray[i] = playerArray[j];
                        playerArray[j] = min[0];
                    }
                }
            }
            Card[] card1 = new Card[6];
            card1=playerArray[target-1].getCards();
            for (int i = 0; i < 5; i++) {
                Card[] max = new Card[1];
                for (int j = i + 1; j < 5; j++) {
                    if (card1[i].compareTo(card1[j])==1) {
                        max[0] = card1[i];
                        card1[i] = card1[j];
                        card1[j] = max[0];
                    }
                }
            }
            System.out.println(card1[0].getSuit()+""_""+card1[0].getFace()+"",""
                    +card1[1].getSuit()+""_""+card1[1].getFace()+"",""+card1[2].getSuit()+""_""+card1[2].getFace()
                    +"",""+card1[3].getSuit()+""_""+card1[3].getFace()+"",""+card1[4].getSuit()+""_""+card1[4].getFace());
        }
    }
}

