import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            int idx = 0;
            Hand[] playerArray = new Hand[count];
            //construct players' hands
            for(int x=0;x<count;x++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                playerArray[x] = new Hand(cardsArray);
                //System.out.println(0);
            }
            MaxPQ <Hand>gg=new MaxPQ(playerArray);
            for(int i=0;i<target;i++){                
                if(i==target-1){
                    playerArray[0]=gg.delMax();
                    break;
                }else gg.delMax();
            }
            MinPQ<Card>haha=new MinPQ(playerArray[0].getCards());
            
            //haha.insert(playerArray[0].getCards());
            for(int i=0;i<5;i++){
                Card ff=haha.delMin();
                String face=ff.getFace();
                String suit=ff.getSuit();
                System.out.print(suit);
                System.out.print(""_"");
                System.out.print(face);
                if(i!=4) System.out.print("","");
            }
        }
    }
}

