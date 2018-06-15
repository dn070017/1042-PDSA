
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
            Card[] card;//紀錄每一張牌
            
            MinPQ<Hand> pqMin = new MinPQ(target+1);
            MaxPQ<Hand> pqMax = new MaxPQ();

            for (int i = 0; i < count; i++) {
                String[] PlayerHand = br.readLine().split("","");//讀每一個player的手牌     
                card = new Card[5];
                for (int j = 0; j < 5; j++) {
                    String[] sf = PlayerHand[j].split(""_"");//讀每一張排的花色和數字
                    card[j] = new Card(sf[1], sf[0]);
                }
                hand[i] = new Hand(card);
                if (count - target >= count / 2) {
                    pqMin.insert(hand[i]);
                    if(pqMin.size()>target) pqMin.delMin();
                }
                if (count - target < count / 2) {
                    pqMax.insert(hand[i]);
                    if(pqMax.size()>count-target+1) pqMax.delMax();
                }
            }    
            Card[] answer=null;
            if (count - target >= count / 2) {
                    answer = pqMin.delMin().getCards();            
            }                
                if (count - target < count / 2) {
                    answer = pqMax.delMax().getCards();            
            }
                for (int i = 0; i < 5; i++) {
                System.out.print(answer[i].getSuit() + ""_"" + answer[i].getFace());
                if (i < 4) {
                    System.out.print("","");
                }
            }
        }
    }
}
