import java.util.Arrays;

 public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private String[] checkFace = new String[]{""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
    private String[] checkSuit = new String[]{""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};
    private Card[] checkCardsFace = new Card[13] ;
    private Card[] checkCardsSuit = new Card[4] ;
    
    
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
    
    public double[][] checkhandspoint(Card[] cards){
        double[][] checkhandspoint = new double[cards.length][2] ;
        
        for (int i = 0; i < 13; i++) {
            Card card = new Card(checkFace[i], ""Clubs"");
            checkCardsFace[i] = card;
        }
        for (int i = 0; i < 4; i++) {
            Card card = new Card(""1"", checkSuit[i]);
            checkCardsSuit[i] = card;
        }
        
        
        for(int k = 0; k < cards.length; k++){
            for (int i = 0; i < 13; i++) {
            if (cards[k].compareTo(checkCardsFace[i])== 0) {
                checkhandspoint[k][0] = i;
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (Card.SUIT_ORDER.compare(checkCardsSuit[i], cards[k])== 0) {
                checkhandspoint[k][1] = i;
                checkhandspoint[k][1] = checkhandspoint[k][1] /10 ;
                break;
            }
        }
        }
        
        return checkhandspoint ;
    }
    
    public double checkhands(Card[] hands){
        double handpoints = 0 ;
        Arrays.sort(hands) ;
        double[][] check = checkhandspoint(hands) ;
        if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] == check[3][0] && check[3][0] != check[4][0]){
            handpoints = 2*13+check[3][0]+check[3][1] ; //two pair
            return handpoints ;
        }else if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]){
            handpoints = 2*13+check[4][0]+check[4][1] ; //two pair
            return handpoints ;
        }else if (check[0][0] != check[1][0] && check[1][0] == check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]){
            handpoints = 2*13+check[4][0]+check[4][1] ; //two pair
            return handpoints ;
        }else if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] == check[3][0] && check[3][0] == check[4][0]){
            handpoints = 5*13+check[4][0]+check[4][1] ; //full house
            return handpoints ;
        }else if (check[0][0] == check[1][0] && check[1][0] == check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]){
            handpoints = 5*13+check[2][0]+check[2][1] ; //full house
            return handpoints ;
        }else if (check[0][1] == check[1][1] && check[0][1] == check[2][1] && check[0][1] == check[3][1] && check[0][1] == check[4][1]){
            handpoints = 4*13+check[4][0]+check[4][1] ; //flush
            return handpoints ;
        }else if (check[4][0]-check[3][0] ==check[3][0]-check[2][0] && check[3][0]-check[2][0] ==check[2][0]-check[1][0] && check[2][0]-check[1][0] ==check[1][0]-check[0][0]){
            handpoints = 3*13+check[4][0]+check[4][1] ;//Straight
            return handpoints ;
        }else if (check[0][0] != check[1][0] && check[1][0] != check[2][0] && check[2][0] != check[3][0] && check[3][0] != check[4][0]){
            handpoints = check[4][0]+check[4][1] ; //high card
            return handpoints ;
        }else if(check[3][0] == check[4][0]){
            handpoints = 1*13+check[4][0]+check[4][1] ; //one pair
            return handpoints ;
        }else if(check[2][0] == check[3][0]){
            handpoints = 1*13+check[3][0]+check[3][1] ; //one pair
            return handpoints ;
        }else if(check[1][0] == check[2][0]){
            handpoints = 1*13+check[2][0]+check[2][1] ; //one pair
            return handpoints ;
        }else if(check[0][0] == check[1][0]){
            handpoints = 1*13+check[1][0]+check[1][1] ; //one pair
            return handpoints ;
        }
        return handpoints ;
    }
     
    // TODO 
    public int compareTo(Player that) {
        if (checkhands(this.cards) > checkhands(that.cards)){
            return 1;
        }else{
            return -1;
        }
    }
}

