import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
               int idx = 0;
            int playerCount = count;
            Hand [] playerArray = new Hand[playerCount];
            MaxPQ maxpq= new MaxPQ(count);
            for(int i=0;i<count;i++) {
              //  String name = in;
    //            Hand player = new Hand;
                //playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int j = 0; j < 5; j++){
                    String[] sep = cardStr[j].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;
                }
                playerArray[i]=new Hand(cardsArray);
                maxpq.insert(playerArray[i]);
            }

            //Arrays.sort(playerArray);
            for(int g=0;g<target-1;g++)
            {maxpq.delMax();}
            Hand jjj= (Hand) maxpq.delMax();
            
            Card a[]=jjj.getCards();
           //Card a[]=playerArray[count-target].getCards();
            Arrays.sort(a);
            for(int i=0;i<5;i++){
            System.out.print(a[i].getSuit());
            System.out.print(""_"");
           System.out.print(a[i].getFace());
           if(i!=4) System.out.print("","");
            }
        }
    }

   /* private static void MaxPQ(int count) {
.
    }*/
}

