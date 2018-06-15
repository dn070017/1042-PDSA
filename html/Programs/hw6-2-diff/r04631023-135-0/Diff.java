
import java.util.Arrays;

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
    
    public double checkhands(Card[] hands){
        double handpoints = 0 ;
        Arrays.sort(hands) ;
        if (hands[0].point == hands[1].point && hands[1].point != hands[2].point){
            if (hands[2].point == hands[3].point && hands[3].point != hands[4].point){
                handpoints = 2*13+hands[3].point+hands[3].plus ; //teo pair
            }else if (hands[2].point == hands[3].point && hands[3].point == hands[4].point){
                handpoints = 5*13+hands[4].point+hands[4].plus ; //full house
            }else if (hands[2].point != hands[3].point && hands[3].point == hands[4].point){
                handpoints = 2*13+hands[4].point+hands[4].plus ; //two pair
            }else if (hands[2].point != hands[3].point && hands[3].point != hands[4].point){
                handpoints = 1*13+hands[1].point+hands[1].plus ; //one pair
            }
        }else if (hands[0].point == hands[1].point && hands[1].point == hands[2].point){
            if (hands[3].point == hands[4].point){
                handpoints = 5*13+hands[2].point+hands[2].plus ; //full house
            }else {
                handpoints = 1*13+hands[2].point+hands[2].plus ; //one pair
            }
        }else if (hands[1].point == hands[2].point && hands[2].point != hands[3].point){
            if (hands[3].point == hands[4].point){
                handpoints = 2*13+hands[4].point+hands[4].plus ; //two pair
            }else{
                handpoints = 1*13+hands[2].point+hands[2].plus ; //one pair
            }
        }else if (hands[1].point == hands[2].point && hands[2].point == hands[3].point){
            handpoints = 1*13+hands[3].point+hands[3].plus ; //one pair
        }else if (hands[2].point == hands[3].point){
            if (hands[3].point == hands[4].point){
                handpoints = 1*13+hands[4].point+hands[4].plus ; //one pair
            }else{
                handpoints = 1*13+hands[3].point+hands[3].plus ; //one pair
            }
        }else if (hands[3].point == hands[4].point){
            handpoints = 1*13+hands[4].point+hands[4].plus ; //one pair
        }else if (hands[0].plus == hands[1].plus && hands[0].plus == hands[2].plus && hands[0].plus == hands[3].plus && hands[0].plus == hands[4].plus){
            handpoints = 4*13+hands[4].point+hands[4].plus ; //flush
            return handpoints ;
        }else if (hands[4].point-hands[3].point ==hands[3].point-hands[2].point && hands[3].point-hands[2].point ==hands[2].point-hands[1].point && hands[2].point-hands[1].point ==hands[1].point-hands[0].point){
            handpoints = 3*13+hands[4].point+hands[4].plus ;//Straight
            return handpoints ;
        }else{
            handpoints = hands[4].point+hands[4].plus ;//highcard
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

