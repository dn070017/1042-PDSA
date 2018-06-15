
import java.util.Comparator;

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
        int face1 = 0;
        int suit1 = 0;
        int face2 = 0;
        int suit2 = 0;
        Card max1 = this.cards[0];
        Card max2 = that.cards[0];
        Card min1 = this.cards[0];
        Card min2 = that.cards[0];
        Stack<Integer> this1 = new Stack<Integer>();
        Stack<Integer> that1 = new Stack<Integer>();
        QuickUnionUF uf1 = new QuickUnionUF(5);
        QuickUnionUF uf2 = new QuickUnionUF(5);

        for(int i = 0; i < 5; i ++){
            for(int j = i + 1; j < 5; j ++){
                if(this.cards[i].getFace().equals(this.cards[j].getFace())){
                    uf1.union(j, i);
                }
                if(that.cards[i].getFace().equals(that.cards[j].getFace())){
                    uf2.union(j, i);
                }
            }
        }
        
        for(int i = 0; i < 5; i ++){
            suit1 = 0;
            suit2 = 0;
            for(int j = 0; j < 5; j ++){
                if(max1.compareTo(this.cards[j]) < 0){
                    max1 = this.cards[j];
                }
                if(max2.compareTo(that.cards[j]) < 0){
                    max2 = that.cards[j];
                }
                if(min1.compareTo(this.cards[j]) > 0){
                    min1 = this.cards[j];
                }
                if(min2.compareTo(that.cards[j]) > 0){
                    min2= that.cards[j];
                }
                if(uf1.find(j) == i){
                    face1 ++;
                }
                if(uf2.find(j) == i){
                    face2 ++;
                }
                if(this.cards[i].getSuit().equals(this.cards[j].getSuit())){
                    suit1 ++;
                }
                if(that.cards[i].getSuit().equals(that.cards[j].getSuit())){
                    suit2 ++;
                }
            }
            this1.push(face1);
            that1.push(face2);
            face1 = 0;
            face2 = 0;
        }
        
        int count1 = 0;
        int count2 = 0;
        int thismax = 0;
        int thismin = 0;
        int thatmax = 0;
        int thatmin = 0;
        
        while(!this1.isEmpty()){
            int a = this1.pop();
            if(a >= 2){
                count1 = count1 + a ;
            }
        }

        if(count1 == 4){
            count1 = 2;
        }
        else if(count1 == 3){
            count1 = 2;
        }
        else if(count1 == 2){
            count1 = 1;
        }
        else{
            if(suit1 == 5){
                count1 = 4;
            }
            else if(max1.getFace().equals(""A"") && min1.getFace().equals(""5"")){
                max1 = min1;
                count1 = 3;
            }
            else if(max1.getFace().equals(""J"")){
                thismax = 11;
                thismin = Integer.parseInt(min1.getFace());
            }
            else if(max1.getFace().equals(""Q"")){
                thismax = 12;
                thismin = Integer.parseInt(min1.getFace());
            }
            else if(max1.getFace().equals(""K"")){
                thismax = 13;
                thismin = Integer.parseInt(min1.getFace());
            }
            else{
                thismax = Integer.parseInt(max1.getFace());
                thismin = Integer.parseInt(min1.getFace());
            }
            if((thismax - thismin) == 5){
                count1 = 3;
            }
            else{
                count1 = 0;
            }
        }
        
        while(!that1.isEmpty()){
            int b = that1.pop();
            if(b >= 2){
                count2 = count2 + b ;
            }
        }

        if(count2 == 4){
            count2 = 2;
        }
        else if(count2 == 3){
            count2 = 2;
        }
        else if(count2 == 2){
            count2 = 1;
        }
        else{
            if(suit2 == 5){
                count2 = 4;
            }
            else if(max2.getFace().equals(""A"") && min2.getFace().equals(""5"")){
                max2 = min2;
                count2 = 3;
            }
            else if(max2.getFace().equals(""J"")){
                thatmax = 11;
                thatmin = Integer.parseInt(min2.getFace());
            }
            else if(max2.getFace().equals(""Q"")){
                thatmax = 12;
                thatmin = Integer.parseInt(min2.getFace());
            }
            else if(max2.getFace().equals(""K"")){
                thatmax = 13;
                thatmin = Integer.parseInt(min2.getFace());
            }
            else{
                thatmax = Integer.parseInt(max2.getFace());
                thatmin = Integer.parseInt(min2.getFace());
            }
            if((thatmax - thatmin) == 5){
                count2 = 3;
            }
            else{
                count2 = 0;
            }
        }
        
        if(count1 > count2){
            return +1;
        }
        else if(count1 < count2){
            return -1;
        }
        else{
            if(max1.compareTo(max2) > 0){
                return +1;
            }
            else{
                return -1;
            }
        }
    }
}

