
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            Hand[] hand = new Hand[count];//訂出幾個人
            Card[]card = new Card[5];//紀錄每一張牌
            MinPQ<Hand> pq = new MinPQ();
            for (int i = 0; i < count; i++) {
                String[] PlayerHand = br.readLine().split("","");//讀每一個player的手牌                
                for (int j = 0; j < 5; j++) {
                    String[] sf = PlayerHand[j].split(""_"");//讀每一張排的花色和數字
                    card[j] = new Card(sf[1], sf[0]);
                }
                hand[i] = new Hand(card);
                pq.insert(hand[i]);
                if (pq.size() > target) {
                    pq.delMin();
                }
            }
            
            Card[] answer=pq.delMin().getCards();
            Arrays.sort(answer);
            for(int i=0;i<5;i++){
                System.out.print(answer[i].getSuit()+""_""+answer[i].getFace());
                if(i<4) System.out.print("","");
            }
        }
    }
}

