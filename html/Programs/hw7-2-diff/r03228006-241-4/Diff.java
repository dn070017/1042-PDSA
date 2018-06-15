import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {
        //""input14.txt""  args[0]
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            Hand[] HandArray = new Hand[count];
            int idx = 0;
            
            for(String in = br.readLine(); in != null; in = br.readLine()) {
                Card[][] cardsArray = new Card[count][5];
                String[] cardStr = in.split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[idx][i] = card;
                    //System.out.println(cardsArray[idx][i].getFace());
                }
                Hand player = new Hand(cardsArray[idx]);
                HandArray[idx++] = player;
                //拿完這個人的五張牌之後               
            }
                Arrays.sort(HandArray);
                int ii = 0;
                //System.out.println(target);
                for (int i = 0; i<5; i++){
                  System.out.printf(HandArray[count-target].getCards()[i].getSuit());
                  System.out.printf(""_"");
                  System.out.printf(HandArray[count-target].getCards()[i].getFace());
                  ii++;
                  while(ii<5){
                  System.out.printf("","");
                  break;}
                }          
    }
}}

