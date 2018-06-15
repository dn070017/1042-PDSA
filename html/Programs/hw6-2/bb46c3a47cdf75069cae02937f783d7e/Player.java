import java.util.Comparator;
import java.util.Arrays;

public class Player implements Comparable<Player>{ 
    private static final int HIGH_CARDS = 0;
    private static final int ONE_PAIR = 1;
    private static final int TWO_PAIRS = 2;
    private static final int STRIGHT = 3;
    private static final int FLUSH = 4;
    private static final int FULL_HOUSE = 5;

    private Card[] cards = new Card[5];
    private String name;
    public int[] faceCounting = new int[15];           // pity for define A as 14, 2 extra Integers
    public int[] suitCounting = new int[4];
    public int cardsPriority;
    public Card maxCard;
     
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
        cardsPriority = cardsToInt(this.cards);
    }
     
    // TODO 
    public int compareTo(Player that) {
.

        if (this.cardsPriority < that.cardsPriority) {
            return -1;
        }
        else if (this.cardsPriority > that.cardsPriority){
            return 1;
        }
        else{
            // TODO 2pairs
            return this.maxCard.compareTo(that.maxCard);
        }
    }

    private int cardsToInt(Card[] cards){
        int[] cardsCount = new int[6];
        int strightFlag = 0;
        int pairFace = 0;
        int fullHouseFace = 0;

        for (int i = 0; i < cards.length; i++){
            ++faceCounting[faceToInt(cards[i].getFace())];
            ++suitCounting[suitToInt(cards[i].getSuit())];
        }

        for (int i = 0; i < suitCounting.length; i++){
            if (suitCounting[i] == 5){
                maxCard = getMaxCard(cards);
                return FLUSH;                                
            }
        }

        for (int i = 0; i < faceCounting.length; i++){
            switch(faceCounting[i]){
                case 1:
                    ++cardsCount[1];
                    break; 
                case 2:
                    ++cardsCount[2];
                    pairFace = i;
                    break;       // pair
                case 3:
                    ++cardsCount[3];
                    fullHouseFace = i;
                    break;       // full house
                case 4:
                    ++cardsCount[4];
                    break;
            }

            if (faceCounting[i] == 1){
                ++strightFlag;
                if (strightFlag == 5) {
                    maxCard = getMaxCard(cards);
                    return STRIGHT;                         
                }
            }
            else{
                strightFlag = 0;
            }
        }

        if (faceCounting[2] == 1 && faceCounting[3] == 1 && faceCounting[4] == 1 && faceCounting[5] == 1 && faceCounting[14] == 1){
            for(int i = 0; i<cards.length; i++){
                if (faceToInt(cards[i].getFace()) == 5){
                    maxCard = cards[i];
                }
            }
            return STRIGHT;
        }


        if (cardsCount[3] == 1 && cardsCount[2] == 1){
            Card[] fullHouseCards = new Card[3];
            int index = 0;

            for (int i = 0; i < cards.length; i++){
                if (faceToInt(cards[i].getFace()) == fullHouseFace){
                    fullHouseCards[index++] = cards[i];
                }
            }
            maxCard = getMaxCard(fullHouseCards);
            return FULL_HOUSE;
        }
        else if(cardsCount[2] == 2){
            Card[] pairCards = new Card[2];
            int index = 0;

            for (int i = 0; i < cards.length; i++) {
                if (faceToInt(cards[i].getFace()) == pairFace){
                    pairCards[index++] = cards[i];
                }
            }
            maxCard = getMaxCard(pairCards);
            return TWO_PAIRS;
        }
        else if(cardsCount[2] == 1){
            Card[] pairCards = new Card[2];
            int index = 0;

            for (int i = 0; i < cards.length; i++) {
                if (faceToInt(cards[i].getFace()) == pairFace){
                    pairCards[index++] = cards[i];
                }
            }
            maxCard = getMaxCard(pairCards);
            return ONE_PAIR;
        }
        else{
            maxCard = getMaxCard(cards);
            return HIGH_CARDS; 
        }
    }

    // don't like nlogn sort, we can actually use linear to get max 
    private Card getMaxCard(Card[] cards){
        Card regCard = cards[0];

        for (int i = 1; i < cards.length; i++){
            if (regCard.compareTo(cards[i]) == -1){
                regCard = cards[i];
            }
        }

        return regCard;
    }

    public static int faceToInt(String face){
        try{
            return Integer.parseInt(face);
        }
        catch(Exception e){
            switch(face){
                case ""A"": return 14;
                case ""K"": return 13;
                case ""Q"": return 12;
                case ""J"": return 11;
            }
        }

        return 0;
    }

    public static int suitToInt(String suit){
        switch(suit){
            case ""Spades"": return 3;
            case ""Hearts"": return 2;
            case ""Diamonds"": return 1;
            case ""Clubs"": return 0;
        }
        return 4;
    }   

}

