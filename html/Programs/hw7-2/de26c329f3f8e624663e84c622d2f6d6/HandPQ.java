import java.io.BufferedReader;
import java.io.FileReader;	
public class HandPQ {
      public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            Hand[] game=new Hand[count];
            for (int i=0 ; i<count ; i++){
                String[] player=br.readLine().split("","");
                Card[] cards=new Card[5];
                for (int j=0 ; j<5 ; j++){
                    String[] temp=player[j].split(""_"");
                    cards[j]= new Card(temp[1],temp[0]);
                }
                game[i]=new Hand(cards);
            }
            MaxPQ<Hand> thisgame=new MaxPQ<Hand>(game);
            while ((count-thisgame.size())<target-1)
              thisgame.delMax();
            Card[] ans=new Card[5];
            ans=thisgame.delMax().getCards();
            for (int i=0 ; i<4 ; i++)
            System.out.print(ans[i].getSuit()+""_""+ans[i].getFace()+"","");
            System.out.print(ans[4].getSuit()+""_""+ans[4].getFace());
        }
    }
}

