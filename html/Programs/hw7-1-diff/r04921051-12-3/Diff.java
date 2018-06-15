import java.util.Arrays;

public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] card){
        cards = card;
    }

    // TODO
    public int compareTo(Hand that) {
.
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        if(play2rank(this) > play2rank(that)) return 1;
        else if(play2rank(this) < play2rank(that)) return -1;
        else return 0;
               
    }

      // Do not modified this function
    public Card[] getCards() { return this.cards; }
    
public static int play2rank(Hand c){
    int result = 0;
//================ Check Pairs =====================    

    int pi = 5;
    for(int i=4;i > 1;i--){
        int a = face2num(c.cards[i]);
        int b = face2num(c.cards[i-1]);        
        if(a == b){
            result = 1;
            pi = i;
        }    
    }

    if(result == 1){
        for(int i=0;i < 4;i++){
        int a = face2num(c.cards[i]);
        int b = face2num(c.cards[i+1]);        
        if(a == b && !(i==pi) && !(i+1 == pi)){
            result = 2;
        }    
        }        
    }    
 


//================ Check Strait ==================== 
    int strait = 1;
    for(int i=0;i < 4;i++){
        int a = face2num(c.cards[i]);
        int b = face2num(c.cards[i+1]);
        if(a + 1 == b);
        else{
            strait = 0;
        }
    }
    if(strait == 1) result = 4;
//================ Check Flush =====================    
    int flush = 1;
    for(int i=1;i < 5;i++){
        if(c.cards[0].getSuit().equals(c.cards[i].getSuit())==false){
            flush = 0;
        }
    }
    
    if(flush == 1) result = 5;

//================ Check Full House =================      
    
    if(c.cards[0].getFace().equals(c.cards[1].getFace()) && c.cards[1].getFace().equals(c.cards[2].getFace()) && c.cards[3].getFace().equals(c.cards[4].getFace())) result = 6;
    
    if(c.cards[4].getFace().equals(c.cards[3].getFace()) && c.cards[3].getFace().equals(c.cards[2].getFace()) && c.cards[1].getFace().equals(c.cards[0].getFace())) result = 6;   
  
      
    

    
    
    return result;
    }

    public static int face2num(Card c){
        switch(c.getFace()){
            case(""A""):
                return 13;
            case(""J""):
                return 10;
            case(""Q""):
                return 11;
            case(""K""):
                return 12;
            default:
                return (Integer.parseInt(c.getFace())-1);
        }
    }
        
    public static int suit2num(Card c){
        switch(c.getSuit()){
            case(""Spades""):
                return 4;
            case(""Hearts""):
                return 3;
            case(""Diamonds""):
                return 2;
            case(""Clubs""):
                return 1;
            default:
                return 0;
        }   
    } 
    
}

