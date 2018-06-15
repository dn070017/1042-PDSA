import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class HandPQ {

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {

            String[] header = br.readLine().split("","");
            int count = Integer.parseInt(header[0]);
            int target = Integer.parseInt(header[1]);

            Hand[] hands = new Hand[target];
            for (int i = 0 ; i < count; i++) {
                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int j = 0; j < 5; j++) {
                    String[] sep = cardStr[j].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[j] = card;
                }
                if (i < target) {
                    hands[i] = new Hand(cardsArray);
                } else {
                    Hand newHand = new Hand(cardsArray) ;
                    for (int j = 0 ; j < target; j++){
                        //System.out.print(hands[j].getCards()) ;
                        if (hands[j].compareTo(newHand) == -1){
                            Hand copyHand = new Hand(hands[j].getCards()) ;
                            hands[j] = newHand ;
                            newHand = copyHand ;
                        }
                    }
                }
            }
            Hand targetHand = hands[0] ;
            for (int i = 1 ; i < target; i++){
                if (hands[i].compareTo(targetHand) == -1){
                            Hand copyHand = hands[i] ;
                            hands[i] = targetHand ;
                            targetHand = copyHand ;
                        }
            }
            Card[] handCards = targetHand.getCards() ;
            Arrays.sort(handCards);
            String ans = """" ;
            for (int i = 0 ; i < 5 ; i++){
                ans += handCards[i].getSuit() ;
                ans += ""_"" ;
                ans += handCards[i].getFace();
                if (i < 4){
                    ans += "","" ;
                }
            }
            System.out.println(ans) ;
        }
    }
}

