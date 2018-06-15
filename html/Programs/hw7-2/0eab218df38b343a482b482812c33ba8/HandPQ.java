
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
            Card[][] card = new Card[count][];//前面格子代表每一個人，後面格子代表他的每一張牌
            //MaxPQ pq=new MaxPQ();
            MinPQ pq=new MinPQ();
            for (int i = 0; i < count; i++) {
                String[] PlayerHand = br.readLine().split("","");//讀每一個player的手牌
                card[i] = new Card[5];
                for (int j = 0; j < 5; j++) {
                    String[] sf = PlayerHand[i].split(""_"");//讀每一張排的花色和數字
                    card[i][j] = new Card(sf[1], sf[0]);
                }
                hand[i] = new Hand(card[i]);
                pq.insert(hand[i]);
                if(pq.size()>target)
                    pq.delMin();
            }
            Hand[] kk=new Hand[5];
            Card[] anser = hand[count-target].getCards();
            for(int i=0;i<pq.size();i++){
                if(i==pq.size()-1)
                    System.out.println(pq.delMin());
            }
//                kk=pq.delMin();
//            
//            for (int i = 0; i < 5; i++) {//輸出牌
//                System.out.println(anser[i].getSuit()+""_""+anser[i].getFace());
//                if(i<4)
//                    System.out.println("","");
//            }

        }
    }
}

