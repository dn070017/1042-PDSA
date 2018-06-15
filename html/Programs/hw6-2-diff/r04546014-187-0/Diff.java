import java.util.Arrays;
import static java.util.Arrays.sort;


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
     
    // TODO 
    public int compareTo(Player that) {
.
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        int thisCardCombination=this.cardsConbination(this.cards);
        int thatCardCombination=that.cardsConbination(that.cards);
        if(thisCardCombination>thatCardCombination) return 1;
        if(thisCardCombination<thatCardCombination) return -1;
        else{
            return 0;
        }
    }
    public int cardsConbination(Card[] card){
            if(card[0].suitCompare(card)) return 5;//同花
            if(Card.stright(card)) return 4;//順子
            else return Card.howManyPair(card);//蘆、2pairs、1pair、雜牌
    }

}
