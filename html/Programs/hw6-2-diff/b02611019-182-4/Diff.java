
import java.util.Arrays;
import java.util.Collections;

class intCard {

    int intface;
    int intsuit;

    intCard(int intface, int intsuit) {
        this.intface = intface;
        this.intsuit = intsuit;
    }
}

public class Player implements Comparable<Player> {

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
        Arrays.sort(this.cards);
//        for(int i=0;i<5;i++){
//        StdOut.print(this.cards[i].getFace()+this.cards[i].getSuit()+""  "");
//        }
//        StdOut.print(""\n"");
    }

    public int suit2int(Card card) {
        switch (card.getSuit()) {
            case ""Spades"":
                return 3;
            case ""Hearts"":
                return 2;
            case ""Diamonds"":
                return 1;
            case ""Clubs"":
                return 0;
        }
        return 0;
    }

    public int face2int(Card card) {
        switch (card.getFace()) {
            case ""A"":
                return 14;
            case ""K"":
                return 13;
            case ""Q"":
                return 12;
            case ""J"":
                return 11;
            case ""10"":
                return 10;
            case ""9"":
                return 9;
            case ""8"":
                return 8;
            case ""7"":
                return 7;
            case ""6"":
                return 6;
            case ""5"":
                return 5;
            case ""4"":
                return 4;
            case ""3"":
                return 3;
            case ""2"":
                return 2;
        }
        return 0;
    }

    public boolean fullhouse(int[] intface) {
        if (intface[0] == intface[1] && intface[3] == intface[4] && (intface[1] == intface[2] || intface[2] == intface[3])) {
            return true;
        }
        return false;
    }

    public boolean flush(int[] intsuit) {
        if (intsuit[0] == intsuit[1] && intsuit[1] == intsuit[2] && intsuit[2] == intsuit[3] && intsuit[3] == intsuit[4]) {
            return true;
        }
        return false;
    }

    public boolean straight(int[] intface) {
        for (int i = 0; i <= 3; i++) {
            if (intface[i] != intface[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean twopair(int[] intface, Card[] cards) {
        if (intface[0] == intface[1]) {
            if (intface[3] == intface[4]) {
                thissize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            } 
            if (intface[2] == intface[3]) {
                thissize = new Card(cards[3].getFace(), cards[3].getSuit());
                return true;
            }
        }
        if(intface[1] == intface[2]){
            if (intface[3] == intface[4]) {
                thissize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            } 
        }
        return false;
    }
    public boolean thattwopair(int[] intface, Card[] cards) {
        if (intface[0] == intface[1]) {
            if (intface[3] == intface[4]) {
                thatsize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            } 
            if (intface[2] == intface[3]) {
                thatsize = new Card(cards[3].getFace(), cards[3].getSuit());
                return true;
            }
        }
        if(intface[1] == intface[2]){
            if (intface[3] == intface[4]) {
                thatsize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            } 
        }
        return false;
    }
    public boolean pair(int[] intface, Card[] cards) {
        for(int i=0;i<=3;i++){
            if(intface[i]==intface[i+1]){
                thissize = new Card(cards[i+1].getFace(), cards[i+1].getSuit());
                return true;
            }
        }
        return false;
    }
    
    
    public boolean thatpair(int[] intface, Card[] cards) {
        for(int i=0;i<=3;i++){
            if(intface[i]==intface[i+1]){
                thatsize = new Card(cards[i+1].getFace(), cards[i+1].getSuit());
                return true;
            }
        }
        return false;
    }

    static Card thissize;
    static Card thatsize;

    // TODO 

    public int compareTo(Player that) {

        
.
        int thisord = 1;
        int thatord = 1;
        int[] thisintface = new int[5];
        int[] thisintsuit = new int[5];
        int[] thatintface = new int[5];
        int[] thatintsuit = new int[5];

        for (int i = 0; i < 5; i++) {
            thisintface[i] = face2int(this.cards[i]);
            thatintface[i] = face2int(that.cards[i]);
            thisintsuit[i] = suit2int(this.cards[i]);
            thatintsuit[i] = suit2int(that.cards[i]);
        }
        //Check this size
        thissize = new Card(this.cards[4].getFace(),this.cards[4].getSuit());
        if(pair(thisintface, this.cards)){
            thisord =2;
        }
        if (twopair(thisintface, this.cards)) {
            thisord =3;
        }
        if (straight(thisintface)) {
            thisord = 4;
            thissize = new Card(this.cards[4].getFace(), this.cards[4].getSuit());
        }
        if (flush(thisintsuit)) {
            thisord = 5;
            thissize = new Card(this.cards[4].getFace(), this.cards[4].getSuit());
        }
        if (fullhouse(thisintface)) {
            thisord = 6;
            thissize = new Card(this.cards[2].getFace(), this.cards[2].getSuit());
        }
        //check that size
        thatsize = new Card(that.cards[4].getFace(),that.cards[4].getSuit());
        if(thatpair(thatintface, that.cards)){
            thatord =2;
        }
        if (thattwopair(thatintface, that.cards)) {
            thatord =3;
        }
        if (straight(thatintface)) {
            thatord = 4;
            thatsize = new Card(that.cards[4].getFace(), that.cards[4].getSuit());
        }
        if (flush(thatintsuit)) {
            thatord = 5;
            thatsize = new Card(that.cards[4].getFace(), that.cards[4].getSuit());
        }
        if (fullhouse(thatintface)) {
            thatord = 6;
            thatsize = new Card(that.cards[2].getFace(), that.cards[2].getSuit());
        }
        
        if(thisord>thatord){
            return +1;
        }
        if(thisord<thatord){
            return -1;
        }
        return(thissize.compareTo(thatsize));
    }
}

