import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {
        //""input14.txt""  args[0]
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] header = br.readLine().split("","");
            //int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MinPQ<Hand> KK = new MinPQ();
            int idx = 0;
            
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cards = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_""); 
                    cards[i] = new Card(sep[1], sep[0]);
                    //System.out.println(cardsArray[idx][i].getFace());
                }
                Hand player = new Hand(cards);
                KK.insert(player);
                idx++;
                if (KK.size() > target){
                  KK.delMin();
                }     
            }
                idx = 0;
                //System.out.println(target);
                for (int i = 0; i<5; i++){
                  System.out.printf(KK.min().getCards()[i].getSuit());
                  System.out.printf(""_"");
                  System.out.printf(KK.min().getCards()[i].getFace());
                  idx++;
                  while(idx<5){
                  System.out.printf("","");
                  break;}
                }          
    }
}}

