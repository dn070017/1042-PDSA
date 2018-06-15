
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */
public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private int level;
    private int big;
    
    
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
        Arrays.sort(cards,Card.SUIT_ORDER);
        Arrays.sort(cards);
        
        this.cards = cards;
        this.level = judgeLevel(cards);
    }
    
    public Card[] getCards(){
        return this.cards;
    }
    
    public int getLevel(){
        return this.level;
    }
    
    public int getBig(){
        return this.big;
    }
    
    private static int transFace(String ff){
        int intface = 0;
        if (ff.equals(""J"")){
            intface = 11;
        }
        else if (ff.equals(""Q"")){
            intface = 12;
        }
        else if (ff.equals(""K"")){
            intface = 13;
        }
        else if (ff.equals(""A"")){
            intface = 14;
        }
        else{
            intface = Integer.parseInt(ff);
        }
        return intface;
    }
            
    private int judgeLevel(Card[] cards){
        int out = 0;
        Card C1 = cards[0];
        Card C2 = cards[1];
        Card C3 = cards[2];
        Card C4 = cards[3];
        Card C5 = cards[4];
        if ((C1.getFace().equals(C2.getFace()))&&(C3.getFace().equals(C4.getFace()))&&
                (C4.getFace().equals(C5.getFace()))){
            this.big=4;
            out = 6;
        }
        
        else if ((C4.getFace().equals(C5.getFace()))&&(C1.getFace().equals(C2.getFace()))&&
                (C2.getFace().equals(C3.getFace()))){
            this.big=2;
            out = 6;
        }
        
        else if ((C1.getSuit().equals(C2.getSuit()))&&(C1.getSuit().equals(C3.getSuit()))&&
                (C1.getSuit().equals(C4.getSuit()))&&(C1.getSuit().equals(C5.getSuit()))){
            this.big=4;
            out = 5;
        }
        
        else if ((transFace(C1.getFace())+1==transFace(C2.getFace()))&&
                (transFace(C2.getFace())+1==transFace(C3.getFace()))&&
                (transFace(C3.getFace())+1==transFace(C4.getFace()))){
            if (transFace(C4.getFace())+1==transFace(C5.getFace())){
                this.big=4;
                out = 4;
            }
            else if (transFace(C5.getFace())-12==transFace(C1.getFace())){
                this.big=3;
                out = 4;
            }    
        }
        
        else if ((C5.getFace().equals(C4.getFace()))&&
                ((C3.getFace().equals(C2.getFace()))||
                (C2.getFace().equals(C1.getFace())))){
            this.big=4;
            out = 3;
        }
        
        else if ((C1.getFace().equals(C2.getFace()))&&
                (C3.getFace().equals(C4.getFace()))){
            this.big=3;
            out = 3;
        }
        
        else if (C1.getFace().equals(C2.getFace())){
            this.big=1;
            out = 2;
        }
        
        else if (C2.getFace().equals(C3.getFace())){
            this.big=2;
            out = 2;
        }
                
        else if (C3.getFace().equals(C4.getFace())){
            this.big=3;
            out = 2;
        } 
        
        else if (C4.getFace().equals(C5.getFace())){
            this.big=4;
            out = 2;
        }                
        
        else{
            this.big=4;
            out = 1;
        }
        return out;
    }
            
    // TODO 
    public int compareTo(Player that) {
.
        if (this.level > that.level){
            return 1;
        }
        else if (this.level==that.level){
                        
            Card C1 = this.getCards()[this.getBig()];
            Card C2 = that.getCards()[that.getBig()];
            return C1.compareTo(C2);   
        }
        else{
            return -1;
        }
        //return 0;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Card[] cardsArray = new Card[5];
        /*
        cardsArray[0] = new Card(""A"",""Hearts"");
        cardsArray[1] = new Card(""Q"",""Hearts"");
        cardsArray[2] = new Card(""10"",""Hearts"");
        cardsArray[3] = new Card(""7"",""Hearts"");
        cardsArray[4] = new Card(""2"",""Hearts"");
        */
        /*
        cardsArray[0] = new Card(""A"",""Spades"");
        cardsArray[1] = new Card(""A"",""Hearts"");
        cardsArray[2] = new Card(""A"",""Diamonds"");
        cardsArray[3] = new Card(""2"",""Clubs"");
        cardsArray[4] = new Card(""2"",""Hearts"");
        */
        
        cardsArray[0] = new Card(""A"",""Spades"");
        cardsArray[1] = new Card(""4"",""Hearts"");
        cardsArray[2] = new Card(""4"",""Diamonds"");
        cardsArray[3] = new Card(""3"",""Clubs"");
        cardsArray[4] = new Card(""2"",""Hearts"");
        
        Player P = new Player(""Felix"");
        P.setCards(cardsArray);
        for (int i=0;i<5;i++){
            Card temp = P.getCards()[i];
            System.out.println(temp.getFace());
            System.out.println(temp.getSuit());
            System.out.println(""Next"");
        }
        System.out.println(P.getLevel());
        System.out.println(P.getBig());
    }
}
    
    


