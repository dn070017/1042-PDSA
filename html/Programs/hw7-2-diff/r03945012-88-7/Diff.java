import java.io.BufferedReader;
import java.io.FileReader;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);
            
            
            // Find the Largest M items (target)
            MinPQ<Hand> pq = new MinPQ<Hand>();
            
            for(int k = 0; k < count; k++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                Hand player1 = new Hand(cardsArray);
                pq.insert(player1);
                if (pq.size() > target){
                    pq.delMin();
                }
            }
            Card[] cardsWinner = new Card[5];
            Hand winner = pq.delMin();
            cardsWinner = winner.getCards();
            String answer = """";
            for (int i = 0; i < 4; i++){
                answer += cardsWinner[i].getSuit();
                answer += ""_"";
                answer += cardsWinner[i].getFace();
                answer += "","";
            }
            answer += cardsWinner[4].getSuit();
            answer += ""_"";
            answer += cardsWinner[4].getFace();
            StdOut.println(answer);
//            Card[] cardsArray2 = new Card[5];
//            String[] cardStr2 = br.readLine().split("","");
//            for(int i = 0; i < 5; i++){
//                String[] sep2 = cardStr2[i].split(""_"");
//                Card card2 = new Card(sep2[1], sep2[0]);
//                cardsArray2[i] = card2;
//            }
//            Hand player2 = new Hand(cardsArray2);
//            player1.compareTo(player2);
            
        
        }
    }
}

