import edu.princeton.cs.algs4.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            MaxPQ<Hand> pq=new MaxPQ<>();
            for (String in = br.readLine(); in!=null; in=br.readLine()) {
                String c[]=in.split("","");
                Card cards[]=new Card[5];
                for (int i = 0; i < 5; i++) {
                    String[] s=c[i].split(""_"");
                    Card card=new Card(s[1],s[0]);
                    cards[i]=card;
                }
                pq.insert(new Hand(cards));
            }

            String ans="""";
            for (int i = 0; i < target; i++) {
                pq.delMax();
                if(i==target-1) {
                    Card c[]=pq.delMax().getCards();
                    for (int j = 0; j < 5; j++) {
                        ans+=(c[j].getSuit()+""_""+c[j].getFace());
                        if(j!=4)ans+="","";
                    }
                }
            }
            System.out.println(ans);
        }
    }
}

