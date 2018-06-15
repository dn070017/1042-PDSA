import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]); //Nth big
            MinPQ<Hand> pq = new  MinPQ<Hand>(target+1);
            
            for(int i=0; i<count; i++){
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int j = 0; j < 5; j++){
                    String[] sep = cardStr[j].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;
//                    System.out.printf( card.suit_int +"" ""+ card.face_int + ""\n"");
                }
                Hand hand = new Hand(cardsArray);
                if(pq.size() < target){ pq.insert(hand);}
                else if(pq.size() == target){
                    pq.insert(hand);
                    Hand temp = pq.delMin();                   
                }
            }
            Hand Nth_big = pq.delMin();
            Card[] cards = Nth_big.getCards();
            System.out.print(cards[0].getSuit() + ""_"" + cards[0].getFace() + "",""
                           + cards[1].getSuit() + ""_"" + cards[1].getFace() + "",""
                           + cards[2].getSuit() + ""_"" + cards[2].getFace() + "",""
                           + cards[3].getSuit() + ""_"" + cards[3].getFace() + "",""
                           + cards[4].getSuit() + ""_"" + cards[4].getFace() + "","" + ""\n"" );
        }
    }
}

