
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author clint
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
        Arrays.sort(cards);
        this.cards = cards;
    }
     
    // TODO 
    public int compareTo(Player that) {
.
        int[] thisHand = this.hand();
        int[] thatHand = that.hand();
        for (int i = 0; i < thisHand.length; i++) {
            if      (thisHand[0] < thatHand[0]) {return -1;}
            else if (thisHand[0] > thatHand[0]) {return  1;}
            else                                {continue; }
        } // end loop
        return 0;
    }
    
    // my implementation
    public Card[] getCards() {
        return cards;
    } // end getCards
    
    public int[] mapFaces(boolean isAce) {
        // 
        int[] face2Num = new int[cards.length];
        
        // map each cards to a 
        for (int i = 0; i < face2Num.length; i++) {
            face2Num[i] = cards[i].mapFace(isAce);
        } // end for loop
        
        return face2Num;
    } // end mapCards
    
    public int[] mapSuits() {
        // 
        int[] suit2Num = new int[cards.length];
        
        // map each cards to a 
        for (int i = 0; i < suit2Num.length; i++) {
            suit2Num[i] = cards[i].mapSuit();
        } // end for loop
        
        return suit2Num;
    } // end mapCards
    
    public int[] convertFace() {
        int[] numFaces = mapFaces(true);
        int[] res = new int[cards.length];
        res[0] = 0;
        
        for (int i = 1; i < res.length; i++) {
            if (numFaces[i] == numFaces[i-1]) { res[i] = res[i-1] + 1; } 
            else                              { res[i] = 0; }
        } // end for loop
        return res;           
    } // end COnvertFace
    
    public int[] convertSuit() {
        //Arrays.sort(this.cards, Card.SUIT_ORDER);
        int[] numSuits = mapSuits();
        int[] res = new int[cards.length];
        res[0] = 0;
        
        for (int i = 1; i < res.length; i++) {
            if (numSuits[i] == numSuits[i-1]) { res[i] = res[i-1] + 1; } 
            else                              { res[i] = 0; }
        } // end for loop
        
        //Arrays.sort(this.cards);
        return res;
    } // end convertSuit
    
    public boolean isStraight(boolean isAce) {
        int res = 0;
        int[] numFaces = mapFaces(isAce);
        Arrays.sort(numFaces);
        
        for (int i = 1; i < numFaces.length; i++) {
            if (numFaces[i] - numFaces[i-1] == 1) res++;
        }
        if (res == 4) return true;
        else          return false;
    } // end isStraight
    
    public int[] hand() {
        int[] handFace = convertFace();
        int[] handSuit = convertSuit();
        
        // check fullhouse
        if (sum(handFace) == 6) { 
            int idx = search(handFace, 3);
            return new int[] {
                mapHand(""TwoPairs""),
                cards[idx].mapFace(true),
                cards[idx].mapSuit()}; 
            
        } else if (sum(handFace) == 4) { 
            int idx = search(handFace, 2);
            return new int[] {
                mapHand(""fullhouse""),
                cards[idx].mapFace(true),
                cards[idx].mapSuit()}; 
            
        } else if (sum(handFace) == 2) { 
            int idx = search(handFace, 1);
            return new int[] {
                mapHand(""TwoPairs""),
                cards[idx].mapFace(true),
                cards[idx].mapSuit()}; 
          
        } else if (sum(handFace) == 1) { 
            int idx = search(handFace, 1);
            return new int[] {
                mapHand(""OnePairs""),
                cards[idx].mapFace(true),
                cards[idx].mapSuit()}; 
        
        } else if (sum(handFace) == 0)    { 
            if (handSuit[4] == 4)         { 
                return new int[] {
                    mapHand(""Flash""),
                    cards[4].mapFace(true),
                    cards[4].mapSuit()};
                
            } else if (isStraight(true)) { 
                return new int[] {
                    mapHand(""Straight""),
                    cards[4].mapFace(true),
                    cards[4].mapSuit()}; 
                
            } else if (isStraight(false)) { 
                return new int[] {
                    mapHand(""Straight""),
                    5,
                    cards[0].mapSuit()}; 
                
            } else { 
                return new int[] {
                    mapHand(""HighCard""),
                    cards[4].mapFace(true),
                    cards[4].mapSuit()}; 
            } // end inner if-else
            
        } else { 
            return new int[] {0};    
        } // end outer if-else
    } // end showCards
    
    public String printCards() {
        String res = """";
        res += (cards[0].getSuit() + ""_"" + cards[0].getFace());
        
        for(int i = 1; i < 5; i++){
            res += ("","" + cards[i].getSuit() + ""_"" + cards[i].getFace());
        } // end for loop
        
        return res;
    } // end printCards
    
    private int sum(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++)
        { res += arr[i]; }
        return res;
    } // end sum
    
    private int search(int[] arr, int num) {
        for (int i = 4; i >= 0; i--) {
            if (arr[i] == num) { return i; }
        } // end loop
        return -1;
    } // end search
    
    private int mapHand(String str) {
        if      (str.equals(""fullhouse"")) { return 5; }
        else if (str.equals(""Flash""))     { return 4; }
        else if (str.equals(""Straight""))  { return 3; }
        else if (str.equals(""TwoPairs""))  { return 2; }
        else if (str.equals(""OnePairs""))  { return 1; }
        else if (str.equals(""HighCard""))  { return 0; }
        else                              { return -1; }
    }
    
    public static void main(String[] args) {
        System.out.println(""Poker hand"");
        Player[] players = new Player[15];
        players[0]  = new Player(""Player01"");
        players[1]  = new Player(""Player02"");
        players[2]  = new Player(""Player03"");
        players[3]  = new Player(""Player04"");
        players[4]  = new Player(""Player05"");
        players[5]  = new Player(""Player06"");
        players[6]  = new Player(""Player07"");
        players[7]  = new Player(""Player08"");
        players[8]  = new Player(""Player09"");
        players[9]  = new Player(""Player10"");
        players[10] = new Player(""Player11"");
        players[11] = new Player(""Player12"");
        
        String[] cardStrs = new String[15];
        cardStrs[0] = ""Spades_9,Hearts_9,Diamonds_9,Spades_2,Hearts_2""; // fullhouse
        cardStrs[1] = ""Spades_4,Hearts_4,Diamonds_4,Spades_A,Hearts_A""; // fullhouse
        cardStrs[2] = ""Spades_10,Spades_9,Spades_Q,Spades_J,Spades_8"";  // flash
        cardStrs[3] = ""Hearts_10,Hearts_9,Hearts_Q,Hearts_J,Hearts_8"";  // flash
        cardStrs[4] = ""Spades_10,Hearts_K,Hearts_Q,Hearts_J,Clubs_A"";   // largest straight
        cardStrs[5] = ""Spades_A,Hearts_5,Hearts_2,Hearts_3,Clubs_4"";    // smallest straight
        cardStrs[6] = ""Spades_2,Hearts_2,Hearts_A,Clubs_A,Hearts_8"";    // two pairs
        cardStrs[7] = ""Spades_8,Hearts_8,Hearts_8,Clubs_8,Hearts_A"";    // two pairs
        cardStrs[8] = ""Spades_8,Hearts_9,Hearts_Q,Hearts_J,Hearts_8"";   // one pairs
        cardStrs[9] = ""Spades_3,Hearts_3,Hearts_A,Hearts_J,Hearts_K"";   // one pairs
        cardStrs[10] = ""Spades_8,Hearts_Q,Hearts_A,Hearts_J,Hearts_7"";  // high card
        cardStrs[11] = ""Spades_8,Hearts_9,Hearts_Q,Hearts_J,Hearts_2"";  // high card
        
        for (int i = 0; i < 12; i++) {
            System.out.println(""Initialize player "" + i);
            Card[]   cardsArray = new Card[5];
            String[] cardStr = cardStrs[i].split("","");
            
            for(int j = 0; j < 5; j++){
                String[] sep = cardStr[j].split(""_"");
                Card card = new Card(sep[1], sep[0]);
                cardsArray[j] = card;
            } // end for
            
            players[i].setCards(cardsArray);
        }
        System.out.println(""=========================================="");
        int idx;
        System.out.println(""Player01, Player02 fullHouse"");
        idx = 0;
        System.out.println(""--- Player01 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        idx = 1;
        System.out.println(""--- Player02 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        System.out.println(""=========================================="");
        System.out.println(""Player03, Player04 flash"");
        idx = 2;
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        idx = 3;
        System.out.println(""--- Player04 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
       System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        System.out.println(""=========================================="");
        
        System.out.println(""Player05, Player06 Straight"");
        idx = 4;
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        idx = 5;
        System.out.println(""--- Player06 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        System.out.println(""=========================================="");
        System.out.println(""Player07, Player08 Two Pairs"");
        idx = 6;
        System.out.println(""--- Player07 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        idx = 7;
        System.out.println(""--- Player08 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        System.out.println(""=========================================="");
        System.out.println(""Player09, Player10 One Pair"");
        idx = 8;
        System.out.println(""--- Player09 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        idx = 9;
        System.out.println(""--- Player10 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        System.out.println(""=========================================="");
        System.out.println(""Player11, Player12 high card"");
        idx = 10;
        System.out.println(""--- Player11 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        idx = 11;
        System.out.println(""--- Player12 ---"");
        System.out.println(""Cards       "" + players[idx].printCards());
        System.out.println(""convertFace "" + Arrays.toString(players[idx].convertFace()));
        System.out.println(""convertSuit "" + Arrays.toString(players[idx].convertSuit()));
        System.out.println(""Hand        "" + Arrays.toString(players[idx].hand()));
        System.out.println(""=========================================="");
    } // end main
}

