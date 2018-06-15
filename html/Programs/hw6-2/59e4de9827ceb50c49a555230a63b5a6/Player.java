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
            if (!this.cards[i].getFace().equals(faceOrder[faceNum-i])) return false;
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

