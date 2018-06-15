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
            Hand[] HandArray = new Hand[target];
            int idx = 0;
            
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cards = new Card[5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cards[i] = card;
                    //System.out.println(cardsArray[idx][i].getFace());
                }
                Hand player = new Hand(cards);
                idx++;
                while(idx > target){
                   Arrays.sort(HandArray);
                   //System.out.println(player.compareTo(HandArray[0]));
                   if(player.compareTo(HandArray[0])>0){
                   HandArray[0] = player;}
                   break;}
                while(idx <= target){
                   //System.out.println(idx);
                   HandArray[idx-1] = player;
                   break;}
                //如果player能夠擠進target，才能夠被放到array            
            }
                Arrays.sort(HandArray);
                //System.out.println(HandArray[0].compareTo(HandArray[1]));
                //System.out.println(HandArray[0].compareTo(HandArray[2]));
                idx = 0;
                //System.out.println(target);
                for (int i = 0; i<5; i++){
                  System.out.printf(HandArray[0].getCards()[i].getSuit());
                  System.out.printf(""_"");
                  System.out.printf(HandArray[0].getCards()[i].getFace());
                  idx++;
                  while(idx<5){
                  System.out.printf("","");
                  break;}
                }          
    }
}}

