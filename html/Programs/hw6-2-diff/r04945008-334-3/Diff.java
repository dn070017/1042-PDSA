import java.util.Arrays;
import java.util.Comparator; 
public class Player implements Comparable<Player>{ 

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
    
    public int findPair() {
        Arrays.sort(this.cards);
        int[] count = new int[5];
        for (int i = 0; i < 5; i++) {count[i]=0;
            for (int j = 0; j < 5; j++) {
                if(this.cards[i].getFace().equals(this.cards[j].getFace())) count[i]++;
            }
        }
        for(int i = 0; i < 5; i++){
            if(count[i] == 3) return i;
            if(count[i] == 1) return i;
        }
        return -1;
    }
    
    public int getRank() {
        Arrays.sort(this.cards);
        int[] count = new int[5];
        for (int i = 0; i < 5; i++) {count[i]=0;
            for (int j = 0; j < 5; j++) {
                if(this.cards[i].getFace().equals(this.cards[j].getFace())) count[i]++;
            }
        }

        if(count[2] == 3 && (count[0] == 1 || count[4] == 1)) return +15;                                   //fullhouse
        if(this.cards[0].getSuit().equals(this.cards[1].getSuit()) && this.cards[0].getSuit().equals(this.cards[2].getSuit()) && this.cards[0].getSuit().equals(this.cards[3].getSuit()) && this.cards[0].getSuit().equals(this.cards[4].getSuit()))
        return +14; 
        
        if(this.cards[0].getFace().equals(""A"") && this.cards[4].getFace().equals(""10"")) return +13;         //10JQKA
        if(this.cards[0].getFace().equals(""K"") && this.cards[4].getFace().equals(""9""))  return +12;
        if(this.cards[0].getFace().equals(""Q"") && this.cards[4].getFace().equals(""8""))  return +11;
        if(this.cards[0].getFace().equals(""J"") && this.cards[4].getFace().equals(""7""))  return +10;
        if(this.cards[0].getFace().equals(""10"") && this.cards[4].getFace().equals(""6"")) return +9;
        if(this.cards[0].getFace().equals(""9"") && this.cards[4].getFace().equals(""5""))  return +8;
        if(this.cards[0].getFace().equals(""8"") && this.cards[4].getFace().equals(""4""))  return +7;
        if(this.cards[0].getFace().equals(""7"") && this.cards[4].getFace().equals(""3""))  return +6;
        if(this.cards[0].getFace().equals(""6"") && this.cards[4].getFace().equals(""2""))  return +5;
        if(this.cards[0].getFace().equals(""A"") && this.cards[4].getFace().equals(""2""))  return +4;          //A2345
        
        if(count[1] == 2 && count[3] == 2) return +3;                                                       //2 pair
        if(count[0] == 1 || count[1] == 1 || count[2] == 1 || count[3] == 1 || count[4] == 1) return +2;    //1 pair
        return 1;                                                                                           //high card
    }
     
    // TODO 
    public int compareTo(Player that) {
.
        int a,b;
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        if(this.getRank() < that.getRank()) return -1;
        if(this.getRank() > that.getRank()) return +1;
        if(this.getRank() == that.getRank()){
            switch(this.getRank()){
                case 15:
                    a=this.findPair();
                    b=that.findPair();
                    if(this.cards[a].compareTo(that.cards[b]) == 1) return +1; 
                    if(this.cards[a].compareTo(that.cards[b]) == -1) return -1;
                    return 0;
                case 3:
                    a=this.findPair();
                    b=that.findPair();
                    if(this.cards[a].compareTo(that.cards[b]) == 1) return +1; 
                    if(this.cards[a].compareTo(that.cards[b]) == -1) return -1;
                    return 0;
                case 2:
                    a=this.findPair();
                    b=that.findPair();
                    if(this.cards[a].compareTo(that.cards[b]) == 1) return +1; 
                    if(this.cards[a].compareTo(that.cards[b]) == -1) return -1;
                    return 0;
                default:
                    if(this.cards[0].compareTo(that.cards[0]) == 1) return +1; 
                    if(this.cards[0].compareTo(that.cards[0]) == -1) return -1;
                    return 0;
            } 
        }
        return 0;
    }
}


