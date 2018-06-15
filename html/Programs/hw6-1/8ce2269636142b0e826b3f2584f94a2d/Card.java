import java.util.Comparator;

public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
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

        int i=0;
        int[] f={0,0},s={0,0};
        for(Card c=this;c!=that;c=that){
            switch (c.getFace()){
                case ""A"":f[i]=14;break;
                case ""J"":f[i]=11;break;
                case ""Q"":f[i]=12;break;
                case ""K"":f[i]=13;break;
                default:f[i]=Integer.parseInt(c.getFace());
            }
            switch (c.getSuit()){
                case ""Spades"":s[i]=99;break;
                case ""Hearts"":s[i]=98;break;
                case ""Diamonds"":s[i]=97;break;
                case ""Clubs"":s[i]=96;break;
            }
            i++;
        }
        if(f[0]!=f[1]){
            return f[0]-f[1];
        }else return s[0]-s[1];
    }

    SuitOrder c = new SuitOrder();

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            // complete this function so the Card can be sorted according to the suit

            int i=0;
            int[] f={0,0},s={0,0};
            for(Card c=c1;c!=c2;c=c2){
                switch (c.getFace()){
                    case ""A"":f[i]=14;break;
                    case ""J"":f[i]=11;break;
                    case ""Q"":f[i]=12;break;
                    case ""K"":f[i]=13;break;
                    default:f[i]=Integer.parseInt(c.getFace());
                }
                switch (c.getSuit()){
                    case ""Spades"":s[i]=99;break;
                    case ""Hearts"":s[i]=98;break;
                    case ""Diamonds"":s[i]=97;break;
                    case ""Clubs"":s[i]=96;break;
                }
                i++;
            }
            return s[0]-s[1];

        }
    }   
}

