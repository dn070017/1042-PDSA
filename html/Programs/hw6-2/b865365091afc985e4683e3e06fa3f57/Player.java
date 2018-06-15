import cardgame.Card;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player implements Comparable<Player>{ 
    
    public static final Comparator<Card> SUIT_ORDER = new Card.SuitOrder();

    private static final Comparator<Card> FACE_ORDER = new Card.FaceOrder();
    enum types{
        fullhouse, flush, straight, tpair, opair, high
    }
    
    public void set_types(){
        
    }

    private Card[] cards = new Card[5];
    private String name;
     
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
     
    // TODO 
    public int compareTo(Player that) {
.
        return 0;
    }
    private static class SuitOrder implements Comparator<Card> {

        private static List<String> orders;
        SuitOrder() {
            String[] suits = {""Spades"", ""Hearts"", ""Diamonds"", ""Clubs""};
            orders = new ArrayList<String>();
            for (String suit : suits) {
                orders.add(suit);
            }
        }

        @Override
        public int compare(Card c1, Card c2) {
            int suitc1 = orders.indexOf(c1.getSuit());
            int suitc2 = orders.indexOf(c2.getSuit());
            if (suitc1 < suitc2) return 1;
            else if (suitc1 > suitc2) return -1;
            else return 0;
        }

    }

    private static class FaceOrder implements Comparator<Card> {
        private static List<String> orders;
        FaceOrder() {
            String[] faces = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2""};
            orders = new ArrayList<String>();
            for (String face : faces) {
                orders.add(face);
            }
        }

        @Override
        public int compare(Card c1, Card c2) {
            int facec1 = orders.indexOf(c1.getFace());
            int facec2 = orders.indexOf(c2.getFace());
            if (facec1 < facec2) return 1;
            else if (facec1 > facec2) return -1;
            else return 0;
        }
    }
}


