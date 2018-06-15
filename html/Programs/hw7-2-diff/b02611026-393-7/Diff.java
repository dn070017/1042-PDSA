
import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

//            System.out.println(count);
//            System.out.println(target);

            HandPQ PQ = new HandPQ();
            MinPQ<Hand> pq = new MinPQ<Hand>(target);


            for (int j = 0; j < count; j++) {
                Card[] cards = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");

                    cards[i] = new Card(sep[1], sep[0]);

//                    System.out.println(cards[i].getFace());
//                    System.out.println(cards[i].getSuit());
                }
                Hand input = new Hand(cards);
                pq.insert(input);
                if (pq.size() > target) {
                    pq.delMin();
                }

            }

            Hand output = pq.delMin();
            //output[0] = ;
            Card[] outputs = output.getCards();

            System.out.print(outputs[0].getSuit());
            System.out.print(""_"");
            System.out.print(outputs[0].getFace());
            System.out.print("","");
            System.out.print(outputs[1].getSuit());
            System.out.print(""_"");
            System.out.print(outputs[1].getFace());
            System.out.print("","");
            System.out.print(outputs[2].getSuit());
            System.out.print(""_"");
            System.out.print(outputs[2].getFace());
            System.out.print("","");
            System.out.print(outputs[3].getSuit());
            System.out.print(""_"");
            System.out.print(outputs[3].getFace());
            System.out.print("","");
            System.out.print(outputs[4].getSuit());
            System.out.print(""_"");
            System.out.print(outputs[4].getFace());
            
            
        }
    }
}

