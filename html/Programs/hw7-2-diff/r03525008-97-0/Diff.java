import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        //try(BufferedReader br = new BufferedReader(new FileReader(""in.txt""))){
          try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            MaxPQ<Hand> maxPQ = new MaxPQ(count);

            for(int i =0;i<count;i++) {
                String[] cards_name = br.readLine().split("","");
                Card[] cards = new Card[5];
                for (int j = 0; j < cards_name.length; j++) {
                    String temp[] = cards_name[j].split(""_"");
                    cards[j] = new Card(temp[1], temp[0]);
                }
                Hand hand=new Hand(cards);
                maxPQ.insert(hand);
            }
            for(int i=0;i<target-1;i++){
                maxPQ.delMax();
            }
            Arrays.sort(maxPQ.max().getCards());
            System.out.println(maxPQ.max().toString());
        }

/*        Card[] a=new Card[5];
        a[0] = new Card(""A"",""Spades"");
        a[1] = new Card(""K"",""Spades"");
        a[2] = new Card(""10"",""Spades"");
        a[3] = new Card(""5"",""Spades"");
        a[4] = new Card(""J"",""Hearts"");

        Card[] b=new Card[5];
        b[0] = new Card(""A"",""Hearts"");
        b[1] = new Card(""A"",""Clubs"");
        b[2] = new Card(""8"",""Spades"");
        b[3] = new Card(""2"",""Spades"");
        b[4] = new Card(""K"",""Hearts"");

        Hand A=new Hand(a);
        Hand B=new Hand(b);
        System.out.println(A.compareTo(B));*/
    }
}

