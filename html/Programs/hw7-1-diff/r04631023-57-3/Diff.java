import java.util.Arrays;


public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 
    private String[] checkFace = new String[]{""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
    private String[] checkSuit = new String[]{""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};
    private Card[] checkCardsFace = new Card[13];
    private Card[] checkCardsSuit = new Card[4];
    double[][] check = new double[5][2];


    public double[][] checkhandspoint(Card[] cards) {
        double[][] checkhandspoint = new double[cards.length][2];
        for (int k = 0; k < cards.length; k++) {
            for (int i = 0; i < 13; i++) {
                if (cards[k].getFace().compareTo(checkFace[i]) == 0) {
                    checkhandspoint[k][0] = i;
                    break;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (cards[k].getSuit().compareTo(checkSuit[i]) == 0) {
                    checkhandspoint[k][1] = i;
                    checkhandspoint[k][1] = checkhandspoint[k][1] / 10;
                    break;
                }
            }
        }
        return checkhandspoint;
    }
    
    public double checkhands(Card[] cards) {
        double handpoints = 0;
        Arrays.sort(cards);
        double[][] check = checkhandspoint(cards);
        if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] == check[3][0] && check[3][0] != check[4][0]) {
            handpoints = 2 * 13 + check[3][0] + check[3][1]; //two pair
            return handpoints;
        } else if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 2 * 13 + check[4][0] + check[4][1]; //two pair
            return handpoints;
        } else if (check[0][0] != check[1][0] && check[1][0] == check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 2 * 13 + check[4][0] + check[4][1]; //two pair
            return handpoints;
        } else if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] == check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 5 * 13 + check[4][0] + check[4][1]; //full house
            return handpoints;
        } else if (check[0][0] == check[1][0] && check[1][0] == check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 5 * 13 + check[2][0] + check[2][1]; //full house
            return handpoints;
        } else if (check[0][1] == check[1][1] && check[0][1] == check[2][1] && check[0][1] == check[3][1] && check[0][1] == check[4][1]) {
            handpoints = 4 * 13 + check[4][0] + check[4][1]; //flush
            return handpoints;
        } else if (check[4][0] - check[3][0] == check[3][0] - check[2][0] && check[3][0] - check[2][0] == check[2][0] - check[1][0] && check[2][0] - check[1][0] == check[1][0] - check[0][0]) {
            handpoints = 3 * 13 + check[4][0] + check[4][1];//Straight
            return handpoints;
        } else if (check[0][0] != check[1][0] && check[1][0] != check[2][0] && check[2][0] != check[3][0] && check[3][0] != check[4][0]) {
            handpoints = check[4][0] + check[4][1]; //high card
            return handpoints;
        } else if (check[3][0] == check[4][0]) {
            handpoints = 1 * 13 + check[4][0] + check[4][1]; //one pair
            return handpoints;
        } else if (check[2][0] == check[3][0]) {
            handpoints = 1 * 13 + check[3][0] + check[3][1]; //one pair
            return handpoints;
        } else if (check[1][0] == check[2][0]) {
            handpoints = 1 * 13 + check[2][0] + check[2][1]; //one pair
            return handpoints;
        } else if (check[0][0] == check[1][0]) {
            handpoints = 1 * 13 + check[1][0] + check[1][1]; //one pair
            return handpoints;
        }
        return handpoints;
    }

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        this.cards = cards ;
        return;
    }

    // TODO
    public int compareTo(Hand that) {
        if (checkhands(this.cards) > checkhands(that.cards)) {
            return 1;
        } else if (checkhands(this.cards) == checkhands(that.cards)) {
            return 0;
        } else {
            return -1;
        }
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
}

