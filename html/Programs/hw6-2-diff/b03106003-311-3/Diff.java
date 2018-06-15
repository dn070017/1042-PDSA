
import java.util.Arrays;

public class Player implements Comparable<Player>{ 

    public Card[] cards = new Card[5];
    private String name;
    int mainCardIndex = 4;
     
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
        // complete this function so the Player can be sorted according to the cards he/she has
        int ans = 0;
        mainCardIndex = 4;
        Arrays.sort(cards);
        if(this.getOrder(cards) > that.getOrder(cards)){
            ans = 1;
        }else if(this.getOrder(cards) < that.getOrder(cards)){
            ans = -1;
        }else if(this.getOrder(cards) == that.getOrder(cards)){
            ans = cards[mainCardIndex].compareTo(that.cards[that.mainCardIndex]);  
        }
        return ans;
    }
    
    //new
    public int getOrder(Card[] card){
    int flag = 0;
    //1 High Card
    //2 1-Pair
    //3 2-Pair
    //4 Straight
    //5 Flush
    //6 Full House
    if(full_house(card)){
        flag = 6;
    }else if(flush(card)){
        flag = 5;
    }else if(straight(card)){
        flag = 4;
    }else if(two_pare(card)){
        flag = 3;
    }else if(one_pare(card)){
        flag = 2;
    }else{
        flag = 1;
    }
    return flag;
    }
    public boolean full_house(Card[] card){
        boolean flag = false;
        // use three_of_a_kind and two_pare because 1 1 1 5 5  can also be seen as a two_pare
        if(two_pare(card)&&three_of_a_kind(card) ){
            flag = true;
        }
        return flag;
        
    }
    public boolean flush(Card[] card){
        boolean flag = false;
        if(card[0].getSuit() == card[1].getSuit() && card[1].getSuit() == card[2].getSuit() && card[2].getSuit() == card[3].getSuit() && card[3].getSuit() == card[4].getSuit()){
            flag = true;
            mainCardIndex = 4;
        }
        return flag;
    }
    public boolean straight(Card[] card){
        boolean flag = false;    
            if(getnumber(card[0].getFace())+1 == getnumber(card[1].getFace())&&getnumber(card[1].getFace())+1 == getnumber(card[2].getFace())
                    &&getnumber(card[2].getFace())+1 == getnumber(card[3].getFace())
                    &&getnumber(card[3].getFace())+1 == getnumber(card[4].getFace())
                    ){
                flag = true;
                mainCardIndex = 4;
            }else if(getnumber(card[4].getFace())== 14 &&getnumber(card[4].getFace())== 2){
                if(getnumber(card[0].getFace())+1 == getnumber(card[1].getFace())&&getnumber(card[1].getFace())+1 == getnumber(card[2].getFace())
                    &&getnumber(card[2].getFace())+1 == getnumber(card[3].getFace())
                    ){
                flag = true;
                mainCardIndex = 3;
            }
            }
        return flag;
    }        
    public boolean three_of_a_kind(Card[] card){
        boolean flag = false;
        if(getnumber(card[0].getFace()) == getnumber(card[1].getFace()) && getnumber(card[1].getFace()) == getnumber(card[2].getFace())){
            flag = true;
            mainCardIndex = 2;
        }if(getnumber(card[1].getFace()) == getnumber(card[2].getFace()) && getnumber(card[2].getFace()) == getnumber(card[3].getFace())){
            flag = true;
            mainCardIndex = 3;
        }if(getnumber(card[2].getFace()) == getnumber(card[3].getFace()) && getnumber(card[3].getFace()) == getnumber(card[4].getFace())){
            mainCardIndex = 4;
        }
        return flag;
    }
    public boolean two_pare(Card[] card){
        boolean flag = false;
        if(getnumber(card[0].getFace()) == getnumber(card[1].getFace())&& getnumber(card[2].getFace()) == getnumber(card[3].getFace())){
            flag = true;
            mainCardIndex = 3;
        }else if(getnumber(card[0].getFace()) == getnumber(card[1].getFace())&&getnumber(card[3].getFace()) == getnumber(card[4].getFace())){
            flag = true;
            mainCardIndex = 4;
        }else if(getnumber(card[1].getFace()) == getnumber(card[2].getFace())&&getnumber(card[3].getFace()) == getnumber(card[4].getFace())){
            flag = true;
            mainCardIndex = 4;
        }
        return flag;
    }
    public boolean one_pare(Card[] card){
        boolean flag = false;
        if(getnumber(card[0].getFace()) == getnumber(card[1].getFace())){
            flag = true;
            mainCardIndex = 1;
        }else if(getnumber(card[1].getFace()) == getnumber(card[2].getFace())){
            flag = true;
            mainCardIndex = 2;
        }else if(getnumber(card[2].getFace()) == getnumber(card[3].getFace())){
            flag = true;
            mainCardIndex = 3;
        }else if(getnumber(card[3].getFace()) == getnumber(card[4].getFace())){
            flag = true;
            mainCardIndex = 4;
        }
        return flag;
    }
    static private int getnumber(String s){
        int flag = 0;
        switch (s){
            case""A"":
                flag = 14;
                break;
            case""2"":
                flag = 2;
                break;            
            case""3"":
                flag = 3;
                break;                
            case""4"":
                flag = 4;
                break;        
            case""5"":
                flag = 5;
                break; 
            case""6"":
                flag = 6;
                break;
            case""7"":
                flag = 7;
                break;
            case""8"":
                flag = 8;
                break;
            case""9"":
                flag = 9;
                break;
            case""10"":
                flag = 10;
                break;
            case""J"":
                flag = 11;
                break;
            case""Q"":
                flag = 12;
                break;
            case""K"":
                flag = 13;
                break;
                default:
                System.out.println(""Wrong!"");
                break;
        }
        return flag;
    
    }
    
}

