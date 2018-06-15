
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            String ans = """";

            if((float)target/(float)count <=0.5) {
                MaxPQ<Hand> pq = new MaxPQ<>(count);
                for (String in = br.readLine(); in != null; in = br.readLine()) {
                    String c[] = in.split("","");
                    Card cards[] = new Card[5];
                    for (int i = 0; i < 5; i++) {
                        String[] s = c[i].split(""_"");
                        Card card = new Card(s[1], s[0]);
                        cards[i] = card;
                    }
                    pq.insert(new Hand(cards));
                }

//                System.gc();
                for (int i = 0; i < target; i++) {
                    if (i == target-1) {
                        Card c[] =pq.delMax().getCards();
                         pq.delMax().getCards();
                        for (int j = 0; j < 5; j++) {
                            ans += (c[j].getSuit() + ""_"" + c[j].getFace());
                            if (j != 4) ans += "","";
                        }
                        break;
                    }else{
                        pq.delMax();
                    }
                }
            }else{
                MinPQ<Hand> pq=new MinPQ<>(count);
                for (String in = br.readLine(); in != null; in = br.readLine()) {
                    String c[] = in.split("","");
                    Card cards[] = new Card[5];
                    for (int i = 0; i < 5; i++) {
                        String[] s = c[i].split(""_"");
                        Card card = new Card(s[1], s[0]);
                        cards[i] = card;
                    }
                    pq.insert(new Hand(cards));
                }
//                System.gc();

                for (int i = count; i >= target; i--) {
                    if (i == target) {
                        Card c[] = pq.delMin().getCards();
                        for (int j = 0; j < 5; j++) {
                            ans += (c[j].getSuit() + ""_"" + c[j].getFace());
                            if (j != 4) ans += "","";
                        }
                        break;
                    }else{
                        pq.delMin();
                    }
                }
            }
            System.out.println(ans);
        }
    }
}

