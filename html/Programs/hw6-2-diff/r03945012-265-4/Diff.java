
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * 1042 PDSA
 * hw06_Player
 * @author Robert
 */


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

        Card[] PlayerOneCards = this.cards;
        Arrays.sort(PlayerOneCards);
        String[] Cardone = FiveCard(PlayerOneCards);
        
        Card[] PlayerTwoCards = that.cards;
        Arrays.sort(PlayerTwoCards);
        String[] Cardtwo = FiveCard(PlayerTwoCards);
        
        String CardType = ""full_house, flush, straight, two_pair, one_pair, highcard"";
        int card_compare;
        card_compare = CardType.indexOf(Cardone[0])-CardType.indexOf(Cardtwo[0]);
        if (card_compare>0){
            return -1;
        }
        else if (card_compare<0){
            return 1;
        }
        else{
            String Number = ""2,3,4,5,6,7,8,9,10,J,Q,K,A"";
            int bigcard_compare;
            bigcard_compare = Number.indexOf(Cardone[1])-Number.indexOf(Cardtwo[1]);
            if (bigcard_compare>0)
                return 1;
            else if (bigcard_compare<0)
                return -1;
            else{
                String Color = ""Clubs, Diamonds, Hearts, Spades"";
                int bigcolor_compare;
                bigcolor_compare = Color.indexOf(Cardone[2])-Color.indexOf(Cardtwo[2]);
                if (bigcolor_compare>0)
                    return 1;
                else if (bigcolor_compare<0)
                    return -1;
                else
                    return 0;
            }
        }
    }
    
    // full_house > flush > straight > two_pair > one_pair > highcard
    // full house: 11122
    // two pair: 11223
    // one pair: 11234
    private static String[] FiveCard(Card[] fivecards){
        String[] result = new String[3];
        String[] cards_number= new String[5];
        for (int i = 0; i<5; i++)
            cards_number[i] = fivecards[i].getFace();
        Set<String> temp = new HashSet<String>();
        for (String element : cards_number){
             temp.add(element);
        }
        int non_duplicate = temp.size();
        switch(non_duplicate){
            case 5:  // flush or straight or highcard
                // flush or not
                if (fivecards[0].getSuit().equals(fivecards[1].getSuit()) &&
                    fivecards[0].getSuit().equals(fivecards[2].getSuit()) &&
                    fivecards[0].getSuit().equals(fivecards[3].getSuit()) &&
                    fivecards[0].getSuit().equals(fivecards[4].getSuit())){
                    result[0] = ""flush"";
                    result[1] = fivecards[0].getFace();
                    result[2] = fivecards[0].getSuit();
                    break;
                }
                // straight or not
                String Number = ""2,3,4,5,6,7,8,9,10,J,Q,K,A"";
                int face_compare;
                for (int i = 0; i<4; i++){
                    face_compare = Number.indexOf(cards_number[i+1])-Number.indexOf(cards_number[i]);
                    if (face_compare > 4 || face_compare < 2){
                        break;
                    }
                    else if (i == 3){
                        result[0] = ""straight"";
                        result[1] = fivecards[4].getFace();
                        result[2] = fivecards[4].getSuit();
                        break;
                    }           
                }
                if (""2"".equals(cards_number[0]) && ""3"".equals(cards_number[1]) &&
                    ""4"".equals(cards_number[2]) && ""5"".equals(cards_number[3]) &&
                    ""A"".equals(cards_number[4])){
                    result[0] = ""straight"";
                    result[1] = fivecards[3].getFace();
                    result[2] = fivecards[3].getSuit();
                    break;
                }
                // highcard here
                result[0] = ""highcard"";
                result[1] = fivecards[4].getFace();
                result[2] = fivecards[4].getSuit();
                break;
            case 4: // one pair here
                temp.clear();
                int index_1 = 0;
                for (int i=0; i<5; i++){
                    temp.add(cards_number[i]);
                    if (temp.add(cards_number[i]) == false)
                        index_1 = i;
                }
                result[0] = ""one_pair"";
                result[1] = fivecards[index_1].getFace();
                result[2] = fivecards[index_1].getSuit();
                break;
            case 3: // two pair here
                temp.clear();
                int index_2 = 0;
                for (int i=2; i<5; i++){
                    temp.add(cards_number[i]);
                    if (temp.add(cards_number[i]) == false)
                        index_2 = i;
                }
                result[0] = ""two_pair"";
                result[1] = fivecards[index_2].getFace();
                result[2] = fivecards[index_2].getSuit();
                break;
            case 2: // full_house here
            case 1:
                result[0] = ""full_house"";
                result[1] = fivecards[2].getFace();
                result[2] = fivecards[2].getSuit();
                break;
        }
        return result;
    }
}


