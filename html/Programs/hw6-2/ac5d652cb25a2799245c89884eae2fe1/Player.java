
import java.util.Arrays;

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
    }

    // TODO 
    public int compareTo(Player that) {
.
        
        if(this.fullhouse(this.cards) > that.fullhouse(this.cards)) return 1;
        else if(this.fullhouse(this.cards) < that.fullhouse(this.cards)) return -1;
        else{
            if(this.flush(this.cards) > that.flush(this.cards)) return 1;
            else if(this.flush(this.cards) < that.flush(this.cards)) return -1;
            else{
                if(this.straight(this.cards) > that.flush(this.cards)) return 1;
                else if(this.straight(this.cards) < that.flush(this.cards)) return -1;
                else return 0;
            }
        }

    }

    private static int cardface(String face) {
        int facenum;

        if (face.equals(""A"")) {
            facenum = 14;
        } else if (face.equals(""J"")) {
            facenum = 11;
        } else if (face.equals(""Q"")) {
            facenum = 12;
        } else if (face.equals(""K"")) {
            facenum = 13;
        } else {
            facenum = Integer.parseInt(face);
        }

        return facenum;
    }

    private static int cardsuit(String suit) {
        int suitnum;

        if (suit.equals(""Spades"")) {
            suitnum = 4;
        } else if (suit.equals(""Hearts"")) {
            suitnum = 3;
        } else if (suit.equals(""Diamonds"")) {
            suitnum = 2;
        } else {
            suitnum = 1;
        }

        return suitnum;
    }
    
    private int fullhouse(Card[] card){
        int cardnum[]=new int[5];
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);
                
        if ((cardnum[0] == cardnum[1]) && (cardnum[3] == cardnum[4])) {
            if((cardnum[2] == cardnum[1]) || (cardnum[2] == cardnum[3]))
                return cardnum[2];
        } 
            return 0;
    }
    
    private int flush(Card[]card){
        int suitnum = cardsuit(card[0].getFace());
        int cardnum = cardface(card[0].getFace());
        int i=1;
        
        while(cardsuit(card[i].getFace()) == suitnum){
            if(cardnum < cardface(card[0].getFace())) cardnum=cardface(card[0].getFace());
            i++;
            if(i==5){
                return (cardnum*10+suitnum);
            }
        }
        
        return 0;
    }

    private int straight(Card[]card){
        int cardnum[]=new int[5];
        Card nope;
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);
        
        int i=1;
        while(cardnum[i] == cardnum[i-1]+1){
            if(i==4) return (cardsuit(card[i].getFace()) + cardnum[i]*10);          
            i++;       
        }

        return 0;
    }
    
    
    
}

