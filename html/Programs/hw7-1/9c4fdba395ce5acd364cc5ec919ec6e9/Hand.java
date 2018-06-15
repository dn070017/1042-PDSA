
public class Hand implements Comparable<Hand> {

    class intCard {
        int intface;
        int intsuit;
        intCard(int intface, int intsuit) {
            this.intface = intface;
            this.intsuit = intsuit;
        }
    }
    
    // sorted by Card value are recommended but not necessary
    private Card[] cards;

    // TODO, Judge System will call this constructor once for each hand
    public Hand(Card[] cards) {
        this.cards = cards;
        return;
    }

    // TODO
    public int compareTo(Hand that) {
        return 0;
    }

    // Do not modified this function
    public Card[] getCards() {
        return this.cards;
    }
}

