import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
        Card[] thatcards;
        int x=get_cardsvalue(this.cards);
        int y=get_cardsvalue(that.cards);
       if(x>y) return 1;
        else if(x<y)return -1;
        else return this.cards[0].compareTo(that.cards[0]);
    }
    private int get_cardsvalue(Card[] cards){
        Arrays.sort(cards,Collections.<Card>reverseOrder());
        for(int i=0;i<5;i++)
            System.out.println(cards[i].toString());
        if(check_fullhouse(cards)) return 6;
        else if(check_flush(cards)) return 5;
        else if(check_straight(cards)) return 4;
        else if(check_2pair(cards)) return 3;
        else if(check_1pair(cards)) return 2;
        else return 1;
    }
    private boolean check_fullhouse(Card[] cards){
        if(cards[0].getFace().equals(cards[1].getFace())&cards[1].getFace().equals(cards[2].getFace())&cards[3].getFace().equals(cards[4].getFace())){
            return true;
        }else if(cards[2].getFace().equals(cards[3].getFace())&cards[3].getFace().equals(cards[4].getFace())&cards[0].getFace().equals(cards[1].getFace())){
            Card temp=cards[0];
            cards[0]=cards[2];
            cards[2]=temp;
            return true;
        }
        else return false;
    }
    private boolean check_flush(Card[] cards){
        if(cards[0].getSuit().equals(cards[1].getSuit())&cards[1].getSuit().equals(cards[2].getSuit())&cards[2].getSuit().equals(cards[3].getSuit())&
                cards[3].getSuit().equals(cards[4].getSuit())){
            return true;
        }
        else
            return false;
    }
    private boolean check_straight(Card[] cards){
        int[] c=new int[5];
        for(int i=0;i<5;i++){
            if(cards[i].getFace().equals(""A"")){c[i] =14;}
            else if(cards[i].getFace().equals(""K"")){c[i] = 13;}
            else if(cards[i].getFace().equals(""Q"")){c[i] = 12;}
            else if(cards[i].getFace().equals(""J"")){c[i] = 11;}
            else {c[i] = Integer.parseInt(cards[i].getFace());}
        }
        if(c[0]-c[1]==1&c[1]-c[2]==1&c[2]-c[3]==1&c[3]-c[4]==1){
            return true;
        }else if(c[0]==14&c[1]==5&c[2]==4&c[3]==3&c[4]==2){
            Card temp=cards[0];
            cards[0]=cards[1];
            cards[1]=temp;
            return true;
        }else return false;
    }
    private boolean check_2pair(Card[] cards){
        if(cards[0].getFace().equals(cards[1].getFace())&cards[2].getFace().equals(cards[3].getFace())){
            return true;
        }else if(cards[0].getFace().equals(cards[1].getFace())&cards[3].getFace().equals(cards[4].getFace())){
            return true;
        }else if(cards[1].getFace().equals(cards[2].getFace())&cards[3].getFace().equals(cards[4].getFace())){
            Card temp=cards[0];
            cards[0]=cards[1];
            cards[1]=temp;
            return true;
        }else return false;
    }
    private boolean check_1pair(Card[] cards){
        if(cards[0].getFace().equals(cards[1].getFace())){
            return true;
        }else if(cards[1].getFace().equals(cards[2].getFace())){
            Card temp=cards[0];
            cards[0]=cards[1];
            cards[1]=temp;
            return true;
        }else if(cards[2].getFace().equals(cards[3].getFace())){
            Card temp=cards[0];
            cards[0]=cards[2];
            cards[2]=temp;
            return true;
        }else if(cards[3].getFace().equals(cards[4].getFace())){
            Card temp=cards[0];
            cards[0]=cards[3];
            cards[3]=temp;
            return true;
        }else return false;
    }



}


