public class Card {
    
	private final String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private final String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
    }
     
    // DO NOT MODIFY THIS   
    public String getFace(){
        return this.face;
    }
    
    // DO NOT MODIFY THIS    
    public String getSuit(){
        return this.suit;
    }   
    
    // TODO
    public int compareTo(Card that) {
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
        Integer[] A_face = new Integer[1] ;
        Integer[] B_face = new Integer[1] ;
        if(this.getFace().equalsIgnoreCase(""A""))
            A_face[0] = 14 ;
        else if(this.getFace().equalsIgnoreCase(""K""))
            A_face[0] = 13 ;
        else if(this.getFace().equalsIgnoreCase(""Q""))
            A_face[0] = 12 ;
        else if(this.getFace().equalsIgnoreCase(""J""))
            A_face[0] = 11 ;
        else 
            A_face[0] = Integer.parseInt(this.getFace());
        
        if(that.getFace().equalsIgnoreCase(""A""))
            B_face[0] = 14 ;
        else if(that.getFace().equalsIgnoreCase(""K""))
            B_face[0] = 13 ;
        else if(that.getFace().equalsIgnoreCase(""Q""))
            B_face[0] = 12 ;
        else if(that.getFace().equalsIgnoreCase(""J""))
            B_face[0] = 11 ;
        else
            B_face[0] = Integer.parseInt(that.getFace());
        
        if(A_face[0] > B_face[0])
            return +1 ;
        else if(A_face[0] < B_face[0])
            return -1 ;
        
        else if(Objects.equals(A_face[0], B_face[0])){
        if(SUIT_ORDER.compare(this,that) == -1)
           return -1 ;
        else if(SUIT_ORDER.compare(this, that) == 1)
           return +1 ;
        else 
           return 0;
        }
           return 0;
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit
            String a = c1.getSuit();
            String b = c2.getSuit();
            if (a.equalsIgnoreCase(""Spades"")){
               if(b.equalsIgnoreCase(""Spades""))
                   return 0 ;
               else if(b.equalsIgnoreCase(""Hearts""))
                   return +1 ;
               else if(b.equalsIgnoreCase(""Diamonds""))
                   return +1 ;
               else if(b.equalsIgnoreCase(""Clubs""))
                   return +1 ;
             }
            else if (a.equalsIgnoreCase(""Hearts"")){
                if(b.equalsIgnoreCase(""Spades""))
                    return -1 ;
                else if(b.equalsIgnoreCase(""Hearts""))
                    return 0;
                else if(b.equalsIgnoreCase(""Diamonds""))
                    return +1;
                else if(b.equalsIgnoreCase(""Clubs""))
                    return +1;                
            }
            else if(a.equalsIgnoreCase(""Diamonds"")){
                if (b.equalsIgnoreCase(""Spades""))
                    return -1;
                else if(b.equalsIgnoreCase(""Hearts""))
                    return -1;
                else if(b.equalsIgnoreCase(""Diamonds""))
                    return 0;
                else if(b.equalsIgnoreCase(""Clubs""))
                    return +1;
            }
            else if(a.equalsIgnoreCase(""Clubs"")){
                if (b.equalsIgnoreCase(""Spades""))
                    return -1;
                else if(b.equalsIgnoreCase(""Hearts""))
                    return -1;
                else if(b.equalsIgnoreCase(""Diamonds""))
                    return -1;
                else if(b.equalsIgnoreCase(""Clubs""))
                    return 0;
            }
            return 0;
        }
    }   

    /*public static void main(String[] args) {
        Card[] test = new Card[2];
        test[0] = new Card(""10"",""Diamonds"") ;
        test[1] = new Card(""10"",""Hearts"") ;
        System.out.println(test[1].compareTo(test[0]));
        System.out.println(SUIT_ORDER.compare(test[1],test[0]));
    }*/
    
}

