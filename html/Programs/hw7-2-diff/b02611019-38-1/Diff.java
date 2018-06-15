import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MaxPQ<Hand> pq = new MaxPQ<Hand>();
            Card[] cards =new Card[5];
            Hand[] hands=new Hand[count];
            for(int i=0;i<count;i++){
                String[] cardline = br.readLine().split("","");
                for(int k=0;k<5;k++){
                    String[] suitface =new String[2];
                    suitface = cardline[k].split(""_"");
                    cards[k]= new Card(suitface[1],suitface[0]);
//                    StdOut.print(suitface[0]+""_""+suitface[1]+"" "");
                }
//                StdOut.print(""\n"");
                pq.insert(new Hand(cards));
            }
            
            for(int i=0;i<3;i++){
//                StdOut.print(pq.delMax().getCards()[0].getFace());
//                StdOut.print(1);
            }
            
            Card[] maxcards = new Card[5];
            maxcards = pq.max().getCards();
            for(int i=0;i<5;i++){
                StdOut.print(maxcards[i].getSuit()+""_""+maxcards[i].getFace());
                if(i!=4){
                    StdOut.print("","");
                }
            }
        }
    }
}

