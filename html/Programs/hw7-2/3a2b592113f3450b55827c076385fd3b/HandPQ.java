
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;

public class HandPQ {

    public static void main(String[] args) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            MinPQ<Hand> handspq = new MinPQ<Hand>();
            int N = 0;
            for (String in = br.readLine(); in != null; in = br.readLine()) {
                Card[] cards = new Card[5];
                String[] cardStr = in.split("","");
                for (int i = 0; i < cardStr.length; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    cards[i] = new Card(sep[1], sep[0]);
                }
                handspq.insert(new Hand(cards));
                if (++N == target + 1) {
                    handspq.delMin();
                }
            }

            Hand hand3rd = handspq.delMin();
            Card[] cards3rd = hand3rd.getCards();
            System.out.println(cards3rd[0].getSuit() +""_"" +cards3rd[0].getFace() + "",""
                    + cards3rd[1].getSuit() +""_"" +cards3rd[1].getFace() + "",""
                    + cards3rd[2].getSuit() +""_"" +cards3rd[2].getFace() + "",""
                    + cards3rd[3].getSuit() +""_"" +cards3rd[3].getFace() + "",""
                    + cards3rd[4].getSuit() +""_"" +cards3rd[4].getFace());
//            int k = 0;
//            while (!handspq.isEmpty()) {
//                Card[] c = handspq.delMin().getCards();
//                System.out.println(++k + "": "" + c[0].getFace() + c[0].getSuit() + "" ""
//                        + c[1].getFace() + c[1].getSuit() + "" ""
//                        + c[2].getFace() + c[2].getSuit() + "" ""
//                        + c[3].getFace() + c[3].getSuit() + "" ""
//                        + c[4].getFace() + c[4].getSuit());
//            }

        }
    }
}
