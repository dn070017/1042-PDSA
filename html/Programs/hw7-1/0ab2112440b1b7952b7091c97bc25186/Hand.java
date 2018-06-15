
import java.util.Arrays;

/**
 * 請實作一份 Hand 型別。並上傳至作業 7-1。
 * Hand 為五張撲克牌的組合，需要實作 Comparable 介面 (interface)。
 * 此作業中，我們只考慮 
 *     full house > flush > straight > two pair > one pair > high card 
 *     (Reference: http://en.wikipedia.org/wiki/List_of_poker_hands) 
 * 這六種組合。詳細的 api 請參考附件。
 */

/**
 *
 * @author clint
 */
public class Hand implements Comparable<Hand> {

    // sorted by Card value are recommended but not necessary
    private Card[] cards; 

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards){
        Arrays.sort(cards);
        this.cards = cards;
    }

    // TODO
    public int compareTo(Hand that) {
        return 0;
    }

     
    // Do not modified this function
    public Card[] getCards() { return this.cards; }
    
    /**
     * method: hand
     * the main method in the class; it determine the hands of cards
     * @return int[]
     */
    public int[] hand() {
        // first 
        int[] handFace = convertFace();
        int[] handSuit = convertSuit();
        
        // case 01: four kinds (in this case, it belongs to TwoPairs)
        if (sum(handFace) == 6) { 
            int idx = search(handFace, 3);
            return new int[] {
                mapHand(""TwoPairs""),
                mapFace(cards[idx], true),
                mapSuit(cards[idx])}; 
            
        // case02: full house
        } else if (sum(handFace) == 4) { 
            int idx = search(handFace, 2);
            return new int[] {
                mapHand(""fullhouse""),
                mapFace(cards[idx], true),
                mapSuit(cards[idx])}; 
        
        // case03: two pairs
        } else if (sum(handFace) == 2) { 
            int idx = search(handFace, 1);
            return new int[] {
                mapHand(""TwoPairs""),
                mapFace(cards[idx], true),
                mapSuit(cards[idx])}; 
            
        // case04: one pairs  
        } else if (sum(handFace) == 1) { 
            int idx = search(handFace, 1);
            return new int[] {
                mapHand(""OnePairs""),
                mapFace(cards[idx], true),
                mapSuit(cards[idx])}; 
        
        // case05: Flush, Straight or High Card
        } else if (sum(handFace) == 0)    { 
            // case 05-1: Flush (all the suits are the same)
            if (handSuit[4] == 4)         { 
                return new int[] {
                    mapHand(""Flush""),
                    mapFace(cards[4],true),
                    mapSuit(cards[4])};
            
            // case 05-2: Straight (with Ace be the largest card)
            } else if (isStraight(true)) { 
                return new int[] {
                    mapHand(""Straight""),
                    mapFace(cards[4], true),
                    mapSuit(cards[4])}; 
            
            // case 05-3: Straight (the smallest one: A 2 3 4 5)
            } else if (isStraight(false)) { 
                return new int[] {
                    mapHand(""Straight""),
                    5,
                    mapSuit(cards[0])}; 
                
            // case 05-4: High Card   
            } else { 
                return new int[] {
                    mapHand(""HighCard""),
                    mapFace(cards[4], true),
                    mapSuit(cards[4])}; 
            } // end inner if-else
            
        } else { 
            return new int[] {0};    
        } // end outer if-else
    } // end showCards
    
    /**
     * helper method: sum
     * sum all elements in an integer array
     * @param arr
     * @return int
     */
    private int sum(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++)
        { res += arr[i]; }
        return res;
    } // end sum
    
    /**
     * helper method: search
     * the method search the input number in the array
     * return the last match in order to get the largest cards
     * (search from the last item of array)
     * @param arr; input array
     * @param num; query number
     * @return integer 
     */
    private int search(int[] arr, int num) {
        for (int i = 4; i >= 0; i--) {
            if (arr[i] == num) { return i; }
        } // end loop
        return -1;
    } // end search
    
    /**
     * helper method: isStraight
     * determine whether the card is straight or not
     * @param isAce14
     * @return boolean; true if hand of cards is Straight, false otherwise
     */
    public boolean isStraight(boolean isAce14) {
        // initialization
        int[] numFaces = mapFaces(isAce14);
        Arrays.sort(numFaces);
        int res = 0;
        
        // check if face of cards[i] = face of cards[i-1] + 1
        for (int i = 1; i < numFaces.length; i++) {
            if (numFaces[i] - numFaces[i-1] == 1) res++;
        } // end for loop
        
        // return whether the cards is straight or not
        if (res == 4) return true;
        else          return false;
    } // end isStraight
    
    /**
     * helper method: convertFace
     * get sorted card array, convert into integer array
     * if face of cards[i] == face of cards[i-1], set a[i] = a[i-1] + 1
     * if face of cards[i] != face of cards[i-1], set a[i] = 0
     * Note: Face [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
     * @return int[]
     */
    public int[] convertFace() {
        // initialization
        int[] numFaces = mapFaces(true);
        int[] res = new int[cards.length];
        res[0] = 0;
        
        // convert the cards into the result array by face
        for (int i = 1; i < res.length; i++) {
            if (numFaces[i] == numFaces[i-1]) { res[i] = res[i-1] + 1; } 
            else                              { res[i] = 0; }
        } // end for loop
        return res;           
    } // end COnvertFace
    
    /**
     * helper method: convertSuit
     * get sorted card array, convert into integer array
     * if suit of cards[i] == suit of cards[i-1], set a[i] = a[i-1] + 1
     * if suit of cards[i] != suit of cards[i-1], set a[i] = 0
     * note:
     *   1. Spades, Hearts, Diamonds, Clubs
     *   2. the method is targeted for hand ""Flush""
     * @return int[]
     */
    public int[] convertSuit() {
        // initialization
        int[] numSuits = mapSuits();
        int[] res = new int[cards.length];
        res[0] = 0;
        
        // convert the cards into the result array by suit
        for (int i = 1; i < res.length; i++) {
            if (numSuits[i] == numSuits[i-1]) { res[i] = res[i-1] + 1; } 
            else                              { res[i] = 0; }
        } // end for loop
        
        return res;
    } // end convertSuit
    
    /**
     * helper method: mapFaces
     * extension of method mapFace; mapFaces utilize mapFace
     * to map the face of all cards
     * Note: Face [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
     * @param isAce14; should Ace be mapped to 14 or 1 (true -- 14, false -- 1
     * @return int[]
     */
    public int[] mapFaces(boolean isAce14) {
        // initialization 
        int[] face2Num = new int[cards.length];
        
        // map each cards to an integer 
        for (int i = 0; i < face2Num.length; i++) {
            face2Num[i] = mapFace(cards[i], isAce14);
        } // end for loop
        
        return face2Num;
    } // end mapCards
    
    /**
     * helper method: mapSuits
     * extension of method mapSuit; mapSuits utilize mapSuit
     * to map the suit of all cards
     * Note: Spades, Hearts, Diamonds, Clubs
     * @return int[]
     */
    public int[] mapSuits() {
        // initialization
        int[] suit2Num = new int[cards.length];
        
        // map each cards to an integer
        for (int i = 0; i < suit2Num.length; i++) {
            suit2Num[i] = mapSuit(cards[i]);
        } // end for loop
        
        return suit2Num;
    } // end mapCards
    
    
    /**
     * helper method: mapFace
     * convert the face into integer and return
     * Note: Face [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
     * A --- 14 or 1
     * K --- 13
     * Q --- 12
     * J --- 11
     * @param isAce14; should Ace be mapped to 14 or 1 (true -- 14, false -- 1
     * @return Integer
     */
    private Integer mapFace(Card card, boolean isAce14) {
        String cardFace = card.getFace();
        switch (cardFace) {
            // special cards: A K Q J
            case ""A"": if (isAce14) return 14;
                      else         return 1;
            case ""K"": return 13;
            case ""Q"": return 12;
            case ""J"": return 11;
                
            // normal cards: 2~10, convert string to Integer
            default:
                return Integer.valueOf(cardFace);
            } // end switch
    } // end func mapFace
    
    /**
     * helper method: mapSuit
     * return integer that corresponds to the suit
     * note: Spades, Hearts, Diamonds, Clubs
     * Spades   --- 4
     * Hearts   --- 3
     * Diamonds --- 2
     * Clubs    --- 1
     * @return Integer
     */
    private Integer mapSuit(Card card) {
        String cardSuit;
        cardSuit = card.getSuit();
            switch (cardSuit) {
                case ""Spades"":   return 4; // Spades
                case ""Hearts"":   return 3; // Hearts
                case ""Diamonds"": return 2; // Diamonds
                default:         return 1; // Clubs
            } // end switch
    } // end func mapSuit
    
    /**
     * helper method mapHand
     * this function decides the order of hands
     * note: full house > flush > straight > two pair > one pair > high card
     * @param strHand; the type of hand
     * @return integer 
     */
    private int mapHand(String strHand) {
        if      (strHand.equals(""fullhouse"")) { return 5; }
        else if (strHand.equals(""Flush""))     { return 4; }
        else if (strHand.equals(""Straight""))  { return 3; }
        else if (strHand.equals(""TwoPairs""))  { return 2; }
        else if (strHand.equals(""OnePairs""))  { return 1; }
        else if (strHand.equals(""HighCard""))  { return 0; }
        else                              { return -1; }
    } // end func mapHand
}
