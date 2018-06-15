import java.util.Comparator;



public class Card implements Comparable<Card> {

    private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
    private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
    private String[] checkFace = new String[]{""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""} ;
    private String[] checkSuit = new String[]{""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""} ;
    public double point ;
    public double plus ;

    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
        for (int i = 0; i < 13; i++){
            if (this.face == checkFace[i]){
                this.point = i ;
                break ;
            }
        }
        for (int i = 0; i < 4; i++){
            if (this.suit == checkSuit[i]){
                this.plus = i/10 ;
                break ;
            }
        }
    }

    // DO NOT MODIFY THIS   
    public String getFace() {
        return this.face;
    }

    // DO NOT MODIFY THIS    
    public String getSuit() {
        return this.suit;
    }

    // TODO
    public int compareTo(Card that) {
        if (this.point+this.plus > that.point+that.plus){
            return 1 ;
        }else{
            return -1 ;
        }
    }

    // TODO
    private static class SuitOrder implements Comparator<Card> {

        public int compare(Card c1, Card c2) {
            if (c1.plus < c2.plus) {
                return 1 ;
            }else if(c1.plus > c2.plus){
                return -1 ;
            }else{
                return 0 ;
            }
        }
    }
}

