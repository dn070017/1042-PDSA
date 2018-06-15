"r04945025","0.088","104112","@559cb12c3293f8885cd003f15629d6e4@public class Player implements Comparable<Player> {
    /* 
        Hand:
        5: Full House
        4: Flush
        3: Straight
        2: Two Pairs
        1: One Pairs
        0: High Cards
    */
    
    private Card[] cards = new Card[5];
    private String name;
    private int    hand;
    private Card   bestCard;
    
    public Player(String name) {
        this.name = name;
    }
     
    public String getName() {
        return this.name;
    }
    
    public int getHand(){
        return this.hand;
    }
    
    public Card getBestCard(){
        return this.bestCard;
    }
    
    public void InsertionSort(Card[] cards){
        for(int i = 0; i < cards.length; i++){
            for(int j = i; j > 0; j--){
                if(cards[j].compareTo(cards[j-1]) < 0){
                    Card tmp   = cards[j];
                    cards[j]   = cards[j-1];
                    cards[j-1] = tmp;
                }
                else{
                    break;
                }
            }
        }
    }
    
    private int suit2Int(String suit){
        switch(suit){
            case ""Clubs"":
                return 0;
            case ""Diamonds"":
                return 1;
            case ""Hearts"":
                return 2;
            case ""Spades"":
                return 3;
            default:
                throw new IllegalArgumentException();
        }
    }
    
    private int face2Int(String face){
        switch(face){
            case ""J"":
                return 11;
            case ""Q"":
                return 12;
            case ""K"":
                return 13;
            case ""A"":
                return 14;
            default:
                return Integer.parseInt(face);
        }
    }

    public void setCards(Card[] cards) {
        int[] faceCount  = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] suitNumber = {0, 0, 0, 0, 0, 0};
        int[] faceNumber = {0, 0, 0, 0, 0};
        int[] suitCount  = {0, 0, 0, 0};
        InsertionSort(cards);
        this.cards = cards;
        
        for(int i = 0; i < 5; i++){
            int suit = suit2Int(cards[i].getSuit());
            int face = face2Int(cards[i].getFace()) - 2;
            suitCount[suit] += 1;
            faceCount[face] += 1;
        }
        for(int i = 0; i < 4; i++){
            suitNumber[suitCount[i]] += 1;
        }
        for(int i = 0; i < 13; i++){
            faceNumber[faceCount[i]] += 1;
        }

        // Full House
        if(faceNumber[3] == 1 && faceNumber[2] == 1){
            int bestCardFace = -1;
            this.hand = 5;
            for(int i = 0; i < 13; i++){
                if(faceCount[i] == 3){
                    bestCardFace = i + 2;
                    break;
                }
            }
            for(int i = 4; i >= 0; i--){
                if(face2Int(cards[i].getFace()) == bestCardFace){
                    this.bestCard = cards[i];
                    return;
                }
            }
        }
        
        // Flush
        else if(suitNumber[5] == 1){
            this.hand = 4;
            this.bestCard = cards[4];
            return;
        }
        
        // Straight and High Card
        else if(faceNumber[1] == 5){
            int count = 5;
            int index = 0;
            int[] straight = new int[5];
            for(int i = 0; i < 13; i++){
                if(faceCount[i] == 1){
                    straight[index++] = i;
                }
            }
            if(straight[4] == 12 && straight[0] == 0){
                count = 4;
            }
            int tmp = 1;
            for(int i = 0; i < 4; i++){
                if(straight[i + 1] - straight[i] == 1){
                    tmp++;
                }
                else{
                    break;
                }
            }
            if(tmp == count){
                this.hand = 3;
                if(count == 5){
                    this.bestCard = cards[4];
                    return;
                }
                else{
                    this.bestCard = cards[3];
                    return;
                }
            }
            else{
                this.hand = 0;
                this.bestCard = cards[4];
                return;
            }
        }
        
        // Two Pairs
        else if(faceNumber[2] == 2){
            int bestCardFaceA = -1;
            int bestCardFaceB = -1;
            int i;
            this.hand = 2;
            for(i = 0; i < 13; i++){
                if(faceCount[i] == 2){
                    bestCardFaceA = i + 2;
                    break;
                }
            }
            for(i = i + 1; i < 13; i++){
                if(faceCount[i] == 2){
                    bestCardFaceB = i + 2;
                    break;
                }
            }
            for(i = 4; i >= 0; i--){
                int face = face2Int(cards[i].getFace());
                if(face == bestCardFaceA || face == bestCardFaceB){
                    this.bestCard = cards[i];
                    return;
                }
            }
        }
        
        // One Pair
        else if(faceNumber[2] == 1){
            int bestCardFace = -1;
            this.hand = 1;
            for(int i = 0; i < 13; i++){
                if(faceCount[i] == 2){
                    bestCardFace = i + 2;
                    break;
                }
            }
            for(int i = 4; i >= 0; i--){
                if(face2Int(cards[i].getFace()) == bestCardFace){
                    this.bestCard = cards[i];
                    return;
                }
            }
        }
        
        // High Card
        else{
            // System.out.printf(""Some Error Occurs\n"");
        }
        // System.out.printf(""%s: %s, %s-%s\n"", this.name, this.hand, this.bestCard.getSuit(), this.bestCard.getFace());
        return;
    }
    
    public int compareTo(Player that) {
        int handA = this.getHand();
        int handB = that.getHand();
        if(handA > handB){
            return +1;
        }
        else if(handA == handB){
            return this.getBestCard().compareTo(that.getBestCard());
        }
        else{
            return -1;
        }
    }
}


"r04447001","0.09","105232","@469b255785601302e434ca0b4fc0149a@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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
        int[] counttable1 = new int[15];
        int[] counttable2 = new int[15];
        int[] suittable1 = new int[4];
        int[] suittable2 = new int[4];
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);

        for (int i = 0; i < this.cards.length; i++) {
            if (this.cards[i].getFace().equalsIgnoreCase(""A"")) {
                counttable1[14]++;
            } else if (this.cards[i].getFace().equalsIgnoreCase(""K"")) {
                counttable1[13]++;
            } else if (this.cards[i].getFace().equalsIgnoreCase(""Q"")) {
                counttable1[12]++;
            } else if (this.cards[i].getFace().equalsIgnoreCase(""J"")) {
                counttable1[11]++;
            } else {
                counttable1[Integer.parseInt(this.cards[i].getFace())]++;
            }

            if (this.cards[i].getSuit().equalsIgnoreCase(""Spades"")) {
                suittable1[3]++;
            } else if (this.cards[i].getSuit().equalsIgnoreCase(""Hearts"")) {
                suittable1[2]++;
            } else if (this.cards[i].getSuit().equalsIgnoreCase(""Diamonds"")) {
                suittable1[1]++;
            } else {
                suittable1[0]++;
            }
        }//making table for p1

        for (int i = 0; i < that.cards.length; i++) {
            if (that.cards[i].getFace().equals(""A"")) {
                counttable2[14]++;
            } else if (that.cards[i].getFace().equalsIgnoreCase(""K"")) {
                counttable2[13]++;
            } else if (that.cards[i].getFace().equalsIgnoreCase(""Q"")) {
                counttable2[12]++;
            } else if (that.cards[i].getFace().equalsIgnoreCase(""J"")) {
                counttable2[11]++;
            } else {
                counttable2[Integer.parseInt(that.cards[i].getFace())]++;
            }

            if (that.cards[i].getSuit().equalsIgnoreCase(""Spades"")) {
                suittable2[3]++;
            } else if (that.cards[i].getSuit().equalsIgnoreCase(""Hearts"")) {
                suittable2[2]++;
            } else if (that.cards[i].getSuit().equalsIgnoreCase(""Diamonds"")) {
                suittable2[1]++;
            } else {
                suittable2[0]++;
            }
        }//making table for p2

        int isfull_house1 = 0;
        int isfull_house2 = 0;
        int maxfull1 = 0;
        int maxfull2 = 0;
        for (int i = 0; i < counttable1.length; i++) {
            if (counttable1[i] == 3) {
                maxfull1 = i;
                for (int j = 0; j < counttable1.length; j++) {
                    if (counttable1[j] == 2) {
                        isfull_house1 = 1;
                    }
                }
            }
        }
        for (int i = 0; i < counttable2.length; i++) {
            if (counttable2[i] == 3) {
                maxfull2 = i;
                for (int j = 0; j < counttable2.length; j++) {
                    if (counttable2[j] == 2) {
                        isfull_house2 = 1;
                    }
                }
            }
        }
        if (isfull_house1 > isfull_house2) {
            return 1;
        }
        if (isfull_house1 < isfull_house2) {
            return -1;
        }
        if (isfull_house1 == 1 && isfull_house2 == 1) {
            if(maxfull1 > maxfull2) return 1;
            if(maxfull1 < maxfull2) return -1;
        }// for full house

        int isflush1 = 0;
        int isflush2 = 0;
        for (int i = 0; i < suittable1.length; i++) {
            if (suittable1[i] == 5) {
                isflush1 = 1;
            }
        }
        for (int i = 0; i < suittable2.length; i++) {
            if (suittable2[i] == 5) {
                isflush2 = 1;
            }
        }

        if (isflush1 > isflush2) {
            return 1;
        }
        if (isflush1 < isflush2) {
            return -1;
        }
        if (isflush1 == 1 && isflush2 == 1) {
            return this.cards[4].compareTo(that.cards[4]);
        }// for flush     

        int isstraight1 = 0;
        int islittle1 = 0;
        int isstraight2 = 0;
        int islittle2 = 0;
        for (int i = 0; i < (counttable1.length - 4); i++) {
            if (counttable1[i] == 1) {
                if (counttable1[(i + 1)] == 1 && counttable1[(i + 2)] == 1 && counttable1[(i + 3)] == 1 && counttable1[(i + 4)] == 1) {
                    isstraight1 = 1;
                    break;
                }
            }
        }
        if(counttable1[2] == 1 && counttable1[3] == 1 && counttable1[4] == 1 && counttable1[5] == 1 && counttable1[14] == 1){
            isstraight1 = 1;
            islittle1 = 1;
        }//account for A 2 3 4 5
        
        for (int i = 0; i < (counttable2.length - 4); i++) {
            if (counttable2[i] == 1) {
                if (counttable2[(i + 1)] == 1 && counttable2[(i + 2)] == 1 && counttable2[(i + 3)] == 1 && counttable2[(i + 4)] == 1) {
                    isstraight2 = 1;
                    break;
                }    
            }
        }
        if(counttable2[2] == 1 && counttable2[3] == 1 && counttable2[4] == 1 && counttable2[5] == 1 && counttable2[14] == 1){
            isstraight2 = 1;
            islittle2 = 1;
        }//account for A 2 3 4 5

        if (isstraight1 > isstraight2) {
            return 1;
        }
        if (isstraight1 < isstraight2) {
            return -1;
        }
        if (isstraight1 == 1 && isstraight2 == 1) {
            if(islittle1 > islittle2) return -1;
            if(islittle1 < islittle2) return 1;
            if(islittle1 == 1 && islittle2 == 1){
                return this.cards[3].compareTo(that.cards[3]);
            }
            return this.cards[4].compareTo(that.cards[4]);
        }// for flush        

        int count = 0;
        int istwopairs1 = 0;
        int istwopairs2 = 0;
        int maxpair1 = 0;
        int maxpair2 = 0;
        int pairs1 = 0;
        int pairs2 = 0;
        
        Card[] maxcard1 = new Card[2];
        for (int i = 0; i < counttable1.length; i++) {
            if (counttable1[i] == 2) {
            pairs1++;
            maxpair1 = i;               
            }
        }

        if (pairs1 == 2) {
            istwopairs1 = 1;
            String number = String.valueOf(maxpair1);
            if(number.equals(""11"")) number = ""J"";
            if(number.equals(""12"")) number = ""Q"";
            if(number.equals(""13"")) number = ""K"";
            if(number.equals(""14"")) number = ""A"";
            for(int j = 0; j < this.cards.length; j++){
                if(this.cards[j].getFace().equalsIgnoreCase(number))
                maxcard1[count++] = this.cards[j]; 
            }
            Arrays.sort(maxcard1);
        }
        
        count = 0;
        Card[] maxcard2 = new Card[2];
        
        for (int i = 0; i < counttable2.length; i++) {
            if (counttable2[i] == 2) {
                pairs2++;
                maxpair2 = i;
            }
        }
        
        if (pairs2 == 2) {
            istwopairs2 = 1;
            String number = String.valueOf(maxpair2);
            if(number.equals(""11"")) number = ""J"";
            if(number.equals(""12"")) number = ""Q"";
            if(number.equals(""13"")) number = ""K"";
            if(number.equals(""14"")) number = ""A"";
            for(int j = 0; j < that.cards.length; j++){
                if(that.cards[j].getFace().equalsIgnoreCase(number))
                maxcard2[count++] = that.cards[j]; 
            }
            Arrays.sort(maxcard2);
        }

        if (istwopairs1 > istwopairs2) {
            return 1;
        }
        if (istwopairs1 < istwopairs2) {
            return -1;
        }
        if (istwopairs1 == 1 && istwopairs2 == 1) {
            return maxcard1[1].compareTo(maxcard2[1]);
        }// for flush        
        
        
        int ispairs1 = 0;
        int ispairs2 = 0;

        count = 0;
        if (pairs1 == 1) {
            ispairs1 = 1;
            String number = String.valueOf(maxpair1);
            if(number.equals(""11"")) number = ""J"";
            if(number.equals(""12"")) number = ""Q"";
            if(number.equals(""13"")) number = ""K"";
            if(number.equals(""14"")) number = ""A"";
            for(int j = 0; j < this.cards.length; j++){
                if(this.cards[j].getFace().equalsIgnoreCase(number))
                maxcard1[count++] = this.cards[j]; 
            }
            Arrays.sort(maxcard1);
        }

        count = 0;       
        if (pairs2 == 1) {
            ispairs2 = 1;
            String number = String.valueOf(maxpair2);
            if(number.equals(""11"")) number = ""J"";
            if(number.equals(""12"")) number = ""Q"";
            if(number.equals(""13"")) number = ""K"";
            if(number.equals(""14"")) number = ""A"";
            for(int j = 0; j < that.cards.length; j++){
                if(that.cards[j].getFace().equalsIgnoreCase(number))
                maxcard2[count++] = that.cards[j]; 
            }
            Arrays.sort(maxcard2);
        }
        
        if (ispairs1 > ispairs2) {
            return 1;
        }
        if (ispairs1 < ispairs2) {
            return -1;
        }
        if (ispairs1 == 1 && ispairs2 == 1) {
            return maxcard1[1].compareTo(maxcard2[1]);
        }// for flush         
.
        return this.cards[4].compareTo(that.cards[4]);
    }
}

"r04921051","0.09","107568","@62003afeb04de462800cbdd37b7ac8fb@
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
/*
關於作業 6-2，如果有手牌的等級是一樣的 (e.g 同樣拿到 1-Pair) 判斷的基準如下：
1. High Card：檢查五張牌中最大的
2. 1-Pair：檢查形成 Pair 的兩張牌中最大的
3. 2-Pair：檢查行程 2-Pair 的四張牌中最大的
4. Straight：檢查五張牌中最大張的（除了 A 2 3 4 5 是最小的以外）
5. Flush：檢查五張牌中最大張的
6. Full House：檢查 3 張數字相同的牌中最大的
不需要考慮同花順，如果有同花順的情況發生，當作同花判斷就好。
*/
    
    // TODO 
    public int compareTo(Player that) {
.
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        if(play2rank(this) > play2rank(that)) return 1;
        else if(play2rank(this) < play2rank(that)) return -1;
        else{
            if(play2rank(this)==0) return this.cards[4].compareTo(that.cards[4]);
            if((play2rank(this)==1)||(play2rank(this)==2)){

                int pi=5;
                int pj=5;
                 for(int i=0;i < 4;i++){
                    int a = face2num(this.cards[i]);
                    int b = face2num(this.cards[i+1]);        
                    if(a == b){
                             pi = i;
                    }    
                  } 
                 
                 for(int i=0;i < 4;i++){
                    int a = face2num(that.cards[i]);
                    int b = face2num(that.cards[i+1]);        
                    if(a == b){
                             pj = i;
                    }    
                  } 
                //System.out.println(""(""+this.cards[pi+1].getSuit()+"",""+this.cards[pi+1].getFace()+"") (""+that.cards[pi+1].getSuit()+"",""+that.cards[pi+1].getFace()+"")"");
                 return this.cards[pi+1].compareTo(that.cards[pj+1]); 
            }
                
            if(play2rank(this)==4) return this.cards[4].compareTo(that.cards[4]);
            
            
            return 0;
        }             

    }
    
    public static int play2rank(Player c){
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

"r04942099","0.092","105712","@fb365efc1bc0f0a7213748ff686d3c23@
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
                        side = 4;
                    }
                }
            }
        }
        return a;
    }

    private int isstraight(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) - facetointeger(cards[1].getFace()) == -1) {
            if (facetointeger(cards[1].getFace()) - facetointeger(cards[2].getFace()) == -1) {
                if (facetointeger(cards[2].getFace()) - facetointeger(cards[3].getFace()) == -1) {
                    if (facetointeger(cards[3].getFace()) - facetointeger(cards[4].getFace()) == -1) {
                        a = 1;
                    }
                }
            }
        }
        return a;
    }
     private int pairsnumber2(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 3;
            }
            if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace()) ){
                a=1;
                side = 4;
            }
        }
        if(facetointeger(cards[2].getFace())==facetointeger(cards[1].getFace()) && facetointeger(cards[4].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 4;
            
        }
                  
        return a;
    }
        
        private int pairsnumber1(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            a=1;
            side = 1;
            if(facetointeger(cards[0].getFace())==facetointeger(cards[2].getFace()) ) side = 2;
        }else if(facetointeger(cards[1].getFace())==facetointeger(cards[2].getFace())  ){
            a=1;
            side = 2;
            if(facetointeger(cards[1].getFace())==facetointeger(cards[3].getFace()) ) side = 3;
        }else if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace())  ){
            a=1;
            side = 3;
            if(facetointeger(cards[2].getFace())==facetointeger(cards[4].getFace()) ) side = 4;
        }else if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace())  ){
            a=1;
            side = 4;
        }
                  
        return a;
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
        }else if(pairsnumber2(this.cards)==1){
            rank1=3;
            side1=side;
        }else if(pairsnumber1(this.cards)==1){
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
        }else if(pairsnumber2(that.cards)==1){
            rank2=3;
            side2=side;
        }else if(pairsnumber1(that.cards)==1){
            rank2=2;
            side2=side;
        }else{
            rank2=1;
        }
        if(rank1>rank2) return 1;
        if(rank1<rank2) return -1;
        if(rank1==rank2){
            if(rank1==6) {
                if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
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
            if(rank1==3 ){
                 if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
                else return -1;
            }
            if(rank1==2){
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

"r04921094","0.09","105296","@bb46c3a47cdf75069cae02937f783d7e@import java.util.Comparator;
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

"b03611023","0.096","106992","@07a12bf93819adde1e4aa85ee56053cb@import java.util.Arrays;
public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private String[] copy = {""0"",""1"",""2"",""3"",""4"",""5"",""6"",""7"",""8"",""9"",""10"", ""J"", ""Q"", ""K"", ""A""};
    private String[] Suit = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""}; 
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
      public int findnum2(Card card){
        
        for(int j =0;j<16;j++){
         if(card.getFace().equals(copy[j]))
             return j;
        }
        return -1;
    }
     public int findsuit2(Card card){
        for(int j = 0; j<4;j++){
            if(card.getSuit().equals(Suit[j]))
                return j;
        }
        return -1;
    }
    // TODO 
    public int compareTo(Player that) {
        int a,b;
        
        this.cards = this.reang(this.cards);
        that.cards = that.reang(that.cards);
        
        a = this.findtype(this.cards);
        b = that.findtype(that.cards);
        
        if(a>b)
            return 1;
        else if(a<b)
                return -1;
        else{
            if(a==5){
                Card aa;
                Card bb;
                aa = this.confullhouse(this.cards);
                bb = that.confullhouse(that.cards);
               if(this.findnum2(aa)>this.findnum2(bb))
                    return 1;
                if(this.findnum2(aa)<this.findnum2(bb))
                    return -1;
                else{
                    if(this.findsuit2(aa)>this.findsuit2(bb))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==4){
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return 1;
                if(this.findnum2(this.cards[4])<that.findnum2((that.cards[4])))
                    return -1;
                else{
                    if(this.findsuit2(this.cards[4])>that.findsuit2((that.cards[4])))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==3){
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return 1;
                if(this.findnum2(this.cards[4])<that.findnum2((that.cards[4])))
                    return -1;
                else{
                    if(this.findsuit2(this.cards[4])>that.findsuit2((that.cards[4])))
                        return 1;
                    else 
                        return -1;
                }
                
            }
            if(a==2){
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return 1;
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return -1;
                else{
                    if(this.findsuit2(this.cards[4])>that.findsuit2((that.cards[4])))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==1){
                Card aa;
                Card bb;
                aa = this.contwopair(this.cards);
                bb = that.contwopair(that.cards);
               if(this.findnum2(aa)>this.findnum2(bb))
                    return 1;
                if(this.findnum2(aa)<this.findnum2(bb))
                    return -1;
                else{
                    if(this.findsuit2(aa)>this.findsuit2(bb))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==0){
                Card aa;
                Card bb;
                aa = this.conpair(this.cards);
                bb = that.conpair(that.cards);
               if(this.findnum2(aa)>this.findnum2(bb))
                    return 1;
                if(this.findnum2(aa)<this.findnum2(bb))
                    return 1;
                else{
                    if(this.findsuit2(aa)>this.findsuit2(bb))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==-1){
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return 1;
                if(this.findnum2(this.cards[4])<that.findnum2((that.cards[4])))
                    return -1;
                else{
                    if(this.findsuit2(this.cards[4])>that.findsuit2((that.cards[4])))
                        return 1;
                    else 
                        return -1;
                }
            }
        }        
        
        
.
        return 0;
    }
    public Card[] reang(Card[] cards){
       // Card[] after = new Card[5];
        for(int i = 0;i<5;i++){
            int min = i;
            for(int j = i+1;j<5;j++){
                if(this.findnum2(cards[min])>this.findnum2(cards[j])){
                    Card temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
                else if(this.findnum2(cards[min])==this.findnum2(cards[j]) &&
                        this.findsuit2(cards[min])>this.findsuit2(cards[j])){
                    Card temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
                    
            }
        }
        
        return cards;
    }
    public int findtype(Card[] cards){       
        int[] fa = new int[5];
        int[] su = new int[5];       
        for(int i =0 ;i<5;i++){
            fa[i] =this.findnum2(cards[i]);            
            su[i] =this.findsuit2(cards[i]);
        }        
                
        if(fa[0] ==fa[1] && fa[0] == fa[2] && fa[3] == fa[4])
            return 5;
        else if(fa[0] ==fa[1] && fa[2] == fa[3] && fa[2] == fa[4])
            return 5;
        else if(su[0] == su[1] && su[0] == su[2] && su[0] == su[3] && su[0] == su[4])
            return 4;
        else if(fa[0]+1==fa[1]&&fa[0]+2==fa[2]&&fa[0]+3==fa[3]
                &&fa[0]+4==fa[4])
            return 3;
        else if(fa[4]==14&&fa[3]==13&&fa[2]==12&&fa[1]==11&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==13&&fa[2]==12&&fa[1]==3&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==13&&fa[2]==4&&fa[1]==3&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==5&&fa[2]==4&&fa[1]==3&&fa[0]==2)
            return 2;
         else if(fa[0]==fa[1]&&fa[2]==fa[3])
             return 1;
         else if(fa[0]==fa[1]&&fa[3]==fa[4])
             return 1;
         else if(fa[1]==fa[2]&&fa[3]==fa[4])
             return 1;
         else if(fa[0]==fa[1])
             return 0;
         else if(fa[1]==fa[2])
             return 0;
         else if(fa[2]==fa[3])
             return 0;
         else if(fa[3]==fa[4])
             return 0;
        else
             return -1;
    }
    
     public Card confullhouse(Card[] that){
          int[] fa = new int[5];
          for(int i =0 ;i<5;i++){
            fa[i] = this.findnum2(that[i]);                     
        } 
          if(fa[0]==fa[1]&&fa[0]==fa[2])
              return that[2];
          else
         return that[4];
     }
     public Card contwopair(Card[] that){
         int[] fa = new int[5];
          for(int i =0 ;i<5;i++){
             fa[i] = this.findnum2(that[i]);                    
        } 
          if(fa[2]==fa[3])
              return that[3];
          else
              return that[4];
     }
     public Card conpair(Card[] that){
          int[] fa = new int[5];
          for(int i =0 ;i<5;i++){
            fa[i] = this.findnum2(that[i]);                     
        } 
          if(fa[0]==fa[1])
              return that[1];
          else if(fa[1]==fa[2])
              return that[2];
          else if(fa[2]==fa[3])
              return that[3];
          else
              return that[4];
     }
    
   public static void main(String[] args){
       
     } 
}


"r04921104","0.088","104336","@62534dd00abcb80cde96ebfaed484698@
import java.util.Arrays;

public class Player implements Comparable<Player> {

    private Card[] cards = new Card[5];
    private String name;
    private int cardstype;

    private static int getfacecode(Card c) {
        switch (c.getFace()) {
            case ""A"":
                return 14;
            case ""K"":
                return 13;
            case ""Q"":
                return 12;
            case ""J"":
                return 11;
            default:
                return Integer.parseInt(c.getFace());
        }
    }

    private static int getsuitcode(Card c) {
        switch (c.getSuit()) {
            case ""Spades"":
                return 4;
            case ""Hearts"":
                return 3;
            case ""Diamonds"":
                return 2;
            case ""Clubs"":
                return 1;
            default:
                return 0;
        }
    }

    //full house = 5... high card = 0
    public int findcardstype() {

        int difffacecount = 1;
        for (int i = 0; i < 4; i++) {
            if (getfacecode(this.cards[i]) != getfacecode(this.cards[i + 1])) {
                difffacecount++;
            }
        }
        if (difffacecount == 2) {
            return 5; // full house
        }
        if (difffacecount == 3) {
            for (int i = 0; i < 3; i++) {
                if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1]) && getfacecode(this.cards[i + 1]) == getfacecode(this.cards[i + 2])) {
                    return 1; // 3 of a kind, equal to 1 pair
                }
            }
            return 2; // 2 pairs
        }
        if (difffacecount == 4) {
            return 1; // 1 pair
        }

        for (int i = 0; i < 4; i++) {
            if (getsuitcode(this.cards[i]) != getsuitcode(this.cards[i + 1])) {
                break;
            }
            if (i == 3) {
                return 4; // flush
            }
        }
        for (int i = 0; i < 4; i++) {
            if (getfacecode(this.cards[i]) + 1 != getfacecode(this.cards[i + 1])) {
                if (i == 3 && getfacecode(this.cards[i + 1]) == 14) {
                    return 3; // 2345A
                }
                break;
            }
            if (i == 3) {
                return 3; // straight         
            }
        }

        return 0;// high card
    }

    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }

    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
        Arrays.sort(this.cards);
        this.cardstype = this.findcardstype();
    }

    // TODO 
    public int compareTo(Player that) {
.

        if (this.cardstype < that.cardstype) {
            return -1;
        }
        if (this.cardstype > that.cardstype) {
            return +1;
        }

        //  
        switch (this.cardstype) {
            case 0: // high card
                return this.cards[4].compareTo(that.cards[4]);
            case 1: // 1 pair
                return this.find_onepair_max().compareTo(that.find_onepair_max());
            case 2: // 2 pairs
                return this.find_twopairs_max().compareTo(that.find_twopairs_max());
            case 3: // straight
                if (getsuitcode(this.cards[0]) == 2 && getsuitcode(that.cards[0]) == 10) {
                    return -1; //2345A & 10JQKA
                } else if (getsuitcode(this.cards[0]) == 10 &&getsuitcode(that.cards[0]) == 2) {
                    return +1; //10JQKA & 2345A
                } else if (getsuitcode(this.cards[0]) == 2 && getsuitcode(that.cards[0]) == 2) {
                    return this.cards[3].compareTo(that.cards[3]); // 2345A & 2345A, compare 4th card
                } else {
                    return this.cards[4].compareTo(that.cards[4]);
                }
            case 4: // flush
                return this.cards[4].compareTo(that.cards[4]);
            case 5: // full house
                return this.find_fullhouse_max().compareTo(that.find_fullhouse_max());
        }
        return 0;
    }

    private Card find_onepair_max() {
        for (int i = 0; i < 4; i++) {
            if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1])) {
                return this.cards[i + 1];
            }
        }
        return null;
    }

    private Card find_twopairs_max() {
        for (int i = 2; i < 4; i++) {
            if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1])) {
                return this.cards[i + 1];
            }
        }
        return null;
    }

    private Card find_fullhouse_max() {
        for (int i = 0; i < 3; i++) {
            if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1]) &&getfacecode(this.cards[i + 1]) == getfacecode(this.cards[i + 2])) {
                return this.cards[i + 2];
            }
        }
        return null;
    }
}

"r03723070","0.09","105360","@b26d8fc6d579166c7acec2a0cebf0ab3@import java.util.Arrays;
public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private int [] playerrank = new int[5];//[pair,Full_House,flush,stright,,bignumber]
    private int playerorder = 0;//[kind,big#]
    private Card playerbig;
    private String [] Oface = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"",""A""};
    private int [] cardfacesize = new int [5];
        int pair = 0;
        int stright = 1;
        Card bigstright=cards[4];
        int flush = 1;
        Card bigflush = cards[4];
        
        int FullHouse =0;
        Card bigFullHouse;
        
        Card big2p;
        
        int kind = 0;//(FullHouse=5,flush=4,stright=3,2pair=2,1pair=1,HC=0)
    
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
        //High Card
        //
        //System.out.println(cards[0].getFace());
        for (int i = 0;i<13;i++){
            for(int j = 0;j<5;j++){
                if(Oface[i].equals(cards[j].getFace())){
                cardfacesize[j] =i;
            }
            }
        }
        int pairnum = 0;
       
        //paiar
        for(int i =0;i<4;i++){
            if(cardfacesize[i] == cardfacesize[i+1]){
                big2p =cards[i];
                pairnum+=1;
                //System.out.printf(""pairnum is %d\n"",pairnum);
            }
            if(cardfacesize[i] != cardfacesize[i+1] || i==3){
                if((pairnum+1)%2==0) {
                    if(big2p.compareTo(cards[i])<0){
                        big2p =cards[i];
                    }
                    pair+=(pairnum+1)/2;
                    //System.out.printf(""pair is %d\n"",pair);
                }
                if((pairnum+1)%3==0){
                    bigFullHouse = cards[i];
                    FullHouse+=(pairnum+1)/3;
                    //System.out.printf(""flush is %d\n"",flush);
                }
                pairnum = 0;
            }
            if(!(cards[i].getSuit()).equals(cards[i+1].getSuit())){
                   //System.out.printf(""flush is %d\n"",flush);
                   flush=0;
               }
        }
        //stright
        for (int i =0;i<4;i++){
            if(cardfacesize[i+1] - cardfacesize[i] !=1){
                stright=0;
            }
        }
        /*if(cardfacesize[0]==0&&cardfacesize[1]==1&&cardfacesize[2]==2&&cardfacesize[3]==3&&cardfacesize[4]==12){
                bigstright=cards[0];
                stright=1;
            }*/

        //allocate the rank
        {   //FullHouse
            if(pair==1&&FullHouse==1){
                kind = 5;
                playerorder=kind;
                playerbig=bigFullHouse;        
            }
            //flish
            else if(flush==1){
                kind = 4;
                playerorder=kind;
                playerbig=bigflush; 
            }
            //stright
            else if(stright==1){
                kind = 3;
                playerorder=kind;
                playerbig=bigstright; 
            }
            //2pair
            else if(pair==2){
                kind = 2;
                playerorder=kind;
                playerbig=big2p; 
            }
            else if(pair==1){
                kind = 1;
                playerorder=kind;
                playerbig=big2p; 
            }
            else {
                kind = 0;
                playerorder=kind;
                playerbig=cards[4]; 
            }
            
        }
            //System.out.println(this.name);
            //System.out.printf(""FullHouse is %d\n"",FullHouse);
            //System.out.printf(""pair is %d\n"",pair);
            //System.out.printf(""flush is %d\n"",flush);
            //System.out.printf(""kind is %d\n"",kind);
       
        
    }
     
    // TODO 
    public int compareTo(Player that) {
.
        if(this.kind>that.kind) return 1;
        else if(this.kind<that.kind) return -1;
        else {
            if(this.playerbig.compareTo(that.playerbig)>0)return 1;
            if(this.playerbig.compareTo(that.playerbig)<0)return -1;
        }
        return 0;
    }
}


"r04921115","0.098","108336","@9ca8997e13ad0b89751ad8ea3f167dba@import java.util.ArrayList;
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
     
    // TODO 
    public int compareTo(Player that) {
.

    	Arrays.sort(this.cards, Card.SUIT_ORDER);
    	Arrays.sort(that.cards, Card.SUIT_ORDER);
    	Arrays.sort(this.cards);
    	Arrays.sort(that.cards);
    	
    	String[] faceString = {""1"",""2"",""3"",""4"",""5"",""6"",""7"",""8"",""9"",""10"",""J"",""Q"",""K"",""A""};
    	String[] suitString = {""Clubs"",""Diamonds"",""Hearts"",""Spades""};
    	String[] type= {""high card"",""one pair"",""two pair"",""straight"",""flush"",""full house""};
    	
    	int[] faceint1 = new int[cards.length];
    	int[] suitint1 = new int[cards.length];
    	int[] faceint2 = new int[cards.length];
    	int[] suitint2 = new int[cards.length];
    	
    	int player1typeindex = 0;
    	int player2typeindex = 0;
    	
    	UF ufthisface = new UF(cards.length);
    	UF ufthatface = new UF(cards.length);
    	UF ufthissuit = new UF(cards.length);
    	UF ufthatsuit = new UF(cards.length);
    	
    	int samesuitcount=0;
    	int samefacecount=0;
    	
    	//faceint initialize
    	for(int i = 0; i<cards.length;i++){
        	for(int j = 0; j<faceString.length;j++){
        		if(this.cards[i].getFace().equals(faceString[j])){
        			faceint1[i] = j+1;
        		}
        		if(that.cards[i].getFace().equals(faceString[j])){
        			faceint2[i] = j+1;
        		}
    		}
    	}

    	//suitint initialize
    	for(int i = 0; i<cards.length;i++){
        	for(int j = 0; j<suitString.length;j++){
        		if(this.cards[i].getSuit().equals(suitString[j])){
        			suitint1[i] = j;
        		}
        		if(that.cards[i].getSuit().equals(suitString[j])){
        			suitint2[i] = j;
        		}
    		}
    	}
    	
    	//this union
    	for(int i =0; i<cards.length; i++){
        	for(int j =0; j<cards.length; j++){
        		if(this.cards[i].getFace().equals(this.cards[j].getFace())){
        			ufthisface.union(i, j);
        		}
        		if(this.cards[i].getSuit().equals(this.cards[j].getSuit())){
        			ufthissuit.union(i, j);
        		}
        	}
    	}
    	
    	//that union
    	for(int i =0; i<cards.length; i++){
    		for(int j =0; j<cards.length; j++){
        		if(that.cards[i].getFace().equals(that.cards[j].getFace())){
        			ufthatface.union(i, j);
        		}
        		if(that.cards[i].getSuit().equals(that.cards[j].getSuit())){
        			ufthatsuit.union(i, j);
        		}
        	}
    	}

    	//list 1
		ArrayList<String> arrlist1 = new ArrayList<String>();
    	int samecount;
    	if(ufthisface.count()==2){
			player1typeindex = 5;
    	}else if(ufthissuit.count()==1){
    		player1typeindex=4;
    	}else if(ufthisface.count()==3){
    		for(int i = 0;i<cards.length-2;i++){
    			if(faceint1[i]==faceint1[i+2]){
    				player1typeindex=1;
    				break;
    			}else{
    				player1typeindex=2;
    			}
    		}
    	}else if(ufthisface.count()==4){
    		player1typeindex=1;
    	}else if(ufthisface.count()==5){
    		boolean flag = true;
    		for(int i = 1;i<cards.length;i++){
    			if((faceint1[i]-faceint1[i-1])!=1){
    				flag=false;
    			}
    		}
    		if(flag == true){
    			player1typeindex=3;
    		}else if(faceint1[0]==2 & faceint1[1]==3 & faceint1[2]==4 & faceint1[3]==5 & faceint1[4]==14){
	    		player1typeindex=3;
			}else{
				player1typeindex=0;
			}
    	}
    	
    	
    	//list 2
    	ArrayList<String> arrlist2 = new ArrayList<String>();
    	if(ufthatface.count()==2){
			player2typeindex = 5;
    	}else if(ufthatsuit.count()==1){
    		player2typeindex=4;
    	}else if(ufthatface.count()==3){
    		for(int i = 0;i<cards.length-2;i++){
    			if(faceint2[i]==faceint2[i+2]){
    				player2typeindex=1;
    				break;
    			}else{
    				player2typeindex=2;
    			}
    		}
    	}else if(ufthatface.count()==4){
    		player2typeindex=1;
    	}else if(ufthatface.count()==5){
    		boolean flag = true;
    		for(int i = 1;i<cards.length;i++){
    			if((faceint2[i]-faceint2[i-1])!=1){
    				flag=false;
    			}
    		}
    		if(flag == true){
    			player2typeindex=3;
    		}else if(faceint2[0]==2 & faceint2[1]==3 & faceint2[2]==4 & faceint2[3]==5 & faceint2[4]==14){
	    		player2typeindex=3;
			}else{
				player2typeindex=0;
			}
    	}
    	
    	
    	//compare
    	int highface1=0;
    	int highface2=0;
    	int highsuit1=0;
    	int highsuit2=0;
    	
//    	
//    	System.out.println(""play1: ""+this.name+player1typeindex);
//    	System.out.println(""play2:""+that.name+player2typeindex);
    	
    	if(player1typeindex > player2typeindex){
    		return +1;
    	}else if(player1typeindex < player2typeindex){
    		return -1;
    	}else if(player1typeindex == player2typeindex){
    		if(player1typeindex == 5){
    			for(int i = 0;i<cards.length-2;i++){
        			if(faceint1[i]==faceint1[i+2]){
        				highface1=faceint1[i];
        				highsuit1=suitint1[i];
        			}
        			if(faceint2[i]==faceint2[i+2]){
        				highface2=faceint2[i];
        				highsuit2=suitint2[i];
        			}
    			}
    			if(highface1 > highface2){
    				return +1;
    			}else if(highface1 < highface2){
    				return -1;
    			}else{
    				if(highsuit1 > highsuit2){
        				return +1;
        			}else if(highsuit1 < highsuit2){
        				return -1;
        			}else{
        				return 0;
        			}
    			}
    		}
    		if(player1typeindex==2 || player1typeindex==1){
    			for(int i = cards.length-1; i>0;i--){
    				if(faceint1[i]==faceint1[i-1]){
    					highface1=faceint1[i];
    					highsuit1=suitint1[i];
    					break;
    				}
    			}
    			for(int i = cards.length-1; i>0;i--){
    				if(faceint2[i]==faceint2[i-1]){
    					highface2=faceint2[i];
    					highsuit2=suitint2[i];
    					break;
    				}
    			}
    			if(highface1 > highface2){
    				return +1;
    			}else if(highface1 < highface2){
    				return -1;
    			}else{
    				if(highsuit1 > highsuit2){
        				return +1;
        			}else if(highsuit1 < highsuit2){
        				return -1;
        			}else{
        				return 0;
        			}
    			}
    		}
    		if(player1typeindex==3){
    			if(faceint1[0]==2 & faceint1[cards.length-1]==14){
    				highface1=faceint1[cards.length-1-1];
    				highsuit1=suitint1[cards.length-1-1];
    			}else{
    				highface1=faceint1[cards.length-1];
    				highsuit1=suitint1[cards.length-1];
    			}
    			if(faceint2[0]==2 & faceint2[cards.length-1]==14){
    				highface2=faceint2[cards.length-1-1];
    				highsuit2=suitint2[cards.length-1-1];
    			}else{
    				highface2=faceint2[cards.length-1];
    				highsuit2=suitint2[cards.length-1];
    			}
    			if(highface1 > highface2){
    				return +1;
    			}else if(highface1 < highface2){
    				return -1;
    			}else{
    				if(highsuit1 > highsuit2){
        				return +1;
        			}else if(highsuit1 < highsuit2){
        				return -1;
        			}else{
        				return 0;
        			}
    			}
    		}
    		if(player1typeindex==0 || player1typeindex==4){
    			if(faceint1[cards.length-1] > faceint2[cards.length-1]){
    				return +1;
    			}else if(faceint1[cards.length-1] < faceint2[cards.length-1]){
    				return -1;
    			}else{
    				if(suitint1[cards.length-1] > suitint2[cards.length-1]){
        				return +1;
        			}else if(suitint1[cards.length-1] < suitint2[cards.length-1]){
        				return -1;
        			}else{
        				return 0;
        			}
    			}
    		}
    	}


//    	System.out.println("""");
//    	System.out.println(""that"");
//    	for(int i =0; i<5; i++){
//    		System.out.println(that.cards[i].getSuit() + that.cards[i].getFace());
//    	}
    	
//    	System.out.println("""");
//    	System.out.println(""this"");
//    	for(int i =0; i<5; i++){
//    		System.out.println(this.cards[i].getSuit() + this.cards[i].getFace());
//    	}
//    	System.out.println("""");
//    	System.out.println(""that"");
//    	for(int i =0; i<5; i++){
//    		System.out.println(that.cards[i].getSuit() + that.cards[i].getFace());
//    	}
//    	System.out.println("""");
//    	System.out.println(""ufthis"");
//    	for(int i =0; i<cards.length; i++){
//    		System.out.println(ufthisface.find(i));
//    	}
//		System.out.println(""count""+ufthisface.count());
//
//    	System.out.println("""");
//    	System.out.println(""ufthat"");
//    	for(int i =0; i<cards.length; i++){
//    		System.out.println(ufthatface.find(i));
//    	}
        return 0;
    }
}

"b01502105","0.088","105248","@72a11dbf8b172102efbb01820902c7fa@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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
    
    public int[] getSequence_num(Player player){
        
        int[] sequence_num = new int[2];
        StringBuilder card_face = new StringBuilder();
        for(int i=0;i<5;i++){
            card_face.append(player.cards[i].getFace());
        }
        //full house 
        if( player.cards[0].getFace().equals(player.cards[1].getFace()) ){  //AAOOO
            
            if( player.cards[1].getFace().equals(player.cards[2].getFace()) && 
                player.cards[3].getFace().equals(player.cards[4].getFace())){       
                sequence_num[0] = 5;
                sequence_num[1] = 1;
                // System.out.printf(""full house type_1  AAABB\n"");
                return sequence_num;
            }
            else if( player.cards[2].getFace().equals(player.cards[3].getFace()) && 
                     player.cards[3].getFace().equals(player.cards[4].getFace())){  
                sequence_num[0] = 5;
                sequence_num[1] = 2;
                // System.out.printf(""full house type_2  AABBB\n"");
                return sequence_num;
            }  
        }
        //flush
        if( (player.cards[0].getSuit().equals(player.cards[1].getSuit())) &&
            (player.cards[1].getSuit().equals(player.cards[2].getSuit())) &&
            (player.cards[2].getSuit().equals(player.cards[3].getSuit())) &&    
            (player.cards[3].getSuit().equals(player.cards[4].getSuit())) ){
            sequence_num[0] = 4;
            switch(player.cards[0].getSuit()){
                case ""Clubs"":    sequence_num[1] = 1; return sequence_num;
                case ""Diamonds"": sequence_num[1] = 2; return sequence_num;   
                case ""Hearts"":   sequence_num[1] = 3; return sequence_num;
                case ""Spades"":   sequence_num[1] = 4; return sequence_num;
                default: System.out.printf(""suit_input wrong\n"");
            }             
        }
        //Straight
        switch(card_face.toString()){
            case ""A2345"": sequence_num[0] = 3; sequence_num[1] = 1; return sequence_num;
            case ""23456"": sequence_num[0] = 3; sequence_num[1] = 2; return sequence_num;
            case ""34567"": sequence_num[0] = 3; sequence_num[1] = 3; return sequence_num;    
            case ""45678"": sequence_num[0] = 3; sequence_num[1] = 4; return sequence_num;
            case ""56789"": sequence_num[0] = 3; sequence_num[1] = 5; return sequence_num;    
            case ""678910"":sequence_num[0] = 3; sequence_num[1] = 6; return sequence_num;
            case ""78910J"":sequence_num[0] = 3; sequence_num[1] = 7; return sequence_num;
            case ""8910JQ"":sequence_num[0] = 3; sequence_num[1] = 8; return sequence_num;
            case ""910JQK"":sequence_num[0] = 3; sequence_num[1] = 9; return sequence_num;
            case ""10JQKA"":sequence_num[0] = 3; sequence_num[1] = 10;return sequence_num;
        }
        
        if( player.cards[0].getFace().equals(player.cards[1].getFace()) ){  //AAOOO           
            if( player.cards[2].getFace().equals(player.cards[3].getFace()) &&
                   !(player.cards[3].getFace().equals(player.cards[4].getFace())) ){  
                sequence_num[0] = 2;
                sequence_num[1] = 1;
                // System.out.printf(""two pairs type_1  AABBC\n"");
                return sequence_num;
            }
            else if( player.cards[3].getFace().equals(player.cards[4].getFace()) &&
                   !(player.cards[2].getFace().equals(player.cards[3].getFace())) ){  
                sequence_num[0] = 2;
                sequence_num[1] = 2;
                // System.out.printf(""two pairs type_2  AABCC\n"");
                return sequence_num;
            }
            else{  //AABCD
                sequence_num[0] = 1;
                sequence_num[1] = 1;
                // System.out.printf(""one pair type_1  AABCD\n"");
                return sequence_num;
            }
        }
        else if(player.cards[1].getFace().equals(player.cards[2].getFace())){  //OAAOO
            
            if( player.cards[3].getFace().equals(player.cards[4].getFace()) &&
              !(player.cards[2].getFace().equals(player.cards[3].getFace())) ){  
                sequence_num[0] = 2;
                sequence_num[1] = 3;
                // System.out.printf(""two pairs type_3  ABBCC\n"");
                return sequence_num;
            }
            else{   //ABBCD
                sequence_num[0] = 1;
                sequence_num[1] = 2;
                // System.out.printf(""one pair type_2  ABBCD\n"");
                return sequence_num;
            }
        }
        else if( player.cards[2].getFace().equals(player.cards[3].getFace())){ //ABCCD
            sequence_num[0] = 1;
            sequence_num[1] = 3;
            // System.out.printf(""one pair type_3  ABBCD\n"");
            return sequence_num;
        }
        else if( player.cards[3].getFace().equals(player.cards[4].getFace())){ //ABCDD
            sequence_num[0] = 1;
            sequence_num[1] = 4;
            // System.out.printf(""one pair type_4  ABCDD\n"");
            return sequence_num;
        }
        else{  //faces are distinct
            sequence_num[0] = 0; 
            sequence_num[1] = 0;
            // System.out.printf(""High card\n""); 
            return sequence_num;
        }
    }
    // TODO 
    public int compareTo(Player that) {
.
        int[][] player_sequence_num = new int[2][2];
        player_sequence_num[0] = getSequence_num(this);
        player_sequence_num[1] = getSequence_num(that);

        if( player_sequence_num[0][0] < player_sequence_num[1][0]) return -1;
        if( player_sequence_num[0][0] > player_sequence_num[1][0]) return +1;
        
        // player_sequence_num[0][0] = player_sequence_num[1][0]
        Card[] key_card = new Card[2];
        switch(player_sequence_num[0][0]){   //sequence level are the same
            case 5:  //full house
                if(player_sequence_num[0][1] == 1 )   {key_card[0] = this.cards[2];}
                else if(player_sequence_num[0][1] ==2){key_card[0] = this.cards[4];}
                if(player_sequence_num[1][1] == 1 )   {key_card[1] = that.cards[2];}
                else if(player_sequence_num[1][1] ==2){key_card[1] = that.cards[4];}
                
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
            
            case 4:  //flush
                key_card[0] = this.cards[4];
                key_card[1] = that.cards[4];
                
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
                
            case 3:  //straight
                if( player_sequence_num[0][1] < player_sequence_num[1][1]) return -1;
                if( player_sequence_num[0][1] > player_sequence_num[1][1]) return +1;
                //player_sequence_num[0][1] = player_sequence_num[1][1]
                key_card[0] = this.cards[4];
                key_card[1] = that.cards[4];
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
                
            case 2:  //two pairs
                switch(player_sequence_num[0][1]){
                    case 1:  //AABBC
                        key_card[0] = this.cards[3]; break;
                    case 2:  //AABCC
                        key_card[0] = this.cards[4]; break;
                    case 3:  //ABBCC
                        key_card[0] = this.cards[4]; break;
                }
                switch(player_sequence_num[1][1]){
                    case 1:  //AABBC
                        key_card[1] = that.cards[3]; break;
                    case 2:  //AABCC
                        key_card[1] = that.cards[4]; break;
                    case 3:  //ABBCC
                        key_card[1] = that.cards[4]; break;
                }
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
                
            case 1:  //one pair
                switch(player_sequence_num[0][1]){
                    case 1:  //AABCD
                        key_card[0] = this.cards[1]; break;
                    case 2:  //ABBCD
                        key_card[0] = this.cards[2]; break;
                    case 3:  //ABCCD
                        key_card[0] = this.cards[3]; break;
                    case 4:  //ABCDD
                        key_card[0] = this.cards[4]; break;
                }
                switch(player_sequence_num[1][1]){
                    case 1:  //AABCD
                        key_card[1] = that.cards[1]; break;
                    case 2:  //ABBCD
                        key_card[1] = that.cards[2]; break;
                    case 3:  //ABCCD
                        key_card[1] = that.cards[3]; break;
                    case 4:  //ABCDD
                        key_card[1] = that.cards[4]; break;
                }
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
                
            case 0:  //high card
                key_card[0] = this.cards[4];
                key_card[1] = that.cards[4];
                
                if( key_card[0].compareTo(key_card[1]) < 0) return -1;
                if( key_card[0].compareTo(key_card[1]) > 0) return +1;
        }
        return 0;
    }
}

"r03525008","0.102","103552","@21d57b083bc09a2e38a3e9204d55bf4c@import java.util.*;

public class Player implements Comparable<Player>{

    private Card[] cards = new Card[5];
    private String name;

    enum CardType {
        full_house , flush , straight , two_pair , one_pair , high_card
    }

    private CardType cardType;
    public CardQuery cardQuery = new CardQuery();

    private static final SuitOrder suitOrder = new SuitOrder();
    private static final FaceOrder faceOrder = new FaceOrder();
    private static final CardTypeOrder cardTypeOrder = new CardTypeOrder();


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

    public CardType getCardType(){ return this.cardType; }

    public void setCardType(){
        Map<String,Integer> faces = cardQuery.getFaceCount();
        if (faces.size() == 2) {
            // (4,1) or (3,2)
            if (cardQuery.getThrees().size() != 0) {
                cardType = CardType.full_house;
                return;
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 3){
            // (3,1,1) or (2,2,1)
            if (cardQuery.getPairs().size() != 0) {
                cardType = CardType.two_pair;
                return;
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 4){
            // (2,1,1,1)
            cardType = CardType.one_pair;
        } else if (faces.size() == 5){

            // check for flush
            if (cardQuery.getSuitSet().size() == 1) {
                cardType = CardType.flush;
                return;
            }

            // check for straight
            String[] straights = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2"",""1""};
            Set<String> faceSet = faces.keySet();
            for(int i = 0; i < straights.length - 5 ; i++){
                Set<String> straightOne = new HashSet<String>();
                for(int j = i ; j < i + 5 ; j++){
                    straightOne.add(straights[j]);
                }
                if (faceSet.containsAll(straightOne)) {
                   cardType = CardType.straight;
                   return;
                }
            }

            // high card otherwise
            cardType = CardType.high_card;
        }
    }

    // TODO 
    public int compareTo(Player that) {
.

        this.setCardType();
        that.setCardType();
        int result = cardTypeOrder.compare(this.getCardType(), that.getCardType());
        if (result != 0){
            return result;
        } else {
            // broke ties
            switch (this.getCardType()) {
                case full_house:
                    String max1 = Collections.max(this.cardQuery.getThrees() , faceOrder);
                    String max2 = Collections.max(that.cardQuery.getThrees() , faceOrder);
                    result = faceOrder.compare(max1 , max2);
                    return result;

                case flush:
                    String flush1 = Collections.max(this.cardQuery.getSuitSet() , faceOrder);
                    String flush2 = Collections.max(that.cardQuery.getSuitSet() , faceOrder);
                    result = suitOrder.compare(flush1 , flush2);
                    if (result != 0) {
                        return result;
                    } else {
                        Set<String> suit1 = this.cardQuery.getSuitSet(flush1);
                        Set<String> suit2 = that.cardQuery.getSuitSet(flush2);
                        result = suitOrder.compare(Collections.max(suit1,suitOrder) , Collections.max(suit2,suitOrder));
                        return result;
                    }

                case two_pair:
                case one_pair:
                    String pair1 = Collections.max(this.cardQuery.getPairs() , faceOrder);
                    String pair2 = Collections.max(that.cardQuery.getPairs() , faceOrder);
                    result = faceOrder.compare(pair1 , pair2);
                    if (result != 0) {
                       return result;
                    } else {
                        Set<String> suit1 = this.cardQuery.getSuitSet(pair1);
                        Set<String> suit2 = that.cardQuery.getSuitSet(pair1);
                        result = suitOrder.compare(Collections.max(suit1,suitOrder) , Collections.max(suit2,suitOrder));
                        return result;
                    }

                case high_card:
                case straight:

                    String high_card1 = Collections.max(this.cardQuery.getOnes() , faceOrder);
                    String high_card2 = Collections.max(that.cardQuery.getOnes() , faceOrder);

                    result = faceOrder.compare(high_card1 , high_card2);
                    if (result != 0) {
                        return result;
                    } else {
                        Set<String> suit1 = this.cardQuery.getSuitSet(high_card1);
                        Set<String> suit2 = that.cardQuery.getSuitSet(high_card2);
                        result = suitOrder.compare(Collections.max(suit1,suitOrder) , Collections.max(suit2,suitOrder));
                        return result;
                    }
            }
        }
        return 0;
    }



    private static class SuitOrder implements Comparator<String> {
        private static List<String> order;
        SuitOrder() {
            String[] suits = {""Spades"", ""Hearts"", ""Diamonds"", ""Clubs""};
            order = new ArrayList<String>();
            for (String suit : suits) {
                order.add(suit);
            }
        }

        @Override
        public int compare(String suit1, String suit2) {
            int suit_1 = order.indexOf(suit1);
            int suit_2 = order.indexOf(suit2);
            if (suit_1 < suit_2) return 1;
            else if (suit_1 > suit_2) return -1;
            else return 0;
        }
    }

    private static class FaceOrder implements Comparator<String> {
        private static List<String> order;
        FaceOrder() {
            String[] faces = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2"",""1""};
            order = new ArrayList<String>();
            for (String face : faces) {
                order.add(face);
            }
        }

        @Override
        public int compare(String face1, String face2) {
            int face_1 = order.indexOf(face1);
            int face_2 = order.indexOf(face2);
            if (face_1 < face_2) return 1;
            else if (face_1 > face_2) return -1;
            else return 0;
        }
    }


    private static class CardTypeOrder implements Comparator<CardType> {
        private static List<CardType> order;
        CardTypeOrder() {
            CardType[] cardTypes = {
                    CardType.full_house,
                    CardType.flush,
                    CardType.straight,
                    CardType.two_pair,
                    CardType.one_pair,
                    CardType.high_card
            };
            order = new ArrayList<CardType>();
            for (CardType cardType : cardTypes) {
                order.add(cardType);
            }
        }

        @Override
        public int compare(CardType cardType1, CardType cardType2) {
            int cardType_1 = order.indexOf(cardType1);
            int cardType_2 = order.indexOf(cardType2);
            if (cardType_1 < cardType_2) return 1;
            else if (cardType_1 > cardType_2) return -1;
            else return 0;
        }
    }


    private class CardQuery {

        public Map<String , Integer> getFaceCount(){
            Map<String,Integer> faces = new HashMap<String,Integer>();
            for(Card c:cards){
                if (faces.containsKey(c.getFace()))
                    faces.put(c.getFace() , faces.get(c.getFace())+1);
                else
                    faces.put(c.getFace() , 1);
            }
            return faces;
        }

        public Set<String> getSuitSet(){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        public Set<String> getSuitSet(Set<String> faceSet){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                if (faceSet.contains(c.getFace()))
                    suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        public Set<String> getSuitSet(String face){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                if (face.equals(c.getFace()))
                    suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        public Set<String> getOnes(){
            Set<String> ones = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 1)
                    ones.add(entry.getKey());
            }
            return ones;
        }

        public Set<String> getPairs(){
            Set<String> pairs = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 2)
                    pairs.add(entry.getKey());
            }
            return pairs;
        }

        public Set<String> getThrees(){
            Set<String> threes = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 3)
                    threes.add(entry.getKey());
            }
            return threes;
        }

    }
}


"r04631023","0.092","105312","@f685153004403d886ef5019f6b2c885e@import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Player implements Comparable<Player> {

    public Card[] cards = new Card[5];
    private String name;
    private String[] checkFace = new String[]{""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
    private String[] checkSuit = new String[]{""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};
    private Card[] checkCardsFace = new Card[13];
    private Card[] checkCardsSuit = new Card[4];
    double[][] check = new double[5][2];

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

    public double[][] checkhandspoint(Card[] cards) {
        double[][] checkhandspoint = new double[cards.length][2];
        for (int k = 0; k < cards.length; k++) {
            for (int i = 0; i < 13; i++) {
                if (cards[k].getFace().compareTo(checkFace[i]) == 0) {
                    checkhandspoint[k][0] = i;
                    //System.out.println(i);
                    break;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (cards[k].getSuit().compareTo(checkSuit[i]) == 0) {
                    checkhandspoint[k][1] = i;
                    checkhandspoint[k][1] = checkhandspoint[k][1] / 10;
                    //System.out.println(checkhandspoint[k][1]);
                    break;
                }
            }
        }
        return checkhandspoint;
    }

    public double checkhands(Card[] cards) {
        double handpoints = 0;
        Arrays.sort(cards);
        double[][] check = checkhandspoint(cards);
        /*
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(check[i][j]);
            }
        }
                */
        if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] == check[3][0] && check[3][0] != check[4][0]) {
            handpoints = 2 * 13 + check[3][0] + check[3][1]; //two pair
            return handpoints;
        } else if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 2 * 13 + check[4][0] + check[4][1]; //two pair
            return handpoints;
        } else if (check[0][0] != check[1][0] && check[1][0] == check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 2 * 13 + check[4][0] + check[4][1]; //two pair
            return handpoints;
        } else if (check[0][0] == check[1][0] && check[1][0] != check[2][0] && check[2][0] == check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 5 * 13 + check[4][0] + check[4][1]; //full house
            return handpoints;
        } else if (check[0][0] == check[1][0] && check[1][0] == check[2][0] && check[2][0] != check[3][0] && check[3][0] == check[4][0]) {
            handpoints = 5 * 13 + check[2][0] + check[2][1]; //full house
            return handpoints;
        } else if (check[0][1] == check[1][1] && check[0][1] == check[2][1] && check[0][1] == check[3][1] && check[0][1] == check[4][1]) {
            handpoints = 4 * 13 + check[4][0] + check[4][1]; //flush
            return handpoints;
        } else if (check[4][0] - check[3][0] == check[3][0] - check[2][0] && check[3][0] - check[2][0] == check[2][0] - check[1][0] && check[2][0] - check[1][0] == check[1][0] - check[0][0]) {
            handpoints = 3 * 13 + check[4][0] + check[4][1];//Straight
            return handpoints;
        } else if (check[0][0] != check[1][0] && check[1][0] != check[2][0] && check[2][0] != check[3][0] && check[3][0] != check[4][0]) {
            handpoints = check[4][0] + check[4][1]; //high card
            return handpoints;
        } else if (check[3][0] == check[4][0]) {
            handpoints = 1 * 13 + check[4][0] + check[4][1]; //one pair
            return handpoints;
        } else if (check[2][0] == check[3][0]) {
            handpoints = 1 * 13 + check[3][0] + check[3][1]; //one pair
            return handpoints;
        } else if (check[1][0] == check[2][0]) {
            handpoints = 1 * 13 + check[2][0] + check[2][1]; //one pair
            return handpoints;
        } else if (check[0][0] == check[1][0]) {
            handpoints = 1 * 13 + check[1][0] + check[1][1]; //one pair
            return handpoints;
        }
        return handpoints;
    }

    // TODO 
    public int compareTo(Player that) {
        if (checkhands(this.cards) > checkhands(that.cards)) {
            return 1;
        } else if (checkhands(this.cards) == checkhands(that.cards)) {
            return 0;
        } else {
            return -1;
        }
    }
    /*

    public static void main(String[] args) throws Exception {

        try (BufferedReader br = new BufferedReader(new FileReader(""input.txt""))) {

            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for (String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for (int i = 0; i < 5; i++) {
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                player.setCards(cardsArray);

                System.out.println(player.checkhands(player.cards));
            }

            Arrays.sort(playerArray);
            System.out.println(playerArray[playerCount - 1].getName());
        }
    }
*/
}

"r03228006","0.092","105232","@a44d3b58c2e4289614110f661c5deda0@import java.util.Arrays;

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
    public int Faceint(Card c){
          if(c.getFace().equals(""A"")) return 14;
          else if(c.getFace().equals(""K"")) return 13;
          else if(c.getFace().equals(""Q"")) return 12;
          else if(c.getFace().equals(""J"")) return 11;
          return Integer.parseInt(c.getFace());} 
    // TODO 
    public int compareTo(Player that) {
.
        //sort from small to large
         Arrays.sort(this.cards);
         Arrays.sort(that.cards);
         //full house > 3+2 
         if ((this.cards[2].getFace().equals(this.cards[0].getFace())|this.cards[2].getFace().equals(this.cards[4].getFace()))|(that.cards[2].getFace().equals(that.cards[0].getFace())|that.cards[2].getFace().equals(that.cards[4].getFace()))){
             if((this.cards[2].getFace().equals(this.cards[0].getFace())|this.cards[2].getFace().equals(this.cards[4].getFace()))&(that.cards[2].getFace().equals(that.cards[0].getFace())|that.cards[2].getFace().equals(that.cards[4].getFace()))){
                return this.cards[2].compareTo(that.cards[2]); //both are full house and compare face&suit
             }
             else if ((this.cards[2].getFace().equals(this.cards[0].getFace())|this.cards[2].getFace().equals(this.cards[4].getFace()))){
                return +1; //only this is full house
             }
             else {return -1;}// only that is full house
         }
         //flush > same suit
         boolean x0=true;
         boolean x1=true;
         for(int i=1;i<5;i++){
           if(!this.cards[0].getSuit().equals(this.cards[i].getSuit())) {x0=false;}
           if(!that.cards[0].getSuit().equals(that.cards[i].getSuit())) {x1=false;}}//unless everyone same
         if (x0|x1){
           if (x0 & x1) {return this.cards[4].compareTo(that.cards[4]);}
           else if (x0){return +1;}//只有x0是flush
           else {return -1;}}//只有x1是flush}
         
         //straight > 
         x0=true;
         x1=true;
         for(int i=0;i<4;i++){
           if(Faceint(this.cards[i])!=(Faceint(this.cards[i+1])-1)) {x0=false;}
           if(Faceint(that.cards[i])!=(Faceint(that.cards[i+1])-1)) {x1=false;}}//unless everyone continuous
         //if(x0){maxs0=Faceint(this.cards[4]);}
         //if(x1){maxs1=Faceint(that.cards[4]);}
         if(this.cards[4].getFace().equals(""A"")&this.cards[0].getFace().equals(""2"")){//2,3,4,5,A
             x0=true;
             for(int i=0;i<4;i++){
                 if(Faceint(this.cards[i])!=i+2){x0=false;}
             }}
         if(that.cards[4].getFace().equals(""A"")&that.cards[0].getFace().equals(""2"")){
             x1=true;
             for(int i=0;i<4;i++){
                 if(Faceint(that.cards[i])!=i+2){x1=false;}
             }}
         if(x0|x1){
             if(x0 & x1){
                 if(this.cards[4].getFace().equals(""A"")&this.cards[0].getFace().equals(""2"")) {
                     if(that.cards[4].getFace().equals(""A"")&that.cards[0].getFace().equals(""2"")){
                             return this.cards[4].compareTo(that.cards[4]);}  //if both smallest straight
                     else {return -1;}}
                 else if(that.cards[4].getFace().equals(""A"")&that.cards[0].getFace().equals(""2"")) {return +1;}
                 else return this.cards[4].compareTo(that.cards[4]);}  
             else if(x0){return +1;} // this straight
             else {return -1;}//that straight
         } 
         
         //two pair >
         x0=false;
         x1=false;
         int pair0=0;
         int pair1=0; 
         Card[] pairs = new Card[2];
         //Card pairn0=new Card(name, name);
         for(int i=0;i<4;i++){
           if(this.cards[i].getFace().equals(this.cards[i+1].getFace())){
               pairs[0] = this.cards[i];
               pair0++;}
           if(that.cards[i].getFace().equals(that.cards[i+1].getFace())){
               pairs[1] = that.cards[i];
               //Card p1 = new Card(that.cards[i].getFace(),that.cards[i].getSuit());
               pair1++;}
           }
         if(pair0>=2){x0=true;}
         if(pair1>=2){x1=true;}
         //Card p1 = new Card(""3"",""Hearts"");
         //Card p2= new Card(""J"",""Clubs""); 
         //int K = p1.compareTo(p2);
         if(x0|x1){
            if(x0 & x1) {return pairs[0].compareTo(pairs[1]);}
            else if(x0) {return +1;}
            else {return -1;}
         }
         //one pair > 
         if(pair0==1){x0=true;}
         if(pair1==1){x1=true;}
         if(x0|x1){
            if(x0 & x1) {return pairs[0].compareTo(pairs[1]);}
            else if(x0) {return +1;}
            else {return -1;}
         }
         //high card
         return this.cards[4].compareTo(that.cards[4]);
    }

}
"r04631006","0.094","105216","@a306ca75bfb2326ab293e73bcb3acf08@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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

    public int CardsName(Card[] cards) {
        int a = 0;
        int[] b = new int[5];
        int[] cc = new int[5];
        Arrays.sort(cards);
        for (int i = 0; i < 5; i++) {
            for (int j = i; j < 5; j++) {
                if (cards[i].getFace().equals(cards[i].getFace()) && cards[i].getFace().equals(cards[j].getFace())) {
                    cc[i] = cc[i] + 1;
                }
            }
            if (cc[i] == 3) {
                i = i + 2;
            }
            if (cc[i] == 2) {
                i = i + 1;
            }
        }
        Arrays.sort(cc);
//        for (int i = 0; i < 5; i++) {
//            System.out.printf(""%s "", cards[i].getFace());
//            System.out.printf(""\n "");
//        }
        if (cc[4] == 3 && cc[3] == 2) {
            return 6;
        } else if (cc[4] == 2 && cc[3] == 2) {
            return 3;
        } else if (cc[4] == 2 && cc[3] == 1) {
            return 2;
        } else if (Card.SUIT_ORDER.compare(cards[4], cards[3]) == 0 && Card.SUIT_ORDER.compare(cards[4], cards[2]) == 0 && Card.SUIT_ORDER.compare(cards[4], cards[1]) == 0 && Card.SUIT_ORDER.compare(cards[4], cards[0]) == 0) {
            return 5;
        } else {
            Arrays.sort(cards);
            for (int i = 0; i < 5; i++) {
                switch (cards[i].getFace()) {
                    case ""A"":
                        b[i] = 14;
                        break;
                    case ""K"":
                        b[i] = 13;
                        break;
                    case ""Q"":
                        b[i] = 12;
                        break;
                    case ""J"":
                        b[i] = 11;
                        break;
                    default:
                        b[i] = Integer.parseInt(cards[i].getFace());
                        break;
                }
            }
            if (b[1] == b[0] + 1 && b[2] == b[1] + 1 && b[3] == b[2] + 1 && b[4] == b[3] + 1) {
                return 4;
            } else {
                return 1;
            }

        }

    }

    // TODO 
    @Override
    public int compareTo(Player that) {
.
        int x = 0;
        int y = 0;
        int b = 0;
        int a = 0;
        x = CardsName(this.cards);
        y = CardsName(that.cards);
        if (x > y) {
            return +1;
        } else if (x < y) {
            return -1;
        } else {
            Arrays.sort(this.cards);
            Arrays.sort(that.cards);
            if (this.cards[4].getFace().equals(that.cards[4].getFace())) {
                if (Card.SUIT_ORDER.compare(this.cards[4], that.cards[3]) == 1) {
                    return 1;
                } else if (Card.SUIT_ORDER.compare(this.cards[4], that.cards[3]) == -1) {
                    return -1;
                }
            } else {
                switch (this.cards[4].getFace()) {
                    case ""A"":
                        b = 14;
                        break;
                    case ""K"":
                        b = 13;
                        break;
                    case ""Q"":
                        b = 12;
                        break;
                    case ""J"":
                        b = 11;
                        break;
                    default:
                        b = Integer.parseInt(this.cards[4].getFace());
                        break;
                }
                switch (that.cards[4].getFace()) {
                    case ""A"":
                        a = 14;
                        break;
                    case ""K"":
                        a = 13;
                        break;
                    case ""Q"":
                        a = 12;
                        break;
                    case ""J"":
                        a = 11;
                        break;
                    default:
                        a = Integer.parseInt(that.cards[4].getFace());
                        break;
                }
                if (b > a) {
                    return +1;
                } else if (b < a) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }
}
"r03525008","0.088","105264","@900b7a1a294bfd240eeb808dbe2845b5@import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
        Card[] thatcards;
        int x=get_cardsvalue(this.cards);
        int y=get_cardsvalue(that.cards);
       if(x>y) return 1;
        else if(x<y)return -1;
        else return this.cards[0].compareTo(that.cards[0]);
    }
    private int get_cardsvalue(Card[] cards){
        Arrays.sort(cards,Collections.<Card>reverseOrder());
/*        for(int i=0;i<5;i++)
            System.out.println(cards[i].toString());*/
        if(check_fullhouse(cards)) return 6;
        else if(check_flush(cards)) return 5;
        else if(check_straight(cards)) return 4;
        else if(check_2pair(cards)) return 3;
        else if(check_1pair(cards)) return 2;
        else return 1;
    }
    private boolean check_fullhouse(Card[] cards){
        if(cards[0].getFace().equals(cards[1].getFace())&cards[1].getFace().equals(cards[2].getFace())&cards[3].getFace().equals(cards[4].getFace())){
            return true;
        }else if(cards[2].getFace().equals(cards[3].getFace())&cards[3].getFace().equals(cards[4].getFace())&cards[0].getFace().equals(cards[1].getFace())){
            Card temp=cards[0];
            cards[0]=cards[2];
            cards[2]=temp;
            return true;
        }
        else return false;
    }
    private boolean check_flush(Card[] cards){
        if(cards[0].getSuit().equals(cards[1].getSuit())&cards[1].getSuit().equals(cards[2].getSuit())&cards[2].getSuit().equals(cards[3].getSuit())&
                cards[3].getSuit().equals(cards[4].getSuit())){
            return true;
        }
        else
            return false;
    }
    private boolean check_straight(Card[] cards){
        int[] c=new int[5];
        for(int i=0;i<5;i++){
            if(cards[i].getFace().equals(""A"")){c[i] =14;}
            else if(cards[i].getFace().equals(""K"")){c[i] = 13;}
            else if(cards[i].getFace().equals(""Q"")){c[i] = 12;}
            else if(cards[i].getFace().equals(""J"")){c[i] = 11;}
            else {c[i] = Integer.parseInt(cards[i].getFace());}
        }
        if(c[0]-c[1]==1&c[1]-c[2]==1&c[2]-c[3]==1&c[3]-c[4]==1){
            return true;
        }else if(c[0]==14&c[1]==5&c[2]==4&c[3]==3&c[4]==2){
            Card temp=cards[0];
            cards[0]=cards[1];
            cards[1]=temp;
            return true;
        }else return false;
    }
    private boolean check_2pair(Card[] cards){
        if(cards[0].getFace().equals(cards[1].getFace())&cards[2].getFace().equals(cards[3].getFace())){
            return true;
        }else if(cards[0].getFace().equals(cards[1].getFace())&cards[3].getFace().equals(cards[4].getFace())){
            return true;
        }else if(cards[1].getFace().equals(cards[2].getFace())&cards[3].getFace().equals(cards[4].getFace())){
            Card temp=cards[0];
            cards[0]=cards[1];
            cards[1]=temp;
            return true;
        }else return false;
    }
    private boolean check_1pair(Card[] cards){
        if(cards[0].getFace().equals(cards[1].getFace())){
            return true;
        }else if(cards[1].getFace().equals(cards[2].getFace())){
            Card temp=cards[0];
            cards[0]=cards[1];
            cards[1]=temp;
            return true;
        }else if(cards[2].getFace().equals(cards[3].getFace())){
            Card temp=cards[0];
            cards[0]=cards[2];
            cards[2]=temp;
            return true;
        }else if(cards[3].getFace().equals(cards[4].getFace())){
            Card temp=cards[0];
            cards[0]=cards[3];
            cards[3]=temp;
            return true;
        }else return false;
    }



}


"r04546032","0.094","105168","@35e98c9019087eeb2959927f36f2bc27@import java.util.Arrays;

public class Player implements Comparable<Player> {

    private Card[] cards = new Card[5];
    private String name;
    public int[] Points = new int[5];
    public int[] Points1 = new int[5];
    public int[] Points2 = new int[5];	

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
        int outcome =0;
         //得到牌的所有數字
        for (int i = 0; i < 5; i++) {
            Points1[i] = GetPoint(this.cards[i]);
	    Points2[i] = GetPoint(that.cards[i]);	
        }
        Arrays.sort(Points1);
	Arrays.sort(Points2);
        if (compare(this) > compare(that)) {
            outcome = 1;
        } else if (compare(this) < compare(that)) {
            outcome = -1;
        } else if(compare(this) == compare(that)){
            if(compare(this) == 3 ) //straight 
		{ 
//                    if(Points1[4] > Points2[4])
//                    {
//                        outcome = 1;
//                    }
//                    else if(Points1[4] == Points2[4])
//                    {
//                        outcome = 0;
//                    }
//                    else outcome = -1;
                    outcome = 0;
                }

           
             if(compare(this) == 5)  //full house
                {
                     if( fullhouse(Points1) > fullhouse(Points2) )
                    {
                        outcome = 1;
                    }
                    else if(fullhouse(Points1) == fullhouse(Points2))
                    {
                        outcome = 0;
                    }
                    else outcome = -1;
                }
             if(compare(this) == 2 && compare(that)==2)  //two pair
                {
                     if( twopair(Points1) > twopair(Points2) )
                    {
                        outcome = 1;
                    }
                    else if(twopair(Points1) == twopair(Points2))
                    {
                        outcome = 0;
                    }
                    else outcome = -1;
                }
//              //  if(compare(this) == 1)  //one pair
                 if(compare(this) == 1 && compare(that) == 1)
                {
                     if( onepair(Points1) > onepair(Points2) )
                    {
                        outcome = 1;
                    }
                    else if(onepair(Points1) == onepair(Points2))
                    {
                        outcome = 0;
                    }
                    else outcome = -1;
                }
                 if(compare(this) == 4 || compare(this) == 0)  //high card and flush
                 {
                      if( highfulshcard(Points1) > highfulshcard(Points2) )
                    {
                        outcome = 1;
                    }
                    else if(highfulshcard(Points1) == highfulshcard(Points2))
                    {
                        outcome = 0;
                    }
                    else outcome = -1;
                 }
            
        }
        return outcome ;
    }

    public int GetPoint(Card c) {
        int face = 0;
        switch (c.getFace()) {
            case ""A"":
                face = 1;
                break;
            case ""2"":
                face = 2;
                break;
            case ""3"":
                face = 3;
                break;
            case ""4"":
                face = 4;
                break;
            case ""5"":
                face = 5;
                break;
            case ""6"":
                face = 6;
                break;
            case ""7"":
                face = 7;
                break;
            case ""8"":
                face = 8;
                break;
            case ""9"":
                face = 9;
                break;
            case ""10"":
                face = 10;
                break;
            case ""J"":
                face = 11;
                break;
            case ""Q"":
                face = 12;
                break;
            case ""K"":
                face = 13;
                break;
        }
        return face;
    }

    public int compare(Player that) {
        int count = 0; 
        int Pair = 0;
        //sort the suit first  check if it is flush first
        Arrays.sort(that.cards, Card.SUIT_ORDER);
        for (int i = 0; i < 4; i++) {
            if (Card.SUIT_ORDER.compare(that.cards[i], that.cards[i + 1]) == 0) //flush
            {
                count++;
                if (count == 4) {
                    return 4;
                }
            }
        }
        count = 0;
        //得到牌的所有數字
        for (int i = 0; i < 5; i++) {
            Points[i] = GetPoint(that.cards[i]);
        }
        Arrays.sort(Points);
        for (int i = 0; i < 4; i++) //straight
        {
            if (Points[i] == Points[i + 1] - 1) {
                count++;
            }
        }
        if (count == 4) {
            return 3;
        }
        if(Points[0]==1 && Points[1] == 10 && Points[2] == 11 &&Points[3] == 12 &&Points[4] == 13)
        {
            return 3;
        }
        if(Points[0]==1 && Points[1] == 2 && Points[2] == 11 &&Points[3] == 12 &&Points[4] == 13)
        {
            return 3;
        }
        if(Points[0]==1 && Points[1] == 2 && Points[2] == 3 &&Points[3] == 12 &&Points[4] == 13)
        {
            return 3;
        }
        if(Points[0]==1 && Points[1] == 2 && Points[2] == 3 &&Points[3] == 4 &&Points[4] == 13)
        {
            return 3;
        }
        count = 0;
        // one pair two pair full house
        for (int i = 0; i < 4; i++) {
            if (Points[i] == Points[i + 1]) //one pair
            {
                Pair++;
            } else {
                Pair--;
            }

        }
        if (Pair == 2) {  //fullhouse
            return 5;
        } else if (Pair == 0) { //two pair
            return 2;
        } else if (Pair == -2) { //one pair
            return 1;
        } else {
            return 0;
        }
    }
    
    
    
    public int fullhouse(int [] c)
    { int count = 0; int flag = 0;
        for(int i = 0; i < 4; i++)
        {
            if(c[i] == c[i+1])
            {
                count++;
                if(count == 2)
                {
                    if(c[i] == 1)
                    {
                        flag = 100;
                    }
                    else flag = c[i];
                   break;
                }
            }
            else
            {
                count--;
            }
        
        }
        return flag;
    }
    public int judge(int [] c)
    { int pair = 0;
          for (int i = 0; i < 4; i++) {
            if (Points[i] == Points[i + 1]) //one pair
            {
                pair++;
            } else {
                pair--;
            }

        }
          return pair;
    }
    
    
     public int onepair(int [] c)
    { int count = 0; int flag = 0;
        for(int i = 0; i < 4; i++)
        {
            if(c[i] == c[i+1])
            {
                count++;
                
                if(count == 1)
                {
                     if(c[i] == 1)
                    {
                        flag = 100;
                    }
                     else flag = c[i];
                   break;
                }
            }
        }
        return flag;
    }
     
      public int twopair(int [] c)
    { int count = 0; int flag = 0;
        for(int i = 0; i < 4; i++)
        {
            if(c[i] == c[i+1] )
            {
                count++;
                if(count == 2)
                {
                   flag = c[i];
                   break;
                }
            }
             if(c[i] == c[i+1] && c[i] == 1)
            {
                flag = 100;break;
            }
        }
        return flag;
    }
      public int highfulshcard(int [] c)
      {         
              if(c[0] == 1)
              {
                  return 100;
              } 
              else{
                  return c[4];
              }
      }
//      public int straight(int []c )
//      {
//          if(c[0] == 1)
//          {
//              return -99;
//          }
//          if(c[1] == 1 || c[2] == 1 || c[3] == 1 || c[4] == 1)
//          {
//              return 100;
//          }
//          else{
//              return c[4];
//          }
//      }
}

"b03611033","0.094","103072","@0e4935c6e03086ada252460741c0d9d4@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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
        
        if(this.fullhouse(this.cards) > that.fullhouse(that.cards)) return 1;
        else if(this.fullhouse(this.cards) < that.fullhouse(that.cards)) return -1;
        else{
//            StdOut.println(""==="");
//            StdOut.println(this.flush(this.cards));
//            StdOut.println(that.flush(that.cards));
            if(this.flush(this.cards) > that.flush(that.cards)) return 1;
            else if(this.flush(this.cards) < that.flush(that.cards)) return -1;
            else{

                if(this.straight(this.cards) > that.straight(that.cards)) return 1;
                else if(this.straight(this.cards) < that.straight(that.cards)) return -1;
                else{ 

                    if(this.pair(this.cards) > that.pair(that.cards)) return 1;
                    else if(this.pair(this.cards) < that.pair(that.cards)) return -1;
                    return 0;
                }
            }
        }

    }

    private static int cardface(String face) {
        int facenum;

        if (face.equals(""A"")) {
            facenum = 14;
        } else if (face.equals(""J"")) {
            facenum = 11;
        } else if (face.equals(""Q"")) {
            facenum = 12;
        } else if (face.equals(""K"")) {
            facenum = 13;
        } else {
            facenum = Integer.parseInt(face);
        }

        return facenum;
    }

    private static int cardsuit(String suit) {
        int suitnum;

        if (suit.equals(""Spades"")) {
            suitnum = 4;
        } else if (suit.equals(""Hearts"")) {
            suitnum = 3;
        } else if (suit.equals(""Diamonds"")) {
            suitnum = 2;
        } else {
            suitnum = 1;
        }

        return suitnum;
    }
    
    private int fullhouse(Card[] card){
        int cardnum[]=new int[5];
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);
                
        if ((cardnum[0] == cardnum[1]) && (cardnum[3] == cardnum[4])) {
            if((cardnum[2] == cardnum[1]) || (cardnum[2] == cardnum[3]))
                return cardnum[2];
        } 
            return 0;
    }
    
    private int flush(Card[]card){
        int suitnum = cardsuit(card[0].getSuit());
        int cardnum = cardface(card[0].getFace());
        int i=1;
        
        while(cardsuit(card[i].getSuit()) == suitnum){
            if(cardnum < cardface(card[i].getFace())) cardnum=cardface(card[i].getFace());
            i++;
            if(i==5) return (cardnum*10+suitnum);
        }
        
        return 0;
    }

    private int straight(Card[]card){
        int cardnum[]=new int[5];
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);

        int i=1;
        while(cardnum[i] == cardnum[i-1]+1){
            //StdOut.println(cardnum[i]);
            if(i==4) return (cardsuit(card[4].getSuit()) + cardnum[4]*10);          
            i++;       
        }

        return 0;
    }
    
    private int pair(Card[]card){
        int cardnum[]=new int[5];
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);
        
        if(cardnum[0]==cardnum[1]){
            if(cardnum[2]==cardnum[3]) return (cardsuit(card[3].getFace()) + cardnum[3]*10+1000);
            else if(cardnum[3]==cardnum[4]) return (cardsuit(card[4].getFace()) + cardnum[4]*10+1000);
            else return (cardsuit(card[1].getFace()) + cardnum[1]*10);
        }
        else if(cardnum[1]==cardnum[2]){
            if(cardnum[3]==cardnum[4]) return (cardsuit(card[4].getSuit()) + cardnum[4]*10+1000);
            else return (cardsuit(card[1].getFace()) + cardnum[1]*10);
        }
        if(cardnum[2]==cardnum[3]) return (cardsuit(card[3].getSuit()) + cardnum[3]*10);
        if(cardnum[3]==cardnum[4]) return (cardsuit(card[4].getSuit()) + cardnum[4]*10);
        
        return 0;
    }
    
}

"b03611033","0.094","103056","@0e4935c6e03086ada252460741c0d9d4@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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
        
        if(this.fullhouse(this.cards) > that.fullhouse(that.cards)) return 1;
        else if(this.fullhouse(this.cards) < that.fullhouse(that.cards)) return -1;
        else{
//            StdOut.println(""==="");
//            StdOut.println(this.flush(this.cards));
//            StdOut.println(that.flush(that.cards));
            if(this.flush(this.cards) > that.flush(that.cards)) return 1;
            else if(this.flush(this.cards) < that.flush(that.cards)) return -1;
            else{

                if(this.straight(this.cards) > that.straight(that.cards)) return 1;
                else if(this.straight(this.cards) < that.straight(that.cards)) return -1;
                else{ 

                    if(this.pair(this.cards) > that.pair(that.cards)) return 1;
                    else if(this.pair(this.cards) < that.pair(that.cards)) return -1;
                    return 0;
                }
            }
        }

    }

    private static int cardface(String face) {
        int facenum;

        if (face.equals(""A"")) {
            facenum = 14;
        } else if (face.equals(""J"")) {
            facenum = 11;
        } else if (face.equals(""Q"")) {
            facenum = 12;
        } else if (face.equals(""K"")) {
            facenum = 13;
        } else {
            facenum = Integer.parseInt(face);
        }

        return facenum;
    }

    private static int cardsuit(String suit) {
        int suitnum;

        if (suit.equals(""Spades"")) {
            suitnum = 4;
        } else if (suit.equals(""Hearts"")) {
            suitnum = 3;
        } else if (suit.equals(""Diamonds"")) {
            suitnum = 2;
        } else {
            suitnum = 1;
        }

        return suitnum;
    }
    
    private int fullhouse(Card[] card){
        int cardnum[]=new int[5];
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);
                
        if ((cardnum[0] == cardnum[1]) && (cardnum[3] == cardnum[4])) {
            if((cardnum[2] == cardnum[1]) || (cardnum[2] == cardnum[3]))
                return cardnum[2];
        } 
            return 0;
    }
    
    private int flush(Card[]card){
        int suitnum = cardsuit(card[0].getSuit());
        int cardnum = cardface(card[0].getFace());
        int i=1;
        
        while(cardsuit(card[i].getSuit()) == suitnum){
            if(cardnum < cardface(card[i].getFace())) cardnum=cardface(card[i].getFace());
            i++;
            if(i==5) return (cardnum*10+suitnum);
        }
        
        return 0;
    }

    private int straight(Card[]card){
        int cardnum[]=new int[5];
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);

        int i=1;
        while(cardnum[i] == cardnum[i-1]+1){
            //StdOut.println(cardnum[i]);
            if(i==4) return (cardsuit(card[4].getSuit()) + cardnum[4]*10);          
            i++;       
        }

        return 0;
    }
    
    private int pair(Card[]card){
        int cardnum[]=new int[5];
        
        for (int i = 0; i < 5; i++) {
            cardnum[i] = cardface(card[i].getFace());
        }
        Arrays.sort(cardnum);
        
        if(cardnum[0]==cardnum[1]){
            if(cardnum[2]==cardnum[3]) return (cardsuit(card[3].getFace()) + cardnum[3]*10+1000);
            else if(cardnum[3]==cardnum[4]) return (cardsuit(card[4].getFace()) + cardnum[4]*10+1000);
            else return (cardsuit(card[1].getFace()) + cardnum[1]*10);
        }
        else if(cardnum[1]==cardnum[2]){
            if(cardnum[3]==cardnum[4]) return (cardsuit(card[4].getSuit()) + cardnum[4]*10+1000);
            else return (cardsuit(card[1].getFace()) + cardnum[1]*10);
        }
        if(cardnum[2]==cardnum[3]) return (cardsuit(card[3].getSuit()) + cardnum[3]*10);
        if(cardnum[3]==cardnum[4]) return (cardsuit(card[4].getSuit()) + cardnum[4]*10);
        
        return 0;
    }
    
}

"b02611019","0.092","105280","@bd3d6f78afde07df75fa8cef65c66de0@
import java.util.Arrays;
import java.util.Collections;

class intCard {

    int intface;
    int intsuit;

    intCard(int intface, int intsuit) {
        this.intface = intface;
        this.intsuit = intsuit;
    }
}

public class Player implements Comparable<Player> {

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
        Arrays.sort(this.cards);
//        for(int i=0;i<5;i++){
//        StdOut.print(this.cards[i].getFace()+this.cards[i].getSuit()+""  "");
//        }
//        StdOut.print(""\n"");
    }

    public int suit2int(Card card) {
        switch (card.getSuit()) {
            case ""Spades"":
                return 3;
            case ""Hearts"":
                return 2;
            case ""Diamonds"":
                return 1;
            case ""Clubs"":
                return 0;
        }
        return 0;
    }

    public int face2int(Card card) {
        switch (card.getFace()) {
            case ""A"":
                return 14;
            case ""K"":
                return 13;
            case ""Q"":
                return 12;
            case ""J"":
                return 11;
            case ""10"":
                return 10;
            case ""9"":
                return 9;
            case ""8"":
                return 8;
            case ""7"":
                return 7;
            case ""6"":
                return 6;
            case ""5"":
                return 5;
            case ""4"":
                return 4;
            case ""3"":
                return 3;
            case ""2"":
                return 2;
        }
        return 0;
    }

    public boolean fullhouse(int[] intface) {
        if (intface[0] == intface[1] && intface[3] == intface[4] && (intface[1] == intface[2] || intface[2] == intface[3])) {
            return true;
        }
        return false;
    }

    public boolean flush(int[] intsuit) {
        if (intsuit[0] == intsuit[1] && intsuit[1] == intsuit[2] && intsuit[2] == intsuit[3] && intsuit[3] == intsuit[4]) {
            return true;
        }
        return false;
    }

    public boolean straight(int[] intface) {
        for (int i = 0; i <= 3; i++) {
            if (intface[i]+1 != intface[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean twopair(int[] intface, Card[] cards) {
        if (intface[0] == intface[1]) {
            if (intface[3] == intface[4]) {
                thissize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            } 
            if (intface[2] == intface[3]) {
                thissize = new Card(cards[3].getFace(), cards[3].getSuit());
                return true;
            }
        }
        if(intface[1] == intface[2]){
            if (intface[3] == intface[4]) {
                thissize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            } 
        }
        return false;
    }
    public boolean thattwopair(int[] intface, Card[] cards) {
        if (intface[0] == intface[1]) {
            if (intface[3] == intface[4]) {
                thatsize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            } 
            if (intface[2] == intface[3]) {
                thatsize = new Card(cards[3].getFace(), cards[3].getSuit());
                return true;
            }
        }
        if(intface[1] == intface[2]){
            if (intface[3] == intface[4]) {
                thatsize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            } 
        }
        return false;
    }
    public boolean pair(int[] intface, Card[] cards) {
        for(int i=0;i<=3;i++){
            if(intface[i]==intface[i+1]){
                thissize = new Card(cards[i+1].getFace(), cards[i+1].getSuit());
                return true;
            }
        }
        return false;
    }
    
    
    public boolean thatpair(int[] intface, Card[] cards) {
        for(int i=0;i<=3;i++){
            if(intface[i]==intface[i+1]){
                thatsize = new Card(cards[i+1].getFace(), cards[i+1].getSuit());
                return true;
            }
        }
        return false;
    }

    static Card thissize;
    static Card thatsize;

    // TODO 

    public int compareTo(Player that) {

        
.
        int thisord = 1;
        int thatord = 1;
        int[] thisintface = new int[5];
        int[] thisintsuit = new int[5];
        int[] thatintface = new int[5];
        int[] thatintsuit = new int[5];

        for (int i = 0; i < 5; i++) {
            thisintface[i] = face2int(this.cards[i]);
            thatintface[i] = face2int(that.cards[i]);
            thisintsuit[i] = suit2int(this.cards[i]);
            thatintsuit[i] = suit2int(that.cards[i]);
        }
        //Check this size
        thissize = new Card(this.cards[4].getFace(),this.cards[4].getSuit());
        if(pair(thisintface, this.cards)){
            thisord =2;
        }
        if (twopair(thisintface, this.cards)) {
            thisord =3;
        }
        if (straight(thisintface)) {
            thisord = 4;
            thissize = new Card(this.cards[4].getFace(), this.cards[4].getSuit());
        }
        if (flush(thisintsuit)) {
            thisord = 5;
            thissize = new Card(this.cards[4].getFace(), this.cards[4].getSuit());
        }
        if (fullhouse(thisintface)) {
            thisord = 6;
            thissize = new Card(this.cards[2].getFace(), this.cards[2].getSuit());
        }
        //check that size
        thatsize = new Card(that.cards[4].getFace(),that.cards[4].getSuit());
        if(thatpair(thatintface, that.cards)){
            thatord =2;
        }
        if (thattwopair(thatintface, that.cards)) {
            thatord =3;
        }
        if (straight(thatintface)) {
            thatord = 4;
            thatsize = new Card(that.cards[4].getFace(), that.cards[4].getSuit());
        }
        if (flush(thatintsuit)) {
            thatord = 5;
            thatsize = new Card(that.cards[4].getFace(), that.cards[4].getSuit());
        }
        if (fullhouse(thatintface)) {
            thatord = 6;
            thatsize = new Card(that.cards[2].getFace(), that.cards[2].getSuit());
        }
        
        if(thisord>thatord){
            return +1;
        }
        if(thisord<thatord){
            return -1;
        }
        return(thissize.compareTo(thatsize));
    }
}

"r04228002","0.088","105296","@44fdfb2091aa11b1d4ca8bb00db5d470@
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */


/**
 *
 * @author Lenovo
 */
public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private int level;
    private int big;
    
    
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
        Arrays.sort(cards,Card.SUIT_ORDER);
        Arrays.sort(cards);
        
        this.cards = cards;
        this.level = judgeLevel(cards);
    }
    
    public Card[] getCards(){
        return this.cards;
    }
    
    public int getLevel(){
        return this.level;
    }
    
    public int getBig(){
        return this.big;
    }
    
    private static int transFace(String ff){
        int intface = 0;
        if (ff.equals(""J"")){
            intface = 11;
        }
        else if (ff.equals(""Q"")){
            intface = 12;
        }
        else if (ff.equals(""K"")){
            intface = 13;
        }
        else if (ff.equals(""A"")){
            intface = 14;
        }
        else{
            intface = Integer.parseInt(ff);
        }
        return intface;
    }
            
    private int judgeLevel(Card[] cards){
        int out = 0;
        Card C1 = cards[0];
        Card C2 = cards[1];
        Card C3 = cards[2];
        Card C4 = cards[3];
        Card C5 = cards[4];
        if ((C1.getFace().equals(C2.getFace()))&&(C3.getFace().equals(C4.getFace()))&&
                (C4.getFace().equals(C5.getFace()))){
            this.big=4;
            out = 6;
        }
        
        else if ((C4.getFace().equals(C5.getFace()))&&(C1.getFace().equals(C2.getFace()))&&
                (C2.getFace().equals(C3.getFace()))){
            this.big=2;
            out = 6;
        }
        
        else if ((C1.getSuit().equals(C2.getSuit()))&&(C1.getSuit().equals(C3.getSuit()))&&
                (C1.getSuit().equals(C4.getSuit()))&&(C1.getSuit().equals(C5.getSuit()))){
            this.big=4;
            out = 5;
        }
        
        else if ((transFace(C1.getFace())+1==transFace(C2.getFace()))&&
                (transFace(C2.getFace())+1==transFace(C3.getFace()))&&
                (transFace(C3.getFace())+1==transFace(C4.getFace()))){
            if (transFace(C4.getFace())+1==transFace(C5.getFace())){
                this.big=4;
                out = 4;
            }
            else if (transFace(C5.getFace())-12==transFace(C1.getFace())){
                this.big=3;
                out = 4;
            }    
        }
        
        else if ((C5.getFace().equals(C4.getFace()))&&
                ((C3.getFace().equals(C2.getFace()))||
                (C2.getFace().equals(C1.getFace())))){
            this.big=4;
            out = 3;
        }
        
        else if ((C1.getFace().equals(C2.getFace()))&&
                (C3.getFace().equals(C4.getFace()))){
            this.big=3;
            out = 3;
        }
        
        else if (C1.getFace().equals(C2.getFace())){
            this.big=1;
            out = 2;
        }
        
        else if (C2.getFace().equals(C3.getFace())){
            this.big=2;
            out = 2;
        }
                
        else if (C3.getFace().equals(C4.getFace())){
            this.big=3;
            out = 2;
        } 
        
        else if (C4.getFace().equals(C5.getFace())){
            this.big=4;
            out = 2;
        }                
        
        else{
            this.big=4;
            out = 1;
        }
        return out;
    }
            
    // TODO 
    public int compareTo(Player that) {
.
        if (this.level > that.level){
            return 1;
        }
        else if (this.level==that.level){
                        
            Card C1 = this.getCards()[this.getBig()];
            Card C2 = that.getCards()[that.getBig()];
            return C1.compareTo(C2);   
        }
        else{
            return -1;
        }
        //return 0;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Card[] cardsArray = new Card[5];
        /*
        cardsArray[0] = new Card(""A"",""Hearts"");
        cardsArray[1] = new Card(""Q"",""Hearts"");
        cardsArray[2] = new Card(""10"",""Hearts"");
        cardsArray[3] = new Card(""7"",""Hearts"");
        cardsArray[4] = new Card(""2"",""Hearts"");
        */
        /*
        cardsArray[0] = new Card(""A"",""Spades"");
        cardsArray[1] = new Card(""A"",""Hearts"");
        cardsArray[2] = new Card(""A"",""Diamonds"");
        cardsArray[3] = new Card(""2"",""Clubs"");
        cardsArray[4] = new Card(""2"",""Hearts"");
        */
        
        cardsArray[0] = new Card(""A"",""Spades"");
        cardsArray[1] = new Card(""4"",""Hearts"");
        cardsArray[2] = new Card(""4"",""Diamonds"");
        cardsArray[3] = new Card(""3"",""Clubs"");
        cardsArray[4] = new Card(""2"",""Hearts"");
        
        Player P = new Player(""Felix"");
        P.setCards(cardsArray);
        for (int i=0;i<5;i++){
            Card temp = P.getCards()[i];
            System.out.println(temp.getFace());
            System.out.println(temp.getSuit());
            System.out.println(""Next"");
        }
        System.out.println(P.getLevel());
        System.out.println(P.getBig());
    }
}
    
    


"b02611019","0.084","105264","@618a78c7659c90befbf7aa7564371b25@
import java.util.Arrays;
import java.util.Collections;

public class Player implements Comparable<Player> {

    class intCard {

        int intface;
        int intsuit;

        intCard(int intface, int intsuit) {
            this.intface = intface;
            this.intsuit = intsuit;
        }
    }
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
        Arrays.sort(this.cards);
//        for(int i=0;i<5;i++){
//        StdOut.print(this.cards[i].getFace()+this.cards[i].getSuit()+""  "");
//        }
//        StdOut.print(""\n"");
    }

    public int suit2int(Card card) {
        switch (card.getSuit()) {
            case ""Spades"":
                return 3;
            case ""Hearts"":
                return 2;
            case ""Diamonds"":
                return 1;
            case ""Clubs"":
                return 0;
        }
        return 0;
    }

    public int face2int(Card card) {
        switch (card.getFace()) {
            case ""A"":
                return 14;
            case ""K"":
                return 13;
            case ""Q"":
                return 12;
            case ""J"":
                return 11;
            case ""10"":
                return 10;
            case ""9"":
                return 9;
            case ""8"":
                return 8;
            case ""7"":
                return 7;
            case ""6"":
                return 6;
            case ""5"":
                return 5;
            case ""4"":
                return 4;
            case ""3"":
                return 3;
            case ""2"":
                return 2;
        }
        return 0;
    }

    public boolean fullhouse(int[] intface) {
        if (intface[0] == intface[1] && intface[3] == intface[4] && (intface[1] == intface[2] || intface[2] == intface[3])) {
            return true;
        }
        return false;
    }

    public boolean flush(int[] intsuit) {
        if (intsuit[0] == intsuit[1] && intsuit[1] == intsuit[2] && intsuit[2] == intsuit[3] && intsuit[3] == intsuit[4]) {
            return true;
        }
        return false;
    }

    public boolean straight(int[] intface) {
        for (int i = 0; i <= 3; i++) {
            if (intface[i] + 1 != intface[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public boolean twopair(int[] intface, Card[] cards) {
        if (intface[0] == intface[1]) {
            if (intface[3] == intface[4]) {
                thissize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            }
            if (intface[2] == intface[3]) {
                thissize = new Card(cards[3].getFace(), cards[3].getSuit());
                return true;
            }
        }
        if (intface[1] == intface[2]) {
            if (intface[3] == intface[4]) {
                thissize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            }
        }
        return false;
    }

    public boolean thattwopair(int[] intface, Card[] cards) {
        if (intface[0] == intface[1]) {
            if (intface[3] == intface[4]) {
                thatsize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            }
            if (intface[2] == intface[3]) {
                thatsize = new Card(cards[3].getFace(), cards[3].getSuit());
                return true;
            }
        }
        if (intface[1] == intface[2]) {
            if (intface[3] == intface[4]) {
                thatsize = new Card(cards[4].getFace(), cards[4].getSuit());
                return true;
            }
        }
        return false;
    }

    public boolean pair(int[] intface, Card[] cards) {
        for (int i = 0; i <= 3; i++) {
            if (intface[i] == intface[i + 1]) {
                thissize = new Card(cards[i + 1].getFace(), cards[i + 1].getSuit());
                return true;
            }
        }
        return false;
    }

    public boolean thatpair(int[] intface, Card[] cards) {
        for (int i = 0; i <= 3; i++) {
            if (intface[i] == intface[i + 1]) {
                thatsize = new Card(cards[i + 1].getFace(), cards[i + 1].getSuit());
                return true;
            }
        }
        return false;
    }

    static Card thissize;
    static Card thatsize;

    // TODO 
    public int compareTo(Player that) {

.
        int thisord = 1;
        int thatord = 1;
        int[] thisintface = new int[5];
        int[] thisintsuit = new int[5];
        int[] thatintface = new int[5];
        int[] thatintsuit = new int[5];

        for (int i = 0; i < 5; i++) {
            thisintface[i] = face2int(this.cards[i]);
            thatintface[i] = face2int(that.cards[i]);
            thisintsuit[i] = suit2int(this.cards[i]);
            thatintsuit[i] = suit2int(that.cards[i]);
        }
        //Check this size
        thissize = new Card(this.cards[4].getFace(), this.cards[4].getSuit());
        if (pair(thisintface, this.cards)) {
            thisord = 2;
        }
        if (twopair(thisintface, this.cards)) {
            thisord = 3;
        }
        if (straight(thisintface)) {
            thisord = 4;
            thissize = new Card(this.cards[4].getFace(), this.cards[4].getSuit());
        }
        if (flush(thisintsuit)) {
            thisord = 5;
            thissize = new Card(this.cards[4].getFace(), this.cards[4].getSuit());
        }
        if (fullhouse(thisintface)) {
            thisord = 6;
            thissize = new Card(this.cards[2].getFace(), this.cards[2].getSuit());
        }
        //check that size
        thatsize = new Card(that.cards[4].getFace(), that.cards[4].getSuit());
        if (thatpair(thatintface, that.cards)) {
            thatord = 2;
        }
        if (thattwopair(thatintface, that.cards)) {
            thatord = 3;
        }
        if (straight(thatintface)) {
            thatord = 4;
            thatsize = new Card(that.cards[4].getFace(), that.cards[4].getSuit());
        }
        if (flush(thatintsuit)) {
            thatord = 5;
            thatsize = new Card(that.cards[4].getFace(), that.cards[4].getSuit());
        }
        if (fullhouse(thatintface)) {
            thatord = 6;
            thatsize = new Card(that.cards[2].getFace(), that.cards[2].getSuit());
        }

        if (thisord > thatord) {
            return +1;
        }
        if (thisord < thatord) {
            return -1;
        }
        return (thissize.compareTo(thatsize));
    }
}

"b01703032","0.1","105328","@f39862e44077d01c62dad930be9e935c@import java.util.*;

public class Player implements Comparable<Player>{

    private Card[] cards = new Card[5];
    private String name;

    enum CardType {
        full_house , flush , straight , two_pair , one_pair , high_card
    }

    private CardType cardType;
    public CardQuery cardQuery = new CardQuery();

    private static final SuitOrder SUIT_ORDER = new SuitOrder();
    private static final FaceOrder FACE_ORDER = new FaceOrder();
    private static final CardTypeOrder CARD_TYPE_ORDER = new CardTypeOrder();

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

    public CardType getCardType(){ return this.cardType; }

    public void setCardType(){
        Map<String,Integer> faces = cardQuery.getFaceCount();
        if (faces.size() == 2) {
            // (4,1) or (3,2)
            if (cardQuery.getThrees().size() != 0) {
                cardType = CardType.full_house;
                return;
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 3){
            // (3,1,1) or (2,2,1)
            if (cardQuery.getPairs().size() != 0) {
                cardType = CardType.two_pair;
                return;
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 4){
            // (2,1,1,1)
            cardType = CardType.one_pair;
        } else if (faces.size() == 5){

            // check for flush
            if (cardQuery.getSuitSet().size() == 1) {
                cardType = CardType.flush;
                return;
            }

            // check for straight
            String[] straights = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2"",""1""};
            Set<String> faceSet = faces.keySet();
            for(int i = 0; i < straights.length - 5 ; i++){
                Set<String> straightOne = new HashSet<String>();
                for(int j = i ; j < i + 5 ; j++){
                    straightOne.add(straights[j]);
                }
                if (faceSet.containsAll(straightOne)) {
                   cardType = CardType.straight;
                   return;
                }
            }

            // high card otherwise
            cardType = CardType.high_card;
        }
    }

    // TODO 
    public int compareTo(Player that) {
.

        this.setCardType();
        that.setCardType();
        int result = CARD_TYPE_ORDER.compare(this.getCardType(), that.getCardType());
        if (result != 0){
            return result;
        } else {
            // broke ties
            switch (this.getCardType()) {
                case full_house:
                    String max1 = Collections.max(this.cardQuery.getThrees() , FACE_ORDER);
                    String max2 = Collections.max(that.cardQuery.getThrees() , FACE_ORDER);
                    result = FACE_ORDER.compare(max1 , max2);
                    return result;

                case flush:
                    String flush1 = Collections.max(this.cardQuery.getSuitSet() , FACE_ORDER);
                    String flush2 = Collections.max(that.cardQuery.getSuitSet() , FACE_ORDER);
                    result = SUIT_ORDER.compare(flush1 , flush2);
                    if (result != 0) {
                        return result;
                    } else {
                        Set<String> suit1 = this.cardQuery.getSuitSet(flush1);
                        Set<String> suit2 = that.cardQuery.getSuitSet(flush2);
                        result = SUIT_ORDER.compare(Collections.max(suit1, SUIT_ORDER) , Collections.max(suit2, SUIT_ORDER));
                        return result;
                    }

                case two_pair:
                case one_pair:
                    String pair1 = Collections.max(this.cardQuery.getPairs() , FACE_ORDER);
                    String pair2 = Collections.max(that.cardQuery.getPairs() , FACE_ORDER);
                    result = FACE_ORDER.compare(pair1 , pair2);
                    if (result != 0) {
                       return result;
                    } else {
                        Set<String> suit1 = this.cardQuery.getSuitSet(pair1);
                        Set<String> suit2 = that.cardQuery.getSuitSet(pair1);
                        result = SUIT_ORDER.compare(Collections.max(suit1, SUIT_ORDER) , Collections.max(suit2, SUIT_ORDER));
                        return result;
                    }

                case high_card:
                case straight:

                    String high_card1 = Collections.max(this.cardQuery.getOnes() , FACE_ORDER);
                    String high_card2 = Collections.max(that.cardQuery.getOnes() , FACE_ORDER);

                    result = FACE_ORDER.compare(high_card1 , high_card2);
                    if (result != 0) {
                        return result;
                    } else {
                        Set<String> suit1 = this.cardQuery.getSuitSet(high_card1);
                        Set<String> suit2 = that.cardQuery.getSuitSet(high_card2);
                        result = SUIT_ORDER.compare(Collections.max(suit1, SUIT_ORDER) , Collections.max(suit2, SUIT_ORDER));
                        return result;
                    }
            }
        }
        return 0;
    }



    private static class SuitOrder implements Comparator<String> {
        private static List<String> order;
        SuitOrder() {
            String[] suits = {""Spades"", ""Hearts"", ""Diamonds"", ""Clubs""};
            order = new ArrayList<String>();
            for (String suit : suits) {
                order.add(suit);
            }
        }

        @Override
        public int compare(String suit1, String suit2) {
            int suit_1 = order.indexOf(suit1);
            int suit_2 = order.indexOf(suit2);
            if (suit_1 < suit_2) return 1;
            else if (suit_1 > suit_2) return -1;
            else return 0;
        }
    }

    private static class FaceOrder implements Comparator<String> {
        private static List<String> order;
        FaceOrder() {
            String[] faces = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2"",""1""};
            order = new ArrayList<String>();
            for (String face : faces) {
                order.add(face);
            }
        }

        @Override
        public int compare(String face1, String face2) {
            int face_1 = order.indexOf(face1);
            int face_2 = order.indexOf(face2);
            if (face_1 < face_2) return 1;
            else if (face_1 > face_2) return -1;
            else return 0;
        }
    }


    private static class CardTypeOrder implements Comparator<CardType> {
        private static List<CardType> order;
        CardTypeOrder() {
            CardType[] cardTypes = {
                    CardType.full_house,
                    CardType.flush,
                    CardType.straight,
                    CardType.two_pair,
                    CardType.one_pair,
                    CardType.high_card
            };
            order = new ArrayList<CardType>();
            for (CardType cardType : cardTypes) {
                order.add(cardType);
            }
        }

        @Override
        public int compare(CardType cardType1, CardType cardType2) {
            int cardType_1 = order.indexOf(cardType1);
            int cardType_2 = order.indexOf(cardType2);
            if (cardType_1 < cardType_2) return 1;
            else if (cardType_1 > cardType_2) return -1;
            else return 0;
        }
    }


    private class CardQuery {

        public Map<String , Integer> getFaceCount(){
            Map<String,Integer> faces = new HashMap<String,Integer>();
            for(Card c:cards){
                if (faces.containsKey(c.getFace()))
                    faces.put(c.getFace() , faces.get(c.getFace())+1);
                else
                    faces.put(c.getFace() , 1);
            }
            return faces;
        }

        public Set<String> getSuitSet(){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        public Set<String> getSuitSet(String face){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                if (face.equals(c.getFace()))
                    suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        public Set<String> getOnes(){
            Set<String> ones = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 1)
                    ones.add(entry.getKey());
            }
            return ones;
        }

        public Set<String> getPairs(){
            Set<String> pairs = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 2)
                    pairs.add(entry.getKey());
            }
            return pairs;
        }

        public Set<String> getThrees(){
            Set<String> threes = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 3)
                    threes.add(entry.getKey());
            }
            return threes;
        }

    }
}
"r04945009","0.094","104624","@82839df35883cb71d68d61a1de81728f@public class Player implements Comparable<Player>{ 

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
    
    public void sort() {
        int N = 5;
        for (int i=0; i<N; i++)
            for (int j=i; j>0; j--)
                if (this.cards[j].compareTo(this.cards[j-1]) == -1) {
                    exch(j, j-1);
                }
    }
    
    public void exch(int i , int j) {
        Card swap = this.cards[i];
        this.cards[i] = this.cards[j];
        this.cards[j] = swap;
    }
    
    // Full House
    public boolean fullHouse() {
        if (!this.cards[0].getFace().equals(this.cards[1].getFace())) return false;
        if (!this.cards[3].getFace().equals(this.cards[4].getFace())) return false;
        return !(!this.cards[2].getFace().equals(this.cards[1].getFace()) && this.cards[2].getFace() != this.cards[3].getFace());
    }
    
    public int compare_fullHouse(Player that) {
        return this.cards[2].compareTo(that.cards[2]);
    }
    
    // Flush
    public boolean flush() {
        String first = this.cards[0].getSuit();
        for (int i=1; i<5; i++) {
            if (!first.equals(this.cards[i].getSuit())) return false;
        }
        return true;
    }
    
    public int compare_flush(Player that) {
        return this.cards[4].compareTo(that.cards[4]);
    }
    
    
    // Straight
    private final String[] faceOrder = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
    public boolean straight() {
        int faceNum = 0;
        String first = this.cards[0].getFace();
        for (int i=0; i<13; i++) {
            if (first.equals(faceOrder[i])) {
                faceNum = i;
                break;
            }
        }
        if (faceNum <= 5) return false;
        if (faceNum == 12) {
            int count = 0;
            for (int i=1; i<5; i++) {
                if (!this.cards[i].getFace().equals(faceOrder[faceNum-i])) break; count++;
            }
            if (count==4) return true;
        }
        for (int i=1; i<5; i++) {
            if (!this.cards[i].getFace().equals(faceOrder[faceNum+i])) return false;
        }
        return true;
    }
    
    public int compare_straight(Player that) {
        return this.cards[4].compareTo(that.cards[4]);
    }
    
    // 2-Pair
    public boolean twoPair() {
        int count = 0;
        for (int i=0; i<4; i++) {
            if (this.cards[i].getFace().equals(this.cards[i+1].getFace())) count++;
        }
        if (count != 2) return false;
        for (int i=0; i<2; i++) {
            if (this.cards[i].getFace().equals(this.cards[i+1]) && this.cards[i+1].getFace().equals(this.cards[i+2])) return false;
        }
        return true;
    }
    
    public int compare_twoPair(Player that) {
        int thismax=0, thatmax=0;
        if (this.cards[4].getFace().equals(this.cards[3].getFace())) thismax = 4;
        if (this.cards[3].getFace().equals(this.cards[2].getFace())) thismax = 3;
        if (that.cards[4].getFace().equals(that.cards[3].getFace())) thatmax = 4;
        if (that.cards[3].getFace().equals(that.cards[2].getFace())) thatmax = 3;
        return this.cards[thismax].compareTo(that.cards[thatmax]);
    }
    
    // 1-Pair
    public boolean onePair(){
        int count = 0;
        for (int i=0; i<4; i++) {
            if (this.cards[i].getFace().equals(this.cards[i+1].getFace())) count++;
        }
        return count == 1;
    }
    
    public int compare_onePair(Player that) {
        int thismax=0, thatmax=0;
        for (int i=0; i<4; i++) {
            if (this.cards[4-i].getFace().equals(this.cards[3-i].getFace())) {
                thismax = 4-i; break;
            }
        }
        for (int i=0; i<4; i++) {
            if (that.cards[4-i].getFace().equals(that.cards[3-i].getFace())) {
                thatmax = 4-i; break;
            }
        }
        return this.cards[thismax].compareTo(that.cards[thatmax]);
    }
    
    // High Card
    public boolean highCard() {
        for (int i=0; i<4; i++) {
            if (this.cards[i].getFace().equals(this.cards[i+1].getFace())) return false;
        }
        return true;
    }
    
    public int compare_highCard(Player that) {
        return this.cards[4].compareTo(that.cards[4]);
    }
    
    public int checkTypes() {
        if (this.fullHouse()) return 6;
        if (this.flush()) return 5;
        if (this.straight()) return 4;
        if (this.twoPair()) return 3;
        if (this.onePair()) return 2;
        if (this.highCard()) return 1;
        return 0;
    }
    // TODO 
    public int compareTo(Player that) {
.
        this.sort();
        that.sort();
        int a = this.checkTypes();
        int b = that.checkTypes();
        if (this.checkTypes() < that.checkTypes()) return -1;
        if (this.checkTypes() > that.checkTypes()) return +1;
        if (this.checkTypes() == 6) return this.compare_fullHouse(that);
        if (this.checkTypes() == 5) return this.compare_flush(that);
        if (this.checkTypes() == 4) return this.compare_straight(that);
        if (this.checkTypes() == 3) return this.compare_twoPair(that);
        if (this.checkTypes() == 2) return this.compare_onePair(that);
        if (this.checkTypes() == 1) return this.compare_highCard(that);
        return 0;
    }
}

"b03611011","0.09","104800","@d07e5c3d036a927be586d7d5e0bf1fb0@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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
        //for full house
        int s1 = 0, h1 = 0, d1 = 0, c1 = 0;
        int[] o = new int[15];
        int mount = 0;
        int count = 0;
        int count1 = 0;
        int[] A = new int[5];
        for (int j = 0; j < 5; j++) {
            switch (that.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    A[j] = 14;
                    break;
                case ""J"":
                    A[j] = 11;
                    break;
                case ""Q"":
                    A[j] = 12;
                    break;
                case ""K"":
                    A[j] = 13;
                    break;
                default:
                    A[j] = Integer.parseInt(that.cards[j].getFace());
            }
        }
        for (int i = 0; i < 5; i++) {
            int min = i;
            for (int j = i + 1; j < 5; j++) {
                if (A[j] < A[i]) {
                    min = j;
                    int mem = A[i];
                    A[i] = A[min];
                    A[min] = mem;
                }
            }
        }
        int[] B = new int[5];
        for (int j = 0; j < 5; j++) {
            switch (this.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    B[j] = 14;
                    break;
                case ""J"":
                    B[j] = 11;
                    break;
                case ""Q"":
                    B[j] = 12;
                    break;
                case ""K"":
                    B[j] = 13;
                    break;
                default:
                    B[j] = Integer.parseInt(this.cards[j].getFace());
            }
        }
        for (int i = 0; i < 5; i++) {
            int min = i;
            for (int j = i + 1; j < 5; j++) {
                if (B[j] < B[i]) {
                    min = j;
                    int mem = B[i];
                    B[i] = B[min];
                    B[min] = mem;
                }
            }
        }
        for (int j = 0; j <= 14; j++) {//歸零
            o[j] = 0;
        }
        for (int j = 0; j < 5; j++) {
            switch (that.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    o[14]++;
                    break;
                case ""J"":
                    o[11]++;
                    break;
                case ""Q"":
                    o[12]++;
                    break;
                case ""K"":
                    o[13]++;
                    break;
                default:
                    o[Integer.parseInt(that.cards[j].getFace())]++;
            }
        }
        for (int j = 0; j <= 14; j++) {
            if (o[j] == 3) {
                count = count + 10000;
                mount = j;
            }
            if (o[j] == 2) {
                count = count + 100;
            }
            if (o[j] == 4) {
                count = count + 200+j;
            }
            if (count == 10100) {
                count = count + mount;
            }
        }

        mount = 0;
        for (int j = 0; j <= 14; j++) {//歸零
            o[j] = 0;
        }
        for (int j = 0; j < 5; j++) {
            switch (this.cards[j].getFace()) {//葫蘆算
                case ""A"":
                    o[14]++;
                    break;
                case ""J"":
                    o[11]++;
                    break;
                case ""Q"":
                    o[12]++;
                    break;
                case ""K"":
                    o[13]++;
                    break;
                default:
                    o[Integer.parseInt(this.cards[j].getFace())]++;
            }
        }
        for (int j = 0; j <= 14; j++) {
            if (o[j] == 3) {
                count1 = count1 + 10000;
                mount = j;
            }
            if (o[j] == 2) {
                count1 = count1 + 100;
            }
            if (o[j] == 4) {
                count1 = count1 + 200+j;
            }
            if (count1 == 10100) {
                count1 = count1 + mount;
            }
        }

        for (int i = 0; i < 5; i++) { //桐花
            switch (that.cards[i].getSuit()) {
                case ""Spades"":
                    s1++;
                    if (s1 == 5) {
                        count = count + 1000;
                    }
                    break;
                case ""Hearts"":
                    h1++;
                    if (h1 == 5) {
                        count = count + 1000;
                    }
                    break;
                case ""Diamonds"":
                    d1++;
                    if (d1 == 5) {
                        count = count + 1000;
                    }
                    break;
                case ""Clubs"":
                    c1++;
                    if (c1 == 5) {
                        count = count + 1000;
                    }
                    break;
            }
                        if(i==4){count=count+A[4];}
        }
        s1 = 0;
        h1 = 0;
        d1 = 0;
        c1 = 0;
        for (int i = 0; i < 5; i++) {
            switch (this.cards[i].getSuit()) {
                case ""Spades"":
                    s1++;
                    if (s1 >= 5) {
                        count1 = count1 + 1000;
                    }
                    break;
                case ""Hearts"":
                    h1++;
                    if (h1 >= 5) {
                        count1 = count1 + 1000;
                    }
                    break;
                case ""Diamonds"":
                    d1++;
                    if (d1 >= 5) {
                        count1 = count1 + 1000;
                    }
                    break;
                case ""Clubs"":
                    c1++;
                    if (c1 >= 5) {
                        count1 = count1 + 1000;
                    }
                    break;
            }
                        if(i==4){count1=count1+B[4];}
        }
        //順子
//                mount=0;
        for (int k = 0; k < 4; k++) {
            if (A[k + 1] == A[k] + 1) {
                mount++;
            }
        }
        if (mount == 4) {
            count = count + 500 + A[4];
        }
        mount = 0;
        for (int k = 0; k < 4; k++) {
            if (B[k + 1] == B[k] + 1) {
                mount++;
            }

        }
        if (mount == 4) {
            count1 = count1 + 500 + B[4];
        }
        if(count<100&&count1<100){
        count=A[4]*10;
        count1=B[4]*10;
        }
//            System.out.println(A[0]+"" ""+A[1]+"" ""+A[2]+"" ""+A[3]+"" ""+A[4]);
//System.out.println(B[0]+"" ""+B[1]+"" ""+B[2]+"" ""+B[3]+"" ""+B[4]);
//            System.out.println(count+"" ""+count1);
//        System.out.println(that.name + that.cards[0].getFace());
//        System.out.println(this.name + this.cards[0].getFace());//回傳出來 且下一個的輸入
        if (count > count1) {
            this.cards = that.cards;
            this.name = that.name;
//            System.out.println(this.name + this.cards[0].getFace()+""  ""+count+"" ""+count1);//回傳出來 且下一個的輸入
            return 0;
        }

        return 0;
    }
}

"r04631021","0.098","105088","@7905d0f75820c3bf3b84c3739be3ffd2@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    public Card[] HighCard = new Card[1] ;
    
    public void HighCard (Card cards) {
        this.HighCard[0] = cards;
    }
     
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
    public boolean isFullHouse(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards);
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace()))
                    this.HighCard(Cards[0]) ;
                return true ;
            }
            else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                    this.HighCard(Cards[4]) ;
                    return true ;
                }
            }
        }
        return false ;
    }
    public boolean isFlush(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getSuit().equalsIgnoreCase(Cards[1].getSuit())){
            if(Cards[1].getSuit().equalsIgnoreCase(Cards[2].getSuit())){
                if(Cards[2].getSuit().equalsIgnoreCase(Cards[3].getSuit())){
                 if(Cards[3].getSuit().equalsIgnoreCase(Cards[4].getSuit())){
                        this.HighCard(Cards[4]) ;
            return true ;
                    }
                }
            }
        }
            return false ;
        }
    public boolean isStraight(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
      
        MergeX.sort(Cards);
        Integer[] a1 = new Integer[Cards.length] ;
        for(int i = 0 ; i < 5 ; i++){
            if(Cards[i].getFace().equalsIgnoreCase(""J""))
                a1[i] = 11 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""Q""))
                a1[i] = 12 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""K""))
                a1[i] = 13 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""A""))
                a1[i] = 14 ;
            else
            a1[i] = Integer.parseInt(Cards[i].getFace());            
        }
        if(a1[1]==a1[0]+1){
            if(a1[2] == a1[1]+1){
                if(a1[3]== a1[2]+1){
                    if(a1[4] == a1[3]+1){
                        this.HighCard(Cards[4]) ;
                        return true ;
                    }
                }
            }
        }
        else if(a1[0]==2){
            if(a1[1]==3){
                if(a1[2]==4){
                    if(a1[3]==5){
                        if(a1[4]==14){
                            this.HighCard(Cards[3]) ;
                            return true ;
                        }
                    }
                }
            }
        }
        return false ;
    }
    public boolean isTwoPair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                this.HighCard(Cards[3]) ;
                return true ;
            }
            else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
       }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
        }
        return false;
    }
    public boolean isOnePair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            this.HighCard(Cards[1]) ;
            return true ;
        }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            this.HighCard(Cards[2]) ;
            return true ;
        }
        else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
            this.HighCard(Cards[3]) ;
            return true ;
        }
        else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
            this.HighCard(Cards[4]) ;
            return true ;
        }
        
        return false ;
    }
    // TODO 
    public int compareTo(Player that) {
        
.
        
            int[] Points = new int [2] ;
            if(this.isFullHouse())
                Points[0] = 60 ;
            
            else if(this.isFlush())
                Points[0] = 50 ;
            
            else if(this.isStraight())
                Points[0] = 40 ;
            
            else if(this.isTwoPair()){
                Points[0] = 30 ;
            }
            else if(this.isOnePair()){
                Points[0] = 20 ;
            
            }
            else
                Points[0] = 10 ;
            
            
            if(that.isFullHouse())
                Points[1] = 60 ;
            
            else if(that.isFlush())
                Points[1] = 50 ;
            
            else if(that.isStraight())
                Points[1] = 40 ;
            
            else if(that.isTwoPair()){
                Points[1] = 30 ;
            }
            else if(that.isOnePair()){
                Points[1] = 20 ;

    }
            else 
                Points[1] = 10 ;
            
//            System.out.println(Points[0]+"" ""+Points[1] ) ;
            
            if(Points[0]>Points[1])
                return 1 ;
            else if(Points[0]<Points[1])
                return -1 ;
            
            else if(Points[0]==Points[1])
            if(Points[0] == 10){
                MergeX.sort(this.cards);
                MergeX.sort(that.cards);
                this.HighCard(this.cards[4]) ;
                that.HighCard(that.cards[4]) ;
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
                }
            else 
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
            return 0 ;
    }
   
     public static void main(String[] args) {
     Player[] a = new Player[2] ;
     a[0] = new Player(""John Cena"") ;
     a[1] = new Player(""undertaker"") ;
     Card[] fuck = new Card[5];
     Card[] fuck1 = new Card[5] ;
     fuck[0] = new Card(""10"",""Spades"") ;
     fuck[1] = new Card(""A"",""Hearts"") ;
     fuck[2] = new Card(""9"",""Diamonds"") ;
     fuck[3] = new Card(""7"",""Spades"") ;
     fuck[4] = new Card(""5"",""Clubs"") ;
     a[0].setCards(fuck);
     fuck1[0] = new Card(""4"",""Spades"") ;
     fuck1[1] = new Card(""K"",""Hearts"") ;
     fuck1[2] = new Card(""J"",""Clubs"") ;
     fuck1[3] = new Card(""3"",""Spades"") ;
     fuck1[4] = new Card(""Q"",""Hearts"") ;
     a[1].setCards(fuck1);
//     for (int i = 0 ; i < 5 ; i++){
//     System.out.println(a[0].cards[i].getFace());
//     System.out.println(a[1].cards[i].getFace());
//     }
//     System.out.println(a[0].compareTo(a[1]));
//     System.out.println(a[1].HighCard[0].compareTo(a[0].HighCard[0]));
}
}


"r04945021","0.096","110224","@8478bcb866d4b91dcba7ec783a64a7e8@
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
       // System.out.println(thi);
    }
     
    // TODO 
    public int compareTo(Player that) {
        //對兩個傳進來做sorted
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
//        System.out.println(this.name);
//
//       for (int i =0;i<5;i++){
//           System.out.println(that.cards[i].getFace());
//        System.out.println(that.cards[i].getSuit());
//       }
        int SameThisCard = 0;
        int SameThatCard = 0;
        int SameThisID=-1;
        int SameThatID=-1;
        //Arrays.sort(this.cards);
        //只要用前3個卡，掃後三個，即可知有無full house
        for (int i = 0; i < 3; i++) {
                        SameThisCard = 0;
            SameThatCard = 0;
            for (int j = i + 1; j < 5; j++) {
                
                if (this.cards[i].getFace().equals(this.cards[j].getFace())) {
                    SameThisCard++;
                    //System.out.println(this.name);
                }
                if (that.cards[i].getFace().equals(that.cards[j].getFace())) {
                    SameThatCard++;
                }
            }
            if (SameThisCard == 2) {
                SameThisID = i;
            } else if (SameThatCard == 2) {
                SameThatID = i;
            }

        }
        if (SameThisID!=-1&&SameThatID!=-1) {
                return this.cards[SameThisID].compareTo(that.cards[SameThatID]);
        }else if(SameThisID!=-1){
            return 1;}
        else if(SameThatID!=-1){

            return -1;          
        }

        //flush   
        SameThisCard = 0;
        SameThatCard = 0;
        for (int i = 0; i < 4; i++) {
            if (this.cards[i].getSuit().equals(this.cards[i + 1].getSuit())) {
                SameThisCard++;
            }
            if (that.cards[i].getSuit().equals(that.cards[i + 1].getSuit())) {
                SameThatCard++;
            }
        }
//                    System.out.println(this.name);
        //System.out.println(SameThatCard);

        if (SameThisCard == 4 && SameThatCard == 4) {
            return this.cards[4].compareTo(that.cards[4]);
        } else if (SameThisCard == 4) {
            return 1;
        } else if (SameThatCard == 4) {
            return -1;
        }
        
    //Straight
        SameThisCard = 0;
        SameThatCard = 0;

        for (int i = 0; i < 4; i++) {
            if(i==3 && this.cards[i].getFace().equals(""5"") && this.cards[i+1].getFace().equals(""A""))
             {
                SameThisCard++;
            } else if (this.cards[i].getFace().equals(""10"") && this.cards[i + 1].getFace().equals(""J"")) {
                SameThisCard++;
            } else if (this.cards[i].getFace().equals(""J"") && this.cards[i + 1].getFace().equals(""Q"")) {
                SameThisCard++;
            } else if (this.cards[i].getFace().equals(""Q"") && this.cards[i + 1].getFace().equals(""K"")) {
                SameThisCard++;}
            else if(this.cards[i].getFace().equals(""K"") && this.cards[i + 1].getFace().equals(""A"")){
                SameThisCard++;
            } else if (!this.cards[i].getFace().matches(""A|J|Q|K"") && !this.cards[i + 1].getFace().matches(""A|J|Q|K"")) {
                if (Integer.parseInt(this.cards[i].getFace()) + 1 == Integer.parseInt(this.cards[i + 1].getFace())) {
                    SameThisCard++;
                }
            }
            if (i==3 && that.cards[i].getFace().equals(""5"") && that.cards[i+1].getFace().equals(""A"")) {
                SameThatCard++;
               
            } else if (that.cards[i].getFace().equals(""10"") && that.cards[i + 1].getFace().equals(""J"")) {
                SameThatCard++;
              
   
            } else if (that.cards[i].getFace().equals(""J"") && that.cards[i + 1].getFace().equals(""Q"")) {
                SameThatCard++;
              
            } else if (that.cards[i].getFace().equals(""Q"") && that.cards[i + 1].getFace().equals(""K"")) {
                SameThatCard++;
                
            } else if (that.cards[i].getFace().equals(""K"") && that.cards[i + 1].getFace().equals(""A"")) {
                SameThatCard++;
                
            } else if (!that.cards[i].getFace().matches(""A|J|Q|K"") && !that.cards[i + 1].getFace().matches(""A|J|Q|K"")) {
                if (Integer.parseInt(that.cards[i].getFace()) + 1 == Integer.parseInt(that.cards[i + 1].getFace())) {
                    SameThatCard++;
                    
                }
            }
        }
        if (SameThisCard == 4 || SameThatCard ==4) {
            
            if (SameThisCard == 4 && SameThatCard == 4) {
                if (this.cards[4].getFace().equals(""A"") && that.cards[4].getFace().equals(""A"") && that.cards[0].getFace().equals(""2"") && this.cards[0].getFace().equals(""2"")) {

                    return this.cards[4].compareTo(that.cards[4]);
                } else if (this.cards[4].getFace().equals(""A"") && this.cards[0].getFace().equals(""2"")) {
                    return -1;
                } else if (that.cards[4].getFace().equals(""A"") && that.cards[0].getFace().equals(""2"")) {
                          
                    return 1;
                } else if (this.cards[4].getFace().equals(""A"") && that.cards[4].getFace().equals(""A"") && that.cards[0].getFace().equals(""10"") && this.cards[0].getFace().equals(""10"")){
                    return this.cards[4].compareTo(that.cards[4]);
                }else if (this.cards[4].getFace().equals(""A"") && this.cards[0].getFace().equals(""10"")){
                          
                    return 1;
                }else if (that.cards[4].getFace().equals(""A"") && that.cards[0].getFace().equals(""10"")) {
                    return -1;               
                }else {
                    return this.cards[4].compareTo(that.cards[4]);
                }
                }
                else if (SameThisCard==4){
                      
                 return 1;}
             else if(SameThatCard==4){
                
                 return -1;} 
                }
            
             
                      
            
        
        //System.out.println(SameThisCard);
.
        //2-pair
        SameThisCard = 0;
        SameThatCard = 0;
        SameThisID=-1;
        SameThatID=-1;
        int PairThis=0;
        int PairThat=0;
        for (int i = 0; i < 4; i++) {
                        SameThisCard = 0;
            SameThatCard = 0;
            for (int j = i + 1; j < 5; j++) {
                
                if (this.cards[i].getFace().equals(this.cards[j].getFace())) {
                    SameThisCard++;
                    //System.out.println(this.name);
                }
                if (that.cards[i].getFace().equals(that.cards[j].getFace())) {
                    SameThatCard++;
                }
            }
            if (SameThisCard == 1) {
                PairThis++;
                SameThisID = i;
            }
            if (SameThatCard == 1) {
                PairThat++;
                SameThatID = i;
            }

        }
        if (PairThis==2 && PairThat==2) {
                return this.cards[SameThisID].compareTo(that.cards[SameThatID]);
        }else if(PairThis==2){
            return 1;
        }
        else if(PairThat==2)
            return -1;
               
        //1-Pair
        SameThisCard = 0;
        SameThatCard = 0;
        SameThisID=-1;
        SameThatID=-1;
        for (int i = 0; i < 4; i++) {
                        SameThisCard = 0;
            SameThatCard = 0;
            for (int j = i + 1; j < 5; j++) {
                
                if (this.cards[i].getFace().equals(this.cards[j].getFace())) {
                    SameThisCard++;
                    //System.out.println(this.name);
                }
                if (that.cards[i].getFace().equals(that.cards[j].getFace())) {
                    SameThatCard++;
                }
            }
            if (SameThisCard == 1) {
                SameThisID = i;
            }
            if (SameThatCard == 1) {
                SameThatID = i;
            }
        }
      
        if (SameThisID != -1 && SameThatID != -1) {
            //System.out.println(""Y"");
            return this.cards[SameThisID].compareTo(that.cards[SameThatID]);
        } else if (SameThisID != -1) {
            return 1;
        } else if (SameThatID != -1) {
            return -1;
        }

        
        
        //High Card
        return this.cards[4].compareTo(that.cards[4]);
        
    }

}



"b02611023","0.102","109232","@1db5a0067db57876eec01951b7853dda@
import java.util.Comparator;

public class Player implements Comparable<Player> {

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
        int face1 = 0;
        int suit1 = 0;
        int face2 = 0;
        int suit2 = 0;
        int countsecmax1 = 0;
        int countsecmax2 = 0;
        int counts1 = 0;
        int counts2 = 0;
        Card max1 = this.cards[0];
        Card max2 = that.cards[0];
        Card min1 = this.cards[0];
        Card min2 = that.cards[0];
        Card secmax1 = this.cards[0];
        Card secmax2 = that.cards[0];
        Card pairmax1 = null;
        Card pairmax2 = null;
        Stack<Card> pair1 = new Stack<Card>();
        Stack<Card> pair2 = new Stack<Card>();
        Stack<Integer> this1 = new Stack<Integer>();
        Stack<Integer> that1 = new Stack<Integer>();
        QuickUnionUF uf1 = new QuickUnionUF(5);
        QuickUnionUF uf2 = new QuickUnionUF(5);

        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (this.cards[i].getFace().equals(this.cards[j].getFace())) {
                    uf1.union(j, i);
                }
                if (that.cards[i].getFace().equals(that.cards[j].getFace())) {
                    uf2.union(j, i);
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            suit1 = 0;
            suit2 = 0;
            for (int j = 0; j < 5; j++) {
                if (max1.compareTo(this.cards[j]) < 0) {
                    max1 = this.cards[j];
                }
                if (max2.compareTo(that.cards[j]) < 0) {
                    max2 = that.cards[j];
                }
                if (min1.compareTo(this.cards[j]) > 0) {
                    min1 = this.cards[j];
                }
                if (min2.compareTo(that.cards[j]) > 0) {
                    min2 = that.cards[j];
                }
                if (this.cards[i].compareTo(this.cards[j]) > 0) {
                    countsecmax1++;
                }
                if (that.cards[i].compareTo(that.cards[j]) > 0) {
                    countsecmax2++;
                }
                if (uf1.find(j) == i) {
                    face1++;
                    if (face1 >= 2) {
                        if (pairmax1 == null) {
                            pairmax1 = this.cards[i];
                        }
                        if (pairmax1.compareTo(this.cards[j]) < 0) {
                            pairmax1 = this.cards[j];
                        }
                    }
                }
                if (uf2.find(j) == i) {
                    face2++;
                    if (face2 >= 2) {
                        if (pairmax2 == null) {
                            pairmax2 = that.cards[i];
                        }
                        if (pairmax2.compareTo(that.cards[j]) < 0) {
                            pairmax2 = that.cards[j];
                        }
                    }
                }
                if (this.cards[i].getSuit().equals(this.cards[j].getSuit())) {
                    suit1++;
                }
                if (that.cards[i].getSuit().equals(that.cards[j].getSuit())) {
                    suit2++;
                }

            }
            if (face1 == 2) {
                if (!pair1.isEmpty()) {
                    if (counts1 < 1) {
                        Card temp = pair1.pop();
                        if (pairmax1.compareTo(temp) > 0) {
                            pair1.push(pairmax1);
                        } else {
                            pair1.push(temp);
                        }
                    }
                } else {
                    pair1.push(pairmax1);
                }
                pairmax1 = null;
            }
            if (face1 >= 3) {
                if (!pair1.isEmpty()) {
                    pair1.pop();
                    pair1.push(pairmax1);
                } else {
                    pair1.push(pairmax1);
                }
                pairmax1 = null;
                counts1++;
            }

            if (face2 == 2) {
                if (!pair2.isEmpty()) {
                    if (counts2 < 1) {
                        Card temp = pair2.pop();
                        if (pairmax2.compareTo(temp) > 0) {
                            pair2.push(pairmax2);
                        } else {
                            pair2.push(temp);
                        }
                    }
                } else {
                    pair2.push(pairmax2);
                }
                pairmax2 = null;
            }
            if (face2 >= 3) {
                if (!pair2.isEmpty()) {
                    pair2.pop();
                    pair2.push(pairmax2);
                } else {
                    pair2.push(pairmax2);
                }
                pairmax2 = null;
                counts2++;
            }

            if (countsecmax1 == 4) {
                secmax1 = this.cards[i];
            }
            if (countsecmax2 == 4) {
                secmax2 = that.cards[i];
            }
            this1.push(face1);
            that1.push(face2);
            face1 = 0;
            face2 = 0;
            countsecmax1 = 0;
            countsecmax2 = 0;
        }

        int count1 = 0;
        int count2 = 0;
        int thismax = 0;
        int thismin = 0;
        int thatmax = 0;
        int thatmin = 0;

        while (!this1.isEmpty()) {
            int a = this1.pop();
            if (a >= 2) {
                count1 = count1 + a;
            }
        }

        if (count1 == 5) {
            count1 = 5;
            max1 = pair1.pop();
        } else if (count1 == 4) {
            count1 = 2;
            max1 = pair1.pop();
        } else if (count1 == 3) {
            count1 = 1;
            max1 = pair1.pop();
        } else if (count1 == 2) {
            count1 = 1;
            max1 = pair1.pop();
        } else if (suit1 == 5) {
            count1 = 4;
        } else {
            if (max1.getFace().equals(""A"") && secmax1.getFace().equals(""5"")) {
                max1 = secmax1;
                thismax = 5;
                thismin = 1;
            } else if (max1.getFace().equals(""A"") && min1.getFace().equals(""10"")) {
                thismax = 14;
                thismin = 10;
            } else if (max1.getFace().equals(""J"")) {
                thismax = 11;
                thismin = Integer.parseInt(min1.getFace());
            } else if (max1.getFace().equals(""Q"")) {
                thismax = 12;
                thismin = Integer.parseInt(min1.getFace());
            } else if (max1.getFace().equals(""K"")) {
                thismax = 13;
                thismin = Integer.parseInt(min1.getFace());
            } else if (max1.getFace().equals(""A"")) {
                thismax = 0;
                thismin = 0;
            } else {
                thismax = Integer.parseInt(max1.getFace());
                thismin = Integer.parseInt(min1.getFace());
            }
            if ((thismax - thismin) == 4) {
                count1 = 3;
            } else {
                count1 = 0;
            }
        }

//        System.out.println(max1.getFace());
//        System.out.println(max1.getSuit());
//        System.out.println(min1.getFace());
//        System.out.println(min1.getSuit());
//        System.out.println(thismax);
//        System.out.println(thismin);
//        System.out.println(count1);
        while (!that1.isEmpty()) {
            int b = that1.pop();
            if (b >= 2) {
                count2 = count2 + b;
            }
        }

        if (count2 == 5) {
            count2 = 5;
            max2 = pair2.pop();
        } else if (count2 == 4) {
            count2 = 2;
            max2 = pair2.pop();
        } else if (count2 == 3) {
            count2 = 1;
            max2 = pair2.pop();
        } else if (count2 == 2) {
            count2 = 1;
            max2 = pair2.pop();
        } else if (suit2 == 5) {
            count2 = 4;
        } else {
            if (max2.getFace().equals(""A"") && secmax2.getFace().equals(""5"")) {
                max2 = secmax2;
                thatmax = 5;
                thatmin = 1;
            } else if (max2.getFace().equals(""A"") && min2.getFace().equals(""10"")) {
                thatmax = 14;
                thatmin = 10;
            } else if (max2.getFace().equals(""J"")) {
                thatmax = 11;
                thatmin = Integer.parseInt(min2.getFace());
            } else if (max2.getFace().equals(""Q"")) {
                thatmax = 12;
                thatmin = Integer.parseInt(min2.getFace());
            } else if (max2.getFace().equals(""K"")) {
                thatmax = 13;
                thatmin = Integer.parseInt(min2.getFace());
            } else if (max2.getFace().equals(""A"")) {
                thatmax = 0;
                thatmin = 0;
            } else {
                thatmax = Integer.parseInt(max2.getFace());
                thatmin = Integer.parseInt(min2.getFace());
            }
            if ((thatmax - thatmin) == 4) {
                count2 = 3;
            } else {
                count2 = 0;
            }
        }

//        System.out.println(max2.getFace());
//        System.out.println(max2.getSuit());
//        System.out.println(thatmax);
//        System.out.println(thatmin);
//        System.out.println(count2);
        if (count1 > count2) {
            return +1;
        } else if (count1 < count2) {
            return -1;
        } else {
            if (max1.compareTo(max2) > 0) {
                return +1;
            } else {
                return -1;
            }
        }
    }
}

"f02631008","0.092","108368","@c1d5fff343750eeb5d580ccacc477007@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    public Card[] HighCard = new Card[1] ;
    
    public void HighCard (Card cards) {
        this.HighCard[0] = cards;
    }
     
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
    public boolean isFullHouse(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards);
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace()))
                    this.HighCard(Cards[0]) ;
                return true ;
            }
            else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                    this.HighCard(Cards[4]) ;
                    return true ;
                }
            }
        }
        return false ;
    }
    public boolean isFlush(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getSuit().equalsIgnoreCase(Cards[1].getSuit())){
            if(Cards[1].getSuit().equalsIgnoreCase(Cards[2].getSuit())){
                if(Cards[2].getSuit().equalsIgnoreCase(Cards[3].getSuit())){
                 if(Cards[3].getSuit().equalsIgnoreCase(Cards[4].getSuit())){
                        this.HighCard(Cards[4]) ;
            return true ;
                    }
                }
            }
        }
            return false ;
        }
    public boolean isStraight(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
      
        MergeX.sort(Cards);
        Integer[] a1 = new Integer[Cards.length] ;
        for(int i = 0 ; i < 5 ; i++){
            if(Cards[i].getFace().equalsIgnoreCase(""J""))
                a1[i] = 11 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""Q""))
                a1[i] = 12 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""K""))
                a1[i] = 13 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""A""))
                a1[i] = 14 ;
            else
            a1[i] = Integer.parseInt(Cards[i].getFace());            
        }
        if(a1[1]==a1[0]+1){
            if(a1[2] == a1[1]+1){
                if(a1[3]== a1[2]+1){
                    if(a1[4] == a1[3]+1){
                        this.HighCard(Cards[4]) ;
                        return true ;
                    }
                }
            }
        }
        else if(a1[0]==2){
            if(a1[1]==3){
                if(a1[2]==4){
                    if(a1[3]==5){
                        if(a1[4]==14){
                            this.HighCard(Cards[3]) ;
                            return true ;
                        }
                    }
                }
            }
        }
        return false ;
    }
    public boolean isTwoPair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                this.HighCard(Cards[3]) ;
                return true ;
            }
            else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
       }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
        }
        return false;
    }
    public boolean isOnePair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            this.HighCard(Cards[1]) ;
            return true ;
        }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            this.HighCard(Cards[2]) ;
            return true ;
        }
        else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
            this.HighCard(Cards[3]) ;
            return true ;
        }
        else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
            this.HighCard(Cards[4]) ;
            return true ;
        }
        
        return false ;
    }
    // TODO 
    public int compareTo(Player that) {
        
.
        
            int[] Points = new int [2] ;
            if(this.isFullHouse())
                Points[0] = 60 ;
            
            else if(this.isFlush())
                Points[0] = 50 ;
            
            else if(this.isStraight())
                Points[0] = 40 ;
            
            else if(this.isTwoPair()){
                Points[0] = 30 ;
            }
            else if(this.isOnePair()){
                Points[0] = 20 ;
            
            }
            else
                Points[0] = 10 ;
            
            
            if(that.isFullHouse())
                Points[1] = 60 ;
            
            else if(that.isFlush())
                Points[1] = 50 ;
            
            else if(that.isStraight())
                Points[1] = 40 ;
            
            else if(that.isTwoPair()){
                Points[1] = 30 ;
            }
            else if(that.isOnePair()){
                Points[1] = 20 ;

    }
            else 
                Points[1] = 10 ;
            
//            System.out.println(Points[0]+"" ""+Points[1] ) ;
            
            if(Points[0]>Points[1])
                return 1 ;
            else if(Points[0]<Points[1])
                return -1 ;
            
            else if(Points[0]==Points[1])
            if(Points[0] == 10){
                MergeX.sort(this.cards);
                MergeX.sort(that.cards);
                this.HighCard(this.cards[4]) ;
                that.HighCard(that.cards[4]) ;
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
                }
            else 
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
            return 0 ;
    }
   
     /*public static void main(String[] args) {
     Player[] a = new Player[2] ;
     a[0] = new Player(""John Cena"") ;
     a[1] = new Player(""undertaker"") ;
     Card[] fuck = new Card[5];
     Card[] fuck1 = new Card[5] ;
     fuck[0] = new Card(""10"",""Spades"") ;
     fuck[1] = new Card(""A"",""Hearts"") ;
     fuck[2] = new Card(""9"",""Diamonds"") ;
     fuck[3] = new Card(""7"",""Spades"") ;
     fuck[4] = new Card(""5"",""Clubs"") ;
     a[0].setCards(fuck);
     fuck1[0] = new Card(""4"",""Spades"") ;
     fuck1[1] = new Card(""K"",""Hearts"") ;
     fuck1[2] = new Card(""J"",""Clubs"") ;
     fuck1[3] = new Card(""3"",""Spades"") ;
     fuck1[4] = new Card(""Q"",""Hearts"") ;
     a[1].setCards(fuck1);
//     for (int i = 0 ; i < 5 ; i++){
//     System.out.println(a[0].cards[i].getFace());
//     System.out.println(a[1].cards[i].getFace());
//     }
//     System.out.println(a[0].compareTo(a[1]));
//     System.out.println(a[1].HighCard[0].compareTo(a[0].HighCard[0]));
}*/
}


"b02611012","0.092","105296","@10467328dfb7532134748094ad28605c@
import java.util.Arrays;


public class Player implements Comparable<Player> {

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
    @SuppressWarnings(""empty-statement"")
    public int compareTo(Player that) {
.
        int[] thisf = new int[5];
        int[] thiss = new int[5];
        int[] thatf = new int[5];
        int[] thats = new int[5];
        int[] thishand = new int[3];
        int[] thathand = new int[3];
        for (int i = 0; i < 5; i++) {
            if (this.cards[i].getFace().equals(""A"")) {
                thisf[i] = 14;
            } else if (this.cards[i].getFace().equals(""J"")) {
                thisf[i] = 11;
            } else if (this.cards[i].getFace().equals(""Q"")) {
                thisf[i] = 12;
            } else if (this.cards[i].getFace().equals(""K"")) {
                thisf[i] = 13;
            } else {
                thisf[i] = Integer.parseInt(this.cards[i].getFace());
            }
            if (that.cards[i].getFace().equals(""A"")) {
                thatf[i] = 14;
            } else if (that.cards[i].getFace().equals(""J"")) {
                thatf[i] = 11;
            } else if (that.cards[i].getFace().equals(""Q"")) {
                thatf[i] = 12;
            } else if (that.cards[i].getFace().equals(""K"")) {
                thatf[i] = 13;
            } else {
                thatf[i] = Integer.parseInt(that.cards[i].getFace());
            }
            if (this.cards[i].getSuit().equals(""Spades"")) {
                thiss[i] = 4;
            } else if (this.cards[i].getSuit().equals(""Hearts"")) {
                thiss[i] = 3;
            } else if (this.cards[i].getSuit().equals(""Diamonds"")) {
                thiss[i] = 2;
            } else if (this.cards[i].getSuit().equals(""Clubs"")) {
                thiss[i] = 1;
            }
            if (that.cards[i].getSuit().equals(""Spades"")) {
                thats[i] = 4;
            } else if (that.cards[i].getSuit().equals(""Hearts"")) {
                thats[i] = 3;
            } else if (that.cards[i].getSuit().equals(""Diamonds"")) {
                thats[i] = 2;
            } else if (that.cards[i].getSuit().equals(""Clubs"")) {
                thats[i] = 1;
            }
        }
        //pair 2pair full house
        int paircounta = 0;
        int ahigh = 0;
        int paircountb = 0;
        int bhigh = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (thisf[i] == thisf[j]) {
                    paircounta++;
                    if (thisf[i] > ahigh) {
                        ahigh = thisf[i];
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (thatf[i] == thatf[j]) {
                    paircountb++;
                    if (thatf[i] > bhigh) {
                        bhigh = thatf[i];
                    }
                }
            }
        }
        if (paircounta > 0) {
            //1 pair
            if (paircounta == 1 || paircounta == 3) {
                thishand[0] = 1;
                thishand[1] = ahigh;
                int suithigh = 0;
                for (int i = 0; i < 5; i++) {
                    if (thisf[i] == ahigh) {
                        if (thiss[i] > suithigh) {
                            suithigh = thiss[i];
                        }
                    }
                }
                thishand[2] = suithigh;
            }
            //2 pairs
            if (paircounta == 2) {
                thishand[0] = 2;
                thishand[1] = ahigh;
                int suithigh = 0;
                for (int i = 0; i < 5; i++) {
                    if (thisf[i] == ahigh) {
                        if (thiss[i] > suithigh) {
                            suithigh = thiss[i];
                        }
                    }
                }
                thishand[2] = suithigh;
            }
            //fullhouse
            if (paircounta == 4) {
                thishand[0] = 5;
                for (int i = 0; i < 5; i++) {
                    int a = 0;
                    for (int j = i + 1; j < 5; j++) {
                        if (thisf[i] == thisf[j]) {
                            a++;
                            if (a == 2) {
                                thishand[1] = thisf[i];
                            }
                        }
                    }
                }
            }
        }else{
            Arrays.sort(thisf);
            //straight
            for (int i = 0; i < 4; i++) {
                if (i == 3 && thisf[4] == 14) {
                    thishand[0] = 3;
                    thishand[1] = thisf[3];
                    thishand[2] = thiss[3];
                }
                if (thisf[i] + 1 != thisf[i+1]) {
                    break;
                }
                if (i == 3) {
                    thishand[0] = 3;
                    thishand[1] = thisf[i+1];
                    thishand[2] = thiss[i+1];
                }
            }
            //flush
            for (int i = 0; i < 4; i++) {
                if (thiss[i] != thiss[i+1]) {
                    break;
                }
                if (i == 3) {
                    thishand[0] = 4;
                    thishand[1] = thisf[i+1];
                    thishand[2] = thiss[i+1];
                }
            }
            //highcard
            if (thishand[0] == 0) {
                thishand[1] = thisf[4];
                thishand[2] = thiss[4];
            }
        }
        if (paircountb > 0) {
            //1 pair
            if (paircountb == 1 || paircountb == 3) {
                thathand[0] = 1;
                thathand[1] = bhigh;
                int suithigh = 0;
                for (int i = 0; i < 5; i++) {
                    if (thatf[i] == bhigh) {
                        if (thats[i] > suithigh) {
                            suithigh = thats[i];
                        }
                    }
                }
                thathand[2] = suithigh;
            }
            //2 pairs
            if (paircountb == 2) {
                thathand[0] = 2;
                thathand[1] = bhigh;
                int suithigh = 0;
                for (int i = 0; i < 5; i++) {
                    if (thatf[i] == bhigh) {
                        if (thats[i] > suithigh) {
                            suithigh = thats[i];
                        }
                    }
                }
                thathand[2] = suithigh;
            }
            //fullhouse
            if (paircountb == 4) {
                thathand[0] = 5;
                for (int i = 0; i < 5; i++) {
                    int a = 0;
                    for (int j = i + 1; j < 5; j++) {
                        if (thatf[i] == thatf[j]) {
                            a++;
                            if (a == 2) {
                                thathand[1] = thatf[i];
                            }
                        }
                    }
                }
            }
        }else{
            Arrays.sort(thatf);
            //straight
            for (int i = 0; i < 4; i++) {
                if (i == 3 && thatf[4] == 14) {
                    thathand[0] = 3;
                    thathand[1] = thatf[3];
                    thathand[2] = thats[3];
                }
                if (thatf[i] + 1 != thatf[i+1]) {
                    break;
                }
                if (i == 3) {
                    thathand[0] = 3;
                    thathand[1] = thatf[i+1];
                    thathand[2] = thats[i+1];
                }
            }
            //flush
            for (int i = 0; i < 4; i++) {
                if (thats[i] != thats[i+1]) {
                    break;
                }
                if (i == 3) {
                    thathand[0] = 4;
                    thathand[1] = thatf[i+1];
                    thathand[2] = thats[i+1];
                }
            }
            //highcard
            if (thathand[0] == 0) {
                thathand[1] = thatf[4];
                thathand[2] = thats[4];
            }
        }
        //compare
        for (int i = 0; i < 3; i++) {
            if (thishand[i] > thathand[i]) {
                return +1;
            }
            if (thishand[i] < thathand[i]) {
                return -1;
            }
        }
        
        return 0;
    }

}

"r04631036","0.096","107312","@7905d0f75820c3bf3b84c3739be3ffd2@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    public Card[] HighCard = new Card[1] ;
    
    public void HighCard (Card cards) {
        this.HighCard[0] = cards;
    }
     
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
    public boolean isFullHouse(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards);
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace()))
                    this.HighCard(Cards[0]) ;
                return true ;
            }
            else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                    this.HighCard(Cards[4]) ;
                    return true ;
                }
            }
        }
        return false ;
    }
    public boolean isFlush(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getSuit().equalsIgnoreCase(Cards[1].getSuit())){
            if(Cards[1].getSuit().equalsIgnoreCase(Cards[2].getSuit())){
                if(Cards[2].getSuit().equalsIgnoreCase(Cards[3].getSuit())){
                 if(Cards[3].getSuit().equalsIgnoreCase(Cards[4].getSuit())){
                        this.HighCard(Cards[4]) ;
            return true ;
                    }
                }
            }
        }
            return false ;
        }
    public boolean isStraight(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
      
        MergeX.sort(Cards);
        Integer[] a1 = new Integer[Cards.length] ;
        for(int i = 0 ; i < 5 ; i++){
            if(Cards[i].getFace().equalsIgnoreCase(""J""))
                a1[i] = 11 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""Q""))
                a1[i] = 12 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""K""))
                a1[i] = 13 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""A""))
                a1[i] = 14 ;
            else
            a1[i] = Integer.parseInt(Cards[i].getFace());            
        }
        if(a1[1]==a1[0]+1){
            if(a1[2] == a1[1]+1){
                if(a1[3]== a1[2]+1){
                    if(a1[4] == a1[3]+1){
                        this.HighCard(Cards[4]) ;
                        return true ;
                    }
                }
            }
        }
        else if(a1[0]==2){
            if(a1[1]==3){
                if(a1[2]==4){
                    if(a1[3]==5){
                        if(a1[4]==14){
                            this.HighCard(Cards[3]) ;
                            return true ;
                        }
                    }
                }
            }
        }
        return false ;
    }
    public boolean isTwoPair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                this.HighCard(Cards[3]) ;
                return true ;
            }
            else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
       }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
        }
        return false;
    }
    public boolean isOnePair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            this.HighCard(Cards[1]) ;
            return true ;
        }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            this.HighCard(Cards[2]) ;
            return true ;
        }
        else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
            this.HighCard(Cards[3]) ;
            return true ;
        }
        else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
            this.HighCard(Cards[4]) ;
            return true ;
        }
        
        return false ;
    }
    // TODO 
    public int compareTo(Player that) {
        
.
        
            int[] Points = new int [2] ;
            if(this.isFullHouse())
                Points[0] = 60 ;
            
            else if(this.isFlush())
                Points[0] = 50 ;
            
            else if(this.isStraight())
                Points[0] = 40 ;
            
            else if(this.isTwoPair()){
                Points[0] = 30 ;
            }
            else if(this.isOnePair()){
                Points[0] = 20 ;
            
            }
            else
                Points[0] = 10 ;
            
            
            if(that.isFullHouse())
                Points[1] = 60 ;
            
            else if(that.isFlush())
                Points[1] = 50 ;
            
            else if(that.isStraight())
                Points[1] = 40 ;
            
            else if(that.isTwoPair()){
                Points[1] = 30 ;
            }
            else if(that.isOnePair()){
                Points[1] = 20 ;

    }
            else 
                Points[1] = 10 ;
            
//            System.out.println(Points[0]+"" ""+Points[1] ) ;
            
            if(Points[0]>Points[1])
                return 1 ;
            else if(Points[0]<Points[1])
                return -1 ;
            
            else if(Points[0]==Points[1])
            if(Points[0] == 10){
                MergeX.sort(this.cards);
                MergeX.sort(that.cards);
                this.HighCard(this.cards[4]) ;
                that.HighCard(that.cards[4]) ;
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
                }
            else 
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
            return 0 ;
    }
   
     public static void main(String[] args) {
     Player[] a = new Player[2] ;
     a[0] = new Player(""John Cena"") ;
     a[1] = new Player(""undertaker"") ;
     Card[] fuck = new Card[5];
     Card[] fuck1 = new Card[5] ;
     fuck[0] = new Card(""10"",""Spades"") ;
     fuck[1] = new Card(""A"",""Hearts"") ;
     fuck[2] = new Card(""9"",""Diamonds"") ;
     fuck[3] = new Card(""7"",""Spades"") ;
     fuck[4] = new Card(""5"",""Clubs"") ;
     a[0].setCards(fuck);
     fuck1[0] = new Card(""4"",""Spades"") ;
     fuck1[1] = new Card(""K"",""Hearts"") ;
     fuck1[2] = new Card(""J"",""Clubs"") ;
     fuck1[3] = new Card(""3"",""Spades"") ;
     fuck1[4] = new Card(""Q"",""Hearts"") ;
     a[1].setCards(fuck1);
//     for (int i = 0 ; i < 5 ; i++){
//     System.out.println(a[0].cards[i].getFace());
//     System.out.println(a[1].cards[i].getFace());
//     }
//     System.out.println(a[0].compareTo(a[1]));
//     System.out.println(a[1].HighCard[0].compareTo(a[0].HighCard[0]));
}
}


"r03945012","0.094","105248","@98221beacdb5f76fe512d3935c071864@
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
//        StdOut.println(this.name +""  ""+ Cardone[0]);
        
        Card[] PlayerTwoCards = that.cards;
        Arrays.sort(PlayerTwoCards);
        String[] Cardtwo = FiveCard(PlayerTwoCards);
//        StdOut.println(that.name +""  ""+ Cardtwo[0]);
        
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
    // one pair: 11234 , (11134)
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
                    result[1] = fivecards[4].getFace();
                    result[2] = fivecards[4].getSuit();
                    return result;
                }
                // straight or not
                if (""2"".equals(cards_number[0]) && ""3"".equals(cards_number[1]) &&
                    ""4"".equals(cards_number[2]) && ""5"".equals(cards_number[3]) &&
                    ""A"".equals(cards_number[4])){
                    result[0] = ""straight"";
                    result[1] = fivecards[3].getFace();
                    result[2] = fivecards[3].getSuit();
                    return result;
                }
                String Number = ""2,3,4,5,6,7,8,9,10,J,Q,K,A"";
                int face_compare;
                for (int i = 0; i<4; i++){
                    face_compare = Number.indexOf(cards_number[i+1])-Number.indexOf(cards_number[i]);
                    if (face_compare > 4 || face_compare < 2){
                        // highcard here   
                        result[0] = ""highcard"";
                        result[1] = fivecards[4].getFace();
                        result[2] = fivecards[4].getSuit();
                        return result;
                    }
                    else if (i == 3){
                        result[0] = ""straight"";
                        result[1] = fivecards[4].getFace();
                        result[2] = fivecards[4].getSuit();
                        return result;
                    }
                }
            case 4: // one pair here
                temp.clear();
                int index_1 = 0;
                for (int i=0; i<5; i++){
                    if (temp.add(cards_number[i]) == false)
                        index_1 = i;
                    temp.add(cards_number[i]);
                }
                result[0] = ""one_pair"";
                result[1] = fivecards[index_1].getFace();
                result[2] = fivecards[index_1].getSuit();
                break;
            case 3: // two pair(11223) here and one pair(11134)
                temp.clear();
                int index_2 = 0;
                int k = 0;
                String[] temp_dup = new String[2];
                for (int i=0; i<5; i++){
                    if (temp.add(cards_number[i]) == false){
                        index_2 = i;
                        // Triplets
                        temp_dup[k++] = cards_number[i];
                    }
                    temp.add(cards_number[i]);
                }
                if (temp_dup[0].equals(temp_dup[1])){
                    result[0] = ""one_pair"";
                    result[1] = fivecards[index_2].getFace();
                    result[2] = fivecards[index_2].getSuit();
                    return result;
                }
                else{
                    result[0] = ""two_pair"";
                    result[1] = fivecards[index_2].getFace();
                    result[2] = fivecards[index_2].getSuit();
                    return result;
                }
            case 2: // full_house here and (11112)
                if (cards_number[1].equals(cards_number[2]) && 
                    cards_number[2].equals(cards_number[3])){
                    result[0] = ""one_pair"";
                    result[1] = fivecards[3].getFace();
                    result[2] = fivecards[3].getSuit();
                    return result;
                }
                temp.clear();
                int m = 0;
                String[] dup_two = new String[3];
                for (int i=0; i<5; i++){
                    if (temp.add(cards_number[i]) == false){
                        dup_two[m++] = cards_number[i];
                    }
                    temp.add(cards_number[i]);
                }
                if (dup_two[1].equals(dup_two[2])){
                    result[0] = ""full_house"";
                    result[1] = fivecards[4].getFace();
                    result[2] = fivecards[4].getSuit();
                    break;
                }
                else{
                    result[0] = ""full_house"";
                    result[1] = fivecards[2].getFace();
                    result[2] = fivecards[2].getSuit();
                    break;
                }
            case 1:
                result[0] = ""one_pair"";
                result[1] = fivecards[4].getFace();
                result[2] = fivecards[4].getSuit();
                break;
        }
        return result;
    }
}


"b03704074","0.092","105296","@e43599c298967ec91b94f27223146ba6@
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
     
    // TODO 
    public int compareTo(Player that) {
.
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        int c1size = -1;
        int c2size = -1;
        int flag1 = -1;
        int flag2 = -1;
        //葫蘆
        if(this.cards[0].getFace().equals(this.cards[1].getFace())  &&  this.cards[1].getFace().equals(this.cards[2].getFace())  
            &&  this.cards[3].getFace().equals(this.cards[4].getFace()))
            c1size = 14;
        else if(this.cards[0].getFace().equals(this.cards[1].getFace())  &&  this.cards[2].getFace().equals(this.cards[3].getFace())  
            &&  this.cards[3].getFace().equals(this.cards[4].getFace()))
            c1size = 14;
        //同花
        else if(this.cards[0].getSuit().equals(this.cards[1].getSuit())  &&  this.cards[1].getSuit().equals(this.cards[2].getSuit())  
            &&  this.cards[2].getSuit().equals(this.cards[3].getSuit())  && this.cards[3].getSuit().equals(this.cards[4].getSuit()))
            c1size = 13;
        //順子
        else if(this.cards[0].getFace().equals(""10"") && this.cards[1].getFace().equals(""J"")  && this.cards[2].getFace().equals(""Q"")
            && this.cards[3].getFace().equals(""K"") && this.cards[4].getFace().equals(""A""))
            c1size = 12;
        else if(this.cards[0].getFace().equals(""9"") && this.cards[1].getFace().equals(""10"")  && this.cards[2].getFace().equals(""J"")
            && this.cards[3].getFace().equals(""Q"") && this.cards[4].getFace().equals(""K""))
            c1size = 11;
        else if(this.cards[0].getFace().equals(""8"") && this.cards[1].getFace().equals(""9"")  && this.cards[2].getFace().equals(""10"")
            && this.cards[3].getFace().equals(""J"") && this.cards[4].getFace().equals(""Q""))
            c1size = 10;
        else if(this.cards[0].getFace().equals(""7"") && this.cards[1].getFace().equals(""8"")  && this.cards[2].getFace().equals(""9"")
            && this.cards[3].getFace().equals(""10"") && this.cards[4].getFace().equals(""J""))
            c1size = 9;
        else if(this.cards[0].getFace().equals(""6"") && this.cards[1].getFace().equals(""7"")  && this.cards[2].getFace().equals(""8"")
            && this.cards[3].getFace().equals(""9"") && this.cards[4].getFace().equals(""10""))
            c1size = 8;
        else if(this.cards[0].getFace().equals(""7"") && this.cards[1].getFace().equals(""8"")  && this.cards[2].getFace().equals(""9"")
            && this.cards[3].getFace().equals(""10"") && this.cards[4].getFace().equals(""J""))
            c1size = 7;
        else if(this.cards[0].getFace().equals(""4"") && this.cards[1].getFace().equals(""5"")  && this.cards[2].getFace().equals(""6"")
            && this.cards[3].getFace().equals(""7"") && this.cards[4].getFace().equals(""8""))
            c1size = 6;
        else if(this.cards[0].getFace().equals(""3"") && this.cards[1].getFace().equals(""4"")  && this.cards[2].getFace().equals(""5"")
            && this.cards[3].getFace().equals(""6"") && this.cards[4].getFace().equals(""7""))
            c1size = 5;
        else if(this.cards[0].getFace().equals(""2"") && this.cards[1].getFace().equals(""3"")  && this.cards[2].getFace().equals(""4"")
            && this.cards[3].getFace().equals(""5"") && this.cards[4].getFace().equals(""6""))
            c1size = 4;
        else if(this.cards[0].getFace().equals(""2"") && this.cards[1].getFace().equals(""3"")  && this.cards[2].getFace().equals(""4"")
            && this.cards[3].getFace().equals(""5"") && this.cards[4].getFace().equals(""A""))
            c1size = 3;
        //兩ㄆㄟ
        else if(this.cards[0].getFace().equals(this.cards[1].getFace())  &&  this.cards[2].getFace().equals(this.cards[3].getFace()))
            c1size = 2;
        else if(this.cards[0].getFace().equals(this.cards[1].getFace())  &&  this.cards[3].getFace().equals(this.cards[4].getFace()))
            c1size = 2;
        else if(this.cards[1].getFace().equals(this.cards[2].getFace())  &&  this.cards[3].getFace().equals(this.cards[4].getFace()))
            c1size = 2;
        //一ㄆㄟ
        else if(this.cards[0].getFace().equals(this.cards[1].getFace())){
            c1size = 1;
            flag1 = 0;}
        else if(this.cards[1].getFace().equals(this.cards[2].getFace())){
            c1size = 1;
            flag1 = 1;}
        else if(this.cards[2].getFace().equals(this.cards[3].getFace())){
            c1size = 1;
            flag1 = 2;}
        else if(this.cards[3].getFace().equals(this.cards[4].getFace())){
            c1size = 1;
            flag1 = 3;}
        //散牌
        else c1size = 0;
        
        //葫蘆
        if(that.cards[0].getFace().equals(that.cards[1].getFace())  &&  that.cards[1].getFace().equals(that.cards[2].getFace())  
            &&  that.cards[3].getFace().equals(that.cards[4].getFace()))
            c2size = 14;
        else if(that.cards[0].getFace().equals(that.cards[1].getFace())  &&  that.cards[2].getFace().equals(that.cards[3].getFace())  
            &&  that.cards[3].getFace().equals(that.cards[4].getFace()))
            c2size = 14;
        //同花
        else if(that.cards[0].getSuit().equals(that.cards[1].getSuit())  &&  that.cards[1].getSuit().equals(that.cards[2].getSuit())  
            &&  that.cards[2].getSuit().equals(that.cards[3].getSuit())  && that.cards[3].getSuit().equals(that.cards[4].getSuit()))
            c2size = 13;
        //順子
        else if(that.cards[0].getFace().equals(""10"") && that.cards[1].getFace().equals(""J"")  && that.cards[2].getFace().equals(""Q"")
            && that.cards[3].getFace().equals(""K"") && that.cards[4].getFace().equals(""A""))
            c2size = 12;
        else if(that.cards[0].getFace().equals(""9"") && that.cards[1].getFace().equals(""10"")  && that.cards[2].getFace().equals(""J"")
            && that.cards[3].getFace().equals(""Q"") && that.cards[4].getFace().equals(""K""))
            c2size = 11;
        else if(that.cards[0].getFace().equals(""8"") && that.cards[1].getFace().equals(""9"")  && that.cards[2].getFace().equals(""10"")
            && that.cards[3].getFace().equals(""J"") && that.cards[4].getFace().equals(""Q""))
            c2size = 10;
        else if(that.cards[0].getFace().equals(""7"") && that.cards[1].getFace().equals(""8"")  && that.cards[2].getFace().equals(""9"")
            && that.cards[3].getFace().equals(""10"") && that.cards[4].getFace().equals(""J""))
            c2size = 9;
        else if(that.cards[0].getFace().equals(""6"") && that.cards[1].getFace().equals(""7"")  && that.cards[2].getFace().equals(""8"")
            && that.cards[3].getFace().equals(""9"") && that.cards[4].getFace().equals(""10""))
            c2size = 8;
        else if(that.cards[0].getFace().equals(""5"") && that.cards[1].getFace().equals(""6"")  && that.cards[2].getFace().equals(""7"")
            && that.cards[3].getFace().equals(""8"") && that.cards[4].getFace().equals(""9""))
            c2size = 7;
        else if(that.cards[0].getFace().equals(""4"") && that.cards[1].getFace().equals(""5"")  && that.cards[2].getFace().equals(""6"")
            && that.cards[3].getFace().equals(""7"") && that.cards[4].getFace().equals(""8""))
            c2size = 6;
        else if(that.cards[0].getFace().equals(""3"") && that.cards[1].getFace().equals(""4"")  && that.cards[2].getFace().equals(""5"")
            && that.cards[3].getFace().equals(""6"") && that.cards[4].getFace().equals(""7""))
            c2size = 5;
        else if(that.cards[0].getFace().equals(""2"") && that.cards[1].getFace().equals(""3"")  && that.cards[2].getFace().equals(""4"")
            && that.cards[3].getFace().equals(""5"") && that.cards[4].getFace().equals(""6""))
            c2size = 4;
        else if(that.cards[0].getFace().equals(""2"") && that.cards[1].getFace().equals(""3"")  && that.cards[2].getFace().equals(""4"")
            && that.cards[3].getFace().equals(""5"") && that.cards[4].getFace().equals(""A""))
            c2size = 3;
        //兩ㄆㄟ
        else if(that.cards[0].getFace().equals(that.cards[1].getFace())  &&  that.cards[2].getFace().equals(that.cards[3].getFace()))
            c2size = 2;
        else if(that.cards[0].getFace().equals(that.cards[1].getFace())  && that.cards[3].getFace().equals(that.cards[4].getFace()))
            c2size = 2;
        else if(that.cards[1].getFace().equals(that.cards[2].getFace())  &&  that.cards[3].getFace().equals(that.cards[4].getFace()))
            c2size = 2;
        //一ㄆㄟ
        else if(that.cards[0].getFace().equals(that.cards[1].getFace())){
            c2size = 1;
            flag2 = 0;}
        else if(that.cards[1].getFace().equals(that.cards[2].getFace())){
            c2size = 1;
            flag2 = 1;}
        else if(that.cards[2].getFace().equals(that.cards[3].getFace())){
            c2size = 1;
            flag2 = 2;}
        else if(that.cards[3].getFace().equals(that.cards[4].getFace())){
            c2size = 1;
            flag2 = 3;}
        //散牌
        else c2size = 0;
        
        if(c1size>c2size) return +1;
        if(c1size<c2size) return -1;
        //c1size == c2size
        
        //都是葫蘆，直接比中間的大小
        if(c1size == 14) return this.cards[2].compareTo(that.cards[2]);
        //都是同花
        else if(c1size == 13) return this.cards[4].compareTo(that.cards[4]);
        //都是2ㄆㄟ
        else if(c1size == 2) return this.cards[3].compareTo(that.cards[3]);
        //都是1ㄆㄟ
        else if (c1size == 1) return this.cards[flag1].compareTo(that.cards[flag2]);
        //散牌
        else  return this.cards[4].compareTo(that.cards[4]);
        
       
        
            
    }
}

"r02b48003","0.09","105344","@b7e674802e1093f44dbafb212a0d2e63@
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
            if      (thisHand[i] < thatHand[i]) {return -1;}
            else if (thisHand[i] > thatHand[i]) {return  1;}
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
            face2Num[i] = mapFace(cards[i], isAce);
        } // end for loop
        
        return face2Num;
    } // end mapCards
    
    public int[] mapSuits() {
        // 
        int[] suit2Num = new int[cards.length];
        
        // map each cards to a 
        for (int i = 0; i < suit2Num.length; i++) {
            suit2Num[i] = mapSuit(cards[i]);
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
                mapFace(cards[idx], true),
                mapSuit(cards[idx])}; 
            
        } else if (sum(handFace) == 4) { 
            int idx = search(handFace, 2);
            return new int[] {
                mapHand(""fullhouse""),
                mapFace(cards[idx], true),
                mapSuit(cards[idx])}; 
            
        } else if (sum(handFace) == 2) { 
            int idx = search(handFace, 1);
            return new int[] {
                mapHand(""TwoPairs""),
                mapFace(cards[idx], true),
                mapSuit(cards[idx])}; 
          
        } else if (sum(handFace) == 1) { 
            int idx = search(handFace, 1);
            return new int[] {
                mapHand(""OnePairs""),
                mapFace(cards[idx], true),
                mapSuit(cards[idx])}; 
        
        } else if (sum(handFace) == 0)    { 
            if (handSuit[4] == 4)         { 
                return new int[] {
                    mapHand(""Flash""),
                    mapFace(cards[4],true),
                    mapSuit(cards[4])};
                
            } else if (isStraight(true)) { 
                return new int[] {
                    mapHand(""Straight""),
                    mapFace(cards[4], true),
                    mapSuit(cards[4])}; 
                
            } else if (isStraight(false)) { 
                return new int[] {
                    mapHand(""Straight""),
                    5,
                    mapSuit(cards[0])}; 
                
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
    } // end func mapHand
    
    /**
     * method: mapFace
     * convert the face into integer and return
     * note that A can be 1 or 14
     * @param isAce
     * @return integer
     */
    private Integer mapFace(Card card, boolean isAce) {
        // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
        String cardFace = card.getFace();
            switch (cardFace) {
                case ""A"": if (isAce) return 14;
                          else       return 1;
                case ""K"": return 13;
                case ""Q"": return 12;
                case ""J"": return 11;
                default:
                    return Integer.valueOf(cardFace); // end if-else
            }
    } // end func mapFace
    
    /**
     * method: mapSuit
     * return integer that corresponds to the suit
     * rules Spades > Hearts > Diamonds > Clubs
     * @return 
     */
    private Integer mapSuit(Card card) {
        String cardSuit;
        cardSuit = card.getSuit();
            switch (cardSuit) {
                case ""Spades"":   return 4; // Spades
                case ""Hearts"":   return 3; // Hearts
                case ""Diamonds"": return 2; // Diamonds
                default:         return 1; // Clubs
            }
    } // end func mapSuit
    
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
        
        System.out.println(""Player01 vs Player02"");
        if (players[0].compareTo(players[1]) < 0) {
            System.out.println(""Player02 wins"");
        } else if (players[0].compareTo(players[1]) > 0) {
            System.out.println(""Player01 wins"");
        } else {
            System.out.println(""Player01 Player02 even"");
        } 
        
        System.out.println(""Player02 vs Player03"");
        if (players[1].compareTo(players[2]) < 0) {
            System.out.println(""Player03 wins"");
        } else if (players[1].compareTo(players[2]) > 0) {
            System.out.println(""Player02 wins"");
        } else {
            System.out.println(""Player02 Player03 even"");
        } 
        
    } // end main
}

"b02611016","0.092","101696","@e8e7a745a66f197d74db0c1a1112e069@/*
.
 * To change this template file, choose Tools | Templates
.
 */

import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Arrays;
/**
 *
 * @author Lab304
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
        int [] this_face=new int[15];
        int [] that_face=new int[15];
        int this_round1=1;
        int that_round1=1;
        int this_round2=0;
        int that_round2=0;
        int this_round3=0;
        int that_round3=0;
        for(int i=0;i<5;i++)
        {
            if(this.cards[i].getFace().equals(""A"")) { this_face[14]=this_face[14]+1;} 
            else if(this.cards[i].getFace().equals(""K"")){this_face[13]=this_face[13]+1;}
            else if(this.cards[i].getFace().equals(""Q"")){this_face[12]=this_face[12]+1;}
            else if(this.cards[i].getFace().equals(""J"")){this_face[11]=this_face[11]+1;}
            else{this_face[Integer.parseInt(this.cards[i].getFace())]=this_face[Integer.parseInt(this.cards[i].getFace())]+1;}
            
        }
     //   for(int i=2;i<15;i++)
      //  {System.out.println(this_face[i]);}
        for(int i=0;i<5;i++)
        {
            if(that.cards[i].getFace().equals(""A"")) { that_face[14]=that_face[14]+1;} 
            else if(that.cards[i].getFace().equals(""K"")){that_face[13]=that_face[13]+1;}
            else if(that.cards[i].getFace().equals(""Q"")){that_face[12]=that_face[12]+1;}
            else if(that.cards[i].getFace().equals(""J"")){that_face[11]=that_face[11]+1;}
            else{that_face[Integer.parseInt(that.cards[i].getFace())]=that_face[Integer.parseInt(that.cards[i].getFace())]+1;}
        }
   //      for(int i=2;i<15;i++)
    //    {System.out.println(that_face[i]);}
  search: for(int j=2;j<15;j++)
        { if(this_face[j]>=2){
            if(this_face[j]==3){for(int k=j+1;k<15;k++)
              {if(this_face[k]==2){this_round1=6;this_round2=j; break search;}
            
              }
             this_round1=2;this_round2=j;
            }
            else{for(int k=j+1;k<15;k++)
              {if(this_face[k]==3){this_round1=6;this_round2=k; break search;}
               else if(this_face[k]==2){this_round1=3;this_round2=k; break search;}
               
              }
             this_round1=2;this_round2=j;
            }
          }
        }
  
 search1: for(int j=2;j<15;j++)
        { if(that_face[j]>=2){
            if(that_face[j]==3){for(int k=j+1;k<15;k++)
              {if(that_face[k]==2){that_round1=6;that_round2=j; break search1;}
              
              }
            that_round1=2;that_round2=j;
            }
            else{for(int k=j+1;k<15;k++)
              {if(that_face[k]==3){that_round1=6;that_round2=k; break search1;}
               else if(that_face[k]==2){that_round1=3;that_round2=k; break search1;}
               
              }
            that_round1=2;that_round2=j;
            }
          }
        }
 search2:for(int j=2;j<11;j++)
        { if(j==2&&this_face[j]==1)
          {if(this_face[14]==1){if(this_face[3]==1&&this_face[4]==1&&this_face[5]==1){this_round1=4;this_round2=5;break search2;}}
           if(this_face[3]==1&&this_face[4]==1&&this_face[5]==1&&this_face[6]==1){this_round1=4;this_round2=6;break search2;}
          }
         else if(j!=2&&this_face[j]==1){if(this_face[j+1]==1&&this_face[j+2]==1&&this_face[j+3]==1&&this_face[j+4]==1){this_round1=4;this_round2=j+4;break search2;}}
        }
 search3:for(int j=2;j<11;j++)
        { if(j==2&&that_face[j]==1)
          {if(that_face[14]==1){if(that_face[3]==1&&that_face[4]==1&&that_face[5]==1){that_round1=4;that_round2=5;break search3;}}
           if(that_face[3]==1&&that_face[4]==1&&that_face[5]==1&&that_face[6]==1){that_round1=4;this_round2=6;break search3;}
          }
         else if(j!=2&&that_face[j]==1){if(that_face[j+1]==1&&that_face[j+2]==1&&that_face[j+3]==1&&that_face[j+4]==1){that_round1=4;that_round2=j+4;break search3;}}
        }
 search4:
         if(this.cards[0].getSuit().equals(this.cards[1].getSuit())&&this.cards[0].getSuit().equals(this.cards[2].getSuit())&&this.cards[0].getSuit().equals(this.cards[3].getSuit())&&this.cards[0].getSuit().equals(this.cards[4].getSuit()))
         {this_round1=5;}
         if(that.cards[0].getSuit().equals(that.cards[1].getSuit())&&that.cards[0].getSuit().equals(that.cards[2].getSuit())&&that.cards[0].getSuit().equals(that.cards[3].getSuit())&&that.cards[0].getSuit().equals(that.cards[4].getSuit()))
         {that_round1=5;}
.
     //if(this.cards[1].getFace().equals(""9"")) {return 1;} 
     //    System.out.println(this_round1);
    //     System.out.println(that_round1);
         
        if(this_round1>that_round1){return 1;}
        if(this_round1<that_round1){return -1;}
        if(this_round1==that_round1&&this_round1==6)
          {if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
          }
        if(this_round1==that_round1&&this_round1==4)
          {if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
           if(this_round2==that_round2)
             {if(this_round2==14)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""A""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""A""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else if(this_round2==13)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""K""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""K""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else if(this_round2==12)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""Q""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""Q""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else if(this_round2==11)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""J""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""J""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else
                {for(int i=0;i<5;i++)
                    {if(Integer.parseInt(this.cards[i].getFace())==this_round2)
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(Integer.parseInt(that.cards[i].getFace())==this_round2)
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
             if(this_round3>that_round3){return 1;}
             if(this_round3<that_round3){return -1;}
             }
          } 
        if(this_round1==that_round1&&this_round1==5)
          {if(this.cards[0].getFace().equals(""Spades"")) { this_round2=4;} 
           else if(this.cards[0].getSuit().equals(""Hearts"")){this_round2=3;}
           else if(this.cards[0].getSuit().equals(""Diamonds"")){this_round2=2;}
           else if(this.cards[0].getSuit().equals(""Clubs"")){this_round2=1;}
           if(that.cards[0].getFace().equals(""Spades"")) { that_round2=4;} 
           if(that.cards[0].getSuit().equals(""Hearts"")){that_round2=3;}
           if(that.cards[0].getSuit().equals(""Diamonds"")){that_round2=2;}
           if(that.cards[0].getSuit().equals(""Clubs"")){that_round2=1;}
           if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
           if(this_round2==that_round2)
             {for(int k=14;k>1;k--)
                 {if(this_face[k]==1){this_round3=k;break;}
                 }
              for(int k=14;k>1;k--)
                 {if(that_face[k]==1){that_round3=k;break;}
                 }
              if(this_round3>that_round3){return 1;}
              if(this_round3<that_round3){return -1;}
             } 
          }
        if(this_round1==that_round1&&this_round1==3)
          {if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
           if(this_round2==that_round2)
             {if(this_round2==14)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""A""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""A""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
            else if(this_round2==13)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""K""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""K""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else if(this_round2==12)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""Q""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""Q""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else if(this_round2==11)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""J""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""J""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else
                {for(int i=0;i<5;i++)
                    {if(Integer.parseInt(this.cards[i].getFace())==this_round2)
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(Integer.parseInt(that.cards[i].getFace())==this_round2)
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
             if(this_round3>that_round3){return 1;}
             if(this_round3<that_round3){return -1;}
             }
          }
        if(this_round1==that_round1&&this_round1==2)    
          {if(this_round2>that_round2){return 1;}
           if(this_round2<that_round2){return -1;}
           if(this_round2==that_round2)
             {if(this_round2==14)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""A""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""A""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
            else if(this_round2==13)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""K""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""K""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else if(this_round2==12)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""Q""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""Q""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else if(this_round2==11)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""J""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""J""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
              else
                {for(int i=0;i<5;i++)
                    {if(Integer.parseInt(this.cards[i].getFace())==this_round2)
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){if(this_round3<3)this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){if(this_round3<2)this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){if(this_round3<1)this_round3=1;}
                       }
                     if(Integer.parseInt(that.cards[i].getFace())==this_round2)
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){if(that_round3<3)that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){if(that_round3<2)that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){if(that_round3<1)that_round3=1;}
                       }
                    }
                }
             if(this_round3>that_round3){return 1;}
             if(this_round3<that_round3){return -1;}
             }
          }
        if(this_round1==that_round1&&this_round1==1)    
          {for(int k=14;k>1;k--)
                 {if(this_face[k]==1){this_round2=k;break;}
                 }
              for(int k=14;k>1;k--)
                 {if(that_face[k]==1){that_round2=k;break;}
                 }
               if(this_round2>that_round2){return 1;}
               if(this_round2<that_round2){return -1;}
               if(this_round2==that_round2)
                 {if(this_round2==14)
                    {for(int i=0;i<5;i++)
                        {if(this.cards[i].getFace().equals(""A""))
                           {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                            else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                            else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                            else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                           }
                         if(that.cards[i].getFace().equals(""A""))
                            {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                             else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                             else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                             else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                            }
                        }
                    }
                 else if(this_round2==13)
                     {for(int i=0;i<5;i++)
                         {if(this.cards[i].getFace().equals(""K""))
                            {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                             else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                             else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                             else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                            }
                          if(that.cards[i].getFace().equals(""K""))
                            {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                             else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                             else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                             else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                            }
                        }
                     }
              else if(this_round2==12)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""Q""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""Q""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else if(this_round2==11)
                {for(int i=0;i<5;i++)
                    {if(this.cards[i].getFace().equals(""J""))
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(that.cards[i].getFace().equals(""J""))
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
              else
                {for(int i=0;i<5;i++)
                    {if(Integer.parseInt(this.cards[i].getFace())==this_round2)
                       {if(this.cards[i].getSuit().equals(""Spades"")) { this_round3=4;} 
                        else if(this.cards[i].getSuit().equals(""Hearts"")){this_round3=3;}
                        else if(this.cards[i].getSuit().equals(""Diamonds"")){this_round3=2;}
                        else if(this.cards[i].getSuit().equals(""Clubs"")){this_round3=1;}
                       }
                     if(Integer.parseInt(that.cards[i].getFace())==this_round2)
                       {if(that.cards[i].getSuit().equals(""Spades"")) { that_round3=4;} 
                        else if(that.cards[i].getSuit().equals(""Hearts"")){that_round3=3;}
                        else if(that.cards[i].getSuit().equals(""Diamonds"")){that_round3=2;}
                        else if(that.cards[i].getSuit().equals(""Clubs"")){that_round3=1;}
                       }
                    }
                }
             if(this_round3>that_round3){return 1;}
             if(this_round3<that_round3){return -1;}
                 }
           
           }
        return 0;
    }
    
    
        public static void main(String[] args) throws Exception{

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
        
            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                
                player.setCards(cardsArray);
                // Arrays.sort(cardsArray, cardsArray[2]SuitOrder);
             // System.out.println( cardsArray[4].compareTo(cardsArray[3]));
             
               
            }
            
            //Arrays.sort(playerArray);
            System.out.println(playerArray[1].compareTo(playerArray[3]));
         //   System.out.println(playerArray[playerCount-3].setCards(cardsArray));
            System.out.println(playerArray[0].getName());
        }
    }
}

"r04631034","0.102","105600","@068c2144450639ae1a53ec510ebb4ef4@
import java.util.*;
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

              HashMap<String,Integer> map =new HashMap<String,Integer>();
              for(int i =2;i<11;i++){
                        map.put(String.valueOf(i),i);
              }
              map.put(""J"",11);
              map.put(""Q"",12);
              map.put(""K"",13);
              map.put(""A"",14);
              map.put(""Spades"",18);
              map.put(""Hearts"",17);
              map.put(""Diamonds"",16);
              map.put(""Clubs"",15);
              
              MergeX.sort(this.cards,Card.SUIT_ORDER);    
              for(int i = 0 ; i < this.cards.length-1; i++){
                   for(int j = i+1 ; j < this.cards.length;j++){
                        if(map.get(this.cards[i].getFace()) > map.get(this.cards[j].getFace()) ) continue;
                        else{
                             String temp_Face=this.cards[j].getFace();
                             String temp_Suit=this.cards[j].getSuit();
                             this.cards[j] = new Card(this.cards[i].getFace(),this.cards[i].getSuit());
                             this.cards[i]= new Card(temp_Face , temp_Suit); 
                        }
                   }
              }
              
              MergeX.sort(that.cards,Card.SUIT_ORDER);    
              for(int i = 0 ; i < that.cards.length-1; i++){
                   for(int j = i+1 ; j < that.cards.length;j++){
                        if(map.get(that.cards[i].getFace()) > map.get(that.cards[j].getFace()) ) continue;
                        else{
                             String temp_Face=that.cards[j].getFace();
                             String temp_Suit=that.cards[j].getSuit();
                             that.cards[j] = new Card(that.cards[i].getFace(),that.cards[i].getSuit());
                             that.cards[i]= new Card(temp_Face , temp_Suit); 
                        }
                   }
              }
              
              
              /// this card
              
              
              int[][] output=new int[2][3];
              
               Set<String> Set_this = new HashSet<String>();
               for(int i = 0 ; i < this.cards.length; i ++){
                    Set_this.add(this.cards[i].getFace());
               }
               if(Set_this.size() == 2){           //  fullhouse:5
                    output[0][0]=5;
                    int count=0;
                    for(int i = 0 ; i < this.cards.length-1; i++){
                         if(map.get(this.cards[i].getFace()) ==map.get(this.cards[i+1].getFace())){
                              count++;
                              if(count==2){
                                   output[0][1]=map.get(this.cards[i-1].getFace());
                                   output[0][2]=map.get(this.cards[i-1].getSuit());
                              }
                              else count=0;
                         }
                    }
               }
               else if(Set_this.size()==3){       /// two pair :2
                    output[0][0]=2;
                    for(int i = 0 ; i < this.cards.length-1; i++){
                        if(map.get(this.cards[i].getFace()) ==map.get(this.cards[i+1].getFace())){
                                   output[0][1]=map.get(this.cards[i].getFace());
                                   output[0][2]=map.get(this.cards[i].getSuit());
                                   break;
                        }
                    }
               }
               else if(Set_this.size()==4){       /// one pair :1
                    output[0][0]=1;
                    for(int i = 0 ; i < this.cards.length-1; i++){
                        if(map.get(this.cards[i].getFace()) ==map.get(this.cards[i+1].getFace())){
                                   output[0][1]=map.get(this.cards[i].getFace());
                                   output[0][2]=map.get(this.cards[i].getSuit());
                                   break;
                        }
                    }
               }
               else if(Set_this.size() == 5){
                    int count_flush=0;
                    int count_straight=0;
                    int count_last4 = 0;
                    for(int i = 0 ; i < this.cards.length-1; i++){
                         if(map.get(this.cards[i].getFace()) - 1 ==map.get(this.cards[i+1].getFace())){
                              count_straight++;
                              if(count_straight==4){                            // straight
                                   output[0][1]=map.get(this.cards[0].getFace());
                                   output[0][2]=map.get(this.cards[0].getSuit());
                                   output[0][0]=3;
                              }
                         }
                         if(map.get(this.cards[i].getSuit()) ==map.get(this.cards[i+1].getSuit())){
                              count_last4++;
                              if(count_last4==3 & map.get(this.cards[0].getFace())==14 ){                            // fullhouse
                                   output[0][1]=map.get(this.cards[0].getFace());
                                   output[0][2]=map.get(this.cards[0].getSuit());
                                   output[0][0]=3;
                              }
                         }
                         if(map.get(this.cards[i].getSuit()) ==map.get(this.cards[i+1].getSuit())){
                              count_flush++;
                              if(count_flush==4){                            // fullhouse
                                   output[0][1]=map.get(this.cards[0].getFace());
                                   output[0][2]=map.get(this.cards[i].getSuit());
                                   output[0][0]=4;
                              }
                         }
                    }
                    if(output[0][0]==0){           //  one card :0
                         output[0][1]=map.get(this.cards[0].getFace());
                         output[0][2]=map.get(this.cards[0].getSuit());
                    }
               }
               
               
               ///// that card
               
               Set<String> Set_that = new HashSet<String>();
               for(int i = 0 ; i < that.cards.length; i ++){
                    Set_that.add(that.cards[i].getFace());
               }
               if(Set_that.size() == 2){           //  fullhouse:5
                    output[1][0]=5;
                    int count=0;
                    for(int i = 0 ; i < that.cards.length-1; i++){
                         if(map.get(that.cards[i].getFace()) ==map.get(that.cards[i+1].getFace())){
                              count++;
                              if(count==2){
                                   output[1][1]=map.get(that.cards[i-1].getFace());
                                   output[1][2]=map.get(that.cards[i-1].getSuit());
                              }
                              else count=0;
                         }
                    }
               }
               else if(Set_that.size()==3){       /// two pair :2
                    output[1][0]=2;
                    for(int i = 0 ; i < that.cards.length-1; i++){
                        if(map.get(that.cards[i].getFace()) ==map.get(that.cards[i+1].getFace())){
                                   output[1][1]=map.get(that.cards[i].getFace());
                                   output[1][2]=map.get(that.cards[i].getSuit());
                                   break;
                        }
                    }
               }
               else if(Set_that.size()==4){       /// one pair :1
                    output[0][0]=1;
                    for(int i = 0 ; i < that.cards.length-1; i++){
                        if(map.get(this.cards[i].getFace()) ==map.get(that.cards[i+1].getFace())){
                                   output[0][1]=map.get(that.cards[i].getFace());
                                   output[0][2]=map.get(that.cards[i].getSuit());
                                   break;
                        }
                    }
               }
               else if(Set_that.size() == 5){
                    int count_flush=0;
                    int count_straight=0;
                    int count_last4 = 0;
                    for(int i = 0 ; i < that.cards.length-1; i++){
                         if(map.get(that.cards[i].getFace()) - 1 ==map.get(that.cards[i+1].getFace())){
                              count_straight++;
                              if(count_straight==4){                            // straight
                                   output[1][1]=map.get(that.cards[0].getFace());
                                   output[1][2]=map.get(that.cards[0].getSuit());
                                   output[1][0]=3;
                              }
                         }
                         if(map.get(this.cards[i].getSuit()) ==map.get(this.cards[i+1].getSuit())){
                              count_last4++;
                              if(count_last4==3 & map.get(this.cards[0].getFace())==14 ){                            // fullhouse
                                   output[1][1]=map.get(this.cards[0].getFace());
                                   output[1][2]=map.get(this.cards[0].getSuit());
                                   output[1][0]=3;
                              }
                         }
                         if(map.get(that.cards[i].getSuit()) ==map.get(that.cards[i+1].getSuit())){
                              count_flush++;
                              if(count_flush==4){                            // fullhouse
                                   output[1][1]=map.get(that.cards[0].getFace());
                                   output[1][2]=map.get(that.cards[i].getSuit());
                                   output[1][0]=4;
                              }
                         }
                    }
                    if(output[1][0]==1){           //  one card :0
                         output[1][1]=map.get(that.cards[0].getFace());
                         output[1][2]=map.get(that.cards[0].getSuit());
                    }
               }
               if(output[0][0] > output[1][0]) return +1;
               if(output[0][0] < output[1][0]) return -1;
               if(output[0][1] == 4 & output[1][1] == 4){
                    if(output[0][2] > output[1][2]) return +1;
                    if(output[0][2] < output[1][2]) return -1;
               }
               if(output[0][1] > output[1][1]) return +1;
               if(output[0][1] < output[1][1]) return -1;
               if(output[0][2] > output[1][2]) return +1;
               if(output[0][2] < output[1][2]) return -1;
               return 0;
    }
}

"r04921065","0.09","105232","@8452b8e0ae3ee0502706d63c91968415@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    //private int[] Value;
     
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

    
    
   
    public int[] handcards(Card[] cards){ //回傳 Value陣列
        int[] Value = new int[6];
        for (int x =0;x<6;x++){
            Value[x]=0;
        }
        
        int sameCards=1;
        int groupRank=0;
        int[] ranks = new int[14];
        for (int x=0; x<=13; x++){
            ranks[x]=0; //zero the contents of the array
       }
// 交作業時，要獨立交Player.java，裡面不能用Card.java裡面自創的function(changeFace)
//        for (int x=0; x<5; x++){ 
//            if (cards[x].getFace().equals(""A""))
//                    ranks[1]++;
//            else
//            ranks[cards[x].changeFace(cards[x])]++;
//        }
          for (int x=0; x<5; x++){
              switch (cards[x].getFace()){  // '': char, """": String 記得要加break;否則會一行一行執行下去
                  case(""A""):
                      ranks[1]++;
                      break;
                  case(""J""):
                      ranks[11]++;
                      break;
                  case(""Q""):
                      ranks[12]++;
                      break;
                  case(""K""):
                      ranks[13]++;
                      break;
                  default:
                      //System.out.println(cards[x].getFace());
                      ranks[Integer.parseInt(cards[x].getFace())]++;
              }
          }
        
        
        for (int x=13; x>=1; x--){
            if (ranks[x]>sameCards){
                sameCards=ranks[x]; // 找最多一樣的數字
                groupRank=x;
            }
        }
        
          //flush
        boolean flush = true;  
        for (int i = 0;i<4;i++){
            if (!cards[i].getSuit().equals(cards[i+1].getSuit()))
                    flush = false;
        }
  
        
        // largeGroupRank, smallGroupRank
        sameCards=1;
        int sameCards2=1;
        int largeGroupRank=0,smallGroupRank=0;
        for (int x=13; x>=1; x--){
            if (ranks[x]>sameCards)
            {
                if (sameCards!=1){
                    sameCards2 = sameCards;
                    smallGroupRank = largeGroupRank;
                }
                sameCards = ranks[x];
                largeGroupRank = x;
            }else if (ranks[x]>sameCards2){
                sameCards2 = ranks[x];
                smallGroupRank = x;
            }
        }
        
        //Straight
        int topStraightValue=0;
        boolean straight=false;     
        for (int x=1; x<=9; x++){
            if (ranks[x]==1 && ranks[x+1]==1 && ranks[x+2]==1 && ranks[x+3]==1 && ranks[x+4]==1){
                straight = true;
                topStraightValue=x+4;
                break;
            }
        }
        if (ranks[10]==1 && ranks[11]==1 && ranks[12]==1 && ranks[13]==1 && ranks[1]==1){
            straight = true;
            topStraightValue=14;
        }

        
         int[] orderedRanks = new int[5];
         int index=0;
         if (ranks[1]==1){ //if ace, run this before because ace is highest card
         orderedRanks[index]=14;
         index++;
         }
         for (int x=13; x>=2; x--){ //從大到小找
             if (ranks[x]==1){
                 orderedRanks[index]=x;
                 index++;
             }
         }
         
         if ( sameCards==1 ) {
             Value[0]=1;   // no pair
             Value[1]=orderedRanks[0]; //the first determining factor is the highest card,
             Value[2]=orderedRanks[1]; //then the next highest card,
             Value[3]=orderedRanks[2]; //and so on
             Value[4]=orderedRanks[3];
             Value[5]=orderedRanks[4];
         }
         if (sameCards==2 && sameCards2==1) {
             Value[0]=2;  // 1 pair
             Value[1]=largeGroupRank;   //rank of pair
.
             Value[3]=orderedRanks[1];
             Value[4]=orderedRanks[2];
         }
         if (sameCards==2 && sameCards2==2) { //此時largeGroup的數目 = smallGroup的數目
             Value[0]=3;//two pair
             Value[1]= largeGroupRank>smallGroupRank ? largeGroupRank : smallGroupRank; //rank of greater pair
             Value[2]= largeGroupRank<smallGroupRank ? largeGroupRank : smallGroupRank; //rank of smaller pair
             Value[3]=orderedRanks[0];  //extra card
         }
         if (straight) {
             Value[0]=4; // straight
             Value[1]=topStraightValue;  //if we have two straights, the one with the highest top cards wins

         }
         if (flush) {
             Value[0]=5; // flush
             Value[1]=orderedRanks[0]; //tie determined by ranks of cards
             Value[2]=orderedRanks[1];
             Value[3]=orderedRanks[2];
             Value[4]=orderedRanks[3];
             Value[5]=orderedRanks[4];
         }
         if (sameCards==3 && sameCards2==2)  {
             Value[0]=6;// full house
             Value[1]=largeGroupRank;
             Value[2]=smallGroupRank;
         }
         return Value;
    }
    
    
    // TODO 
    public int compareTo(Player that) {
.
        // P1,P2 分別為this,that的Value值
           int[] P1 = this.handcards(this.cards);
           //System.out.println(P1[0]+"" ""+P1[1]);
           int[] P2 = that.handcards(that.cards);
           //System.out.println(P2[0]+"" ""+P2[1]);
          
           if (P1[0]>P2[0]) return 1;
           else if (P1[0]==P2[0])
               switch(P1[0]){
                   case 1:
                       return P1[1]>P2[1]? 1:-1;
                  // case 2:
                    //   return P1[1]>P2[1]? 1:-1;
                   case 3:
                       return P1[1]>P2[1]? 1:-1;
                   case 4:
                       return P1[1]>P2[1]? 1:-1;
                   case 5:
                       return P1[1]>P2[1]? 1:-1;
                   case 6:
                       return P1[1]>P2[1]? 1:-1;
               }
           else 
               return -1;
//         int[] F1 = new int[5];
//         int[] S1 = new int[5];
//         int[] F2 = new int[5];
//         int[] S2 = new int[5];
//       for (int i = 0;i<5;i++){
//       F1[i] = this.cards[i].changeFace(this.cards[i]);
//       S1[i] = this.cards[i].changeSuit(this.cards[i]);
//       F2[i] = that.cards[i].changeFace(that.cards[i]);
//       S2[i] = that.cards[i].changeSuit(that.cards[i]);
//         }
       
       
        //System.out.println(ff[0]);
        return 0;
    } 
}


"b02611026","0.09","104912","@5260bb9caa7561db1f09ee26db4705b0@
public class Player implements Comparable<Player> {

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
        that.sort();
        this.sort();

//        System.out.println(""that"");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(that.cards[i].getFace() + ""_"" + that.cards[i].getSuit());
//        }

//        System.out.println("" "");
//
//        System.out.println(""this"");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(this.cards[i].getFace() + ""_"" + this.cards[i].getSuit());
//        }

        int this_handkind = 0;
        int that_handkind = 0;
        
        int[] onepair_thisans = this.onepair();
        int[] onepair_thatans = that.onepair();

        if (this.fullhouse() > 0) {
            this_handkind = 6;
//            System.out.println(""this fullhous"");
        } else if (this.flush() > 0) {
            this_handkind = 5;
//            System.out.println(""this flush"");
        } else if (this.straight() > 0) {
            this_handkind = 4;
//            System.out.println(""this straight"");
        } else if (this.twopairs() > 0) {
            this_handkind = 3;
//            System.out.println(""this 2pairs"");
        } else if (onepair_thisans[0] > 0) {
            this_handkind = 2;
//            System.out.println(""this 1pair"");
        } else {
            this_handkind = 1;
//            System.out.println(""this highcard"");
        }

        if (that.fullhouse() > 0) {
            that_handkind = 6;
//            System.out.println(""that fullhous"");
        } else if (that.flush() > 0) {
            that_handkind = 5;
//            System.out.println(""that flush"");
        } else if (that.straight() > 0) {
            that_handkind = 4;
//            System.out.println(""that straight"");
        } else if (that.twopairs() > 0) {
            that_handkind = 3;
//            System.out.println(""that 2pairs"");
        } else if (onepair_thatans[0] > 0) {
            that_handkind = 2;
//            System.out.println(""that 1pair"");
        } else {
            that_handkind = 1;
//            System.out.println(""that highcard"");
        }

//        System.out.println(that.flush());
//        System.out.println(that.straight());
//        System.out.println(that_handkind);
//        System.out.println(this.flush());
//        System.out.println(this_handkind);
        if (this_handkind > that_handkind) {
            return +1;
        } else if (this_handkind < that_handkind) {
            return -1;
        } else {
            if (this_handkind == 1) {
                return this.cards[4].compareTo(that.cards[4]);
            }
            if (this_handkind == 2) {
                return this.cards[onepair_thatans[1]].compareTo(that.cards[onepair_thatans[1]]);
            }
            if (this_handkind == 3) {
                return this.cards[3].compareTo(that.cards[3]);
            }
            if (this_handkind == 4) {
                if((this.cards[4].getFace()).equals(""A"") && (that.cards[4].getFace()).equals(""A"")){
                    return this.cards[3].compareTo(that.cards[3]);
                }
                else 
                    return this.cards[4].compareTo(that.cards[4]);
            }
            if (this_handkind == 5) {
                return this.cards[4].compareTo(that.cards[4]);
            }
            else{
                return this.cards[2].compareTo(that.cards[2]);
            }
        }

//        System.out.println(this.fullhouse());
//        System.out.println(that.flush());
//        System.out.println(this.twopairs());
//        System.out.println(onepair_thatans[0]);
//        System.out.println(onepair_thatans[1]);
//        System.out.println(that.straight());
.
        
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public void sort() {
        for (int i = 0; i < 5; i++) {
            for (int j = i; j > 0; j--) {
                if (cards[j].compareTo(cards[j - 1]) < 0) {
                    exch(cards, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public int fullhouse() {
        if ((cards[0].getFace()).equals(cards[1].getFace()) && (cards[1].getFace()).equals(cards[2].getFace()) && ((cards[3].getFace()).equals(cards[4].getFace()))) {
            return 1;
        } else if ((cards[2].getFace()).equals(cards[3].getFace()) && (cards[3].getFace()).equals(cards[4].getFace()) && ((cards[0].getFace()).equals(cards[1].getFace()))) {
            return 1;
        } else {
            return 0;
        }
    }

    public int flush() {
        for (int i = 0; i < 4; i++) {
            if (!((cards[i].getSuit()).equals(cards[i + 1].getSuit()))) {
                return 0;
            }
        }
        return 1;
    }

    public int straight() {

        for (int i = 0; i < 4; i++) {
            int facenum1 = 0;
            if (cards[i].getFace().equals(""A"")) {
                facenum1 = 14;
            } else if (cards[i].getFace().equals(""J"")) {
                facenum1 = 11;
            } else if (cards[i].getFace().equals(""Q"")) {
                facenum1 = 12;
            } else if (cards[i].getFace().equals(""K"")) {
                facenum1 = 13;
            } else {
                facenum1 = Integer.parseInt(cards[i].getFace());
            }

            int facenum2 = 0;

            if (cards[i + 1].getFace().equals(""A"")) {
                facenum2 = 14;
            } else if (cards[i + 1].getFace().equals(""J"")) {
                facenum2 = 11;
            } else if (cards[i + 1].getFace().equals(""Q"")) {
                facenum2 = 12;
            } else if (cards[i + 1].getFace().equals(""K"")) {
                facenum2 = 13;
            } else {
                facenum2 = Integer.parseInt(cards[i + 1].getFace());
            }
            if (facenum2 != (facenum1 + 1)) {
                if (facenum1 == 5 && facenum2 == 14) {
//                    System.out.println(""---1"");
                    return 1;

                } else {
//                    System.out.println(""---2"");
                    return 0;
                }
            }
        }
//        System.out.println(""---3"");
        return 1;
    }

    public int twopairs() {
        if ((cards[2].getFace()).equals(cards[3].getFace()) && (cards[0].getFace()).equals(cards[1].getFace())) {
            return 1;
        } else if ((cards[1].getFace()).equals(cards[2].getFace()) && (cards[3].getFace()).equals(cards[4].getFace())) {
            return 1;
        } else if ((cards[0].getFace()).equals(cards[1].getFace()) && (cards[3].getFace()).equals(cards[4].getFace())) {
            return 1;
        } else {
            return 0;
        }
    }

    public int[] onepair() {
        int[] ans = new int[2];
        int sum = 0;
        for (int i = 1; i < 5; i++) {
            if ((cards[i - 1].getFace()).equals(cards[i].getFace())) {
                sum++;
                ans[1] = i;
            }
        }

        if (sum == 1) {
            ans[0] = 1;
        } else {
            ans[0] = 0;
        }
        return ans;
    }

    public int highcard() {

        return 0;
    }

//    public static void main(String[] args) throws Exception {
//        Card[] testthis = new Card[5];
//        testthis[0] = new Card(""9"", ""Clubs"");
//        testthis[1] = new Card(""J"", ""Diamonds"");
//        testthis[2] = new Card(""10"", ""Diamonds"");
//        testthis[3] = new Card(""K"", ""Diamonds"");
//        testthis[4] = new Card(""Q"", ""Diamonds"");
//
//        Card[] testthat = new Card[5];
//        testthat[0] = new Card(""10"", ""Hearts"");
//        testthat[1] = new Card(""8"", ""Hearts"");
//        testthat[2] = new Card(""9"", ""Hearts"");
//        testthat[3] = new Card(""J"", ""Hearts"");
//        testthat[4] = new Card(""Q"", ""Hearts"");
//
//        Player[] testp = new Player[2];
//        testp[0] = new Player(""a"");
//        testp[1] = new Player(""b"");
//
//        testp[0].setCards(testthis);
//        testp[1].setCards(testthat);
//
//       
//
//        System.out.println(testp[0].compareTo(testp[1]));
//        
//    }

}

"r04921012","0.088","103584","@5a49098461a1333c6216b12e6d51fb03@import java.util.ArrayList;
import java.util.Arrays;
public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    public String name;
    public String type;
    private ArrayList<count> C=new ArrayList<count>();
    private Card great;
     
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
        //System.out.println(name+""setting type..."");
        setType();
        great=cards[4];
    }
     
    // TODO
    public Card[] getCard(){
        return this.cards;
    }
    public int compareTo(Player that) {
.

        String[] typeOrder={"" highcard"", ""pair"", ""2pairs"", ""straight"", ""flush"", ""fullhouse""};
        int thisI=getIndex(typeOrder,this.type);
        int thatI=getIndex(typeOrder,that.type);

            if (thisI>thatI)return 1;
            else if (thisI==thatI){
                if (this.type.equals(""highcard"")||this.type.equals(""flush"")){
                    //if (this.type.equals(""straight"")&&this.cards[4].getFace().equals(""A"")&&this.cards[0].getFace().equals(""5""))
                    return this.cards[4].compareTo(that.cards[4]);
                }
                else if(this.type.equals(""straight"")||this.type.equals(""fullhouse"")||this.type.equals(""pair"")||this.type.equals(""2pairs"")){
                    return this.great.compareTo(that.great);
                }
                else return this.cards[4].compareTo(that.cards[4]);
            }
            
            else return -1;
    }
    public void setType(){
        
        C.add(new count(cards[0]));
        for (int i =1;i<5;i++){//System.out.println(""for looping..."");
            int j =0;
            do{//System.out.println(j);
                if (cards[i].getFace().equals(C.get(j).val.getFace())){
                    C.get(j).N++;
                    if (cards[i].compareTo(C.get(j).val)==1)
                        C.get(j).setVal(cards[i]);
                }
                else{ 
                    if (j==C.size()-1)
                        C.add(new count(cards[i]));}
                j++;
            }while(j<C.size());
        }
        //System.out.println(""C size=""+C.size());
        String[] faceOrder={"" 2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"",""A""};
        if (C.size()==2){
            type=""fullhouse"";
            if (C.get(0).N==3) 
                great=C.get(0).val;
            else
                great=C.get(1).val;
        }
        else if (C.size()==3){
            type=""2pairs"";
            for (int i=0;i<3;i++){
                if (C.get(i).N==2)
                    great=C.get(i).val;
            }
        }
        else if (C.size()==4){
            type=""pair"";
            for (int i=0;i<4;i++){
                if (C.get(i).N==2)
                    great=C.get(i).val;
            }
        }
        else{
            boolean b=true;
            for (int k=0;k<4;k++){
                if (!cards[k].getSuit().equals(cards[k+1].getSuit())){
                    b=false;
                    break;
                }    
            }

            if (b)type=""flush"";
            else if(getIndex(faceOrder,cards[4].getFace())==getIndex(faceOrder,cards[0].getFace())+4){
                type=""straight"";
                if (cards[0].getFace().equals(""5"")&&cards[4].getFace().equals(""A""))
                    great=cards[0];
                else
                    great=cards[4];
            }
            else type=""highcard"";
        }
        
    }
    public int getIndex(String[] ref,String s){
        int I=0;
            for (int i=0;i<ref.length;i++){
                if (ref[i].equals(s))
                    I=i;
            }
            return I;
    }

}


class count{
    public Card val;
    public int N;
    public count(Card val){
        this.val=val;
        this.N=1;
    }
    public void setVal(Card c){
        this.val=c;
    }
}



"r04921065","0.088","105008","@952971c25a52457b35f2dffe244699f5@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    //private int[] Value;
     
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

    
    
   
    public int[] handcards(Card[] cards){ //回傳 Value陣列
        int[] Value = new int[6];
        for (int x =0;x<6;x++){
            Value[x]=0;
        }
        
//        int sameCards=1;
//        int groupRank=0;
        int[] ranks = new int[14];
        for (int x=0; x<=13; x++){
            ranks[x]=0; //zero the contents of the array
       }
// 交作業時，要獨立交Player.java，裡面不能用Card.java裡面自創的function(changeFace)
//        for (int x=0; x<5; x++){ 
//            if (cards[x].getFace().equals(""A""))
//                    ranks[1]++;
//            else
//            ranks[cards[x].changeFace(cards[x])]++;
//        }
          for (int x=0; x<5; x++){
              switch (cards[x].getFace()){  // '': char, """": String 記得要加break;否則會一行一行執行下去
                  case(""A""):
                      ranks[1]++;
                      break;
                  case(""J""):
                      ranks[11]++;
                      break;
                  case(""Q""):
                      ranks[12]++;
                      break;
                  case(""K""):
                      ranks[13]++;
                      break;
                  default:
                      //System.out.println(cards[x].getFace());
                      ranks[Integer.parseInt(cards[x].getFace())]++;
              }
          }
        
        
//        for (int x=13; x>=1; x--){
//            if (ranks[x]>sameCards){
//                sameCards=ranks[x]; // 找最多一樣的數字
//                groupRank=x;
//            }
//        }
        
          //flush
        boolean flush = true;  
        for (int i = 0;i<4;i++){
            if (!cards[i].getSuit().equals(cards[i+1].getSuit()))
                    flush = false;
        }
  
        
        // largeGroupRank, smallGroupRank
        int sameCards=1;
        int sameCards2=1;
        int largeGroupRank=0,smallGroupRank=0;
        for (int x=13; x>=1; x--){
            if (ranks[x]>sameCards)
            {
                if (sameCards!=1){ //if sameCards was not the default(預設) value
                    sameCards2 = sameCards; //讓原本sameCards變次要的
                    smallGroupRank = largeGroupRank; //讓原本laregeGroupRank變次要的
                }
                sameCards = ranks[x];
                largeGroupRank = x;
                
            }else if (ranks[x]>sameCards2){ //考慮2-pair
                sameCards2 = ranks[x];
                smallGroupRank = x;  //此時不知道largeGroupRank與smallGroupRank誰大誰小
            }
        }
        if (largeGroupRank==1) largeGroupRank=largeGroupRank+13; //讓A變最大
        if (smallGroupRank==1) smallGroupRank=smallGroupRank+13; //讓A變最大
        
        
        //Straight
        int topStraightValue=0;
        boolean straight=false;     
        for (int x=1; x<=9; x++){
            if (ranks[x]==1 && ranks[x+1]==1 && ranks[x+2]==1 && ranks[x+3]==1 && ranks[x+4]==1){
                straight = true;
                topStraightValue=x+4;
                break;
            }
        }
        if (ranks[10]==1 && ranks[11]==1 && ranks[12]==1 && ranks[13]==1 && ranks[1]==1){
            straight = true;
            topStraightValue=14;
        }

        
         int[] orderedRanks = new int[5];
         int index=0;
         if (ranks[1]==1){ //if ace, run this before because ace is highest card
         orderedRanks[index]=14;
         index++;
         }
         for (int x=13; x>=2; x--){ //從大到小找
             if (ranks[x]==1){
                 orderedRanks[index]=x;
                 index++;
             }
         }
         
         if ( sameCards==1 ) {
             Value[0]=1;   // no pair
             Value[1]=orderedRanks[0]; //the first determining factor is the highest card,
             Value[2]=orderedRanks[1]; //then the next highest card,
             Value[3]=orderedRanks[2]; //and so on
             Value[4]=orderedRanks[3];
             Value[5]=orderedRanks[4];
         }
         if (sameCards==2 && sameCards2==1) {
             Value[0]=2;  // 1 pair
             Value[1]=largeGroupRank;   //rank of pair
.
             Value[3]=orderedRanks[1];
             Value[4]=orderedRanks[2];
         }
         if (sameCards==2 && sameCards2==2) { //此時largeGroup的數目 = smallGroup的數目
             Value[0]=3;//two pair
             Value[1]= largeGroupRank>smallGroupRank ? largeGroupRank : smallGroupRank; //rank of greater pair
             Value[2]= largeGroupRank<smallGroupRank ? largeGroupRank : smallGroupRank; //rank of smaller pair
             Value[3]=orderedRanks[0];  //extra card
         }
         if (straight) {
             Value[0]=4; // straight
             Value[1]=topStraightValue;  //if we have two straights, the one with the highest top cards wins

         }
         if (flush) {
             Value[0]=5; // flush
             Value[1]=orderedRanks[0]; //tie determined by ranks of cards
             Value[2]=orderedRanks[1];
             Value[3]=orderedRanks[2];
             Value[4]=orderedRanks[3];
             Value[5]=orderedRanks[4];
         }
         if (sameCards==3 && sameCards2==2)  {
             Value[0]=6;// full house
             Value[1]=largeGroupRank;
             Value[2]=smallGroupRank;
         }
         return Value;
    }
    
    
    // TODO 
    public int compareTo(Player that) {
.
        // P1,P2 分別為this,that的Value值
           int[] P1 = this.handcards(this.cards);
           //System.out.println(P1[0]+"" ""+P1[1]);
           int[] P2 = that.handcards(that.cards);
           //System.out.println(P2[0]+"" ""+P2[1]);
          
           if (P1[0]>P2[0]) return 1;
           else if (P1[0]==P2[0])
               switch(P1[0]){
                   case 1:
                       return P1[1]>P2[1]? 1:-1;
                   case 2:
                       return P1[1]>P2[1]? 1:-1;
                   case 3:
                       return P1[1]>P2[1]? 1:-1;
                   case 4:
                       return P1[1]>P2[1]? 1:-1;
                   case 5:
                       return P1[1]>P2[1]? 1:-1;
                   case 6:
                       return P1[1]>P2[1]? 1:-1;
               }
           else 
               return -1;
//         int[] F1 = new int[5];
//         int[] S1 = new int[5];
//         int[] F2 = new int[5];
//         int[] S2 = new int[5];
//       for (int i = 0;i<5;i++){
//       F1[i] = this.cards[i].changeFace(this.cards[i]);
//       S1[i] = this.cards[i].changeSuit(this.cards[i]);
//       F2[i] = that.cards[i].changeFace(that.cards[i]);
//       S2[i] = that.cards[i].changeSuit(that.cards[i]);
//         }
       
       
        //System.out.println(ff[0]);
        return 0;
    } 
}


"b03106003","0.094","103536","@6fb1b8fa028d74a494bacb135723bd63@
import java.util.Arrays;

public class Player implements Comparable<Player>{ 

    public Card[] cards = new Card[5];
    public String name;
    public int mainCardIndex = 4;
    public int kind;
     
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
        Arrays.sort(cards);
        setOrder(cards);
        /*
        if(name.equals(""Kristoff"")){
            System.out.println(name);
            System.out.println(cards[0].getSuit());
            System.out.println(cards[1].getSuit());
            System.out.println(cards[0].getSuit().equals(cards[1].getSuit()));
        }
        */
        
    }
     
    // TODO 
    public int compareTo(Player that) {
        // complete this function so the Player can be sorted according to the cards he/she has
        int ans = 0;
        /*
        if(name.equals(""Anna"")){
            System.out.println(name);
            System.out.println(cards[0].getSuit());
            System.out.println(cards[1].getSuit());
            System.out.println(cards[0].getSuit().equals(cards[1].getSuit()));
        }
        */
        Arrays.sort(cards);
        Arrays.sort(that.cards);
        
        if(this.kind > that.kind){
            ans = 1;
        }else if(this.kind < that.kind){
            ans = -1;
        }else if(this.kind == that.kind){
            ans = cards[mainCardIndex].compareTo(that.cards[that.mainCardIndex]);  
        }
        return ans;
    }
    
    public boolean sameSuit(Card c1, Card c2){
    if (c1.getSuit().equals(c2.getSuit())){
        return true;
    }
        return false;
    }
    
    //new
    
    public int setOrder(Card[] card){
    int flag = 0;
    //1 High Card
    //2 1-Pair
    //3 2-Pair
    //4 Straight
    //5 Flush
    //6 Full House
    Arrays.sort(card);
    if(full_house(card)){
        flag = 6;
    }else if(flush(card)){
        flag = 5;
    }else if(straight(card)){
        flag = 4;
    }else if(two_pare(card)){
        flag = 3;
    }else if(one_pare(card)){
        flag = 2;
    }else{
        flag = 1;
        mainCardIndex = 4;
    }        
        kind = flag;
        return flag;
    }
    public boolean full_house(Card[] card){
        boolean flag = false;
        // use three_of_a_kind and two_pare because 1 1 1 5 5  can also be seen as a two_pare
        if(two_pare(card)&&three_of_a_kind(card)&&getnumber(card[1].getFace()) != getnumber(card[3].getFace())){
            three_of_a_kind(card);
            flag = true;
        }else{
            mainCardIndex = 4;
        }
        return flag;
        
    }
    public boolean flush(Card[] card){
        boolean flag = false;
        /*
        System.out.println(name);
        System.out.println(card[0].getSuit());
        System.out.println(card[1].getSuit());
        System.out.println(card[0].getSuit().equals(card[1].getSuit()));
        */
        
        if(card[0].getSuit().equals(card[1].getSuit())&&card[1].getSuit().equals(card[2].getSuit())&&card[2].getSuit().equals(card[3].getSuit())&&card[3].getSuit().equals(card[4].getSuit())){
            flag = true;
            mainCardIndex = 4;
        }
        return flag;
    }
    public boolean straight(Card[] card){
        boolean flag = false;    
            if(getnumber(card[0].getFace())+1 == getnumber(card[1].getFace())&&getnumber(card[1].getFace())+1 == getnumber(card[2].getFace())
                    &&getnumber(card[2].getFace())+1 == getnumber(card[3].getFace())
                    &&getnumber(card[3].getFace())+1 == getnumber(card[4].getFace())
                    ){
                flag = true;
                mainCardIndex = 4;
            }else if(getnumber(card[4].getFace())== 14 &&getnumber(card[4].getFace())== 2){
                if(getnumber(card[0].getFace())+1 == getnumber(card[1].getFace())&&getnumber(card[1].getFace())+1 == getnumber(card[2].getFace())
                    &&getnumber(card[2].getFace())+1 == getnumber(card[3].getFace())
                    ){
                flag = true;
                mainCardIndex = 3;
            }
            }
        return flag;
    }        
    public boolean three_of_a_kind(Card[] card){
        boolean flag = false;
        if(getnumber(card[0].getFace()) == getnumber(card[1].getFace()) && getnumber(card[1].getFace()) == getnumber(card[2].getFace())){
            flag = true;
            mainCardIndex = 2;
        }if(getnumber(card[1].getFace()) == getnumber(card[2].getFace()) && getnumber(card[2].getFace()) == getnumber(card[3].getFace())){
            flag = true;
            mainCardIndex = 3;
        }if(getnumber(card[2].getFace()) == getnumber(card[3].getFace()) && getnumber(card[3].getFace()) == getnumber(card[4].getFace())){
            flag = true;
            mainCardIndex = 4;
        }
        return flag;
    }
    public boolean two_pare(Card[] card){
        boolean flag = false;
        if(getnumber(card[0].getFace()) == getnumber(card[1].getFace())&& getnumber(card[2].getFace()) == getnumber(card[3].getFace())){
            flag = true;
            mainCardIndex = 3;
        }else if(getnumber(card[0].getFace()) == getnumber(card[1].getFace())&&getnumber(card[3].getFace()) == getnumber(card[4].getFace())){
            flag = true;
            mainCardIndex = 4;
        }else if(getnumber(card[1].getFace()) == getnumber(card[2].getFace())&&getnumber(card[3].getFace()) == getnumber(card[4].getFace())){
            flag = true;
            mainCardIndex = 4;
        }
        return flag;
    }
    public boolean one_pare(Card[] card){
        boolean flag = false;
        if(getnumber(card[0].getFace()) == getnumber(card[1].getFace())){
            flag = true;
            mainCardIndex = 1;
        }else if(getnumber(card[1].getFace()) == getnumber(card[2].getFace())){
            flag = true;
            mainCardIndex = 2;
        }else if(getnumber(card[2].getFace()) == getnumber(card[3].getFace())){
            flag = true;
            mainCardIndex = 3;
        }else if(getnumber(card[3].getFace()) == getnumber(card[4].getFace())){
            flag = true;
            mainCardIndex = 4;
        }
        return flag;
    }
    static private int getnumber(String s){
        int flag = 0;
        switch (s){
            case""A"":
                flag = 14;
                break;
            case""2"":
                flag = 2;
                break;            
            case""3"":
                flag = 3;
                break;                
            case""4"":
                flag = 4;
                break;        
            case""5"":
                flag = 5;
                break; 
            case""6"":
                flag = 6;
                break;
            case""7"":
                flag = 7;
                break;
            case""8"":
                flag = 8;
                break;
            case""9"":
                flag = 9;
                break;
            case""10"":
                flag = 10;
                break;
            case""J"":
                flag = 11;
                break;
            case""Q"":
                flag = 12;
                break;
            case""K"":
                flag = 13;
                break;
                default:
                System.out.println(""Wrong!"");
                break;
        }
        return flag;
    
    }
    
}

"r04921028","0.094","105248","@26491c1aed53ef29517eb979d94b1210@import java.util.Arrays;

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
        String[] comsuit = { ""Clubs"" , ""Diamonds"", ""Hearts"", ""Spades""};
        String[] comface = { ""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
        
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        
        
        int [] thissamecaed = {0,0,0,0};
        int [] thatsamecaed = {0,0,0,0};
        
        int thissamesuit = 0;
        int thatsamesuit = 0;
        
        int thisstraight = 0; 
        int thatstraight = 0;
        
        int thisMVP = 0; 
        int thatMVP = 0;
        
        int [] thishold = {0,0,0,0,0,0};
        int [] thathold = {0,0,0,0,0,0};
        
        int thiscard = 0;
        int thatcard = 0;
        //======================================
        for(int i=0;i<5;i++){
            for(int j=0;j<13;j++){
                   if((this.cards[i].getFace()).equals(comface[j])){
                     thishold[i]=j;
                   }
                }
        }
        if(thishold[0]==0&&thishold[4]==13){
              thisstraight++;
        }
        for(int i=0;i<4;i++){
            if((this.cards[i].getFace()).equals(this.cards[i+1].getFace())){
              thissamecaed[i]++;
            }
            //flush
            if((this.cards[i].getSuit()).equals(this.cards[i+1].getSuit())){
              thissamesuit++;
            }
            //straight 
            if((thishold[i]+1)%13==(thishold[i+1])%13){
              thisstraight++;
            }
        }
        //=============================================
        for(int i=0;i<5;i++){
            for(int j=0;j<13;j++){
                   if((that.cards[i].getFace()).equals(comface[j])){
                     thathold[i]=j;
                   }
                }
        }
        if(thathold[0]==0&&thathold[4]==13){
              thatstraight++;
        }
        for(int i=0;i<4;i++){
            if((that.cards[i].getFace()).equals(that.cards[i+1].getFace())){
              thatsamecaed[i]++;
            }
            //flush
            if((that.cards[i].getSuit()).equals(that.cards[i+1].getSuit())){
              thatsamesuit++;
            }
            //straight 
            
            if((thathold[i]+1)==(thathold[i+1])){
              thatstraight++;
            }
			
        }
        
        //FULL HOUSE
        if((thissamecaed[0]==1&&thissamecaed[1]==1&&thissamecaed[3]==1))
        {thisMVP = 2; thiscard=0;}
        else if((thissamecaed[0]==1&&thissamecaed[2]==1&&thissamecaed[3]==1))
        {thisMVP = 4; thiscard=0;}
        //flush
        else if(thissamesuit==4)
        {thisMVP = 4; thiscard=1;}
        //straight
        else if(thisstraight==4)
        {
            thiscard=2;
            if(thishold[4]==13&&thishold[0]==0)
                thisMVP = 3;
            else 
                thisMVP = 4; 
        }
        //two pair
        else if((thissamecaed[0]==1&&thissamecaed[3]==1))
        {thisMVP=4; thiscard=3;}
        else if((thissamecaed[1]==1&&thissamecaed[3]==1))
        {thisMVP=4; thiscard=3;}
        else if((thissamecaed[0]==1&&thissamecaed[2]==1))
        {thisMVP=3; thiscard=3;}
        
        //one pair
        else if((thissamecaed[0]==1))
        {thisMVP=1; thiscard=4;}
        else if((thissamecaed[1]==1))
        {thisMVP=2; thiscard=4;}
        else if((thissamecaed[2]==1))
        {thisMVP=3; thiscard=4;}
        else if((thissamecaed[3]==1))
        {thisMVP=4; thiscard=4;}
        //high card
        else
        {thisMVP = 4; thiscard=5;}
        //=================================================
        //FULL HOUSE
        if((thatsamecaed[0]==1&&thatsamecaed[1]==1&&thatsamecaed[3]==1))
        {thatMVP = 2; thatcard=0;}
        else if((thatsamecaed[0]==1&&thatsamecaed[2]==1&&thatsamecaed[3]==1))
        {thatMVP = 4; thatcard=0;}
        //flush
        else if(thatsamesuit==4)
        {thatMVP = 4; thatcard=1;}
        //straight
        else if(thatstraight==4)
        {
            thatcard=2;
            if(thathold[4]==13&&thathold[0]==0)
                thatMVP = 3;
            else 
                thatMVP = 4; 
        }
        //two pair
        else if((thatsamecaed[0]==1&&thatsamecaed[3]==1))
        {thatMVP=4; thatcard=3;}
        else if((thatsamecaed[1]==1&&thatsamecaed[3]==1))
        {thatMVP=4; thatcard=3;}
        else if((thatsamecaed[0]==1&&thatsamecaed[2]==1))
        {thatMVP=3; thatcard=3;}
        
        //one pair
        else if((thatsamecaed[0]==1))
        {thatMVP=1; thatcard=4;}
        else if((thatsamecaed[1]==1))
        {thatMVP=2; thatcard=4;}
        else if((thatsamecaed[2]==1))
        {thatMVP=3; thatcard=4;}
        else if((thatsamecaed[3]==1))
        {thatMVP=4; thatcard=4;}
        //high card
        else
        {thatMVP = 4; thatcard=5;}

        if(thiscard<thatcard)
            return 1;
        else if(thiscard>thatcard)
            return -1;
        else if((thiscard==thatcard)&&thiscard==0)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==1)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==2)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==3)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==4)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else if((thiscard==thatcard)&&thiscard==5)
            return this.cards[thisMVP].compareTo(that.cards[thatMVP]);
        else
            return 0;
    }
}


"r04631004","0.09","105296","@34e5806e9f04922da98da9207a048217@import java.util.Arrays;

public class Player implements Comparable<Player> {

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
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        //檢查full house
        if (this.cards[0].getFace().equals(this.cards[1].getFace()) && this.cards[0].getFace().equals(this.cards[2].getFace())) { //若this是前三張相同
            if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[0].getFace().equals(that.cards[2].getFace())) { //判斷that是否也有full house
                if (this.cards[0].compareTo(that.cards[0]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[1].getFace().equals(that.cards[3].getFace())) {
                if (this.cards[0].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[2].getFace().equals(that.cards[3].getFace()) && that.cards[2].getFace().equals(that.cards[4].getFace())) {
                if (this.cards[0].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else {  //表示that不是full house
                return 1;
            }
        } else if (this.cards[1].getFace().equals(this.cards[2].getFace()) && this.cards[1].getFace().equals(this.cards[3].getFace())) { //若this是中間三張相同
            if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[0].getFace().equals(that.cards[2].getFace())) { //判斷that是否也有full house
                if (this.cards[1].compareTo(that.cards[0]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[1].getFace().equals(that.cards[3].getFace())) {
                if (this.cards[1].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[2].getFace().equals(that.cards[3].getFace()) && that.cards[2].getFace().equals(that.cards[4].getFace())) {
                if (this.cards[1].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else {  //表示that不是full house
                return 1;
            }
        } else if (this.cards[2].getFace().equals(this.cards[3].getFace()) && this.cards[2].getFace().equals(this.cards[4].getFace())) { //若this是後三張相同
            if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[0].getFace().equals(that.cards[2].getFace())) { //判斷that是否也有full house
                if (this.cards[2].compareTo(that.cards[0]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[1].getFace().equals(that.cards[3].getFace())) {
                if (this.cards[2].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else if (that.cards[2].getFace().equals(that.cards[3].getFace()) && that.cards[2].getFace().equals(that.cards[4].getFace())) {
                if (this.cards[2].compareTo(that.cards[2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            } else {  //表示that不是full house
                return 1;
            }
        }

        //檢查Flush
        int flush_check = 0;
        for (int i = 1; i < 5; i++) {
            if (Card.SUIT_ORDER.compare(this.cards[0], this.cards[i]) != 0) {
                flush_check = 1;
                break;
            }
        }
        if (flush_check == 0) {
            for (int i = 1; i < 5; i++) {  //this是flush時判斷that是不是flush
                if (Card.SUIT_ORDER.compare(that.cards[0], that.cards[i]) != 0) {
                    flush_check = 1;
                    break;
                }
            }
            if (flush_check == 1) { //that不是flush
                return 1;
            } else {
                if (Card.SUIT_ORDER.compare(this.cards[0], that.cards[0]) == 1) {
                    return 1;
                } else if (Card.SUIT_ORDER.compare(this.cards[0], that.cards[0]) == -1) {
                    return -1;
                } else //同花色時,比最後一張
                {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[4].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                }
            }
        } else {  //this不是flush時判斷that是不是flush
            flush_check = 0;
            for (int i = 1; i < 5; i++) {
                if (Card.SUIT_ORDER.compare(that.cards[0], that.cards[i]) != 0) {
                    flush_check = 1;
                    break;
                }
            }
            if (flush_check == 0) {
                return -1;
            }
        }

        //檢查Straight
        int this_straightcheck = 0; //this_straightcheck=1 時表示不是Straight
        int first = 0;
        int last = 0;
        Card[] this_maxcard = new Card[1];
        for (int i = 0; i < 5; i++) { //this是否是Straight
            switch (this.cards[i].getFace()) {
                case ""A"":
                    last = 13;
                    break;
                case ""2"":
                    last = 1;
                    break;
                case ""3"":
                    last = 2;
                    break;
                case ""4"":
                    last = 3;
                    break;
                case ""5"":
                    last = 4;
                    break;
                case ""6"":
                    last = 5;
                    break;
                case ""7"":
                    last = 6;
                    break;
                case ""8"":
                    last = 7;
                    break;
                case ""9"":
                    last = 8;
                    break;
                case ""10"":
                    last = 9;
                    break;
                case ""J"":
                    last = 10;
                    break;
                case ""Q"":
                    last = 11;
                    break;
                case ""K"":
                    last = 12;
                    break;
            }
            if (i > 0) {
                if (last - first != 1) {
                    if (i == 4 && last == 13 && first == 4) {  //A 2 3 4 5的情況
                        this_maxcard[0] = this.cards[3];
                        break;
                    }
                    this_straightcheck = 1;
                    break;
                }
            }
            if (i == 4) {
                this_maxcard[0] = this.cards[4];
            }
            first = last;
        }

        int that_straightcheck = 0;
        first = 0;
        last = 0;
        Card[] that_maxcard = new Card[1];
        for (int i = 0; i < 5; i++) { //that是否是Straight
            switch (that.cards[i].getFace()) {
                case ""A"":
                    last = 13;
                    break;
                case ""2"":
                    last = 1;
                    break;
                case ""3"":
                    last = 2;
                    break;
                case ""4"":
                    last = 3;
                    break;
                case ""5"":
                    last = 4;
                    break;
                case ""6"":
                    last = 5;
                    break;
                case ""7"":
                    last = 6;
                    break;
                case ""8"":
                    last = 7;
                    break;
                case ""9"":
                    last = 8;
                    break;
                case ""10"":
                    last = 9;
                    break;
                case ""J"":
                    last = 10;
                    break;
                case ""Q"":
                    last = 11;
                    break;
                case ""K"":
                    last = 12;
                    break;
            }
            if (i > 0) {
                if (last - first != 1) {
                    if (i == 4 && last == 13 && first == 4) {  //A 2 3 4 5的情況
                        that_maxcard[0] = that.cards[3];
                        break;
                    }
                    that_straightcheck = 1;
                    break;
                }
            }
            if (i == 4) {
                that_maxcard[0] = that.cards[4];
            }
            first = last;
        }

        if (this_straightcheck == 0 && that_straightcheck == 1) {
            return 1;
        } else if (this_straightcheck == 1 && that_straightcheck == 0) {
            return -1;
        } else if (this_straightcheck == 0 && that_straightcheck == 0) { //如果this與that都是straight
            if (this_maxcard[0].compareTo(that_maxcard[0]) == 1) {
                return 1;
            } else if (this_maxcard[0].compareTo(that_maxcard[0]) == -1) {
                return -1;
            }
        }

        //檢查 2 pair
        if (this.cards[0].getFace().equals(this.cards[1].getFace())) { //找出this第一個pair
            if (this.cards[2].getFace().equals(this.cards[3].getFace())) //this 是2 pair 且位置是0 1 2 3
            {
                if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[2].getFace().equals(that.cards[3].getFace())) //判斷that是否為2 pair
                {
                    if (this.cards[3].compareTo(that.cards[3]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[3]) == -1) {
                        return -1;
                    }
                } else if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[3].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[3].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else {  //表示that不是2 pair
                    return 1;
                }
            }
            if (this.cards[3].getFace().equals(this.cards[4].getFace())) //this 是2 pair 且位置是0 1 3 4
            {
                if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[2].getFace().equals(that.cards[3].getFace())) //判斷that是否為2 pair
                {
                    if (this.cards[4].compareTo(that.cards[3]) == 1) {
                        return 1;
                    } else if (this.cards[4].compareTo(that.cards[3]) == -1) {
                        return -1;
                    }
                } else if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[4].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[4].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else {  //表示that不是2 pair
                    return 1;
                }
            }
        }
        if (this.cards[1].getFace().equals(this.cards[2].getFace())) { //找出this第一個pair
            if (this.cards[3].getFace().equals(this.cards[4].getFace())) //this 是2 pair 且位置是 1 2 3 4
            {
                if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[2].getFace().equals(that.cards[3].getFace())) //判斷that是否為2 pair
                {
                    if (this.cards[4].compareTo(that.cards[3]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[3]) == -1) {
                        return -1;
                    }
                } else if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else if (that.cards[1].getFace().equals(that.cards[2].getFace()) && that.cards[3].getFace().equals(that.cards[4].getFace())) {
                    if (this.cards[4].compareTo(that.cards[4]) == 1) {
                        return 1;
                    } else if (this.cards[3].compareTo(that.cards[4]) == -1) {
                        return -1;
                    }
                } else {  //表示that不是2 pair
                    return 1;
                }
            }
        }

        //檢查pair
        int this_paircheck = 0;
        int temp_thisj = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (this.cards[i].getFace().equals(this.cards[j].getFace())) {
                    temp_thisj = j;
                    this_paircheck = 1;
                    break;
                }
            }
        }

        int that_paircheck = 0;
        int temp_thatj = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (that.cards[i].getFace().equals(that.cards[j].getFace())) {
                    temp_thatj = j;
                    that_paircheck = 1;
                    break;
                }
            }
        }

        if (this_paircheck == 1 && that_paircheck == 0) {
            return 1;
        } else if (this_paircheck == 0 && that_paircheck == 1) {
            return -1;
        } else if (this_paircheck == 1 && that_paircheck == 1) {
            if (this.cards[temp_thisj].compareTo(that.cards[temp_thatj]) == 1) {
                return 1;
            } else {
                return -1;
            }
        }

        //檢查High Card
        if (this.cards[4].compareTo(that.cards[4]) == 1) //比最後一張
        {
            return 1;
        } else if (this.cards[4].compareTo(that.cards[4]) == -1) {
            return -1;
        }

        return 0;
    }
}

"r03849033","0.09","105264","@5ae277d49087525110495d12b1b0c781@
import java.util.Arrays;

public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private Card thismax;
    private Card thatmax;     
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
    Arrays.sort(this.cards);
    Arrays.sort(that.cards);    
    int thisvalue = 0;
    int thatvalue = 0;
   
    int[] thispair = new int[4];
    int[] thatpair = new int[4];
    int this1 = 0;
    int that1 = 0;    
    int[] thisstraight = new int[5];
    int[] thatstraight = new int[5]; 
    int thisst = 0;
    int thatst = 0;   
    int[] thisflush  = new int[4];
    int[] thatflush  = new int[4];    

    for(int j = 0; j < 4; j++) {
        if(this.cards[j].getFace().equals(this.cards[j+1].getFace())) {thispair[j]=1; this1=j+1; }
        if(that.cards[j].getFace().equals(that.cards[j+1].getFace())) {thatpair[j]=1; that1=j+1;}        
    } 
    String[] ranks  = { ""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"",""A"" };    
    for(int i = 0; i < 13; i++) {
    for(int j = 0; j < 5; j++) {    
        if(this.cards[j].getFace().equals(ranks[i])) {thisstraight[j]=i; }
        if(that.cards[j].getFace().equals(ranks[i])) {thatstraight[j]=i; }
    }}        
    for(int j = 1; j < 5; j++) {
        if(thisstraight[j]-1==thisstraight[j-1]){thisst++;}
        if(thatstraight[j]-1==thatstraight[j-1]){thatst++;}
     }
    for(int j = 0; j < 4; j++) {
        if(this.cards[j].getSuit().equals(this.cards[j+1].getSuit())) {thisflush[j]=1; }
        if(that.cards[j].getSuit().equals(that.cards[j+1].getSuit())) {thatflush[j]=1; }        
    }
    int[] case1 = {1,1,0,1};
    int[] case2 = {1,0,1,1};
    int[] case3 = {1,1,1,1};
    
    int[] case4 = {1,0,1,0}; 
    int[] case5 = {1,0,0,1};    
    int[] case6 = {0,1,0,1};
    
    int[] case7 = {1,1,1,0};  
    int[] case8 = {0,1,1,1};
    
    int[] case9 = {0,0,0,0};
    int[] case10 = {0,1,2,3,12};    
    if(Arrays.equals(thispair,case1)){ 
        thisvalue=6;  thismax = this.cards[2]; }
    else  if(Arrays.equals(thispair,case2)){ 
        thisvalue=6;   thismax = this.cards[4]; }
    
    else  if(Arrays.equals(thisflush,case3)){ 
        thisvalue=5;   thismax = this.cards[4]; }
    
    else  if(thisst==4){ 
        thisvalue=4;   thismax = this.cards[4]; }
    else  if(Arrays.equals(thisstraight,case10)){ 
        thisvalue=3;   thismax = this.cards[4]; } 
    
    else  if(Arrays.equals(thispair,case4) || Arrays.equals(thispair,case7)){ 
        thisvalue=2;   thismax = this.cards[3]; }    
    else  if(Arrays.equals(thispair,case5) || Arrays.equals(thispair,case6) || Arrays.equals(thispair,case8)){ 
        thisvalue=2;   thismax = this.cards[4]; } 
    else if (!Arrays.equals(thispair,case9)){
        thisvalue=1;   thismax = this.cards[this1];}
    else {  thismax = this.cards[4];}
    
    if(Arrays.equals(thatpair,case1)){ 
        thatvalue=6;   thatmax = that.cards[2]; }
    else  if(Arrays.equals(thatpair,case2)){ 
        thatvalue=6;   thatmax = that.cards[4]; }
    
    else  if(Arrays.equals(thatflush,case3)){ 
        thatvalue=5;   thatmax = that.cards[4]; }
    
    else  if(thatst==4){ 
        thatvalue=4;   thatmax = that.cards[4]; } 
    else  if(Arrays.equals(thatstraight,case10)){ 
        thatvalue=3;   thatmax = that.cards[4]; }  
    
    else  if(Arrays.equals(thatpair,case4) || Arrays.equals(thatpair,case7)){ 
        thatvalue=2;   thatmax = that.cards[3]; }    
    else  if(Arrays.equals(thatpair,case5) || Arrays.equals(thatpair,case6) || Arrays.equals(thatpair,case8)){ 
        thatvalue=2;   thatmax = that.cards[4]; } 
    else if (!Arrays.equals(thatpair,case9)){
        thatvalue=1;   thatmax = that.cards[that1];}
    else {  thatmax = that.cards[4];}    
   
    if(thisvalue>thatvalue){ return 1;}
    else if(thisvalue<thatvalue){ return -1;}
    
    else if(thismax.compareTo(thatmax)>0){return 1;}
    else if(thismax.compareTo(thatmax)<0){return -1;}    
.
        return 0;
    }
}

"b03611035","0.092","104352","@baae3a736ffd971625849fc97960d11d@import java.util.Arrays;
import java.util.Comparator;

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
        // 先確定牌型，用int來存
        int thistype;
        int thattype;
        // 要存最大牌
        int thishigh=0;
        int thathigh=0;
        
        
        // 先sort牌
        Arrays.sort(cards);
        Arrays.sort(that.cards);
        int[] thiscard=new int[5];
        int[] thatcard=new int[5];
        for(int i=0;i<5;i++){
            if(cards[i].getFace().equals(""A""))thiscard[i]=14;
            else if(cards[i].getFace().equals(""J""))thiscard[i]=11;
            else if(cards[i].getFace().equals(""Q""))thiscard[i]=12;
            else if(cards[i].getFace().equals(""K""))thiscard[i]=13;
            else thiscard[i]=Integer.parseInt(cards[i].getFace());
            if(that.cards[i].getFace().equals(""A""))thatcard[i]=14;
            else if(that.cards[i].getFace().equals(""J""))thatcard[i]=11;
            else if(that.cards[i].getFace().equals(""Q""))thatcard[i]=12;
            else if(that.cards[i].getFace().equals(""K""))thatcard[i]=13;
            else thatcard[i]=Integer.parseInt(that.cards[i].getFace());
        }
        // 先檢查this
        // 葫蘆 012同&34同 or 01同&&234同 
        if(thiscard[0]==thiscard[1]&&thiscard[0]==thiscard[2]&&thiscard[3]==thiscard[4]) {
            thistype=7;
            thishigh=thiscard[0];
        }
        else if(thiscard[0]==thiscard[1]&&thiscard[2]==thiscard[3]&&thiscard[2]==thiscard[4]) {
            thistype=7;
            thishigh=thiscard[2];
        }
        // 同花
        else if(cards[0].getSuit().equals(cards[1].getSuit())&&cards[0].getSuit().equals(cards[2].getSuit())&&cards[0].getSuit().equals(cards[3].getSuit())&&cards[0].getSuit().equals(cards[4].getSuit())) {
            thistype=6;
            thishigh=thiscard[4];
        }
        // 順子2-6~10~A
        else if(thiscard[0]+1==thiscard[1]&&thiscard[0]+2==thiscard[2]&&thiscard[0]+3==thiscard[3]&&thiscard[0]+4==thiscard[4]){
            thistype=5;            
            thishigh=thiscard[4];
        }// 順子A1234
        else if(thiscard[0]==2&&thiscard[1]==3&&thiscard[2]==3&&thiscard[3]==4&&thiscard[4]==14){
            thistype=5;            
            thishigh=4;
        }
        // 三條
        else if(thiscard[0]==thiscard[1]&&thiscard[0]==thiscard[2]){
            thistype=4;
            thishigh=thiscard[0];
        }
        else if(thiscard[2]==thiscard[4]&&thiscard[3]==thiscard[4]){
            thistype=4;
            thishigh=thiscard[2];
        }
        // 兩對 01+23 01+34 12+34
        else if(thiscard[0]==thiscard[1]&&thiscard[2]==thiscard[3]){
            thistype=3;
            if(thiscard[0]>thiscard[2])thishigh=thiscard[0];
            else if(thiscard[0]<thiscard[2])thishigh=thiscard[2];            
        }
        else if(thiscard[0]==thiscard[1]&&thiscard[4]==thiscard[3]){
            thistype=3;
            if(thiscard[0]>thiscard[4])thishigh=thiscard[0];
            else if(thiscard[0]<thiscard[4])thishigh=thiscard[4];            
        }
        else if(thiscard[2]==thiscard[1]&&thiscard[4]==thiscard[3]){
            thistype=3;
            if(thiscard[2]>thiscard[4])thishigh=thiscard[2];
            else if(thiscard[2]<thiscard[4])thishigh=thiscard[4];            
        }
        // 一對01 12 23 34
        else if(thiscard[0]==thiscard[1]){
            thistype=2;
            thishigh=thiscard[0];
        }
        else if(thiscard[1]==thiscard[2]){
            thistype=2;
            thishigh=thiscard[1];
        }
        else if(thiscard[2]==thiscard[3]){
            thistype=2;
            thishigh=thiscard[2];
        }
        else if(thiscard[3]==thiscard[4]){
            thistype=2;
            thishigh=thiscard[3];
        }
        // 單隻
        else {
            thistype=1;
            thishigh=thiscard[4];
        }
        // 再檢查that
        // 葫蘆 012同&34同 or 01同&&234同 
       if(thatcard[0]==thatcard[1]&&thatcard[0]==thatcard[2]&&thatcard[3]==thatcard[4]) {
            thattype=7;
            thathigh=thatcard[0];
        }
        else if(thatcard[0]==thatcard[1]&&thatcard[2]==thatcard[3]&&thatcard[2]==thatcard[4]) {
            thattype=7;
            thathigh=thatcard[2];
        }
        // 同花
        else if(that.cards[0].getSuit().equals(that.cards[1].getSuit())&&that.cards[0].getSuit().equals(that.cards[2].getSuit())&&that.cards[0].getSuit().equals(that.cards[3].getSuit())&&that.cards[0].getSuit().equals(that.cards[4].getSuit())) {
            thattype=6;
            thathigh=thatcard[4];
        }
        // 順子2-6~10~A
        else if(thatcard[0]+1==thatcard[1]&&thatcard[0]+2==thatcard[2]&&thatcard[0]+3==thatcard[3]&&thatcard[0]+4==thatcard[4]){
            thattype=5;            
            thathigh=thatcard[4];
        }// 順子A1234
        else if(thatcard[0]==2&&thatcard[1]==3&&thatcard[2]==3&&thatcard[3]==4&&thatcard[4]==14){
            thattype=5;            
            thathigh=4;
        }
        // 三條
        else if(thatcard[0]==thatcard[1]&&thatcard[0]==thatcard[2]){
            thattype=4;
            thathigh=thatcard[0];
        }
        else if(thatcard[2]==thatcard[4]&&thatcard[3]==thatcard[4]){
            thattype=4;
            thathigh=thatcard[2];
        }
        // 兩對 01+23 01+34 12+34
        else if(thatcard[0]==thatcard[1]&&thatcard[2]==thatcard[3]){
            thattype=3;
            if(thatcard[0]>thatcard[2])thathigh=thatcard[0];
            else if(thatcard[0]<thatcard[2])thathigh=thatcard[2];            
        }
        else if(thatcard[0]==thatcard[1]&&thatcard[4]==thatcard[3]){
            thattype=3;
            if(thatcard[0]>thatcard[4])thathigh=thatcard[0];
            else if(thatcard[0]<thatcard[4])thathigh=thatcard[4];            
        }
        else if(thatcard[2]==thatcard[1]&&thatcard[4]==thatcard[3]){
            thattype=3;
            if(thatcard[2]>thatcard[4])thathigh=thatcard[2];
            else if(thatcard[2]<thatcard[4])thathigh=thatcard[4];            
        }
        // 一對01 12 23 34
        else if(thatcard[0]==thatcard[1]){
            thattype=2;
            thathigh=thatcard[0];
        }
        else if(thatcard[1]==thatcard[2]){
            thattype=2;
            thathigh=thatcard[1];
        }
        else if(thatcard[2]==thatcard[3]){
            thattype=2;
            thathigh=thatcard[2];
        }
        else if(thatcard[3]==thatcard[4]){
            thattype=2;
            thathigh=thatcard[3];
        }
        // 單隻
        else {
            thattype=1;
            thathigh=thatcard[4];
        }
        // 比牌型    
        if(thistype>thattype)return 1;
        else if(thistype<thattype)return -1;
        // 再比最大牌
        else{
            if(thishigh>thathigh)return 1;
            else if(thishigh<thathigh)return -1;
            else return 0;
        }
        
    }
}


"r04945008","0.092","105232","@9b964c9a1635072eb00a894975d1b4bb@import java.util.Arrays;
import java.util.Comparator; 
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
    
    public int findPair() {
        Arrays.sort(this.cards);
        int[] count = new int[5];
        for (int i = 0; i < 5; i++) {count[i]=0;
            for (int j = 0; j < 5; j++) {
                if(this.cards[i].getFace().equals(this.cards[j].getFace())) count[i]++;
            }
        }
        for(int i = 4; i >= 0; i--){
            if(count[i] == 3) return i;
            if(count[i] == 2) return i;
        }
        return 0;
    }
    
    public int getRank() {
        Arrays.sort(this.cards);
        int[] count = new int[5];
        for (int i = 0; i < 5; i++) {count[i]=0;
            for (int j = 0; j < 5; j++) {
                if(this.cards[i].getFace().equals(this.cards[j].getFace())) count[i]+=1;
            }
        }

        if(count[2] == 3 && (count[0] == 2 || count[4] == 2)) return +15;                                   //fullhouse
        if(this.cards[0].getSuit().equals(this.cards[1].getSuit()) && this.cards[0].getSuit().equals(this.cards[2].getSuit()) && this.cards[0].getSuit().equals(this.cards[3].getSuit()) && this.cards[0].getSuit().equals(this.cards[4].getSuit()))
        return +14; 
        
        if(this.cards[0].getFace().equals(""10"") && this.cards[1].getFace().equals(""J"") && this.cards[2].getFace().equals(""Q"") && this.cards[3].getFace().equals(""K"") && this.cards[4].getFace().equals(""A"")) return +13;         //10JQKA
        if(this.cards[0].getFace().equals(""9"") && this.cards[1].getFace().equals(""10"") && this.cards[2].getFace().equals(""J"") && this.cards[3].getFace().equals(""Q"") && this.cards[4].getFace().equals(""K""))  return +12;
        if(this.cards[0].getFace().equals(""8"") && this.cards[1].getFace().equals(""9"") && this.cards[2].getFace().equals(""10"") && this.cards[3].getFace().equals(""J"") && this.cards[4].getFace().equals(""Q""))  return +11;
        if(this.cards[0].getFace().equals(""7"") && this.cards[1].getFace().equals(""8"") && this.cards[2].getFace().equals(""9"") && this.cards[3].getFace().equals(""10"") && this.cards[4].getFace().equals(""J""))  return +10;
        if(this.cards[0].getFace().equals(""6"") && this.cards[1].getFace().equals(""7"") && this.cards[2].getFace().equals(""8"") && this.cards[3].getFace().equals(""9"") && this.cards[4].getFace().equals(""10"")) return +9;
        if(this.cards[0].getFace().equals(""5"") && this.cards[1].getFace().equals(""6"") && this.cards[2].getFace().equals(""7"") && this.cards[3].getFace().equals(""8"") && this.cards[4].getFace().equals(""9""))  return +8;
        if(this.cards[0].getFace().equals(""4"") && this.cards[1].getFace().equals(""5"") && this.cards[2].getFace().equals(""6"") && this.cards[3].getFace().equals(""7"") && this.cards[4].getFace().equals(""8""))  return +7;
        if(this.cards[0].getFace().equals(""3"") && this.cards[1].getFace().equals(""4"") && this.cards[2].getFace().equals(""5"") && this.cards[3].getFace().equals(""6"") && this.cards[4].getFace().equals(""7""))  return +6;
        if(this.cards[0].getFace().equals(""2"") && this.cards[1].getFace().equals(""3"") && this.cards[2].getFace().equals(""4"") && this.cards[3].getFace().equals(""5"") && this.cards[4].getFace().equals(""6""))  return +5;
        if(this.cards[0].getFace().equals(""2"") && this.cards[1].getFace().equals(""3"") && this.cards[2].getFace().equals(""4"") && this.cards[3].getFace().equals(""5"") && this.cards[4].getFace().equals(""A""))  return +4;          //A2345
        
        if(count[1] == 2 && count[3] == 2) return +3;                                                         //2 pair
        if(count[1] == 2 || count[3] == 2) return +2;                                                         //1 pair
        return 1;                                                                                             //high card
    }
     
    // TODO 
    public int compareTo(Player that) {
.
        int a,b;
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        if(this.getRank() < that.getRank()) return -1;
        if(this.getRank() > that.getRank()) return +1;
        if(this.getRank() == that.getRank()){
            switch(this.getRank()){
                case 15:
                    a=this.findPair();
                    b=that.findPair();
                    if(this.cards[a].compareTo(that.cards[b]) == 1) return +1; 
                    if(this.cards[a].compareTo(that.cards[b]) == -1) return -1;
                    return 0;
                case 4:
                    if(this.cards[3].compareTo(that.cards[3]) == 1) return +1; 
                    if(this.cards[3].compareTo(that.cards[3]) == -1) return -1;
                    return 0;  
                case 3:
                    a=this.findPair();
                    b=that.findPair();
                    if(this.cards[a].compareTo(that.cards[b]) == 1) return +1; 
                    if(this.cards[a].compareTo(that.cards[b]) == -1) return -1;
                    return 0;
                case 2:
                    a=this.findPair();
                    b=that.findPair();
                    if(this.cards[a].compareTo(that.cards[b]) == 1) return +1; 
                    if(this.cards[a].compareTo(that.cards[b]) == -1) return -1;
                    return 0;
                default:
                    if(this.cards[4].compareTo(that.cards[4]) == 1) return +1; 
                    if(this.cards[4].compareTo(that.cards[4]) == -1) return -1;
                    return 0;
            } 
        }
        return 0;
    }
}


"r04945008","0.092","107552","@8b701d94296c6757b57b013146d1b944@import java.util.Arrays;
import java.util.Comparator; 
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
    
    public int findPair() {
        Arrays.sort(this.cards);
        int[] count = new int[5];
        for (int i = 0; i < 5; i++) {count[i]=0;
            for (int j = 0; j < 5; j++) {
                if(this.cards[i].getFace().equals(this.cards[j].getFace())) count[i]++;
            }
        }
        for(int i = 4; i >= 0; i--){
            if(count[i] == 3) return i;
            if(count[i] == 2) return i;
        }
        return 0;
    }
    
    public int getRank() {
        Arrays.sort(this.cards);
        int[] count = new int[5];
        for (int i = 0; i < 5; i++) {count[i]=0;
            for (int j = 0; j < 5; j++) {
                if(this.cards[i].getFace().equals(this.cards[j].getFace())) count[i]+=1;
            }
        }

        if(count[2] == 3 && (count[0] == 2 || count[4] == 2)) return +15;                                   //fullhouse
        if(this.cards[0].getSuit().equals(this.cards[1].getSuit()) && this.cards[0].getSuit().equals(this.cards[2].getSuit()) && this.cards[0].getSuit().equals(this.cards[3].getSuit()) && this.cards[0].getSuit().equals(this.cards[4].getSuit()))
        return +14; 
        
        if(this.cards[0].getFace().equals(""10"") && this.cards[1].getFace().equals(""J"") && this.cards[2].getFace().equals(""Q"") && this.cards[3].getFace().equals(""K"") && this.cards[4].getFace().equals(""A"")) return +13;         //10JQKA
        if(this.cards[0].getFace().equals(""9"") && this.cards[1].getFace().equals(""10"") && this.cards[2].getFace().equals(""J"") && this.cards[3].getFace().equals(""Q"") && this.cards[4].getFace().equals(""K""))  return +12;
        if(this.cards[0].getFace().equals(""8"") && this.cards[1].getFace().equals(""9"") && this.cards[2].getFace().equals(""10"") && this.cards[3].getFace().equals(""J"") && this.cards[4].getFace().equals(""Q""))  return +11;
        if(this.cards[0].getFace().equals(""7"") && this.cards[1].getFace().equals(""8"") && this.cards[2].getFace().equals(""9"") && this.cards[3].getFace().equals(""10"") && this.cards[4].getFace().equals(""J""))  return +10;
        if(this.cards[0].getFace().equals(""6"") && this.cards[1].getFace().equals(""7"") && this.cards[2].getFace().equals(""8"") && this.cards[3].getFace().equals(""9"") && this.cards[4].getFace().equals(""10"")) return +9;
        if(this.cards[0].getFace().equals(""5"") && this.cards[1].getFace().equals(""6"") && this.cards[2].getFace().equals(""7"") && this.cards[3].getFace().equals(""8"") && this.cards[4].getFace().equals(""9""))  return +8;
        if(this.cards[0].getFace().equals(""4"") && this.cards[1].getFace().equals(""5"") && this.cards[2].getFace().equals(""6"") && this.cards[3].getFace().equals(""7"") && this.cards[4].getFace().equals(""8""))  return +7;
        if(this.cards[0].getFace().equals(""3"") && this.cards[1].getFace().equals(""4"") && this.cards[2].getFace().equals(""5"") && this.cards[3].getFace().equals(""6"") && this.cards[4].getFace().equals(""7""))  return +6;
        if(this.cards[0].getFace().equals(""2"") && this.cards[1].getFace().equals(""3"") && this.cards[2].getFace().equals(""4"") && this.cards[3].getFace().equals(""5"") && this.cards[4].getFace().equals(""6""))  return +5;
        if(this.cards[0].getFace().equals(""2"") && this.cards[1].getFace().equals(""3"") && this.cards[2].getFace().equals(""4"") && this.cards[3].getFace().equals(""5"") && this.cards[4].getFace().equals(""A""))  return +4;          //A2345
        
        if(count[0]+count[1]+count[2]+count[3]+count[4] == 9) return +3;                                                         //2 pair
        if(count[0]+count[1]+count[2]+count[3]+count[4] == 7 || count[0]+count[1]+count[2]+count[3]+count[4] == 11) return +2;    //1 pair
        return 1;                                                                                                                //high card
    }
     
    // TODO 
    public int compareTo(Player that) {
.
        int a,b;
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        if(this.getRank() < that.getRank()) return -1;
        if(this.getRank() > that.getRank()) return +1;
        if(this.getRank() == that.getRank()){
            switch(this.getRank()){
                case 15:
                    a=this.findPair();
                    b=that.findPair();
                    if(this.cards[a].compareTo(that.cards[b]) == 1) return +1; 
                    if(this.cards[a].compareTo(that.cards[b]) == -1) return -1;
                    return 0;
                case 4:
                    if(this.cards[3].compareTo(that.cards[3]) == 1) return +1; 
                    if(this.cards[3].compareTo(that.cards[3]) == -1) return -1;
                    return 0;  
                case 3:
                    a=this.findPair();
                    b=that.findPair();
                    if(this.cards[a].compareTo(that.cards[b]) == 1) return +1; 
                    if(this.cards[a].compareTo(that.cards[b]) == -1) return -1;
                    return 0;
                case 2:
                    a=this.findPair();
                    b=that.findPair();
                    if(this.cards[a].compareTo(that.cards[b]) == 1) return +1; 
                    if(this.cards[a].compareTo(that.cards[b]) == -1) return -1;
                    return 0;
                default:
                    if(this.cards[4].compareTo(that.cards[4]) == 1) return +1; 
                    if(this.cards[4].compareTo(that.cards[4]) == -1) return -1;
                    return 0;
            } 
        }
        return 0;
    }
}


"r03222054","0.088","105312","@ba58091898c84f923a1a8be8ef0edbc9@
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    
    private static int faceToInt(String thatFace)
    {
         // String thatFace = that.getFace();
          int faceInt2 = 0;
        
    if(thatFace.equals(""2""))
            faceInt2 = 2;
        if(thatFace.equals(""3""))
            faceInt2 = 3;
        if(thatFace.equals(""4""))
            faceInt2 = 4;
        if(thatFace.equals(""5""))
            faceInt2 = 5;
        if(thatFace.equals(""6""))
            faceInt2 = 6;
        if(thatFace.equals(""7""))
            faceInt2 = 7;
        if(thatFace.equals(""8""))
            faceInt2 = 8;
        if(thatFace.equals(""9""))
            faceInt2 = 9;
        if(thatFace.equals(""10""))
            faceInt2 = 10;
        if(thatFace.equals(""J""))
            faceInt2 = 11;
        if(thatFace.equals(""Q""))
            faceInt2 = 12;
        if(thatFace.equals(""K""))
            faceInt2 = 13;
        if(thatFace.equals(""A""))
            faceInt2 = 14;
        return faceInt2;
    }
       private static int suitToInt(String suitStr) 
       {
        int num1 = 0;
      //  String suitStr = that.getSuit();
        if (suitStr.equals(""Spades""))
            num1=  3;
        else if (suitStr.equals(""Hearts""))
            num1 =2;
        else if (suitStr.equals(""Diamonds""))
            num1=1;
        else if(suitStr.equals(""Clubs""))
            num1 = 0;
        
        return num1;
       }
    
    
    
    public int compareTo(Player that) 
    {
        Card[] thisCard = this.cards;
        Card[] thatCard = that.cards;
        
        int thisKey = checkCombination(this.cards);
        int thatKey = checkCombination(that.cards);
.
        return thisKey-thatKey;
    }
    
    public static boolean isStraight(List<List<Integer>> that)
    {
        
        //Arrays.sort(that);
        int key0=that.get(0).get(0);//
        for( int i =1; i<5; i++)
        {
           if( i==4 && that.get(0).get(0) == 2 && that.get(i).get(0) ==14)
               return true;
           else if(that.get(i).get(0)-key0 != i)
           // if(Card.faceToMap(that[i].getFace()) - key0 != i)
                return false;
        }
        return true;
    }
    
public static boolean isFlush(Card[] that)
{
    String lastSuit = that[0].getSuit();
    for(int i=1; i< 5; i++)
    {
        if(!that[i].getSuit().equals(lastSuit))
            return false;
        
    }
    return true;
    
}
    



public static Integer checkCombination(Card[] that)
    {
        Arrays.sort(that);
        String lastCardFace = that[0].getFace();
        int kind =1;//how many kind of face;
        
        for(int i =1; i<5; i++)
        {
            if(!that[i].getFace().equals(lastCardFace))
                kind++;
        }
        List<List<Integer>> count = new ArrayList<List<Integer>>() {} ;// how many 
        int index = 0;
        count.add(new ArrayList<Integer>());
        count.get(index).add(faceToInt(lastCardFace));
        int maxIndexCount =1;
        int key= 4*(faceToInt(that[4].getFace())-1) + suitToInt(that[4].getSuit()) ;
        
        for( int i =1; i<5; i++)
        {
            if(that[i].getFace().equals(lastCardFace))
            {
                count.get(index).add(faceToInt(lastCardFace));
                if(count.get(index).size() == maxIndexCount)
                {
                  //  maxIndexCount++;
                    key = 4*(faceToInt(that[i].getFace())-1) + suitToInt(that[i].getSuit()) ;
                }
                
                if(count.get(index).size() > maxIndexCount)
                {
                    maxIndexCount++;
                    key = 4*(faceToInt(that[i].getFace())-1) + suitToInt(that[i].getSuit()) ;
                }
            }
            
            else
            {
              index ++;
                lastCardFace = that[i].getFace();
                 count.add(new ArrayList<Integer>());
                count.get(index).add(faceToInt(lastCardFace));
            }         
        }       
        if (count.size() == 2 && maxIndexCount ==3)// fullhouse
            return 6*56+key;
        
        else if(count.size() ==5 )
        {
            if (isFlush(that))
                return 5*56 +key;
            
            else if(Player.isStraight(count))
            {
                if(that[4].getFace().equals(""A"") && that[3].getFace().equals(""5""))
                {
                  // System.out.print(that[3].getFace());
                    key = 4*(faceToInt(that[3].getFace())-1) + suitToInt(that[3].getSuit()) ;
                   // return 4*56 +key
                }
                return 4*56 + key;
            }

            
            else return 1*56+key;
        }
        
        if(maxIndexCount ==2 &&count.size() == 3 )
            return 3*56+key;
        
        else if(maxIndexCount ==2 &&count.size() == 4 )
            return 2*56+key;
      
       // String one = Integer.toString(maxIndexCount);
       // String two = Integer.toString(count.size());
    //    String three = new String(one + "" ""+two);
        return 0;
        

    }
    
}

"b03611038","0.096","104192","@07a12bf93819adde1e4aa85ee56053cb@import java.util.Arrays;
public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private String[] copy = {""0"",""1"",""2"",""3"",""4"",""5"",""6"",""7"",""8"",""9"",""10"", ""J"", ""Q"", ""K"", ""A""};
    private String[] Suit = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""}; 
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
      public int findnum2(Card card){
        
        for(int j =0;j<16;j++){
         if(card.getFace().equals(copy[j]))
             return j;
        }
        return -1;
    }
     public int findsuit2(Card card){
        for(int j = 0; j<4;j++){
            if(card.getSuit().equals(Suit[j]))
                return j;
        }
        return -1;
    }
    // TODO 
    public int compareTo(Player that) {
        int a,b;
        
        this.cards = this.reang(this.cards);
        that.cards = that.reang(that.cards);
        
        a = this.findtype(this.cards);
        b = that.findtype(that.cards);
        
        if(a>b)
            return 1;
        else if(a<b)
                return -1;
        else{
            if(a==5){
                Card aa;
                Card bb;
                aa = this.confullhouse(this.cards);
                bb = that.confullhouse(that.cards);
               if(this.findnum2(aa)>this.findnum2(bb))
                    return 1;
                if(this.findnum2(aa)<this.findnum2(bb))
                    return -1;
                else{
                    if(this.findsuit2(aa)>this.findsuit2(bb))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==4){
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return 1;
                if(this.findnum2(this.cards[4])<that.findnum2((that.cards[4])))
                    return -1;
                else{
                    if(this.findsuit2(this.cards[4])>that.findsuit2((that.cards[4])))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==3){
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return 1;
                if(this.findnum2(this.cards[4])<that.findnum2((that.cards[4])))
                    return -1;
                else{
                    if(this.findsuit2(this.cards[4])>that.findsuit2((that.cards[4])))
                        return 1;
                    else 
                        return -1;
                }
                
            }
            if(a==2){
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return 1;
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return -1;
                else{
                    if(this.findsuit2(this.cards[4])>that.findsuit2((that.cards[4])))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==1){
                Card aa;
                Card bb;
                aa = this.contwopair(this.cards);
                bb = that.contwopair(that.cards);
               if(this.findnum2(aa)>this.findnum2(bb))
                    return 1;
                if(this.findnum2(aa)<this.findnum2(bb))
                    return -1;
                else{
                    if(this.findsuit2(aa)>this.findsuit2(bb))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==0){
                Card aa;
                Card bb;
                aa = this.conpair(this.cards);
                bb = that.conpair(that.cards);
               if(this.findnum2(aa)>this.findnum2(bb))
                    return 1;
                if(this.findnum2(aa)<this.findnum2(bb))
                    return 1;
                else{
                    if(this.findsuit2(aa)>this.findsuit2(bb))
                        return 1;
                    else 
                        return -1;
                }
            }
            if(a==-1){
                if(this.findnum2(this.cards[4])>that.findnum2((that.cards[4])))
                    return 1;
                if(this.findnum2(this.cards[4])<that.findnum2((that.cards[4])))
                    return -1;
                else{
                    if(this.findsuit2(this.cards[4])>that.findsuit2((that.cards[4])))
                        return 1;
                    else 
                        return -1;
                }
            }
        }        
        
        
.
        return 0;
    }
    public Card[] reang(Card[] cards){
       // Card[] after = new Card[5];
        for(int i = 0;i<5;i++){
            int min = i;
            for(int j = i+1;j<5;j++){
                if(this.findnum2(cards[min])>this.findnum2(cards[j])){
                    Card temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
                else if(this.findnum2(cards[min])==this.findnum2(cards[j]) &&
                        this.findsuit2(cards[min])>this.findsuit2(cards[j])){
                    Card temp = cards[i];
                    cards[i] = cards[j];
                    cards[j] = temp;
                }
                    
            }
        }
        
        return cards;
    }
    public int findtype(Card[] cards){       
        int[] fa = new int[5];
        int[] su = new int[5];       
        for(int i =0 ;i<5;i++){
            fa[i] =this.findnum2(cards[i]);            
            su[i] =this.findsuit2(cards[i]);
        }        
                
        if(fa[0] ==fa[1] && fa[0] == fa[2] && fa[3] == fa[4])
            return 5;
        else if(fa[0] ==fa[1] && fa[2] == fa[3] && fa[2] == fa[4])
            return 5;
        else if(su[0] == su[1] && su[0] == su[2] && su[0] == su[3] && su[0] == su[4])
            return 4;
        else if(fa[0]+1==fa[1]&&fa[0]+2==fa[2]&&fa[0]+3==fa[3]
                &&fa[0]+4==fa[4])
            return 3;
        else if(fa[4]==14&&fa[3]==13&&fa[2]==12&&fa[1]==11&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==13&&fa[2]==12&&fa[1]==3&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==13&&fa[2]==4&&fa[1]==3&&fa[0]==2)
            return 3;
         else if(fa[4]==14&&fa[3]==5&&fa[2]==4&&fa[1]==3&&fa[0]==2)
            return 2;
         else if(fa[0]==fa[1]&&fa[2]==fa[3])
             return 1;
         else if(fa[0]==fa[1]&&fa[3]==fa[4])
             return 1;
         else if(fa[1]==fa[2]&&fa[3]==fa[4])
             return 1;
         else if(fa[0]==fa[1])
             return 0;
         else if(fa[1]==fa[2])
             return 0;
         else if(fa[2]==fa[3])
             return 0;
         else if(fa[3]==fa[4])
             return 0;
        else
             return -1;
    }
    
     public Card confullhouse(Card[] that){
          int[] fa = new int[5];
          for(int i =0 ;i<5;i++){
            fa[i] = this.findnum2(that[i]);                     
        } 
          if(fa[0]==fa[1]&&fa[0]==fa[2])
              return that[2];
          else
         return that[4];
     }
     public Card contwopair(Card[] that){
         int[] fa = new int[5];
          for(int i =0 ;i<5;i++){
             fa[i] = this.findnum2(that[i]);                    
        } 
          if(fa[2]==fa[3])
              return that[3];
          else
              return that[4];
     }
     public Card conpair(Card[] that){
          int[] fa = new int[5];
          for(int i =0 ;i<5;i++){
            fa[i] = this.findnum2(that[i]);                     
        } 
          if(fa[0]==fa[1])
              return that[1];
          else if(fa[1]==fa[2])
              return that[2];
          else if(fa[2]==fa[3])
              return that[3];
          else
              return that[4];
     }
    
   public static void main(String[] args){
       
     } 
}


"r04631025","0.094","105744","@ca6d7d1972258f3a5e31056562e97394@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    public Card[] HighCard = new Card[1] ;
    
    public void HighCard (Card cards) {
        this.HighCard[0] = cards;
    }
     
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
    public boolean isFullHouse(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards);
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace()))
                    this.HighCard(Cards[0]) ;
                return true ;
            }
            else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                    this.HighCard(Cards[4]) ;
                    return true ;
                }
            }
        }
        return false ;
    }
    public boolean isFlush(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getSuit().equalsIgnoreCase(Cards[1].getSuit())){
            if(Cards[1].getSuit().equalsIgnoreCase(Cards[2].getSuit())){
                if(Cards[2].getSuit().equalsIgnoreCase(Cards[3].getSuit())){
                 if(Cards[3].getSuit().equalsIgnoreCase(Cards[4].getSuit())){
                        this.HighCard(Cards[4]) ;
            return true ;
                    }
                }
            }
        }
            return false ;
        }
    public boolean isStraight(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
      
        MergeX.sort(Cards);
        Integer[] a1 = new Integer[Cards.length] ;
        for(int i = 0 ; i < 5 ; i++){
            if(Cards[i].getFace().equalsIgnoreCase(""J""))
                a1[i] = 11 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""Q""))
                a1[i] = 12 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""K""))
                a1[i] = 13 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""A""))
                a1[i] = 14 ;
            else
            a1[i] = Integer.parseInt(Cards[i].getFace());            
        }
        if(a1[1]==a1[0]+1){
            if(a1[2] == a1[1]+1){
                if(a1[3]== a1[2]+1){
                    if(a1[4] == a1[3]+1){
                        this.HighCard(Cards[4]) ;
                        return true ;
                    }
                }
            }
        }
        else if(a1[0]==2){
            if(a1[1]==3){
                if(a1[2]==4){
                    if(a1[3]==5){
                        if(a1[4]==14){
                            this.HighCard(Cards[3]) ;
                            return true ;
                        }
                    }
                }
            }
        }
        return false ;
    }
    public boolean isTwoPair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                this.HighCard(Cards[3]) ;
                return true ;
            }
            else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
       }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
        }
        return false;
    }
    public boolean isOnePair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            this.HighCard(Cards[1]) ;
            return true ;
        }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            this.HighCard(Cards[2]) ;
            return true ;
        }
        else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
            this.HighCard(Cards[3]) ;
            return true ;
        }
        else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
            this.HighCard(Cards[4]) ;
            return true ;
        }
        
        return false ;
    }
    // TODO 
    public int compareTo(Player that) {
        
.
        
            int[] Points = new int [2] ;
            if(this.isFullHouse())
                Points[0] = 60 ;
            
            else if(this.isFlush())
                Points[0] = 50 ;
            
            else if(this.isStraight())
                Points[0] = 40 ;
            
            else if(this.isTwoPair()){
                Points[0] = 30 ;
            }
            else if(this.isOnePair()){
                Points[0] = 20 ;
            
            }
            else
                Points[0] = 10 ;
            
            
            if(that.isFullHouse())
                Points[1] = 60 ;
            
            else if(that.isFlush())
                Points[1] = 50 ;
            
            else if(that.isStraight())
                Points[1] = 40 ;
            
            else if(that.isTwoPair()){
                Points[1] = 30 ;
            }
            else if(that.isOnePair()){
                Points[1] = 20 ;

    }
            else 
                Points[1] = 10 ;
            
//            System.out.println(Points[0]+"" ""+Points[1] ) ;
            
            if(Points[0]>Points[1])
                return 1 ;
            else if(Points[0]<Points[1])
                return -1 ;
            
            else if(Points[0]==Points[1])
            if(Points[0] == 10){
                MergeX.sort(this.cards);
                MergeX.sort(that.cards);
                this.HighCard(this.cards[4]) ;
                that.HighCard(that.cards[4]) ;
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
                }
            else 
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
            return 0 ;
    }
   

}


"b03611041","0.144","116496","@62365cb548356ea22416f30a63c43812@import java.util.Arrays;

public class Player implements Comparable<Player> {

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
        int countThis = 0, ptsThis = 0, countThat = 0, ptsThat = 0, strThis = 0, strThat = 0, flushThis = 0, flushThat = 0;
        Arrays.sort(this.cards, Card::compareTo);
        Arrays.sort(that.cards, Card::compareTo);

        int[] iFace1 = new int[cards.length];
        int[] iFace2 = new int[cards.length];
        int[] j1 = new int[cards.length];
        int[] j2 = new int[cards.length];

        for (int i = 0; i < cards.length; i++) {
            if (this.cards[i].getFace().equals(""A"")) iFace1[i] = 14;
            else if (this.cards[i].getFace().equals(""J"")) iFace1[i] = 11;
            else if (this.cards[i].getFace().equals(""Q"")) iFace1[i] = 12;
            else if (this.cards[i].getFace().equals(""K"")) iFace1[i] = 13;
            else iFace1[i] = Integer.parseInt(this.cards[i].getFace());

            if (that.cards[i].getFace().equals(""A"")) iFace2[i] = 14;
            else if (that.cards[i].getFace().equals(""J"")) iFace2[i] = 11;
            else if (that.cards[i].getFace().equals(""Q"")) iFace2[i] = 12;
            else if (that.cards[i].getFace().equals(""K"")) iFace2[i] = 13;
            else iFace2[i] = Integer.parseInt(that.cards[i].getFace());
//            System.out.println(this.cards[i].getFace()+"" ""+this.cards[i].getSuit());
        }
//        System.out.println(""\n"");
        for (int i = 0; i < cards.length - 1; i++) {
            if (iFace1[i] == iFace1[i + 1]) {
                j1[i] = 1;
                j1[i + 1] = 1;
                countThis++;
            }
            if (iFace2[i] == iFace2[i + 1]) {
                j2[i] = 1;
                j2[i + 1] = 1;
                countThat++;
            }
            if (Math.abs(iFace1[i] - iFace1[i + 1]) != 1) strThis++;
            if (Math.abs(iFace2[i] - iFace2[i + 1]) != 1) strThat++;
            if (!this.cards[i].getSuit().equals(this.cards[i + 1].getSuit())) flushThis++;
            if (!that.cards[i].getSuit().equals(that.cards[i + 1].getSuit())) flushThat++;
        }

        if (iFace1[0] == 2 && iFace1[1] == 3 && iFace1[2] == 4 && iFace1[3] == 5 && iFace1[4] == 14) strThis = 0;
        if (iFace2[0] == 2 && iFace2[1] == 3 && iFace2[2] == 4 && iFace2[3] == 5 && iFace2[4] == 14) strThat = 0;

        if (countThis == 3) ptsThis += 5;
        else if (countThis == 2) ptsThis += 2;
        else if (countThis == 1) ptsThis += 1;

        if (countThat == 3) ptsThat += 5;
        else if (countThat == 2) ptsThat += 2;
        else if (countThat == 1) ptsThat += 1;

        if (strThis == 0) ptsThis += 3;
        if (strThat == 0) ptsThat += 3;

        if (flushThis == 0) ptsThis += 4;
        if (flushThat == 0) ptsThat += 4;
//        System.out.println(ptsThis);
.
        if (ptsThis - ptsThat != 0) return ptsThis - ptsThat;
        else {
            if(countThat==countThis &&countThat!=0){
                int a=-1,b=-1;
                for (int i = j1.length-1; i >=0; i--) {
                    if(j1[i]==1 && a==-1)a=i;
                    if(j2[i]==1 && b==-1)b=i;
                }
                if(iFace1[a]-iFace2[b]==0)return this.cards[a].getSuit().compareTo(that.cards[b].getSuit());
                else return iFace1[a]-iFace2[b];
            }
            if(iFace1[0]-iFace2[0]==0)return this.cards[0].getSuit().compareTo(that.cards[0].getSuit());
            else return iFace1[0] - iFace2[0];
        }
    }
}
"r04921044","0.092","105280","@f3731d483e3f31e083fa5a027ac0cf69@
import java.util.*;

public class Player implements Comparable<Player>{

    public Card[] cards = new Card[5];
    private String name;

    public enum Type {
        NOT_SET(1),
        HIGH_CARD(2),
        ONE_PAIR(3),
        TWO_PAIR(4),
        STRAIGHT(5),
        FLUSH(6),
        FULL_HOUSE(7);

        private final int _val;

        Type(int value) {
            _val = value;
        }

        public static int compareTo(Type t1, Type t2) {
            return t1._val - t2._val;
        }
    }

    public Type type;
    public Card highestCard;

    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
        this.type = Type.NOT_SET;
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

        if (this.type == Type.NOT_SET) {
            this.distinguishType();
        }
        if (that.type == Type.NOT_SET) {
            that.distinguishType();
        }

        if (this.type != that.type)
            return Type.compareTo(this.type, that.type);

        return this.highestCard.compareTo(that.highestCard);
    }

    public void distinguishType() {
        Arrays.sort(cards);

        if (this.isFullHouse() ||
            this.isFlush() ||
            this.isStraight() ||
            this.hasPair())
            return;

        this.type = Type.HIGH_CARD;
        this.highestCard = cards[4];
    }

    public boolean isFullHouse() {
        if (!cards[0].getFace().equals(cards[1].getFace()) ||
            !cards[3].getFace().equals(cards[4].getFace()))
            return false;

        if (cards[2].getFace().equals(cards[1].getFace())) {
            this.type = Type.FULL_HOUSE;
            this.highestCard = cards[2];
            return true;
        }
        else if (cards[2].getFace().equals(cards[3].getFace())) {
            this.type = Type.FULL_HOUSE;
            this.highestCard = cards[4];
            return true;
        }

        return false;
    }

    public boolean isFlush() {
        if (cards[0].getSuit().equals(cards[1].getSuit()) &&
            cards[1].getSuit().equals(cards[2].getSuit()) &&
            cards[2].getSuit().equals(cards[3].getSuit()) &&
            cards[3].getSuit().equals(cards[4].getSuit())) {
            this.type = Type.FLUSH;
            this.highestCard = cards[4];
            return true;
        }

        return false;
    }

    public boolean isStraight() {
        Map<String, Integer> faceMap = new HashMap<String, Integer> ();
        faceMap.put(""A"", 14);
        faceMap.put(""2"", 2);
        faceMap.put(""3"", 3);
        faceMap.put(""4"", 4);
        faceMap.put(""5"", 5);
        faceMap.put(""6"", 6);
        faceMap.put(""7"", 7);
        faceMap.put(""8"", 8);
        faceMap.put(""9"", 9);
        faceMap.put(""10"", 10);
        faceMap.put(""J"", 11);
        faceMap.put(""Q"", 12);
        faceMap.put(""K"", 13);

        int count = 0;
        for (int i=0; i<5; ++i) {
            if ((faceMap.get(cards[i].getFace())+1) % 13 == faceMap.get(cards[(i+1)%5].getFace()) % 13)
                count++;
        }

        if (count == 4) {
            this.type = Type.STRAIGHT;
            this.highestCard = cards[4];
            return true;
        }

        return false;
    }

    public boolean hasPair() {
        int count = 0;
        for (int i=0; i<4; ++i) {
            if (cards[i].getFace().equals(cards[i+1].getFace())) {
                this.highestCard = cards[i+1];
                count++;
                i++;
            }
        }

        if (count == 2) {
            this.type = Type.TWO_PAIR;
            return true;
        }
        else if (count == 1) {
            this.type = Type.ONE_PAIR;
            return true;
        }

        return false;
    }
}

"r03522826","0.092","105696","@7515f59fbe1d03af34045638b14092b2@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private int Judge, index, special;
    
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
//        Maxnum = 0;
        special = 0;
        index = 0;
    }
    
    public Integer getCase(){
        return Judge;
    }
    
    public Integer getIndex(){
        return index;
    }
    
    public String getCard(int index){
        return cards[index].getFace();
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
     public void Pairs()
     {
//         int pairnum = 0;
         int pairsize = 0;
         int ind1 = 0;
         for(int i = 0; i < 5; i++){
//             while(cards[i].getNumber() != pairnum){
                for(int j = i+1; j < 5; j++)
                {
                    if(cards[i].getFace().equals(cards[j].getFace()))
                    {
                        ind1 = i;
                        if(pairsize < 4){
                            pairsize += 1;
//                            paircounts += 1;
                        }
                        if(cards[i].compareTo(cards[ind1]) > 0){
//                            Maxpair = cards[i].getNumber();
                            index = i;
                        }
                        
                    }
                }
//             }
         }
         if((pairsize == 1)||(pairsize == 3))
             Judge = 2;
         if((pairsize == 2))
             Judge = 3;
         if(pairsize == 4)
             Judge = 6;
     }
     
     public void Straight(){
         int[] Number = new int[5];
         for (int i = 0; i < 5; i++){ 
             if(cards[i].getFace().equals(""A""))
                 Number[i] = 1;
             else if(cards[i].getFace().equals(""K""))
                 Number[i] = 13;
             else if(cards[i].getFace().equals(""Q""))
                 Number[i] = 12;
             else if(cards[i].getFace().equals(""J""))
                 Number[i] = 11;
             else
                 Number[i] = Integer.parseInt(cards[i].getFace());
         }
         
         if(((Number[4] - Number[0]) == 4)&&(Judge < 5)){
             if((Number[3] - Number[1])==2)
                 if((Number[2]-Number[1])==1)
                    Judge = 4;
                    index = 4;
         }
         if((Number[4] == 1)&&(Judge < 5))
         {
             if(((Number[3]-Number[0]) == 3)&&(Number[3] == 5)){
                 if((Number[2]-Number[1]) == 1)
                    Judge = 4;
                    special  = 1;
                    index = 3;
             }
             if(((Number[3]-Number[0]) == 3)&&(Number[3] == 13)){
                 if((Number[2]-Number[1]) == 1)
                    Judge = 4;
             }
         }
     }
     
     public void Flush(){
         int count = 1;
         for(int i = 0; i < 4; i++)
             if(cards[i].getSuit().equals(cards[i+1].getSuit()))
                 count += 1;
         if ((count == 5)&&(Judge < 6))
             Judge = 5;
     }
     
     
    // TODO 
    public int compareTo(Player that) {
.
        Insertion.sort(this.cards);
        Insertion.sort(that.cards);
        this.Judge = 1;
        that.Judge = 1;
//        this.Maxnum = this.cards[4].getNumber();
//        that.Maxnum = that.cards[4].getNumber();
        this.index = 4;
        that.index = 4;
        this.Pairs();
        this.Flush();
        this.Straight();
        that.Pairs();
        that.Flush();
        that.Straight();
        if (this.Judge > that.Judge)
            return 1;
        else if (this.Judge < that.Judge)
            return -1;
        else
            return (this.cards[this.index].compareTo(that.cards[that.index]));
        
    }
}

"r03522826","0.096","105664","@7515f59fbe1d03af34045638b14092b2@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private int Judge, index, special;
    
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
//        Maxnum = 0;
        special = 0;
        index = 0;
    }
    
    public Integer getCase(){
        return Judge;
    }
    
    public Integer getIndex(){
        return index;
    }
    
    public String getCard(int index){
        return cards[index].getFace();
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
     public void Pairs()
     {
//         int pairnum = 0;
         int pairsize = 0;
         int ind1 = 0;
         for(int i = 0; i < 5; i++){
//             while(cards[i].getNumber() != pairnum){
                for(int j = i+1; j < 5; j++)
                {
                    if(cards[i].getFace().equals(cards[j].getFace()))
                    {
                        ind1 = i;
                        if(pairsize < 4){
                            pairsize += 1;
//                            paircounts += 1;
                        }
                        if(cards[i].compareTo(cards[ind1]) > 0){
//                            Maxpair = cards[i].getNumber();
                            index = i;
                        }
                        
                    }
                }
//             }
         }
         if((pairsize == 1)||(pairsize == 3))
             Judge = 2;
         if((pairsize == 2))
             Judge = 3;
         if(pairsize == 4)
             Judge = 6;
     }
     
     public void Straight(){
         int[] Number = new int[5];
         for (int i = 0; i < 5; i++){ 
             if(cards[i].getFace().equals(""A""))
                 Number[i] = 1;
             else if(cards[i].getFace().equals(""K""))
                 Number[i] = 13;
             else if(cards[i].getFace().equals(""Q""))
                 Number[i] = 12;
             else if(cards[i].getFace().equals(""J""))
                 Number[i] = 11;
             else
                 Number[i] = Integer.parseInt(cards[i].getFace());
         }
         
         if(((Number[4] - Number[0]) == 4)&&(Judge < 5)){
             if((Number[3] - Number[1])==2)
                 if((Number[2]-Number[1])==1)
                    Judge = 4;
                    index = 4;
         }
         if((Number[4] == 1)&&(Judge < 5))
         {
             if(((Number[3]-Number[0]) == 3)&&(Number[3] == 5)){
                 if((Number[2]-Number[1]) == 1)
                    Judge = 4;
                    special  = 1;
                    index = 3;
             }
             if(((Number[3]-Number[0]) == 3)&&(Number[3] == 13)){
                 if((Number[2]-Number[1]) == 1)
                    Judge = 4;
             }
         }
     }
     
     public void Flush(){
         int count = 1;
         for(int i = 0; i < 4; i++)
             if(cards[i].getSuit().equals(cards[i+1].getSuit()))
                 count += 1;
         if ((count == 5)&&(Judge < 6))
             Judge = 5;
     }
     
     
    // TODO 
    public int compareTo(Player that) {
.
        Insertion.sort(this.cards);
        Insertion.sort(that.cards);
        this.Judge = 1;
        that.Judge = 1;
//        this.Maxnum = this.cards[4].getNumber();
//        that.Maxnum = that.cards[4].getNumber();
        this.index = 4;
        that.index = 4;
        this.Pairs();
        this.Flush();
        this.Straight();
        that.Pairs();
        that.Flush();
        that.Straight();
        if (this.Judge > that.Judge)
            return 1;
        else if (this.Judge < that.Judge)
            return -1;
        else
            return (this.cards[this.index].compareTo(that.cards[that.index]));
        
    }
}

"r04631041","0.092","105696","@55426360ce0f35f03ccd12b8b212a4c5@public class Player implements Comparable<Player> {

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
                        side = 4;
                    }
                }
            }
        }
        return a;
    }

    private int isstraight(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) - facetointeger(cards[1].getFace()) == -1) {
            if (facetointeger(cards[1].getFace()) - facetointeger(cards[2].getFace()) == -1) {
                if (facetointeger(cards[2].getFace()) - facetointeger(cards[3].getFace()) == -1) {
                    if (facetointeger(cards[3].getFace()) - facetointeger(cards[4].getFace()) == -1) {
                        a = 1;
                    }
                }
            }
        }
        return a;
    }
     private int pairsnumber2(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 3;
            }
            if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace()) ){
                a=1;
                side = 4;
            }
        }
        if(facetointeger(cards[2].getFace())==facetointeger(cards[1].getFace()) && facetointeger(cards[4].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 4;
            
        }
                  
        return a;
    }
        
        private int pairsnumber1(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            a=1;
            side = 1;
            if(facetointeger(cards[0].getFace())==facetointeger(cards[2].getFace()) ) side = 2;
        }else if(facetointeger(cards[1].getFace())==facetointeger(cards[2].getFace())  ){
            a=1;
            side = 2;
            if(facetointeger(cards[1].getFace())==facetointeger(cards[3].getFace()) ) side = 3;
        }else if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace())  ){
            a=1;
            side = 3;
            if(facetointeger(cards[2].getFace())==facetointeger(cards[4].getFace()) ) side = 4;
        }else if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace())  ){
            a=1;
            side = 4;
        }
                  
        return a;
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
        }else if(pairsnumber2(this.cards)==1){
            rank1=3;
            side1=side;
        }else if(pairsnumber1(this.cards)==1){
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
        }else if(pairsnumber2(that.cards)==1){
            rank2=3;
            side2=side;
        }else if(pairsnumber1(that.cards)==1){
            rank2=2;
            side2=side;
        }else{
            rank2=1;
        }
        if(rank1>rank2) return 1;
        if(rank1<rank2) return -1;
        if(rank1==rank2){
            if(rank1==6) {
                if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
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
            if(rank1==3 ){
                 if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
                else return -1;
            }
            if(rank1==2){
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

"r04631027","0.096","105792","@7905d0f75820c3bf3b84c3739be3ffd2@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    public Card[] HighCard = new Card[1] ;
    
    public void HighCard (Card cards) {
        this.HighCard[0] = cards;
    }
     
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
    public boolean isFullHouse(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards);
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace()))
                    this.HighCard(Cards[0]) ;
                return true ;
            }
            else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                    this.HighCard(Cards[4]) ;
                    return true ;
                }
            }
        }
        return false ;
    }
    public boolean isFlush(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getSuit().equalsIgnoreCase(Cards[1].getSuit())){
            if(Cards[1].getSuit().equalsIgnoreCase(Cards[2].getSuit())){
                if(Cards[2].getSuit().equalsIgnoreCase(Cards[3].getSuit())){
                 if(Cards[3].getSuit().equalsIgnoreCase(Cards[4].getSuit())){
                        this.HighCard(Cards[4]) ;
            return true ;
                    }
                }
            }
        }
            return false ;
        }
    public boolean isStraight(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
      
        MergeX.sort(Cards);
        Integer[] a1 = new Integer[Cards.length] ;
        for(int i = 0 ; i < 5 ; i++){
            if(Cards[i].getFace().equalsIgnoreCase(""J""))
                a1[i] = 11 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""Q""))
                a1[i] = 12 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""K""))
                a1[i] = 13 ;
            else if(Cards[i].getFace().equalsIgnoreCase(""A""))
                a1[i] = 14 ;
            else
            a1[i] = Integer.parseInt(Cards[i].getFace());            
        }
        if(a1[1]==a1[0]+1){
            if(a1[2] == a1[1]+1){
                if(a1[3]== a1[2]+1){
                    if(a1[4] == a1[3]+1){
                        this.HighCard(Cards[4]) ;
                        return true ;
                    }
                }
            }
        }
        else if(a1[0]==2){
            if(a1[1]==3){
                if(a1[2]==4){
                    if(a1[3]==5){
                        if(a1[4]==14){
                            this.HighCard(Cards[3]) ;
                            return true ;
                        }
                    }
                }
            }
        }
        return false ;
    }
    public boolean isTwoPair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
                this.HighCard(Cards[3]) ;
                return true ;
            }
            else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
       }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
                this.HighCard(Cards[4]) ;
                return true ;
            }
        }
        return false;
    }
    public boolean isOnePair(){
        Card[] Cards = new Card[5] ;
        Cards = this.cards ;
        MergeX.sort(Cards) ;
        if(Cards[0].getFace().equalsIgnoreCase(Cards[1].getFace())){
            this.HighCard(Cards[1]) ;
            return true ;
        }
        else if(Cards[1].getFace().equalsIgnoreCase(Cards[2].getFace())){
            this.HighCard(Cards[2]) ;
            return true ;
        }
        else if(Cards[2].getFace().equalsIgnoreCase(Cards[3].getFace())){
            this.HighCard(Cards[3]) ;
            return true ;
        }
        else if(Cards[3].getFace().equalsIgnoreCase(Cards[4].getFace())){
            this.HighCard(Cards[4]) ;
            return true ;
        }
        
        return false ;
    }
    // TODO 
    public int compareTo(Player that) {
        
.
        
            int[] Points = new int [2] ;
            if(this.isFullHouse())
                Points[0] = 60 ;
            
            else if(this.isFlush())
                Points[0] = 50 ;
            
            else if(this.isStraight())
                Points[0] = 40 ;
            
            else if(this.isTwoPair()){
                Points[0] = 30 ;
            }
            else if(this.isOnePair()){
                Points[0] = 20 ;
            
            }
            else
                Points[0] = 10 ;
            
            
            if(that.isFullHouse())
                Points[1] = 60 ;
            
            else if(that.isFlush())
                Points[1] = 50 ;
            
            else if(that.isStraight())
                Points[1] = 40 ;
            
            else if(that.isTwoPair()){
                Points[1] = 30 ;
            }
            else if(that.isOnePair()){
                Points[1] = 20 ;

    }
            else 
                Points[1] = 10 ;
            
//            System.out.println(Points[0]+"" ""+Points[1] ) ;
            
            if(Points[0]>Points[1])
                return 1 ;
            else if(Points[0]<Points[1])
                return -1 ;
            
            else if(Points[0]==Points[1])
            if(Points[0] == 10){
                MergeX.sort(this.cards);
                MergeX.sort(that.cards);
                this.HighCard(this.cards[4]) ;
                that.HighCard(that.cards[4]) ;
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
                }
            else 
                return(this.HighCard[0].compareTo(that.HighCard[0])) ;
            return 0 ;
    }
   
     public static void main(String[] args) {
     Player[] a = new Player[2] ;
     a[0] = new Player(""John Cena"") ;
     a[1] = new Player(""undertaker"") ;
     Card[] fuck = new Card[5];
     Card[] fuck1 = new Card[5] ;
     fuck[0] = new Card(""10"",""Spades"") ;
     fuck[1] = new Card(""A"",""Hearts"") ;
     fuck[2] = new Card(""9"",""Diamonds"") ;
     fuck[3] = new Card(""7"",""Spades"") ;
     fuck[4] = new Card(""5"",""Clubs"") ;
     a[0].setCards(fuck);
     fuck1[0] = new Card(""4"",""Spades"") ;
     fuck1[1] = new Card(""K"",""Hearts"") ;
     fuck1[2] = new Card(""J"",""Clubs"") ;
     fuck1[3] = new Card(""3"",""Spades"") ;
     fuck1[4] = new Card(""Q"",""Hearts"") ;
     a[1].setCards(fuck1);
//     for (int i = 0 ; i < 5 ; i++){
//     System.out.println(a[0].cards[i].getFace());
//     System.out.println(a[1].cards[i].getFace());
//     }
//     System.out.println(a[0].compareTo(a[1]));
//     System.out.println(a[1].HighCard[0].compareTo(a[0].HighCard[0]));
}
}


"r03525006","0.096","108208","@a9d883d27f789b9993e2f1eee4668251@import java.util.Arrays;

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

    public int[] faceArray() {
        String[] faceArray = {""A"", ""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K""};
        int[] ans = new int[5];
        int face = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++)
                if (cards[i].getFace().equals(faceArray[j])) face = j+1;
            ans[i] = face;
        }
        return ans;
    }

    public int[] faceArrayOrder() {
        String[] faceArray = {""A"", ""K"", ""Q"", ""J"", ""10"", ""9"", ""8"", ""7"", ""6"", ""5"", ""4"", ""3"", ""2""};
        int[] ans = new int[5];
        int face = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++)
                if (cards[i].getFace().equals(faceArray[j])) face = j+1;
            ans[i] = face;
        }
        return ans;
    }

    public int[] suitArray() {
        String[] suitArray = {""Spades"", ""Hearts"", ""Diamonds"", ""Clubs""};
        int[] ans = new int[5];
        int suit = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++)
                if (cards[i].getSuit().equals(suitArray[j])) suit = j+1;
            ans[i] = suit;
        }
        return ans;
    }

    // TODO
    @Override
    public int compareTo(Player that) {
.
        int orderPlayer1 = 6;
        int orderPlayer2 = 6;
        // straight
        if (this.isStraight()) orderPlayer1 = 3;
        if (that.isStraight()) orderPlayer2 = 3;
        // two pair
        if (this.isTwoPair()) orderPlayer1 = 4;
        if (that.isTwoPair()) orderPlayer2 = 4;
        // full house
        if (this.isFullHouse()) orderPlayer1 = 1;
        if (that.isFullHouse()) orderPlayer2 = 1;
        // flush
        if (this.isFlush()) orderPlayer1 = 2;
        if (that.isFlush()) orderPlayer2 = 2;

        // one pair
        if (this.isOnePair()) orderPlayer1 = 5;
        if (that.isOnePair()) orderPlayer2 = 5;
        // compare
        if (orderPlayer1 < orderPlayer2) return 1;
        else if (orderPlayer1 > orderPlayer2) return -1;
        else {
            // full house
            if (orderPlayer1 == 1) {
                Card Player1 = this.getFullHouse();
                Card Player2 = that.getFullHouse();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // flush
            else if (orderPlayer1 == 2) {
                Card Player1 = this.getFlush();
                Card Player2 = that.getFlush();
                if(Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // straight
            else if (orderPlayer1 == 3) {
                Card Player1 = this.getStraight();
                Card Player2 = that.getStraight();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // two pair
            else if (orderPlayer1 == 4) {
                Card Player1 = this.getTwoPair();
                Card Player2 = that.getTwoPair();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // one pair
            else if (orderPlayer1 == 5) {
                Card Player1 = this.getOnePair();
                Card Player2 = that.getOnePair();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // high card
            else if (orderPlayer1 == 6) {
                Card Player1 = this.getHighCard();
                Card Player2 = that.getHighCard();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;

            } else return 0;
        }
    }

    public boolean isFullHouse() {
        int[] a = this.faceArray();
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (a[i] == a[j]) count++;
        return count == 4;
    }

    public boolean isFlush() {
        int[] a = this.suitArray();
        if ((a[0]==a[1]) & (a[1]==a[2]) & (a[2]==a[3]) & (a[3]==a[4])) return true;
        else return false;
    }

    public boolean isStraight() {
        int[] a = this.faceArray();
        int count = 0;
        int faceMin = a[0];
        int faceSum = a[0];
        for (int i = 1; i < 5; i++) {
            faceSum = faceSum + a[i];
            if (a[i] < faceMin) {
                faceMin = a[i];
            }
        }
        if (faceMin == 1) {
            if (faceSum < 16) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 1; j < 6; j++) {
                        if (a[i] == j) count++;
                    }
                }
                return count == 5;
            } else {
                for (int i = 0; i < 5; i++) {
                    for (int j = 10; j < 14; j++) {
                        if (a[i] == j) count++;
                    }
                }
                return count == 4;
            }
        } else {
            for (int i = 0; i < 5; i++) {
                for (int j = faceMin; j < faceMin + 5; j++) {
                    if (a[i] == j) count++;
                }
            }
            return count == 5;
        }
    }

    public boolean isTwoPair() {
        int[] a = this.faceArray();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++)
                if (a[i] == a[j]) count++;
        }
        return count == 2;
    }

    public boolean isOnePair() {
        int[] a = this.faceArray();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++)
                if (a[i] == a[j]) count++;
        }
        return count == 1 || count == 3;
    }

    private Card getFullHouse() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        int face1Count = 0;
        String face1 = cardCopy[4].getFace();
        for (int i = 0; i < 4; i++)
            if (face1.equals(cardCopy[i].getFace())) face1Count++;
        if (face1Count == 1) return cardCopy[2];
        else return cardCopy[4];
    }

    private Card getFlush() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        return cardCopy[4];
    }

    private Card getStraight() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        if (cardCopy[4].getFace().equals(""A"") & cardCopy[0].getFace().equals(""2"")) return cardCopy[3];
        else return cardCopy[0];
    }

    private Card getTwoPair() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        String face1 = cardCopy[4].getFace();
        int face1Count = 0;
        for (int i = 0; i < 4; i++)
            if (face1.equals(cardCopy[i].getFace())) face1Count++;
        if (face1Count == 1) return cardCopy[4];
        else return cardCopy[3];
    }

    private Card getOnePair() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        int pairIndex1 = 0;
        int pairIndex2 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (cardCopy[i].getFace().equals(cardCopy[j].getFace())) {
                    pairIndex1 = i;
                    pairIndex2 = j;
                    break;
                }
            }
        }
        if (cardCopy[pairIndex1].compareTo(cardCopy[pairIndex2]) == 1) return cardCopy[pairIndex1];
        else return cardCopy[pairIndex2];
    }

    private Card getHighCard() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        return cardCopy[4];
    }
}

"r04631013","0.09","103792","@6efca791a91e80290ff1b412eb0178e8@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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

        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        int flower_a[] = new int[2];
        flower_a[0] = 0;
        flower_a[1] = 0;
        for (int k = 0; k < 2; k++) {
            int my[] = new int[5];
            Card[] see = new Card[5];
            switch (k) {
                case 0:
                    see = this.cards;
                    break;
                case 1:
                    see = that.cards;
            }
            for (int i = 0; i < 5; i++) {
                if (""J"".equals(see[i].getFace())) {
                    my[i] = 11;
                } else if (""Q"".equals(see[i].getFace())) {
                    my[i] = 12;
                } else if (""K"".equals(see[i].getFace())) {
                    my[i] = 13;
                } else if (""A"".equals(see[i].getFace())) {
                    my[i] = 14;
                } else {
                    my[i] = Integer.parseInt(see[i].getFace());
                }
// System.out.printf(""%d\n "", my[i]);
            }
            int my_2[] = new int[5];
            my_2[0] = my[4];
            for (int i = 0; i < 4; i++) {
                my_2[i + 1] = my[i];
            }
            int save_0 = 0;
            int save_1 = 0;
// culmulate how many 1 and 0
            for (int i = 0; i < 5; i++) {
                int temp = my_2[i] - my[i];
                if (temp == 0) {
                    save_0 = save_0 + 1;
                } else if (temp == -1) {
                    save_1 = save_1 + 1;
                }
            }
//            System.out.printf(""%d\n "", save_0);
//            System.out.printf(""%d\n "", save_1);
            
            
            if (save_0 == 3) {
                flower_a[k] = 6;
            } 
            if (see[0].getSuit().equals(see[1].getSuit())) {
                if (see[1].getSuit().equals(see[2].getSuit())) {
                    if (see[2].getSuit().equals(see[3].getSuit())) {
                        if (see[3].getSuit().equals(see[4].getSuit())) {
                            flower_a[k] = 5;

                        }
                    }
                }
            } 
            if (save_1 == 4) {
                if (flower_a[k] != 5) {
                    flower_a[k] = 4;
                }
            } else if (save_1 == 3) {
                if (""A"".equals(see[4].getFace())) {
                    flower_a[k] = 4;
                }
            } else if (save_0 == 2) {
                if (see[0].getFace().equals(see[1].getFace()) && see[1].getFace().equals(see[2].getFace())) {
                    flower_a[k] = 2;
                } else if (see[1].getFace().equals(see[2].getFace()) && see[2].getFace().equals(see[3].getFace())) {
                    flower_a[k] = 2;
                } else if (see[2].getFace().equals(see[3].getFace()) && see[3].getFace().equals(see[4].getFace())) {
                    flower_a[k] = 2;
                } else {
                    flower_a[k] = 3;
                }
            } else if (save_0 == 1) {
                flower_a[k] = 2;
            } else {
                flower_a[k] = 1;
            }
//            System.out.printf(""%d\n "", flower_a[k]);

        }

        int roo = 0;
        if (flower_a[0] > flower_a[1]) {
            return 1;
        } else if (flower_a[0] < flower_a[1]) {
            return -1;
        } else {
            if (flower_a[0] == 6) {
                int a;
                int b;
                if (this.cards[0].getFace().equals(this.cards[1].getFace()) && this.cards[1].getFace().equals(this.cards[2].getFace())) {
                    a = 2;
                } else {
                    a = 4;
                }
                if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[1].getFace().equals(that.cards[2].getFace())) {
                    b = 2;
                } else {
                    b = 4;
                }

                roo = this.cards[a].compareTo(that.cards[b]);
// return roo;
            }
// //////////////////////
            if (flower_a[0] == 5) {
                if (flower_a[1] == 5) {
                    roo = this.cards[4].compareTo(that.cards[4]);
                }
            }
//////////////////////////////
            if (flower_a[0] == 4) {
                roo = this.cards[4].compareTo(that.cards[4]);
// return roo;
            }
///////////////////////////////////////////////////
            if (flower_a[0] == 3) {
                roo = this.cards[3].compareTo(that.cards[3]);
// return roo;
            }
////////////////////////////////////////////////
            if (flower_a[0] == 1) {
                roo = this.cards[4].compareTo(that.cards[4]);
// return roo;
            }
/////////////////////////////////////
            int my_num = 0;
            int your_num = 0;
            if (flower_a[0] == 2) {
                for (int i = 0; i < 4; i++) {
                    if (this.cards[i].getFace().equals(this.cards[i + 1].getFace())) {
                        int j;
                        j = Card.SUIT_ORDER.compare(this.cards[i], this.cards[i + 1]);
                        if (j == 1) {
                            my_num = i;
                        } else {
                            my_num = i + 1;
                        }
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (that.cards[i].getFace().equals(that.cards[i++].getFace())) {
                        int j = Card.SUIT_ORDER.compare(that.cards[i], that.cards[i + 1]);
                        if (j == 1) {
                            your_num = i;
                        } else {
                            your_num = i + 1;
                        }

                    }
                }
                roo = Card.SUIT_ORDER.compare(this.cards[my_num], that.cards[your_num]);
// return roo;
            }
            return roo;
        }

    }
}


"r04631009","0.094","105664","@66f072cdab02b539e2c27442d59a71b8@public class Player implements Comparable<Player> {

    private Card[] cards = new Card[5];
    //give players 5 cards
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
            default:
                num1 = Integer.parseInt(face) - 1;
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
                        side = 4;
                    }
                }
            }
        }
        return a;
    }

    private int isstraight(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) - facetointeger(cards[1].getFace()) == -1) {
            if (facetointeger(cards[1].getFace()) - facetointeger(cards[2].getFace()) == -1) {
                if (facetointeger(cards[2].getFace()) - facetointeger(cards[3].getFace()) == -1) {
                    if (facetointeger(cards[3].getFace()) - facetointeger(cards[4].getFace()) == -1) {
                        a = 1;
                    }
                }
            }
        }
        return a;
    }

    private int pairsnumber2(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) == facetointeger(cards[1].getFace())) {
            if (facetointeger(cards[2].getFace()) == facetointeger(cards[3].getFace())) {
                a = 1;
                side = 3;
            }
            if (facetointeger(cards[3].getFace()) == facetointeger(cards[4].getFace())) {
                a = 1;
                side = 4;
            }
        }
        if (facetointeger(cards[2].getFace()) == facetointeger(cards[1].getFace()) && facetointeger(cards[4].getFace()) == facetointeger(cards[3].getFace())) {
            a = 1;
            side = 4;

        }

        return a;
    }

    private int pairsnumber1(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) == facetointeger(cards[1].getFace())) {
            a = 1;
            side = 1;
            if (facetointeger(cards[0].getFace()) == facetointeger(cards[2].getFace())) {
                side = 2;
            }
        } else if (facetointeger(cards[1].getFace()) == facetointeger(cards[2].getFace())) {
            a = 1;
            side = 2;
            if (facetointeger(cards[1].getFace()) == facetointeger(cards[3].getFace())) {
                side = 3;
            }
        } else if (facetointeger(cards[2].getFace()) == facetointeger(cards[3].getFace())) {
            a = 1;
            side = 3;
            if (facetointeger(cards[2].getFace()) == facetointeger(cards[4].getFace())) {
                side = 4;
            }
        } else if (facetointeger(cards[3].getFace()) == facetointeger(cards[4].getFace())) {
            a = 1;
            side = 4;
        }

        return a;
    }

    // TODO 
    public int compareTo(Player that) {
        int rank1 = 0;  //    full house(6) > flush(5) > straight(4) > two pair(3) > one pair(2) > high card(1)
        int rank2 = 0;
        int side1 = 0;
        int side2 = 0;

        Insertion.sort(that.cards);
        Insertion.sort(this.cards);

        if (isfullhouse(this.cards) == 1) {
            rank1 = 6;
            side1 = side;
        } else if (isflush(this.cards) == 1) {
            rank1 = 5;
        } else if (isstraight(this.cards) == 1) {
            rank1 = 4;
        } else if (pairsnumber2(this.cards) == 1) {
            rank1 = 3;
            side1 = side;
        } else if (pairsnumber1(this.cards) == 1) {
            rank1 = 2;
            side1 = side;
        } else {
            rank1 = 1;
        }

        if (isfullhouse(that.cards) == 1) {
            rank2 = 6;
            side2 = side;
        } else if (isflush(that.cards) == 1) {
            rank2 = 5;
        } else if (isstraight(that.cards) == 1) {
            rank2 = 4;
        } else if (pairsnumber2(that.cards) == 1) {
            rank2 = 3;
            side2 = side;
        } else if (pairsnumber1(that.cards) == 1) {
            rank2 = 2;
            side2 = side;
        } else {
            rank2 = 1;
        }
        if (rank1 > rank2) {
            return 1;
        }
        if (rank1 < rank2) {
            return -1;
        }
        if (rank1 == rank2) {
            if (rank1 == 6) {
                if (this.cards[side1].compareTo(that.cards[side2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            }
            if (rank1 == 5) {
                if (this.cards[4].compareTo(that.cards[4]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            }
            if (rank1 == 4) {
                if (this.cards[4].compareTo(that.cards[4]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            }
            if (rank1 == 3) {
                if (this.cards[side1].compareTo(that.cards[side2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            }
            if (rank1 == 2) {
                if (this.cards[side1].compareTo(that.cards[side2]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            }
            if (rank1 == 1) {
                if (this.cards[4].compareTo(that.cards[4]) == 1) {
                    return 1;
                } else {
                    return -1;
                }
            }

        }
.
        return 0;
    }
}

"r04522627","0.094","107504","@0f1737999b36efe57fc14d594734ca92@
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
    
    boolean tryParseInt(String value) {  
     try {  
         Integer.parseInt(value);  
         return true;  
      } catch (NumberFormatException e) {  
         return false;  
      }  
    }
    
    public int getFaceValue(String face){
        int v = 0;
        if(tryParseInt(face))
        {
            v = Integer.parseInt(face);
        }
        else if(face.equals(""A""))
        {
            v = 14;
        }
        else if(face.equals(""K""))
        {
            v = 13;
        }
        else if(face.equals(""Q""))
        {
            v = 12;
        }
        else if(face.equals(""J""))
        {
            v = 11;
        }
        return v;
    }
    
    public int getSuitValue(String suit){
        int v1 = 0;  
        if(suit.equals(""Spades""))
            {
                v1 = 4;
            }
            else if(suit.equals(""Hearts""))
            {
                v1 = 3;
            }
            else if(suit.equals(""Diamonds""))
            {
                v1 = 2;
            }
            else if(suit.equals(""Clubs""))
            {
                v1 = 1;
            }
        return v1;
    }
    
    public double getPlayerValue(Player p)
    {
        double c1 = 0;
        int i = 0,j =0;
        int[] PairIndex = new int[2];
        while(i < 4)
        {
            if(p.cards[i].getFace().equals(p.cards[++i].getFace()))
            {
                c1 = c1 + 2;
                PairIndex[j++] = i; 
                if(i == 4) break;
                if(p.cards[i].getFace().equals(p.cards[++i].getFace()))
                {
                    c1 = c1 + 1 + getFaceValue(p.cards[i].getFace()) * 0.01;
                    if(i == 4) break;
                     if(p.cards[i].getFace().equals(p.cards[++i].getFace()))
                    {
                        c1 = c1 + 1 + getFaceValue(p.cards[i].getFace()) * 0.0001;
                    }
                }
            }
        }
        //flush
        if(c1 < 5)
        {
            i = 0; 
            int k = 0;
            while(i < 4)
            {
                if(p.cards[i].getSuit().equals(p.cards[++i].getSuit()))
                {
                    k++;
                }
            }
            if(k == 4)
            {
                c1 = 4.8  + getFaceValue(p.cards[4].getFace()) * 0.001 + getSuitValue(p.cards[4].getSuit()) * 0.0001;
            }
        }
        //straight  4.5x  4.1x
        if(c1 < 4.8)
        {
            i = 0; 
            int k = 0;
            while(i < 4)
            {
                if(getFaceValue(p.cards[i].getFace()) - getFaceValue(p.cards[++i].getFace()) == -1) k++;
            }
            if(k == 4)
            {
                c1 = 4.5 + getFaceValue(p.cards[4].getFace()) * 0.001 + getSuitValue(p.cards[4].getSuit()) * 0.0001;
            }
            else if(k == 3 && getFaceValue(p.cards[0].getFace()) == 2 && getFaceValue(p.cards[4].getFace()) == 14)
            {
                c1 = 4.1 + getFaceValue(p.cards[3].getFace()) * 0.001 + getSuitValue(p.cards[3].getSuit()) * 0.0001;
            }
        }
        
        if(c1 == 4)
        {
            c1 =  c1 + getFaceValue(p.cards[PairIndex[1]].getFace()) * 0.01 + getSuitValue(p.cards[PairIndex[0]].getSuit()) * 0.0001;
        }
        else if(c1 == 2)
        {
             c1 = c1 +  getFaceValue(p.cards[PairIndex[0]].getFace()) * 0.01;
        }
                
        else if(c1 == 0)
        {
            c1 = getFaceValue(p.cards[4].getFace()) * 0.01 + getSuitValue(p.cards[4].getSuit()) * 0.001;
        }
        
        return c1;
    }
    
    // TODO 
    public int compareTo(Player that) {
.
        double c1 = 0, c2 = 0;
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        
        c1 = getPlayerValue(this);
        c2 = getPlayerValue(that);
        
        
        if(c1 > c2)
        {
            return 1;
        }
        else if(c1 == c2)
        {
            return 0;
        }
        else
        {
            return -1;
        }
        
    }
}


"r04631013","0.08","105264","@4d4d462b901f66c81e45e1929b4433b8@ import java.util.Arrays;

public class Player implements Comparable<Player> {

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

Arrays.sort(this.cards);
Arrays.sort(that.cards);
int flower_a[] = new int[2];
flower_a[0] = 0;
flower_a[1] = 0;
for (int k = 0; k < 2; k++) {
int my[] = new int[5];
Card[] see = new Card[5];
switch (k) {
case 0:
see = this.cards;
break;
case 1:
see = that.cards;
}
for (int i = 0; i < 5; i++) {
if (""J"".equals(see[i].getFace())) {
my[i] = 11;
} else if (""Q"".equals(see[i].getFace())) {
my[i] = 12;
} else if (""K"".equals(see[i].getFace())) {
my[i] = 13;
} else if (""A"".equals(see[i].getFace())) {
my[i] = 14;
} else {
my[i] = Integer.parseInt(see[i].getFace());
}
// System.out.printf(""%d\n "", my[i]);
}
int my_2[] = new int[5];
my_2[0] = my[4];
for (int i = 0; i < 4; i++) {
my_2[i + 1] = my[i];
}
int save_0 = 0;
int save_1 = 0;
// culmulate how many 1 and 0
for (int i = 0; i < 5; i++) {
int temp = my_2[i] - my[i];
if (temp == 0) {
save_0 = save_0 + 1;
} else if (temp == -1) {
save_1 = save_1 + 1;
}
}
// System.out.printf(""%d\n "", save_0);
// System.out.printf(""%d\n "", save_1);


if (save_0 == 3) {
flower_a[k] = 6;
} 
if (see[0].getSuit().equals(see[1].getSuit())) {
if (see[1].getSuit().equals(see[2].getSuit())) {
if (see[2].getSuit().equals(see[3].getSuit())) {
if (see[3].getSuit().equals(see[4].getSuit())) {
flower_a[k] = 5;

}
}
}
} 
if (save_1 == 4) {
if (flower_a[k] != 5) {
flower_a[k] = 4;
}
} else if (save_1 == 3) {
if (""A"".equals(see[4].getFace())) {
flower_a[k] = 4;
}
} else if (save_0 == 2) {
if (see[0].getFace().equals(see[1].getFace()) && see[1].getFace().equals(see[2].getFace())) {
flower_a[k] = 2;
} else if (see[1].getFace().equals(see[2].getFace()) && see[2].getFace().equals(see[3].getFace())) {
flower_a[k] = 2;
} else if (see[2].getFace().equals(see[3].getFace()) && see[3].getFace().equals(see[4].getFace())) {
flower_a[k] = 2;
} else {
flower_a[k] = 3;
}
} else if (save_0 == 1) {
flower_a[k] = 2;
} else {
flower_a[k] = 1;
}
// System.out.printf(""%d\n "", flower_a[k]);

}

int roo = 0;
if (flower_a[0] > flower_a[1]) {
return 1;
} else if (flower_a[0] < flower_a[1]) {
return -1;
} else {
if (flower_a[0] == 6) {
int a;
int b;
if (this.cards[0].getFace().equals(this.cards[1].getFace()) && this.cards[1].getFace().equals(this.cards[2].getFace())) {
a = 2;
} else {
a = 4;
}
if (that.cards[0].getFace().equals(that.cards[1].getFace()) && that.cards[1].getFace().equals(that.cards[2].getFace())) {
b = 2;
} else {
b = 4;
}

roo = this.cards[a].compareTo(that.cards[b]);
// return roo;
}
// //////////////////////
if (flower_a[0] == 5) {
if (flower_a[1] == 5) {
roo = this.cards[4].compareTo(that.cards[4]);
}
}
//////////////////////////////
if (flower_a[0] == 4) {
roo = this.cards[4].compareTo(that.cards[4]);
// return roo;
}
///////////////////////////////////////////////////
if (flower_a[0] == 3) {
roo = this.cards[3].compareTo(that.cards[3]);
// return roo;
}
////////////////////////////////////////////////
if (flower_a[0] == 1) {
roo = this.cards[4].compareTo(that.cards[4]);
// return roo;
}
/////////////////////////////////////
int my_num = 0;
int your_num = 0;
if (flower_a[0] == 2) {
for (int i = 0; i < 4; i++) {
if (this.cards[i].getFace().equals(this.cards[i + 1].getFace())) {
int j;
j = Card.SUIT_ORDER.compare(this.cards[i], this.cards[i + 1]);
if (j == 1) {
my_num = i;
} else {
my_num = i + 1;
}
}
}
for (int i = 0; i < 4; i++) {
if (that.cards[i].getFace().equals(that.cards[i++].getFace())) {
int j = Card.SUIT_ORDER.compare(that.cards[i], that.cards[i + 1]);
if (j == 1) {
your_num = i;
} else {
your_num = i + 1;
}

}
}
roo = Card.SUIT_ORDER.compare(this.cards[my_num], that.cards[your_num]);
// return roo;
}
return roo;
}

}
}
"r04525016","0.09","104624","@119accf5755c67d18661021cece47deb@public class Player implements Comparable<Player> {

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
        int player1 = this.cardType(this.cards);
        int player2 = that.cardType(that.cards);

        if (player1 > player2) return 1;
        else if (player1 < player2) return -1;
        else if (player1 == player2) {

            int SPlayer1 = this.secondRound(player1, this.cards);
            int SPlayer2 = that.secondRound(player2, that.cards);

            if (SPlayer1 > SPlayer2) return 1;
            else if (SPlayer1 < SPlayer2) return -1;
            else if (SPlayer1 == SPlayer2) {
//
//                int TPlayer1 = this.thirdRound(player1, this.cards);
//                int TPlayer2 = that.thirdRound(player2, that.cards);
//
//                if (TPlayer1 > TPlayer2) return 1;
//                else if (TPlayer1 < TPlayer2) return -1;
//                else if(TPlayer1 == TPlayer2)  return 0;
            }
        }
        return 0;
    }


    public int secondRound(int s, Card[] c) {
        int[] allFace = getAllFace(c);
        int secondValue = -1;
        if (s == 5) {
            for (int i = 0; i < 13; i++) {
                if (allFace[i] == 3) {
                    return i;
                }
            }
        }
//        else if (s == 4) {
//            String temp = c[0].getSuit();
//            secondValue = suitIndex(temp);
//            return secondValue;
//        }
        else if (s == 0 | s == 3 | s == 4) {
            for (int i = 0; i < 13; i++) {
                if (allFace[i] == 1) {
                    secondValue = i;
                }
            }
            return secondValue;
        } else if (s == 3 | s == 2) {
            for (int i = 0; i < 13; i++) {
                if (allFace[i] == 2) {
                    secondValue = i;
                }
            }
            return secondValue;
        }
        return secondValue;
    }


//    public int thirdRound(int t, Card[] c) {
//
//
//
//
//    }

    public int[] getAllFace(Card[] c) {
        int[] allFace = new int[13];

        for (int i = 0; i < allFace.length; i++) {
            allFace[i] = 0;
        }

        for (int i = 0; i < c.length; i++) {
            String temp = c[i].getFace();
            allFace[faceIndex(temp)]++;
        }

        return allFace;
    }


    public int cardType(Card[] c) {

//        int[] allFace = new int[13];
//
//        for (int i = 0; i < allFace.length; i++) {
//            allFace[i] = 0;
//        }
//
//        for (int i = 0; i < c.length; i++) {
//            String temp = c[i].getFace();
//            allFace[faceIndex(temp)]++;
//        }


        int[] allFace = getAllFace(c);

        int[] allType = new int[4];
        for (int i = 0; i < allFace.length; i++) {
            if (allFace[i] == 3) allType[3]++;
            else if (allFace[i] == 2) allType[2]++;
            else if (allFace[i] == 1) allType[1]++;
        }

        int type = -1;
        if (allType[3] == 1) {
            if (allType[2] == 1) {
                type = 5;
            } else if (allType[1] == 2) {
                type = 1;
            }
        } else if (allType[2] == 2) {
            type = 2;
        } else if (allType[2] == 1) {
            type = 1;
        } else if (allType[1] == 5) {
            if (isFlush(cards)) {
                type = 4;
            } else if (isStraight(allFace)) {
                type = 3;
            } else {
                type = 0;
            }
        }

//
//        for (int i = 0; i < allFace.length; i++) {
//            System.out.print(allFace[i] + "" "");
//        }
//
//        System.out.print(""["");
//        String sp = """";
//        for (int i : allType) {
//            System.out.print(sp + i);
//            sp = "","";
//        }
//        System.out.println(""]"");

        return type;
    }

    private boolean isStraight(int[] allFace) {

        int counter = 0;
        for (int i = 0; i < allFace.length; i++) {
            if (allFace[i] == 1) {
                if (i == 0 && allFace[12] == 1) {
                    int[] temp = {9, 10, 11, 12, 0, 1, 2, 3};
                    int counter2 = 0;
                    for (int k = 1; k < temp.length; k++) {
                        if (allFace[k] - allFace[k - 1] == 0) {
                            counter2++;
                        }
                        if (counter2 == 4) {
                            return true;
                        }
                    }
                }

//                    while (!temp.equals(allFace[counter2])) {
//                        if (allFace[temp[counter2]] == 1) {
//                            counter2++;
//                        }
//                        if (counter2 == 5) {
//                            return true;
//                        }
//                    }
//                }


                for (int j = i + 1; j < i + 5; j++) {
                    if (allFace[j] - allFace[j - 1] == 0) {
                        counter++;
                    }
                    if (counter == 4) {
                        return true;
                    }
                }
            }
            return false;
        }


        if (counter == 4) return true;

        else return false;
    }

    public boolean isFlush(Card[] c) {
        int[] allSuit = new int[4];
        for (int i = 0; i < c.length; i++) {
            String temp = c[i].getSuit();
            allSuit[suitIndex(temp)]++;
        }
        for (int j = 0; j < 4; j++) {
            if (allSuit[j] == 5) return true;
        }
        return false;
    }

    public int faceIndex(String f) {
        String[] faces = {""A"", ""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K""};
        int face_index = 0;
        while (!f.equals(faces[face_index])) face_index++;
        return face_index;
    }

    public int suitIndex(String s) {
        String[] suits = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};
        int suit_index = 0;
        while (!s.equals(suits[suit_index])) suit_index++;
        return suit_index;
    }

}
"r04921074","0.098","109392","@d4f14ffe8dea6a81f607223eadd87696@
import java.lang.reflect.Array;
import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */

/**
 *
 * @author USER
 */
public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
     
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }
    
        public int FacetoINT(String a){
        int facepoint = 0;
        if(a.equals(""A""))facepoint = 130;
        else if(a.equals(""2""))facepoint = 10;
        else if(a.equals(""3""))facepoint = 20;
        else if(a.equals(""4""))facepoint = 30;
        else if(a.equals(""5""))facepoint = 40;
        else if(a.equals(""6""))facepoint = 50;
        else if(a.equals(""7""))facepoint = 60;
        else if(a.equals(""8""))facepoint = 70;
        else if(a.equals(""9""))facepoint = 80;
        else if(a.equals(""10""))facepoint = 90;
        else if(a.equals(""J""))facepoint = 100;
        else if(a.equals(""Q""))facepoint = 110;
        else if(a.equals(""K""))facepoint = 120;
        return facepoint;
    } 
        public int SuittoINT(String b){
        int suitpoint = 0;
        if(b.equals(""Clubs""))suitpoint=1;
        else if(b.equals(""Diamonds""))suitpoint=2;
        else if(b.equals(""Hearts""))suitpoint=3;
        else if(b.equals(""Spades""))suitpoint=4;
        return suitpoint;
    } 
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
    public int GetPoint(Card aa) {
        int GP = 0;
        GP = FacetoINT(aa.getFace())+SuittoINT(aa.getSuit());
        return GP;
    } 
    // TODO 
    public int compareTo(Player that) {
        
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        
        int thisSET = GetOrder(this.cards);
        int thatSET = GetOrder(that.cards);
        
        if(thisSET>thatSET)return +1;
        else if(thatSET>thisSET)return -1;
        else if(thisSET == thatSET){
                if(thisSET==0){ 
                    if(GetPoint(this.cards[4])>GetPoint(that.cards[4]))return+1;
                    else return-1;
                }
                else if(thisSET==1){
                    if(OnePair(this.cards)[1]>OnePair(that.cards)[1])return+1;
                    else return-1;
                }
                else if(thisSET==2){
                    if(TwoPair(this.cards)[1]>TwoPair(that.cards)[1])return+1;
                    else return-1;
                }
                else if(thisSET==3){
                    if(Straight(this.cards)[1]>Straight(that.cards)[1])return+1;
                    else return-1;
                }
                else if(thisSET==4){
                    if(Flush(this.cards)[1]>Flush(that.cards)[1])return+1;
                    else return-1;
                }
                else if(thisSET==5){
                    if(FullHouse(this.cards)[1]>FullHouse(that.cards)[1])return+1;
                    else return-1;
                }
                else return 0;
               
        }
.
        else  return 0;
    }
    public int[] Straight(Card[] aa) {
        Arrays.sort(aa);
        int[] GP = new int[2];
        for(int i=0; i<4; i++){
           if((GetPoint(aa[i+1])/10)-(GetPoint(aa[i])/10)==1)GP[0] = GP[0]+1;
        } 
        GP[1] = GetPoint(aa[4]);//save max card
        GP[0] = GP[0]/4;//save whether set is establish?
        if((GetPoint(aa[0])/10)==1&&(GetPoint(aa[1])/10)==2&&(GetPoint(aa[2])/10)==3&&(GetPoint(aa[3])/10)==4&&(GetPoint(aa[4])/10)==13){
            GP[0]=1; GP[1]=GetPoint(aa[3]);
        }
        return GP;
    }
    public int GetOrder(Card[] O){
        Arrays.sort(O);
        int Order = 0;
        if(FullHouse(O)[0]==1) Order = 5;
        else{
            if(Flush(O)[0]==1) Order = 4;
            else{
                if(Straight(O)[0]==1)Order = 3;
                else{
                    if(TwoPair(O)[0]==1)Order = 2;
                    else{
                        if(OnePair(O)[0]==1)Order = 1;
                        else Order = 0;
                    }
                }
            }//2-else
        }//1-else
        return Order;
    }
    public int[] FullHouse(Card[] bb) {
        Arrays.sort(bb);
        int[] GP = new int[2];
        int[] temp = new int[5];
        int[] tempC = new int[5];
        for(int i=0; i<5; i++){
            temp[i]=GetPoint(bb[i])/10;
            tempC[i]=GetPoint(bb[i]);
        }
        if(temp[0]==temp[1]&&temp[2]==temp[3]&&temp[3]==temp[4]){
            GP[0]=1;
            GP[1]=tempC[4];
        }
        else if(temp[0]==temp[1]&&temp[1]==temp[2]&&temp[3]==temp[4]){
            GP[0]=1;
            GP[1]=tempC[2];
        }
        else{GP[0]=0; GP[1]=0;}
        return GP;
    }
    public int[] Flush(Card[] cc) {
        Arrays.sort(cc);
         int[] GP = new int[2];
        for(int i=0; i<4; i++){
           if((GetPoint(cc[0])%10)==(GetPoint(cc[i+1])%10))GP[0] = GP[0]+1;
        } 
        GP[1] = GetPoint(cc[4]);//save max card
        GP[0] = GP[0]/4;//save whether set is establish?
        return GP;
    }
    public int[] TwoPair(Card[] dd) {
        Arrays.sort(dd);
        int[] GP = new int[2];
        int count = 0;
        int temp = 0;
        for(int i=0; i<4; i++){
            for(int j=i+1; j<5; j++){
                if((GetPoint(dd[i])/10)==(GetPoint(dd[j])/10)){
                    temp=j;
                    count++;
                }
            }            
        }
        if(count==2){
            GP[0]=1;GP[1]=GetPoint(dd[temp]);}
        else if(count==3){
            GP[0]=1;GP[1]=GetPoint(dd[temp]);}
        else if(count==6){
            GP[0]=1;GP[1]=GetPoint(dd[temp]);}
        else{ GP[0]=0;GP[1]=0;
        }
        return GP;
    }
    
    
    public int[] OnePair(Card[] ee) {
        Arrays.sort(ee);
        int[] GP = new int[2];
        int temp = 0;
        int count = 0;
        for(int i=0; i<4; i++){
            for(int j=i+1; j<5; j++){
                if((GetPoint(ee[i])/10)==(GetPoint(ee[j])/10)){
                    temp=j;
                    count++;
                }
            }            
        }
        if(count==1){
            GP[0]=1; GP[1]=GetPoint(ee[temp]);}
        else{GP[0]=0; GP[1]=0;            
        }
        return GP;
    }
    
    
}


"r03522831","0.092","105664","@7515f59fbe1d03af34045638b14092b2@public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
    private int Judge, index, special;
    
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
//        Maxnum = 0;
        special = 0;
        index = 0;
    }
    
    public Integer getCase(){
        return Judge;
    }
    
    public Integer getIndex(){
        return index;
    }
    
    public String getCard(int index){
        return cards[index].getFace();
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
     public void Pairs()
     {
//         int pairnum = 0;
         int pairsize = 0;
         int ind1 = 0;
         for(int i = 0; i < 5; i++){
//             while(cards[i].getNumber() != pairnum){
                for(int j = i+1; j < 5; j++)
                {
                    if(cards[i].getFace().equals(cards[j].getFace()))
                    {
                        ind1 = i;
                        if(pairsize < 4){
                            pairsize += 1;
//                            paircounts += 1;
                        }
                        if(cards[i].compareTo(cards[ind1]) > 0){
//                            Maxpair = cards[i].getNumber();
                            index = i;
                        }
                        
                    }
                }
//             }
         }
         if((pairsize == 1)||(pairsize == 3))
             Judge = 2;
         if((pairsize == 2))
             Judge = 3;
         if(pairsize == 4)
             Judge = 6;
     }
     
     public void Straight(){
         int[] Number = new int[5];
         for (int i = 0; i < 5; i++){ 
             if(cards[i].getFace().equals(""A""))
                 Number[i] = 1;
             else if(cards[i].getFace().equals(""K""))
                 Number[i] = 13;
             else if(cards[i].getFace().equals(""Q""))
                 Number[i] = 12;
             else if(cards[i].getFace().equals(""J""))
                 Number[i] = 11;
             else
                 Number[i] = Integer.parseInt(cards[i].getFace());
         }
         
         if(((Number[4] - Number[0]) == 4)&&(Judge < 5)){
             if((Number[3] - Number[1])==2)
                 if((Number[2]-Number[1])==1)
                    Judge = 4;
                    index = 4;
         }
         if((Number[4] == 1)&&(Judge < 5))
         {
             if(((Number[3]-Number[0]) == 3)&&(Number[3] == 5)){
                 if((Number[2]-Number[1]) == 1)
                    Judge = 4;
                    special  = 1;
                    index = 3;
             }
             if(((Number[3]-Number[0]) == 3)&&(Number[3] == 13)){
                 if((Number[2]-Number[1]) == 1)
                    Judge = 4;
             }
         }
     }
     
     public void Flush(){
         int count = 1;
         for(int i = 0; i < 4; i++)
             if(cards[i].getSuit().equals(cards[i+1].getSuit()))
                 count += 1;
         if ((count == 5)&&(Judge < 6))
             Judge = 5;
     }
     
     
    // TODO 
    public int compareTo(Player that) {
.
        Insertion.sort(this.cards);
        Insertion.sort(that.cards);
        this.Judge = 1;
        that.Judge = 1;
//        this.Maxnum = this.cards[4].getNumber();
//        that.Maxnum = that.cards[4].getNumber();
        this.index = 4;
        that.index = 4;
        this.Pairs();
        this.Flush();
        this.Straight();
        that.Pairs();
        that.Flush();
        that.Straight();
        if (this.Judge > that.Judge)
            return 1;
        else if (this.Judge < that.Judge)
            return -1;
        else
            return (this.cards[this.index].compareTo(that.cards[that.index]));
        
    }
}

"r03522809","0.092","105664","@705e9230f9ade40c14945ec6dde41506@import java.util.Arrays;


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
        Insertion.sort(this.cards);
        Insertion.sort(that.cards);
        int thiscardvalue,thatcardvalue,value=0;
        int thisvalue[]=new int[5];
        int thatvalue[]=new int[5];

//        System.out.println(this.cards[0].getSuit());
        
        for(int i=0;i<5;i++){
                if(this.cards[i].getFace().equals(""A"")){thisvalue[i] = 14;} //get that card value
                else if(this.cards[i].getFace().equals(""J"")){thisvalue[i]= 11;}
                else if(this.cards[i].getFace().equals(""Q"")){thisvalue[i]= 12;}          
                else if(this.cards[i].getFace().equals(""K"")){thisvalue[i]= 13;}
                else{thisvalue[i]=Integer.parseInt(this.cards[i].getFace());}
                
                if(that.cards[i].getFace().equals(""A"")){thatvalue[i] = 14;} //get that card value
                else if(that.cards[i].getFace().equals(""J"")){thatvalue[i]= 11;}
                else if(that.cards[i].getFace().equals(""Q"")){thatvalue[i]= 12;}          
                else if(that.cards[i].getFace().equals(""K"")){thatvalue[i]= 13;}
                else{thatvalue[i]=Integer.parseInt(that.cards[i].getFace());}
            }
            if( (thisvalue[4]==thisvalue[3] && thisvalue[4]==thisvalue[2] && thisvalue[1]==thisvalue[0]) ||
               ( thisvalue[0]==thisvalue[1] && thisvalue[0]==thisvalue[2] && thisvalue[3]==thisvalue[4] ) ){thiscardvalue=6;}
            else if( this.cards[0].getSuit().equals(this.cards[1].getSuit()) && 
                     this.cards[0].getSuit().equals(this.cards[2].getSuit()) && 
                     this.cards[0].getSuit().equals(this.cards[3].getSuit()) && 
                     this.cards[0].getSuit().equals(this.cards[4].getSuit())){thiscardvalue=5;}
            else if( thisvalue[0]==2 &&
                     thisvalue[1]==3 &&
                     thisvalue[2]==4 &&
                     thisvalue[3]==5 &&
                     thisvalue[4]==14){thisvalue[0]=1;thisvalue[1]=2;thisvalue[2]=3;thisvalue[3]=4;thisvalue[4]=5;thiscardvalue=4;}
            else if( thisvalue[0]==thisvalue[1]-1 &&
                     thisvalue[0]==thisvalue[2]-2 &&
                     thisvalue[0]==thisvalue[3]-3 &&
                     thisvalue[0]==thisvalue[4]-4 ){thiscardvalue=4;}
            else if( thisvalue[0]==thisvalue[1]  &&  thisvalue[2]==thisvalue[3] ||
                     thisvalue[0]==thisvalue[1]  &&  thisvalue[3]==thisvalue[4] ||
                     thisvalue[1]==thisvalue[2]  &&  thisvalue[3]==thisvalue[4]){thiscardvalue=3;}
            else if( thisvalue[0]==thisvalue[1] || 
                     thisvalue[1]==thisvalue[2] || 
                     thisvalue[2]==thisvalue[3] || 
                     thisvalue[3]==thisvalue[4]){thiscardvalue=2;}
            else{thiscardvalue=1;}
            
            if( (thatvalue[4]==thatvalue[3] && thatvalue[4]==thatvalue[2] && thatvalue[1]==thatvalue[0]) ||
               ( thatvalue[0]==thatvalue[1] && thatvalue[0]==thatvalue[2] && thatvalue[3]==thatvalue[4] ) ){thatcardvalue=6;}
            else if( that.cards[0].getSuit().equals(that.cards[1].getSuit()) && 
                     that.cards[0].getSuit().equals(that.cards[2].getSuit()) && 
                     that.cards[0].getSuit().equals(that.cards[3].getSuit()) && 
                     that.cards[0].getSuit().equals(that.cards[4].getSuit())){thatcardvalue=5;}
            else if( thatvalue[0]==2 &&
                     thatvalue[1]==3 &&
                     thatvalue[2]==4 &&
                     thatvalue[3]==5 &&
                     thatvalue[4]==14){thatvalue[0]=1;thatvalue[1]=2;thatvalue[2]=3;thatvalue[3]=4;thatvalue[4]=5;thatcardvalue=4;}
            else if( thatvalue[0]==thatvalue[1]-1 &&
                     thatvalue[0]==thatvalue[2]-2 &&
                     thatvalue[0]==thatvalue[3]-3 &&
                     thatvalue[0]==thatvalue[4]-4 ){thatcardvalue=4;}
            else if( thatvalue[0]==thatvalue[1]  &&  thatvalue[2]==thatvalue[3] ||
                     thatvalue[0]==thatvalue[1]  &&  thatvalue[3]==thatvalue[4] ||
                     thatvalue[1]==thatvalue[2]  &&  thatvalue[3]==thatvalue[4]){thatcardvalue=3;}
            else if( thatvalue[0]==thatvalue[1] || 
                     thatvalue[1]==thatvalue[2] || 
                     thatvalue[2]==thatvalue[3] || 
                     thatvalue[3]==thatvalue[4]){thatcardvalue=2;}
            else{thatcardvalue=1;}
            
//            if(thiscardvalue==6){System.out.println(thisvalue[0]+""\t""+thisvalue[1]+""\t""+thisvalue[2]+""\t""+thisvalue[3]+""\t""+thisvalue[4]);}
//            if(thatcardvalue==6){System.out.println(thatvalue[0]+""\t""+thatvalue[1]+""\t""+thatvalue[2]+""\t""+thatvalue[3]+""\t""+thatvalue[4]);}        
//           System.out.println(thatcardvalue);
//           System.out.println(thiscardvalue+""\t""+thatcardvalue);
           
           if(thiscardvalue>thatcardvalue){return 1;}
           else if(thiscardvalue<thatcardvalue){return -1;}
           else if(thiscardvalue==6 && thatcardvalue==6){
                if((thisvalue[0]==thisvalue[1] && thisvalue[0]==thisvalue[2]) &&
                   (thatvalue[0]==thatvalue[1] && thatvalue[0]==thatvalue[2]) ){if(thisvalue[0]>thatvalue[0]){value= 1;}
                                                                                else if(thisvalue[0]<thatvalue[0]){value= -1;}
                                                                                else if(thisvalue[0]==thatvalue[0]){
                                                                                    if(Card.SUIT_ORDER.compare(this.cards[0],that.cards[0])>0){value= 1;}
                                                                                    else if(Card.SUIT_ORDER.compare(this.cards[0],that.cards[0])<0){value= -1;}
                                                                                    else if(thisvalue[4]>thatvalue[4]){value= 1;}
                                                                                    else if(thisvalue[4]<thatvalue[4]){value= -1;}
                                                                                    else if(thisvalue[4]==thatvalue[4]){
                                                                                      if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
                                                                                      else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
                                                                                        }
                                                                                    }
                                                                                }
                else if((thisvalue[4]==thisvalue[3] && thisvalue[4]==thisvalue[2]) &&
                        (thatvalue[4]==thatvalue[3] && thatvalue[4]==thatvalue[2]) ){if(thisvalue[4]>thatvalue[4]){value= 1;}
                                                                                else if(thisvalue[4]<thatvalue[4]){value= -1;}
                                                                                else if(thisvalue[4]==thatvalue[4]){
                                                                                    if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
                                                                                    else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
                                                                                    else if(thisvalue[0]>thatvalue[0]){value= 1;}
                                                                                    else if(thisvalue[0]<thatvalue[0]){value= -1;}
                                                                                    else if(thisvalue[0]==thatvalue[0]){
                                                                                      if(Card.SUIT_ORDER.compare(this.cards[0],that.cards[0])>0){value= 1;}
                                                                                      else if(Card.SUIT_ORDER.compare(this.cards[0],that.cards[0])<0){value= -1;}
                                                                                            }
                                                                                    }
                                                                                }
                else if(thisvalue[2]>thatvalue[2]){value= 1;}
                else if(thisvalue[2]<thatvalue[2]){value= -1;}
           }
           else if(thiscardvalue==5 && thatcardvalue==5){
                for(int i=4;i>=0;i--){
                    if(thisvalue[i]>thatvalue[i]){value= 1;break;}
                    else if(thisvalue[i]<thatvalue[i]){value= -1;break;}
                     }
            }
           else if(thiscardvalue==4 && thatcardvalue==4){

                    for(int i=4;i>=0;i--){
                    if(thisvalue[i]>thatvalue[i]){value= 1;break;}
                    else if(thisvalue[i]<thatvalue[i]){value= -1;break;}
                    else if(thisvalue[i]==thatvalue[i]){
                        if(Card.SUIT_ORDER.compare(this.cards[i],that.cards[i])>0){value= 1;break;}
                        else if(Card.SUIT_ORDER.compare(this.cards[i],that.cards[i])<0){value= -1;break;}
                         }
                    }
           }
          else if(thiscardvalue==3 && thatcardvalue==3){
                    if( thisvalue[4]==thisvalue[3] && thatvalue[4]==thatvalue[3] ){
                        if(thisvalue[4]>thatvalue[4]){value= 1;}
                        else if(thisvalue[4]<thatvalue[4]){value= -1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
                                }
                    else if( thisvalue[3]==thisvalue[2] && thatvalue[3]==thatvalue[2] ){
                        if(thisvalue[3]>thatvalue[3]){value= 1;}
                        else if(thisvalue[3]<thatvalue[3]){value= -1;}                        
                        else if(Card.SUIT_ORDER.compare(this.cards[3],that.cards[3])>0){value= 1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[3],that.cards[3])<0){value= -1;}
                                }
                     else if(thisvalue[3]>thatvalue[3]){value= 1;}
                     else if(thisvalue[3]<thatvalue[3]){value=-1;}
                      }
//          else if(thiscardvalue==3 && thatcardvalue==3){
//                    if( thisvalue[4]==thisvalue[3] && thatvalue[4]==thatvalue[3] ){
//                        if(thisvalue[4]>thatvalue[4]){value= 1;}
//                        else if(thisvalue[4]<thatvalue[4]){value= -1;}
//                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
//                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
//                                }
//                    else if( thisvalue[3]==thisvalue[2] && thatvalue[3]==thatvalue[2] ){
//                        if(thisvalue[3]>thatvalue[3]){value= 1;}
//                        else if(thisvalue[3]<thatvalue[3]){value= -1;}
//                        else if(Card.SUIT_ORDER.compare(this.cards[3],that.cards[3])>0){value= 1;}
//                        else if(Card.SUIT_ORDER.compare(this.cards[3],that.cards[3])<0){value= -1;}
//                                }
//                     else if(thisvalue[3]>thatvalue[3]){value= 1;}
//                     else if(thisvalue[3]<thatvalue[3]){value=-1;}
//                      }
           else if(thiscardvalue==2 && thatcardvalue==2){
               int vi=0,va=0;
                  for( vi=4;vi>=1;vi--){
                      if(thisvalue[vi]==thisvalue[vi-1]){break;}
                  }
                  for( va=4;va>=1;va--){
                      if(thisvalue[va]==thisvalue[va-1]){break;}
                  }
                  if(vi>va){value= 1;}
                  else if(vi>va){value= -1;}
                  else if(vi==va){
                        if(Card.SUIT_ORDER.compare(this.cards[vi],that.cards[va])>0){value= 1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[vi],that.cards[va])<0){value= -1;}
                  }
           }
           else if(thiscardvalue==1 && thatcardvalue==1){
               if(thisvalue[4]>thatvalue[4]){value= 1;}
               else if(thisvalue[4]<thatvalue[4]){value= -1;}
               else if(thisvalue[4]==thatvalue[4]){
                        if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])>0){value= 1;}
                        else if(Card.SUIT_ORDER.compare(this.cards[4],that.cards[4])<0){value= -1;}
               }
                   }
           
           return value;
.
    }

}
"r04631026","0.092","105696","@4929e030be06f614c34349edac958ba8@public class Player implements Comparable<Player> {

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
                        side = 4;
                    }
                }
            }
        }
        return a;
    }

    private int isstraight(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) - facetointeger(cards[1].getFace()) == -1) {
            if (facetointeger(cards[1].getFace()) - facetointeger(cards[2].getFace()) == -1) {
                if (facetointeger(cards[2].getFace()) - facetointeger(cards[3].getFace()) == -1) {
                    if (facetointeger(cards[3].getFace()) - facetointeger(cards[4].getFace()) == -1) {
                        a = 1;
                    }
                }
            }
        }
        return a;
    }
     private int pairsnumber2(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 3;
            }
            if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace()) ){
                a=1;
                side = 4;
            }
        }
        if(facetointeger(cards[2].getFace())==facetointeger(cards[1].getFace()) && facetointeger(cards[4].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 4;
            
        }
                  
        return a;
    }
        
        private int pairsnumber1(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            a=1;
            side = 1;
            if(facetointeger(cards[0].getFace())==facetointeger(cards[2].getFace()) ) side = 2;
        }else if(facetointeger(cards[1].getFace())==facetointeger(cards[2].getFace())  ){
            a=1;
            side = 2;
            if(facetointeger(cards[1].getFace())==facetointeger(cards[3].getFace()) ) side = 3;
        }else if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace())  ){
            a=1;
            side = 3;
            if(facetointeger(cards[2].getFace())==facetointeger(cards[4].getFace()) ) side = 4;
        }else if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace())  ){
            a=1;
            side = 4;
        }
                  
        return a;
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
        }else if(pairsnumber2(this.cards)==1){
            rank1=3;
            side1=side;
        }else if(pairsnumber1(this.cards)==1){
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
        }else if(pairsnumber2(that.cards)==1){
            rank2=3;
            side2=side;
        }else if(pairsnumber1(that.cards)==1){
            rank2=2;
            side2=side;
        }else{
            rank2=1;
        }
        if(rank1>rank2) return 1;
        if(rank1<rank2) return -1;
        if(rank1==rank2){
            if(rank1==6) {
                if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
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
            if(rank1==3 ){
                 if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
                else return -1;
            }
            if(rank1==2){
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
"b02611028","0.092","102464","@0dc11dc7b298d7f43d17b8ae7795d6a6@import java.util.Arrays;

/*
.
 * To change this template file, choose Tools | Templates
.
 */
/**
 *
 * @author Han
 */
public class Player implements Comparable<Player> {

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
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        // String a = this.cards[1].getFace();
        int[] points = new int[5];
        int[] suits =new int[5];
        int[] points2 = new int[5];
        int[] suits2 =new int[5];
        for (int i = 0; i < 5; i++) {
            
            if(this.cards[i].getSuit().equals(""Clubs""))suits[i]=1;
            if(this.cards[i].getSuit().equals(""Diamonds""))suits[i]=2;
            if(this.cards[i].getSuit().equals(""Hearts""))suits[i]=3;
            if(this.cards[i].getSuit().equals(""Spades""))suits[i]=4;
            
            if(that.cards[i].getSuit().equals(""Clubs""))suits2[i]=1;
            if(that.cards[i].getSuit().equals(""Diamonds""))suits2[i]=2;
            if(that.cards[i].getSuit().equals(""Hearts""))suits2[i]=3;
            if(that.cards[i].getSuit().equals(""Spades""))suits2[i]=4;
            
            if (!this.cards[i].getFace().equals(""J"") && !this.cards[i].getFace().equals(""Q"") && !this.cards[i].getFace().equals(""K"") && !this.cards[i].getFace().equals(""A"")) {
                points[i] = Integer.parseInt(this.cards[i].getFace());
            } else {
                if (this.cards[i].getFace().equals(""J"")) {
                    points[i] = 11;
                }
                if (this.cards[i].getFace().equals(""Q"")) {
                    points[i] = 12;
                }
                if (this.cards[i].getFace().equals(""K"")) {
                    points[i] = 13;
                }
                if (this.cards[i].getFace().equals(""A"")) {
                    points[i] = 14;
                }
            }

            if (!that.cards[i].getFace().equals(""J"") && !that.cards[i].getFace().equals(""Q"") && !that.cards[i].getFace().equals(""K"") && !that.cards[i].getFace().equals(""A"")) {
                points2[i] = Integer.parseInt(that.cards[i].getFace());
            } else {
                if (that.cards[i].getFace().equals(""J"")) {
                    points2[i] = 11;
                }
                if (that.cards[i].getFace().equals(""Q"")) {
                    points2[i] = 12;
                }
                if (that.cards[i].getFace().equals(""K"")) {
                    points2[i] = 13;
                }
                if (that.cards[i].getFace().equals(""A"")) {
                    points2[i] = 14;
                }
            }

        }
        

        int priority = 0;
        int priority2 = 0;

        int count = 0;
        int pairindex1 = 0;
        int pairindex2 = 0;

        for (int i = 0; i < 4; i++) {
            if (points[i] == points[i + 1]) {
                count = count + 1;
                pairindex1=i+1;//biggest pair card index
            }
        }
        if (count == 0) {
            priority = 1;
        }
        if (count == 1) {
            priority = 2;
        }

        if (points[0] == points[1] && points[2] == points[3] && points[1] != points[2] && points[4] != points[2] || points[1] == points[2] && points[3] == points[4] && points[0] != points[1]&& points[2] != points[3] || points[0] == points[1] && points[3] == points[4] && points[2] != points[1]&& points[2] != points[3]) {
            priority = 3;
        }
        if (points[0] + 1 == points[1] && points[1] + 1 == points[2] && points[2] + 1 == points[3] && points[3] + 1 == points[4]) {
            priority = 4;
        }
        if (suits[0]==suits[1]&&suits[1]==suits[2]&&suits[2]==suits[3]&&suits[3]==suits[4]) {
            priority = 5;
        }
        if (points[0] == points[1] && points[2] == points[3] && points[3] == points[4] || points[0] == points[1] && points[1] == points[2] && points[3] == points[4]) {
            priority = 6;
        }

        count = 0;
        

        for (int i = 0; i < 4; i++) {
            if (points2[i] == points2[i + 1]) {
                count = count + 1;
            }
        }
        if (count == 0) {
            priority2 = 1;
        }
        count = 0;
        for (int i = 0; i < 4; i++) {
            if (points2[i] == points2[i + 1]) {
                count = count + 1;
                pairindex2 = i+1;
            }
        }

        if (count == 1) {
            priority2 = 2;
        }
        if (points2[0] == points2[1] && points2[2] == points2[3] && points2[1] != points2[2] && points2[4] != points2[2] || points2[1] == points2[2] && points2[3] == points2[4] && points2[0] != points2[1]&& points2[2] != points2[3] || points2[0] == points2[1] && points2[3] == points2[4] && points2[2] != points2[1]&& points2[2] != points2[3]) {
            priority2 = 3;
        }
        if (points2[0] + 1 == points2[1] && points2[1] + 1 == points2[2] && points2[2] + 1 == points2[3] && points2[3] + 1 == points2[4]) {
            priority2 = 4;
        }
        if (suits2[0]==suits2[1]&&suits2[1]==suits2[2]&&suits2[2]==suits2[3]&&suits2[3]==suits2[4]) {
            priority2 = 5;
        }
        if (points2[0] == points2[1] && points2[2] == points2[3] && points2[3] == points2[4] || points2[0] == points2[1] && points2[1] == points2[2] && points2[3] == points2[4]) {
            priority2 = 6;
        }
.
        Card c = this.cards[0];
        Card d = that.cards[0];

        if (priority > priority2) {

            return 1;
        }
        if (priority < priority2) {
            return -1;
        }
        if (priority == priority2) {
            if (priority == 1) {
                for (int i = 1; i < 4; i++) {
                    if (c.compareTo(this.cards[i + 1]) == -1) {
                        c = this.cards[i + 1];
                    }
                    if (d.compareTo(that.cards[i + 1]) == -1) {
                        d = that.cards[i + 1];
                    }
                }
                if (c.compareTo(d) == -1) {
                    return -1;
                }
                if (c.compareTo(d) == 1) {
                    return 1;
                }
            }
            if(priority==2){
                if(points[pairindex1]>points2[pairindex2])return 1;
                if(points[pairindex1]<points2[pairindex2])return -1;
                if(points[pairindex1]==points2[pairindex2]){
                    if(suits[pairindex1]>suits2[pairindex2])return 1;
                    if(suits[pairindex1]<suits2[pairindex2])return -1;
                }
            }
            if(priority==3){
                if(points[3]>points2[3])return 1;
                if(points[3]<points2[3])return -1;
                
            }
            if(priority==4){
                if(points[4]>points2[4])return 1;
                if(points[4]<points2[4])return -1;
            }
            
            /*if (priority == 2){
                
             }*/

        }
        return 0;
    }
}

"b01b01039","0.092","107392","@9fe199ce8b645888e1f39fa06e5e0384@import java.util.Arrays;
import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;

public class Player implements Comparable<Player>{ 
	// HAND {full house > flush > straight > two pair > one pair > high card}
	private String[] FACE = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
	private String[] SUIT = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};	
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
        if(IsFH(this.cards) > IsFH(that.cards))      return  1;
		else if(IsFH(this.cards) < IsFH(that.cards)) return -1;
		else if(IsFl(this.cards) > IsFl(that.cards)) return  1;
		else if(IsFl(this.cards) < IsFl(that.cards)) return -1;
		else if(IsSt(this.cards) > IsSt(that.cards)) return  1;
		else if(IsSt(this.cards) < IsSt(that.cards)) return -1;
		else if(Is2P(this.cards) > Is2P(that.cards)) return  1;
		else if(Is2P(this.cards) < Is2P(that.cards)) return -1;
		else if(Is1P(this.cards) > Is1P(that.cards)) return  1;
		else if(Is1P(this.cards) < Is1P(that.cards)) return -1;
		else if(IsHC(this.cards) > IsHC(that.cards)) return  1;
		else if(IsHC(this.cards) < IsHC(that.cards)) return -1;
		else if(IsHS(this.cards) > IsHS(that.cards)) return  1;
		else if(IsHS(this.cards) < IsHS(that.cards)) return -1;
		else                                         return  0;
    }
	
	private int[] SF2IF(Card[] cards){
		int n = cards.length;
		int[] face = new int[n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < 13; j++){
				if(cards[i].getFace().equals(FACE[j])) face[i] = j;
			}
		}
		/*System.out.println(""==============="");
		for(Integer i : face){
			System.out.println(i);
		}
		System.out.println(""==============="");*/
		return face;
	}
	
	private int IsFH(Card[] cards){
		int[] face = SF2IF(cards);
		if (face[0] == face[1] && face[1] == face[2]){
			if  (face[3] == face[4])return  face[0];
			else                    return       -1;
		}else if (face[2] == face[3] && face[3] == face[4]){
			if (face[0] == face[1]) return  face[2];
			else                    return       -1;
		}else{
			return -1;
		}
	}
	
	private int IsFl(Card[] cards){
		int[] face = SF2IF(cards);
		//Card c = new Card(""2"", ""Clubs"");
		Arrays.sort(cards, cards[0].SUIT_ORDER);
		if (cards[0].getSuit().equals(cards[4].getSuit())){
			Arrays.sort(cards);
			return  face[4];
		}else{
			Arrays.sort(cards);
			return       -1;
		}
	}
	
	private int IsSt(Card[] cards){
		int[] face = SF2IF(cards);
		if(face[4] == 12 && face[0] == 0){  //2(0) 3(1) 4(2) 5(3) A(12)
			if(face[1] == 1 && face[2] == 2 && face[3] == 3) return  0;
			else                                           return -1;
		}else{
			for(int i = 1; i < 5; i++){
				if(face[i] - face[i-1] != 1) break;
				if(i == 4) return face[4];
			}
		}
		return -1;
	}
	
	private int Is2P(Card[] cards){
		int[] face = SF2IF(cards);
		if(face[0] == face[1]){
			if(face[2] == face[3]) return face[2];
			if(face[3] == face[4]) return face[3];
		}else if(face[1] == face[2]){
			if(face[3] == face[4]) return face[3];
		}
		return -1;
	}
	
	private int Is1P(Card[] cards){
		int[] face = SF2IF(cards);
		if(face[0] == face[1]) return face[0];
		if(face[1] == face[2]) return face[1];
		if(face[2] == face[3]) return face[2];
		if(face[3] == face[4]) return face[3];
		return -1;
	}
	
	private int IsHC(Card[] cards){
		int[] face = SF2IF(cards);
		return face[4];
	}

	private int IsHS(Card[] cards){
		int s = 0;
		for(int i = 0; i < 4; i++){
			if(cards[4].getSuit().equals(SUIT[i])) s = i;
		}
		return -s;
	}
	
	public void Hand(){
		if(IsFH(cards)>0)      System.out.println(""FH"");
		else if(IsFl(cards)>=0) System.out.println(""Fl"");
		else if(IsSt(cards)>=0) System.out.println(""St"");
		else if(Is2P(cards)>=0) System.out.println(""2P"");
		else if(Is1P(cards)>=0) System.out.println(""1P"");
		else if(IsHC(cards)>=0) System.out.println(""HC"");
		else if(IsHS(cards)>=0) System.out.println(""FH"");
	}
	
	public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                player.setCards(cardsArray);
            }

			/*for(int i=0 ; i<playerArray.length; i++){
				playerArray[i].Hand();
			}*/
			
            Arrays.sort(playerArray);

            System.out.println(playerArray[playerCount - 1].getName());
        }
    }
}


"b01b01039","0.096","104416","@9fe199ce8b645888e1f39fa06e5e0384@import java.util.Arrays;
import java.util.Comparator;
import java.io.FileReader;
import java.io.BufferedReader;

public class Player implements Comparable<Player>{ 
	// HAND {full house > flush > straight > two pair > one pair > high card}
	private String[] FACE = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
	private String[] SUIT = {""Clubs"", ""Diamonds"", ""Hearts"", ""Spades""};	
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
        if(IsFH(this.cards) > IsFH(that.cards))      return  1;
		else if(IsFH(this.cards) < IsFH(that.cards)) return -1;
		else if(IsFl(this.cards) > IsFl(that.cards)) return  1;
		else if(IsFl(this.cards) < IsFl(that.cards)) return -1;
		else if(IsSt(this.cards) > IsSt(that.cards)) return  1;
		else if(IsSt(this.cards) < IsSt(that.cards)) return -1;
		else if(Is2P(this.cards) > Is2P(that.cards)) return  1;
		else if(Is2P(this.cards) < Is2P(that.cards)) return -1;
		else if(Is1P(this.cards) > Is1P(that.cards)) return  1;
		else if(Is1P(this.cards) < Is1P(that.cards)) return -1;
		else if(IsHC(this.cards) > IsHC(that.cards)) return  1;
		else if(IsHC(this.cards) < IsHC(that.cards)) return -1;
		else if(IsHS(this.cards) > IsHS(that.cards)) return  1;
		else if(IsHS(this.cards) < IsHS(that.cards)) return -1;
		else                                         return  0;
    }
	
	private int[] SF2IF(Card[] cards){
		int n = cards.length;
		int[] face = new int[n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < 13; j++){
				if(cards[i].getFace().equals(FACE[j])) face[i] = j;
			}
		}
		/*System.out.println(""==============="");
		for(Integer i : face){
			System.out.println(i);
		}
		System.out.println(""==============="");*/
		return face;
	}
	
	private int IsFH(Card[] cards){
		int[] face = SF2IF(cards);
		if (face[0] == face[1] && face[1] == face[2]){
			if  (face[3] == face[4])return  face[0];
			else                    return       -1;
		}else if (face[2] == face[3] && face[3] == face[4]){
			if (face[0] == face[1]) return  face[2];
			else                    return       -1;
		}else{
			return -1;
		}
	}
	
	private int IsFl(Card[] cards){
		int[] face = SF2IF(cards);
		//Card c = new Card(""2"", ""Clubs"");
		Arrays.sort(cards, cards[0].SUIT_ORDER);
		if (cards[0].getSuit().equals(cards[4].getSuit())){
			Arrays.sort(cards);
			return  face[4];
		}else{
			Arrays.sort(cards);
			return       -1;
		}
	}
	
	private int IsSt(Card[] cards){
		int[] face = SF2IF(cards);
		if(face[4] == 12 && face[0] == 0){  //2(0) 3(1) 4(2) 5(3) A(12)
			if(face[1] == 1 && face[2] == 2 && face[3] == 3) return  0;
			else                                           return -1;
		}else{
			for(int i = 1; i < 5; i++){
				if(face[i] - face[i-1] != 1) break;
				if(i == 4) return face[4];
			}
		}
		return -1;
	}
	
	private int Is2P(Card[] cards){
		int[] face = SF2IF(cards);
		if(face[0] == face[1]){
			if(face[2] == face[3]) return face[2];
			if(face[3] == face[4]) return face[3];
		}else if(face[1] == face[2]){
			if(face[3] == face[4]) return face[3];
		}
		return -1;
	}
	
	private int Is1P(Card[] cards){
		int[] face = SF2IF(cards);
		if(face[0] == face[1]) return face[0];
		if(face[1] == face[2]) return face[1];
		if(face[2] == face[3]) return face[2];
		if(face[3] == face[4]) return face[3];
		return -1;
	}
	
	private int IsHC(Card[] cards){
		int[] face = SF2IF(cards);
		return face[4];
	}

	private int IsHS(Card[] cards){
		int s = 0;
		for(int i = 0; i < 4; i++){
			if(cards[4].getSuit().equals(SUIT[i])) s = i;
		}
		return -s;
	}
	
	public void Hand(){
		if(IsFH(cards)>0)      System.out.println(""FH"");
		else if(IsFl(cards)>=0) System.out.println(""Fl"");
		else if(IsSt(cards)>=0) System.out.println(""St"");
		else if(Is2P(cards)>=0) System.out.println(""2P"");
		else if(Is1P(cards)>=0) System.out.println(""1P"");
		else if(IsHC(cards)>=0) System.out.println(""HC"");
		else if(IsHS(cards)>=0) System.out.println(""FH"");
	}
	
	public static void main(String[] args) throws Exception{
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            int idx = 0;
            int playerCount = Integer.parseInt(br.readLine());
            Player[] playerArray = new Player[playerCount];

            for(String in = br.readLine(); in != null; in = br.readLine()) {
                String name = in;
                Player player = new Player(name);
                playerArray[idx++] = player;

                Card[] cardsArray = new Card[5];
                String[] cardStr = br.readLine().split("","");
                for(int i = 0; i < 5; i++){
                    String[] sep = cardStr[i].split(""_"");
                    Card card = new Card(sep[1], sep[0]);
                    cardsArray[i] = card;
                }
                player.setCards(cardsArray);
            }

			/*for(int i=0 ; i<playerArray.length; i++){
				playerArray[i].Hand();
			}*/
			
            Arrays.sort(playerArray);

            System.out.println(playerArray[playerCount - 1].getName());
        }
    }
}


"r04631046","0.098","105696","@4929e030be06f614c34349edac958ba8@public class Player implements Comparable<Player> {

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
                        side = 4;
                    }
                }
            }
        }
        return a;
    }

    private int isstraight(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) - facetointeger(cards[1].getFace()) == -1) {
            if (facetointeger(cards[1].getFace()) - facetointeger(cards[2].getFace()) == -1) {
                if (facetointeger(cards[2].getFace()) - facetointeger(cards[3].getFace()) == -1) {
                    if (facetointeger(cards[3].getFace()) - facetointeger(cards[4].getFace()) == -1) {
                        a = 1;
                    }
                }
            }
        }
        return a;
    }
     private int pairsnumber2(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 3;
            }
            if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace()) ){
                a=1;
                side = 4;
            }
        }
        if(facetointeger(cards[2].getFace())==facetointeger(cards[1].getFace()) && facetointeger(cards[4].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 4;
            
        }
                  
        return a;
    }
        
        private int pairsnumber1(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            a=1;
            side = 1;
            if(facetointeger(cards[0].getFace())==facetointeger(cards[2].getFace()) ) side = 2;
        }else if(facetointeger(cards[1].getFace())==facetointeger(cards[2].getFace())  ){
            a=1;
            side = 2;
            if(facetointeger(cards[1].getFace())==facetointeger(cards[3].getFace()) ) side = 3;
        }else if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace())  ){
            a=1;
            side = 3;
            if(facetointeger(cards[2].getFace())==facetointeger(cards[4].getFace()) ) side = 4;
        }else if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace())  ){
            a=1;
            side = 4;
        }
                  
        return a;
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
        }else if(pairsnumber2(this.cards)==1){
            rank1=3;
            side1=side;
        }else if(pairsnumber1(this.cards)==1){
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
        }else if(pairsnumber2(that.cards)==1){
            rank2=3;
            side2=side;
        }else if(pairsnumber1(that.cards)==1){
            rank2=2;
            side2=side;
        }else{
            rank2=1;
        }
        if(rank1>rank2) return 1;
        if(rank1<rank2) return -1;
        if(rank1==rank2){
            if(rank1==6) {
                if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
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
            if(rank1==3 ){
                 if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
                else return -1;
            }
            if(rank1==2){
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
"r03631015","0.092","105760","@135cf1c7cb424f63e5a2568dcdf49f89@public class Player implements Comparable<Player> {
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
                        side = 4;
                    }
                }
            }
        }
        return a;
    }
    private int isstraight(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) - facetointeger(cards[1].getFace()) == -1) {
            if (facetointeger(cards[1].getFace()) - facetointeger(cards[2].getFace()) == -1) {
                if (facetointeger(cards[2].getFace()) - facetointeger(cards[3].getFace()) == -1) {
                    if (facetointeger(cards[3].getFace()) - facetointeger(cards[4].getFace()) == -1) {
                        a = 1;
                    }
                }
            }
        }
        return a;
    }
     private int pairsnumber2(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 3;
            }
            if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace()) ){
                a=1;
                side = 4;
            }
        }
        if(facetointeger(cards[2].getFace())==facetointeger(cards[1].getFace()) && facetointeger(cards[4].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 4;
            
        }
                  
        return a;
    }
        
        private int pairsnumber1(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            a=1;
            side = 1;
            if(facetointeger(cards[0].getFace())==facetointeger(cards[2].getFace()) ) side = 2;
        }else if(facetointeger(cards[1].getFace())==facetointeger(cards[2].getFace())  ){
            a=1;
            side = 2;
            if(facetointeger(cards[1].getFace())==facetointeger(cards[3].getFace()) ) side = 3;
        }else if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace())  ){
            a=1;
            side = 3;
            if(facetointeger(cards[2].getFace())==facetointeger(cards[4].getFace()) ) side = 4;
        }else if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace())  ){
            a=1;
            side = 4;
        }
                  
        return a;
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
        }else if(pairsnumber2(this.cards)==1){
            rank1=3;
            side1=side;
        }else if(pairsnumber1(this.cards)==1){
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
        }else if(pairsnumber2(that.cards)==1){
            rank2=3;
            side2=side;
        }else if(pairsnumber1(that.cards)==1){
            rank2=2;
            side2=side;
        }else{
            rank2=1;
        }
        if(rank1>rank2) return 1;
        if(rank1<rank2) return -1;
        if(rank1==rank2){
            if(rank1==6) {
                if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
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
            if(rank1==3 ){
                 if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
                else return -1;
            }
            if(rank1==2){
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
"r04631031","0.094","105696","@4929e030be06f614c34349edac958ba8@public class Player implements Comparable<Player> {

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
                        side = 4;
                    }
                }
            }
        }
        return a;
    }

    private int isstraight(Card[] cards) {
        int a = 0;
        if (facetointeger(cards[0].getFace()) - facetointeger(cards[1].getFace()) == -1) {
            if (facetointeger(cards[1].getFace()) - facetointeger(cards[2].getFace()) == -1) {
                if (facetointeger(cards[2].getFace()) - facetointeger(cards[3].getFace()) == -1) {
                    if (facetointeger(cards[3].getFace()) - facetointeger(cards[4].getFace()) == -1) {
                        a = 1;
                    }
                }
            }
        }
        return a;
    }
     private int pairsnumber2(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 3;
            }
            if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace()) ){
                a=1;
                side = 4;
            }
        }
        if(facetointeger(cards[2].getFace())==facetointeger(cards[1].getFace()) && facetointeger(cards[4].getFace())==facetointeger(cards[3].getFace()) ){
                a=1;
                side = 4;
            
        }
                  
        return a;
    }
        
        private int pairsnumber1(Card[] cards){
        int a=0;
        if(facetointeger(cards[0].getFace())==facetointeger(cards[1].getFace())  ){
            a=1;
            side = 1;
            if(facetointeger(cards[0].getFace())==facetointeger(cards[2].getFace()) ) side = 2;
        }else if(facetointeger(cards[1].getFace())==facetointeger(cards[2].getFace())  ){
            a=1;
            side = 2;
            if(facetointeger(cards[1].getFace())==facetointeger(cards[3].getFace()) ) side = 3;
        }else if(facetointeger(cards[2].getFace())==facetointeger(cards[3].getFace())  ){
            a=1;
            side = 3;
            if(facetointeger(cards[2].getFace())==facetointeger(cards[4].getFace()) ) side = 4;
        }else if(facetointeger(cards[3].getFace())==facetointeger(cards[4].getFace())  ){
            a=1;
            side = 4;
        }
                  
        return a;
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
        }else if(pairsnumber2(this.cards)==1){
            rank1=3;
            side1=side;
        }else if(pairsnumber1(this.cards)==1){
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
        }else if(pairsnumber2(that.cards)==1){
            rank2=3;
            side2=side;
        }else if(pairsnumber1(that.cards)==1){
            rank2=2;
            side2=side;
        }else{
            rank2=1;
        }
        if(rank1>rank2) return 1;
        if(rank1<rank2) return -1;
        if(rank1==rank2){
            if(rank1==6) {
                if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
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
            if(rank1==3 ){
                 if(this.cards[side1].compareTo(that.cards[side2])==1) return 1;
                else return -1;
            }
            if(rank1==2){
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
"r04228027","0.09","102544","@62534dd00abcb80cde96ebfaed484698@
import java.util.Arrays;

public class Player implements Comparable<Player> {

    private Card[] cards = new Card[5];
    private String name;
    private int cardstype;

    private static int getfacecode(Card c) {
        switch (c.getFace()) {
            case ""A"":
                return 14;
            case ""K"":
                return 13;
            case ""Q"":
                return 12;
            case ""J"":
                return 11;
            default:
                return Integer.parseInt(c.getFace());
        }
    }

    private static int getsuitcode(Card c) {
        switch (c.getSuit()) {
            case ""Spades"":
                return 4;
            case ""Hearts"":
                return 3;
            case ""Diamonds"":
                return 2;
            case ""Clubs"":
                return 1;
            default:
                return 0;
        }
    }

    //full house = 5... high card = 0
    public int findcardstype() {

        int difffacecount = 1;
        for (int i = 0; i < 4; i++) {
            if (getfacecode(this.cards[i]) != getfacecode(this.cards[i + 1])) {
                difffacecount++;
            }
        }
        if (difffacecount == 2) {
            return 5; // full house
        }
        if (difffacecount == 3) {
            for (int i = 0; i < 3; i++) {
                if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1]) && getfacecode(this.cards[i + 1]) == getfacecode(this.cards[i + 2])) {
                    return 1; // 3 of a kind, equal to 1 pair
                }
            }
            return 2; // 2 pairs
        }
        if (difffacecount == 4) {
            return 1; // 1 pair
        }

        for (int i = 0; i < 4; i++) {
            if (getsuitcode(this.cards[i]) != getsuitcode(this.cards[i + 1])) {
                break;
            }
            if (i == 3) {
                return 4; // flush
            }
        }
        for (int i = 0; i < 4; i++) {
            if (getfacecode(this.cards[i]) + 1 != getfacecode(this.cards[i + 1])) {
                if (i == 3 && getfacecode(this.cards[i + 1]) == 14) {
                    return 3; // 2345A
                }
                break;
            }
            if (i == 3) {
                return 3; // straight         
            }
        }

        return 0;// high card
    }

    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }

    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
        Arrays.sort(this.cards);
        this.cardstype = this.findcardstype();
    }

    // TODO 
    public int compareTo(Player that) {
.

        if (this.cardstype < that.cardstype) {
            return -1;
        }
        if (this.cardstype > that.cardstype) {
            return +1;
        }

        //  
        switch (this.cardstype) {
            case 0: // high card
                return this.cards[4].compareTo(that.cards[4]);
            case 1: // 1 pair
                return this.find_onepair_max().compareTo(that.find_onepair_max());
            case 2: // 2 pairs
                return this.find_twopairs_max().compareTo(that.find_twopairs_max());
            case 3: // straight
                if (getsuitcode(this.cards[0]) == 2 && getsuitcode(that.cards[0]) == 10) {
                    return -1; //2345A & 10JQKA
                } else if (getsuitcode(this.cards[0]) == 10 &&getsuitcode(that.cards[0]) == 2) {
                    return +1; //10JQKA & 2345A
                } else if (getsuitcode(this.cards[0]) == 2 && getsuitcode(that.cards[0]) == 2) {
                    return this.cards[3].compareTo(that.cards[3]); // 2345A & 2345A, compare 4th card
                } else {
                    return this.cards[4].compareTo(that.cards[4]);
                }
            case 4: // flush
                return this.cards[4].compareTo(that.cards[4]);
            case 5: // full house
                return this.find_fullhouse_max().compareTo(that.find_fullhouse_max());
        }
        return 0;
    }

    private Card find_onepair_max() {
        for (int i = 0; i < 4; i++) {
            if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1])) {
                return this.cards[i + 1];
            }
        }
        return null;
    }

    private Card find_twopairs_max() {
        for (int i = 2; i < 4; i++) {
            if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1])) {
                return this.cards[i + 1];
            }
        }
        return null;
    }

    private Card find_fullhouse_max() {
        for (int i = 0; i < 3; i++) {
            if (getfacecode(this.cards[i]) == getfacecode(this.cards[i + 1]) &&getfacecode(this.cards[i + 1]) == getfacecode(this.cards[i + 2])) {
                return this.cards[i + 2];
            }
        }
        return null;
    }
}

"b03611003","0.088","105264","@f77384351c2a406ffe675dd63932dfb0@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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
        int typethis = this.typesofhand(this.cards);
        int typethat = this.typesofhand(that.cards);

        if (typethis > typethat) {
            return 1;
        } else if (typethis < typethat) {
            return -1;
        } else {
            if (typethis == 5) {
                return this.compareFull(that.cards);
            } else if (typethis == 4) {
                return this.compareFlush(that.cards);
            } else if (typethis == 3) {
                return this.compareStraight(that.cards);
            } else if (typethis == 2) {
                return this.compareTwopair(that.cards);
            } else if (typethis == 1) {
                return this.comparePair(that.cards);
            } else {
                return this.compareHigh(that.cards);
            }

        }
    }

    public static int toface(Card A) {
        if (""A"".equals(A.getFace())) {
            return 14;
        } else if (""J"".equals(A.getFace())) {
            return 11;
        } else if (""Q"".equals(A.getFace())) {
            return 12;
        } else if (""K"".equals(A.getFace())) {
            return 13;
        } else {
            return Integer.parseInt(A.getFace());
        }
    }

//    4 3 2 1 黑陶、紅心、方塊、梅花
    public static int tosuit(Card A) {
        if (""Spades"".equals(A.getSuit())) {
            return 4;
        } else if (""Hearts"".equals(A.getSuit())) {
            return 3;
        } else if (""Diamonds"".equals(A.getSuit())) {
            return 2;
        } else {
            return 1;
        }
    }
//    5 Full、4 Flush、3 Straight、2 two pair、1 one pair、0 High

    public int typesofhand(Card[] cards) {
        Arrays.sort(cards);

        int[] face = new int[5];
        int[] suit = new int[5];
        int[] numberofface = new int[15];  // 從2到14(A)
        int[] numberofsuit = new int[5];    // 從1(clubs)到4(spades)

        int fullhouse = 0;
        int pair = 0;
        int straight = 0;
        int flush = 0;

        for (int i = 0; i < 5; i++) {
            face[i] = Player.toface(cards[i]);
            suit[i] = Player.tosuit(cards[i]);
            numberofface[face[i]]++;
            numberofsuit[suit[i]]++;
        }

//        測重複 full、pair
//        Full house 要符合 fullhouse == 1、pair ==2
//        Pair 即 pair個數
//        Straight 符合 straight == 4
        for (int i = 2; i < 15; i++) {
            if (numberofface[i] == 4) {
                pair += 2;
            }
            if (numberofface[i] == 3) {
                fullhouse += 1;
                pair += 1;
            }
            if (numberofface[i] == 2) {
                pair += 1;
            }
            if (numberofface[i] == 1 && numberofface[i - 1] == 1) {
                straight += 1;
            }
            if (i == 5 && straight == 3) {
                if (numberofface[14] == 1) {
                    straight += 1;
                    break;
                }
            }
        }

//        flush == 1則是 Flush
        for (int i = 0; i < 4; i++) {
            if (numberofsuit[i] == 5) {
                flush += 1;
            }
        }

        if (fullhouse == 1 && pair == 2) {
            return 5;
        } else if (flush == 1) {
            return 4;
        } else if (straight == 4) {
            return 3;
        } else if (pair == 2) {
            return 2;
        } else if (pair == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    public int compareFull(Card[] that) {
        int thisthree = Player.toface(this.cards[2]);
        int thistwo;
        int thatthree = Player.toface(that[2]);
        int thattwo;

        if (thisthree == Player.toface(this.cards[0])) {
            thistwo = Player.toface(this.cards[4]);
        } else {
            thistwo = Player.toface(this.cards[2]);
        }

        if (thatthree == Player.toface(that[0])) {
            thattwo = Player.toface(that[4]);
        } else {
            thattwo = Player.toface(that[2]);
        }

        if (thisthree > thatthree) {
            return 1;
        } else if (thisthree < thatthree) {
            return -1;
        } else {
            if (thistwo > thattwo) {
                return 1;
            } else {
                return -1;
            }
        }

    }

    public int compareFlush(Card[] that) {
        if (Player.toface(this.cards[4]) > Player.toface(that[4])) {
            return 1;
        } else if (Player.toface(this.cards[4]) < Player.toface(that[4])) {
            return -1;
        } else {
            if (Player.toface(this.cards[3]) > Player.toface(that[3])) {
                return 1;
            } else if (Player.toface(this.cards[3]) < Player.toface(that[3])) {
                return -1;
            } else {
                if (Player.toface(this.cards[2]) > Player.toface(that[2])) {
                    return 1;
                } else if (Player.toface(this.cards[2]) < Player.toface(that[2])) {
                    return -1;
                } else {
                    if (Player.toface(this.cards[1]) > Player.toface(that[1])) {
                        return 1;
                    } else if (Player.toface(this.cards[1]) < Player.toface(that[1])) {
                        return -1;
                    } else {
                        if (Player.toface(this.cards[0]) > Player.toface(that[0])) {
                            return 1;
                        } else if (Player.toface(this.cards[0]) < Player.toface(that[0])) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }
    }

    public int compareStraight(Card[] that) {
        if (Player.toface(this.cards[4]) > Player.toface(that[4])) {
            return 1;
        } else if (Player.toface(this.cards[4]) < Player.toface(that[4])) {
            return -1;
        } else {
            return 0;
        }
    }

    public int compareTwopair(Card[] that) {
        int thispair1 = Player.toface(this.cards[3]);
        int thatpair1 = Player.toface(that[3]);
        int thispair2 = Player.toface(this.cards[1]);
        int thatpair2 = Player.toface(that[1]);
        int thiskicker1;
        int thatkicker1;

        if (thispair1 == Player.toface(this.cards[4])) {
            if (thispair2 == Player.toface(this.cards[2])) {
                thiskicker1 = Player.toface(this.cards[0]);
            } else {
                thiskicker1 = Player.toface(this.cards[2]);
            }
        } else {
            thiskicker1 = Player.toface(this.cards[4]);
        }

        if (thatpair1 == Player.toface(that[4])) {
            if (thatpair2 == Player.toface(that[2])) {
                thatkicker1 = Player.toface(that[0]);
            } else {
                thatkicker1 = Player.toface(that[2]);
            }
        } else {
            thatkicker1 = Player.toface(that[4]);
        }

        if (thispair1 > thatpair1) {
            return 1;
        } else if (thispair1 < thatpair1) {
            return -1;
        } else {
            if (thispair2 > thatpair2) {
                return 1;
            } else if (thispair2 < thatpair2) {
                return -1;
            } else {
                if (thiskicker1 > thatkicker1) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

    }

    public int comparePair(Card[] that) {
        int thispair;
        int thatpair;
        int pair1rank = 0;
        int pair2rank = 0;

        for (int i = 1; i < 5; i++) {
            if (Player.toface(this.cards[i]) == Player.toface(this.cards[i - 1])) {
                thispair = Player.toface(this.cards[i]);
                pair1rank = i;
            }
            if (Player.toface(that[i]) == Player.toface(that[i - 1])) {
                thatpair = Player.toface(that[i]);
                pair2rank = i;
            }
        }

        int[] thiskicker = new int[3];
        int[] thatkicker = new int[3];

        if (Player.toface(this.cards[pair1rank]) > Player.toface(that[pair2rank])) {
            return 1;
        } else if (Player.toface(this.cards[pair1rank]) < Player.toface(that[pair2rank])) {
            return -1;
        } else {
            if (pair1rank == 4) {
                thiskicker[0] = 0;
                thiskicker[1] = 1;
                thiskicker[2] = 2;
            } else if (pair1rank == 3) {
                thiskicker[0] = 0;
                thiskicker[1] = 1;
                thiskicker[2] = 4;
            } else if (pair1rank == 2) {
                thiskicker[0] = 0;
                thiskicker[1] = 3;
                thiskicker[2] = 4;
            } else if (pair1rank == 1) {
                thiskicker[0] = 2;
                thiskicker[1] = 3;
                thiskicker[2] = 4;
            }
            if (pair2rank == 4) {
                thatkicker[0] = 0;
                thatkicker[1] = 1;
                thatkicker[2] = 2;
            } else if (pair2rank == 3) {
                thatkicker[0] = 0;
                thatkicker[1] = 1;
                thatkicker[2] = 4;
            } else if (pair2rank == 2) {
                thatkicker[0] = 0;
                thatkicker[1] = 3;
                thatkicker[2] = 4;
            } else if (pair1rank == 1) {
                thatkicker[0] = 2;
                thatkicker[1] = 3;
                thatkicker[2] = 4;
            }

            if (Player.toface(this.cards[thiskicker[2]]) > Player.toface(that[thatkicker[2]])) {
                return 1;
            } else if (Player.toface(this.cards[thiskicker[2]]) < Player.toface(that[thatkicker[2]])) {
                return -1;
            } else {
                if (Player.toface(this.cards[thiskicker[1]]) > Player.toface(that[thatkicker[1]])) {
                    return 1;
                } else if (Player.toface(this.cards[thiskicker[1]]) < Player.toface(that[thatkicker[1]])) {
                    return -1;
                } else {
                    if (Player.toface(this.cards[thiskicker[0]]) > Player.toface(that[thatkicker[0]])) {
                        return 1;
                    } else if (Player.toface(this.cards[thiskicker[0]]) < Player.toface(that[thatkicker[0]])) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }

        }
    }

    public int compareHigh(Card[] that) {
        if (Player.toface(this.cards[4]) > Player.toface(that[4])) {
            return 1;
        } else if (Player.toface(this.cards[4]) < Player.toface(that[4])) {
            return -1;
        } else {
            if (Player.toface(this.cards[3]) > Player.toface(that[3])) {
                return 1;
            } else if (Player.toface(this.cards[3]) < Player.toface(that[3])) {
                return -1;
            } else {
                if (Player.toface(this.cards[2]) > Player.toface(that[2])) {
                    return 1;
                } else if (Player.toface(this.cards[2]) < Player.toface(that[2])) {
                    return -1;
                } else {
                    if (Player.toface(this.cards[1]) > Player.toface(that[1])) {
                        return 1;
                    } else if (Player.toface(this.cards[1]) < Player.toface(that[1])) {
                        return -1;
                    } else {
                        if (Player.toface(this.cards[0]) > Player.toface(that[0])) {
                            return 1;
                        } else if (Player.toface(this.cards[0]) < Player.toface(that[0])) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
            }
        }

    }
}

"b02611002","0.1","105456","@c38dfb933858e5c864f512840c299579@import java.util.*;

public class Player implements Comparable<Player>{

    private Card[] cards = new Card[5];
    private String name;

    enum CardType {
        full_house , flush , straight , two_pair , one_pair , high_card
    }

    private CardType cardType;
    public CardQuery cardQuery = new CardQuery();

    private static final SuitOrder SUIT_ORDER = new SuitOrder();
    private static final FaceOrder FACE_ORDER = new FaceOrder();
    private static final CardTypeOrder CARD_TYPE_ORDER = new CardTypeOrder();

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

    public CardType getCardType(){ return this.cardType; }

    public void setCardType(){
        Map<String,Integer> faces = cardQuery.getFaceCount();
        if (faces.size() == 2) {
            // (4,1) or (3,2)
            if (cardQuery.getThrees().size() != 0) {
                cardType = CardType.full_house;
                return;
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 3){
            // (3,1,1) or (2,2,1)
            if (cardQuery.getPairs().size() != 0) {
                cardType = CardType.two_pair;
                return;
            }
            cardType = CardType.high_card;
        } else if (faces.size() == 4){
            // (2,1,1,1)
            cardType = CardType.one_pair;
        } else if (faces.size() == 5){

            // check for flush
            if (cardQuery.getSuitSet().size() == 1) {
                cardType = CardType.flush;
                return;
            }

            // check for straight
            String[] straights = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2"",""A""};
            Set<String> faceSet = faces.keySet();
            for(int i = 0; i < straights.length - 5 ; i++){
                Set<String> straightOne = new HashSet<String>();
                for(int j = i ; j < i + 5 ; j++){
                    straightOne.add(straights[j]);
                }
                if (faceSet.containsAll(straightOne)) {
                   cardType = CardType.straight;
                   return;
                }
            }

            // high card otherwise
            cardType = CardType.high_card;
        }
    }

    // TODO 
    public int compareTo(Player that) {
.

        this.setCardType();
        that.setCardType();
        that.setCardType();
        int result = CARD_TYPE_ORDER.compare(this.getCardType(), that.getCardType());
        if (result != 0){
            return result;
        } else {
            // broke ties
            switch (this.getCardType()) {
                case full_house:
                    String max1 = Collections.max(this.cardQuery.getThrees() , FACE_ORDER);
                    String max2 = Collections.max(that.cardQuery.getThrees() , FACE_ORDER);
                    result = FACE_ORDER.compare(max1 , max2);
                    return result;

                case flush:
                    String flush1 = Collections.max(this.cardQuery.getSuitSet() , FACE_ORDER);
                    String flush2 = Collections.max(that.cardQuery.getSuitSet() , FACE_ORDER);
                    result = SUIT_ORDER.compare(flush1 , flush2);
                    if (result != 0) {
                        return result;
                    } else {
                        Set<String> suit1 = this.cardQuery.getSuitSet(flush1);
                        Set<String> suit2 = that.cardQuery.getSuitSet(flush2);
                        result = SUIT_ORDER.compare(Collections.max(suit1, SUIT_ORDER) , Collections.max(suit2, SUIT_ORDER));
                        return result;
                    }

                case two_pair:
                case one_pair:
                    String pair1 = Collections.max(this.cardQuery.getPairs() , FACE_ORDER);
                    String pair2 = Collections.max(that.cardQuery.getPairs() , FACE_ORDER);
                    result = FACE_ORDER.compare(pair1 , pair2);
                    if (result != 0) {
                       return result;
                    } else {
                        Set<String> suit1 = this.cardQuery.getSuitSet(pair1);
                        Set<String> suit2 = that.cardQuery.getSuitSet(pair1);
                        result = SUIT_ORDER.compare(Collections.max(suit1, SUIT_ORDER) , Collections.max(suit2, SUIT_ORDER));
                        return result;
                    }

                case high_card:
                case straight:

                    String high_card1 = Collections.max(this.cardQuery.getOnes() , FACE_ORDER);
                    String high_card2 = Collections.max(that.cardQuery.getOnes() , FACE_ORDER);

                    result = FACE_ORDER.compare(high_card1 , high_card2);
                    if (result != 0) {
                        return result;
                    } else {
                        Set<String> suit1 = this.cardQuery.getSuitSet(high_card1);
                        Set<String> suit2 = that.cardQuery.getSuitSet(high_card2);
                        result = SUIT_ORDER.compare(Collections.max(suit1, SUIT_ORDER) , Collections.max(suit2, SUIT_ORDER));
                        return result;
                    }
            }
        }
        return 0;
    }



    private static class SuitOrder implements Comparator<String> {
        private static List<String> order;
        SuitOrder() {
            String[] suits = {""Spades"", ""Hearts"", ""Diamonds"", ""Clubs""};
            order = new ArrayList<String>();
            for (String suit : suits) {
                order.add(suit);
            }
        }

        @Override
        public int compare(String suit1, String suit2) {
            int suit_1 = order.indexOf(suit1);
            int suit_2 = order.indexOf(suit2);
            if (suit_1 < suit_2) return 1;
            else if (suit_1 > suit_2) return -1;
            else return 0;
        }
    }

    private static class FaceOrder implements Comparator<String> {
        private static List<String> order;
        FaceOrder() {
            String[] faces = {""A"",""K"",""Q"",""J"",""10"",""9"",""8"",""7"",""6"",""5"",""4"",""3"",""2""};
            order = new ArrayList<String>();
            for (String face : faces) {
                order.add(face);
            }
        }

        @Override
        public int compare(String face1, String face2) {
            int face_1 = order.indexOf(face1);
            int face_2 = order.indexOf(face2);
            if (face_1 < face_2) return 1;
            else if (face_1 > face_2) return -1;
            else return 0;
        }
    }


    private static class CardTypeOrder implements Comparator<CardType> {
        private static List<CardType> order;
        CardTypeOrder() {
            CardType[] cardTypes = {
                    CardType.full_house,
                    CardType.flush,
                    CardType.straight,
                    CardType.two_pair,
                    CardType.one_pair,
                    CardType.high_card
            };
            order = new ArrayList<CardType>();
            for (CardType cardType : cardTypes) {
                order.add(cardType);
            }
        }

        @Override
        public int compare(CardType cardType1, CardType cardType2) {
            int cardType_1 = order.indexOf(cardType1);
            int cardType_2 = order.indexOf(cardType2);
            if (cardType_1 < cardType_2) return 1;
            else if (cardType_1 > cardType_2) return -1;
            else return 0;
        }
    }


    private class CardQuery {

        public Map<String , Integer> getFaceCount(){
            Map<String,Integer> faces = new HashMap<String,Integer>();
            for(Card c:cards){
                if (faces.containsKey(c.getFace()))
                    faces.put(c.getFace() , faces.get(c.getFace())+1);
                else
                    faces.put(c.getFace() , 1);
            }
            return faces;
        }

        public Set<String> getSuitSet(){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        public Set<String> getSuitSet(String face){
            Set<String> suitSet = new HashSet<String>();
            for (Card c:cards){
                if (face.equals(c.getFace()))
                    suitSet.add(c.getSuit());
            }
            return suitSet;
        }

        public Set<String> getOnes(){
            Set<String> ones = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 1)
                    ones.add(entry.getKey());
            }
            return ones;
        }

        public Set<String> getPairs(){
            Set<String> pairs = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 2)
                    pairs.add(entry.getKey());
            }
            return pairs;
        }

        public Set<String> getThrees(){
            Set<String> threes = new HashSet<String>();
            Map<String,Integer> faces = getFaceCount();
            for (Map.Entry<String,Integer> entry:faces.entrySet()) {
                if (entry.getValue() == 3)
                    threes.add(entry.getKey());
            }
            return threes;
        }

    }
}
"b03611015","0.092","105264","@09f1bddb0b104e5202af59b197b16f42@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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
        Card[] thishand = this.cards;
        Card[] thathand = that.cards;
        if (handtype(thishand) > handtype(thathand)) {
            return 1;
        } else if (handtype(thishand) == handtype(thathand)) {
            return 0;
        } else {
            return -1;
        }

    }

    public static int face(Card that) {
        if (""A"".equals(that.getFace())) {
            return 14;
        } else if (""J"".equals(that.getFace())) {
            return 11;
        } else if (""Q"".equals(that.getFace())) {
            return 12;
        } else if (""K"".equals(that.getFace())) {
            return 13;
        } else {
            return Integer.parseInt(that.getFace());
        }

    }

    public static double suit(Card A) {
        if (""Spades"".equals(A.getSuit())) {
            return 0.4;
        } else if (""Hearts"".equals(A.getSuit())) {
            return 0.3;
        } else if (""Diamonds"".equals(A.getSuit())) {
            return 0.2;
        } else {
            return 0.1;
        }
    }

    public double handtype(Card[] A) {

        Arrays.sort(A);
        int face[] = new int[5];
        double suit[] = new double[5];
        for (int i = 0; i < 5; i++) {
            suit[i] = suit(A[i]);
        }
        for (int i = 0; i < 5; i++) {
            face[i] = face(A[i]);
        }
        int num = 1;
        int count = 0;
        int fullhouse = 0;
        int fullhouseCard = 0;
        int straight = 1;
        int pairValue = 0;
        int pair = 0;
        int twopair = 0;
        int straightValue = 0;
        int flush = 1;
        double flushValue = 0;
        int pairmax = 0;

        for (int i = 1; i < 5; i++) {

            if (face[i] == face[i - 1]) {
                num++;
            }
            if (face[i] > face[i - 1]) {
                if (num == 2) {
                    num = 1;
                    count++;
                    pairmax = face[i - 1];
                }
                if (num == 3) {
                    num = 1;
                    fullhouse++;
                    fullhouseCard = face[i - 1];
                }
                if (face[i] == face[i - 1] + 1) {
                    straight++;
                }
            }
            if (i == 4) {
                if (num == 2) 
                        count++;
                if (count == 1) {
                    

                        if (pairmax < face[3]) {
                            pairmax = face[3];
                        }
                    
                    if (num == 3) {
                        fullhouse++;
                        fullhouseCard = face[i];
                    }
                }
                if (fullhouse == 1 && count == 0) {
                    pair = 1;
                    fullhouse = fullhouse - 1;
                }
            }
            if (count == 2 && i == 4) {
                twopair = 1;
            }
            if (count == 1 && i == 4) {
                if (fullhouse == 0) {
                    pair = 1;
                }

            }

            if (i == 4 && straight == 5) {
                straightValue = face[4];
            }

        }
        if (face[0] == 2 && face[1] == 3 && face[2] == 4 && face[3] == 5 && face[4] == 14) {
            straight = 5;
            straightValue = 5;
        }
        for (int i = 1; i < 5; i++) {
            if (suit[i] == suit[i - 1]) {
                flush++;
            }
            if (flush == 5) {
                flushValue = face[i];
            }
        }

        if (fullhouse == 1) {
            return 600 + fullhouseCard;
        } else if (flush == 5) {
            return 500 + flushValue;
        } else if (straight == 5) {
            return 400 + straightValue;
        } else if (twopair == 1) {
            return 300 + face[3];
        } else if (pair == 1) {
            return 200 + pairmax;
        } else {
            return 100 + face[4];
        }
    }
}

"b99611017","0.09","104960","@9ce8a500fa38d2afe148d519a065e349@
import java.util.Arrays;

public class Player implements Comparable<Player> {

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

    private int[] cardLabel(Card[] cards) {

        int[] label = new int[5];

        for (int i = 0; i < 5; i++) {

            int value = 0;
            if (""A"".equals(cards[i].getFace())) {
                value += 12;
            } else if (""K"".equals(cards[i].getFace())) {
                value += 11;
            } else if (""Q"".equals(cards[i].getFace())) {
                value += 10;
            } else if (""J"".equals(cards[i].getFace())) {
                value += 9;
            } else if (""10"".equals(cards[i].getFace())) {
                value += 8;
            } else if (""9"".equals(cards[i].getFace())) {
                value += 7;
            } else if (""8"".equals(cards[i].getFace())) {
                value += 6;
            } else if (""7"".equals(cards[i].getFace())) {
                value += 5;
            } else if (""6"".equals(cards[i].getFace())) {
                value += 4;
            } else if (""5"".equals(cards[i].getFace())) {
                value += 3;
            } else if (""4"".equals(cards[i].getFace())) {
                value += 2;
            } else if (""3"".equals(cards[i].getFace())) {
                value += 1;
            } else if (""2"".equals(cards[i].getFace())) {
                value += 0;
            }
            if (""Spades"".equals(cards[i].getSuit())) {
                value = value * 4 + 3;
            } else if (""Hearts"".equals(cards[i].getSuit())) {
                value = value * 4 + 2;
            } else if (""Diamonds"".equals(cards[i].getSuit())) {
                value = value * 4 + 1;
            } else if (""Clubs"".equals(cards[i].getSuit())) {
                value = value * 4;
            }
            label[i] = value;
        }
        return label;
    }

    private int findMax(int[] a) {

        int b = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b) {
                b = a[i];
            }
        }
        return b;
    }

    private int findGroup(int[] a) {
        int b = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                b++;
            }
        }
        return b;
    }

    //找大招
    private int[] combo(int[] card) {
        int faceGroup = 0;
        int suitGroup = 0;
        int fgMax = 0;
        int sgMax = 0;
        int ans[] = new int[2];

        int[] face = new int[13];
        int[] suit = new int[4];
        for (int i = 0; i < card.length; i++) {
            face[card[i] /4]++;
            suit[card[i] % 4]++;

        }

        faceGroup = findGroup(face);
        suitGroup = findGroup(suit);
        fgMax = findMax(face);
        sgMax = findMax(suit);
        //full house
        if (faceGroup == 2 && fgMax == 3) {
            ans[0] = 5;
            if(card[0]/4==card[2]/4)
            {
                ans[1]=card[2];
            }
            else if(card[0]/4!=card[2]/4)
            {
                ans[1]=card[4];
            }
            
        }
        //flush
        if (suitGroup == 1 && sgMax == 5 && ans[0] == 0) {

            ans[0] = 4;
            ans[1] = card[card.length-1];
           
        }
        //straight    and high card
        if (faceGroup == 5 && fgMax == 1 && ans[0] == 0) {

           int TF = 0;
            for (int i = 0; i < card.length - 2; i++) {
                if (card[i + 1] /4 == card[i] /4 + 1) {
                    TF +=1;
                } 
            }
            if ((TF==3&&card[3]/4+1==card[4]/4)||(TF==3&&card[3]/4+9==card[4]/4)) {
                ans[0] = 2;
                ans[1] = card[card.length-1];
                
            } else {
                ans[0] = 0;
                ans[1] = card[card.length - 1];
            }
           
        }
        //two pair
        if (faceGroup == 3 && fgMax == 2 && ans[0] == 0) {
            ans[0] = 2;
            int temp = 0;
            for (int i = 0; i < face.length; i++) {
                if (face[i] == 2) {
                    temp = i;
                }
            }
            for (int i = 0; i < card.length; i++) {
                if (card[i] /4 == temp) {
                    ans[1] = card[i];
                }
            }
           
        }
        //one pair
        if (faceGroup == 4 && fgMax == 2 && ans[0] == 0) {
            ans[0] = 1;
            int temp = 0;
            for (int i = 0; i < face.length; i++) {
                if (face[i] == 2) {
                    temp = i;
                }
            }
            for (int i = 0; i < card.length; i++) {
                if (card[i] /4 == temp) {
                    
                    ans[1] = card[i];
                }
            }
          
        }

        return ans;
    }

    // TODO 
    public int compareTo(Player that) {

        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
.

        int ans = 0;
        int[] a = combo(cardLabel(this.cards));
        int[] b = combo(cardLabel(that.cards));
        if (a[0] > b[0]) {
            ans = 1;
        } else if (a[0] < b[0]) {
            ans = -1;
        } else if (a[0] == b[0] && a[1] > b[1]) {
            ans = 1;
        } else if (a[0] == b[0] && a[1] < b[1]) {
            ans = -1;
        }
        return ans;

    }

}

"r03525006","0.092","108096","@7f1f22f10e6b8bfc3a382b917c7bdc78@import java.util.Arrays;

public class Player implements Comparable<Player>{

    public Card[] cards = new Card[5];
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

    public int[] faceArray(Card[] card) {
        String[] faceArray = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
        int[] ans = new int[5];
        int face = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++)
                if (card[i].getFace().equals(faceArray[j])) face = j+1;
            ans[i] = face;
        } return ans;
    }

    // TODO
    @Override
    public int compareTo(Player that) {
.
        
        int orderPlayer1 = 6;
        int orderPlayer2 = 6;
        // one pair
        if (this.isOnePair()) orderPlayer1 = 5;
        if (that.isOnePair()) orderPlayer2 = 5;
        // two pair
        if (this.isTwoPair()) orderPlayer1 = 4;
        if (that.isTwoPair()) orderPlayer2 = 4;
        // straight
        if (this.isStraight()) orderPlayer1 = 3;
        if (that.isStraight()) orderPlayer2 = 3;
        // flush
        if (this.isFlush()) orderPlayer1 = 2;
        if (that.isFlush()) orderPlayer2 = 2;
        // full house
        if (this.isFullHouse()) orderPlayer1 = 1;
        if (that.isFullHouse()) orderPlayer2 = 1;


        // compare
        if (orderPlayer1 < orderPlayer2) return 1;
        else if (orderPlayer1 > orderPlayer2) return -1;
        else {
            // full house
            if (orderPlayer1 == 1) {
                Card Player1 = this.getFullHouse();
                Card Player2 = that.getFullHouse();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // flush
            else if (orderPlayer1 == 2) {
                Card Player1 = this.getFlush();
                Card Player2 = that.getFlush();
                if(Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // straight
            else if (orderPlayer1 == 3) {
                Card Player1 = this.getStraight();
                Card Player2 = that.getStraight();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // two pair
            else if (orderPlayer1 == 4) {
                Card Player1 = this.getTwoPair();
                Card Player2 = that.getTwoPair();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // one pair
            else if (orderPlayer1 == 5) {
                Card Player1 = this.getOnePair();
                Card Player2 = that.getOnePair();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;
            }
            // high card
            else if (orderPlayer1 == 6) {
                Card Player1 = this.getHighCard();
                Card Player2 = that.getHighCard();
                if (Player1.compareTo(Player2) == 1) return 1;
                else if (Player1.compareTo(Player2) == -1) return -1;
                else return 0;

            } else return 0;
        }
    }

    public boolean isFullHouse() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getFace().equals(cards[j].getFace())) count++;
        return count == 4;
    }

    public boolean isFlush() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getSuit().equals(cards[j].getSuit())) count++;
        return count == 10;
    }

    public boolean isStraight() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        int[] faceNum = this.faceArray(cardCopy);
        int count = 0;
        if (cardCopy[4].getFace().equals(""A"") & cardCopy[0].getFace().equals(""2"")) {
            for (int i = 1; i < 4; i++)
                if (faceNum[i] == i+1) count++;
            return count == 3;
        } else {
            for (int i = 0; i < 4; i++)
                if (faceNum[i+1] - faceNum[i] == 1) count++;
            return count == 4;
        }
    }

    public boolean isTwoPair() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getFace().equals(cards[j].getFace())) count++;
        return count == 2;
    }

    public boolean isOnePair() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getFace().equals(cards[j].getFace())) count++;
        return count == 1;
    }

    private Card getFullHouse() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        int face1Count = 0;
        String face1 = cardCopy[4].getFace();
        for (int i = 0; i < 4; i++)
            if (face1.equals(cardCopy[i].getFace())) face1Count++;
        if (face1Count == 1) return cardCopy[2];
        else return cardCopy[4];
    }

    private Card getFlush() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        return cardCopy[4];
    }

    private Card getStraight() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        if (cardCopy[4].getFace().equals(""A"") & cardCopy[0].getFace().equals(""2"")) return cardCopy[3];
        else return cardCopy[0];
    }

    private Card getTwoPair() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        String face1 = cardCopy[4].getFace();
        int face1Count = 0;
        for (int i = 0; i < 4; i++)
            if (face1.equals(cardCopy[i].getFace())) face1Count++;
        if (face1Count == 1) return cardCopy[4];
        else return cardCopy[3];
    }

    private Card getOnePair() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        int pairIndex1 = 0;
        int pairIndex2 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (cardCopy[i].getFace().equals(cardCopy[j].getFace())) {
                    pairIndex1 = i;
                    pairIndex2 = j;
                    break;
                }
            }
        }
        if (cardCopy[pairIndex1].compareTo(cardCopy[pairIndex2]) == 1) return cardCopy[pairIndex1];
        else return cardCopy[pairIndex2];
    }

    private Card getHighCard() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        return cardCopy[4];
    }
}

"r03525006","0.092","108112","@62e9cdb246e7bfb52ff2ade48e80ab86@import java.util.Arrays;

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

    public int[] faceArray(Card[] card) {
        String[] faceArray = {""2"", ""3"", ""4"", ""5"", ""6"", ""7"", ""8"", ""9"", ""10"", ""J"", ""Q"", ""K"", ""A""};
        int[] ans = new int[5];
        int face = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++)
                if (card[i].getFace().equals(faceArray[j])) face = j+1;
            ans[i] = face;
        } return ans;
    }

    // TODO
    @Override
    public int compareTo(Player that) {
.

        int orderPlayer1 = 6;
        int orderPlayer2 = 6;
        // one pair
        if (this.isOnePair()) orderPlayer1 = 5;
        if (that.isOnePair()) orderPlayer2 = 5;
        // two pair
        if (this.isTwoPair()) orderPlayer1 = 4;
        if (that.isTwoPair()) orderPlayer2 = 4;
        // straight
        if (this.isStraight()) orderPlayer1 = 3;
        if (that.isStraight()) orderPlayer2 = 3;
        // flush
        if (this.isFlush()) orderPlayer1 = 2;
        if (that.isFlush()) orderPlayer2 = 2;
        // full house
        if (this.isFullHouse()) orderPlayer1 = 1;
        if (that.isFullHouse()) orderPlayer2 = 1;


        // compare
        if (orderPlayer1 < orderPlayer2) return 1;
        else if (orderPlayer1 > orderPlayer2) return -1;
        else {
            switch (orderPlayer1) {
                // full house
                case 1 :
                    Card fullHouse1 = this.getFullHouse();
                    Card fullHouse2 = that.getFullHouse();
                    if (fullHouse1.compareTo(fullHouse2) == 1) return 1;
                    else if (fullHouse1.compareTo(fullHouse2) == -1) return -1;
                    else return 0;

                // flush
                case 2 :
                    Card flush1 = this.getFlush();
                    Card flush2 = that.getFlush();
                    if (flush1.compareTo(flush2) == 1) return 1;
                    else if (flush1.compareTo(flush2) == -1) return -1;
                    else return 0;

                // straight
                case 3 :
                    Card straight1 = this.getStraight();
                    Card straight2 = that.getStraight();
                    if (straight1.compareTo(straight2) == 1) return 1;
                    else if (straight1.compareTo(straight2) == -1) return -1;
                    else return 0;

                // two pair
                case 4 :
                    Card twoPair1 = this.getTwoPair();
                    Card twoPair2 = that.getTwoPair();
                    if (twoPair1.compareTo(twoPair2) == 1) return 1;
                    else if (twoPair1.compareTo(twoPair2) == -1) return -1;
                    else return 0;

                // one pair
                case 5 :
                    Card onePair1 = this.getOnePair();
                    Card onePair2 = that.getOnePair();
                    if (onePair1.compareTo(onePair2) == 1) return 1;
                    else if (onePair1.compareTo(onePair2) == -1) return -1;
                    else return 0;

                // high card
                case 6 :
                    Card Player1 = this.getHighCard();
                    Card Player2 = that.getHighCard();
                    if (Player1.compareTo(Player2) == 1) return 1;
                    else if (Player1.compareTo(Player2) == -1) return -1;
                    else return 0;

            }
        } return 0;
    }

    public boolean isFullHouse() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getFace().equals(cards[j].getFace())) count++;
        return count == 4;
    }

    public boolean isFlush() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getSuit().equals(cards[j].getSuit())) count++;
        return count == 10;
    }

    public boolean isStraight() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        int[] faceNum = this.faceArray(cardCopy);
        int count = 0;
        if (cardCopy[4].getFace().equals(""A"") & cardCopy[0].getFace().equals(""2"")) {
            for (int i = 1; i < 4; i++)
                if (faceNum[i] == i+1) count++;
            return count == 3;
        } else {
            for (int i = 0; i < 4; i++)
                if (faceNum[i+1] - faceNum[i] == 1) count++;
            return count == 4;
        }
    }

    public boolean isTwoPair() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getFace().equals(cards[j].getFace())) count++;
        return count == 2;
    }

    public boolean isOnePair() {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = i + 1; j < 5; j++)
                if (cards[i].getFace().equals(cards[j].getFace())) count++;
        return count == 1;
    }

    private Card getFullHouse() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        int face1Count = 0;
        String face1 = cardCopy[4].getFace();
        for (int i = 0; i < 4; i++)
            if (face1.equals(cardCopy[i].getFace())) face1Count++;
        if (face1Count == 1) return cardCopy[2];
        else return cardCopy[4];
    }

    private Card getFlush() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        return cardCopy[4];
    }

    private Card getStraight() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        if (cardCopy[4].getFace().equals(""A"") & cardCopy[0].getFace().equals(""2"")) return cardCopy[3];
        else return cardCopy[0];
    }

    private Card getTwoPair() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        String face1 = cardCopy[4].getFace();
        int face1Count = 0;
        for (int i = 0; i < 4; i++)
            if (face1.equals(cardCopy[i].getFace())) face1Count++;
        if (face1Count == 1) return cardCopy[4];
        else return cardCopy[3];
    }

    private Card getOnePair() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        int pairIndex1 = 0;
        int pairIndex2 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                if (cardCopy[i].getFace().equals(cardCopy[j].getFace())) {
                    pairIndex1 = i;
                    pairIndex2 = j;
                    break;
                }
            }
        }
        if (cardCopy[pairIndex1].compareTo(cardCopy[pairIndex2]) == 1) return cardCopy[pairIndex1];
        else return cardCopy[pairIndex2];
    }

    private Card getHighCard() {
        Card[] cardCopy = new Card[5];
        for (int i = 0; i < 5; i++)
            cardCopy[i] = cards[i];
        Arrays.sort(cardCopy);
        return cardCopy[4];
    }
}

