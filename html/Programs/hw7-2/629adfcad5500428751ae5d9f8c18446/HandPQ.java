

import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            Hand targethand;
            String output = null;

            if (target > count / 2) {
//                用 maxpq ， 取倒數
                MaxPQ<Hand> handpq = new MaxPQ<Hand>(count - target + 2);

                int PQsize = count - target + 2;
                int counthand = 1;

                for (String in = br.readLine(); in != null; in = br.readLine()) {
                    Card[] cardsArray = new Card[5];
                    String[] cardStr = in.split("","");
                    for (int i = 0; i < 5; i++) {
                        String[] sep = cardStr[i].split(""_"");
                        Card card = new Card(sep[1], sep[0]);
                        cardsArray[i] = card;
                    }
                    Hand item = new Hand(cardsArray);

                    if (counthand <= PQsize) {
                        handpq.insert(item);
                    } else if (counthand > PQsize) {
                        handpq.delMax();
//                        Card B =  handpq.delMax().getCards()[0];      
//                        System.out.println(B.getFace()+B.getSuit());
                        handpq.insert(item);
                    }
                    counthand++;
                }
//               Card A = handpq.delMax().getCards()[0];
//               System.out.println(A.getFace()+A.getSuit());
                handpq.delMax();
               targethand = new Hand(handpq.delMax().getCards()) ;
                
            } else {
//                用 minpq 
                MinPQ<Hand> handpq = new MinPQ<Hand>(target + 1);

                int PQsize = target + 1;
                int counthand = 1;

                for (String in = br.readLine(); in != null; in = br.readLine()) {
                    Card[] cardsArray = new Card[5];
                    String[] cardStr = in.split("","");
                    for (int i = 0; i < 5; i++) {
                        String[] sep = cardStr[i].split(""_"");
                        Card card = new Card(sep[1], sep[0]);
                        cardsArray[i] = card;
                    }
                    Hand item = new Hand(cardsArray);

                    if (counthand <= PQsize) {
                        handpq.insert(item);
                    } else if (counthand > PQsize) {
                        handpq.delMin();
                        handpq.insert(item);
                    }
                    counthand++;
                }

               handpq.delMin();
               targethand = new Hand(handpq.delMin().getCards()) ;
                
            }

            for (int i = 0; i < 5; i++) {
                if(i == 0){
                    output = targethand.getCards()[i].getSuit() + ""_"" + targethand.getCards()[i].getFace();
                }else{
                     output += targethand.getCards()[i].getSuit() + ""_"" + targethand.getCards()[i].getFace();
                }
                
                if (i != 4) {
                    output += "","";
                }
            }

            System.out.print(output);

        }
    }
}

