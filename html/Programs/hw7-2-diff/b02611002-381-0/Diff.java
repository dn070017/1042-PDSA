import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;


public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            //TODO
            MinPQ<Hand> MHPQ = new MinPQ<>();
            Card Clubs1 = new Card(""A"",""Clubs"");
            Card[] minHand = new Card[5];
            for(int i=0;i<5;i++) minHand[i]=Clubs1; // create minimun hand

            for(String in=br.readLine(); in!=null; in=br.readLine()){ // read input hands

                Card[] readCards = new Card[5];
                String[] cardStr = in.split("","");
                for(int i=0;i<5;i++){   // read 5 cards in current hand(readCards)
                    String[] sep=cardStr[i].split(""_"");
                    Card card = new Card(sep[1],sep[0]);
                    readCards[i]=card;
                }

                Hand readHand = new Hand(readCards);

                MHPQ.insert(readHand);
                while (MHPQ.size()>target) MHPQ.delMin();



            }

//            //TODO disp MHPQ
//            for(Hand h:MHPQ){
//                Card[] cs=h.getCards();
//                for(Card c:cs){
//                    System.out.println(c.getFace()+c.getSuit());
//                }
//                System.out.println(""\n-----"");
//            }

            Card[] returnVal = MHPQ.delMin().getCards();
            String[] returnValS = new String[10];
            //Clubs_4,Diamonds_4,Hearts_10,Hearts_K,Spades_A
            int i=0;
            for(Card c:returnVal) {
                returnValS[i]=c.getSuit();
                returnValS[i+1]=c.getFace();
                i+=2;
            }
            for(int j=10;j>0;j-=2){
                System.out.printf(""%s_%s,"",returnValS[j-2],returnValS[j-1]);
            }
        }
    }
}

