
public class Player implements Comparable<Player> {

    private Card[] cards = new Card[5];
    private String name;
    private int side = 0;

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

    private int facetointeger(String face) {
        int num1 = 0;

        switch (face) {
            case ""A"":
                num1 = 13;
                break;
            case ""K"":
                num1 = 12;
                break;
            case ""Q"":
                num1 = 11;
                break;
            case ""J"":
                num1 = 10;
                break;
            case ""10"":
                num1 = 9;
                break;
            case ""9"":
                num1 = 8;
                break;
            case ""8"":
                num1 = 7;
                break;
            case ""7"":
                num1 = 6;
                break;
            case ""6"":
                num1 = 5;
                break;
            case ""5"":
                num1 = 4;
                break;
            case ""4"":
                num1 = 3;
                break;
            case ""3"":
                num1 = 2;
                break;
            case ""2"":
                num1 = 1;
                break;

        }

        return num1;
    }

    private int isfullhouse(Card[] cards) {
        int a = 0;
        if (cards[0].getFace().equals(cards[1].getFace())) {
            if (cards[1].getFace().equals(cards[2].getFace())) {
                if (cards[3].getFace().equals(cards[4].getFace())) {
                    a = 1;
                    side = 2;
                }
            }
            if (cards[2].getFace().equals(cards[3].getFace())) {
                if (cards[3].getFace().equals(cards[4].getFace())) {
                    a = 1;
                    side = 4;
                }
            }
        }
        return a;
    }

    private int isflush(Card[] cards) {
        int a = 0;
        if (cards[0].getSuit().equals(cards[1].getSuit())) {
            if (cards[1].getSuit().equals(cards[2].getSuit())) {
                if (cards[2].getSuit().equals(cards[3].getSuit())) {
                    if (cards[3].getSuit().equals(cards[4].getSuit())) {
                        a = 1;
                        side = 0;
                    }
                }
            }
        }
        return a;
    }

    private int isstraight(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) - facetointeger(cards[1].getFace()) == 1) {
            if (facetointeger(cards[1].getFace()) - facetointeger(cards[2].getFace()) == 1) {
                if (facetointeger(cards[2].getFace()) - facetointeger(cards[3].getFace()) == 1) {
                    if (facetointeger(cards[3].getFace()) - facetointeger(cards[4].getFace()) == 1) {
                        a = 1;
                    }
                }
            }
        }
        return a;
    }
    private int pairsnumber(Card[] cards){
        int number=0;
         if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace()) ){
             number++;
             side=1;
             if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace()) && facetointeger(cards[2].getFace()) != facetointeger(cards[1].getFace())){
                 number++;
                 side=3;
             }
             if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace()) && facetointeger(cards[2].getFace()) != facetointeger(cards[4].getFace())){
                 number++;
                 side=4;
             }
             
         }
         if(facetointeger(cards[1].getFace())==facetointeger(cards[2].getFace()) ){
             number++;
             side=2;
             if(facetointeger(cards[1].getFace()) != facetointeger(cards[3].getFace()) && facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace())){
                 number++;
                 side=4;
             }
         }
        return number;
    }
    // TODO 
    public int compareTo(Player that) {
        int rank1 = 0;  //    full house(6) > flush(5) > straight(4) > two pair(3) > one pair(2) > high card(1)
        int rank2 = 0;
        int side1 = 0;
        int side2 = 0;
        Insertion.sort( that.cards);
        Insertion.sort( this.cards);

        if (isfullhouse(this.cards) == 1) {
            rank1 = 6;
            side1 = side;
        }else if(isflush(this.cards)==1){
            rank1 = 5;
        }else if(isstraight(this.cards)==1){
            rank1 = 4;
        }else if(pairsnumber(this.cards)==2){
            rank1=3;
            side1=side;
        }else if(pairsnumber(this.cards)==1){
            rank1=2;
            side1=side;
        }else{
            rank1=1;
        }
        
        if (isfullhouse(that.cards) == 1) {
            rank2 = 6;
            side2 = side;
        }else if(isflush(that.cards)==1){
            rank2 = 5;
        }else if(isstraight(that.cards)==1){
            rank2 = 4;
        }else if(pairsnumber(that.cards)==2){
            rank2=3;
            side2=side;
        }else if(pairsnumber(that.cards)==1){
            rank2=2;
            side2=side;
        }else{
            rank2=1;
        }
        if(rank1>rank2) return 1;
        if(rank1<rank2) return -1;
        if(rank1==rank2){
            if(rank1==6) {
                if(Card.SUIT_ORDER.compare(this.cards[side1], this.cards[side2])==1) return 1;
                else return -1;
            }
            if(rank1==5){
                if(this.cards[4].compareTo(that.cards[4])==1) return 1;
                else return -1;
            }
            if(rank1==4){
                if(this.cards[4].compareTo(that.cards[4])==1) return 1;
                else return -1;
            }
            if(rank1==3 || rank1==2){
                 if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
                else return -1;
            }
            if(rank1==1){
                if(this.cards[4].compareTo(that.cards[4])==1) return 1;
                else return -1;
            }
            
        }
.
        return 0;
    }
}

