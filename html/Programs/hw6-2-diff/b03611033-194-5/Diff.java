
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
        
        if(this.fullhouse(this.cards) > that.fullhouse(that.cards)) return 1;
        else if(this.fullhouse(this.cards) < that.fullhouse(that.cards)) return -1;
        else{
//            StdOut.println(""==="");
//            StdOut.println(this.flush(this.cards));
//            StdOut.println(that.flush(that.cards));
            if(this.flush(this.cards) > that.flush(that.cards)) return 1;
            else if(this.flush(this.cards) < that.flush(that.cards)) return -1;
            else{

                if(this.straight(this.cards) > that.straight(that.cards)) return 1;
                else if(this.straight(this.cards) < that.straight(that.cards)) return -1;
                else{ 

                    if(this.pair(this.cards) > that.pair(that.cards)) return 1;
                    else if(this.pair(this.cards) < that.pair(that.cards)) return -1;
                    return 0;
                }
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
        int suitnum = cardsuit(card[0].getSuit());
        int cardnum = cardface(card[0].getFace());
        int i=1;
        
        while(cardsuit(card[i].getSuit()) == suitnum){
            if(cardnum < cardface(card[i].getFace())) cardnum=cardface(card[i].getFace());
            i++;
            if(i==5) return (cardnum*10+suitnum);
        }
        
        return 0;
    }

    private int straight(Card[]card){
        int cardnum[]=new int[5];
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);

        int i=1;
        while(cardnum[i] == cardnum[i-1]+1){
            //StdOut.println(cardnum[i]);
            if(i==4) return (cardsuit(card[4].getSuit()) + cardnum[4]*10);          
            i++;       
        }

        return 0;
    }
    
    private int pair(Card[]card){
        int cardnum[]=new int[5];
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);
        
        if(cardnum[0]==cardnum[1]){
            if(cardnum[2]==cardnum[3]) return (cardsuit(card[3].getFace()) + cardnum[3]*10+1000);
            else if(cardnum[3]==cardnum[4]) return (cardsuit(card[4].getFace()) + cardnum[4]*10+1000);
            else return (cardsuit(card[1].getFace()) + cardnum[1]*10);
        }
        else if(cardnum[1]==cardnum[2]){
            if(cardnum[3]==cardnum[4]) return (cardsuit(card[4].getSuit()) + cardnum[4]*10+1000);
            else return (cardsuit(card[1].getFace()) + cardnum[1]*10);
        }
        if(cardnum[2]==cardnum[3]) return (cardsuit(card[3].getSuit()) + cardnum[3]*10);
        if(cardnum[3]==cardnum[4]) return (cardsuit(card[4].getSuit()) + cardnum[4]*10);
        
        return 0;
    }
    
}

